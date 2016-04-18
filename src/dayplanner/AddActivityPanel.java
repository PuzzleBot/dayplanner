/**
 * A panel that contains all editable components required to gather
 * activity adding criteria from the user
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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddActivityPanel extends JPanel{
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel messagePanel;
    
    private JPanel locationSubPanel;
    
    private JComboBox activityTypeBox;
    private JTextField activityTitleField;
    private JTextField[] startTimeFields;
    private JTextField[] endTimeFields;
    private JTextField activityCommentField;
    private JTextField activityLocationField;
    
    private JTextArea messageArea;
    private JScrollPane messageScroll;
    
    private JButton resetButton;
    private JButton enterButton;
    
    /**
     * Constructs the panel with the required components
     */
    public AddActivityPanel(){
        super();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));
        
        JPanel gridPanel;
        
        /*Activity type combo box*/
        gridPanel = new JPanel();
        gridPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.add(new JLabel("Activity type: "));
        String[] comboBoxOptions = {"Home Activity", "School Activity", "Other Activity"};
        activityTypeBox = new JComboBox(comboBoxOptions);
        activityTypeBox.setSelectedIndex(1);
        gridPanel.add(activityTypeBox);
        inputPanel.add(gridPanel);
        
        /*Title field*/
        gridPanel = new JPanel();
        gridPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gridPanel.setBackground(Color.LIGHT_GRAY);
        gridPanel.add(new JLabel("Title: "));
        activityTitleField = new JTextField("", 30);
        gridPanel.add(activityTitleField);
        inputPanel.add(gridPanel);
        
        /*Starting time fields*/
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 1));
        
        JPanel topHalf = new JPanel();
        topHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
        topHalf.setBackground(Color.WHITE);
        topHalf.add(new JLabel("Starting Time (Year/Month/Day Hour:Minute)"));
        gridPanel.add(topHalf);
        
        JPanel bottomHalf = new JPanel();
        bottomHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomHalf.setBackground(Color.WHITE);
        gridPanel.add(bottomHalf);
        
        startTimeFields = new JTextField[5];
        
        int i;
        for(i = 0; i < 5; i++){
            /*Create text fields*/
            if(i != 0){
                startTimeFields[i] = new JTextField("", 2);
            }
            else{
                startTimeFields[i] = new JTextField("", 4);
            }
            bottomHalf.add(startTimeFields[i]);
            
            /*Insert slashes and colons between fields to distinguish them*/
            if((i == 0) || (i == 1)){
                bottomHalf.add(new JLabel("/"));
            }
            else if(i == 2){
                bottomHalf.add(new JLabel("   "));
            }
            else if(i == 3){
                bottomHalf.add(new JLabel(":"));
            }
        }
        inputPanel.add(gridPanel);
        
        
        /*Ending time fields*/
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 1));
        
        topHalf = new JPanel();
        topHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
        topHalf.setBackground(Color.LIGHT_GRAY);
        topHalf.add(new JLabel("Ending Time (Year/Month/Day Hour:Minute)"));
        gridPanel.add(topHalf);
        
        bottomHalf = new JPanel();
        bottomHalf.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomHalf.setBackground(Color.LIGHT_GRAY);
        gridPanel.add(bottomHalf);
        
        endTimeFields = new JTextField[5];
        
        for(i = 0; i < 5; i++){
            /*Create text fields*/
            if(i != 0){
                endTimeFields[i] = new JTextField("", 2);
            }
            else{
                endTimeFields[i] = new JTextField("", 4);
            }
            bottomHalf.add(endTimeFields[i]);
            
            /*Insert slashes and colons between fields to distinguish them*/
            if((i == 0) || (i == 1)){
                bottomHalf.add(new JLabel("/"));
            }
            else if(i == 2){
                bottomHalf.add(new JLabel("   "));
            }
            else if(i == 3){
                bottomHalf.add(new JLabel(":"));
            }
        }
        inputPanel.add(gridPanel);
        
        /*Comment field*/
        gridPanel = new JPanel();
        gridPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.add(new JLabel("Comment: "));
        activityCommentField = new JTextField("", 30);
        gridPanel.add(activityCommentField);
        inputPanel.add(gridPanel);
        
        /*Location field (only visible when otherActivity is selected)*/
        locationSubPanel = new JPanel();
        locationSubPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        locationSubPanel.setBackground(Color.LIGHT_GRAY);
        locationSubPanel.add(new JLabel("Location: "));
        activityLocationField = new JTextField("", 30);
        locationSubPanel.add(activityLocationField);
        locationSubPanel.setVisible(false);
        inputPanel.add(locationSubPanel);

        add(inputPanel, BorderLayout.CENTER);
        
        
        /*Messages panel*/
        messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setBackground(Color.DARK_GRAY);
        messageArea = new JTextArea("", 10, 30);
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageScroll = new JScrollPane(messageArea);
        messageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        messagePanel.add(messageScroll, BorderLayout.CENTER);
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        textPanel.setBackground(Color.DARK_GRAY);
        JLabel areaLabel = new JLabel("Messages");
        areaLabel.setForeground(Color.WHITE);
        textPanel.add(areaLabel);
        messagePanel.add(textPanel, BorderLayout.NORTH);
        
        add(messagePanel, BorderLayout.SOUTH);
        
        
        /*Buttons panel*/
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,1));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(new JLabel(" "));
        
        JPanel resetPanel = new JPanel();
        resetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resetPanel.setBackground(Color.DARK_GRAY);
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 40));
        resetPanel.add(resetButton);
        buttonPanel.add(resetPanel);
        
        JPanel enterPanel = new JPanel();
        enterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        enterPanel.setBackground(Color.DARK_GRAY);
        enterButton = new JButton("Enter");
        enterButton.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(enterButton);
        enterPanel.add(enterButton);
        buttonPanel.add(enterPanel);
        
        add(buttonPanel, BorderLayout.EAST);
        
        setVisible(false);
    }
    
    /**
     * Activity type component accessor
     */
    public JComboBox getActTypeBox(){
        return activityTypeBox;
    }
    
    /**
     * Reset button accessor
     */
    public JButton getResetButton(){
        return resetButton;
    }
    
    /**
     * Enter button accessor
     */
    public JButton getEnterButton(){
        return enterButton;
    }
    
    /**
     * Changes visibility of the location field based on the given boolean
     */
    public void changeLocationVisibility(boolean isVisible){
        locationSubPanel.setVisible(isVisible);
    }
    
    /**
     * Clears all text fields
     */
    public void clearAllInput(){
        activityTitleField.setText("");
        activityCommentField.setText("");
        activityLocationField.setText("");
        
        int i;
        for(i = 0; i < 5; i++){
            startTimeFields[i].setText("");
        }
        
        for(i = 0; i < 5; i++){
            endTimeFields[i].setText("");
        }
    }
    
    /**
     * Activity type input retrieval
     */
    public String getActType(){
        if(activityTypeBox.getSelectedIndex() == 0){
            return "HomeActivity";
        }
        else if(activityTypeBox.getSelectedIndex() == 1){
            return "SchoolActivity";
        }
        else{
            return "OtherActivity";
        }
    }
    
    /**
     * Title input retrieval
     */
    public String getActivityTitle() throws EmptyTitleException{
        if((activityTitleField.getText() == null) || (activityTitleField.getText().equals(""))){
            throw new EmptyTitleException("You must specify a title.");
        }
        return activityTitleField.getText();
    }
    
    /**
     * Comment input retrieval
     */
    public String getActivityComment(){
        return activityCommentField.getText();
    }
    
    /**
     * Location input retrieval
     */
    public String getActivityLocation(){
        return activityLocationField.getText();
    }
    
    /**
     * Start time input retrieval
     */
    public Time getActivityStart() throws InvalidTimeException{
        Time startTime = new Time();
        
        try{
            startTime = new Time(Integer.parseInt(startTimeFields[0].getText()), Integer.parseInt(startTimeFields[1].getText()), Integer.parseInt(startTimeFields[2].getText()), Integer.parseInt(startTimeFields[3].getText()), Integer.parseInt(startTimeFields[4].getText()));
        }
        catch(NumberFormatException e){
            throw new InvalidTimeException("Non-integer time input not allowed.");
        }
        return startTime;
    }
    
    /**
     * End time input retrieval
     */
    public Time getActivityEnd() throws InvalidTimeException{
        Time endTime = new Time();
        
        try{
            endTime = new Time(Integer.parseInt(endTimeFields[0].getText()), Integer.parseInt(endTimeFields[1].getText()), Integer.parseInt(endTimeFields[2].getText()), Integer.parseInt(endTimeFields[3].getText()), Integer.parseInt(endTimeFields[4].getText()));
        }
        catch(NumberFormatException e){
            throw new InvalidTimeException("Non-integer time input not allowed.");
        }
        return endTime;
    }
    
    /**
     * Writes the given string to the text area on a new line after
     * the existing text
     */
    public void addMsgText(String newText){
        String currentText = messageArea.getText();
        messageArea.setText(currentText + "\n" + newText);
    }
    
    /**
     * Clears the text area
     */
    public void clearMsgText(){
        messageArea.setText(" ");
    }
}
