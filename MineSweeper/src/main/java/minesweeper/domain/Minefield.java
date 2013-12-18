package minesweeper.domain;

import java.util.Random;

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

    // Vakio Miinakenttä;
    public Minefield() {
        this.height = 10;
        this.width = 10;
        this.mines = 10;
        this.field = new Cell[height][width];
        this.fillField();
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
    
    //palauttaa yksittäisen solun koordinaateilla x,y
    public Cell getCell(int x, int y) {
        return this.field[y][x];
    }
    
    //Täyttää kentän soluilla ja kutsuu metodia,
    //joka arpoo taulun miinat
    private void fillField() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.field[i][j] = new Cell();
            }
        }
        
        // arpoo uudet miinojen paikat ja varmistaa ettei aseta samaa miinaa
        //uudestaan
        //KESKEN: tästä tulee oma metodi.
        Random rng = new Random();
        int x = rng.nextInt(this.width);
        int y = rng.nextInt(this.height);
    }
    
}
