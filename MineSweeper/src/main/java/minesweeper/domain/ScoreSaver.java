/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.domain;

import java.io.FileWriter;
import java.io.File;
import java.util.TreeMap;
import minesweeper.domain.Score;

/**
 *
 * @author juri
 */
public class ScoreSaver {

    private File scoreFile;
    private TreeMap<Integer, Score> scoreMap;

    public void setUpFileForOverwriting(File scoreFile, TreeMap<Integer, Score> scoreMap) {
        this.scoreFile = scoreFile;
        this.scoreMap = scoreMap;
    }

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

    private String[] formatScoresForSaving() {
        String[] saveScoreArray = new String[10];

        for (int i = 0; i < 10; i++) {
            Score score = scoreMap.get(i);
            String formattedString = i + "," + score.getName() + "," + score.getTime();
            saveScoreArray[i] = formattedString;
        }

        return saveScoreArray;
    }
}
