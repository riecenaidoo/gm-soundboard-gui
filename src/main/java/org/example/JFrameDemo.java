package org.example;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo {
    public static void main(String[] args) {
//1. Create the frame.
        JFrame frame = new JFrame("FrameDemo");

//2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//3. Create components and put them in the frame.
//...create emptyLabel...
        JLabel emptyLabel = new JLabel("Hello World.");
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

//4. Size the frame.
        frame.pack();
//... center the frame
        frame.setLocationRelativeTo(null);

//5. Show it.
        frame.setVisible(true);
    }
}