Reagan Williams
Homework #3
rwilliams@fas.harvard.edu
10/7/2011

This solution is an enhancement to hw2 which provides a basic Elevator simulation, with Floor and Passenger objects written in Java.  This program is created using a public Elevator class that can be extended by other developers in other super classes (a Building class perhaps?), or used in statistical gathering scenarios which I hope to implement in future exercises.

To execute this solution, use:

java -classpath HW3.jar cscie160.hw3.Elevator

The output for this differs than my previous submissions.  As a debugging measure it was helpful to print the state of all objects involved at each floor, and I left this in the final version.  After executing, hitting <enter> will allow the Elevator to traverse up/down the building dropping off and picking up passengers.  At each floor you will notice the Elevator's capacity, status (Stopped/Moving), information about the destination of the currently boarded passengers, and information about the Floors in the building.  I hope this will make my submission easier to grade and judge the working nature of the solution.

I've created the Passenger objects in the Elevator main() method using a Random(50).  If your first run doesn't test the bounds of the Exception class, re-run it and it will eventually hit.

All of the JavaDoc comments contained in the doc/ folder.


