Reagan Williams
Final Project
rwilliams [ at ] fas.harvard.edu
12/14/2011

This solution is an RMI-based an Bank/ATM simulator.

To execute this solution, use four terminals and execute:

rmiregistry -J-cp -Jproject.jar
java -classpath project.jar cscie160.project.BankServer
java -classpath project.jar cscie160.project.ATMServer
java -classpath project.jar cscie160.project.Client

The output will show a series of ATM tests, some of which will fail as expected and others will succeed.  The
primary focus for these tests demonstrates how the Client performs transactions against a remote ATM object and
receives remote transaction notifications from that object.  As part of the system design I made use of the ATMException
class to throw exceptions for insufficient funds, invalid authorization attempts, etc.  I'm unhappy with my
implementation of the security permissions using 3 separate Vector objects - given more time I might rewrite that portion
of the Security class to use a single object.  My main takeaway from this project is the new knowledge I have on 
Java Serialization.

All of the JavaDoc comments contained in the cscie160/project/doc/ folder.


