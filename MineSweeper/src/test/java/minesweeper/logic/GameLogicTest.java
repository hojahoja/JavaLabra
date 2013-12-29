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
    public void flaggedCellsIsEmptyAtFirst() {
        assertTrue(testGameLogic.getFlaggedCells().isEmpty());
    }
    
    @Test
    public void openCellMethodOpensTheCorrectCell() {
        Minefield testField = testGameLogic.getMinefield();
        Cell testCell = testField.getCell(3, 4);
        
        testGameLogic.openCell(3, 4);
        assertTrue(testCell.isOpen());
    }
    
    @Test
    public void toggleCellFlagChangesFlaggedStatus() {
        Cell testCell = testGameLogic.getMinefield().getCell(2, 3);
        
        assertFalse(testCell.isFlagged());
        
        testGameLogic.toggleCellFlag(2, 3);
        assertTrue(testCell.isFlagged());
        
        testGameLogic.toggleCellFlag(2, 3);
        assertFalse(testCell.isFlagged());
    }
    
    @Test
    public void toggleCellFlagAddsCellsToFlaggedCellsHashMap() {
        testGameLogic.toggleCellFlag(2, 5);
        testGameLogic.toggleCellFlag(6, 2);
        
        Cell testCell1 = testGameLogic.getMinefield().getCell(2, 5);
        Cell testCell2 = testGameLogic.getMinefield().getCell(6, 2);
        
        assertTrue(testGameLogic.getFlaggedCells().containsKey(testCell1));
        assertTrue(testGameLogic.getFlaggedCells().containsKey(testCell2));
    }
    
    @Test
    public void toggleCellFlagsRemovesCellsFromFlaggedCellsHashMap() {
        testGameLogic.toggleCellFlag(5, 4);
        testGameLogic.toggleCellFlag(5, 4);
        testGameLogic.toggleCellFlag(3, 2);
        testGameLogic.toggleCellFlag(3, 2);
        
        Cell testCell1 = testGameLogic.getMinefield().getCell(5, 4);
        Cell testCell2 = testGameLogic.getMinefield().getCell(3, 2);
        
        assertFalse(testGameLogic.getFlaggedCells().containsKey(testCell1));
        assertFalse(testGameLogic.getFlaggedCells().containsKey(testCell2));
    }
    
    @Test
    public void correctNumberOfAjdacnetFlagsFound() {
        Minefield testField = testGameLogic.getMinefield();
        
        testField.getCell(1, 3).toggleFlag();
        testField.getCell(3, 4).toggleFlag();
        testField.getCell(3, 5).toggleFlag();
        
        assertEquals(3, testGameLogic.calculateAdjacentFlags(2, 4));
    }
}