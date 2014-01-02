package minesweeper.domain;

/**
 *
 * @author juri
 */
public class Cell {
    
    /**
     * Is the cell mined.
     */
    private boolean mine;
    
    /**
     * Is the cell flagged.
     */
    private boolean flagged;
    
    /**
     * Is the cell open.
     */
    private boolean open;
    
    private int adjacentMines;
    
    /**
     * Minefield class uses Cell to store information. 
     */
    public Cell() {
        this.mine = false;
        this.flagged = false;
        this.open = false;
        this.adjacentMines = 0;
    }
    
    /**
     * 
     * @return Cells flagged status.
     */
    public boolean isFlagged() {
        return flagged;
    }
    
    /**
     * 
     * @return Does the cell have a mine.
     */
    public boolean isMine() {
        return mine;
    }

    /**
     * 
     * @return Is the cell open.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Sets the Cells open status to true.
     * 
     * Does nothing if the Cell is flagged or already opened.
     */
    public void setOpen() {
        boolean hasNoFlagAndIsClosed = !this.flagged && !this.open;
        
        if (hasNoFlagAndIsClosed) {
            this.open = true;
        }
    }

    /**
     * Set the Cells mine status to true.
     */
    public void setMine() {
        this.mine = true;
    }
    
    /**
     * Toggles the cells flag status.
     * If the cell is flagged it will remove the flag.
     * If the cell is not flagged it will flag it.
     */
    public void toggleFlag() {
        boolean CellIsClosed = !this.open;
        if (CellIsClosed) {
            if (this.flagged) {
                this.flagged = false;
            } else {
                this.flagged = true;
            }
        }
    }

    public int getAdjacentMineCount() {
        return this.adjacentMines;
    }
    
    /**
     * Increases the known amount of adjacent mines.
     * 
     * Used by minefield class.
     */
    public void increaseAdjacentMineCount() {
        this.adjacentMines++;
    }
}
