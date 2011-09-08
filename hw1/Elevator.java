package cscie160.hw1;

public class Elevator
{
    public static void main(String[] args)
    {
        Elevator e = new Elevator();
       
        e.boardPassenger(2);
        e.boardPassenger(3);
        e.boardPassenger(5);

        System.out.println(e.toString());

        for (int i = 0; i < 12; i++)
        {
            e.move();
        }
    }

    public Elevator()
    {
        currentFloor = 1;
        currentDirection = DIRECTION_UP;

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

    public void move()
    {
        System.out.println("Current: " + currentFloor);


        if (currentFloor <= 1 || currentFloor >= BUILDING_FLOORS)
        {
            doChangeDirection();
        }

        currentFloor += currentDirection;

        if (isDestinationFloor() == true)
        {
            stop();
        }

    }

    public void stop()
    {
        destinationFloorPassengers[currentFloor] = 0;
        destinationFloor[currentFloor] = false;

        System.out.println();
        System.out.println("Stopping on floor " + currentFloor);
        System.out.println(this.toString());
    }

    public void doChangeDirection()
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

    public boolean isDestinationFloor()
    {
        return (destinationFloor[currentFloor] == true);
    }

    public int getPassengerCount()
    {
        int c = 0;

        for (int i = 0; i < destinationFloorPassengers.length; i++)
        {
            c += destinationFloorPassengers[i];
        }

        return c;
    }
	
    public void boardPassenger(int floor)
    {
        try
        {
            destinationFloor[floor] = true;
            destinationFloorPassengers[floor]++;
        }
        catch (Exception e)
        {
        }
    }

    /**
       Overloaded toString method to return Elevator floor and passenger count
    */
    public String toString()
    {
        return "Currently " + getPassengerCount() + " passengers onboard\nCurrent Floor: " + currentFloor;
    }

    private int currentFloor;
    private int currentDirection;
    private boolean[] destinationFloor;
    private int[] destinationFloorPassengers;

    // constant values
    private static final int BUILDING_FLOORS = 7;
    private static final int PASSENGER_CAPACITY = 10;

    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = -1;
}
