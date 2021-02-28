package persistence;

import model.StickyNote;
import model.SavedNotes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

//Represents a reader that reads saved notes from JSON data stored in file
//TODO citation: code taken and modified from JsonReader.java in JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads and returns saved notes from file, and throws IOException if an error occurs in reading
    public SavedNotes reader() throws IOException {
        String data = readSource(source);
        JSONObject object = new JSONObject(data);
        return parseSavedNotes(object);
    }

    //EFFECTS: reads and returns source file as a string
    private String readSource(String source) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }
        return builder.toString();
    }

    //EFFECTS: parses workroom from JSON object and returns the object
    private SavedNotes parseSavedNotes(JSONObject object) {
        String name = object.getString("name");
        SavedNotes sn = new SavedNotes(name);
        addNotes(sn, object);
        return sn;
    }

    //MODIFIES: sn
    //EFFECTS: parses notes from JSON object and adds them to saved notes
    private void addNotes(SavedNotes sn, JSONObject object) {
        JSONArray array = object.getJSONArray("notes");
        for (Object json: array) {
            JSONObject nextNote = (JSONObject) json;
            addNote(sn, nextNote);
        }
    }

    //MODIFIES: sn
    //EFFECTS: parses notes from JSON object and adds it to saved notes
    private void addNote(SavedNotes sn, JSONObject object) {
        String name = object.getString("name");
        String notes = object.getString("notes");
        StickyNote sticky = new StickyNote(name, notes);
        sn.addNote(sticky);
    }


}
