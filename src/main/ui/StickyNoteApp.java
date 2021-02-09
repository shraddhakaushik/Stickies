package ui;

import model.StickyNote;

import java.util.ArrayList;
import java.util.Scanner;

public class StickyNoteApp {
    private StickyNote sticky;
    private Scanner typed;
    private ArrayList<StickyNote> savedNotes;

    public StickyNoteApp() {
        sticky = new StickyNote("untitled", "");
        typed = new Scanner(System.in);
        savedNotes = new ArrayList<>();
        processTyped();
    }

    private void processTyped() {
        boolean keepGoing = true;
        String op = "";
        System.out.println("Welcome to your sticky note!");
        nameNote();
        System.out.println("Type notes below!");

        while (keepGoing) {
            op = typed.next();
            if (op.equals("SAVE")) {
                saveNote(sticky);
            } else if (op.equals("CLEAR")) {
                clearNote();
            } else if (op.equals("RENAME")) {
                nameNote();
            } else if (op.equals("FIND")) {
                findSavedNote(sticky.getName());
            } else if (op.equals("QUIT")) {
                keepGoing = false;
            } else {
                type();
            }
        }
    }


    public void saveNote(StickyNote sticky) {
        savedNotes.add(sticky);
        System.out.println("Note saved!");
    }

    public void findSavedNote(String name) {
        System.out.println("searching...");

        for (StickyNote note: savedNotes) {
            if (name == note.getName()) {
                System.out.println("Note found! type ACCESS to access note");
                getNote(name);
            } else {
                System.out.println("This note could not be found");
            }
        }
    }

    public void getNote(String name) {
        if (typed.next().equals("ACCESS")) {
            System.out.println("Note name: " + sticky.getName());
            System.out.println("Notes: " + sticky.getName());
        } else {
            System.out.println("Not accessing note");
        }

    }


    //MODIFIES: this
    //EFFECTS: ask user if they want to change name. If user responds "yes", allow user to input new name and assign
    //         the new name to the note. Else, name remains unchanged
    public void nameNote() {
        System.out.println("Current name: " + sticky.getName());
        System.out.println("Would you like to change the name this note? Type yes to name");
        if (typed.next().equals("yes")) {
            System.out.println("Type name below");
            sticky.assignName(typed.next());
            System.out.println("New note: " + sticky.getName());
        } else {
            System.out.println("New note: " + sticky.getName());
        }
    }

    //MODIFIES: this
    //EFFECTS: prints words typed and adds words to notes
    public void type() {
        String boo = typed.nextLine();
        sticky.assignNotes(boo);
    }

    //MODIFIES: this
    //EFFECTS: if user types "clear all", resets notes to have no words and prints empty
    public String clearNote() {
        System.out.println("Type yes to confirm clearing all notes. Otherwise proceed to continue notes");
        if (typed.next().equals("yes")) {
            sticky.assignNotes("");
            System.out.println("notes cleared");
        }
        return "";
    }

}
