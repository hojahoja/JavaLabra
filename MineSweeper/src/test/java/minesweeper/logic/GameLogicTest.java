/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.logic;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import minesweeper.domain.Cell;
import minesweeper.domain.Minefield;
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
public class GameLogicTest {
    private GameLogic testGameLogic;
    
    public GameLogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testGameLogic = new GameLogic(10, 10, 10);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void setupParametersAndGettersWorkProperly() {
        GameLogic gameLogic = new GameLogic(2, 5, 3);
        
        assertEquals(2, gameLogic.getFieldHeight());
        assertEquals(5, gameLogic.getFieldWidth());
        
        int mineCount = gameLogic.getMinefield().getMines();
        
        assertEquals(3, mineCount);
    }
    
    @Test
    public void minefieldVariableIsNotNull() {
        assertNotNull(testGameLogic.getMinefield());
    }
    
    @Test
    public void openCellMethodOpensTheCorrectCell() {
        Minefield testField = testGameLogic.getMinefield();
        Cell testCell = testField.getCell(3, 4);
        
        testGameLogic.openCell(3, 4);
        assertTrue(testCell.isOpen());
    }
}