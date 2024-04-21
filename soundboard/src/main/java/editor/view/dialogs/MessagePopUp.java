package editor.view.dialogs;

import javax.swing.*;

public class MessagePopUp extends JDialog {

  private JPanel contentPane;

  private JButton buttonOK;

  private JTextField messageField;

  public MessagePopUp() {
    setContentPane(contentPane);
    setModal(true);
    getRootPane().setDefaultButton(buttonOK);
    messageField.setEditable(false);
    buttonOK.addActionListener(e->dispose());
  }

  public MessagePopUp(String message) {
    this();
    this.messageField.setText(message);
  }

}
