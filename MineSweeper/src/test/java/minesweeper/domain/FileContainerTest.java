package minesweeper.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;

/**
 *
 * @author juri
 */
public class FileContainerTest {

    private FileContainer testContainer;

    public FileContainerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testContainer = new FileContainer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void canReadAndWriteToScoreFiles() {
        File testEasyFile = testContainer.getEasyScoreFile();
        File testMediumFile = testContainer.getMediumScoreFile();
        File testHardFile = testContainer.getHardScoreFile();

        assertTrue(testEasyFile.canRead() && testMediumFile.canRead() && testHardFile.canRead());
        assertTrue(testEasyFile.canWrite() && testMediumFile.canWrite() && testHardFile.canWrite());
    }

    @Test
    public void scoreFilesAreInTheCorrectFileObject() {
        File testEasy = new File("easyScore.txt");
        File testMedium = new File("mediumScore.txt");
        File testHard = new File("hardScore.txt");

        assertEquals(testEasy, testContainer.getEasyScoreFile());
        assertEquals(testMedium, testContainer.getMediumScoreFile());
        assertEquals(testHard, testContainer.getHardScoreFile());

    }

    @Test
    public void flagIconIsFound() {
        ImageIcon comparison = new ImageIcon(getClass().getResource("/Flag.png"));
        ImageIcon testIcon = testContainer.getFlagIcon();
        assertEquals(comparison.getImage(), testIcon.getImage());
    }

    @Test
    public void bombIconIsFound() {
        ImageIcon comparison = new ImageIcon(getClass().getResource("/Bomb.png"));
        ImageIcon testIcon = testContainer.getBombIcon();
        assertEquals(comparison.getImage(), testIcon.getImage());
    }

    @Test
    public void emptyIconIsFound() {
        ImageIcon comparison = new ImageIcon(getClass().getResource("/Empty.png"));
        ImageIcon testIcon = testContainer.getEmptyIcon();
        assertEquals(comparison.getImage(), testIcon.getImage());
    }

    @Test
    public void newFileIsCreatedIfItDoesNotExist() {
        File file = new File("test");
        assertFalse(file.exists());

        testContainer.checkIfFileExists(file);
        assertTrue(file.exists());

        file.delete();
    }

    @Test
    public void NewlycreatedFileHasSomeTextInIt() {
        File file = new File("test");
        assertFalse(file.exists());

        testContainer.checkIfFileExists(file);

        try {
            Scanner scanner = new Scanner(file);
            String string = scanner.nextLine();

            assertFalse(string.isEmpty());
        } catch (Exception e) {
            assertTrue("Something went wrong", false);
        }

        file.delete();
    }
}