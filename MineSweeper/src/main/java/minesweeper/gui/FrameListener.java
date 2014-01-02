package minesweeper.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.RescaleOp;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import minesweeper.logic.GameLogic;

/**
 *
 * @author juri
 */
public class FrameListener implements ActionListener {

    private GameGui gui;
    private GameLogic gameLogic;
    // Event sources.
    private JButton reset;
    private JMenuItem easy;
    private JMenuItem medium;
    private JMenuItem hard;
    private JMenuItem customGame;
    private JMenuItem hiScore;

    public FrameListener(GameGui gui) {
        this.gui = gui;
        this.gameLogic = gui.getGameLogic();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == reset) {
            resetGame();
        } else if (source == easy || source == medium || source == hard) {
            handleDifficultySettins(source);
        } else if (source == customGame) {
            JOptionPane.showMessageDialog(gui.getFrame(), "Work In Progress");
        } else if (source == hiScore) {
            JOptionPane.showMessageDialog(gui.getFrame(), "Work In Progress");
        }
    }

    /**
     * Resets the game. Calls for the gameLogic to create a new minefield and
     * reset the Win/Lose conditions. The components of the content pane are
     * then recreated to update the GUI.
     */
    private void resetGame() {
        gameLogic.resetGame();
        gui.getFrame().getContentPane().removeAll();
        gui.createComponents(gui.getFrame().getContentPane());

        gui.getFrame().setVisible(true);
    }

    private void handleDifficultySettins(Object source) {
        if (source == easy) {            
            gui.createNewGame(8, 8, 10);
        } else if (source == medium) {
            gui.createNewGame(16, 16, 40);
        } else if (source == hard) {
            gui.createNewGame(16, 30, 99);
        }
        
        gameLogic = gui.getGameLogic();
    }

    public void addResetButton(JButton reset) {
        this.reset = reset;
    }

    public void addDifficultyMenuItems(JMenuItem easy, JMenuItem medium, JMenuItem hard) {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }
    
    public void addCustomGameAndHiScoreMenuItems(JMenuItem customGame, JMenuItem hiScore) {
        this.customGame = customGame;
        this.hiScore = hiScore;
    }
}
