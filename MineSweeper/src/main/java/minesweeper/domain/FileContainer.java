package minesweeper.domain;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author juri
 */
public class FileContainer {

    private File easyScore;
    private File mediumScore;
    private File hardScore;
    private ImageIcon flagIcon;
    private ImageIcon bombIcon;
    private ImageIcon emptyIcon;

    /**
     * A class that locates the files in the resources folders and makes them
     * available trough getter methods.
     * 
     * all the method calls create a Class with a specified path to the file.
     */
    public FileContainer() {
        this.easyScore = setUpEasyScoreFile();
        this.mediumScore = setUpMediumScoreFile();
        this.hardScore = setUpHardScoreFile();
        this.flagIcon = setUpFlagIcon();
        this.bombIcon = setUpBombIcon();
        this.emptyIcon = setUpEmptyIcon();
    }
    
    private File setUpEasyScoreFile() {
        return new File("src/main/resources/easyScore");
    }
    
    private File setUpMediumScoreFile() {
        return new File("src/main/resources/mediumScore");
    }
    
    private File setUpHardScoreFile() {
        return new File("src/main/resources/hardScore");
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

    public File getEasyScoreFile() {
        return easyScore;
    }
    
    public File getMediumScoreFile() {
        return mediumScore;
    }
    
    public File getHardScoreFile() {
        return hardScore;
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
