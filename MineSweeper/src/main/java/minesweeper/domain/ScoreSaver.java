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
    private FileWriter fileWriter;
    private File scoreFile;
    private TreeMap<Integer, Score> scoreMap;
    
    public void setUpFileForOverwriting(File scoreFile, TreeMap<Integer, Score> scoreMap) {
        this.scoreFile = scoreFile;
        this.scoreMap = scoreMap;
    }
    
    private String[] formatScoresForSaving() {
        String[] saveScoreArray = new String[10];
        
        for (int i = 0; i < 10; i++) {
            Score score = scoreMap.get(i);
            String formattedString = i+","+score.getName()+","+score.getTime();
        }
        
        return saveScoreArray;
    }
    
    
}
