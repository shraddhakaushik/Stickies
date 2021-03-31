package model.test;

import model.StickyNote;
import model.SavedNotes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class SavedNotesTest {
    private SavedNotes savedNotes;
    private Font font;

    @BeforeEach
    public void setup() {
        this.savedNotes = new SavedNotes("My notes");
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    }

    @Test
    public void testGetName() {
        assertEquals("My notes", savedNotes.getName());
    }

    @Test
    public void testAddNoteOne() {
        savedNotes.addNote(new StickyNote("untitled", "", Color.WHITE, font));
        assertEquals(1, savedNotes.getSavedNotes().size());
    }

    @Test
    public void testAddNoteMany() {
        savedNotes.addNote(new StickyNote("untitled", "", Color.WHITE, font));
        savedNotes.addNote(new StickyNote("hi", "hi", Color.WHITE, font));
        assertEquals(2, savedNotes.getSavedNotes().size());
    }

}
