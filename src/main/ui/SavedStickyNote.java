package ui;

import model.StickyNote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a menu item holding a saved sticky note
public class SavedStickyNote extends JMenuItem {
    private StickyNote stickyNote;
    private StickyNoteApp noteApp;
    private NotesApp notesApp;


    //MODIFIES: this
    //EFFECTS: constructor for SavedStickyNote
    public SavedStickyNote(StickyNote stickyNote, JMenu parent, NotesApp notesApp) {
        super(stickyNote.getName());
        this.stickyNote = stickyNote;
        this.notesApp = notesApp;
        createItem(parent);
        addListener();
    }

    //MODIFIES: parent
    //EFFECTS: adds the saved sticky note to parent
    public void createItem(JMenu parent) {
        parent.add(this);
    }

    //MODIFIES: this
    //EFFECTS: adds an action listener to the saved note
    public void addListener() {
        this.addActionListener(new StickyClickHandler());
    }

    @Override
    //EFFECTS: returns the sticky note represented in the saved sticky;
    public String getName() {
        return stickyNote.getName();
    }

    //Represents an action listener for SavedStickyNote
    public class StickyClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: creates a new graphical sticky note with the sticky note object
        @Override
        public void actionPerformed(ActionEvent e) {
            noteApp = new StickyNoteApp(notesApp);
            noteApp.newSticky(stickyNote);
        }
    }


}
