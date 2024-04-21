package editor.controller.group;

import editor.view.dialogs.MessagePopUp;
import editor.model.EditableCatalogue;
import editor.view.GroupsPanel;
import soundboard.model.catalogue.Group;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddGroupDialog extends JDialog {

    private final EditableCatalogue model;
    private final GroupsPanel view;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField groupNameField;

    public AddGroupDialog(EditableCatalogue model, GroupsPanel view) {
        this.model = model;
        this.view = view;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Add new Group to the Catalogue.
     */
    private void onOK() {
        String groupName = groupNameField.getText();
        if (!groupName.isBlank()) {
            groupName = groupName.trim();
            if(model.hasGroup(groupName)){
                MessagePopUp messagePopUp = new MessagePopUp("Group name already exists!");
                messagePopUp.pack();
                messagePopUp.setVisible(true);
                return;
            }
            model.addGroup(new Group(groupName));
            view.getGroupSelector().addItem(groupName);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
