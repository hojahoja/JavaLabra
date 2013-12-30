package minesweeper.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import minesweeper.domain.FileContainer;
import minesweeper.domain.Minefield;
import minesweeper.logic.GameLogic;

/**
 *
 * @author juri
 */
public class GameGui implements Runnable {

    private JFrame frame;
    private GameLogic gameLogic;
    private JButton fieldButtons[][];
    private FileContainer fileContainer;

    public GameGui() {
        fileContainer = new FileContainer();
        gameLogic = new GameLogic(10, 10, 10);
    }

    @Override
    public void run() {
        frame = new JFrame("Minesweeper9001");
        int preferredX = gameLogic.getFieldWidth() * 80 / 2;
        int preferredY = gameLogic.getFieldHeight() * 80 / 2;
        frame.setPreferredSize(new Dimension(preferredX, preferredY));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();

        frame.setVisible(true);
    }

    /**
     * Creates the components used in the gameplay GUI. Creates the main JPabel
     * that will contains the minefield JPanel and other information.
     *
     * @param contentPane
     */
    private void createComponents(Container contentPane) {
        JPanel mainWindow = new JPanel();
        mainWindow.setLayout(new BoxLayout(mainWindow, BoxLayout.Y_AXIS));

        JPanel mineFieldCells = createMineFieldCells();

        mainWindow.add(mineFieldCells);
        contentPane.add(mainWindow);
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

        addAMouseListenerToButtons(height, width);
        return mineFieldCells;
    }

    // Luo JButton oliot matriisin sisälle
    // Samalla lisätään napeille hiiren kuuntelija.
    // alustaa Jbutton tyhjällä png:llä muuten Jbuttoni ei tunnista
    // Jos default iconia ei ole asetettu Jbutton ei tunnista disabled iconia.
    /**
     * Creates the buttons that will be contained inside the mineFieldCells
     * JPanel
     *
     * @param height
     * @param width
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
     * @param i
     * @param j
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
         * @param height
         * @param width 
         */
        private void addAMouseListenerToButtons(int height, int width) {
        GameListener gameListener = new GameListener(fieldButtons, gameLogic, fileContainer);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j].addMouseListener(gameListener);
            }
        }
    }

    // perus getterit alkaa tästä.
    public JFrame getFrame() {
        return frame;
    }

    public JButton[][] getFieldButtons() {
        return fieldButtons;
    }
}
