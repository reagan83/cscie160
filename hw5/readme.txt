Reagan Williams
Homework #5
rwilliams@fas.harvard.edu
11/10/2011

This solution implements an ATM client/server model using the Java RMI.  The ATM supports ATMFactory objects along with handling multiple Accounts.

To execute this solution, use three terminals and execute:

rmiregistry -J-cp -J.
java -classpath HW5.jar cscie160.hw5.Server
java -classpath HW5.jar cscie160.hw5.Client

The output will show 3 accounts being created with initial balances.  Afterwards, various methods are invoked to add/remove funds and the end-result matches what is expected as described in hw5.  The Client class is exactly as described from the hw5 assignment (with tab fixes)!

All of the JavaDoc comments contained in the cscie160/hw5/doc/ folder.


