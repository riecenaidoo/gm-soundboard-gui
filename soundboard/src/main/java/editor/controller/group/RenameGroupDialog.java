package editor.controller.group;

import editor.model.EditableGroup;
import editor.view.GroupsPanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RenameGroupDialog extends JDialog {
    private final GroupsPanel view;
    private final EditableGroup model;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField groupNameField;

    public RenameGroupDialog(EditableGroup model, GroupsPanel view) {
        this.model = model;
        this.view = view;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        groupNameField.setText(model.getUpdatedName());
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

    private void onOK() {
        String groupName = groupNameField.getText();
        if (!groupName.isBlank()) {
            groupName = groupName.trim();
            model.updateName(groupName);
            if (model.isGroupNameEdited()) view.groupEditedView();

            JComboBox<String> groupSelector = view.getGroupSelector();
            int selectedIndex = groupSelector.getSelectedIndex();
            groupSelector.removeItemAt(selectedIndex);
            groupSelector.insertItemAt(groupName, selectedIndex);
            groupSelector.setSelectedIndex(selectedIndex);
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
