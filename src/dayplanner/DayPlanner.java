/**
 * DayPlanner class used to execute the day planner application procedures
 */

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
package dayplanner;

import java.util.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class DayPlanner {

    public static final String[] types = new String[]{"home", "school", "other", "h", "s", "o"};

    /**
     * One arrayList holds all types of activities, use getClass to find out
     * what activity type the activities are
     */
    private ArrayList<Activity> activityList;
    private HashMap<String, ArrayList<Integer>> keyMap;

    public DayPlanner() {
        activityList = new ArrayList<Activity>();
        keyMap = new HashMap<String, ArrayList<Integer>>();
    }

    /**
     * Create a time object for the valid input
     */
    private Time getTime(String line) {
        String[] tokens = line.split("[ ,\n]+");
        if (tokens.length != 5) {
            return null;
        }
        for (int i = 0; i < 5; i++) {
            if (!tokens[i].matches("[-+]?[0-9]+")) {
                return null;
            }
        }
        int year = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int day = Integer.parseInt(tokens[2]);
        int hour = Integer.parseInt(tokens[3]);
        int minute = Integer.parseInt(tokens[4]);
        try {
            return new Time(year, month, day, hour, minute);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Add an activity of any type to the arrayList and hashmap Used by the gui
     * to add activities
     */
    public void addAndMap(Activity newAct) {
        String[] tokens = newAct.getTitle().split(" ");

        activityList.add(newAct);
        addToMap(tokens, activityList.indexOf(newAct));
    }

    /**
     * Create an activity from the input and call addAndMap to add to the list
     */
    public void addActivity(Scanner input) {
        String type = "";
        do {
            System.out.print("Enter an activity type (home, school, or other)\n> ");
            type = input.nextLine();

            if (!matchedKeyword(type, types)) {
                System.out.println("Invalid activity type. Please try again.");
            }
        } while (!matchedKeyword(type, types));

        System.out.print("Enter a title\n> ");
        String title = input.nextLine();

        Time startingTime = null;
        do {
            System.out.print("Enter a starting time (year, month, day, hour, minute)\n> ");
            startingTime = getTime(input.nextLine());

            if (startingTime == null) {
                System.out.println("Invalid format. Please try again.");
            }
        } while (startingTime == null);

        Time endingTime = null;
        do {
            System.out.print("Enter an ending time (year, month, day, hour, minute)\n> ");
            endingTime = getTime(input.nextLine());
            if (endingTime == null) {
                System.out.println("Invalid format. Please try again.");
            }
        } while (endingTime == null);

        String location = "";
        if (type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o")) {
            System.out.print("Enter a location\n> ");
            location = input.nextLine();
        }

        System.out.print("Enter a line of comment\n> ");
        String comment = input.nextLine();

        try {
            if (type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h")) {
                addAndMap(new HomeActivity(title, startingTime, endingTime, comment));
            } else if (type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s")) {
                addAndMap(new SchoolActivity(title, startingTime, endingTime, comment));
            } else {
                addAndMap(new OtherActivity(title, startingTime, endingTime, location, comment));
            }
        } catch (InvalidTimeException e) {
            System.out.println("Invalid activity:" + e.getMessage());
        }
    }

    /**
     * Adds a new index to the keyword hashmap
     */
    private void addToMap(String[] keywords, int newLocation) {
        int i;

        for (i = 0; i < keywords.length; i++) {
            keywords[i] = keywords[i].toLowerCase();
            if (keyMap.containsKey(keywords[i]) == true) {
                keyMap.get(keywords[i]).add(newLocation);
            } else {
                keyMap.put(new String(keywords[i]), new ArrayList<Integer>());
                keyMap.get(keywords[i]).add(newLocation);
            }
        }
    }

    /**
     * Uses the hashmap to search for activities with the passed criteria
     */
    public void searchList(Class<?> activityType, Time startTime, Time endTime, String[] keyWords) {
        int i;
        boolean exists = false;
        ArrayList<Integer> masterList = null;
        ArrayList<Integer> currentList = null;
        Iterator<Integer> masterIter = null;

        int currentElement;

        if (keyWords != null) {
            keyWords[0] = keyWords[0].toLowerCase();

            if (keyMap.containsKey(keyWords[0])) {
                masterList = new ArrayList<Integer>(keyMap.get(keyWords[0]));

                for (i = 1; i < keyWords.length; i++) {
                    keyWords[i] = keyWords[i].toLowerCase();

                    /*Intersection operation*/
                    if (keyMap.containsKey(keyWords[i])) {
                        currentList = new ArrayList<Integer>(keyMap.get(keyWords[i]));
                        masterIter = masterList.iterator();

                        while (masterIter.hasNext() == true) {
                            currentElement = masterIter.next();
                            if (!currentList.contains(currentElement)) {
                                masterList.remove(currentElement);
                            }
                        }
                    } else {
                        masterList.clear();
                    }
                }

                /*Check each index to make sure times and activity types are satisfied*/
                masterIter = masterList.iterator();

                if (masterIter != null) {
                    while (masterIter.hasNext() == true) {
                        currentElement = masterIter.next();

                        if (activityList.get(currentElement).getClass() == activityType) {
                            if ((activityList.get(currentElement).startingTime.compareTo(startTime) > 0)
                                    && (activityList.get(currentElement).endingTime.compareTo(endTime) < 0)) {

                                System.out.println(activityList.get(currentElement).toString());
                                exists = true;
                            }
                        }
                    }
                }
            }
        }

        if (exists == false) {
            System.out.println("No activities exist with the specified criteria.");
        }
    }

    /**
     * Has the same functionality as searchList, except it outputs the results
     * into a string formatted for the GUI
     */
    public String guiSearchList(Class<?> activityType, Time startTime, Time endTime, String[] keyWords) {
        int i;
        boolean exists = false;
        ArrayList<Integer> masterList = null;
        ArrayList<Integer> currentList = null;
        Iterator<Integer> masterIter = null;
        String resultString = "";

        int currentElement;

        if (keyWords != null) {
            keyWords[0] = keyWords[0].toLowerCase();

            if (keyMap.containsKey(keyWords[0])) {
                masterList = new ArrayList<Integer>(keyMap.get(keyWords[0]));

                for (i = 1; i < keyWords.length; i++) {
                    keyWords[i] = keyWords[i].toLowerCase();

                    /*Intersection operation*/
                    if (keyMap.containsKey(keyWords[i])) {
                        currentList = new ArrayList<Integer>(keyMap.get(keyWords[i]));
                        masterIter = masterList.iterator();

                        while (masterIter.hasNext() == true) {
                            currentElement = masterIter.next();
                            if (!currentList.contains(currentElement)) {
                                masterList.remove(currentElement);
                            }
                        }
                    } else {
                        masterList.clear();
                    }
                }

                /*Check each index to make sure times and activity types are satisfied*/
                masterIter = masterList.iterator();

                if (masterIter != null) {
                    while (masterIter.hasNext() == true) {
                        currentElement = masterIter.next();

                        if (activityList.get(currentElement).getClass() == activityType) {
                            if ((activityList.get(currentElement).startingTime.compareTo(startTime) > 0)
                                    && (activityList.get(currentElement).endingTime.compareTo(endTime) < 0)) {

                                resultString = (resultString + (activityList.get(currentElement).toString()) + "\n");
                                exists = true;
                            }
                        }
                    }
                }
            }
        }

        if (exists == false) {
            resultString = "No activities exist with the specified criteria.\n";
        } else {
            resultString = "Results:\n" + resultString;
        }

        return resultString;
    }

    /* 
     * Check if a keyword is on a list of tokens
     */
    private boolean matchedKeyword(String keyword, String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (keyword.equalsIgnoreCase(tokens[i])) {
                return true;
            }
        }
        return false;
    }

    /*
     * Check if all keywords are in a string 
     */
    private boolean matchedKeywords(String[] keywords, String title) {
        String[] tokens = title.split("[ ,\n]+");
        for (int i = 0; i < keywords.length; i++) {
            if (!matchedKeyword(keywords[i], tokens)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gather a search request and find the matched activites in the related
     * list(s)
     */
    public void searchActivities(Scanner input) {
        String type = "";
        do {
            System.out.print("Enter an activity type (home, school, or other)\n> ");
            type = input.nextLine();

            if (!type.isEmpty() && !matchedKeyword(type, types)) {
                System.out.println("Invalid activity type. Please try again.");
            }
        } while (!type.isEmpty() && !matchedKeyword(type, types));

        Time startingTime = null;
        do {
            System.out.print("Enter a starting time (year, month, day, hour, minute)\n> ");
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            } else {
                startingTime = getTime(line);
            }

            if (startingTime == null) {
                System.out.println("Invalid format. Please try again.");
            }
        } while (startingTime == null);

        Time endingTime = null;
        do {
            System.out.print("Enter an ending time (year, month, day, hour, minute)\n> ");
            String line = input.nextLine();
            if (line.isEmpty()) {
                break;
            } else {
                endingTime = getTime(line);
            }

            if (endingTime == null) {
                System.out.println("Invalid format. Please try again.");
            }
        } while (endingTime == null);

        System.out.println("Enter title keywords:");
        String[] keywords = null;
        String line = input.nextLine();
        if (!line.isEmpty()) {
            keywords = line.split("[ ,\n]+");
        }

        // search for matched activities
        System.out.println("Matched activities: ");
        if (type.isEmpty() || type.equalsIgnoreCase("home") || type.equalsIgnoreCase("h")) {
            searchList(HomeActivity.class, startingTime, endingTime, keywords);
        }

        if (type.isEmpty() || type.equalsIgnoreCase("school") || type.equalsIgnoreCase("s")) {
            searchList(SchoolActivity.class, startingTime, endingTime, keywords);
        }

        if (type.isEmpty() || type.equalsIgnoreCase("other") || type.equalsIgnoreCase("o")) {
            searchList(OtherActivity.class, startingTime, endingTime, keywords);
        }
    }

    /**
     * Reads an activity data file and converts the data to an activity
     * ArrayList
     */
    public void fileParse(String filePath) {
        Scanner inputStream = null;
        String dataLine;
        String[] tokens;
        int i;

        Class<?> activityType = Activity.class;
        String title = "";
        Time startTime = new Time();
        Time endTime = new Time();
        String comment = "";
        String location = "";

        /*Tracks what should be read next. 0 means reading for activity type,
         1 means title, 2 means starting time, 3 means ending time, 4 means
         comment, 5 means location
         */
        int readmode;

        try {
            inputStream = new Scanner(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Make sure your filepath is correct.");
            System.out.println("Specified path: " + filePath);
            System.exit(0);
        }

        while (inputStream.hasNextLine() == true) {
            dataLine = inputStream.nextLine();
            tokens = dataLine.split("[ =,]+");

            readmode = -1;

            for (i = 0; i < tokens.length; i++) {
                if (readmode == -1) {
                    if (tokens[i].equalsIgnoreCase("type")) {
                        readmode = 0;
                    } else if (tokens[i].equalsIgnoreCase("title")) {
                        readmode = 1;
                    } else if (tokens[i].equalsIgnoreCase("start")) {
                        readmode = 2;
                    } else if (tokens[i].equalsIgnoreCase("end")) {
                        readmode = 3;
                    } else if (tokens[i].equalsIgnoreCase("comment")) {
                        readmode = 4;
                    } else if (tokens[i].equalsIgnoreCase("location")) {
                        readmode = 5;
                    } else if (tokens[i].equalsIgnoreCase("/")) {
                        try {
                            if (activityType == HomeActivity.class) {
                                addAndMap(new HomeActivity(title, startTime, endTime, comment));
                            } else if (activityType == SchoolActivity.class) {
                                addAndMap(new SchoolActivity(title, startTime, endTime, comment));
                            } else if (activityType == OtherActivity.class) {
                                addAndMap(new OtherActivity(title, startTime, endTime, comment, location));
                            }
                        } catch (InvalidTimeException e) {
                            System.out.println("Detected: End time before start time.");
                            System.out.println("Replacing start time with 1/1/1, 1:1 and end time with 2/2/2, 2:2");

                            try {
                                if (activityType == HomeActivity.class) {
                                    addAndMap(new HomeActivity(title, new Time(), new Time(2), comment));
                                } else if (activityType == SchoolActivity.class) {
                                    addAndMap(new SchoolActivity(title, new Time(), new Time(2), comment));
                                } else if (activityType == OtherActivity.class) {
                                    addAndMap(new OtherActivity(title, new Time(), new Time(2), comment, location));
                                }
                            } catch (Exception e2) {
                                System.out.println("Creation failed.");
                            }
                        } finally {
                            activityType = Activity.class;
                            title = "";
                            startTime = new Time();
                            endTime = new Time();
                            comment = "";
                            location = "";
                        }
                    } else {
                        System.out.println("File read: Invalid line format detected.");
                        System.out.println("Bad segment: " + tokens[i]);
                    }
                } else {
                    switch (readmode) {
                        case 0:
                            if ((tokens[i].equalsIgnoreCase("home"))
                                    || (tokens[i].equalsIgnoreCase("homeActivity"))
                                    || (tokens[i].equalsIgnoreCase("h"))) {

                                activityType = HomeActivity.class;
                            } else if ((tokens[i].equalsIgnoreCase("school"))
                                    || (tokens[i].equalsIgnoreCase("schoolActivity"))
                                    || (tokens[i].equalsIgnoreCase("s"))) {

                                activityType = SchoolActivity.class;
                            } else {
                                activityType = OtherActivity.class;
                            }
                            break;
                        case 1:
                            title = title + tokens[i] + " ";
                            break;
                        case 2:
                            try {
                                switch (i) {
                                    case 1:
                                        startTime.setYear(Integer.parseInt(tokens[i]));
                                        break;
                                    case 2:
                                        startTime.setMonth(Integer.parseInt(tokens[i]));
                                        break;
                                    case 3:
                                        startTime.setDay(Integer.parseInt(tokens[i]));
                                        break;
                                    case 4:
                                        startTime.setHour(Integer.parseInt(tokens[i]));
                                        break;
                                    case 5:
                                        startTime.setMinute(Integer.parseInt(tokens[i]));
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid time component detected. It has been replaced with 1.");
                                System.out.println("Error type: " + e.getMessage());

                                switch (i) {
                                    case 1:
                                        startTime.setYear();
                                        break;
                                    case 2:
                                        startTime.setMonth();
                                        break;
                                    case 3:
                                        startTime.setDay();
                                        break;
                                    case 4:
                                        startTime.setHour();
                                        break;
                                    case 5:
                                        startTime.setMinute();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        case 3:
                            try {
                                switch (i) {
                                    case 1:
                                        endTime.setYear(Integer.parseInt(tokens[i]));
                                        break;
                                    case 2:
                                        endTime.setMonth(Integer.parseInt(tokens[i]));
                                        break;
                                    case 3:
                                        endTime.setDay(Integer.parseInt(tokens[i]));
                                        break;
                                    case 4:
                                        endTime.setHour(Integer.parseInt(tokens[i]));
                                        break;
                                    case 5:
                                        endTime.setMinute(Integer.parseInt(tokens[i]));
                                        break;
                                    default:
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid time component detected. It has been replaced with 1.");
                                System.out.println("Error type: " + e.getMessage());

                                switch (i) {
                                    case 1:
                                        endTime.setYear();
                                        break;
                                    case 2:
                                        endTime.setMonth();
                                        break;
                                    case 3:
                                        endTime.setDay();
                                        break;
                                    case 4:
                                        endTime.setHour();
                                        break;
                                    case 5:
                                        endTime.setMinute();
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        case 4:
                            comment = comment + tokens[i] + " ";
                            break;
                        case 5:
                            location = location + tokens[i] + " ";
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        inputStream.close();
    }

    /**
     * Writes the activity arrayList to a file (same format as input file)
     */
    public void fileWrite(String filePath) {
        Iterator<Activity> i;
        i = activityList.iterator();
        Activity currentActivity = null;
        OtherActivity castCurrent = null;
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(new FileOutputStream(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path. Make sure that the file path is correct.");
            System.out.println("File write aborted due to invald path.");
            System.exit(0);
        }

        while (i.hasNext()) {
            currentActivity = i.next();

            if (currentActivity.getClass() == HomeActivity.class) {
                outputStream.print("type = home\n");
            } else if (currentActivity.getClass() == SchoolActivity.class) {
                outputStream.print("type = school\n");
            } else if (currentActivity.getClass() == OtherActivity.class) {
                outputStream.print("type = other\n");
            } else {
                continue;
            }

            outputStream.print("title = " + currentActivity.getTitle() + "\n");
            outputStream.print("start = " + currentActivity.getStartingTime().getYear() + ", "
                    + currentActivity.getStartingTime().getMonth() + ", "
                    + currentActivity.getStartingTime().getDay() + ", "
                    + currentActivity.getStartingTime().getHour() + ", "
                    + currentActivity.getStartingTime().getMinute() + "\n");

            outputStream.print("end = " + currentActivity.getEndingTime().getYear() + ", "
                    + currentActivity.getEndingTime().getMonth() + ", "
                    + currentActivity.getEndingTime().getDay() + ", "
                    + currentActivity.getEndingTime().getHour() + ", "
                    + currentActivity.getEndingTime().getMinute() + "\n");

            outputStream.print("comment = " + currentActivity.getComment() + "\n");

            if (currentActivity.getClass() == OtherActivity.class) {
                castCurrent = (OtherActivity) currentActivity;
                outputStream.print("location = " + castCurrent.getLocation() + "\n");
            }

            outputStream.print("/\n");
        }

        outputStream.close();
    }

    public void saveData(String[] arguments) {
        if (arguments.length == 1) {
            this.fileWrite(arguments[0]);
        } else if (arguments.length == 2) {
            this.fileWrite(arguments[1]);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DayPlanner planner = new DayPlanner();
        DayPlannerWindow mainWindow;
        String command = "";
        if (args.length == 1) {
            planner.fileParse(args[0]);
        } else if (args.length == 2) {
            planner.fileParse(args[0]);
        } else {
            System.out.println("You must pass a data file path as an argument.");
            System.exit(0);
        }

        mainWindow = new DayPlannerWindow(planner, args);

    }
}
