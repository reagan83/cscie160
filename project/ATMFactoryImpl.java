package cscie160.hw5;

import java.rmi.server.UnicastRemoteObject;

/**
 * ATM Factory Implementation that interfaces with ATM Factory
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
 */
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory
{
    /**
     * Default constructor that creates a new ATMFactory object
     *
     */
    public ATMFactoryImpl() throws java.rmi.RemoteException
    {
        super();
    }

    /**
     * Creates and returns a new RMI-ready ATMImpl object.
     *
     * @return ATMImpl object 
     */
    public ATM getATM() throws java.rmi.RemoteException
    {
        // this should return remote reference to an ATM object?
        return new ATMImpl();
    }
}