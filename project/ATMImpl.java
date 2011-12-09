package cscie160.hw5;

import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * ATMImpl RMI ready class that interfaces with ATM
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
    public static HashMap a;

    /**
     * ATM Impl constructor that creates 3 account objects with varying initial balances.
     */
    public ATMImpl() throws java.rmi.RemoteException
    {
        a = new HashMap();

        a.put(1, new Account(1));
        Account act = (Account)a.get(1);
        act.setBalance(0);

        a.put(2, new Account(2));

        act = (Account)a.get(2);
        act.setBalance(100);

        a.put(3, new Account(3));

        act = (Account)a.get(3);
        act.setBalance(500);
    }

    /**
     * Deposit amount into account
     *
     * @param accountNumber Account number
     * @param amount Amount to deposit
     */
    public void deposit(int accountNumber, float amount) throws java.rmi.RemoteException
    {
        Account act = (Account)a.get(accountNumber);

        act.setBalance(act.getBalance() + amount);
    }

    /**
     * Withdraw amount from account
     *
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     */
    public void withdraw(int accountNumber, float amount) throws java.rmi.RemoteException
    {
        Account act = (Account)a.get(accountNumber);

        if (act.getBalance() >= amount)
            act.setBalance(act.getBalance() - amount);
//        else
//            throw new ATMException("Insufficient funds.");
    }

    /**
     * Return the account balance
     *
     * @param accountNumber Account number
     * @return Account balance
     */
    public Float getBalance(int accountNumber) throws java.rmi.RemoteException
    {
        Account act = (Account)a.get(accountNumber);
        return (Float)act.getBalance();
    }
}