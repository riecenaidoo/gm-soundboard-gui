package soundboard.view.searchbar;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;

public class SearchBarView {

  private JTextField songField;

  private JButton playButton;

  private JButton queueButton;

  private JPanel panel;

  {
    // GUI initializer generated by IntelliJ IDEA GUI Designer
    // >>> IMPORTANT!! <<<
    // DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  public JButton getPlayButton() {
    return playButton;
  }

  public JButton getQueueButton() {
    return queueButton;
  }

  public JPanel getPanel() {
    return panel;
  }

  public boolean hasText() {
    return !songField.getText().isBlank();
  }

  /**
   * Return and clear text from the searchbar.
   *
   * @return text from the songField.
   */
  public String popText() {
    String text = songField.getText();
    songField.setText("");
    return text;
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    panel = new JPanel();
    panel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
    songField = new JTextField();
    panel.add(songField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    playButton = new JButton();
    playButton.setText("Play");
    playButton.setToolTipText("Play the URL immediately");
    panel.add(playButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    queueButton = new JButton();
    queueButton.setText("Queue");
    queueButton.setToolTipText("Add Song to the end of the Queue");
    panel.add(queueButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return panel;
  }

}