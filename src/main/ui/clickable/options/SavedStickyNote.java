package ui.clickable.options;

import model.StickyNote;
import ui.NotesApp;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedStickyNote extends JMenuItem {
    private StickyNote stickyNote;
    private StickyNoteApp noteApp;
    private NotesApp notesApp;


    public SavedStickyNote(StickyNote stickyNote, JMenu parent, NotesApp notesApp) {
        super(stickyNote.getName());
        this.stickyNote = stickyNote;
        this.notesApp = notesApp;
        createItem(parent);
        addListener();
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

    public class StickyClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp = new StickyNoteApp(notesApp);
            noteApp.newSticky(stickyNote);
        }
    }


}
