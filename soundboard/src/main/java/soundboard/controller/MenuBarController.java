package soundboard.controller;

import soundboard.App;
import soundboard.view.MenuBar;

public class MenuBarController {

    public MenuBarController(App model, MenuBar view) {
        view.getDisconnect().addActionListener(l -> model.closeSoundboard());
    }
}
