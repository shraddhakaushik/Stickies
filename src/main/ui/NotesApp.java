package ui;


import ui.clickable.options.NewNote;
import ui.clickable.options.SavedStickyNote;

import javax.swing.*;
import java.awt.*;

public class NotesApp extends JFrame {
    private StickyNoteApp stickyApp;
    private JMenu menu;

    public NotesApp() {
        super("Stickies");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 600);
        setupMainMenu();
        menu = new JMenu("Menu");
    }

    public void setupMainMenu() {
        JMenuBar panel = new JMenuBar();
        NewNote newNote = new NewNote(stickyApp, panel, this);
        panel.setLayout(new GridBagLayout());
        add(panel, BorderLayout.CENTER);
    }

    public JMenu getMenu() {
        return menu;
    }
}
