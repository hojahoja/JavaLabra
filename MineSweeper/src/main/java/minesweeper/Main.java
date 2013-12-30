package minesweeper;


import javax.swing.SwingUtilities;
import minesweeper.gui.GameGui;
public class Main {

    public static void main(String[] args) {
        GameGui mineSweeper = new GameGui();       
        SwingUtilities.invokeLater(mineSweeper);
    }
}
