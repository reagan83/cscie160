package cscie160.project;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ATM Listener interface
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-08
 */

public interface ATMListener extends java.rmi.Remote
{
    public void processNotification(TransactionNotification tn) throws java.rmi.RemoteException;
}