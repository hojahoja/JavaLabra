package minesweeper.domain;

import java.io.File;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * This class handles the creation and initialization of the image and text
 * files used by the program
 *
 * @author juri
 */
public class FileContainer {

    // Files
    private File easyScore;
    private File mediumScore;
    private File hardScore;
    private ImageIcon flagIcon;
    private ImageIcon bombIcon;
    private ImageIcon emptyIcon;

    /**
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

    /**
     * Sets up the easyScore.txt for usage
     *
     * @return file object
     */
    private File setUpEasyScoreFile() {
        File file = new File("easyScore.txt");
        checkIfFileExists(file);
        return file;
    }

    /**
     * Sets up the mediumScore.txt for usage
     *
     * @return file object
     */
    private File setUpMediumScoreFile() {
        File file = new File("mediumScore.txt");
        checkIfFileExists(file);
        return file;
    }

    /**
     * Sets up the hardScore.txt for usage
     *
     * @return file object
     */
    private File setUpHardScoreFile() {
        File file = new File("hardScore.txt");
        checkIfFileExists(file);
        return file;
    }

    /**
     * Sets up the Flag.png for usage
     *
     * @return file object
     */
    private ImageIcon setUpFlagIcon() {
        return new ImageIcon(getClass().getResource("/Flag.png"));
    }

    /**
     * Sets up the Bomb.png for usage
     *
     * @return file object
     */
    private ImageIcon setUpBombIcon() {
        return new ImageIcon(getClass().getResource("/Bomb.png"));
    }

    /**
     * Sets up the Empty.png for usage
     *
     * @return file object
     */
    private ImageIcon setUpEmptyIcon() {
        return new ImageIcon(getClass().getResource("/Empty.png"));
    }

    /**
     * Check if the file the file already exists if not create the actual file.
     *
     * @param file object
     */
    private void checkIfFileExists(File file) {
        if (file.exists() == false) {
            try {
                file.createNewFile();
                createBasicScoreFile(file);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    /**
     * Adds the basic Placeholder scores to the newly created score files
     *
     * @param file object
     */
    private void createBasicScoreFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
            for (int i = 0; i < 10; i++) {
                fileWriter.write(i + ",Placeholder,59:59\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    // Basic getters
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
