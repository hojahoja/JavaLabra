/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.gui;

import javax.swing.JFrame;
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
public class GameGuiTest {
    
    public GameGuiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of run method, of class GameGui.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        GameGui instance = new GameGui();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrame method, of class GameGui.
     */
    @Test
    public void testGetFrame() {
        System.out.println("getFrame");
        GameGui instance = new GameGui();
        JFrame expResult = null;
        JFrame result = instance.getFrame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}