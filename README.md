********************************************************
# CIS 2430 (Object-oriented programming): Assignment 3
# Brandon Tan
# November 28, 2014
********************************************************

This program is the result of three assignments completed for the object-oriented
programming course at the University of Guelph.


# The goal of this project

The goal of this assignment is to implement a GUI interface for the Day Planner
application rather than a command line interface, and to implement exception
handling as a method of defensive programming.

This application is expected to run entirely on a GUI interface without the need
for command-line input or prompts, and to be able to catch bad input using
exceptions.

For the purposes of this assignment, the instructor (Dr. Fei Song) has allowed the
use of his “Assignment 1 base” found on the course moodle.


# Known limitations

- The program will not let the user know if the output file path is invalid until
  they try to quit the program. (which is when file writing happens)

- The program uses the same searching mechanism as the previous version (assignment 2)
  which I have been told outputted incorrect results. Results should still display
  in the GUI though, despite them being incorrect results.



# Additional Features (Bonus requirements)


- Clicking the red X or the Save and Quit option causes an “Are you sure” type
  prompt to appear before the program closes, and allows the user to change their
  mind and not close the program if they don’t want to.

- Error prompts from invalid input are displayed in separate windows instead
  of in the messages text area, and are closable using the OK button on the
  prompt window.

- The user is allowed to choose whether they want to save the changes they have
  made to the file or not when they close the program through the “Are you sure”
  prompt that appears. (It is no longer automatically done at the end of the
  program unless the user chooses “Yes” to save the file.)



# Input file format

The program takes an input file (file path specified on the command line) and uses
it to generate activity objects. The format is as follows:

type = activity_class_type
title = activity_title
start = year,month,day,hour,minute
end = year,month,day,hour,minute
comment = activity_comment
location = otherActivity_location
/


Blocks of text of this format represent one object. 
- activity_class_type should be replaced with either “home”, “school”, or “other”
    without quotes to denote the type of activity.
- activity_title should be replaced with the activity title. (character string)
- activity_comment should be replaced with a describing comment. (character string)
- otherActivity_location only applies to activities of type “other”. It should
    be replaced with a location. (character string)

- “year”, “month”, “day”, “hour”, and “minute” should be replaced by integers
    to form an effective calendar date and clock time. (commas between are required)
	- the “start” line denotes the starting time.
	- the “end” line denotes the ending time.

A “/“ character (without quotes) should follow the end of each activity block on a
new line to let the program know to create an activity with the given information.

An “assets” directory has been provided in the same directory as this README as
a location to place data files.



# Compilation and running

Option 1: Netbeans environment
	- Open DayPlanner.java (in the src directory which is found in the same 
	folder as this README) in the Netbeans IDE.

        - Set your “main project” to “DayPlanner”. (Run->Set Main project->DayPlanner)

	- Go to File->Project Properties(DayPlanner)->run and type your command-line
	  arguments (one to two file paths) into the field labeled “arguments”.
	  If there is one argument, it will be treated as both an input/output file.
	  If there are two, the first will be the input file, and the second will
	  be the output. 
		Netbeans, by default, will track the path starting from the
	  directory this README is located in.

	- Make sure that the input/output file paths specified exist.

	- Go to Run->Run main project.

        - Use the “Commands” menu at the top of the GUI to choose to Add or Search
	  Clicking these options will bring up text fields which allow the input
	  of required information.
		- The “Messages” text area at the bottom of the add screen
		  will show text whenever an activity has been added successfully.
		- The “Search results” text area at the bottom of the search screen
		  will display results of the search operation.

		- Whenever invalid input is found, a window prompt will appear
		  saying what is wrong with the input. Clicking “OK” or the red
		  X of the prompt window will close the prompt window.

	- Click the red X or the “Save and Exit” command under the commands menu
	  to exit the program. A prompt will appear asking if a file write operation
	  should be done if the red X is clicked, otherwise the prompt will
	  ask if the user is sure they want to exit the program.


Option 2: Terminal
	- Navigate to the directory this README is located in using the terminal.
	
	- Enter the “src” directory in this directory, then the “dayplanner”
	  directory under the “src” directory.

	- Type in “javac *.java” without quotes.

	- Navigate one directory out of the current directory (to the src directory)
	  and type in “java dayplanner/DayPlanner” without quotes, followed by
	  one to two file paths to the input/output files as arguments.
		- If there is one argument, it will be treated as both an input/output 
		file. If there are two, the first will be the input file, and the second 		will be the output. The paths should be with respect to the directory
		the program is being run from.

        - Use the “Commands” menu at the top of the GUI to choose to Add or Search
	  Clicking these options will bring up text fields which allow the input
	  of required information.
		- The “Messages” text area at the bottom of the add screen
		  will show text whenever an activity has been added successfully.
		- The “Search results” text area at the bottom of the search screen
		  will display results of the search operation.

		- Whenever invalid input is found, a window prompt will appear
		  saying what is wrong with the input. Clicking “OK” or the red
		  X of the prompt window will close the prompt window.

	- Click the red X or the “Save and Exit” command under the commands menu
	  to exit the program. A prompt will appear asking if a file write operation
	  should be done if the red X is clicked, otherwise the prompt will
	  ask if the user is sure they want to exit the program.



# Input testing and debugging

See “TestPlan.txt” in the same directory as this README.
(No formal unit tests were created for this assignment, as I had not learned
about unit testing at this point)



# Future improvements

- A delete operation can be implemented to allow the deletion of activities without
  the need to change the data file.

- The code for file reading can be simplified to reduce redundancy.

- The output file path will be checked for validity before displaying the menu for the
  first time.

        