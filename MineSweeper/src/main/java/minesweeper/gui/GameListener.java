package minesweeper.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import minesweeper.domain.FileContainer;
import minesweeper.domain.ScoreKeeper;
import minesweeper.logic.GameLogic;
import minesweeper.domain.Score;

/**
 * The main listener class of the game. Activates after either the right or left
 * mouse button has been released updates the GUI with the information gathered
 * from gameLogic
 *
 * @author juri
 */
public class GameListener implements MouseListener {

    /**
     * 2D array that contains the JButtons which works as clickable cells of the
     * game.
     */
    private JButton[][] fieldButtons;
    /**
     * Game listener uses gameLogic to operate.
     */
    private GameLogic gameLogic;
    /**
     * FileContainer is used to get change between flag and empty icons on the.
     * fly
     */
    private FileContainer fileContainer;
    /**
     * status JLabel is updated according to the game status (Ongoing/Win/Lose).
     */
    private JLabel status;
    /**
     * Visual flag count (flagged cells/total mines).
     */
    private JLabel flagCount;
    /**
     * Timer is started when the the first cell is clicked and stopped when the
     * game ends.
     */
    private CountUpTimer countUpTimer;
    /**
     * Used to evaluate the score and write it in the score file if needed.
     */
    private ScoreChecker scoreChecker;
    /**
     * boolean that makes sure that scoreIsn't checked multiple times.
     */
    private boolean doneChecking;

    /**
     * The listener class of the game
     *
     * @param fieldButtons
     * @param gameLogic
     * @param fileContainer
     */
    public GameListener(
            JButton[][] fieldButtons,
            GameLogic gameLogic,
            FileContainer fileContainer,
            CountUpTimer countUpTimer,
            ScoreChecker scoreChecker) {

        this.fieldButtons = fieldButtons;
        this.gameLogic = gameLogic;
        this.fileContainer = fileContainer;
        this.countUpTimer = countUpTimer;
        this.doneChecking = false;
        this.scoreChecker = scoreChecker;
    }

    /**
     * This method is used by the GUI to add the status JLabel after the
     * GameListener has already been created.
     *
     * @param status JLabel that shows if the game is ongoing/won/lost
     */
    public void addStatusLabel(JLabel status) {
        this.status = status;
    }

    /**
     * This method is used by the GUI to add the flagCount JLabel after the
     * GameListener has already been created.
     *
     * @param flagCount number of flags
     */
    public void addFlagCountLabel(JLabel flagCount) {
        this.flagCount = flagCount;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        int height = gameLogic.getFieldHeight();
        int width = gameLogic.getFieldWidth();
        Point buttonCoordinates = getClickedButtonCoordinates(height, width, event.getSource());

        if (event.getButton() == MouseEvent.BUTTON1) {
            gameLogic.openCell(buttonCoordinates.x, buttonCoordinates.y);
            countUpTimer.start();
        } else if (event.getButton() == MouseEvent.BUTTON3) {
            gameLogic.toggleCellFlag(buttonCoordinates.x, buttonCoordinates.y);
        }

        updateWindow(height, width);
    }

    /**
     * Searches for the coordinates (x.y) of the clicked JButton.
     *
     * @param height used as a limit
     * @param width used as a limit
     * @param source the JButton that was clicked.
     * @return the coordinates of the clicked button
     */
    private Point getClickedButtonCoordinates(int height, int width, Object source) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (fieldButtons[i][j] == source) {
                    Point coordinates = new Point(j, i);
                    return coordinates;
                }
            }
        }

        return null;
    }

    /**
     * Goes trough all of the JButtons and calls methods that update the GUI.
     *
     * @param height
     * @param width
     */
    public void updateWindow(int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                openedStatusUpdate(j, i);
                flaggedStatusUpdate(j, i);
                winLoseUpdate();
            }
        }
    }

    /**
     * Updates the JButtons that are set open in their corresponding Cell
     * classes. Does nothing if the cell is closed. If the cell has a mine the
     * button is disabled which reveals the bomb icon. If the cell does not have
     * a mine setAdjacentMineInfo method is called.
     *
     * @param j x coordinate
     * @param i y coordinate
     */
    private void openedStatusUpdate(int j, int i) {
        boolean cellIsOpen = gameLogic.getMinefield().getCell(j, i).isOpen();
        boolean cellHasNoMine = !gameLogic.getMinefield().getCell(j, i).isMine();
        if (cellIsOpen) {
            JButton current = fieldButtons[i][j];

            if (cellHasNoMine) {
                setAdjacentMineInfo(j, i, current);
            } else {
                current.setEnabled(false);
            }
        }
    }

    /**
     * Reveals the number of adjacent mines. Calls a method to color the number
     * of adjacent mines shown in the game. If there are no adjacent mines it
     * will only color the JButton to signify it's opened status.
     *
     * @param j x coordinate
     * @param i y coordinate
     * @param current the clicked JButton
     */
    private void setAdjacentMineInfo(int j, int i, JButton current) {
        int mineCount = gameLogic.getMinefield().getCell(j, i).getAdjacentMineCount();

        if (mineCount > 0) {
            Color color = getNumberColor(mineCount);
            current.setText(mineCount + "");
            current.setForeground(color);
        }

        current.setBackground(Color.WHITE);
    }

    /**
     * Chooses a proper color for the specified mine count.
     *
     * @param mineCount number of adjacent mines
     * @return the Color chosen for a specific number.
     */
    private Color getNumberColor(int mineCount) {
        switch (mineCount) {
            case 1:
                return new Color(25, 25, 255); // light blue
            case 2:
                return Color.GREEN;
            case 3:
                return Color.RED;
            case 4:
                return new Color(0, 0, 153); // dark blue
            case 5:
                return new Color(178, 16, 16); // brown
            case 6:
                return Color.CYAN;
            case 7:
                return Color.BLACK;
            default:
                return Color.GRAY;
        }
    }

    /**
     * Toggles if the flag icon is shown in the game. Toggles between an empty
     * icon or a flag icon depending on the flagged status of the cell. Does
     * nothing if the cell is opened.
     *
     *
     * @param j x coordinate
     * @param i y coordinate
     */
    private void flaggedStatusUpdate(int j, int i) {
        ImageIcon flagIcon = fileContainer.getFlagIcon();
        boolean cellHasAFlag = gameLogic.getMinefield().getCell(j, i).isFlagged();
        boolean cellHasFlagIcon = fieldButtons[i][j].getIcon() == flagIcon;
        int flags = gameLogic.getFlaggedCellCount();

        if (cellHasAFlag) {
            fieldButtons[i][j].setIcon(flagIcon);
        } else {
            if (cellHasFlagIcon) {
                fieldButtons[i][j].setIcon(fileContainer.getEmptyIcon());
            }
        }

        String flagCounterText = "Flags: " + flags + "/" + gameLogic.getMinefield().getMines();
        flagCount.setText(flagCounterText);
    }

    /**
     * If the game is lost or won the JLabel is updated accordingly. and the
     * timer is stopped
     *
     */
    private void winLoseUpdate() {
        if (gameLogic.isGameWon()) {
            status.setText("Flawless Victory");
            countUpTimer.stop();
            checkHighScores();
        }

        if (gameLogic.isGameLost()) {
            status.setText("Bitter Defeat");
            countUpTimer.stop();
        }
    }

    /**
     * Checks for current difficulty settings and calls scorekeeper to evaluate
     * the finish time for possible high scores. calls for a method to add the
     * new score if is in the top 10. this method will return doing nothing if
     * it has already called in the current game
     *
     */
    private void checkHighScores() {
        if (doneChecking) {
            return;
        }

        String time = countUpTimer.getTime();
        scoreChecker.evaluateScore(time);
        doneChecking = true;
    }

    // Unused methods that are only here because the 
    // implementation of MouseListener needs them to be here.
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
