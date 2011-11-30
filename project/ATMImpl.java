package cscie160.project;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.Naming;
import java.util.HashMap;

/**
 * ATM RMI ready class that interfaces with ATM
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
    private float cashBalance;

    /**
     * ATM Impl constructor that creates 3 account objects with varying initial balances.
     */
    public ATMImpl() throws java.rmi.RemoteException
    {
        cashBalance = (float)500.00;
    }

    /**
     * Deposits amount into account
     *
     * @param accountNumber Account number
     * @param amount Amount to deposit
     */
    public void deposit(AccountInfo ai, float amount) throws java.rmi.RemoteException
    {
        // Account act = (Account)a.get(accountNumber);

        // act.setBalance(act.getBalance() + amount);
    }

    /**
     * Withdraw amount from account
     *
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     */
    public void withdraw(AccountInfo ai, float amount) throws java.rmi.RemoteException
    {
        // Account act = (Account)ai.get(accountNumber);

        // if (act.getBalance() >= amount)
        //     act.setBalance(act.getBalance() - amount);
//        else
//            throw new ATMException("Insufficient funds.");
    }

    /**
     * Transfer balance to another account
     *
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     */
    public void transfer(AccountInfo from, AccountInfo to, float amount) throws java.rmi.RemoteException
    {
//        Account act = (Account)a.get(accountNumber);

        // notify all registered listeners of the transaction
        // use security to authenticate account
        // use security to authorize transfer
        // use bank to obtain account references
        // use reference to perform transaction

        try
        {

            // System.out.println("Connecting to RMI server");
            // Bank b = (Bank)Naming.lookup("//localhost/bank");

            // System.out.println("Before balance changes:");
            // b.printBalances();

            // Account a = (Account)b.getAccount(from.getAccountNumber());

            // a.addBalance(50);

            // System.out.println("After balance changes:");

            // b.printBalances();

        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

    }

    /**
     * Return the account balance
     *
     * @param accountNumber Account number
     * @return Account balance
     */
    public Float getBalance(AccountInfo ai) throws java.rmi.RemoteException
    {
        // Account act = (Account)a.get(accountNumber);
        // return (Float)act.getBalance();
        return (float)0.0;
    }

    public static void main(String[] args)
    {

        try
        {
            System.out.println("Connecting to RMI server");
            BankImpl b = (BankImpl)Naming.lookup("//localhost/bank");

            System.out.println("Before balance changes:");
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }

        // ATM a;

        // try
        // {
        //     a = new ATMImpl();
        //     a.transfer(new AccountInfo(1, 1234), new AccountInfo(2, 2345), (float)150.00);
        // }
        // catch (RemoteException re)
        // {
        //     System.out.println("Remote Exception: " + re);
        // }

    }
}