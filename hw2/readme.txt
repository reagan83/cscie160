Reagan Williams
Homework #2
rwilliams@fas.harvard.edu
9/27/2011

This solution is an improvement to hw1 which provides an basic Elevator simulation, with Floor objects written in Java.  This program is created using a public Elevator class that can be extended by other developers in other super classes (a Building class perhaps?), or used in statistical gathering scenarios which I hope to implement in future exercises.

To execute this solution, use:

java -classpath HW2.jar cscie160.hw2.Elevator

The output you get will be a sample of normal (hw1) behavior, which pre-boards passengers and deboards them at the correct floor while 'sweeping' every floor in the building.  This version also allows for passengers to register requests to have an elevator stop at floors to pick up passengers.  In addition, it has error handling for capacity checking to ensure the elevator doesn't exceed it's capacity.  In the event of a full capacity exception, the remaining passengers will wait until the elevator drops off other passengers to free up space and return to their floor to attempt to re-board.  This simulation exercises this behavior by loading more than 10 passengers in the elevator, then registering board requests for more passengers to show the basic queuing that the Floor class handles.

As in hw1, I'm not excited about the way I implemented the Floor.unloadPassengers() method.  It doesn't seem right to have the Elevator stop() method call Floor.unloadPassengers(), then to have Floor recall Elevator to clear the passengers.  It is possible I misunderstood the requirements, or was not creative enough to come up with a more elegant solution.

All of the JavaDoc comments contained in the doc/ folder.


