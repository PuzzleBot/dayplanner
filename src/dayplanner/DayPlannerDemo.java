package dayplanner;

import java.util.Scanner;

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


public class DayPlannerDemo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DayPlanner planner = new DayPlanner();
        String command = "";
        if (args.length == 1) {
            planner.fileParse(args[0]);
        } else if (args.length == 2) {
            planner.fileParse(args[0]);
        } else {
            System.out.println("You must pass a data file path as an argument.");
            System.exit(0);
        }
        do {
            System.out.print("Enter add, search, or quit\n> ");
            command = input.nextLine();
            if (command.equalsIgnoreCase("add") || command.equalsIgnoreCase("a")) {
                planner.addActivity(input);
            } else if (command.equalsIgnoreCase("search") || command.equalsIgnoreCase("s")) {
                planner.searchActivities(input);
            }
        } while (!command.equalsIgnoreCase("quit") && !command.equalsIgnoreCase("q"));
        
        if(args.length == 1){
            planner.fileWrite(args[0]);
        }
        else if(args.length == 2){
            planner.fileWrite(args[1]);
        }
        
    }
}
