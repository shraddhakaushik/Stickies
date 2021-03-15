package persistence;

import model.StickyNote;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSticky(String nm, String nt, int r, int g, int b, String ff, int fs, int fz, StickyNote sticky) {
        assertEquals(nm, sticky.getName());
        assertEquals(nt, sticky.getNotes());
        assertEquals(r, sticky.getColor().getRed());
        assertEquals(g, sticky.getColor().getGreen());
        assertEquals(b, sticky.getColor().getBlue());
        assertEquals(ff, sticky.getFont().getFamily());
        assertEquals(fs, sticky.getFont().getStyle());
        assertEquals(fz, sticky.getFont().getSize());
    }
}
