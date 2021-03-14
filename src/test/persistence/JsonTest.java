package persistence;

import model.StickyNote;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSticky(String nm, String nt, String col, String ff, int fs, int fz, StickyNote sticky) {
        assertEquals(nm, sticky.getName());
        assertEquals(nt, sticky.getNotes());
        assertEquals(col, sticky.getColor().toString());
        assertEquals(ff, sticky.getFont().getFamily());
        assertEquals(ff, sticky.getFont().getStyle());
        assertEquals(ff, sticky.getFont().getSize());
    }
}
