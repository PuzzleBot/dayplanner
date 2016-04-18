/**
 * A prompt window displayed when "Save and Exit" is clicked
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

public class ConfirmSaveExitWindow extends ExitWindow{
    
    public ConfirmSaveExitWindow(){
        super();
        setLayout(new GridLayout(3,1));
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        JPanel textPanel;
        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.add(new JLabel("Are you sure you want to exit?"));
        add(textPanel);
        
        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        textPanel.add(new JLabel("(Your activities will be saved.)"));
        add(textPanel);
        
        JPanel buttonPanel;
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        yesButton = new JButton("Yes");
        buttonPanel.add(yesButton);
        buttonPanel.add(new JLabel("    "));
        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        add(buttonPanel);
        
        setVisible(true);
    }
    
    public JButton getNoButton(){
        return null;
    }
}
