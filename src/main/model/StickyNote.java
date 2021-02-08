package model;


public class StickyNote {
    private String name;
    private String notes;
    private static final String DEFAULT_NAME = "untitled";

    public StickyNote(String name, String notes) {
        this.name = name;
        name = DEFAULT_NAME;
        this.notes = notes;
    }

    public void assignName(String rename) {
        name = rename;
    }

    public void assignNotes(String typed) {
        notes = typed;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }
}
