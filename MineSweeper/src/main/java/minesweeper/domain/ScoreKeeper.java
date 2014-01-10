package minesweeper.domain;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author juri
 */
public class ScoreKeeper {

    private FileContainer fileContainer;
    private TreeMap<Integer, Score> easy;
    private TreeMap<Integer, Score> medium;
    private TreeMap<Integer, Score> hard;
    private ScoreSaver scoreSaver = new ScoreSaver();
    private Scanner scanner;

    public ScoreKeeper(FileContainer fileContainer) {
        this.fileContainer = fileContainer;
        this.easy = new TreeMap<>();
        this.medium = new TreeMap<>();
        this.hard = new TreeMap<>();
    }

    private void setUpScore(TreeMap<Integer, Score> scoreMap, File scoreFile) {

        try {
            scanner = new Scanner(scoreFile, "UTF-8");
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

    private String[] scoresAsStringArray(TreeMap<Integer, Score> scoreMap) {
        String[] scoreArray = new String[10];

        for (int i = 0; i < 10; i++) {
            String nameAndTime = scoreMap.get(i).toString();
            scoreArray[i] = (i + 1) + ": " + nameAndTime;
        }

        return scoreArray;
    }

    public Integer evaluateTime(String time, TreeMap<Integer, Score> scoreMap) {
        Integer[] evalTime = createComparableTime(time);

        for (int i = 0; i < 10; i++) {
            Integer[] compare = createComparableTime(scoreMap.get(i).getTime());

            if (evalTime[0] < compare[0]) {
                return i;
            } else if (evalTime[0] == compare[0] && evalTime[1] < compare[1]) {
                return i;
            }
        }

        return 11;
    }

    private Integer[] createComparableTime(String time) {
        Integer[] comparableTime = new Integer[2];
        String[] splitTime = time.split(":");

        for (int i = 0; i < 2; i++) {
            comparableTime[i] = Integer.parseInt(splitTime[i]);
        }

        return comparableTime;
    }

    public void updateScores() {
        setUpScore(this.easy, this.fileContainer.getEasyScoreFile());
        setUpScore(this.medium, this.fileContainer.getMediumScoreFile());
        setUpScore(this.hard, this.fileContainer.getHardScoreFile());
    }

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
