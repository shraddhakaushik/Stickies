package ui;


import ui.options.NewNote;

import javax.swing.*;
import java.awt.*;

//Represents a Sticky Notes application
public class NotesApp extends JFrame {
    private StickyNoteApp stickyApp;

    //EFFECTS: constructor for notes app
    public NotesApp() {
        super("Stickies");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 300);
        setupMainMenu();
    }

    //EFFECTS: sets up the starting menu
    public void setupMainMenu() {
        JMenuBar panel = new JMenuBar();
        NewNote newNote = new NewNote(stickyApp, panel, this);
        panel.setLayout(new GridBagLayout());
        add(panel, BorderLayout.CENTER);
    }

}
