package ui;


import model.StickyNote;
import model.SavedNotes;
import persistence.JsonWriter;
import persistence.JsonReader;
import ui.options.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents a sticky note in a Sticky Notes application
//TODO citation: code partially taken and modified from WorkRoomApp.java in JsonSerializationDemo
//TODO URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

//TODO citation: code inspired by SimpleDrawingPlayer project
//TODO URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class StickyNoteApp {
    private static final String JSON_STORE = "./data/savedNotes.json";
    private StickyNote sticky;
    private Scanner typed;
    private SavedNotes savedNotes = new SavedNotes("NoteRoom");
    private String op = "";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean keepGoing = true;
    private List<Options> options;
    private JFrame frame;
    private JTextArea notes;
    private String name;
    private String noteNotes;
    private Font font;
    private Color color;
    private NotesApp app;
    private JMenu menu;


    //EFFECTS: constructor for StickyNoteApp
    public StickyNoteApp(NotesApp app) {
        this.app = app;
        initializeNoteApp();
    }

    //MODIFIES: this
    //EFFECTS: initializes all trivial fields and loads notes from saved JSON file
    public void initializeNoteApp() {
        menu = new JMenu("Menu");
        options = new ArrayList<>();
        font = new Font(Font.SANS_SERIF, Font.ITALIC, 12);
        noteNotes = "";
        name = "untitled";
        color = Color.WHITE;
        sticky = new StickyNote(name, noteNotes, color, font);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        loadSavedNotes();
    }

    //MODIFIES: this, note
    //EFFECTS: adds the current note saved notes and menu, and saves to JSON file
    public void saveAs(StickyNote note, NotesApp notesApp) {
        note.assignNotes(notes.getText());
        savedNotes.addNote(note);
        saveSavedNotes();
        addToMenu(menu, note);
        addToMenu(notesApp.getMenu(), note);
    }


    //MODIFIES: this
    //EFFECTS: changes name of note frame and updates note's name property
    public void rename(String name) {
        sticky.assignName(name);
        frame.setTitle(name);
    }

    //EFFECTS: adds given sticky note as a menu item to menu
    public void addToMenu(JMenu menu, StickyNote note) {
        SavedStickyNote savedStickyNote = new SavedStickyNote(note, menu, app);
    }


    //MODIFIES: this, note
    //EFFECTS: saves the current note temporarily, only to saved notes and menu
    public void saveNote(StickyNote note) {
        note.assignNotes(notes.getText());
        savedNotes.addNote(note);
        addToMenu(menu, note);
    }

    //MODIFIES: this
    //EFFECTS: changes font style of sticky to style
    public void fontStyleChange(int style) {
        font = new Font(sticky.getFont().getName(), style, sticky.getFont().getSize());
        sticky.assignFont(font);
        notes.setFont(font);
    }

    //MODIFIES: this
    //EFFECTS: changes font name of sticky to fam
    public void fontNameChange(String name) {
        font = new Font(name, sticky.getFont().getStyle(), sticky.getFont().getSize());
        sticky.assignFont(font);
        notes.setFont(font);
    }

    //MODIFIES: this
    //EFFECTS: changes font size of sticky to given int
    public void fontSizeChange(int size) {
        font = new Font(sticky.getFont().getName(), sticky.getFont().getStyle(), size);
        sticky.assignFont(font);
        notes.setFont(font);
    }

    //MODIFIES: this
    //EFFECTS: clears note
    public void clearIt() {
        int result = JOptionPane.showConfirmDialog(null, "Confirm clearing notes");
        if (result == JOptionPane.YES_OPTION) {
            notes.setText("");
            frame.add(notes);
            sticky.assignNotes("");
        }
    }

    public JMenu returnMenu() {
        return menu;
    }

    //MODIFIES: this
    //EFFECTS: creates a new JFrame representing the given sticky note with the corresponding properties, adds each note
    //          from file to menu, and adds options to note
    public void newSticky(StickyNote note) {
        playSound();
        name = note.getName();
        noteNotes = note.getNotes();
        font = note.getFont();
        color = note.getColor();
        note = new StickyNote(name, noteNotes, color, font);
        frame = new JFrame(name);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setSize(500, 200);
        frame.setBackground(color);
        notes = new JTextArea();
        notes.setSize(150, 150);
        notes.setText(note.getNotes());
        notes.setFont(font);
        notes.setBackground(color);
        frame.add(notes, BorderLayout.CENTER);
        createOptions(frame);
        addSavedToMenu(menu);
    }

    public void addSavedToMenu(JMenu menu) {
        for (StickyNote stickyNote : savedNotes.getSavedNotes()) {
            addToMenu(menu, stickyNote);
        }
    }


    //EFFECTS: plays pop sound
    public void playSound() {
        String pop = "./data/pop.wav";
        try {
            File path = new File(pop);
            if (path.exists()) {
                AudioInputStream input = AudioSystem.getAudioInputStream(path);
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                clip.start();
            } else {
                System.out.println("file not found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: changes the color of the note frame, text area, and updates the color property of the sticky note
    public void setColor(Color bgCol) {
        color = bgCol;
        frame.setBackground(color);
        notes.setBackground(color);
        sticky.assignColor(color);
    }

    //MODIFIES: this
    //EFFECTS: creates all option menus and adds them to options as well as a panel that is added to the sticky frame
    private void createOptions(JFrame stickyFrame) {
        JMenuBar panel = new JMenuBar();
        panel.setSize(0, 0);
        stickyFrame.add(panel, BorderLayout.NORTH);

        Options clrNote = new ClearNote(this, panel);
        options.add(clrNote);

        Options editNote = new EditNote(this, panel);
        options.add(editNote);

        Options newNote = new NewNote(this, panel, app);
        options.add(newNote);

        panel.add(menu);

        Options saveNote = new SaveNote(this, panel, sticky, app);
        options.add(saveNote);


    }

    //EFFECTS: saves saved notes to file
    public void saveSavedNotes() {
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
    public void loadSavedNotes() {
        try {
            savedNotes = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }




/*    //EVERYTHING PAST THIS POINT BELONGS TO THE PREVIOUSLY IMPLEMENTED CONTROL LINE INTERFACE

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
            StickyNote note = new StickyNote("untitled", "", color, font);
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
                System.out.println("New note: " + sticky.getName());
            } else {
                System.out.println("New note: " + sticky.getName());
            }
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
        StickyNote newNote = new StickyNote(sticky.getName(), sticky.getNotes(), sticky.getColor(), sticky.getFont());
        int pos = savedNotes.getSavedNotes().indexOf(sticky);
        savedNotes.getSavedNotes().set(pos, newNote);
        processTyped(newNote);
    }*/

}
