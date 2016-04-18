/**
 * Activity class A generalized class for storing activities
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

public abstract class Activity {

    protected String title;
    protected Time startingTime;
    protected Time endingTime;
    protected String comment;

    /**
     * Creates a general activity class with all required information
     */
    public Activity(String title, Time startingTime, Time endingTime, String comment) throws InvalidTimeException{
        valid(startingTime, endingTime);
        this.title = title;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.comment = comment;

    }

    /**
     * Check the validity for a potential activity
     */
    public static void valid(Time startingTime, Time endingTime) throws InvalidTimeException {
        if (startingTime == null || endingTime == null){
            throw new InvalidTimeException("Null time pointer!");
        }
        else if(startingTime.compareTo(endingTime) == 1){
            throw new InvalidTimeException("Starting time comes after ending time.");
        }
    }

    /**
     * Set a new value for title
     */
    public void setTitle(String title) {
        this.title = new String(title);
    }

    /**
     * Set a new value for starting time
     */
    public void setStartingTime(Time startingTime) {
        if (startingTime == null) {
            System.out.println("Invalid starting time");
            System.exit(0);
        } else {
            this.startingTime = startingTime;
        }
    }

    /**
     * Set a new value for ending time
     */
    public void setEndingTime(Time endingTime) {
        if (endingTime == null) {
            System.out.println("Invalid ending time");
            System.exit(0);
        }
        this.endingTime = endingTime;
    }

    /**
     * Set a new value for comment
     */
    public void setComment(String comment) {
        this.comment = new String(comment);
    }

    /**
     * Get the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the value of starting time
     */
    public Time getStartingTime() {
        return startingTime;
    }

    /**
     * Get the value of ending time
     */
    public Time getEndingTime() {
        return endingTime;
    }

    /**
     * Get the value of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Checks if two activities are of the same type and hold the same content
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this.getClass() != other.getClass()) {
            return false;
        } else {
            return title.equals(((Activity) other).title)
                    && startingTime.equals(((Activity) other).startingTime)
                    && endingTime.equals(((Activity) other).endingTime)
                    && comment.equals(((Activity) other).comment);
        }
    }

    public String toString() {
        return title + ": " + startingTime + " to " + endingTime + ", " + comment;
    }
}
