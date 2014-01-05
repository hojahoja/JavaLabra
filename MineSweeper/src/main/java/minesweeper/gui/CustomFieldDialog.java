/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.gui;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author juri
 */
public class CustomFieldDialog extends JDialog {

    private JOptionPane optionPane;
    private JTextField heightField;
    private JTextField widthField;
    private JTextField minesField;
    private int height;
    private int width;
    private int mines;
    private JButton OK;

    public CustomFieldDialog(Frame frame) {
        super(frame, true);

        heightField = new JTextField();
        widthField = new JTextField();
        minesField = new JTextField();

        String guideline = "Enter the numbers with given limiations\n\n";
        String hString = "height 8 - 16";
        String wString = "width 8 - 30";
        String mString = "mines 0 - (grid size)";

        this.setTitle("Custom Field");
        Object[] dialogComponenents = {
            guideline,
            hString, heightField,
            wString, widthField,
            mString, minesField};
        this.optionPane = new JOptionPane(
                dialogComponenents,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null);

        this.setContentPane(this.optionPane);
    }
}
