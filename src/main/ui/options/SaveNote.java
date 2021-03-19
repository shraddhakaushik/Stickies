package ui.options;

import model.StickyNote;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a save note option on the sticky note app
public class SaveNote extends Options {
    private StickyNote stickyNote;
    private JMenuItem save;
    private JMenuItem saveAs;

    //MODIFIES: this
    //EFFECTS: constructor for SaveNote
    public SaveNote(StickyNoteApp noteApp, JMenuBar parent, StickyNote stickyNote) {
        super(noteApp, parent);
        this.stickyNote = stickyNote;

    }

    //MODIFIES: this
    //EFFECTS: creates a menu with save options
    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Save");
        parent.add(menu);
        save = new JMenuItem("Save note temporarily");
        saveAs = new JMenuItem("Save note to file");
        menu.add(save);
        menu.add(saveAs);

    }

    //MODIFIES: this
    //EFFECTS: adds action listeners to all options
    @Override
    public void addListener() {
        save.addActionListener(new SaveNoteClickHandler());
        saveAs.addActionListener(new SaveAsClickHandler());

    }


    //Represents an action listener for save
    private class SaveNoteClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: saves sticky note temporarily
        public void actionPerformed(ActionEvent e) {
            noteApp.saveNote(stickyNote);
        }
    }

    //Represents an action listener for saveAs
    private class SaveAsClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: saves sticky note to JSON file
        public void actionPerformed(ActionEvent e) {
            noteApp.saveAs(stickyNote);
        }
    }

}
