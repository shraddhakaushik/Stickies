package ui;

import model.StickyNote;
import model.SavedNotes;
import org.junit.jupiter.api.Test;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents a Sticky notes application
//TODO citation: code partially taken and modified from WorkRoomApp.java in JsonSerializationDemo
public class StickyNoteApp extends JFrame {
    private static final String JSON_STORE = "./data/workroom.json";
    private StickyNote sticky;
    private Scanner typed;
    private SavedNotes savedNotes = new SavedNotes("NoteRoom");
    private String op = "";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean keepGoing = true;
    private List<Options> options;
    private Options activeOp;
    private JFrame menuFrame;
    private JTextField notes;

    //EFFECTS: constructor for StickyNoteApp
    public StickyNoteApp() {
        super("Stickies");
        initializeNoteApp();
    }

    public void initializeNoteApp() {
        options = new ArrayList<>();
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
        super.setSize(600, 600);
        setupMainMenu();
        sticky = new StickyNote("untitled", "");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }



    public void saveNote(SaveNote note, JPanel panel) {
        SavedSticky note1 = new SavedSticky(this, panel, sticky);
        note.getSticky().assignNotes(notes.getText());
        savedNotes.addNote(note.getSticky());
        menuFrame.add(panel);

    }

    public void setupMainMenu() {
        JPanel panel = new JPanel();
        NewNote newNote = new NewNote(this, panel);
        SavedMenu menu = new SavedMenu(this, panel);
        panel.setLayout(new GridBagLayout());
        super.add(panel, BorderLayout.CENTER);
    }


    public void newSticky(StickyNote note) {
        JFrame frame = new JFrame(note.getName());
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setSize(200, 200);
        notes = new JTextField();
        notes.setSize(150, 150);
        notes.setText(note.getNotes());
        frame.add(notes, BorderLayout.CENTER);
        createOptions(frame);
    }

    public void menu() {
        menuFrame = new JFrame("menu");
        menuFrame.setVisible(true);
        menuFrame.setLayout(new BorderLayout());
        JTextField searchBar = new JTextField();
        searchBar.setSize(0, 0);
        menuFrame.add(searchBar, BorderLayout.NORTH);
        for (StickyNote stick : savedNotes.getSavedNotes()) {
            JPanel panel = new JPanel();
            SaveNote saveNote = new SaveNote(this, panel, stick);
            saveNote(saveNote, panel);
            menuFrame.add(panel, BorderLayout.CENTER);
        }
    }

    public void getMenuFrame() {
        menuFrame.setVisible(true);
    }

    public void edit(JFrame frame) {
        JButton rename = new JButton("Rename");
        JButton colour = new JButton("Colour");
        JButton font = new JButton("Font");
        JPopupMenu choices = new JPopupMenu();
        choices.setVisible(true);
        choices.setInvoker(frame);
        choices.add(rename);
        choices.add(colour);
        choices.add(font);
    }


    //EFFECTS: Interacts with user and responds to given commands appropriately
    private void processTyped(StickyNote stick) {
        startInteraction(stick);
        while (keepGoing) {
            processor(stick);
        }
    }

    //EFFECTS: Scans for recognized commands
    private void processor(StickyNote stick) {
        op = typed.nextLine();
        if (op.equals("SAVE")) {
            savedNotes.addNote(stick);
            System.out.println("Note saved!");
        } else if (op.equals("CLEAR")) {
            clearNote(stick);
        } else if (op.equals("RENAME")) {
            nameNote(stick);
        } else if (op.equals("FIND")) {
            findSavedNote();
        } else if (op.equals("NEW")) {
            StickyNote note = new StickyNote("untitled", "");
            processTyped(note);
        } else if (op.equals("FILE")) {
            saveSavedNotes();
        } else if (op.equals("LOAD")) {
            loadSavedNotes();
        } else if (op.equals("QUIT")) {
            keepGoing = false;
        } else {
            stick.assignNotes(op);
        }
    }

    //EFFECTS: sets up note for user
    private void startInteraction(StickyNote sticky) {
        System.out.println("Welcome to your sticky note!");
        nameNote(sticky);
        System.out.println("Type notes below!");
        System.out.println("Here are some recognized commands you can use at any time:");
        System.out.println("SAVE - adds note to temporary list of saved notes");
        System.out.println("CLEAR - clears notes of the sticky note");
        System.out.println("RENAME - changes name of note");
        System.out.println("FIND - searches for note in temporary saved notes to be viewed");
        System.out.println("NEW - creates new empty note. Previous note will exist in saved notes if saved");
        System.out.println("FILE - saves saved notes permanently to JSON file");
        System.out.println("LOAD - loads saved notes from permanently saved notes in JSON file");
        System.out.println("QUIT - closes app");
    }


    //REQUIRES: note has been saved at least once before. All changes must be re-saved.
    //EFFECTS: searches for note in list of saved notes and if found, allows user to access note
    public void findSavedNote() {
        System.out.println("Type the name of the note you would like to find:");
        String input = typed.nextLine();
        System.out.println("searching...");

        boolean found = false;
        for (StickyNote note : savedNotes.getSavedNotes()) {
            if (note.isName(input)) {
                System.out.println("Note found! type VIEW to view note, or EDIT to edit note");
                getNote(note);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Sorry, this note could not be found");
        }
    }

    //EFFECTS: If "ACCESS" is typed, accesses note and returns the note's details including name and notes
    public void getNote(StickyNote sticky) {
        String op = typed.nextLine();
        if (op.equals("VIEW")) {
            System.out.println("*************************************");
            System.out.println("Note name: " + sticky.getName());
            System.out.println("________________________________");
            System.out.println("Notes: " + sticky.getNotes());
            System.out.println("*************************************");
        } else if (op.equals("EDIT")) {
            System.out.println("Editing note: " + sticky.getName());
            editNote(sticky);
        } else {
            System.out.println("Not accessing note");
        }
    }


    //MODIFIES: this
    //EFFECTS: ask user if they want to change name. If user responds "yes", allow user to input new name and assign
    //         the new name to the note. Else, name remains unchanged
    public void nameNote(StickyNote sticky) {
        System.out.println("Current name: " + sticky.getName());
        System.out.println("Would you like to change the name this note? Type yes to name");
        if (typed.nextLine().equals("yes")) {
            System.out.println("Type name below");
            String op = typed.nextLine();
            Boolean found = true;
            for (StickyNote stickyNote : savedNotes.getSavedNotes()) {
                if (stickyNote.getName().equals(op)) {
                    System.out.println("Sorry, this name is taken!");
                    found = false;
                }
            }
            if (found) {
                sticky.assignName(op);
            }
            System.out.println("New note: " + sticky.getName());
        } else {
            System.out.println("New note: " + sticky.getName());
        }
    }


    //MODIFIES: this
    //EFFECTS: if user types "clear all", resets notes to have no words and prints empty
    public void clearNote(StickyNote sticky) {
        System.out.println("Type yes to confirm clearing all notes. Otherwise proceed to continue notes");
        if (typed.nextLine().equals("yes")) {
            sticky.clearNote();
            System.out.println("notes cleared");
        } else {
            System.out.println("not clearing notes");
        }
    }

    //MODIFIES: this
    //EFFECTS: allows user to edit the name and notes of a saved note
    public void editNote(StickyNote sticky) {
        StickyNote newNote = new StickyNote(sticky.getName(), sticky.getNotes());
        int pos = savedNotes.getSavedNotes().indexOf(sticky);
        savedNotes.getSavedNotes().set(pos, newNote);
        processTyped(newNote);
    }


    //EFFECTS: saves saved notes to file
    private void saveSavedNotes() {
        try {
            jsonWriter.open();
            jsonWriter.write(savedNotes);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads saved notes from file
    private void loadSavedNotes() {
        try {
            savedNotes = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void createOptions(JFrame stickyFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.setSize(0, 0);
        stickyFrame.add(panel, BorderLayout.NORTH);

        ClearNote clrNote = new ClearNote(this, panel);
        options.add(clrNote);

        EditNote editNote = new EditNote(this, panel);
        options.add(editNote);

        FindNote findNote = new FindNote(this, panel);
        options.add(findNote);

        NewNote newNote = new NewNote(this, panel);
        options.add(newNote);

        SavedMenu savedMenu = new SavedMenu(this, panel);
        options.add(savedMenu);

        SaveNote saveNote = new SaveNote(this, panel, sticky);
        options.add(saveNote);

        setActiveOption(newNote);

    }


    public void setActiveOption(Options option) {
        if (activeOp != null) {
            activeOp.deactivate();
        }
        option.activate();
        activeOp = option;
    }

}
