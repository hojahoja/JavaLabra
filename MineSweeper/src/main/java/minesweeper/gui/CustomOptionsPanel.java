/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author juri
 */
public class CustomOptionsPanel extends JPanel {

    private JTextField heightField;
    private JTextField widthField;
    private JTextField minesField;
    int height;
    int width;
    int mines;

    public CustomOptionsPanel() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        heightField = new JTextField();
        widthField = new JTextField();
        minesField = new JTextField();


        JLabel hString = new JLabel("height 8 - 16");
        JLabel wString = new JLabel("width 8 - 30");
        JLabel mString = new JLabel("mines 1 - (grid size)");


        this.add(hString);
        this.add(heightField);
        this.add(wString);
        this.add(widthField);
        this.add(mString);
        this.add(minesField);
    }
    
    public void clearPanelValues() {
        heightField.setText("");
        widthField.setText("");
        minesField.setText("");
        height = width = mines = 0;
    }

    public int getHeightCount() throws NumberFormatException {
        height = Integer.parseInt(heightField.getText());
        height = valueValidator(height, heightField);
        return height;
    }

    public int getWidthCount() throws NumberFormatException {
        width = Integer.parseInt(widthField.getText());
        width = valueValidator(width, widthField);
        return width;
    }

    public int getMinesCount() throws NumberFormatException {
        mines = Integer.parseInt(minesField.getText());
        mines = valueValidator(mines, minesField);
        return mines;
    }

    private int valueValidator(int value, JTextField field) {
        if (!field.equals(minesField) && value < 8) {
            value = 8;
        } else if (field.equals(heightField) && value > 16) {
            value = 16;
        } else if (field.equals(widthField) && value > 30) {
            value = 30;
        } else if (field.equals(minesField) && value < 1 || value > (height * width)) {
            value = 10;
        }

        return value;
    }
    
}
