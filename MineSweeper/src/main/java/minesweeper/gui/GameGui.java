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
    private JPanel mainWindow;
    private GameListener gameListener;
    private FrameListener frameListener;
    private GameMenuBar gameMenuBar;

    public GameGui() {
        fileContainer = new FileContainer();
        gameLogic = new GameLogic(8, 8, 10);
        frameListener = new FrameListener(this); // adds the GUI to the listener
        gameMenuBar = new GameMenuBar(frameListener);
    }

    @Override
    public void run() {
        frame = new JFrame("Minesweeper9001");
        addMenuBar();
        int preferredX = gameLogic.getFieldWidth() * 85 / 2;
        int preferredY = gameLogic.getFieldHeight() * 98 / 2;
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
     * @param contentPane
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
    private void addAMouseListenerToCellButtons(int height, int width) {
        gameListener = new GameListener(fieldButtons, gameLogic, fileContainer);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j].addMouseListener(gameListener);
            }
        }
    }
    
    /**
     * Creates a new instance of the gameLogic class with the set parameters and updates the GUI
     * to show the result. Previous components are removed.
     * 
     * @param height
     * @param width
     * @param mines 
     */
    public void createNewGame(int height, int width, int mines){
        gameLogic = new GameLogic(height, width, mines);   
        frame.getContentPane().removeAll();
        
        int preferredX = gameLogic.getFieldWidth() * 85 / 2;
        int preferredY = gameLogic.getFieldHeight() * 98 / 2;
        frame.setPreferredSize(new Dimension(preferredX, preferredY));
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    // Getters, nothing to see here...
    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton[][] getFieldButtons() {
        return fieldButtons;
    }

    private JPanel createInfoBar() {
        JPanel infoBar = new JPanel(new BorderLayout());
        JLabel flagCount = new JLabel("Flags: 0/"+gameLogic.getMinefield().getMines());
        
        gameListener.addFlagCountLabel(flagCount);
        infoBar.add(flagCount, BorderLayout.WEST);
        return infoBar;
    }
}
