/**
 * A prompt window displayed when the user tries to close the program
 */
package dayplanner;

/*
 * Course: CIS2430 
 * Assignment 3 
 * Author: Brandon Tan 
 * ID: 0845538 
 * Date: November 28, 2014
 *
 * Assignment 1 base provided by Dr F Song 
 * Permitted for use
 */

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public abstract class ExitWindow extends JFrame{
    protected static final int WIDTH = 300;
    protected static final int HEIGHT = 150;
    
    protected JButton yesButton;
    protected JButton cancelButton;
    
    public ExitWindow(){
        super("Confirm Exit");
        addWindowListener(new ExitCheck());
    }
    
    protected class ExitCheck extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
    
    /**
     * Yes button accessor
     */
    public JButton getYesButton(){
        return yesButton;
    }
    
    /**
     * No button accessor
     */
    public abstract JButton getNoButton();
    
    /**
     * Cancel button accessor
     */
    public JButton getCancelButton(){
        return cancelButton;
    }
}