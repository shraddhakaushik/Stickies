package ui;

import model.StickyNote;

public class Main {
    public static void main(String[] args) {
        StickyNoteApp sticky = new StickyNoteApp();
        sticky.setName();
        System.out.println("Type below");
        sticky.type();
        sticky.clearNote();
    }
}
