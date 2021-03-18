package ui.clickable;

import javax.swing.*;

//Represents an abstract class that handles Options and SavedStickyNote's activation and deactivation
public abstract class Clickable {
    protected boolean active;
    protected JComponent parent;

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }
}
