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
        String op = "";
        System.out.println("Welcome to your sticky note!");
        nameNote();
        System.out.println("Type notes below!");

        while (true) {
            op = typed.next();
            if (op.equals("SAVE")) {
                saveNote(sticky);
            } else if (op.equals("CLEAR")) {
                clearNote();
            } else if (op.equals("RENAME")) {
                nameNote();
            } else if (op.equals("QUIT")) {
                break;
            } else {
                type();
            }
        }
    }

    //else if (op.equals("FIND")) {
    //                findSavedNote(sticky.getName());
    public void saveNote(StickyNote sticky) {
        savedNotes.add(sticky);
        System.out.println("Note saved!");
    }

    public String findSavedNote(String name) {
        String result;
        result = "This note could not be found";
        for (StickyNote note : savedNotes) {
            if (name == note.getName()) {
                result = "Note found! type access to access note";
                getNote(name);
            }
        }
        return result;
    }

    public StickyNote getNote(String name) {
        if (typed.next().equals("access")) {
            return sticky;
        }
        System.out.println("Not accessing note");
        return null;
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
            System.out.println("New note: untitled");
        }
    }

    //MODIFIES: this
    //EFFECTS: prints words typed and adds words to notes
    public String type() {
        sticky.assignNotes(typed.next());
        return typed.next();
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
