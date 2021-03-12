package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindNote extends Options {

    public FindNote(StickyNoteApp noteApp, JComponent parent) {
        super(noteApp, parent);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Find");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new FindNoteClickHandler());
    }

    private class FindNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(FindNote.this);
        }
    }
}
