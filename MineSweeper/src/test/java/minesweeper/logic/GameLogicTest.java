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
    private Minefield testField;

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
        testField = testGameLogic.getMinefield();
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
    public void winLoseConditionsAreFalseAfterInitializations() {
        assertFalse(testGameLogic.isGameLost());
        assertFalse(testGameLogic.isGameWon());
    }

    @Test
    public void canNotWinTheGameIfItIsAlreadyLost() {
        testGameLogic.setGameLost();
        assertTrue(testGameLogic.isGameLost());

        testGameLogic.setGameWon();
        assertFalse(testGameLogic.isGameWon());
    }

    @Test
    public void canNotLoseTheGameIfItIsAlreadyWon() {
        testGameLogic.setGameWon();
        assertTrue(testGameLogic.isGameWon());

        testGameLogic.setGameLost();
        assertFalse(testGameLogic.isGameLost());
    }

    @Test
    public void openCellMethodOpensTheCorrectCell() {
        Cell testCell = testField.getCell(3, 4);

        testGameLogic.openCell(3, 4);
        assertTrue(testCell.isOpen());
    }

    @Test
    public void adjcentCellsAreNotOpenedIfMineCountMinusFlagCountIsHigherThanZero() {
        GameLogic gameLogic = new GameLogic(3, 3, 0);
        Minefield minefield = gameLogic.getMinefield();

        minefield.getCell(2, 2).setMine();
        minefield.IncreaseMineCountForAdjacentCells(2, 2);
        gameLogic.openCell(2, 1);

        int openedCells = 0;
        for (int i = 0; i < minefield.getHeight(); i++) {
            for (int j = 0; j < minefield.getWidth(); j++) {
                if (minefield.getCell(j, i).isOpen()) {
                    openedCells++;
                }
            }
        }

        assertEquals(1, openedCells);
    }

    @Test
    public void gameIslostIfMinedCellIsOpened() {
        GameLogic gameLogic = new GameLogic(3, 3, 0);
        gameLogic.getMinefield().getCell(2, 2).setMine();

        gameLogic.openCell(2, 2);

        assertTrue(gameLogic.isGameLost());
    }

    @Test    
    public void gameCanBeWonByOpeningTheLastCell() {
        GameLogic gameLogic  = new GameLogic(2, 2, 0);
        Minefield minefield = gameLogic.getMinefield();
        minefield.getCell(1, 0).isMine();
        
        gameLogic.openCell(0, 0);
        gameLogic.openCell(0, 1);        
        
        minefield.getCell(1, 0).toggleFlag();
        
        gameLogic.openCell(1, 1);      
        assertTrue(gameLogic.isGameWon());
    }
    
    @Test
    public void gameCanBeWonByFlaggingTheLastCell() {
        GameLogic gameLogic  = new GameLogic(10, 10, 0);
        Minefield minefield = gameLogic.getMinefield();
        
        minefield.getCell(6, 4).isMine();
        
        gameLogic.openCell(3, 5);         
        gameLogic.toggleCellFlag(6, 4);        
             
        assertTrue(gameLogic.isGameWon());
    }

    @Test
    public void IfTheGameIsLostAllTheCellsAreOpenedIncludingFlaggedCells() {
        GameLogic gameLogic = new GameLogic(3, 3, 0);
        Minefield minefield = gameLogic.getMinefield();
        minefield.getCell(2, 2).setMine();

        minefield.getCell(2, 1).setMine();
        minefield.getCell(2, 1).isFlagged();

        gameLogic.openCell(2, 2);

        int openedCells = 0;
        for (int i = 0; i < minefield.getHeight(); i++) {
            for (int j = 0; j < minefield.getWidth(); j++) {
                openedCells++;
            }
        }

        assertEquals(9, openedCells);
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

        assertTrue(testGameLogic.getFlaggedCells().contains(testCell1));
        assertTrue(testGameLogic.getFlaggedCells().contains(testCell2));
    }

    @Test
    public void toggleCellFlagsRemovesCellsFromFlaggedCellsHashMap() {
        testGameLogic.toggleCellFlag(5, 4);
        testGameLogic.toggleCellFlag(5, 4);
        testGameLogic.toggleCellFlag(3, 2);
        testGameLogic.toggleCellFlag(3, 2);

        Cell testCell1 = testGameLogic.getMinefield().getCell(5, 4);
        Cell testCell2 = testGameLogic.getMinefield().getCell(3, 2);

        assertFalse(testGameLogic.getFlaggedCells().contains(testCell1));
        assertFalse(testGameLogic.getFlaggedCells().contains(testCell2));
    }

    @Test
    public void correctNumberOfAjdacnetFlagsFound() {
        testField.getCell(1, 3).toggleFlag();
        testField.getCell(3, 4).toggleFlag();
        testField.getCell(3, 5).toggleFlag();

        assertEquals(3, testGameLogic.calculateAdjacentFlags(2, 4));
    }
}