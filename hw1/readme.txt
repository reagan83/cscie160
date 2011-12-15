Reagan Williams
Homework #1
rwilliams [ at ] fas.harvard.edu
9/14/2011

This solution is a basic Elevator simulation program written in Java.  This program is created using a public Elevator class that can be extended by other developers in other super classes (a Building class perhaps?), or used in statistical gathering scenarios which I hope to implement in future exercises.

To execute this solution, use:

java -classpath HW1.jar cscie160.hw1.Elevator

And your output will match the requirements in the homework definition.  If you want to check that I actually created the Elevator class and didn't use a series of println()'s to match the homework output, then open your favorite text editor and point it to Elevator.java.

One major thing to note is that I'm unhappy with is my concession on handling the arrays of floors.  I ended up creating the floor arrays (1 for passenger counts per floor, 1 for if a floor is a destination or not) with 1 extra "floor" because I think that improves the readability of the code, although it creates an unused array member. For example, I did not want currentFloor[0] to actually be a "real life" floor 1.

All of the JavaDoc comments contained in the doc/ folder.


