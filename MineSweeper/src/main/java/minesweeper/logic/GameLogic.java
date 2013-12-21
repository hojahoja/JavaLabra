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

    public GameLogic(int height, int width, int mines) {
        this.minefield = new Minefield(height, width, mines);
        this.fieldHeight = height;
        this.fieldWidth = width;
    }  
    
    public void openCell(int x, int y) {
        minefield.getCell(x, y).setOpen();
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
