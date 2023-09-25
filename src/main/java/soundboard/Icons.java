package soundboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

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
        this.backIcon = buildIcon("/icons/back.png");
        this.leaveIcon = buildIcon("/icons/leave.png");
        this.loopIcon = buildIcon("/icons/loop.png");
        this.loopOffIcon = buildIcon("/icons/loop_off.png");
        this.pauseIcon = buildIcon("/icons/pause.png");
        this.playIcon = buildIcon("/icons/play.png");
        this.repeatOneIcon = buildIcon("/icons/repeat_one.png");
        this.shuffleIcon = buildIcon("/icons/shuffle.png");
        this.shuffleOffIcon = buildIcon("/icons/shuffle_off.png");
        this.skipIcon = buildIcon("/icons/skip.png");
        this.stopIcon = buildIcon("/icons/stop.png");
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

    public static int getIconSize(){
        return ICON_SIZE;
    }

    /**
     * Retrieves an Image from a file.
     *
     * @param pathname Path to the image.
     * @return Image if there was an image at the pathname provided, or null if not.
     */
    private Image getImage(String pathname) {
        try {
            URL imageURL = this.getClass().getResource(pathname);
            if (imageURL == null) return null;
            return ImageIO.read(imageURL);
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
