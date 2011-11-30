package cscie160.project;

import java.rmi.Remote;

/**
 * Bank interface
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public interface Bank extends java.rmi.Remote
{
    public int getAccount(int accountNumber) throws java.rmi.RemoteException;
    public void printBalances() throws java.rmi.RemoteException;
}
