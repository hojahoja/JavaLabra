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

/**
 *
 * @author juri
 */
public class ScoreTest {
    
    private Score score;
    
    public ScoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        score = new Score("10:20", "name");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void initalGettersReturnCorrectValues() {
        assertEquals("10:20", score.getTime());
        assertEquals("name", score.getName());
    }
    
    @Test 
    public void toStringReturnsNameAndTime() {
        assertEquals("name 10:20", score.toString());
    }
}