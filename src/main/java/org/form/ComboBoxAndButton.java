package org.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple form containing a ComboBox
 * and submission Button.
 * <br><br>
 * The Button action will retrieve
 * the option selected in the ComboBox.
 */
public class ComboBoxAndButton {

    static ComboBoxAndButton INSTANCE;

    String selectedChannel;
    JComboBox<String> channelList;
    JButton submit;

    private ComboBoxAndButton() {
        channelList = new JComboBox<>();
        submit = new JButton("SUBMIT");
        submit.addActionListener(new SubmissionListener(this));
    }

    public static ComboBoxAndButton getForm() {
        if (INSTANCE == null) INSTANCE = new ComboBoxAndButton();
        return INSTANCE;
    }

    public static void main(String[] args) {

    }

    void populateChannelList(String[] channelSet) {
        for (String channel : channelSet) {
            channelList.addItem(channel);
        }
    }

    String getSelectedChannel() {
        return (String) channelList.getSelectedItem();
    }

    void setSelectedChannel(int index) {

    }
}

/**
 * Controls the action of the Button.
 * <br><br>
 * To create the listener we pass in the form
 * so that it can access the required methods
 * for submission.
 */
class SubmissionListener implements ActionListener {

    ComboBoxAndButton form;

    SubmissionListener(ComboBoxAndButton form) {
        super();
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.printf("[SUBMISSION] %s", form.getSelectedChannel());
    }
}