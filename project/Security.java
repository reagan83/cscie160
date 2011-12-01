package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * Security class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public interface Security extends java.rmi.Remote
{
    public void authorizeTransaction(String transactionType, AccountInfo ai) throws java.rmi.RemoteException, ATMException;
    public void authenticate(AccountInfo ai) throws java.rmi.RemoteException, ATMException;
}
