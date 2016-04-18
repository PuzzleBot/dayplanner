/**
 * An exception to be thrown if a title field is left empty in the GUI
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

/**
 * An exception to be thrown if a title field is left empty in the GUI
 */
public class EmptyTitleException extends Exception {

    /**
     * Creates a new instance of <code>EmptyTitleException</code> without detail
     * message.
     */
    public EmptyTitleException() {
    }

    /**
     * Constructs an instance of <code>EmptyTitleException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyTitleException(String msg) {
        super(msg);
    }
}
