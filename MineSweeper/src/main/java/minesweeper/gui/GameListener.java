package minesweeper.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import minesweeper.domain.Cell;
import minesweeper.domain.Minefield;

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
    private Minefield minefield;
    private Cell[][] cellInfo;

    public GameListener(JButton[][] fieldButtons, Minefield minefield) {
        this.fieldButtons = fieldButtons;
        this.minefield = minefield;
        this.cellInfo = minefield.getField();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        try {
            //        JButton clickedButton = getClickedButton(event.getSource());
            getClickedButton(event.getSource());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }

    private void getClickedButton(Object source) {
        int height = minefield.getHeight();
        int width = minefield.getWidth();
        JButton clickedButton;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (fieldButtons[i][j] == source) {
//                    clickedButton = fieldButtons[i][j];
                    System.out.println("Succes story at" + " " + j + " ");
//                    return clickedButton;
                }
            }
        }

//        return null;
    }
}
