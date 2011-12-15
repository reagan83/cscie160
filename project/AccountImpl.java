package cscie160.project;

import java.lang.Float;
import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;

/**
 * AccountImpl remote class to create an Account objects and manage balances.
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
     * Method to subtract from the account balance; will throw exception for insufficient funds.
     *
     * @param b Amount needing to be updated
     * @return new balance
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public Float subtractBalance(float b) throws java.rmi.RemoteException, ATMException
    {
        if (b > balance)
        {
            throw new ATMException("Insufficient funds!");
        }
        else
        {
            balance -= b;
        }

        return new Float(balance);
    }

    /**
     * Method to add to the account balance.
     *
     * @param b Amount needing to be updated
     * @return new balance
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public Float addBalance(float b) throws java.rmi.RemoteException
    {
        balance += b;
        return new Float(balance);
    }

    /**
     * Returns the available balance
     *
     * @return Account balance
     * @throws java.rmi.RemoteException
     */
    public Float getBalance() throws java.rmi.RemoteException
    {
        return new Float(balance);
    }

    /**
     * Returns the account number
     *
     * @return Account number
     * @throws java.rmi.RemoteException
     */
    public int getAccountNumber() throws java.rmi.RemoteException
    {
        return accountNumber;
    }
}
