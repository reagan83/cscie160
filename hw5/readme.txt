Reagan Williams
Homework #5
rwilliams@fas.harvard.edu
10/25/2011

This solution performs simple ATM functions through a client/server process over an established TCP protocol.

To execute this solution, use two terminals and execute:

java -classpath HW4.jar cscie160.hw4.Server 7777
java -classpath HW4.jar cscie160.hw4.Client localhost 7777

The output should show an amount being deposited, followed by a few withdraws including an overdrawn balance exception.

I've also included junit tests in the cscie160/hw4/junit/ folder.  These files need to be moved to the root hw4/ folder for execution, but I moved them away to keep things tidy.

I've also included an ant build.xml file that can be found in the cscie160/hw4/ folder.  This file accomplishes the requirements of the homework, without the extra credit (which I hope to submit soon).

All of the JavaDoc comments contained in the cscie160/hw4/doc/ folder.


