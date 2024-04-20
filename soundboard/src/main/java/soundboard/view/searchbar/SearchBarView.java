package soundboard.view.searchbar;

import javax.swing.*;

public class SearchBarView {

  private JTextField songField;

  private JButton playButton;

  private JButton queueButton;

  private JPanel panel;

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
}
