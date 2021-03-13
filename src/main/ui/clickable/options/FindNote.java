package ui.clickable.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindNote extends Options {

    public FindNote(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);
    }

    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Find");
        parent.add(menu);
    }

    @Override
    public void addListener() {
        menu.addActionListener(new FindNoteClickHandler());
    }

    private class FindNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(FindNote.this);
        }
    }
}
