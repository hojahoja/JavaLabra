package minesweeper.gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import minesweeper.domain.FileContainer;
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
    private FileContainer fileContainer;

    public GameListener(JButton[][] fieldButtons, GameLogic gameLogic, FileContainer fileContainer) {
        this.fieldButtons = fieldButtons;
        this.gameLogic = gameLogic;
        this.fileContainer = fileContainer;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        int height = gameLogic.getFieldHeight();
        int width = gameLogic.getFieldWidth();
        Point buttonCoordinates = getClickedButtonCoordinates(height, width, event.getSource());

        if (event.getButton() == MouseEvent.BUTTON1) {
            gameLogic.openCell(buttonCoordinates.x, buttonCoordinates.y);
        } else if (event.getButton() == MouseEvent.BUTTON3) {
            gameLogic.toggleCellFlag(buttonCoordinates.x, buttonCoordinates.y);
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
                checkButtonCondition(j, i);
            }
        }
    }

    private void checkButtonCondition(int j, int i) {
        openedStatusUpdate(j, i);
        flaggedStatusUpdate(j, i);


    }

    private void openedStatusUpdate(int j, int i) {
        boolean cellIsOpen = gameLogic.getMinefield().getCell(j, i).isOpen();
        boolean cellHasNoMine = !gameLogic.getMinefield().getCell(j, i).isMine();
        if (cellIsOpen) {
            JButton current = fieldButtons[i][j];
            if (cellHasNoMine) {
                setMineAdjacentMineInfo(j, i, current);
            } else {
                current.setEnabled(false);
            }
        }
    }

    private void setMineAdjacentMineInfo(int j, int i, JButton current) {
        int mineCount = gameLogic.getMinefield().getCell(j, i).getAdjacentMineCount();

        if (mineCount > 0) {
            Color color = getNumberColor(mineCount);
            current.setText(mineCount + "");
            current.setForeground(color);
        }

        current.setBackground(Color.WHITE);
    }

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

    private void flaggedStatusUpdate(int j, int i) {
        ImageIcon flagIcon = fileContainer.getFlagIcon();
        boolean cellHasAFlag = gameLogic.getMinefield().getCell(j, i).isFlagged();
        boolean cellHasFlagIcon = fieldButtons[i][j].getIcon() == flagIcon;

        if (cellHasAFlag) {
            fieldButtons[i][j].setIcon(flagIcon);
        } else {
            if (cellHasFlagIcon) {
                fieldButtons[i][j].setIcon(fileContainer.getEmptyIcon());
            }
        }
    }
}
