/**
 * HomeActivity class A class for storing home activities
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

public class HomeActivity extends Activity {

    /**
     * Create a home activity with all the required fields
     */
    public HomeActivity(String title, Time startingTime, Time endingTime, String comment) throws InvalidTimeException {
        super(title, startingTime, endingTime, comment);
    }

    public static void main(String[] args) {
        Time startingTime = null;
        Time endingTime = null;
        try {
            startingTime = new Time(2009, 10, 22, 12, 30);
            endingTime = new Time(2009, 10, 22, 13, 20);
        } catch (Exception e) {
            System.out.println("Error!");
            System.exit(0);
        }
        HomeActivity activity = null;
        try {
            activity = new HomeActivity("Call Friends", startingTime, endingTime, "");
        } catch (Exception e) {
            System.out.println("Error!");
            System.exit(0);
        }
        System.out.println("Home Activity: " + activity);
    }

    public Activity clone() throws CloneNotSupportedException {
        HomeActivity newClone = null;
        try {
            newClone = new HomeActivity(title, startingTime, endingTime, comment);
        } catch (InvalidTimeException e) {
            newClone = new HomeActivity(title, new Time(), new Time(2), comment);
        } finally {
            return newClone;
        }
    }
}
