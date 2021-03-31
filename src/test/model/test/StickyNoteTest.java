package model.test;

import model.StickyNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class StickyNoteTest {
    private StickyNote sticky;
    private Font font;

    @BeforeEach
    public void setup() {
        sticky = new StickyNote("untitled","", Color.WHITE, font);
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    }

    @Test
    public void testAssignNameUnchanged() {
        assertEquals("untitled", sticky.getName());
    }

    @Test
    public void testAssignNameChangedDoesntExist() {
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

    @Test
    public void testAssignColorNoChange() {
        sticky.assignColor(Color.WHITE);
        assertEquals(Color.WHITE, sticky.getColor());
    }

    @Test
    public void testAssignColorChange() {
        sticky.assignColor(Color.BLACK);
        assertEquals(Color.BLACK, sticky.getColor());
    }

    @Test
    public void testAssignFontNoChange() {
        sticky.assignFont(font);
        assertEquals(font, sticky.getFont());
    }

    @Test
    public void testAssignFontChange() {
        Font newFont = new Font(Font.MONOSPACED, Font.ITALIC, 12);
        sticky.assignFont(newFont);
        assertEquals(newFont, sticky.getFont());
    }



}