package minesweeper.domain;

import java.util.LinkedList;

/**
 *
 * @author juri
 */
public class Cell {
    
    //Määrittää onko solussa miinaa vai ei
    private boolean mine;
    
    //Kertoo onko solu merkattu lipulla
    private boolean flagged;
    
    //Onko solua avattu
    private boolean open;
    
    //solu tietää monta miinaa sen veiressä on
    private int adjacentMines;

    public Cell() {
        this.mine = false;
        this.flagged = false;
        this.open = false;
        this.adjacentMines = 0;
    }
    
    public boolean isFlagged() {
        return flagged;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isOpen() {
        return open;
    }

    // Avaa solun. Avattua solua ei voi sulkea.
    public void setOpen() {
        boolean hasNoFlagAndIsClosed = !this.flagged && !this.open;
        
        if (hasNoFlagAndIsClosed) {
            this.open = true;
        }
    }

    //aseta miinan soluun. Miinaa ei tarvitse poistaa
    public void setMine() {
        this.mine = true;
    }
    
    // Jos ruutu on avoin metodi muuttaa liputuksen tilaa
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
    
    public void increaseAdjacentMineCount() {
        this.adjacentMines++;
    }
}
