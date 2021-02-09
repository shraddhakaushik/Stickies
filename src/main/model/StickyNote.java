package model;


public class StickyNote {
    private String name;
    private String notes;

    public StickyNote(String name, String notes) {
        this.name = name;
        name = "untitled";
        this.notes = notes;
    }

    public void assignName(String rename) {
        name = rename;
    }

    public void assignNotes(String typed) {
        notes = notes + typed;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }
}
