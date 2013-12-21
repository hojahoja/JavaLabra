package minesweeper.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import minesweeper.domain.Minefield;

/**
 *
 * @author juri
 */
public class GameGui implements Runnable {

    private JFrame frame;
    private Minefield minefield;
    private JButton fieldButtons[][];

    public GameGui() {
        minefield = new Minefield(10, 10, 10);
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
        int height = minefield.getHeight();
        int width = minefield.getWidth();
        JPanel mineFieldCells = new JPanel(new GridLayout(height, width));
        
        createMineFieldButtons(height, width);
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mineFieldCells.add(fieldButtons[i][j]);
            }
        }
        
        return  mineFieldCells;
    }

    private void createMineFieldButtons(int height, int width) {
        
        fieldButtons = new JButton[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                fieldButtons[i][j] = new JButton("");
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public Minefield getMinefield() {
        return minefield;
    }

    public JButton[][] getFieldButtons() {
        return fieldButtons;
    }
}
