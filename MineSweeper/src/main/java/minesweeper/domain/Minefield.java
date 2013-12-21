package minesweeper.domain;

import java.util.Random;
import sun.security.util.Length;

/**
 *
 * @author juri
 */
public class Minefield {

    // Kaksiuloinen taulukko(matriisi), joka toimii miinakentän runkona
    private Cell[][] field;
    
    // Kentän, matriisin kokoon ja miinojen määrään liittyvää tietoa.
    private int height;
    private int width;
    private int mines;

    // Vakio Miinakenttä;
    
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

    //palauttaa yksittäisen solun koordinaateilla x,y
    public Cell getCell(int x, int y) {
        return this.field[y][x];
    }

    //Täyttää kentän soluilla ja kutsuu metodia,
    //joka arpoo taulun miinat
    private void initializeMinefield() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.field[i][j] = new Cell();
            }
        }
        generateMines();
    }
    
    /** Arpoo paikat x ja y miinakentän alueelta.
    *   Asettaa tyhjän solun miinaksi tai ei tee mitään, jos solussa on jo miina.
    *   Looppi varmistaa, että solussa on haluttu määrä miinoja.
    *   Kun miina on asetettu kutsutaan metodia, joka kasvattaa
    *   vierussolujen vierusmiinalaskuria yhdellä
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

    //Metodi käy läpi jokaisen miinoitetun solun vierussolun ja kutsuu
    //metodia, joka varmistaa, että tarkistettavat koordinaatit eivät ole
    //alueen ulkopuolella
    public void IncreaseMineCountForAdjacentCells(int x, int y) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (locationIsInsideMatrixBorders(x+j, y+i)) {
                    getCell(x+j, y+i).increaseAdjacentMineCount();
                }
            }
        }
    }

    
    public boolean locationIsInsideMatrixBorders(int j, int i) {
        return i > -1 && i < height && j > -1 && j < width; 
    }
}
