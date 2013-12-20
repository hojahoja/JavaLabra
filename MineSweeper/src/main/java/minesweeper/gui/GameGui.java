package minesweeper.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author juri
 */
public class GameGui implements Runnable{

    private JFrame frame;
    
    
    @Override
    public void run() {
        frame = new JFrame("Minesweeper9001");
        frame.setPreferredSize(new Dimension(250, 300));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container contentPane) {
        
    }

    public JFrame getFrame() {
        return frame;
    }
    
    
    
}
