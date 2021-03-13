package model.test;

import model.StickyNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StickyNoteTest {
    private StickyNote sticky;

    @BeforeEach
    public void setup() {
        sticky = new StickyNote("untitled","");
    }

    @Test
    public void testAssignNameUnchanged() {
        assertEquals("untitled", sticky.getName());
    }

    @Test
    public void testAssignNameChanged() {
        sticky.assignName("new name");
        assertEquals("new name", sticky.getName());
    }

    @Test
    public void testAssignNotesNoNotes(){
        assertEquals("", sticky.getNotes());
    }

    @Test
    public void testAssignNotesNewNotes() {
        sticky.assignNotes("reminders");
        assertEquals("\nreminders", sticky.getNotes());
    }

    @Test
    public void testIsNameNotName() {
        assertFalse(sticky.isName("bloop"));
    }

    @Test
    public void testIsNameName() {
        assertTrue(sticky.isName("untitled"));
    }

    @Test
    public void testClearNoteAlreadyClear() {
        sticky.clearNote();
        assertEquals("", sticky.getNotes());
    }

    @Test
    public void testClearNoteNotClear() {
        sticky.assignNotes("hello");
        sticky.clearNote();
        assertEquals("", sticky.getNotes());
    }


}