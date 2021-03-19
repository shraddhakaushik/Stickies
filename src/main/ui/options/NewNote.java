package ui.options;

import model.StickyNote;
import ui.NotesApp;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a "New" menu
public class NewNote extends Options {

    private JMenuItem makeNote;
    private NotesApp notesApp;

    //MODIFIES: this
    //EFFECTS: constructor for NewNote
    public NewNote(StickyNoteApp noteApp, JMenuBar parent, NotesApp notesApp) {
        super(noteApp, parent);
        this.notesApp = notesApp;
    }

    //MODIFIES: this
    //EFFECTS: sets up the menu with menu item "Create Note"
    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("New");
        makeNote = new JMenuItem("Create Note");
        menu.add(makeNote);
        parent.add(menu);
    }

    //MODIFIES: this
    //EFFECTS: adds an action listener to makeNote
    @Override
    public void addListener() {
        makeNote.addActionListener(new NewNoteClickHandler());
    }

    //Represents an action listener for NewNote
    private class NewNoteClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS:
        public void actionPerformed(ActionEvent e) {
            noteApp = new StickyNoteApp(notesApp);
            Color col = Color.WHITE;
            Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 12);
            StickyNote defaultNote = new StickyNote("untitled", "", col, font);
            noteApp.newSticky(defaultNote);
        }
    }
}
