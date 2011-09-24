package cscie160.hw2;

public class Floor
{
	public static void main(String[] args)
	{
		Floor f = new Floor();
	}

	public Floor()
	{
		passengersWaitingToBoard = 0;
	}

	public unloadPassengers(Elevator e)
	{
		
		e.boardPassengers(1);
	}

	public getPassengerWaitingToBoardCount()
	{
		return passengersWaitingToBoard;
	}

	public String toString()
    {
        return "Currently " + getPassengerWaitingToBoardCount() + " passengers waiting to board the elevator.";
    }

	private int passengersWaitingToBoard;
}