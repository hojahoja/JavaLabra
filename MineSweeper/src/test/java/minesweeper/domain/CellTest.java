package minesweeper.domain;

import java.util.LinkedList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import minesweeper.domain.Cell;
import org.junit.Before;

/**
 * Unit test for simple App.
 */
public class CellTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CellTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CellTest.class);
    }   
    
    public void testInitialBooleansAreFalse() {
        Cell testCell = new Cell();
        assertFalse(testCell.isFlagged());
        assertFalse(testCell.isMine());
        assertFalse(testCell.isOpen());
    }
    
    public void testInitialAdjacentMineCountIsZero() {
        Cell testCell = new Cell();
        assertEquals(0, testCell.getAdjacentMineCount());
    }
    
    public void testMineCountIncreacesProperly() {
        Cell testCell = new Cell();
        
        testCell.increaseAdjacentMineCount();
        assertEquals(1, testCell.getAdjacentMineCount());
        
        testCell.increaseAdjacentMineCount();
        testCell.increaseAdjacentMineCount();
        assertEquals(3, testCell.getAdjacentMineCount());
    }
    
    public void testSetOpenWorksenWhenNotFlaggedAndClosed() {
        Cell testCell = new Cell();
        testCell.setOpen();
        assertTrue(testCell.isOpen());
    }
    
    public void testSetOpenDoesNothingWhenCellIsFlaggedOrClosed(){
        Cell testCell1 = new Cell();
        Cell testCell2 = new Cell();
        
        testCell1.toggleFlag();
        testCell1.setOpen();
        assertFalse(testCell1.isOpen());
        
        testCell2.setOpen();
        testCell2.setOpen();
        assertTrue(testCell2.isOpen());
    }
    
    public void testSetMineTurnsMineBooleanToTrueAndOnlyTrue() {
        Cell testCell = new Cell();
        
        testCell.setMine();
        assertTrue(testCell.isMine());
        testCell.setMine();
        assertTrue(testCell.isMine());
    }
    
    public void testToggleFlagTogglesFlagProperly() {
        Cell testCell = new Cell();
        
        testCell.toggleFlag();
        assertTrue(testCell.isFlagged());
        
        testCell.toggleFlag();
        assertFalse(testCell.isFlagged());
    }
    
    public void testCannotFlagAnOpenCell() {
        Cell testCell = new Cell();
        testCell.setOpen();
        testCell.toggleFlag();
        
        assertFalse(testCell.isFlagged());
    }   
    
}
