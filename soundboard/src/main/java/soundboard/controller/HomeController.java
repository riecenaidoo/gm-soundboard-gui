package soundboard.controller;

import soundboard.App;
import soundboard.model.ClientSocket;
import soundboard.view.HomeView;

import javax.swing.*;
import java.io.IOException;

public class HomeController {

    public HomeController(App model, HomeView view) {
        JButton connect = view.getConnect();
        JLabel status = view.getStatus();

        connect.addActionListener(e -> {
            try {
                status.setText("Connected!");
                model.connectClient(new ClientSocket(model, model.getHostname(), model.getPort()));
                model.viewSoundboard();
            } catch (IOException i) {
                status.setText("Connection Failed.");
            }

            new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("Label clearing thread interrupted.");
                }
                status.setText("");
            }).start();
        });
    }
}
