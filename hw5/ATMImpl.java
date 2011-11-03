package cscie160.hw5;

import java.rmi;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * ATMImpl server that interfaces with ATM
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
    HashMap<int, Account> a;

    public static void main(String[] args)
    {
        try
        {
            ATMImpl obj = new ATMImpl();
            Naming.rebind("//localhost/atmimpl", obj);
            System.out.println("ATMImpl bound in registry.");
        }
        catch (Exception e)
        {
            System.out.println("ATMImp error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ATM Impl constructor that creates 3 account objects with varying initial balances.
     */
    public ATMImpl() throws RemoteException
    {
        a = new HashMap();

        a.put(1, new Account(1));
        a.get(1).setBalance(0);

        a.put(2, new Account(2));
        a.get(2).setBalance(500);

        a.put(3, new Account(3));
        a.get(3).setBalance(1000));
    }
	
    /**
     * Deposit amount into account
     *
     * @param accountNumber Account number
     * @param amount Amount to deposit
     */
    public void deposit(int accountNumber, float amount) throws ATMException, RemoteException
    {
        a.get(accountNumber).setBalance(a.getBalance() + amount);
    }

    /**
     * Withdraw amount from account
     *
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     */
    public void withdraw(int accountNumber, float amount) throws ATMException, RemoteException
    {
        if (a.get(accountNumber).getBalance() >= amount)
            a.get(accountNumber).setBalance(a.get(accountNumber).getBalance() - amount);
        else
            throw new ATMException("Insufficient funds.");
    }

    /**
     * Return the account balance
     *
     * @param accountNumber Account number
     * @return Account balance
     */
    public Float getBalance(int accountNumber) throws ATMException, RemoteException
    {
        return a.get(accountNumber).getBalance();
    }
}