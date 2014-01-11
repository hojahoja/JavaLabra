package minesweeper.gui;

import java.io.FileWriter;
import java.io.File;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import minesweeper.domain.FileContainer;
import minesweeper.domain.ScoreKeeper;
import minesweeper.domain.Score;
import minesweeper.domain.ScoreSaver;

/**
 * This class manages both ScoreSaver and ScoreKeeper. Also used to evaluate the
 * new score and place it into a proper slot in the score map. Whenever game
 * mode is changed it should be changed in this class too
 *
 * @author juri
 */
public class ScoreChecker {

    /**
     * Indexes representing difficulties 0 = easy; 1 = medium; 2 = hard; 3 =
     * custom;
     *
     */
    private boolean[] difficulty;
    /**
     * Used to store the scores while the game is running.
     */
    private ScoreKeeper scoreKeeper;
    private GameGui gui;
    private FileContainer fileContainer;
    /**
     * The score map of the current difficulty mode.
     */
    private TreeMap<Integer, Score> currentScore;
    private File currentScoreFile;
    private ScoreSaver scoreSaver;

    /**
     * Sets the difficulty to easy when created.
     */
    public ScoreChecker(ScoreKeeper scoreKeeper, GameGui gui, FileContainer fileContainer) {
        this.difficulty = new boolean[4];
        this.difficulty[1] = true;
        this.scoreKeeper = scoreKeeper;
        this.fileContainer = fileContainer;
        this.gui = gui;
        this.currentScore = scoreKeeper.getEasyScores();
        this.currentScoreFile = fileContainer.getEasyScoreFile();
        this.scoreSaver = new ScoreSaver();

    }

    /**
     * Sets up the scoreChecker for the easy difficulty mode.
     *
     */
    public void setDifficultyToEasy() {
        resetDifficulties();
        difficulty[0] = true;
        this.currentScore = scoreKeeper.getEasyScores();
        this.currentScoreFile = fileContainer.getEasyScoreFile();
    }

    /**
     * Sets up the scoreChecker for the medium difficulty mode.
     *
     */
    public void setDifficultyToMedium() {
        resetDifficulties();
        difficulty[1] = true;;
        this.currentScore = scoreKeeper.getMediumScores();
        this.currentScoreFile = fileContainer.getMediumScoreFile();
    }

    /**
     * Sets up the scoreChecker for the hard difficulty mode.
     *
     */
    public void setDifficultyToHard() {
        resetDifficulties();
        difficulty[2] = true;
        this.currentScore = scoreKeeper.getHardScores();
        this.currentScoreFile = fileContainer.getHardScoreFile();
    }

    /**
     * Sets up the scoreChecker for custom game mode.
     *
     */
    public void setDifficultyToCustom() {
        resetDifficulties();
        difficulty[3] = true;
        this.currentScore = null;
    }

    /**
     * This method is used by the difficulty setters to make sure that only one boolean value
     * in the difficulty array is set to true.
     */
    public void resetDifficulties() {
        for (int i = 0; i < difficulty.length; i++) {
            difficulty[i] = false;
        }
    }
    
    /**
     * Evaluates the score and calls other methods to save the score.
     * Does nothing if the current game mode is set to custom.
     * @param time 
     */
    public void evaluateScore(String time) {
        boolean gameModeIsSetToCustom = difficulty[3];
        if (gameModeIsSetToCustom) {
            return;
        }

        int position = scoreKeeper.evaluateTime(time, currentScore);

        if (position < 11) {
            askForname(position, time);
        }

        scoreSaver.setUpFileForOverwriting(currentScoreFile, currentScore);
        scoreSaver.writeNewScoreToFile();
        scoreKeeper.updateScores();
    }

    private void askForname(int position, String time) {
        String name = (String) JOptionPane.showInputDialog(gui.getFrame(), "Your position " + (position + 1) + "\n Type Your name:");
        boolean somethingWasEntered = name == null || name.isEmpty();

        if (somethingWasEntered) {
            JOptionPane.showMessageDialog(gui.getFrame(), "You can't leave the score field empty", "Wrong Imput", JOptionPane.WARNING_MESSAGE);
            askForname(position, time);
        } else {
            Score newScore = new Score(time, name);
            reorderPositions(newScore, position);
        }


    }

    private void reorderPositions(Score newScore, int position) {
        Score previousScore = currentScore.get(position);
        if (position < 10) {
            currentScore.put(position, newScore);
            position++;
            reorderPositions(previousScore, position);
        }
    }
}
