package ui;

import model.StickyNote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveNote extends Options {
    private StickyNote stickyNote;

    public SaveNote(StickyNoteApp noteApp, JComponent parent, StickyNote stickyNote) {
        super(noteApp, parent);
        this.stickyNote = stickyNote;

    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Save");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new SaveNoteClickHandler());
    }

    public StickyNote getSticky() {
        return stickyNote;
    }

    private class SaveNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(SaveNote.this);
            noteApp.menu();
        }
    }
}
