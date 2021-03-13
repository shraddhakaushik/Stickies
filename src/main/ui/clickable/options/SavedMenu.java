package ui.clickable.options;

import ui.StickyNoteApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedMenu extends Options {

    public SavedMenu(StickyNoteApp noteApp, JMenuBar parent) {
        super(noteApp, parent);
    }

    @Override
    public void createMenu(JMenuBar parent) {
        menu = new JMenu("Menu");
        parent.add(menu);
    }


    @Override
    public void addListener() {
        menu.addActionListener(new SavedMenuClickHandler());
    }

    private class SavedMenuClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveChoice(SavedMenu.this);
            menu = noteApp.getMenu();
        }
    }
}
