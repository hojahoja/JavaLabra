package minesweeper.domain;

import java.io.File;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.net.URL;
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
        File file = new File("easyScore.txt");
        checkIfFileExists(file);
        return file;
    }

    private File setUpMediumScoreFile() {
        File file = new File("mediumScore.txt");
        checkIfFileExists(file);
        return file;
    }

    private File setUpHardScoreFile() {
        File file = new File("hardScore.txt");
        checkIfFileExists(file);
        return file;
    }

    private ImageIcon setUpFlagIcon() {
        return new ImageIcon(getClass().getResource("/Flag.png"));
    }

    private ImageIcon setUpBombIcon() {
        return new ImageIcon(getClass().getResource("/Bomb.png"));
    }

    private ImageIcon setUpEmptyIcon() {
        return new ImageIcon(getClass().getResource("/Empty.png"));
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
    
    private void createBasicScoreFile(File file) {
        try {
            FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
            for (int i = 0; i < 10; i++) {
                fileWriter.write(i+",Placeholder,59:59\n");
            }
            fileWriter.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
