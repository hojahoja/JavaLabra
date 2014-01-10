package minesweeper.gui;

import javax.swing.JMenuBar;
import javax.swing.*;

/**
 * The menu is used to set up all the game options and difficulty setting. The
 * High score is also accessed trough the menu
 *
 * @author juri
 */
public class GameMenuBar extends JMenuBar {

    private FrameListener frameListener;
    private JMenu options;
    private JMenu difficulty;
    private JMenuItem custom;
    private JMenu hiScore;
    

    /**
     * Creates the JMenuBar for the GameGUI.
     *
     * @param frameListener
     */
    public GameMenuBar(FrameListener frameListener) {
        this.frameListener = frameListener;
        this.options = new JMenu("Options");
        this.add(options);

        createDifficultySubMenu();
        this.custom = new JMenuItem("Custom Game");        

        this.custom.addActionListener(frameListener);
        createHighscoreSubMenu();


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
        difficulty = new JMenu("Set Difficulty");
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

    private void createHighscoreSubMenu() {
        this.hiScore = new JMenu("View High Scores");
        JMenuItem easyScore = new JMenuItem("Easy");
        JMenuItem mediumScore = new JMenuItem("Medium");
        JMenuItem hardScore = new JMenuItem("Hard");
        
        easyScore.addActionListener(frameListener);
        mediumScore.addActionListener(frameListener);
        hardScore.addActionListener(frameListener);
        frameListener.addCustomGameAndHiScoreMenuItems(custom, easyScore, mediumScore, hardScore);
        
        hiScore.add(easyScore);
        hiScore.add(mediumScore);
        hiScore.add(hardScore);
    }
}
