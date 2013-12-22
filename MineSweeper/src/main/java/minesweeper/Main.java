package minesweeper;


import javax.swing.SwingUtilities;
import minesweeper.gui.GameGui;
import javax.swing.ImageIcon;
public class Main {

    public static void main(String[] args) {
        GameGui MineSweeper = new GameGui();
        SwingUtilities.invokeLater(MineSweeper);
    }
}
