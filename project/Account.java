package cscie160.project;

import java.lang.Float;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;

/**
 * Account interface to create an Account objects and set/update balances.
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public interface Account extends java.rmi.Remote
{
    public Float subtractBalance(float b) throws java.rmi.RemoteException, ATMException;
    public Float addBalance(float b) throws java.rmi.RemoteException;
    public Float getBalance() throws java.rmi.RemoteException;
    public int getAccountNumber() throws java.rmi.RemoteException;
}
