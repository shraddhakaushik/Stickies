package ui.clickable.options;

import model.StickyNote;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedStickyNote extends JMenuItem {
    private StickyNote stickyNote;
    private StickyNoteApp noteApp;


    public SavedStickyNote(StickyNote stickyNote, JMenu parent) {
        super(stickyNote.getName());
        this.stickyNote = stickyNote;
        createItem(parent);
    }

    public void createItem(JMenu parent) {
        parent.add(this);
    }

    public void addListener() {
        this.addActionListener(new StickyClickHandler());
    }

    public StickyNote getSticky() {
        return stickyNote;
    }

    private class StickyClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp.newSticky(stickyNote);
        }
    }


}
