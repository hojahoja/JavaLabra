package minesweeper.domain;

/**
 * Stores Score Information
 *
 * @author juri
 */
public class Score {
    
    /**
     * time as a string
     */
    private String time;
    /**
     * the enter name
     */
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
        return name + " " + time;
    }
}
