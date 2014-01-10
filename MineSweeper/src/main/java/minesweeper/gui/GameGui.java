package minesweeper.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import minesweeper.domain.FileContainer;
import minesweeper.domain.ScoreKeeper;
import minesweeper.logic.GameLogic;

/**
 * The main frame of the game. JRames content pane contains every part of the
 * game graphics
 *
 * @author juri
 */
public class GameGui implements Runnable {

    /**
     * The main JFrame of the GUI It's contentPane contains every graphical
     * component fo the game, not including couple generated JOptionPanes.
     */
    private JFrame frame;
    /**
     * GameLogic is generated GUI.
     */
    private GameLogic gameLogic;
    /**
     * 2D array that contains the JButtons which works as clickable cells of the
     * game.
     */
    private JButton fieldButtons[][];
    /**
     * FileContainer is generated with the GUI.
     */
    private FileContainer fileContainer;
    /**
     * This JPanel contains all the visual components besides the gameMenuBar.
     */
    private JPanel mainWindow;
    /**
     * The main listener of the game field.
     */
    private GameListener gameListener;
    /**
     * The main listener of the graphical components.
     */
    private FrameListener frameListener;
    /**
     * The JMenubar of the frame.
     */
    private GameMenuBar gameMenuBar;
    /**
     * The timer of the game.
     */
    private CountUpTimer countUpTimer;
    /**
     * Has the score information
     */
    private ScoreKeeper scoreKeeper;
    /**
     * Used to check and update the score
     */
    private ScoreChecker scoreChecker;

    /**
     * The game is set up for easy mode when it's first created.
     */
    public GameGui() {
        fileContainer = new FileContainer();
        scoreKeeper = new ScoreKeeper(fileContainer);
        scoreKeeper.updateScores();
        scoreChecker = new ScoreChecker(scoreKeeper, this, fileContainer);
        gameLogic = new GameLogic(8, 8, 10);
        frameListener = new FrameListener(this); // adds the GUI to the listener
        gameMenuBar = new GameMenuBar(frameListener);
        countUpTimer = new CountUpTimer();
    }

    @Override
    public void run() {
        frame = new JFrame("Minesweeper9001");
        addMenuBar();
        int preferredX = gameLogic.getFieldWidth() * 85 / 2;
        int preferredY = gameLogic.getFieldHeight() * 100 / 2;
        frame.setPreferredSize(new Dimension(preferredX, preferredY));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
        frame.pack();

        frame.setVisible(true);
    }

    /**
     * Adds a JMenuBar to the frame.
     *
     */
    private void addMenuBar() {
        frame.setJMenuBar(gameMenuBar);
    }

    /**
     * Creates the components used in the gameplay GUI. Creates the main JPabel
     * that will contains the minefield JPanel and other information.
     *
     * @param contentPane JFrames' contentPane
     */
    public void createComponents(Container contentPane) {
        mainWindow = new JPanel();
        mainWindow.setLayout(new BoxLayout(mainWindow, BoxLayout.Y_AXIS));

        JPanel mineFieldCells = createMineFieldCells();
        JPanel buttonBar = createButtonBar();
        JPanel infoBar = createInfoBar();


        mainWindow.add(buttonBar);
        mainWindow.add(mineFieldCells);
        mainWindow.add(infoBar);
        contentPane.add(mainWindow);
    }

    /**
     * Creates a JPanel that has the reset and high scores buttons.
     *
     * @return the created JPanel.
     */
    private JPanel createButtonBar() {
        JPanel buttonBar = new JPanel();
        JButton reset = new JButton("Reset Game");
        JLabel status = new JLabel("Still Alive");

        frameListener.addResetButton(reset);

        gameListener.addStatusLabel(status);
        reset.addActionListener(frameListener);
        buttonBar.add(reset);
        buttonBar.add(status);

        return buttonBar;
    }

    /**
     * Creates the main minefield. A JPanel with a GridLayout that contains
     * JButtons
     *
     * @return JPanel that contains the minefield cells as JButtons
     */
    private JPanel createMineFieldCells() {
        int height = gameLogic.getFieldHeight();
        int width = gameLogic.getFieldWidth();
        JPanel mineFieldCells = new JPanel(new GridLayout(height, width));

        createMineFieldButtons(height, width);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mineFieldCells.add(fieldButtons[i][j]);
            }
        }

        addAMouseListenerToCellButtons(height, width);
        return mineFieldCells;
    }

    /**
     * Creates the buttons that will be contained inside the mineFieldCells
     * JPanel
     *
     * @param height determines the amount of mines
     * @param width determines the amount of mines
     */
    private void createMineFieldButtons(int height, int width) {

        fieldButtons = new JButton[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j] = new JButton(" ");
                setMineFieldIcons(i, j);
            }
        }
    }

    /**
     * Sets the proper icons to JButtons. An Empty default icon is set to every
     * button. Changing the icon from Empty to a Flag guarantees that the
     * JButton will always have a default icon. This is needed for the Bomb icon
     * to work properly as a DisableButtonIcon.
     *
     * @param i coordinate
     * @param j coordinate
     */
    private void setMineFieldIcons(int i, int j) {
        JButton button = fieldButtons[i][j];
        boolean cellHasAMine = gameLogic.getMinefield().getCell(j, i).isMine();

        button.setMargin(new Insets(0, 0, 0, 0));
        button.setIcon(fileContainer.getEmptyIcon());

        if (cellHasAMine) {
            button.setDisabledIcon(fileContainer.getBombIcon());
        }
    }

    /**
     * Adds the GameListener to JButtons.
     *
     * @param height size
     * @param width size
     */
    private void addAMouseListenerToCellButtons(int height, int width) {
        gameListener = new GameListener(fieldButtons, gameLogic, fileContainer, countUpTimer, scoreChecker);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j].addMouseListener(gameListener);
            }
        }
    }

    /**
     * Creates the lower information bar which contains basic information about the game
     * and has a running clock
     *
     * @return InforBar JPanel
     */
    private JPanel createInfoBar() {
        JPanel infoBar = new JPanel(new BorderLayout());
        JLabel flagCount = new JLabel("Flags: 0/" + gameLogic.getMinefield().getMines());
        JLabel difficulty = new JLabel("       Difficulty: Easy");

        gameListener.addFlagCountLabel(flagCount);
        frameListener.addDifficultyLabel(difficulty);
        infoBar.add(flagCount, BorderLayout.WEST);
        infoBar.add(countUpTimer.getTimeLabel(), BorderLayout.EAST);
        infoBar.add(difficulty, BorderLayout.CENTER);
        countUpTimer.resetTimer();
        return infoBar;
    }

    /**
     * Creates a new instance of the gameLogic class with the set parameters and
     * updates the GUI to show the result. Previous components are removed.
     *
     * @param height of the new game
     * @param width of the new game
     * @param mines in the new game
     */
    public void createNewGame(int height, int width, int mines) {
        gameLogic = new GameLogic(height, width, mines);
        frame.getContentPane().removeAll();

        int preferredX = gameLogic.getFieldWidth() * 85 / 2;
        int preferredY = gameLogic.getFieldHeight() * 100 / 2;
        frame.setPreferredSize(new Dimension(preferredX, preferredY));

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    // Getters, nothing to see here...
    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public ScoreKeeper getScoreKeeper() {
        return scoreKeeper;
    }

    public ScoreChecker getScoreChecker() {
        return scoreChecker;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton[][] getFieldButtons() {
        return fieldButtons;
    }
}
