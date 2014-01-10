package minesweeper.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import minesweeper.domain.ScoreKeeper;
import minesweeper.logic.GameLogic;

/**
 * FrameListener is used to handle events that are outside of the actual gaming
 * field
 *
 * @author juri
 */
public class FrameListener implements ActionListener {

    private GameGui gui;
    private GameLogic gameLogic;
    private CustomOptionsPanel customFieldPanel;
    // Event sources.
    private JButton reset;
    private JLabel difficultyLabel;
    private JMenuItem easy;
    private JMenuItem medium;
    private JMenuItem hard;
    private JMenuItem customGame;
    private JMenuItem easyScore;
    private JMenuItem mediumScore;
    private JMenuItem hardScore;

    public FrameListener(GameGui gui) {
        this.gui = gui;
        this.gameLogic = gui.getGameLogic();
        this.customFieldPanel = new CustomOptionsPanel();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == reset) {
            resetGame();
        } else if (source == easy || source == medium || source == hard) {
            handleDifficultySettins(source);
        } else if (source == customGame) {
            handleCustomGameOptions();
        } else if (source == easyScore || source == mediumScore || source == hardScore) {
            handleGameScores(source);
        }
    }

    /**
     * Resets the game. Calls for the gameLogic to create a new minefield and
     * reset the Win/Lose conditions. The components of the content pane are
     * then recreated to update the GUI.
     */
    private void resetGame() {
        String difficultySetting = difficultyLabel.getText();
        gameLogic.resetGame();
        gui.getFrame().getContentPane().removeAll();
        gui.createComponents(gui.getFrame().getContentPane());

        difficultyLabel.setText(difficultySetting);
        gui.getFrame().setVisible(true);
    }

    /**
     * Calls createNewGame method from gui and gives it parameters according to
     * the chosen difficulty setting
     *
     * @param source
     */
    private void handleDifficultySettins(Object source) {
        if (source == easy) {
            gui.createNewGame(8, 8, 10);
            difficultyLabel.setText("       Difficulty: Easy");
            gui.getScoreChecker().setDifficultyToEasy();
        } else if (source == medium) {
            gui.createNewGame(16, 16, 40);
            difficultyLabel.setText("       Difficulty: Medium");
            gui.getScoreChecker().setDifficultyToMedium();
        } else if (source == hard) {
            gui.createNewGame(16, 30, 99);
            difficultyLabel.setText("       Difficulty: Hard");
            gui.getScoreChecker().setDifficultyToHard();
        }

        gameLogic = gui.getGameLogic();
    }

    /**
     * Add the reset button to FrameListener.
     *
     * @param reset
     */
    public void addResetButton(JButton reset) {
        this.reset = reset;
    }

    public void addDifficultyLabel(JLabel difficultyLabel) {
        this.difficultyLabel = difficultyLabel;
    }

    /**
     * Add JMenuitems related to the difficulty settings to FrameListener.
     *
     * @param easy
     * @param medium
     * @param hard
     */
    public void addDifficultyMenuItems(JMenuItem easy, JMenuItem medium, JMenuItem hard) {
        this.easy = easy;
        this.medium = medium;
        this.hard = hard;
    }

    /**
     * Add JMenuitems Related to Custom Game Settings and High Score screens.
     *
     * @param customGame
     * @param hiScore
     */
    public void addCustomGameAndHiScoreMenuItems(JMenuItem customGame, JMenuItem eScore, JMenuItem mScore, JMenuItem hScore) {
        this.customGame = customGame;
        this.easyScore = eScore;
        this.mediumScore = mScore;
        this.hardScore = hScore;
    }

    /**
     * Handles the custom game Event.
     */
    private void handleCustomGameOptions() {
        int option = createCustomGameOptionPane();
        handleOptionPaneActions(option);
    }

    /**
     * Creates the JOptionPane which contains A JPanel for setting up a custom
     * game mode.
     *
     * @return int value representing either the OK, or the Cancel button on the
     * JOptionPane
     */
    private int createCustomGameOptionPane() {
        int option = JOptionPane.showConfirmDialog(
                gui.getFrame(),
                customFieldPanel,
                "Custom Game Settings",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        return option;
    }

    /**
     * If the OK option is clicked the method will take the information from the
     * JPanels and uses it as parameters to create a new game. If the fields are
     * empty or not Integer values, the method will create an error JOptionPane
     * visible to the user and clears the fields.
     *
     * Cancel option will clear the fields and close the window
     *
     * @param option
     */
    private void handleOptionPaneActions(int option) {
        if (option == JOptionPane.OK_OPTION) {
            try {
                int height = customFieldPanel.getHeightCount();
                int width = customFieldPanel.getWidthCount();
                int mines = customFieldPanel.getMinesCount();

                gui.createNewGame(height, width, mines);
                difficultyLabel.setText("       Difficulty: Custom");
                gui.getScoreChecker().setDifficultyToCustom();
                gameLogic = gui.getGameLogic();

            } catch (NumberFormatException e) {

                JOptionPane.showMessageDialog(gui.getFrame(), "Use Numbers");
                customFieldPanel.clearPanelValues();
            }
        } else if (option == JOptionPane.CANCEL_OPTION) {
            customFieldPanel.clearPanelValues();
        }
    }

    /**
     * Checks the event source and gets the correct Array from scoreKeeper.
     *
     * @param source event source
     */
    private void handleGameScores(Object source) {
        if (source == easyScore) {
            createScoreWindow(gui.getScoreKeeper().getEasyArray());
        } else if (source == mediumScore) {
            createScoreWindow(gui.getScoreKeeper().getMediumArray());
        } else if (source == hardScore) {
            createScoreWindow(gui.getScoreKeeper().getHardArray());
        }
    }

    /**
     * Creates an JOptionPane that shows the score taken from the scoreArray
     *
     * @param scoreArray
     */
    private void createScoreWindow(String[] scoreArray) {
        JOptionPane.showMessageDialog(gui.getFrame(), scoreArray, "High Scores", JOptionPane.INFORMATION_MESSAGE);
    }
}
