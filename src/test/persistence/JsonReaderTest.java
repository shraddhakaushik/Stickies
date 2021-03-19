package persistence;


import model.SavedNotes;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

//TODO citation: code taken and modified from JsonReaderTest.java in JsonSerializationDemo
//TODO URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SavedNotes sn = reader.read();
            fail("IOException e");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySavedNotes() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySavedNotes.json");
        try {
            SavedNotes sn = reader.read();
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
            SavedNotes sn = reader.read();
            assertEquals(2, sn.getSavedNotes().size());
            checkSticky("untitled", "", 255, 255, 255, "Monospaced", 0, 12, sn.getSavedNotes().get(0));
            checkSticky("bloop", "hi", 255, 255, 255, "Monospaced", 0, 12, sn.getSavedNotes().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
