package ui.clickable.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditNote extends Options {
    private JMenuItem rename;
    private JMenuItem recolour;
    private JMenuItem font;
    private JMenuItem fontColour;

    public EditNote(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);
    }

    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Edit");
        parent.add(menu);
        rename = new JMenuItem("Rename");
        recolour = new JMenuItem("Change Note Colour");
        font = new JMenuItem("Change Font");
        fontColour = new JMenuItem("Change Font Colour");
        menu.add(rename);
        menu.add(recolour);
        menu.add(font);
        menu.add(fontColour);
    }

    @Override
    public void addListener() {
        recolour.addActionListener(new EditNoteClickHandler());
        fontColour.addActionListener(new EditFontColorClickHandler());
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

            JDialog swatch = JColorChooser.createDialog(null, "Colour:", false, colourSwatch, new OkListener(), null);
            swatch.add(colourSwatch);
            colourSwatch.setColor(Color.WHITE);
            swatch.setVisible(true);
        }
    }

    private class EditFontColorClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

