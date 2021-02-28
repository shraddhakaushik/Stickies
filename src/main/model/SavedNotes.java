package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SavedNotes {

    ArrayList<StickyNote> savedNotes;
    String name;

    //EFFECTS: Constructs a new saved notes object with an empty arraylist
    public SavedNotes(String name) {
        savedNotes = new ArrayList<>();
        this.name = name;
    }

    //EFFECTS: returns savedNotes so external classes can access
    public ArrayList<StickyNote> getSavedNotes() {
        return savedNotes;
    }

    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds a note to saved notes
    public void addNote(StickyNote note) {
        savedNotes.add(note);
    }

    //EFFECTS: returns saved notes as JSON object
    //TODO citation: code taken and modified from WorkRoom.java in JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("notes", notesToJson());
        return json;
    }

    //EFFECTS: returns saved notes as JSON array
    //TODO citation: code taken and modified from WorkRoom.java in JsonSerializationDemo
    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (StickyNote s : savedNotes) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}


