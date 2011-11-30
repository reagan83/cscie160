package cscie160.project;

import java.rmi.Remote;

/**
 * ATM Factory interface with RMI support.
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public interface ATMFactory extends java.rmi.Remote
{
	public ATM getATM() throws java.rmi.RemoteException;
}