package minesweeper.domain;

import java.io.FileWriter;
import java.io.File;
import java.util.TreeMap;
import minesweeper.domain.Score;

/**
 * This class is used to write scores into the .txt files
 *
 * @author juri
 */
public class ScoreSaver {

    /**
     * the score file that is going to be changed.
     *
     */
    private File scoreFile;
    /**
     * The map the score is from
     */
    private TreeMap<Integer, Score> scoreMap;

    /**
     * Sets up the file for writing.
     *
     * @param scoreFile chosen score file
     * @param scoreMap chosen score map
     */
    public void setUpFileForOverwriting(File scoreFile, TreeMap<Integer, Score> scoreMap) {
        this.scoreFile = scoreFile;
        this.scoreMap = scoreMap;
    }

    /**
     * Rewrites the score file with the new scores taken from the scoreMap
     */
    public void writeNewScoreToFile() {
        String[] saveScoreArray = formatScoresForSaving();
        try {
            FileWriter fileWriter = new FileWriter(scoreFile.getAbsolutePath());
            for (int i = 0; i < 10; i++) {
                fileWriter.write(saveScoreArray[i] + "\n");
            }
            fileWriter.close();
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    /**
     * Takes the score from the scoreMap and turns them into strings that are
     * used to save the score into a file
     *
     * @return String array that contains the scores
     */
    private String[] formatScoresForSaving() {
        String[] saveScoreArray = new String[10];

        for (int i = 0; i < 10; i++) {
            Score score = scoreMap.get(i);
            String formattedString = i + "," + score.getName() + "," + score.getTime();
            saveScoreArray[i] = formattedString;
        }

        return saveScoreArray;
    }

    public File getScoreFile() {
        return scoreFile;
    }

    public TreeMap<Integer, Score> getScoreMap() {
        return scoreMap;
    }   
    
}
