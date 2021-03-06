package cscie160.hw2;

/**
 * Floor class for the Elevator simulator.
 *
 * @author Reagan Williams
 * @version 1.2 (hw2)
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

        f.addPassengerBoardRequest();
        f.addPassengerBoardRequest();

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
		passengersWaitingToBoard = 0;
	}

    /**
     * Handle passenger unloading/loading
     *
     * This method unloads the passengers from the Elevator and loads new
     * waiting passengers.
     *
     * @param e Elevator object
     */
	public void unloadPassengers(Elevator e)
	{
        // call Elevator unload passengers method
        e.unloadCurrentFloorPassengers();

        System.out.println("Currently " + passengersWaitingToBoard + " passengers waiting to board.");

        // load passengers waiting to board
        boolean boardResult = true;

        while (boardResult && passengersWaitingToBoard > 0)
        {
            try
            {
                e.boardPassenger(1);
                passengersWaitingToBoard--;
            }
            catch (CapacityReachedException ex)
            {
                System.out.println("Elevator capacity reached: " + ex);
                boardResult = false;
            }

        }

        System.out.println("Finished looping - now " + passengersWaitingToBoard + " waiting to board");
	}

    /**
     * Add passenger boarding request
     *
     * This method increments the Floor data member counter that tracks the number
     * of passengers waiting to board.
     *
     */
    public void addPassengerBoardRequest()
    {
        passengersWaitingToBoard++;
    }

    /**
     * Get passenger count waiting to board
     *
     * This method returns the Floor data member value that tracks the passengers
     * waiting to board from a floor.
     *
     * @return count of passengers waiting to board
     */
	public int getPassengersWaitingToBoardCount()
	{
		return passengersWaitingToBoard;
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
        return "Currently " + passengersWaitingToBoard + " passengers waiting to board the elevator.";
    }

	private int passengersWaitingToBoard;
}