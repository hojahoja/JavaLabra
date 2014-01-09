package minesweeper;

import com.sun.corba.se.impl.oa.toa.TOA;
import java.util.TreeMap;
import javax.swing.SwingUtilities;
import minesweeper.domain.FileContainer;
import minesweeper.domain.Minefield;
import minesweeper.domain.*;
import minesweeper.gui.CountUpTimer;
import minesweeper.gui.GameGui;
import minesweeper.logic.GameLogic;

public class Main {

    public static void main(String[] args) {
        GameGui mineSweeper = new GameGui();       
        SwingUtilities.invokeLater(mineSweeper);
    }
}
