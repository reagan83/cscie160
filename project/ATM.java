package cscie160.project;

import java.rmi.Remote;

/**
 * ATM interface with RMI support.
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public interface ATM extends java.rmi.Remote
{
	public boolean deposit(AccountInfo ai, float amount) throws java.rmi.RemoteException, ATMException;
	public boolean withdraw(AccountInfo ai, float amount) throws java.rmi.RemoteException, ATMException;
	public boolean transfer(AccountInfo to, AccountInfo from, float amount) throws java.rmi.RemoteException, ATMException;
	public Float getBalance(AccountInfo ai) throws java.rmi.RemoteException, ATMException;
    public void registerListener(ATMListener a) throws java.rmi.RemoteException;
}
