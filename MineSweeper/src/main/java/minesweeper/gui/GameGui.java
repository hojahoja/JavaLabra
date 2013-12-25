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

    // tulee sisältämään Guin näkyvät komponentit
    private JFrame frame;
    private GameLogic gameLogic;
    // Matriisi, joka sisältää miinakentän solujen graafisen ilmentymän.
    // Kaikki Solujen sisältöön kuuluva tieto löytyy MineField luokan omasta matriisista.
    private JButton fieldButtons[][];
    // Siältää JButton iconit ja mahdolliset tallennustiedostot.
    private FileContainer fileContainer;

    public GameGui() {
        fileContainer = new FileContainer();
        gameLogic = new GameLogic(10, 10, 10);
    }

    @Override
    public void run() {
        frame = new JFrame("Minesweeper9001");
        frame.setPreferredSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container contentPane) {
        JPanel mainWindow = new JPanel();
        mainWindow.setLayout(new BoxLayout(mainWindow, BoxLayout.Y_AXIS));

        JPanel mineFieldCells = createMineFieldCells();

        mainWindow.add(mineFieldCells);
        contentPane.add(mainWindow);
    }

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
    private void createMineFieldButtons(int height, int width) {

        fieldButtons = new JButton[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j] = new JButton(" ");
                setMineFieldIcons(i, j);
            }
        }
    }

    private void setMineFieldIcons(int i, int j) {
        JButton button = fieldButtons[i][j];
        boolean cellHasAMine = gameLogic.getMinefield().getCell(j, i).isMine();
        
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setIcon(fileContainer.getEmptyIcon());
        
        if (cellHasAMine) {
            button.setDisabledIcon(fileContainer.getBombIcon());
        }
        
    }

    // perus getterit alkaa tästä.
    public JFrame getFrame() {
        return frame;
    }

    public JButton[][] getFieldButtons() {
        return fieldButtons;
    }

    private void addAListenerToButtons() {
    }

    private void addAMouseListenerToButtons(int height, int width) {
        GameListener gameListener = new GameListener(fieldButtons, gameLogic, fileContainer);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j].addMouseListener(gameListener);
            }
        }
    }
}
