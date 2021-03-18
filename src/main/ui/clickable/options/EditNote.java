package ui.clickable.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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



    public EditNote(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);
    }

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

    @Override
    public void addListener() {
        recolour.addActionListener(new EditNoteClickHandler());
        rename.addActionListener(new RenameClickHandler());
        plain.addActionListener(new StyleClickHandler());
        bold.addActionListener(new StyleClickHandler());
        italic.addActionListener(new StyleClickHandler());
        serif.addActionListener(new FontTypeClickHandler());
        sansSerif.addActionListener(new FontTypeClickHandler());
        monospaced.addActionListener(new FontTypeClickHandler());
        fontSize.addActionListener(new FontSizeClickHandler());
    }

    private class EditNoteClickHandler implements ActionListener {

        JColorChooser colourSwatch = new JColorChooser();

        private class OkListener implements ActionListener {

            public void actionPerformed(ActionEvent e) {
                Color col = colourSwatch.getColor();
                noteApp.setColor(col);
            }

        }

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(EditNote.this);

            JDialog swatch = JColorChooser.createDialog(null, "Colour:",
                    false, colourSwatch, new OkListener(), null);
            swatch.add(colourSwatch);
            colourSwatch.setColor(Color.WHITE);
            swatch.setVisible(true);
        }
    }

    private class RenameClickHandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(EditNote.this);
            String name = JOptionPane.showInputDialog(null,
                    "Enter name",
                    "Confirm name",
                    JOptionPane.INFORMATION_MESSAGE);

            noteApp.rename(name);
        }
    }

    private class FontSizeClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(EditNote.this);
            String size = JOptionPane.showInputDialog(null, "Enter size",
                    "Only input integer values",
                    JOptionPane.INFORMATION_MESSAGE);
            int num = Integer.parseInt(size);
            noteApp.fontSizeChange(num);
        }
    }

    private class StyleClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(EditNote.this);
            if (e.getSource() == plain) {
                noteApp.fontStyleChange(Font.PLAIN);
            } else if (e.getSource() == bold) {
                noteApp.fontStyleChange(Font.BOLD);
            } else if (e.getSource() == italic) {
                noteApp.fontStyleChange(Font.ITALIC);
            }
        }
    }

    private class FontTypeClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(EditNote.this);
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

