package minesweeper.logic;

import minesweeper.domain.Minefield;

/**
 *
 * @author juri
 */
public class GameLogic {
    private Minefield minefield;

    public GameLogic(Minefield minefield) {
       this.minefield = minefield;
    }  

    public Minefield getMinefield() {
        return minefield;
    }   
}
