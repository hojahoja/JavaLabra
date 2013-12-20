package minesweeper;

import java.util.HashSet;
import java.util.Random;
import javax.swing.SwingUtilities;
import minesweeper.domain.Cell;
import minesweeper.domain.Minefield;
import minesweeper.gui.GameGui;

/**
 * Hello world!
 *
 */
public class Main {

    public static void main(String[] args) {
        GameGui MineSweeper = new GameGui();
        SwingUtilities.invokeLater(MineSweeper);
    }
}
