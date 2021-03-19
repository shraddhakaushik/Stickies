package ui.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents a "Clear" menu
public class ClearNote extends Options {
    private JMenuItem clear;

    //EFFECTS: constructor for ClearNote
    public ClearNote(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);

    }

    //MODIFIES: this
    //EFFECTS: creates "Clear" menu with "Clear Notes" item
    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Clear");
        clear = new JMenuItem("Clear Notes");
        menu.add(clear);
        parent.add(menu);
    }

    //MODIFIES: this
    //EFFECTS: adds action listener to clear
    @Override
    public void addListener() {
        clear.addActionListener(new ClearNoteClickHandler());
    }

    //Represents an action listener for ClearNote
    private class ClearNoteClickHandler implements ActionListener {

        //MODIFIES: this
        //EFFECTS: clears the notes on the note
        public void actionPerformed(ActionEvent e) {
            noteApp.clearIt();
        }
    }
}