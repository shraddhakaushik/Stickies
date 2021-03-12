package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearNote extends Options {

    public ClearNote(StickyNoteApp noteApp, JComponent parent) {
        super(noteApp, parent);

    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Clear");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new ClearNoteClickHandler());
    }

    private class ClearNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(ClearNote.this);

        }
    }
}