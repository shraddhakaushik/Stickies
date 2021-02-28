package ui;

import model.StickyNote;
import model.SavedNotes;
import persistence.JsonWriter;
import persistence.JsonReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Sticky notes application
//TODO citation: code partially taken and modified from WorkRoomApp.java in JsonSerializationDemo
public class StickyNoteApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private StickyNote sticky;
    private Scanner typed;
    private SavedNotes savedNotes = new SavedNotes("NoteRoom");
    private String op = "";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private boolean keepGoing = true;

    //EFFECTS: constructor for StickyNoteApp
    public StickyNoteApp() {
        sticky = new StickyNote("untitled", "");
        typed = new Scanner(System.in);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        processTyped(sticky);
    }


    //EFFECTS: Interacts with user, keeps track of what user is typing, and responds to given commands appropriately
    private void processTyped(StickyNote stick) {
        startInteraction(stick);
        while (keepGoing) {
            processor(stick);
        }
    }

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
                System.out.println("Note found! type ACCESS to access note");
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
        if (typed.nextLine().equals("ACCESS")) {
            System.out.println("*************************************");
            System.out.println("Note name: " + sticky.getName());
            System.out.println("________________________________");
            System.out.println("Notes: " + sticky.getNotes());
            System.out.println("*************************************");
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
            for (StickyNote stickyNote : savedNotes.getSavedNotes()) {
                if (stickyNote.getName().equals(op)) {
                    System.out.println("Sorry, this name is taken!");
                    break;
                }
            }
            sticky.assignName(op);
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

}
