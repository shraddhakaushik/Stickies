package ui.clickable.options;

import model.StickyNote;
import ui.NotesApp;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNote extends Options {

    private JMenuItem makeNote;
    private NotesApp notesApp;

    public NewNote(StickyNoteApp noteApp, JMenuBar parent, NotesApp notesApp) {
        super(noteApp, parent);
        this.notesApp = notesApp;
    }

    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("New");
        makeNote = new JMenuItem("Create Note");
        menu.add(makeNote);
        parent.add(menu);
    }

    @Override
    public void addListener() {
        makeNote.addActionListener(new NewNoteClickHandler());
    }

    private class NewNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp = new StickyNoteApp(notesApp);
            noteApp.setActiveChoice(NewNote.this);
            Color col = Color.WHITE;
            Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 12);
            StickyNote defaultNote = new StickyNote("untitled", "", col, font);
            noteApp.newSticky(defaultNote);
        }
    }
}
