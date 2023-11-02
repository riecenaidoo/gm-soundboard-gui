package controller;

import view.MenuBar;

public class MenuBarController {

    public MenuBarController(Soundboard model, MenuBar view) {
        view.getDisconnect().addActionListener(l -> model.closeSoundboard());
    }
}
