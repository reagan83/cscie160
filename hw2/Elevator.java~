package cscie160.hw2;

/**
 * Elevator simulation with passenger loading/unloading and building floor limits.
 * 
 * @author Reagan Williams
 * @version 1.2 (hw2)
 * @since 2011-09-27
 */
public class Elevator
{
    /**
     * main
     * 
     * This code creates an Elevator object, boards passengers to the Elevator hw2 spec, then has the
     * Elevator object sweep (move) to all floors in the building letting off passengers at their
     * destinations and printing an Elevator status along the way.  It also supports the Floor class
     * which allows for registering passenger boarding requests.
     *
     * @param args No parameters are used here.
     *
     */
    public static void main(String[] args)
    {
        Elevator e = new Elevator();

        try
        {
            e.boardPassenger(2);
            e.boardPassenger(2);
            e.boardPassenger(7);
        }
        catch (CapacityReachedException ex)
        {
            System.out.println("Capacity reached: " + ex);
        }
       

        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);
        e.registerRequest(5);

        e.registerRequest(3);
        e.registerRequest(2);
        e.registerRequest(4);
        e.registerRequest(6);


        System.out.println(e.toString());

        // This loop moves the elevator up and down 8 floors and lets off passengers where appropriate.
        // The Elevator class reverses the direction when it has reached the top or bottom, so the upper bound (8)
        // on this loop can be increased without concern for the # of floors in the building.
        for (int i = 0; i < 25; i++)
        {
            e.move();

            if (e.isDestinationFloor())
            {
                e.stop();
            }
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

        f = new Floor[BUILDING_FLOORS + 1];
        for (int i = 0; i < f.length; i++)
        {
            f[i] = new Floor();
        }

        destinationFloor = new boolean[BUILDING_FLOORS + 1];
        for (int i = 0; i < destinationFloor.length; i++)
        {
            destinationFloor[i] = false;
        }

        destinationFloorPassengers = new int[BUILDING_FLOORS + 1];
        for (int i = 0; i < destinationFloorPassengers.length; i++)
        {
            destinationFloorPassengers[i] = 0;
        }

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
        if (currentFloor <= 1 || currentFloor >= BUILDING_FLOORS)
        {
            doSwitchDirection();
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
     * this floor. Finally, it prints a status message about stopping on a floor, then calls the overloaded
     * toString() method to print the Elevator status.
     *
     */
    public void stop()
    {
        System.out.println();
        System.out.println("Stopping on floor " + currentFloor);
        System.out.println(this.toString());

        f[currentFloor].unloadPassengers(this);
    }

    public void unloadCurrentFloorPassengers()
    {
        if (destinationFloorPassengers[currentFloor] > 0)
        {
            System.out.println("Unloading " + destinationFloorPassengers[currentFloor] + " passengers.");
        }
        else
        {
            System.out.println("No passengers to unload.");
        }
        destinationFloorPassengers[currentFloor] = 0;
        destinationFloor[currentFloor] = false;
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
     * Returns the count of passengers
     *
     * This method iterates through all of the buildling floors and totals the passengers destined
     * for each floor.  The method returns this count.
     *
     * @return passenger count on Elevator
     */
    public int getPassengerCount()
    {
        int c = 0;

        for (int i = 0; i < destinationFloorPassengers.length; i++)
        {
            c += destinationFloorPassengers[i];
        }

        return c;
    }

    /**
     * Boards a passenger on the Elevator.
     *
     * This method performs the action of boarding a passenger on the Elevator.  This requires setting
     * the floor passed as a parameter as a destination floor.  This is done regardless if it was previously
     * a destination floor or not.  It then increments the number of passengers destined for that floor by 1.
     *
     * @param floor Floor number the passenger wishes to travel to.
     *
     */ 
    public boolean boardPassenger(int floor) throws CapacityReachedException 
    {
        if (getPassengerCount() >= PASSENGER_CAPACITY)
        {
            throw new CapacityReachedException("Elevator capacity of " + PASSENGER_CAPACITY + " has been reached.");
            return false;
        }
        
        destinationFloor[floor] = true;
        destinationFloorPassengers[floor]++;

        return true;
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
        return "Currently " + getPassengerCount() + " passengers onboard\nCurrent Floor: " + currentFloor;
    }

    /**
     * Returns the current floor of the Elevator object
     *
     * This method returns the data member of the Elevator current floor value
     *
     * @return current floor integer value
     */
    public int getCurrentFloor()
    {
        return currentFloor;
    }

    /**
     * Registers a request with the floor to add a passenger to the waiting queue.
     *
     * This method calls the floor object to increment the passenger waiting queue for that floor.  It also 
     * marks the floor to be stopped at on the Elevator data member destinationFloor. 
     *
     * @param requestFloor int set the floor to init a passenger waiting to load
     */
    public void registerRequest(int requestFloor)
    {
        f[requestFloor].addPassengerBoardRequest();
        destinationFloor[requestFloor] = true;
    }

    private int currentFloor;
    private int currentDirection;

    /**
     * Array of Floor objects
     *
     * Note: array indexes represent true floor values. For example, floor 1 will be array member [1].
     * Thus, member [0] will go unused.
     */
    private Floor[] f;

    /**
     * Array to store whether a floor is a destination or not.
     *
     * Note: array indexes represent true floor values. For example, floor 1 will be array member [1].
     * Thus, the member [0] will go unused.
     */
    private boolean[] destinationFloor;

    /**
     * Array to store the passenger count at which floor.
     *
     * Note: array indexes represent true floor values. For example, floor 1 will be array member [1].
     * Thus, the member [0] will go unused.
     */
    private int[] destinationFloorPassengers;

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
