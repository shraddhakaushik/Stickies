package persistence;

import model.SavedNotes;
import model.StickyNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//TODO citation: code taken and modified from JsonWriterTest.java in JsonSerializationDemo
//TODO URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {
    private Font font;
    private Color color;

    @BeforeEach
    void setupFont() {
        font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        color = Color.WHITE;
    }


    @Test
    void testWriterInvalidFile() {
        try {
            SavedNotes sn = new SavedNotes("My notes");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptySavedNotes() {
        try {
            SavedNotes sn = new SavedNotes("My notes");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNotes.json");
            writer.open();
            writer.write(sn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNotes.json");
            sn = reader.read();
            assertEquals("My notes", sn.getName());
            assertEquals(0, sn.getSavedNotes().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSavedNotes() {
        try {
            SavedNotes sn = new SavedNotes("My notes");
             sn.addNote(new StickyNote("untitled","", color, font));
            sn.addNote(new StickyNote("bloop","hello", color, font));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralNotes.json");
            writer.open();
            writer.write(sn);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralNotes.json");
            sn = reader.read();
            assertEquals("My notes", sn.getName());
            assertEquals(2, sn.getSavedNotes().size());
            checkSticky("untitled","", 255, 255, 255, "Monospaced", 0, 12, sn.getSavedNotes().get(0));
            checkSticky("bloop","hello", 255, 255, 255, "Monospaced", 0, 12, sn.getSavedNotes().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
