package minesweeper.logic;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
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
    private boolean gameWon;
    private boolean gameLost;

    /**
     * The logic class of the game. The constructor takes the specified
     * parameters and creates the minefield class, which is used to store
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
        this.gameWon = false;
        this.gameLost = false;
    }

    /**
     * Opens a cell at point (x.y) and Calls a method to open adjacent cell.
     *
     *
     * @param x
     * @param y
     */
    public void openCell(int x, int y) {
        Cell cell = minefield.getCell(x, y);
        cell.setOpen();

        updateWinConditon(cell);
        if (gameLost == false) {
            openAdjacentCells(minefield.getCell(x, y), x, y);
        } else {
            openAllCells();
        }
    }

    private void openAdjacentCells(Cell cell, int x, int y) {
        int mineCount = cell.getAdjacentMineCount();
        int flagCount = calculateAdjacentFlags(x, y);
        if ((mineCount - flagCount) < 1) {
            breadhFirstSearchCellOpener(cell, x, y);
        }
    }

    /**
     * Calculates cells adjacent flags.
     *
     * @param x
     * @param y
     * @return Number of the adjacent flags for the cell in the given
     * coordinates
     */
    public int calculateAdjacentFlags(int x, int y) {
        int flagCount = 0;

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                if (indexIsValid(x + j, y + i)) {
                    // The index has to be valid before this boolean can be set.
                    boolean CellIsFlagged = minefield.getCell(x + j, y + i).isFlagged();

                    if (CellIsFlagged) {
                        flagCount++;
                    }

                }
            }
        }
        return flagCount;
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

    private void breadhFirstSearchCellOpener(Cell cell, int x, int y) {
        ArrayDeque<Point> checkQueue = new ArrayDeque<>();
        Set<Cell> visited = new HashSet<>();
        checkQueue.add(new Point(x, y));
        visited.add(cell);

        while (checkQueue.isEmpty() == false) {
            Point currentCell = checkQueue.poll();
            x = currentCell.x;
            y = currentCell.y;

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {

                    if (indexIsValid(x + j, y + i)) {
                        Cell nextAdjacentCell = minefield.getCell(x + j, y + i);
                        Point nextAdjacentPoint = new Point(x + j, y + i);
                        boolean cellIsNotAlreadyVisited = !visited.contains(nextAdjacentCell);

                        if (cellIsNotAlreadyVisited) {
                            visited.add(nextAdjacentCell);
                            nextAdjacentCell.setOpen();

                            if (nextAdjacentCell.getAdjacentMineCount() < 1) {
                                checkQueue.add(nextAdjacentPoint);
                            }
                        }
                    }
                }
            }
        }
        visited.clear();
    }

    private boolean indexIsValid(int x, int y) {
        return minefield.locationIsInsideMatrixBorders(x, y);
    }

    private void updateWinConditon(Cell cell) {
        if (cell.isMine()) {
            gameLost = true;
        }
        
        if (flaggedCells.size() == minefield.getMines()) {
            
        }
    }

    private void openAllCells() {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                minefield.getCell(j, i).setOpen();
            }
        }
    }
}
