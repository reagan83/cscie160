package cscie160.project;

import java.rmi.Remote;

/**
 * ATM interface with RMI support.
 * 
 * @author Reagan Williams
 * @version 1.7 (hw7)
 * @since 2011-12-02
 */
public interface ATM extends java.rmi.Remote
{
	public void deposit(AccountInfo ai, float amount) throws java.rmi.RemoteException;
	public void withdraw(AccountInfo ai, float amount) throws java.rmi.RemoteException;
	public void transfer(AccountInfo to, AccountInfo from, float amount) throws java.rmi.RemoteException;
	public Float getBalance(AccountInfo ai) throws java.rmi.RemoteException;
}
