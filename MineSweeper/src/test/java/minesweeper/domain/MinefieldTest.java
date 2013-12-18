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
    
    public MinefieldTest() {
    }
    private Minefield testField;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testField = new Minefield();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initialGettersDefaultTo9And10() {
        assertEquals(testField.getHeight(), 10);
        assertEquals(testField.getWidth(), 10);
        assertEquals(testField.getMines(), 10);
    }
    
    @Test
    public void initalFieldisNotEmpty() {
        int h = testField.getHeight();
        int w = testField.getWidth();
        Cell testCell = new Cell();
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                assertEquals(testCell.getClass(), testField.getCell(j, j).getClass());
            }
        }
        
    }
    
    @Test
    public void getCellReturnsTheCorrectCell() {
        Cell testCell = new Cell();
        testField.getField()[2][4] = testCell;        
        
        assertEquals(testCell, testField.getCell(4, 2));        
    }
}