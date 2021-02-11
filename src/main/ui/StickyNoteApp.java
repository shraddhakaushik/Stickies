package ui;

import model.StickyNote;

import java.util.ArrayList;
import java.util.Scanner;

// Sticky notes application
public class StickyNoteApp {
    private StickyNote sticky;
    private Scanner typed;
    private ArrayList<StickyNote> savedNotes = new ArrayList<>();
    private String op = "";

    //EFFECTS: constructor for StickyNoteApp
    public StickyNoteApp() {
        sticky = new StickyNote("untitled", "");
        typed = new Scanner(System.in);
        processTyped(sticky);
    }


    //EFFECTS: Interacts with user, keeps track of what user is typing, and responds to given commands appropriately
    private void processTyped(StickyNote stick) {
        boolean keepGoing = true;
        startInteraction(stick);
        while (keepGoing) {
            op = typed.nextLine();
            if (op.equals("SAVE")) {
                savedNotes.add(stick);
                System.out.println("Note saved!");
            } else if (op.equals("CLEAR")) {
                clearNote(stick);
            } else if (op.equals("RENAME")) {
                nameNote(stick);
            } else if (op.equals("FIND")) {
                findSavedNote();
            } else if (op.equals("QUIT")) {
                keepGoing = false;
            } else if (op.equals("NEW NOTE")) {
                StickyNote note = new StickyNote("untitled", "");
                processTyped(note);
            } else {
                stick.assignNotes(op);
            }
        }

    }

    //EFFECTS: sets up note for user
    private void startInteraction(StickyNote sticky) {
        System.out.println("Welcome to your sticky note!");
        nameNote(sticky);
        System.out.println("Type notes below!");
        System.out.println("Here are some recognized commands: SAVE, CLEAR, RENAME, FIND, QUIT, NEW NOTE");
    }


    //REQUIRES: note has been saved at least once before. All changes must be re-saved.
    //EFFECTS: searches for note in list of saved notes and if found, allows user to access note
    public void findSavedNote() {
        System.out.println("Type the name of the note you would like to find:");
        String input = typed.nextLine();
        System.out.println("searching...");

        boolean found = false;
        for (StickyNote note : savedNotes) {
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
            System.out.println("Note name: " + sticky.getName());
            System.out.println("Notes: " + sticky.getNotes());
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
            sticky.assignName(typed.nextLine());
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
}
