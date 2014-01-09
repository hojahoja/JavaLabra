package minesweeper.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author juri
 */
public class CountUpTimer implements ActionListener {

    private JLabel timeLabel;
    private Timer timer;
    private int seconds;
    private int minutes;

    public CountUpTimer() {
        this.timeLabel = new JLabel("00:00");
        this.timer = new Timer(1000, this);
        timer.setInitialDelay(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        timeLabel.setText(formatTimeRepresentation(minutes) + ":" + formatTimeRepresentation(seconds));
        seconds++;
        countUp();
    }

    public void start() {        
        if (timer.isRunning() == false) {
            timer.start();
        }
    }

    public void stop() {
        timer.stop();
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void resetTimer() {
        timeLabel.setText("00:00");
        minutes = 0;
        seconds = 0;
    }

    private String formatTimeRepresentation(int value) {
        if (value < 10) {
            return "0" + Integer.toString(value);
        } else {
            return Integer.toString(value);
        }
    }

    private void countUp() {
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }

        if (minutes == 60) {
            timer.stop();
        }
    }
}
