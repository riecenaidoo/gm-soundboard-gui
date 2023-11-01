package view;

import view.catalogue.CatalogueView;
import view.discordbot.DiscordBotView;

import javax.swing.*;

public class SoundboardPanel extends JPanel {

    private final CatalogueView catalogueView;
    private final DiscordBotView discordBotView;

    public SoundboardPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        catalogueView = new CatalogueView();
        discordBotView = new DiscordBotView();
        this.add(catalogueView);
        this.add(discordBotView);
    }

    public DiscordBotView getDiscordBotView() {
        return discordBotView;
    }

    public CatalogueView getCatalogueView() {
        return catalogueView;
    }
}
