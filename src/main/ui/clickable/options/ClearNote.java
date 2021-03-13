package ui.clickable.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearNote extends Options {

    public ClearNote(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);

    }

    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Clear");
        parent.add(menu);
    }

    @Override
    public void addListener() {
        menu.addActionListener(new ClearNoteClickHandler());
    }

    private class ClearNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(ClearNote.this);

        }
    }
}