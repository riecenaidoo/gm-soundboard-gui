package soundboard.controller.searchbar;

import soundboard.controller.RequestHandler;
import soundboard.view.searchbar.SearchBarView;

import java.util.Arrays;
import java.util.List;

public class SearchBarController {

  private final SearchBarView searchBarView;

  public SearchBarController(SearchBarView searchBarView) {
    this.searchBarView = searchBarView;
  }

  public void connect(RequestHandler requestHandler) {
    searchBarView.getPlayButton().addActionListener((l) -> {
      if(searchBarView.hasText()) requestHandler.play(List.of(searchBarView.popText()));
    });

    searchBarView.getQueueButton().addActionListener((l) -> {
      if (searchBarView.hasText()) requestHandler.queue(List.of(searchBarView.popText()));
    });
  }

  public void disconnect() {
    Arrays.stream(searchBarView.getPlayButton().getActionListeners()).forEach(searchBarView.getPlayButton()::removeActionListener);
    Arrays.stream(searchBarView.getQueueButton().getActionListeners()).forEach(searchBarView.getQueueButton()::removeActionListener);
  }
}
