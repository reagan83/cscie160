package cscie160.project;

import java.rmi.server.UnicastRemoteObject;

/**
 * ATM Factory Implementation that interfaces with ATM Factory
 * 
 * @author Reagan Williams
 * @version 1.7 (hw7)
 * @since 2011-12-07
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