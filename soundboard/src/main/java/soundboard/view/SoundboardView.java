package soundboard.view;

import soundboard.view.catalogue.CatalogueView;
import soundboard.view.discordbot.DiscordBotView;

import javax.swing.*;

public class SoundboardView extends JPanel {

    private final CatalogueView catalogueView;
    private final DiscordBotView discordBotView;

    public SoundboardView() {
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
