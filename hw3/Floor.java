package cscie160.hw3;

import java.util.Stack;
import java.util.Random; // might be able to remove later!

/**
 * Floor class for the Elevator simulator.
 *
 * @author Reagan Williams
 * @version 1.3 (hw3)
 * @since 2011-09-27
 *
 */
public class Floor
{
    /**
     * main
     *
     * This code creates a sample Floor object and creates 2 add passengers
     * and prints the status of the Floor object.
     *
     * @param args No parameters are used here.
     *
     */
	public static void main(String[] args)
	{
		Floor f = new Floor();

        System.out.println(f);

	}

    /**
     * Floor constructor
     *
     * This constructor sets the passengers waiting count data member.
     *
     */
	public Floor()
	{
        passengersResident = new Stack<Passenger>();
        passengersQueuedUp = new Stack<Passenger>();
        passengersQueuedDown = new Stack<Passenger>();
    }

    /**
     * Handle passenger unloading/loading
     *
     * This method unloads the passengers from the Elevator and loads new
     * waiting passengers.
     *
     * @param e Elevator object
     */
	public boolean loadUnloadPassengers(Elevator e)
	{
        // call Elevator unload passengers method
        int c = 0;

        // consider rewriting this!
        while (true)
        {
            Passenger p = e.unloadCurrentFloorPassengers();

            if (p == null)
                break;

            p.setCurrentFloor(e.getCurrentFloor());

            passengersResident.push(p);
            c++;
        }

        Stack<Passenger> bp;
        if (e.getCurrentDirection() > 0) // up
        {
            bp = passengersQueuedUp;
        }
        else // down
        {
            bp = passengersQueuedDown;
        }

        // load passengers waiting to board
        if (bp.size() > 0)
        {
            boolean boardResult = true;

            while (boardResult && bp.size() > 0)
            {
                try
                {
                    e.boardPassenger(bp.pop());
                }
                catch (CapacityReachedException ex)
                {
                    System.out.println("Elevator capacity reached: " + ex);
                    boardResult = false;
                }
            }
        }

        if (getPassengersQueuedUpCount() > 0 || getPassengersQueuedDownCount() > 0)
        {
            return false;
        }

        return true;
	}

    /**
     * Add passenger boarding request
     *
     * This method increments the Floor data member counter that tracks the number
     * of passengers waiting to board.
     *
     */
    public void addPassengerBoardRequest(Passenger p)
    {
        if (p.getDestinationFloor() == p.getCurrentFloor())
        {
            passengersResident.push(p);
        }
        else if (p.getCurrentFloor() < p.getDestinationFloor())
        {
            passengersQueuedUp.push(p);
        }
        else
        {
            passengersQueuedDown.push(p);
        }
    }

    /**
     * Get passenger count waiting on floor
     *
     * This method returns the Floor data member value that tracks the passengers
     * waiting to board from a floor.
     *
     * @return count of passengers waiting to board
     */
    public int getPassengersQueuedUpCount()
    {
        return passengersQueuedUp.size();
    }

    public int getPassengersQueuedDownCount()
    {
        return passengersQueuedDown.size();
    }

    public int getPassengersResidentCount()
    {
        return passengersResident.size();
    }

   /**
     * Returns the status of the Elevator object
     * 
     * This method is an overloaded toString method to return floor passenger waiting count.
     * 
     * @return Status containing passenger waiting count
     */
	public String toString()
    {
        return getPassengersResidentCount() + "\t\t" + getPassengersQueuedUpCount() + "\t\t" + getPassengersQueuedDownCount();
    }

    private Stack<Passenger> passengersResident;
    private Stack<Passenger> passengersQueuedUp;
    private Stack<Passenger> passengersQueuedDown;
}
