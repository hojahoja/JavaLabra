package minesweeper.logic;

import java.awt.Point;
import java.util.HashMap;
import java.util.PriorityQueue;
import minesweeper.domain.Cell;
import minesweeper.domain.Minefield;

/**
 *
 * @author juri
 */
public class GameLogic {

    private Minefield minefield;
    private HashMap<Cell, Point> flaggedCells;
    private int fieldHeight;
    private int fieldWidth;

    /**
     * The logic class of the game. The constructor takes the specified
     * parameters and creates a minefield class, which is used to store
     * information about the game
     *
     * @param height
     * @param width
     * @param mines
     */
    public GameLogic(int height, int width, int mines) {
        this.minefield = new Minefield(height, width, mines);
        this.flaggedCells = new HashMap<>();
        this.fieldHeight = height;
        this.fieldWidth = width;
    }

    /**
     * Opens a cell at point (x.y).
     *
     * @param x
     * @param y
     */
    public void openCell(int x, int y) {
        Cell cell = minefield.getCell(x, y);
        cell.setOpen();
        openAdjacentCells(minefield.getCell(x, y));;
    }

    private void openAdjacentCells(Cell cell) {
        PriorityQueue<Cell> checkQueue = new PriorityQueue<>();
        int mineCount = cell.getAdjacentMineCount();
        int flagCount = calculateAdjacentFlags(cell);
    }

    private int calculateAdjacentFlags(Cell cell) {


        return 0;
    }

    /**
     * Toggles a cells flagged status at point (x.y).
     *
     * Adds or removes the cell to the flaggedCells HashMap according to it's
     * status.
     *
     * @param x
     * @param y
     */
    public void toggleCellFlag(int x, int y) {
        Cell cell = minefield.getCell(x, y);
        cell.toggleFlag();
        updateFlaggedCells(cell, x, y);
    }

    private void updateFlaggedCells(Cell cell, int x, int y) {
        if (flaggedCells.containsKey(cell)) {
            flaggedCells.remove(cell);
        } else {
            Point point = new Point(x, y);
            flaggedCells.put(cell, point);
        }
    }

    public Minefield getMinefield() {
        return minefield;
    }

    public HashMap<Cell, Point> getFlaggedCells() {
        return flaggedCells;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }
}
