package minesweeper.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Timer class that counts up from seconds to minutes and shows the passed time
 * in the game
 *
 * @author juri
 */
public class CountUpTimer implements ActionListener {

    private JLabel timeLabel;
    private Timer timer;
    private int seconds;
    private int minutes;

    /**
     * Creates the JLabel and setups the timer to go off every second.
     */
    public CountUpTimer() {
        this.timeLabel = new JLabel("Time: 00:00");
        this.timer = new Timer(1000, this);
        timer.setInitialDelay(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timeLabel.setText("Time: " +formatTimeRepresentation(minutes) + ":" + formatTimeRepresentation(seconds));
        seconds++;
        countUp();
    }

    /**
     * Turns the int representing passed time into a string. Adds a zero in
     * front of the number if it's under 10
     *
     * @param value seconds or minutes
     * @return representation of seconds or minutes in a string
     */
    private String formatTimeRepresentation(int value) {
        if (value < 10) {
            return "0" + Integer.toString(value);
        } else {
            return Integer.toString(value);
        }
    }

    /**
     * Counts up time whenever it's called.
     * 
     */
    private void countUp() {
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }

        if (minutes == 60) {
            timer.stop();
        }
    }

    /**
     * Starts the time if it isn't already running.
     */
    public void start() {
        if (timer.isRunning() == false) {
            timer.start();
        }
    }

    /**
     * Stops the timer
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Resets the JLabel and the ints representing time back to zero.
     * 
     */
    public void resetTimer() {
        timeLabel.setText("Time: 00:00");
        minutes = 0;
        seconds = 0;
    }
    
    /**
     * Takes the String from the timeLabel and modifies it to show only time
     * @return time in String format.
     */
        public String getTime() {
        String time = timeLabel.getText();
        time = time.substring(time.length()-5);
        return time;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }
    

}