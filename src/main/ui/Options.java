package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Options {

    protected JButton button;
    protected StickyNoteApp noteApp;
    private boolean active;

    public Options(StickyNoteApp noteApp, JComponent parent) {
        this.noteApp = noteApp;
        createButton(parent);
        addToParent(parent);
        active = false;
        addListener();
    }

    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        button.setBackground(Color.lightGray);
        return button;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public abstract void createButton(JComponent parent);

    public abstract void addListener();

    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    public void mouseClickedOnScreen(MouseEvent me) {}
}
