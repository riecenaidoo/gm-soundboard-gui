package soundboard.view;

import soundboard.view.catalogue.CatalogueView;
import soundboard.view.discordbot.DiscordBotView;

import javax.swing.*;

public class SoundboardView extends JPanel {

    private final CatalogueView catalogueView;
    private final HomeView homeView;
    private final DiscordBotView discordBotView;

    public SoundboardView() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        catalogueView = new CatalogueView();
        discordBotView = new DiscordBotView();
        homeView = new HomeView();
        this.add(catalogueView);
        this.add(homeView);
    }

    public DiscordBotView getDiscordBotView() {
        return discordBotView;
    }

    public CatalogueView getCatalogueView() {
        return catalogueView;
    }

    public HomeView getHomeView() {
        return homeView;
    }

    public void viewHome() {
        this.remove(discordBotView);
        this.add(homeView);
    }

    public void viewDiscordBot() {
        this.remove(homeView);
        this.add(discordBotView);
    }
}
