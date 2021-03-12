package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditNote extends Options {
    public EditNote(StickyNoteApp noteApp, JComponent parent) {
        super(noteApp, parent);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Edit");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new EditNoteClickHandler());
    }

    private class EditNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(EditNote.this);
            JFrame frame = new JFrame();
            noteApp.edit(frame);
        }
    }
}

