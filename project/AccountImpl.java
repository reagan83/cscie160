package cscie160.project;

import java.lang.Float;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;

/**
 * AccountImpl interface to create an Account objects and set/update balances.
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public class AccountImpl extends UnicastRemoteObject implements Serializable, Account
{
    private float balance;
    private final int accountNumber;

    /**
     * Performs account setup by initiating the account number and balance.
     *
     * @param n account number
     */
    public AccountImpl(int n) throws java.rmi.RemoteException
    {
        balance = (float)0.00;
        accountNumber = n;
    }

    /**
     * Provides interface to set the account balance.  Balances can be negative.
     *
     * @param b Amount needing to be updated
     * @return Newly set balance
     */
    public Float subtractBalance(float b) throws java.rmi.RemoteException
    {
        balance -= b;
        return new Float(balance);
    }

    public Float addBalance(float b) throws java.rmi.RemoteException
    {
        balance += b;
        return new Float(balance);
    }

    /**
     * Returns the available balance
     *
     * @return Account balance
     */
    public Float getBalance() throws java.rmi.RemoteException
    {
        return new Float(balance);
    }

    /**
     * Returns the account number
     *
     * @return Account number
     */
    public int getAccountNumber() throws java.rmi.RemoteException
    {
        return accountNumber;
    }
}
