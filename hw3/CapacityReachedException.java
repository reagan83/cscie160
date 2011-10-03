package cscie160.hw3;

/**
 * Elevator capacity reached exception class.
 * 
 * @author Reagan Williams
 * @version 1.3 (hw3)
 * @since 2011-09-27
 */
public class CapacityReachedException extends Exception
{
    /**
     * Capacity reached constructor
     *
     * @param msg exception message string
     */
    public CapacityReachedException(String msg)
    {
        super(msg);
    }
}
