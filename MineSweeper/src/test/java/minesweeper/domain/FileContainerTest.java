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
    
    @Test
    public void canReadAndWriteToScoreFile() {
        File testFile = testContainer.getScoreFile();
        assertTrue(testFile.canRead());
        assertTrue(testFile.canWrite());
    }
    
    @Test
    public void flagIconIsFound() {
        ImageIcon comparison = new ImageIcon("src/main/resources/Flag.png");
        ImageIcon testIcon = testContainer.getFlagIcon();        
        assertEquals(comparison.getImage(), testIcon.getImage());
    }
    
    @Test
    public void bombIconIsFound() {
        ImageIcon comparison = new ImageIcon("src/main/resources/Bomb.png");
        ImageIcon testIcon = testContainer.getBombIcon();        
        assertEquals(comparison.getImage(), testIcon.getImage());
    }
    
    @Test
    public void emptyIconIsFound() {
        ImageIcon comparison = new ImageIcon("src/main/resources/Empty.png");
        ImageIcon testIcon = testContainer.getEmptyIcon();        
        assertEquals(comparison.getImage(), testIcon.getImage());
    }
    
    
}