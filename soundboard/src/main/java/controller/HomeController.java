package controller;

import view.HomePanel;

import javax.swing.*;

public class HomeController {

    public HomeController(Soundboard model, HomePanel view){
        JButton connect = view.getConnect();
        JLabel status = view.getStatus();

        connect.addActionListener(e -> {
            try {
                model.setClient(Client.getClient(model));
                status.setText("Connected!");

                model.openSoundboard();
            } catch (Client.ClientCreationException c) {
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
