package minesweeper.logic;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import minesweeper.domain.Cell;
import minesweeper.domain.Minefield;

/**
 * The main game engine. Does all the main calculations on algorithms of the game.
 * The creation of the basic game information is still handled in the Minefield Class
 * @author juri
 */
public class GameLogic {

    private Minefield minefield;
    private HashSet<Cell> flaggedCells;
    private int fieldHeight;
    private int fieldWidth;
    private boolean gameWon;
    private boolean gameLost;

    /**
     * The constructor takes the specified
     * parameters and creates the minefield class, which is used to store
     * information about the game.
     *
     * @param height
     * @param width
     * @param mines
     */
    public GameLogic(int height, int width, int mines) {
        this.minefield = new Minefield(height, width, mines);
        this.flaggedCells = new HashSet<>();
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
        }
    }

    /**
     * Opens all the adjacent cells that are safe to open. Opens adjacent cells
     * of adjacent cells that are have no adjacent mines. The method will also
     * open the adjacent mines of numbered cell if the cell has as many adjacent
     * flags as it has adjacent mines.
     *
     * @param cell
     * @param x
     * @param y
     */
    private void openAdjacentCells(Cell cell, int x, int y) {
        int mineCount = cell.getAdjacentMineCount();
        int flagCount = calculateAdjacentFlags(x, y); // Palautusarvo on testattu miksi cobertura herjaa???
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
        updateFlaggedCells(cell);
        updateWinConditon(cell);
    }

    /**
     * Updates a HashMap that has all the flagged cell. Method is used to update
     * the HashMap that is used to determine if the win condition is met
     *
     * @param cell
     * @param x
     * @param y
     */
    private void updateFlaggedCells(Cell cell) {
        if (cell.isOpen()) {
            return;
        }
        
        if (flaggedCells.contains(cell)) {
            flaggedCells.remove(cell);
        } else {
            flaggedCells.add(cell);
        }
    }

    /**
     * Utilizes a BFS-algorithm to open every adjacent cell to the recently
     * opened one. If the cell doesn't have adjacent mines, each adjacent cell
     * is opened. If a cell has adjacent mines, the cell will be opened but it's
     * adjacent cells will not be queued up for inspection. The win condition
     * will be checked after opening a new cell.
     *
     * @param cell
     * @param x
     * @param y
     */
    private void breadhFirstSearchCellOpener(Cell cell, int x, int y) {
        ArrayDeque<Point> checkQueue = new ArrayDeque<>();
        Set<Cell> visited = new HashSet<>();
        checkQueue.add(new Point(x, y));
        visited.add(cell);

        while (checkQueue.isEmpty() == false && gameLost == false) {
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
                            updateWinConditon(nextAdjacentCell);

                            if (nextAdjacentCell.getAdjacentMineCount() < 1) {
                                checkQueue.add(nextAdjacentPoint);
                            }
                        }
                    }
                }
            }
        }
        updateWinConditon(cell);
        visited.clear();
    }

    /**
     * Used by other methods to make sure that checked coordinates exists inside
     * the minefield class.
     *
     * @param x
     * @param y
     * @return false if the index is out of bounds, true if not.
     */
    private boolean indexIsValid(int x, int y) {
        return minefield.locationIsInsideMatrixBorders(x, y);
    }

    /**
     * Used to check the win or lose conditions. If a mined cell is opened
     * that's also unflagged the method changes the gameLost condition to true
     * and calls a method to reveal the whole minefield.
     *
     * If the minefield has as many flagged cells as it has mines a method will
     * check the cells for mines. If all flagged cells have mines the gameWon
     * condition is changed to true
     *
     * @param cell
     */
    private void updateWinConditon(Cell cell) {

        if (cell.isOpen() && cell.isMine() && cell.isFlagged() == false) {
            setGameLost();
        }

        if (flaggedCells.size() == minefield.getMines()) {
            CheckIfTheWinConditionIsMet();
        }

        if (gameLost == true) {
            openAllCells();
        }
    }

    /**
     * This method is called when the mine and and flag counts are the same. The
     * method makes sure that every other cell in the game is open and that the
     * flag count is equal to the mine count. The game can be won when these
     * conditions are met.
     */
    private void CheckIfTheWinConditionIsMet() {
        int cellsOpened = 0;
        int correctCellsFlagged = 0;

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {

                Cell cell = minefield.getCell(j, i);
                if (cell.isMine() && cell.isFlagged()) {
                    correctCellsFlagged++;
                } else if (cell.isOpen()) {
                    cellsOpened++;
                }
            }
        }

        boolean WinConditionIsMet = fieldHeight * fieldWidth - correctCellsFlagged - cellsOpened == 0;
        if (WinConditionIsMet) {
            setGameWon();
        }
    }

    /**
     * Opens every single cell in the game. If a cell is flagged it will be
     * unflagged and opened anyway. The method is used to show the whole
     * minefield and mine placements after the game has been lost.
     */
    private void openAllCells() {
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if (minefield.getCell(j, i).isFlagged()) {
                    minefield.getCell(j, i).toggleFlag();
                }
                minefield.getCell(j, i).setOpen();

            }
        }
    }

    /**
     * Resets the game by setting both win and lose conditions to false and by
     * creating a new minefield.
     */
    public void resetGame() {
        gameLost = false;
        gameWon = false;
        int mines = minefield.getMines();
        minefield = new Minefield(fieldHeight, fieldWidth, mines);
        flaggedCells.clear();
    }

    /**
     * The method will only set the game as won. The game can't be lost if the
     * gameWon condition is already true.
     */
    public void setGameLost() {
        if (gameWon == false) {
            this.gameLost = true;
        }
    }

    /**
     * The method will only set the game as lost. The game can't be won if it
     * has already been lost.
     */
    public void setGameWon() {
        if (gameLost == false) {
            this.gameWon = true;
        }
    }

    // Nothing special about these getters
    public Minefield getMinefield() {
        return minefield;
    }

    public HashSet<Cell> getFlaggedCells() {
        return flaggedCells;
    }

    public int getFlaggedCellCount() {
        return flaggedCells.size();
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public boolean isGameLost() {
        return gameLost;
    }

    public boolean isGameWon() {
        return gameWon;
    }
}
