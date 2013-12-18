package minesweeper.domain;

/**
 *
 * @author juri
 */
public class Minefield {
 
    // Kaksiuloinen taulukko, joka toimii miinakentän runkona
    private Cell[][] field;
    
    private int height;
    private int width;
    
    
    private int mines;

    public Minefield() {
        this.height = 9;
        this.width = 9;
        this.mines = 10;
        this.field = new Cell[height][width];
    }
    
    
    
}
