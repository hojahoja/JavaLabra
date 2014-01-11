/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

import java.util.TreeMap;
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
public class ScoreKeeperTest {

    private ScoreKeeper scoreKeeper;
    private FileContainer fileContainer;

    public ScoreKeeperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fileContainer = new FileContainer();
        scoreKeeper = new ScoreKeeper(fileContainer);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void scoreMapsAreEmptyAfterInitialization() {
        assertTrue(scoreKeeper.getEasyScores().isEmpty());
        assertTrue(scoreKeeper.getMediumScores().isEmpty());
        assertTrue(scoreKeeper.getHardScores().isEmpty());
    }

    @Test
    public void scoreMapsAreNotEmptyAfterSetup() {
        scoreKeeper.updateScores();

        assertFalse(scoreKeeper.getEasyScores().isEmpty());
        assertFalse(scoreKeeper.getMediumScores().isEmpty());
        assertFalse(scoreKeeper.getHardScores().isEmpty());
    }

    @Test
    public void timeEvaluationWokrsWithSeconds() {
        scoreKeeper.updateScores();

        int position = scoreKeeper.evaluateTime("59:30", scoreKeeper.getEasyScores());
        assertEquals(0, position);
    }

    @Test
    public void timeEvaluationWokrsWithMinutes() {
        scoreKeeper.updateScores();

        int position = scoreKeeper.evaluateTime("30:21", scoreKeeper.getEasyScores());
        assertEquals(0, position);
    }

    @Test
    public void createComparableTimeReturnsAnIntegerArrayWithMinutesAndSeconds() {
        Integer[] intTime = scoreKeeper.createComparableTime("12:30");

        Integer tw = 12;
        assertEquals(tw, intTime[0]);

        Integer th = 30;
        assertEquals(th, intTime[1]);
    }
    
    @Test
    public void returnsScoreArraysOfCorrectSize(){
        scoreKeeper.updateScores();
        
        assertEquals(10, scoreKeeper.getEasyArray().length);
        assertEquals(10, scoreKeeper.getMediumArray().length);
        assertEquals(10, scoreKeeper.getHardArray().length);
    }
    
    @Test
    public void allScoreArraysContainActualStrings() {
        scoreKeeper.updateScores();
        
        assertFalse(scoreKeeper.getEasyArray()[1].isEmpty());
        assertFalse(scoreKeeper.getMediumArray()[9].isEmpty());
        assertFalse(scoreKeeper.getHardArray()[5].isEmpty());
    }
}