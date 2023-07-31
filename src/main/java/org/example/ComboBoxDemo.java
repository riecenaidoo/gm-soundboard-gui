package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxDemo extends JPanel
        implements ActionListener {

    public ComboBoxDemo() {
        super(new BorderLayout());

        String[] petStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
        JComboBox<String> petList = new JComboBox<>(petStrings);
        petList.addActionListener(this);

        //Lay out the demo.
        add(petList, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ComboBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ComboBoxDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(ComboBoxDemo::createAndShowGUI);
    }

    /**
     * Listens to the combo box.
     */
    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unchecked")  // It will always be a ComboBox
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        String petName = (String) cb.getSelectedItem();
        System.out.printf("[SELECTED] : %s\n", petName);
    }
}