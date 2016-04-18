/**
 * OtherActivity class A class for storing all non-school or home activities
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

public class OtherActivity extends Activity {

    private String location;    // location of the activity

    /**
     * Create an other activity with all the required fields
     */
    public OtherActivity(String title, Time startingTime, Time endingTime, String comment, String location) throws InvalidTimeException {

        super(title, startingTime, endingTime, comment);
        this.location = location;

    }

    /**
     * Set a new value for location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the value of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Check for equality of two other activities
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this.getClass() != other.getClass()) {
            return false;
        } else {
            return title.equals(((OtherActivity) other).title)
                    && startingTime.equals(((OtherActivity) other).startingTime)
                    && endingTime.equals(((OtherActivity) other).endingTime)
                    && location.equals(((OtherActivity) other).location)
                    && comment.equals(((OtherActivity) other).comment);
        }
    }

    /**
     * Show the content of an other activity in a string
     */
    public String toString() {
        return title + ": " + startingTime + " to " + endingTime + ", " + location + ", " + comment;
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
        OtherActivity activity = null;
        try {
            activity = new OtherActivity("Lunch", startingTime, endingTime, "", "Tim Horton");
        } catch (Exception e) {
            System.out.println("Error!");
            System.exit(0);
        }
        System.out.println("Other Activity: " + activity);
    }
}
