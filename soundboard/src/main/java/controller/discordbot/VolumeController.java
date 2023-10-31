package controller.discordbot;

import controller.API;
import model.DiscordBot;
import view.discordbot.VolumeSlider;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class VolumeController implements ServiceController {

    private final DiscordBot model;
    private final JSlider volumeSlider;

    public VolumeController(DiscordBot model, VolumeSlider volumeSlider) {
        this.model = model;
        this.volumeSlider = volumeSlider;
    }

    public void sync() {
        volumeSlider.setValue(model.getVolume());
    }

    public void connect(API api) {
        volumeSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int desiredVol = volumeSlider.getValue();
                if (model.getVolume() == desiredVol) return;
                api.set_volume(desiredVol);
            }
        });
    }
}