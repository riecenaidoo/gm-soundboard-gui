package soundboard.controller.discordbot;

import soundboard.controller.RequestHandler;
import soundboard.model.Icons;

public interface DiscordBotController {

    void sync();

    void connect(RequestHandler requestHandler);

    void loadIcons(Icons icon);
}
