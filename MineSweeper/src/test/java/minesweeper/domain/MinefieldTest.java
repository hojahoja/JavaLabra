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
public class MinefieldTest {

    private Minefield testField;

    public MinefieldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        testField = new Minefield(10, 10, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void initialGettersGiveCorrectValues() {
        assertEquals(testField.getHeight(), 10);
        assertEquals(testField.getWidth(), 10);
        assertEquals(testField.getMines(), 10);
    }

    @Test
    public void getFieldReturnsSomething() {
        assertNotNull(testField.getField());
    }

    @Test
    public void initalFieldisNotEmpty() {
        int h = testField.getHeight();
        int w = testField.getWidth();
        Cell testCell = new Cell();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                assertEquals(testCell.getClass(), testField.getCell(j, i).getClass());
            }
        }
    }

    @Test
    public void getCellReturnsTheCorrectCell() {
        Cell testCell = new Cell();
        testField.getField()[2][4] = testCell;

        assertEquals(testCell, testField.getCell(4, 2));
    }

    @Test
    public void fieldHasTheCorrectAmmountOfMines() {
        int mineCount = 0;
        int h = testField.getHeight();
        int w = testField.getWidth();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Cell testCell = testField.getCell(j, i);
                if (testCell.isMine()) {
                    mineCount++;
                }
            }
        }

        assertEquals(testField.getMines(), mineCount);
    }

    @Test
    public void locationCheckerRecognizesWrongCoordinates() {
        assertFalse(testField.locationIsInsideMatrixBorders(-1, 2));
        assertFalse(testField.locationIsInsideMatrixBorders(2, testField.getHeight()));
        assertFalse(testField.locationIsInsideMatrixBorders(testField.getWidth(), 0));
        assertFalse(testField.locationIsInsideMatrixBorders(5, -1));
    }

    @Test
    public void locationCheckerRecognizesCorrectCoordinates() {
        assertTrue(testField.locationIsInsideMatrixBorders(0, 9));
        assertTrue(testField.locationIsInsideMatrixBorders(9, 0));
    }

    // vaikea testata pitin haluamalla tavalla satunnaisuuden vuoksi.
    @Test
    public void calculatesAdjacentMinesCorrectly() {
        Minefield anotherField = new Minefield(10, 10, 0);
        Cell testCell1 = anotherField.getCell(1, 1);
        Cell testCell2 = anotherField.getCell(2, 0);
        
        anotherField.IncreaseMineCountForAdjacentCells(0, 0);
        anotherField.IncreaseMineCountForAdjacentCells(0, 1);
        anotherField.IncreaseMineCountForAdjacentCells(1, 0);
        
        assertEquals(3, testCell1.getAdjacentMineCount());
        assertEquals(1, testCell2.getAdjacentMineCount());
    }
}