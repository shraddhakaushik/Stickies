package ui.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents an "Edit" menu
public class EditNote extends Options {
    private JMenuItem rename;
    private JMenuItem recolour;
    private JMenu font;
    private JMenu fontStyle;
    private JMenuItem fontSize;
    private JMenuItem italic;
    private JMenuItem bold;
    private JMenuItem plain;
    private JMenu fontType;
    private JMenuItem serif;
    private JMenuItem sansSerif;
    private JMenuItem monospaced;


    //EFFECTS: constructor for EditNote
    public EditNote(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);
    }

    //MODIFIES: this
    //EFFECTS: creates and sets up an "Edit" menu
    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Edit");
        parent.add(menu);
        rename = new JMenuItem("Rename");
        recolour = new JMenuItem("Change Note Colour");
        initializeFontEditor();
        menu.add(rename);
        menu.add(recolour);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the font editing options
    public void initializeFontEditor() {
        font = new JMenu("Change Font");
        fontType = new JMenu("Change Font");
        fontStyle = new JMenu("Change Font Style");
        fontSize = new JMenuItem("Change Font Size");
        plain = new JMenuItem("Plain");
        bold = new JMenuItem("Bold");
        italic = new JMenuItem("Italic");
        sansSerif = new JMenuItem("Sans Serif");
        serif = new JMenuItem("Serif");
        monospaced = new JMenuItem("Monospaced");
        fontType.add(sansSerif);
        fontType.add(serif);
        fontType.add(monospaced);
        fontStyle.add(bold);
        fontStyle.add(plain);
        fontStyle.add(italic);
        font.add(fontSize);
        font.add(fontStyle);
        font.add(fontType);
        menu.add(font);
    }

    //MODIFIES: this
    //EFFECTS: adds action listeners to all menu items
    @Override
    public void addListener() {
        recolour.addActionListener(new RecolourClickHandler());
        rename.addActionListener(new RenameClickHandler());
        plain.addActionListener(new StyleClickHandler());
        bold.addActionListener(new StyleClickHandler());
        italic.addActionListener(new StyleClickHandler());
        serif.addActionListener(new FontTypeClickHandler());
        sansSerif.addActionListener(new FontTypeClickHandler());
        monospaced.addActionListener(new FontTypeClickHandler());
        fontSize.addActionListener(new FontSizeClickHandler());
    }

    //Represents an action listener for recolour
    private class RecolourClickHandler implements ActionListener {

        JColorChooser colourSwatch = new JColorChooser();

        //MODIFIES: this
        //EFFECTS: creates a colour chooser for the user to edit the note colour
        public void actionPerformed(ActionEvent e) {

            JDialog swatch = JColorChooser.createDialog(null, "Colour:",
                    false, colourSwatch, new OkListener(), null);
            swatch.add(colourSwatch);
            colourSwatch.setColor(Color.WHITE);
            swatch.setVisible(true);
        }

        //Represents an action listener for the OK button on the colour chooser
        private class OkListener implements ActionListener {

            //MODIFIES: this
            //EFFECTS: Gets the colour selected from the chooser and applies it to the note frame
            public void actionPerformed(ActionEvent e) {
                Color col = colourSwatch.getColor();
                noteApp.setColor(col);
            }

        }
    }

    //Represents an action listener for rename
    private class RenameClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: sets the note's name to whatever the user enters in the option pane
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog(null,
                    "Enter name",
                    "Confirm name",
                    JOptionPane.INFORMATION_MESSAGE);

            noteApp.rename(name);
        }
    }

    //Represents an action listener for font size
    private class FontSizeClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: sets font size to whatever user enters in option pane
        @Override
        public void actionPerformed(ActionEvent e) {
            String size = JOptionPane.showInputDialog(null, "Enter size",
                    "Only input integer values",
                    JOptionPane.INFORMATION_MESSAGE);
            int num = Integer.parseInt(size);
            noteApp.fontSizeChange(num);
        }
    }

    //Represents an action listener for font style
    private class StyleClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: sets font style to style selected by user
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == plain) {
                noteApp.fontStyleChange(Font.PLAIN);
            } else if (e.getSource() == bold) {
                noteApp.fontStyleChange(Font.BOLD);
            } else if (e.getSource() == italic) {
                noteApp.fontStyleChange(Font.ITALIC);
            }
        }
    }

    //Represents an action listener for font type
    private class FontTypeClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: sets font type to type selected by user
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sansSerif) {
                noteApp.fontNameChange(Font.SANS_SERIF);
            } else if (e.getSource() == serif) {
                noteApp.fontNameChange(Font.SERIF);
            } else if (e.getSource() == monospaced) {
                noteApp.fontNameChange(Font.MONOSPACED);
            }
        }
    }
}

