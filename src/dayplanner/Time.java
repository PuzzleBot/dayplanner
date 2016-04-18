/**
 * Time class A class for storing a full specific time
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

public class Time {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    /**
     * Create a time object with all the requirement elements
     */
    public Time(int year, int month, int day, int hour, int minute) throws InvalidTimeException {
        timeOK(year, month, day, hour, minute);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;

    }

    /**
     * Create a time object with no arguments
     */
    public Time() {
        year = 1;
        month = 1;
        day = 1;
        hour = 1;
        minute = 1;
    }
    
    /**
     * Create a time object with a single default value
     */
    public Time(int defaultVal) {
        year = defaultVal;
        month = defaultVal;
        day = defaultVal;
        hour = defaultVal;
        minute = defaultVal;
    }

    /**
     * Set a new value for year
     */
    public void setYear(int year) throws InvalidTimeException{
        timeOK(year, 1, 1, 1, 1);
        this.year = year;
    }

    /**
     * Set a new value for month
     */
    public void setMonth(int month) throws InvalidTimeException{
        timeOK(1, month, 1, 1, 1);
        this.month = month;
    }

    /**
     * Set a new value for day
     */
    public void setDay(int day) throws InvalidTimeException{
        timeOK(1, 1, day, 1, 1);
        this.day = day;
    }

    /**
     * Set a new value for hour
     */
    public void setHour(int hour) throws InvalidTimeException{
        timeOK(1, 1, 1, hour, 1);
        this.hour = hour;
    }

    /**
     * Set a new value for minute
     */
    public void setMinute(int minute) throws InvalidTimeException{
        timeOK(1, 1, 1, 1, minute);
        this.minute = minute;
    }
    
    /**
     * Set a default value for year
     */
    public void setYear(){
        this.year = 1;
    }

    /**
     * Set a default value for month
     */
    public void setMonth(){
        this.month = 1;
    }

    /**
     * Set a default value for day
     */
    public void setDay(){
        this.day = 1;
    }

    /**
     * Set a default value for hour
     */
    public void setHour(){
        this.hour = 1;
    }

    /**
     * Set a default value for minute
     */
    public void setMinute(){
        this.minute = 1;
    }

    /**
     * Check for equality with a given time object
     */
    public boolean equals(Time other) {
        if (other == null) {
            return false;
        } else {
            return year == other.year
                    && month == other.month
                    && day == other.day
                    && hour == other.hour
                    && minute == other.minute;
        }
    }

    /**
     * Compare two time objects for ordering
     */
    public int compareTo(Time other) {
        if (year < other.year) {
            return -1;
        } else if (year > other.year) {
            return 1;
        } else if (month < other.month) {
            return -1;
        } else if (month > other.month) {
            return 1;
        } else if (day < other.day) {
            return -1;
        } else if (day > other.day) {
            return 1;
        } else if (hour < other.hour) {
            return -1;
        } else if (hour > other.hour) {
            return 1;
        } else if (minute < other.minute) {
            return -1;
        } else if (minute > other.minute) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get the value for year
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the value for month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Get the value for day
     */
    public int getDay() {
        return day;
    }

    /**
     * Get the value for hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Get the value for minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Show the content of a time object in a string
     */
    public String toString() {
        return hour + ":" + minute + ", " + month + "/" + day + "/" + year;
    }

    /**
     * Validate all the fields for a time object
     */
    static public boolean timeOK(int year, int month, int day, int hour, int minute) throws InvalidTimeException {
        if (year < 0) {
            throw new InvalidTimeException("Invalid Year. (Must be greater than 0)");
        } else if ((month < 1) || (month > 12)) {
            throw new InvalidTimeException("Invalid Month. (Between 1 and 12 only)");
        } else if ((day < 1) || (day > 31)) {
            throw new InvalidTimeException("Invalid Day. (Between 1 and 31 only)");
        } else if ((hour < 0) || (hour > 23)) {
            throw new InvalidTimeException("Invalid Year. (Between 0 and 24 only)");
        } else if ((minute < 0) || (minute > 59)) {
            throw new InvalidTimeException("Invalid Year. (Between 0 and 59 only)");
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws InvalidTimeException {
        Time startingTime = null;
        if (Time.timeOK(2014, 10, 22, 12, 30)) {
            startingTime = new Time(2014, 10, 22, 12, 30);
        }

        Time endingTime = null;
        if (Time.timeOK(2014, 10, 22, 13, 20)) {
            endingTime = new Time(2014, 10, 22, 13, 20);
        }

        if (startingTime != null && endingTime != null) {
            int result = startingTime.compareTo(endingTime);
            if (result < 0) {
                System.out.println(startingTime + " precedes " + endingTime);
            } else if (result > 0) {
                System.out.println(startingTime + " follows " + endingTime);
            } else {
                System.out.println(startingTime + " equals " + endingTime);
            }
        }
    }
}
