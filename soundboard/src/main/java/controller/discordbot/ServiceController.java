package controller.discordbot;

import controller.API;

public interface ServiceController {

    void sync();

    void connect(API api);
}
