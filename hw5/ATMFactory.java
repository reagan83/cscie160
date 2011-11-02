package cscie160.hw5;

import java.rmi.Remote;

/**
 * ATM Factory interface with RMI support.
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
 */
public interface ATMFactory extends java.rmi.Remote
{
	public ATM getATM() throws java.rmi.RemoteException;
}