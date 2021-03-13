package model.test;

import model.StickyNote;
import model.SavedNotes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavedNotesTest {
    private SavedNotes savedNotes;

    @BeforeEach
    public void setup() {
        this.savedNotes = new SavedNotes("My notes");
    }

    @Test
    public void testGetName() {
        assertEquals("My notes", savedNotes.getName());
    }

    @Test
    public void testAddNoteOne() {
        savedNotes.addNote(new StickyNote("untitled", ""));
        assertEquals(1, savedNotes.getSavedNotes().size());
    }

    @Test
    public void testAddNoteMany() {
        savedNotes.addNote(new StickyNote("untitled", ""));
        savedNotes.addNote(new StickyNote("hi", "hi"));
        assertEquals(2, savedNotes.getSavedNotes().size());
    }

}
