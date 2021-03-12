package ui;

import model.StickyNote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedSticky extends Options {
    private StickyNote savedNote;
    private String name;

    public SavedSticky(StickyNoteApp noteApp, JComponent parent, StickyNote savedNote) {
        super(noteApp, parent);
        savedNote = new StickyNote("untitled", "");
        this.savedNote = savedNote;
        name = savedNote.getName();
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Saved Note");
        addToParent(parent);

    }

    @Override
    public void addListener() {
        button.addActionListener(new SavedStickyClickHandler());
    }

    public StickyNote getSticky() {
        return savedNote;
    }

    private class SavedStickyClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(SavedSticky.this);
            noteApp.newSticky(savedNote);
        }

    }
}
