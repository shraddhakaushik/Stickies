package ui;


import ui.options.NewNote;

import javax.swing.*;
import java.awt.*;

//Represents a Sticky Notes application
public class NotesApp extends JFrame {
    private StickyNoteApp stickyApp;
    private JMenu menu = new JMenu();

    //EFFECTS: constructor for notes app
    public NotesApp() {
        super("Stickies");
        stickyApp = new StickyNoteApp(this);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(300, 300);
        setupMainMenu();
    }

    //EFFECTS: returns menu
    public JMenu getMenu() {
        return menu;
    }

    //EFFECTS: sets up the starting menu
    public void setupMainMenu() {
        JMenuBar panel = new JMenuBar();
        NewNote newNote = new NewNote(stickyApp, panel, this);
        menu = stickyApp.returnMenu();
        stickyApp.loadSavedNotes();
        stickyApp.addSavedToMenu(menu);
        panel.add(menu);
        panel.setLayout(new GridBagLayout());
        add(panel, BorderLayout.CENTER);
    }

}
