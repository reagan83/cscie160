package cscie160.hw3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Elevator simulation with passenger loading/unloading and building floor limits.
 * 
 * @author Reagan Williams
 * @version 1.3 (hw3)
 * @since 2011-10-03
 */
public class Elevator
{
    /**
     * main
     * 
     * This code creates an Elevator object, boards passengers to the Elevator hw3 spec, then has the
     * Elevator object sweep (move) to all floors in the building letting off passengers at their
     * destinations and printing an Elevator status along the way.  It also supports the Floor and Passenger classes
     * which allows for registering passenger boarding requests.
     *
     * @param args No parameters are used here.
     *
     */
    public static void main(String[] args)
    {
        Elevator e = new Elevator();


        // Setup random passengers
        Random r = new Random();
        int passengerCount = r.nextInt(50);

        for (int i = 0; i < passengerCount; i++)
        {
            // add 1 to random number to prevent any passengerFloors on floor "0"
            int passengerFloor = r.nextInt(BUILDING_FLOORS) + 1;
            int passengerDestination = r.nextInt(BUILDING_FLOORS) + 1;

            Passenger p = new Passenger(passengerFloor, passengerDestination);

            e.registerRequest(p);
        }

        // Perform elevator simulation
        for (int i = 0; i < 25; i++)
        {
            if (e.isDestinationFloor())
            {
                e.stop();
            }

            e.clearScreen();
            e.printElevatorStatus();
            e.printFloorStatus();

            e.move();

            System.out.println();
            System.out.println("Press [enter] to continue...");
            new Scanner(System.in).nextLine();
        }
    }

    /**
     * Elevator constructor
     *
     * This constructor sets the starting floor and direction.
     * It is also responsible for initializing state variables to track Floor objects and Passengers.
     * 
     */
    public Elevator()
    {
        currentFloor = 1;
        currentDirection = DIRECTION_UP;
        elevatorStatus = "Moving";

        passenger = new ArrayList<Passenger>();

        f = new Floor[BUILDING_FLOORS + 1];
        for (int i = 0; i < f.length; i++)
        {
            f[i] = new Floor();
        }

        destinationFloor = new boolean[BUILDING_FLOORS + 1];

        // create Alternate "destination" array to store destinations for the opposite direction of the elevator
        destinationFloorAlternate = new boolean[BUILDING_FLOORS + 1];

        for (int i = 0; i < destinationFloor.length; i++)
        {
            destinationFloor[i] = false;
            destinationFloorAlternate[i] = false;
        }
    }

    /**
     * This method prints empty lines to the output to "clear" the screen.
     *
     */
    public static void clearScreen()
    {

        for (int i = 0; i < 1000; i++)
        {
            System.out.println();
        }
    }

    /**
     * This method prints the status of the Elevator.
     *
     * This envokes the toString() method of the Elevator class and adds a little style.  In addition, it also
     * prints the status of each of the Passenger slots on the elevator, with the passenger's destination floor.
     *
     */
    public void printElevatorStatus()
    {
        System.out.println("====================");
        System.out.println(this);

        System.out.println("Passenger\tDestination");

        for (int i = 0; i < PASSENGER_CAPACITY; i++)
        {
            if (i >= passenger.size())
                System.out.println(i + "\t\tn/a");
            else
                System.out.println(i + "\t\t"+ passenger.get(i).getDestinationFloor());
        }

        System.out.println("====================");
    }

    /**
     * This method prints the status of each floor.
     *
     * This envokes the toString() method of the Floor class and adds a little style.  In addition, it also
     * will mark the floor # with a * if the elevator is expected to stop in that direction.  Note: there are two
     * directions (up/down), and two separate arrays to tell the elevator when to stop.
     *
     */
    public void printFloorStatus()
    {
        System.out.println("--------------------");
        System.out.println("Floor\tResident\tQueued Up\tQueued Down");
        for (int i = 1; i < f.length; i++)
        {
            System.out.println(i + (destinationFloor[i] == true ? "*" : "") + "\t" + f[i]);
        }
        System.out.println("--------------------");
    }

    /**
     * Registers a request with the floor to add a passenger to the queue.
     *
     * This method calls the floor object to increment the passenger waiting queue for that floor.  It also 
     * marks the floor to be stopped at on the Elevator data member destinationFloor.
     *
     * @param p Passenger Pass passenger object to init passenger on floor
     */
    public void registerRequest(Passenger p)
    {
        f[p.getCurrentFloor()].addPassengerBoardRequest(p);

        // check if passenger wants to remain on Floor or will move
        if (p.getCurrentFloor() != p.getDestinationFloor())
        {
            // set elevator's destination (stop) to passenger's current floor for pickup
            if (p.getDirection() == this.getCurrentDirection())
            {
                System.out.println("p.getDirection: " + p.getDirection() + "=" + this.getCurrentDirection());
                destinationFloor[p.getCurrentFloor()] = true;
            }
            else
            {
                System.out.println("p.Alternate: " + p.getDirection() + "!=" + this.getCurrentDirection());
                destinationFloorAlternate[p.getCurrentFloor()] = true;
            }
        }
    }

    /**
     * Boards a passenger on the Elevator.
     *
     * This method performs the action of boarding a passenger on the Elevator.  This requires setting
     * the floor passed as a parameter as a destination floor.  This is done regardless if it was previously
     * a destination floor or not.  It then increments the number of passengers destined for that floor by 1.
     *
     * @param p Passenger object that includes current floor, destination floor.
     * @throws CapactiyReachedException when elevator capacity has been reached.
     *
     */ 
    public void boardPassenger(Passenger p) throws CapacityReachedException 
    {
        if (getPassengerCount() >= PASSENGER_CAPACITY)
        {
            throw new CapacityReachedException("[Elevator] Elevator capacity of " + PASSENGER_CAPACITY + " has been reached.");
        }

        passenger.add(p);

        destinationFloor[p.getDestinationFloor()] = true;
    }

    /**
     * Unloads a passenger to the current floor.
     *
     * This method iterates through the Elevator passengers, and unloads the first passenger it finds 
     * destined for the current floor.  If a passenger is found, it removes that passenger from the Elevator
     * ArrayList, and returns that object to the caller.  If no passenger is found, null is returned.
     *
     * @return Passenger unloaded from Elevator.  Null if no passengers are left to deboard.
     *
     */
    public Passenger unloadCurrentFloorPassengers()
    {
        for (Iterator<Passenger> it = passenger.iterator(); it.hasNext();)
        {
            Passenger p = it.next();
            if (p.getDestinationFloor() == this.getCurrentFloor())
            {
                it.remove();
                return p;
            }
        }

        return null;
    }

    /**
     * Move the Elevator through the floors.
     * 
     * This method moves the Elevator through the floors in the building.  It performs bounds checking
     * by switching the direction if the Elevator has reached the top or the bottom of the building. In addition,
     * this method also increments the currentFloor variable by a value of +1 or -1, depending on the 
     * currentDirection variable of the Elevator.
     * 
     */
    public void move()
    {
        elevatorStatus = "Moving";

        if ( (currentFloor <= 1 && getCurrentDirection() < 0) || (currentFloor >= BUILDING_FLOORS && getCurrentDirection() > 0) )
        {
            doSwitchDirection();

            if (destinationFloor[currentFloor] == true)
                stop();
        }

        // I thought this declaration was clever when I thought of it.
        // Now after Prof. Sawyers lecture on 9/14/2011 about "cleverness" I feel bad about writing it.
        currentFloor += currentDirection;
    }

    /**
     * Stop the Elevator and allow passengers to exit.
     *
     * When this method is called it clears the object state variables for the current floor.  This includes 
     * setting the destination floor of the current floor to false, and clearing any queued passengers for
     * this floor. After loading, a check is made to the floor to get the Passenger count.  If it is more than 0 after loading
     * then re-mark the floor for stopping to pick up the remaining passengers. Finally, it prints a status message about
     * stopping on a floor, then calls the overloaded toString() method to print the Elevator status.
     *
     */
    public void stop()
    {
        elevatorStatus = "Stopped";

        boolean result = false;

        f[currentFloor].loadUnloadPassengers(this);

        // If floor queue in current Elevator direction still has passengers > 0 an overflow must have occurred.
        // This will reset the destinationFloor to true to pick up these passengers later.
        if ( (getCurrentDirection() > 0) && (f[currentFloor].getPassengersQueuedUpCount() > 0) )
        {
            destinationFloor[currentFloor] = true;
        }
        else if ( (getCurrentDirection() < 0) && (f[currentFloor].getPassengersQueuedDownCount() > 0) )
        {
            destinationFloor[currentFloor] = true;
        }
        else
        {
            destinationFloor[currentFloor] = false;
        }
    }

    /**
     * Switches the direction of the Elevator
     *
     * This method performs bounds checking on the Elevator's current floor and, if necessary, will switch
     * the direction.
     *
     */
    public void doSwitchDirection()
    {
        boolean[] toggleBuffer;

        toggleBuffer = destinationFloorAlternate;
        destinationFloorAlternate = destinationFloor;
        destinationFloor = toggleBuffer;

        if (currentFloor >= BUILDING_FLOORS)
        {
            currentDirection = DIRECTION_DOWN;
        }
        else if (currentFloor <= 1)
        {
            currentDirection = DIRECTION_UP;
        }
    }

    /** 
     * Validates if current floor is a destination floor.
     *
     * This method returns the true/false value of the currentFloor member of the destinationFloor
     * array to indiciate if the currentFloor is a destination floor.
     * 
     * @return true = floor is destination, false if not.
     *
     */
    public boolean isDestinationFloor()
    {
        return (destinationFloor[currentFloor] == true);
    }

    /**
     * Returns the status of the Elevator object
     * 
     * This method is an overloaded toString method to return Elevator floor and passenger count.
     * 
     * @return Status containing passenger count and current floor
    */
    public String toString()
    {
        return "[Elevator] Currently " + getPassengerCount() + " passengers onboard\n"
            + "[Elevator] Current Floor: " + getCurrentFloor() + " (" + getElevatorStatus() + ")\n"
            + "[Elevator] Direction: " + (getCurrentDirection() == 1 ? "Up" : "Down");
    }

    /**
     * Returns the count of passengers
     *
     * This method iterates through all of the buildling floors and totals the passengers destined
     * for each floor.  The method returns this count.
     *
     * @return passenger count on Elevator
     */
    public int getPassengerCount()
    {
        return passenger.size();
    }

    /**
     * Returns the status of the Elevator.
     * 
     * @return Status of elevator (Moving, Stopped)
     */
    public String getElevatorStatus()
    {
        return elevatorStatus;
    }

    /**
     * Returns the current floor of the Elevator object
     *
     * This method returns the data member of the Elevator current floor value
     *
     * @return current floor of Elevator
     */
    public int getCurrentFloor()
    {
        return currentFloor;
    }

    /**
     * Returns the direction of the Elevator.
     *
     * @return direction of elevator (1 = up, -1 = down)
     */
    public int getCurrentDirection()
    {
        return currentDirection;
    }

    private int currentFloor;
    private int currentDirection;
    private String elevatorStatus;

    /**
     * Array of Floor objects
     *
     * Note: array indexes represent true floor values. For example, floor 1 will be array member [1].
     * Thus, member [0] will go unused.
     */
    private Floor[] f;

    /**
     * ArrayList of Passenger objects to store the Elevator passengers.
     */

    private ArrayList<Passenger> passenger;

    /**
     * Array to store whether a floor is a destination or not.
     *
     * Note: array indexes represent true floor values. For example, floor 1 will be array member [1].
     * Thus, the member [0] will go unused.
     */
    private boolean[] destinationFloor;
    private boolean[] destinationFloorAlternate;

    // constant values
    private static final int BUILDING_FLOORS = 7;
    private static final int PASSENGER_CAPACITY = 10; // NEED TO CHANGE BACK TO 10!

    /**
     * The values of these direction varables aid with the floor incrementing/decrementing.
     * See the move() method for how this is used.
     */
    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = -1;
}
