/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
    }
    
    
}