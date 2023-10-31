package controller;

import model.DiscordBot;
import view.MusicPlayerView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controls the audio controls panel of the Soundboard,
 * updating the display in sync with the status of the discord bot.
 */
class MusicPlayerController {

    private final DiscordBot model;
    private final JSlider volumeSlider;


    public MusicPlayerController(DiscordBot model, MusicPlayerView view) {
        this.model = model;
        volumeSlider = view.getVolumeControl();
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

    /**
     * Synchronise the MusicPlayer view with the DiscordBot model's state.
     */
    public void sync() {
        volumeSlider.setValue(model.getVolume());
    }
}