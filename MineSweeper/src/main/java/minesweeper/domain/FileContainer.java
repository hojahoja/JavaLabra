package minesweeper.domain;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author juri
 */
public class FileContainer {

    private File scoreFile;
    private ImageIcon flagIcon;
    private ImageIcon bombIcon;
    private ImageIcon emptyIcon;

    public FileContainer() {
        this.scoreFile = setUpTextFile();
        this.flagIcon = setUpFlagIcon();
        this.bombIcon = setUpBombIcon();
        this.emptyIcon = setUpEmptyIcon();
    }

    private File setUpTextFile() {
        return new File("src/main/resources/scoreFile");
    }

    private ImageIcon setUpFlagIcon() {
        return new ImageIcon("src/main/resources/Flag.png");
    }

    private ImageIcon setUpBombIcon() {
        return new ImageIcon("src/main/resources/Bomb.png");
    }

    private ImageIcon setUpEmptyIcon() {
        return new ImageIcon("src/main/resources/Empty.png");
    }

    public File getScoreFile() {
        return scoreFile;
    }

    public ImageIcon getFlagIcon() {
        return flagIcon;
    }

    public ImageIcon getBombIcon() {
        return bombIcon;
    }

    public ImageIcon getEmptyIcon() {
        return emptyIcon;
    }
}
