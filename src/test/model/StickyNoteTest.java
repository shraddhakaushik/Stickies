package model;

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
        assertEquals(sticky.getName(), "untitled");
    }

    @Test
    public void testAssignNameChanged() {
        sticky.assignName("new name");
        assertEquals(sticky.getName(), "new name");
    }

    @Test
    public void testAssignNotesNoNotes(){
        assertEquals(sticky.getNotes(), "");
    }

    @Test
    public void testAssignNotesNewNotes() {
        sticky.assignNotes("reminders");
        assertEquals(sticky.getNotes(), "reminders");
    }


}