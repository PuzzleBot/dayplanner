/**
 * The main GUI window for the Day Planner application, holding the
 * activity adding and searching panels
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
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class DayPlannerWindow extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 650;

    private DayPlanner planner;
    private String[] runArguments;

    private CommandsListener commandListener;
    private AddListener addListener;
    private SearchListener searchListener;
    private ExitListener exitListener;

    private JPanel midPanel;
    private JPanel welcomePanel;
    private AddActivityPanel addPanel;
    private SearchActivityPanel searchPanel;

    private ErrorWindow errWin;
    private ExitWindow exitWin;

    private JMenuBar topMenuBar;
    private JMenu commandsMenu;
    private JMenuItem addCommand;
    private JMenuItem searchCommand;
    private JMenuItem exitCommand;

    /**
     * Constructs the window with the required components
     */
    public DayPlannerWindow(DayPlanner planner, String[] args) {
        super("Day Planner");
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitCheck());
        setLayout(new BorderLayout());

        this.planner = planner;
        runArguments = args;

        commandListener = new CommandsListener();
        addListener = new AddListener();
        searchListener = new SearchListener();
        exitListener = new ExitListener();

        /*Create the commands menu at the top*/
        topMenuBar = new JMenuBar();
        topMenuBar.setBackground(Color.BLACK);
        add(topMenuBar, BorderLayout.NORTH);

        commandsMenu = new JMenu("Commands");
        addCommand = new JMenuItem("Add activity");
        addCommand.addActionListener(this.commandListener);
        commandsMenu.add(addCommand);
        searchCommand = new JMenuItem("Search for activities");
        searchCommand.addActionListener(this.commandListener);
        commandsMenu.add(searchCommand);
        exitCommand = new JMenuItem("Save and Exit");
        exitCommand.addActionListener(this.commandListener);
        commandsMenu.add(exitCommand);
        topMenuBar.add(commandsMenu);

        /*Create the main content panels*/
        JPanel tempPanel;

        midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.X_AXIS));

        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(5, 1));
        welcomePanel.add(new JLabel(""));
        tempPanel = new JPanel();
        tempPanel.setLayout(new FlowLayout());
        tempPanel.add(new JLabel("Welcome to the Day Planner."));
        welcomePanel.add(tempPanel);
        tempPanel = new JPanel();
        tempPanel.setLayout(new FlowLayout());
        tempPanel.add(new JLabel("Pick a choice from the drop-down menu above to get started."));
        welcomePanel.add(tempPanel);
        midPanel.add(welcomePanel);
        welcomePanel.setVisible(true);

        addPanel = new AddActivityPanel();
        addPanel.getActTypeBox().addActionListener(this.addListener);
        addPanel.getResetButton().addActionListener(this.addListener);
        addPanel.getEnterButton().addActionListener(this.addListener);
        midPanel.add(addPanel);
        addPanel.setVisible(false);

        searchPanel = new SearchActivityPanel();
        searchPanel.getActTypeBox().addActionListener(this.searchListener);
        searchPanel.getResetButton().addActionListener(this.searchListener);
        searchPanel.getEnterButton().addActionListener(this.searchListener);
        midPanel.add(searchPanel);
        searchPanel.setVisible(false);

        add(midPanel);

        setVisible(true);
    }

    /**
     * Listens to the commands menu to know which center panel to show
     */
    private class CommandsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "Add activity":
                    welcomePanel.setVisible(false);
                    addPanel.setVisible(true);
                    searchPanel.setVisible(false);
                    break;
                case "Search for activities":
                    welcomePanel.setVisible(false);
                    addPanel.setVisible(false);
                    searchPanel.setVisible(true);
                    break;
                case "Save and Exit":
                    exitWin = new ConfirmSaveExitWindow();
                    exitWin.getYesButton().addActionListener(exitListener);
                    exitWin.getCancelButton().addActionListener(exitListener);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Listens to the components of the add panel to know when to reset
     * the panel's components or convert the given information to
     * an activity
     */
    private class AddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            try {
                switch (command) {
                    case "comboBoxChanged":
                        if (addPanel.getActTypeBox().getSelectedIndex() == 2) {
                            addPanel.changeLocationVisibility(true);
                        } else {
                            addPanel.changeLocationVisibility(false);
                        }
                        break;
                    case "Reset":
                        addPanel.clearAllInput();
                        break;
                    case "Enter":
                        String activityType = addPanel.getActType();
                        String title = addPanel.getActivityTitle();
                        String comment = addPanel.getActivityComment();

                        Time startTime = addPanel.getActivityStart();
                        Time endTime = addPanel.getActivityEnd();

                        if (activityType.equals("OtherActivity")) {
                            String location = addPanel.getActivityLocation();
                            planner.addAndMap(new OtherActivity(title, startTime, endTime, comment, location));
                            addPanel.addMsgText("Other Activity \'" + title + "' added.");
                        } else {
                            if (activityType.equals("HomeActivity")) {
                                planner.addAndMap(new HomeActivity(title, startTime, endTime, comment));
                                addPanel.addMsgText("Home Activity \'" + title + "' added.");
                            } else if (activityType.equals("SchoolActivity")) {
                                planner.addAndMap(new SchoolActivity(title, startTime, endTime, comment));
                                addPanel.addMsgText("School Activity \'" + title + "' added.");
                            } else {
                                System.out.println("Activity not found");
                            }
                        }

                        break;
                    case "OK":
                        errWin.dispose();
                        break;
                    default:
                        break;
                }
            } catch (Exception except) {
                errWin = new ErrorWindow(except.getMessage());
                errWin.getOKButton().addActionListener(this);
            }
        }
    }

    /**
     * Listens to the components of the search panel to know when to reset
     * the panel's components or convert the given information to
     * a search query
     */
    private class SearchListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            try {
                switch (command) {
                    case "Reset":
                        searchPanel.clearAllInput();
                        break;
                    case "Enter":
                        searchPanel.clearMsgText();
                        String activityType = searchPanel.getActType();
                        String keywords = searchPanel.getActivityKeys();

                        Time startTime = searchPanel.getActivityStart();
                        Time endTime = searchPanel.getActivityEnd();

                        String[] keyWordArray = keywords.split("[ ,\n]+");
                        String resultString;
                        if (activityType.equals("OtherActivity")) {
                            resultString = planner.guiSearchList(OtherActivity.class, startTime, endTime, keyWordArray);
                        } else if (activityType.equals("HomeActivity")) {
                            resultString = planner.guiSearchList(HomeActivity.class, startTime, endTime, keyWordArray);
                        } else if (activityType.equals("SchoolActivity")) {
                            resultString = planner.guiSearchList(SchoolActivity.class, startTime, endTime, keyWordArray);
                        } else {
                            resultString = "No activities found!";
                        }

                        searchPanel.addMsgText(resultString);
                        break;
                    case "OK":
                        errWin.dispose();
                        break;
                    default:
                        break;
                }
            } catch (Exception except) {
                errWin = new ErrorWindow(except.getMessage());
                errWin.getOKButton().addActionListener(this);
            }
        }
    }

    /**
     * Listens to the exit windows to know whether the user is sure they want
     * to quit and to know if they want to save to a file or not
     */
    private class ExitListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "Yes":
                    planner.saveData(runArguments);
                    System.exit(0);
                    break;
                case "No":
                    System.exit(0);
                    break;
                case "Cancel":
                    exitWin.dispose();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Window listener which brings up exit prompts
     */
    private class ExitCheck extends WindowAdapter {

        public ExitCheck() {
            super();
        }

        public void windowClosing(WindowEvent e) {
            exitWin = new ConfirmExitWindow();
            exitWin.getYesButton().addActionListener(exitListener);
            exitWin.getNoButton().addActionListener(exitListener);
            exitWin.getCancelButton().addActionListener(exitListener);
        }
    }
}
