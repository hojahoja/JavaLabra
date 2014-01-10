package minesweeper.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.awt.Image;
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
    
//    @Test
//    public void canReadAndWriteToScoreFiles() {
//        File testEasyFile = testContainer.getEasyScoreFile();
//        File testMediumFile = testContainer.getMediumScoreFile();
//        File testHardFile = testContainer.getHardScoreFile();
//        
//        assertTrue(testEasyFile.canRead() && testMediumFile.canRead() && testHardFile.canRead());
//        assertTrue(testEasyFile.canWrite() && testMediumFile.canWrite() && testHardFile.canWrite());
//    }
    
//    @Test
//    public void scoreFilesAreInTheCorrectFileObject() {
//        File testEasy = new File("src/main/resources/easyScore");
//        File testMedium = new File("src/main/resources/mediumScore");
//        File testHard = new File("src/main/resources/hardScore");
//        
//        assertEquals(testEasy, testContainer.getEasyScoreFile());
//        assertEquals(testMedium, testContainer.getMediumScoreFile());
//        assertEquals(testHard, testContainer.getHardScoreFile());
//                
//    }
    
//    @Test
//    public void flagIconIsFound() {
//        ImageIcon comparison = new ImageIcon("src/main/resources/Flag.png");
//        ImageIcon testIcon = testContainer.getFlagIcon();        
//        assertEquals(comparison.getImage(), testIcon.getImage());
//    }
//    
//    @Test
//    public void bombIconIsFound() {
//        ImageIcon comparison = new ImageIcon("src/main/resources/Bomb.png");
//        ImageIcon testIcon = testContainer.getBombIcon();        
//        assertEquals(comparison.getImage(), testIcon.getImage());
//    }
//    
//    @Test
//    public void emptyIconIsFound() {
//        ImageIcon comparison = new ImageIcon("src/main/resources/Empty.png");
//        ImageIcon testIcon = testContainer.getEmptyIcon();        
//        assertEquals(comparison.getImage(), testIcon.getImage());
//    }
    
    
}