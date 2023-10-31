package controller.discordbot;

import controller.API;
import model.Icons;

public interface DiscordBotController {

    void sync();

    void connect(API api);

    void loadIcons(Icons icon);
}
