package persistence;

import model.StickyNote;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSticky(String name, String notes, StickyNote sticky) {
        assertEquals(name, sticky.getName());
        assertEquals(notes, sticky.getNotes());
    }
}
