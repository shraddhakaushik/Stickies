package ui;

import model.StickyNote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNote extends Options {

    private JFrame noteFrame;
    private StickyNote stickyNote;

    public NewNote(StickyNoteApp noteApp, JComponent parent) {
        super(noteApp, parent);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("New");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new NewNoteClickHandler());
    }

    private class NewNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(NewNote.this);
            StickyNote defaultNote = new StickyNote("untitled", "");
            noteApp.newSticky(defaultNote);
        }
    }
}
