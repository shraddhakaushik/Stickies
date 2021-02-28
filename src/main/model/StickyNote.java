package model;


import org.json.JSONObject;

// Represents a sticky note having a name and notes
public class StickyNote {
    private String name; // tracks name of sticky note through name changes
    private String notes; // tracks notes attributed to sticky note

    //EFFECTS: constructor for sticky note
    public StickyNote(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }

    //MODIFIES: this
    //EFFECTS: changes name to given rename
    public void assignName(String rename) {
        name = rename;
    }

    //MODIFIES: this
    //EFFECTS: attaches typed to notes
    public void assignNotes(String typed) {
        notes = notes + "\n" + typed;
    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }

    //EFFECTS: returns notes
    public String getNotes() {
        return notes;
    }

    //EFFECTS: returns true if given string is equal to name, else returns false
    public boolean isName(String name) {
        if (this.name.equals(name)) {
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: sets notes to "" when called upon
    public void clearNote() {
        notes = "";
    }


    //EFFECTS: returns sticky note as a JSON object
    //TODO citation: code taken and modified from Thingy.java in JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("notes", notes);
        return json;
    }

}

