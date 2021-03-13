package ui.clickable.options;

import model.StickyNote;
import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveNote extends Options {
    private StickyNote stickyNote;
    private JMenuItem save;
    private JMenuItem saveAs;
    private JMenuItem load;


    public SaveNote(StickyNoteApp noteApp, JMenuBar parent, StickyNote stickyNote) {
        super(noteApp, parent);
        this.stickyNote = stickyNote;

    }

    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Save");
        parent.add(menu);
        save = new JMenuItem("Save note temporarily");
        saveAs = new JMenuItem("Save note to file");
        load = new JMenuItem("Load saved notes");
        menu.add(save);
        menu.add(saveAs);
        menu.add(load);
    }

    @Override
    public void addListener() {
        save.addActionListener(new SaveNoteClickHandler());
        saveAs.addActionListener(new SaveAsClickHandler());
        load.addActionListener(new LoadClickHandler());
    }

    public StickyNote getSticky() {
        return stickyNote;
    }

    private class SaveNoteClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(SaveNote.this);
            noteApp.saveNote(stickyNote);
        }
    }

    private class SaveAsClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(SaveNote.this);
            noteApp.saveAs(stickyNote);
        }
    }

    private class LoadClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(SaveNote.this);
            noteApp.saveNote(stickyNote);
        }
    }
}
