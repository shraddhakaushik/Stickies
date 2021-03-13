package ui.clickable;

import javax.swing.*;

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
