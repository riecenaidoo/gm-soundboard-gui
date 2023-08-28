package soundboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Loads Icons and makes them available for use.
 * TODO Asynchronously load the icons so as not to block the main thread.
 */
public class Icons {

    private static int ICON_SIZE = 40;

    private final Icon backIcon;
    private final Icon leaveIcon;
    private final Icon loopIcon;
    private final Icon loopOffIcon;
    private final Icon pauseIcon;
    private final Icon playIcon;
    private final Icon repeatOneIcon;
    private final Icon shuffleIcon;
    private final Icon shuffleOffIcon;
    private final Icon skipIcon;
    private final Icon stopIcon;

    /**
     * Loads Icons from the resources folder and makes them available for the GUI to use.
     * Singly threaded and blocking. Avoid loading large icons.
     */
    public Icons() {
        this.backIcon = buildIcon("src/main/resources/icons/back.png");
        this.leaveIcon = buildIcon("src/main/resources/icons/leave.png");
        this.loopIcon = buildIcon("src/main/resources/icons/loop.png");
        this.loopOffIcon = buildIcon("src/main/resources/icons/loop_off.png");
        this.pauseIcon = buildIcon("src/main/resources/icons/pause.png");
        this.playIcon = buildIcon("src/main/resources/icons/play.png");
        this.repeatOneIcon = buildIcon("src/main/resources/icons/repeat_one.png");
        this.shuffleIcon = buildIcon("src/main/resources/icons/shuffle.png");
        this.shuffleOffIcon = buildIcon("src/main/resources/icons/shuffle_off.png");
        this.skipIcon = buildIcon("src/main/resources/icons/skip.png");
        this.stopIcon = buildIcon("src/main/resources/icons/stop.png");
    }

    /**
     * Set the size of all icons. Must be set before an Icons object is constructed.
     *
     * @param size positive integer greater than zero that corresponds to the
     *             size, in pixels, that icons should be scaled to.
     */
    public static void setIconSize(int size) {
        if (size > 0) ICON_SIZE = size;
    }

    /**
     * Retrieves an Image from a file.
     * TODO Allow images to be retrieved from resources inside the jar file.
     *
     * @param pathname Path to the image.
     * @return Image if there was an image at the pathname provided, or null if not.
     */
    private Image getImage(String pathname) {
        try {
            return ImageIO.read(new File(pathname));
        } catch (NullPointerException | IllegalArgumentException | IOException e) {
            return null;
        }
    }

    /**
     * Creates an Icon from the image at a path.
     *
     * @param pathname Path to the image.
     * @return Icon if there was an image at the pathname provided, or null if not.
     */
    private Icon buildIcon(String pathname) {
        Image image = getImage(pathname);
        if (image == null) return null;
        image = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_REPLICATE);
        return new ImageIcon(image);
    }

    public Icon getBackIcon() {
        return backIcon;
    }

    public Icon getLeaveIcon() {
        return leaveIcon;
    }

    public Icon getLoopIcon() {
        return loopIcon;
    }

    public Icon getLoopOffIcon() {
        return loopOffIcon;
    }

    public Icon getPauseIcon() {
        return pauseIcon;
    }

    public Icon getPlayIcon() {
        return playIcon;
    }

    public Icon getRepeatOneIcon() {
        return repeatOneIcon;
    }

    public Icon getShuffleIcon() {
        return shuffleIcon;
    }

    public Icon getShuffleOffIcon() {
        return shuffleOffIcon;
    }

    public Icon getSkipIcon() {
        return skipIcon;
    }

    public Icon getStopIcon() {
        return stopIcon;
    }
}
