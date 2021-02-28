package persistence;


import model.SavedNotes;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

//TODO citation: code taken and modified from JsonReaderTest.java in JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SavedNotes sn = reader.reader();
            fail("IOException e");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySavedNotes() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySavedNotes.json");
        try {
            SavedNotes sn = reader.reader();
            assertEquals("My notes", sn.getName());
            assertEquals(0, sn.getSavedNotes().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSN() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSavedNotes.json");
        try {
            SavedNotes sn = reader.reader();
            assertEquals(2, sn.getSavedNotes().size());
            checkSticky("untitled", "", sn.getSavedNotes().get(0));
            checkSticky("bloop", "hi", sn.getSavedNotes().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}