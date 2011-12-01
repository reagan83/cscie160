Reagan Williams
Homework #6
rwilliams@fas.harvard.edu
12/03/2011

This solution contains a threaded ATM client/server process over an established TCP protocol.

To execute this solution use two terminals and execute:

java -classpath HW6.jar cscie160.hw6.Server 7777
java -classpath HW6.jar cscie160.hw6.Client localhost 7777

The Client output should show an amount being deposited, followed by a few withdraws including an overdrawn balance exception.  The Server output will show 5 threads being spawned and 7 threaded exections as the 7 commands are being executed.

All of the JavaDoc comments contained in the cscie160/hw6/doc/ folder.


