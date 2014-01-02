/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.gui;

import java.awt.event.MouseListener;
import javax.swing.JButton;
import minesweeper.logic.GameLogic;

/**
 *
 * @author juri
 */
public class FrameListener implements MouseListener {

    private GameGui gui;
    private JButton reset;
    private GameLogic gameLogic;

    public FrameListener(GameGui gui, JButton reset) {
        this.gui = gui;
        this.reset = reset;
        this.gameLogic = gui.getGameLogic();
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent event) {
        if (event.getSource() == reset) {
            gameLogic.resetGame();
            gui.getFrame().getContentPane().removeAll();
            gui.createComponents(gui.getFrame().getContentPane());

            gui.getFrame().setVisible(true);
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }
}
