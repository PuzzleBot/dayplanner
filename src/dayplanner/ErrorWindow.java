/**
 * A prompt window displayed when an exception is thrown from
 * invalid input
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ErrorWindow extends JFrame{
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;
    
    private JButton okButton;
    
    public ErrorWindow(String errorMsg){
        super("Invalid input");
        setLayout(new GridLayout(2,1));
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitCheck());
        
        JPanel textPanel;
        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.add(new JLabel(errorMsg));
        add(textPanel);
        
        JPanel buttonPanel;
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        okButton = new JButton("OK");
        buttonPanel.add(okButton);
        add(buttonPanel);
        
        setVisible(true);
    }
    
    private class ExitCheck extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            dispose();
        }
    }
    
    /**
     * OK button accessor
     */
    public JButton getOKButton(){
        return okButton;
    }
}
