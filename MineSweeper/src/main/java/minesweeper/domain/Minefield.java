package minesweeper.domain;

import java.util.Random;

/**
 * Contains the Information about the minefield and contains all the cells of
 * the game. Creates the cells and marks basic info(mines and the count of
 * adjacent mines), but doesn't do operate the cells otherwise
 *
 * @author juri
 */
public class Minefield {

    /**
     * A 2D array that's contains all the cells created by the Minefield class.
     */
    private Cell[][] field;
    
    /**
     * Minefield height.
     */
    private int height;
    /**
     * Minefield width.
     */
    private int width;
    /**
     * The amount of mines in the minefield.
     */
    private int mines;

    /**
     * Creates the minefield. creates a new instance of Cell[][] and calls
     * initializeMinefield();
     *
     * @param height
     * @param width
     * @param mines
     */
    public Minefield(int height, int width, int mines) {
        this.height = height;
        this.width = width;
        this.mines = mines;
        this.field = new Cell[height][width];
        this.initializeMinefield();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMines() {
        return mines;
    }

    public Cell[][] getField() {
        return field;
    }

    /**
     * Get the specified cell.
     *
     * @param x coordinate of the 2D array
     * @param y coordinate of the 2D array
     * @return the cell in the point (x.y) of the 2D array
     */
    public Cell getCell(int x, int y) {
        return this.field[y][x];
    }

    /**
     * Creates the cell inside the minefield class and calls a method to place
     * the mines.
     */
    private void initializeMinefield() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.field[i][j] = new Cell();
            }
        }
        generateMines();
    }

    /**
     * Makes specified amount of Cells into mines randomly. Uses the Random
     * class to generate a random point (x.y) and makes a cell at that point
     * into a mine. The method will run until a specified amount of mines are
     * generated.
     */
    private void generateMines() {

        Random rng = new Random();
        int hasMines = 0;

        while (hasMines < this.mines) {
            int x = rng.nextInt(this.width);
            int y = rng.nextInt(this.height);
            Cell current = this.getCell(x, y);
            boolean currentCellHasNoMine = !current.isMine();

            if (currentCellHasNoMine) {
                current.setMine();
                IncreaseMineCountForAdjacentCells(x, y);
                hasMines++;
            }
        }
    }

    /**
     * Increases the mine count for adjacent Cells. Takes a mine at point (x.y)
     * and Increases the mine count of each adjacent Cell.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void IncreaseMineCountForAdjacentCells(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (locationIsInsideMatrixBorders(x + j, y + i)) {
                    getCell(x + j, y + i).increaseAdjacentMineCount();
                }
            }
        }
    }

    /**
     * Makes sure that the given location does not go outside of the 2D array.
     *
     * @param j x coordinate
     * @param i y coordinate
     * @return Is the given location outside of borders.
     */
    public boolean locationIsInsideMatrixBorders(int j, int i) {
        return i > -1 && i < height && j > -1 && j < width;
    }

    /**
     * Only in tests for generating non random mines.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void setTestMine(int x, int y) {
        getCell(x, y).setMine();
        IncreaseMineCountForAdjacentCells(x, y);
        mines++;
    }
}
