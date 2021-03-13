package ui.clickable.options;

import ui.clickable.Clickable;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Options extends Clickable {

    protected JMenu menu;
    protected StickyNoteApp noteApp;
    private boolean active;


    public Options(StickyNoteApp noteApp, JMenuBar parent) {
        this.noteApp = noteApp;
        super.parent = parent;
        createMenu(parent);
        parent.add(menu);
        active = false;
        addListener();
    }

    protected JMenu customizeButton(JMenu menu) {
        menu.setBorderPainted(false);
        menu.setFocusPainted(true);
        menu.setContentAreaFilled(true);
        menu.setBackground(Color.lightGray);
        return menu;
    }

    public boolean isActive() {
        return active;
    }

    public abstract void createMenu(JMenuBar parent);

    public abstract void addListener();


}
