package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavedMenu extends Options {
    public SavedMenu(StickyNoteApp noteApp, JComponent parent) {
        super(noteApp, parent);
    }

    @Override
    public void createButton(JComponent parent) {
        button = new JButton("Menu");
        addToParent(parent);
    }

    @Override
    public void addListener() {
        button.addActionListener(new SavedMenuClickHandler());
    }

    private class SavedMenuClickHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            noteApp.setActiveOption(SavedMenu.this);
            noteApp.getMenuFrame();
        }
    }
}
