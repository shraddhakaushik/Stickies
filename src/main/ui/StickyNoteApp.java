package ui;

import model.StickyNote;
import java.util.Scanner;

public class StickyNoteApp {
    private StickyNote sticky;
    private Scanner typed;

    public StickyNoteApp() {
        sticky = new StickyNote("untitled", "");
        typed = new Scanner(System.in);
    }

    //MODIFIES: this
    //EFFECTS: ask user if they want to change name. If user responds "yes", allow user to input new name and assign
    //         the new name to the note. Else, name remains "untitled"
    public void setName() {
        System.out.println("Would you like to name this note? Type yes to change the name");
        if (typed.nextLine().equals("yes")) {
            System.out.println("Type name below");
            sticky.assignName(typed.nextLine());
            System.out.println("New note: " + sticky.getName());
        } else {
            System.out.println("New note: untitled");
        }
    }

    //MODIFIES: this
    //EFFECTS: prints words typed and adds words to notes
    public String type() {
        sticky.assignNotes(typed.nextLine());
        return typed.nextLine();
    }

    //MODIFIES: this
    //EFFECTS: if user types "clear all", resets notes to have no words and prints empty
    public String clearNote() {
        if (typed.nextLine() == "clear all") {
            System.out.println("Type yes to confirm clearing all notes. Otherwise proceed to continue notes");
            if (typed.nextLine() == "yes") {
                sticky.assignNotes("");
                return "";
            }
        }
        return typed.nextLine();
    }

}
