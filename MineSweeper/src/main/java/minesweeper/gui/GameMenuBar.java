/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.gui;

import javax.swing.JMenuBar;
import javax.swing.*;

/**
 *
 * @author juri
 */
public class GameMenuBar extends JMenuBar {

    private FrameListener frameListener;
    private JMenu options;
    private JMenu difficulty;
    private JMenuItem custom;
    private JMenuItem hiScore;

    /**
     * Creates the JMenuBar for the GameGUI. The menu is used to set up all the
     * game options and difficulty setting. The High score is also accessed
     * trough the menu
     *
     * @param frameListener
     */
    public GameMenuBar(FrameListener frameListener) {
        this.frameListener = frameListener;
        this.options = new JMenu("Options");
        this.add(options);

        createDifficultySubMenu();
        this.custom = new JMenuItem("Custom Game");
        this.hiScore = new JMenuItem("View High Scores");
        
        this.custom.addActionListener(frameListener);
        this.hiScore.addActionListener(frameListener);
        frameListener.addCustomGameAndHiScoreMenuItems(custom, hiScore);
        

        this.options.add(difficulty);
        this.options.add(custom);
        this.options.addSeparator();
        this.options.add(hiScore);
    }

    /**
     * Creates a submenu which contains preset minefield settings.
     * 
     */
    private void createDifficultySubMenu() {
        difficulty = new JMenu("Set Difficuly");
        JMenuItem easy = new JMenuItem("Easy (8x8, 10 Mines)");
        JMenuItem medium = new JMenuItem("Medium (16x16, 40 Mines)");
        JMenuItem hard = new JMenuItem("Hard (16x30, 99 Mines)");
        
        easy.addActionListener(frameListener);
        medium.addActionListener(frameListener);
        hard.addActionListener(frameListener);
        frameListener.addDifficultyMenuItems(easy, medium, hard);

        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
    }
}
