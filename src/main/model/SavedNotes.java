package model;

import java.util.ArrayList;

public class SavedNotes {

    ArrayList<StickyNote> savedNotes;

    public SavedNotes() {
        savedNotes = new ArrayList<>();
    }

    public ArrayList<StickyNote> getSavedNotes() {
        return savedNotes;
    }


}
