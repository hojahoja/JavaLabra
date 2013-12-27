package minesweeper.logic;

import minesweeper.domain.Minefield;

/**
 *
 * @author juri
 */
public class GameLogic {
    
    
    private Minefield minefield;
    private int fieldHeight;
    private int fieldWidth;

    /**
     * The logic class of the game.
     * The constructor takes the specified parameters and creates a minefield
     * class, which is used to store information about the game
     * 
     * @param height
     * @param width
     * @param mines 
     */
    public GameLogic(int height, int width, int mines) {
        this.minefield = new Minefield(height, width, mines);
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
        minefield.getCell(x, y).setOpen();
    }
    
    /**
     * Toggles a cells flagged status at point (x.y)
     * 
     * @param x
     * @param y 
     */
    public void toggleCellFlag(int x, int y) {
        minefield.getCell(x, y).toggleFlag();
    }

    public Minefield getMinefield() {
        return minefield;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }   
}
