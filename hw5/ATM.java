package cscie160.hw5;

import java.rmi;

/**
 * ATM interface with RMI support.
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
 */
public interface ATM extends java.rmi.Remote
{
	public void deposit(int accountNumber, float amount) throws ATMException, RemoteException;
	public void withdraw(int accountNumber, float amount) throws ATMException, RemoteException;
	public Float getBalance(int accountNumber) throws ATMException, RemoteException;
}