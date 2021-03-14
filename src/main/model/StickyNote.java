package model;


import org.json.JSONObject;

import java.awt.*;
import java.io.FileNotFoundException;

// Represents a sticky note having a name and notes
public class StickyNote {
    private String name; // tracks name of sticky note through name changes
    private String notes; // tracks notes attributed to sticky note
    private Color color; // tracks the colour of note
    private Font font; // tracks the font properties of note

    //EFFECTS: constructor for sticky note
    public StickyNote(String name, String notes, Color color, Font font) {
        this.name = name;
        this.notes = notes;
        this.color = color;
        this.font = font;
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

    //MODIFIES: this
    //EFFECTS: assigns given color to color
    public void assignColor(Color col) {
        color = col;
    }

    public void assignFont(Font f) {
        font = f;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
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
        String col = color.toString();
        String fontFamily = this.font.getFamily();
        int fontStyle = this.font.getStyle();
        int fontSize = this.font.getSize();
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("notes", notes);
        json.put("color", col);
        json.put("fontFamily", fontFamily);
        json.put("fontStyle", fontStyle);
        json.put("fontSize", fontSize);
        return json;
    }

}

