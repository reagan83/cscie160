package cscie160.hw3;

/**
 * Passenger class of the Elevator simulation.
 * 
 * @author Reagan Williams
 * @version 1.3 (hw3)
 * @since 2011-10-05
 */
public class Passenger
{
    /**
     * Default Passenger constructor
     *
     * Initializes floor data members to 0. 
     */
    public Passenger()
    {
        currentFloor = 0;
        destinationFloor = 0;
    }

    /**
     * Passenger constructor
     *
     * @param cFloor Current Floor
     * @param dFloor Destination Floor
     */
    public Passenger(int cFloor, int dFloor)
    {
        currentFloor = cFloor;
        destinationFloor = dFloor;
    }

    /**
     * Perform passenger operations when arriving at a floor
     *
     * This method sets the current floor of the passenger to the original
     * destination floor of the passenger.
     *
     */
    public void arrive()
    {
        setCurrentFloor(destinationFloor);
    }

    /**
     * Get the intended direction of the passenger
     *
     * @return direction of the passenger (1 = up, -1 = down)
     */
    public int getDirection()
    {
        if (getCurrentFloor() > getDestinationFloor())
            return -1;
        
        return 1;
    }

    /**
     * Get the floor of the passenger is waiting on
     *
     * @return Current floor number
     */
    public int getCurrentFloor()
    {
        return currentFloor;
    }

    /**
     * Get the destination floor of the passenger
     *
     * @return Destination floor number
     */
    public int getDestinationFloor()
    {
        return destinationFloor;
    }

    /**
     * Sets the passenger's current floor
     */
    public void setCurrentFloor(int cFloor)
    {
        currentFloor = cFloor;
    }

    /**
     * Sets the passenger's destination floor
     */
    public void setDestinationFloor(int dFloor)
    {
        destinationFloor = dFloor;
    }

    private int currentFloor;
    private int destinationFloor;
}