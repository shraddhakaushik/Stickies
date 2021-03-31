package ui.options;

import model.StickyNote;
import ui.NotesApp;
import ui.StickyNoteApp;

import javax.swing.*;

//TODO Citation: code inspired by Tool abstract class in SimpleDrawingPlayer, and subclasses inspired by tool subclasses
//TODO URL: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git
//Represents an abstract class for the all menus on a sticky note
public abstract class Options {

    protected JMenu menu;
    protected StickyNoteApp noteApp;
    protected JMenuBar parent;


    //MODIFIES: this
    //EFFECTS: constructor for Options
    public Options(StickyNoteApp noteApp, JMenuBar parent) {
        this.noteApp = noteApp;
        this.parent = parent;
        createMenu(parent);
        parent.add(menu);
        addListener();
    }

    //EFFECTS: abstract method for createMenu
    public abstract void createMenu(JMenuBar parent);

    //EFFECTS: abstract method for addListener
    public abstract void addListener();

}
