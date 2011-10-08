package cscie160.hw3;

/**
 * Passenger class of the Elevator simulation
 * 
 * @author Reagan Williams
 * @version 1.3 (hw3)
 * @since 2011-10-05
 */
public class Passenger
{
    public Passenger()
    {
        currentFloor = 0;
        destinationFloor = 0;
    }

    public Passenger(int cFloor, int dFloor)
    {
        currentFloor = cFloor;
        destinationFloor = dFloor;
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }

    public int getDestinationFloor()
    {
        return destinationFloor;
    }

    public void setCurrentFloor(int cFloor)
    {
        currentFloor = cFloor;
    }

    public void setDestinationFloor(int dFloor)
    {
        destinationFloor = dFloor;
    }

    private int currentFloor;
    private int destinationFloor;
    
}