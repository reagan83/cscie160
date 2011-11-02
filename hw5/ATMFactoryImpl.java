package cscie160.hw5;

import java.rmi.Remote;
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
    private String name;

    public ATMFactoryImpl(String s) throws java.rmi.RemoteException
    {
        super();
        name = s;
    }

    public ATM getATM() throws java.rmi.RemoteException
    {
        return new ATMImpl();
    }
}