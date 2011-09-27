package cscie160.hw2;

public class Floor
{
	public static void main(String[] args)
	{
		Floor f = new Floor();

        System.out.println(f);

        f.addPassengerBoardRequest();
        f.addPassengerBoardRequest();

        System.out.println(f);
	}

	public Floor()
	{
		passengersWaitingToBoard = 0;
	}

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
//            boardResult = e.boardPassenger(1);
//            passengersWaitingToBoard--;

            // according to the reqs this actually needs to be handled/caught by an exception handler.  This is a temp hack at that.
//            if (!boardResult)
//            {
//                System.out.println("Cannot board passenger, elevator at capacity.");
//                e.registerRequest(e.getCurrentFloor());
//            }

        }

        System.out.println("Finished looping - now " + passengersWaitingToBoard + " waiting to board");
	}

    public void addPassengerBoardRequest()
    {
        passengersWaitingToBoard++;
    }

	public int getPassengersWaitingToBoardCount()
	{
		return passengersWaitingToBoard;
	}

	public String toString()
    {
        return "Currently " + passengersWaitingToBoard + " passengers waiting to board the elevator.";
    }

	private int passengersWaitingToBoard;
}