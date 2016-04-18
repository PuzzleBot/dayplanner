/**
 * An exception thrown when one of the following circumstances are found:
 * Year less than 0
 * Month not between 1 and 12 inclusive
 * Day not between 1 and 31 inclusive
 * Hour not between 0 and 23 inclusive
 * Minute not between 0 and 59 inclusive
 * Starting time comes after ending time during activity creation
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
public class InvalidTimeException extends Exception {

    /**
     * Creates a new instance of <code>InvalidTimeException</code> without
     * detail message.
     */
    public InvalidTimeException() {
    }

    /**
     * Constructs an instance of <code>InvalidTimeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidTimeException(String msg) {
        super(msg);
    }
}
