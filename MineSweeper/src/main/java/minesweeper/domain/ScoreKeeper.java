package minesweeper.domain;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * This object holds the current scores in TreeMaps and is used to check if the
 * completion times are good enough to be recorded.
 *
 * @author juri
 */
public class ScoreKeeper {

    /**
     * FileContainr is used to access the score .txt files.
     */
    private FileContainer fileContainer;
    /**
     * TreeMap containing the easy mode scores.
     */
    private TreeMap<Integer, Score> easy;
    /**
     * TreeMap containing the medium mode scores.
     */
    private TreeMap<Integer, Score> medium;
    /**
     * TreeMap containing the hard mode scores.
     */
    private TreeMap<Integer, Score> hard;

    public ScoreKeeper(FileContainer fileContainer) {
        this.fileContainer = fileContainer;
        this.easy = new TreeMap<>();
        this.medium = new TreeMap<>();
        this.hard = new TreeMap<>();
    }

    /**
     * Reads the scoreFile with a scanner and places it in a TreeMap
     *
     * @param scoreMap The TreeMap containing the score.
     * @param scoreFile The accessed score file.
     */
    private void setUpScore(TreeMap<Integer, Score> scoreMap, File scoreFile) {

        try {
            Scanner scanner = new Scanner(scoreFile, "UTF-8");
            while (scanner.hasNextLine()) {
                String scoreLine = scanner.nextLine();
                String[] scoreParts = scoreLine.split(",");

                int placement = Integer.parseInt(scoreParts[0]);
                String name = scoreParts[1];
                String time = scoreParts[2];

                scoreMap.put(placement, new Score(time, name));
            }

            scanner.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            e.getMessage();
        }

    }

    /**
     * Creates a String array which contains the scores in the form they are
     * printed to the user.
     *
     * @param scoreMap the scoreMap the is going to be converted.
     * @return the score array ready for viewing
     */
    private String[] scoresAsStringArray(TreeMap<Integer, Score> scoreMap) {
        String[] scoreArray = new String[10];

        for (int i = 0; i < 10; i++) {
            String nameAndTime = scoreMap.get(i).toString();
            scoreArray[i] = (i + 1) + ": " + nameAndTime;
        }

        return scoreArray;
    }

    /**
     * Takes the finish time of the game as a string and checks if it has a
     * place in the high scores.
     *
     * @param time String representation of time.
     * @param scoreMap current scoreMap
     * @return returns the position of the new score or 11 if the time was not
     * enough to make it into the high score list.
     */
    public Integer evaluateTime(String time, TreeMap<Integer, Score> scoreMap) {
        Integer[] evalTime = createComparableTime(time);

        for (int i = 0; i < 10; i++) {
            Integer[] compare = createComparableTime(scoreMap.get(i).getTime());
            
            boolean newScoreTookLessInMInutes = evalTime[0] < compare[0];
            boolean newScoreTookLessInSeconds = evalTime[0] == compare[0] && evalTime[1] < compare[1];
            
            if (newScoreTookLessInMInutes) {
                return i;
            } else if (newScoreTookLessInSeconds) {
                return i;
            }
        }

        return 11;
    }

    /**
     * Takes time in string format and converts it into an Integer array
     *
     * @param time time in string format ex(20:13)
     * @return Time split into two parts inside an Integer array
     */
    public Integer[] createComparableTime(String time) {
        Integer[] comparableTime = new Integer[2];
        String[] splitTime = time.split(":");

        for (int i = 0; i < 2; i++) {
            comparableTime[i] = Integer.parseInt(splitTime[i]);
        }

        return comparableTime;
    }

    /**
     * Updates all the Maps containing scores.
     * 
     */
    public void updateScores() {
        setUpScore(this.easy, this.fileContainer.getEasyScoreFile());
        setUpScore(this.medium, this.fileContainer.getMediumScoreFile());
        setUpScore(this.hard, this.fileContainer.getHardScoreFile());
    }

    // Getters start here
    public String[] getEasyArray() {
        return scoresAsStringArray(easy);
    }

    public String[] getMediumArray() {
        return scoresAsStringArray(medium);
    }

    public String[] getHardArray() {
        return scoresAsStringArray(hard);
    }

    public TreeMap getEasyScores() {
        return easy;
    }

    public TreeMap getMediumScores() {
        return medium;
    }

    public TreeMap getHardScores() {
        return hard;
    }
}
