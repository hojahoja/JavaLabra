package minesweeper.domain;

/**
 *
 * @author juri
 */
public class Score {
    private String time;
    private String name;

    public Score(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return name+" "+time;
    }
    
}
