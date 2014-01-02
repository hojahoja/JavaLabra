package minesweeper;

import javax.swing.SwingUtilities;
import minesweeper.domain.Minefield;
import minesweeper.gui.GameGui;
import minesweeper.logic.GameLogic;

public class Main {

    public static void main(String[] args) {
        GameGui mineSweeper = new GameGui();       
        SwingUtilities.invokeLater(mineSweeper);
    }
}
