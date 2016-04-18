/**
 * SchoolActivity class A class for storing school activities
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

public class SchoolActivity extends Activity {

    /**
     * Create a school activity with all the required fields
     */
    public SchoolActivity(String title, Time startingTime, Time endingTime, String comment) throws InvalidTimeException{
        super(title, startingTime, endingTime, comment);
    }

    public static void main(String[] args) {
        Time startingTime = null;
        Time endingTime = null;
        try{
            startingTime = new Time(2009, 10, 22, 12, 30);
            endingTime = new Time(2009, 10, 22, 13, 20);
        }
        catch(Exception e){
            System.out.println("Error!");
            System.exit(0);
        }
        SchoolActivity activity = null;
        try{
            activity = new SchoolActivity("Java class", startingTime, endingTime, "");
        }
        catch(Exception e){
            System.out.println("Error!");
            System.exit(0);
        }
        System.out.println("School Activity: " + activity);
    }
}
