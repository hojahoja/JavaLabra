package minesweeper.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import minesweeper.logic.GameLogic;

/**
 *
 * @author juri
 */
public class GameListener implements MouseListener {

    //Turhia meteodeja ohjelman kannalta, jotka kuitenkin luodaan, jotta
    //rajapinta toteutuisi.
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
    /**
     *
     *
     * Turhat metodit siiretty toistaiseksi pois häiritsemästä. Kun luokka on
     * valmiimpi siirretään ne pois ihmisten silmistä.
     *
     */
    private JButton[][] fieldButtons;
    private GameLogic gameLogic;

    public GameListener(JButton[][] fieldButtons, GameLogic gameLogic) {
        this.fieldButtons = fieldButtons;
        this.gameLogic = gameLogic;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        int height = gameLogic.getFieldHeight();
        int width = gameLogic.getFieldWidth();
        Point buttonCoordinates = getClickedButtonCoordinates(height, width, event.getSource());

        if (event.getButton() == MouseEvent.BUTTON1) {
            gameLogic.openCell(buttonCoordinates.x, buttonCoordinates.y);
        }
        
        updateWindow(height, width);
    }

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

    private void updateWindow(int height, int width) {      
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boolean cellIsOpen = gameLogic.getMinefield().getCell(j, i).isOpen();
                if (cellIsOpen) {
                    fieldButtons[i][j].setEnabled(false);
                }
            }
        }
    }
}
