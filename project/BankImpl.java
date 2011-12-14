package cscie160.project;

import java.util.HashMap;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * Bank class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class BankImpl extends UnicastRemoteObject implements Bank
{
    private HashMap<Integer, AccountImpl> a;

    public BankImpl() throws java.rmi.RemoteException
    {
        a = new HashMap<Integer, AccountImpl>();
        Account acc;

        a.put(1, new AccountImpl(1));

        acc = (Account)a.get(1);
        acc.addBalance(0);

        a.put(2, new AccountImpl(2));

        acc = (Account)a.get(2);
        acc.addBalance(100);

        a.put(3, new AccountImpl(3));

        acc = (Account)a.get(3);
        acc.addBalance(500);
    }

    public Account getAccount(int accountNumber) throws java.rmi.RemoteException
    {
//        System.out.println("Object: " + a.get(accountNumber));
        if (a.size() >= accountNumber)
        {
            return (Account)a.get(accountNumber);
        }

        System.out.println("[" + Bank.class.getName() + "] Invalid account number.");
        return null;
    }

    // test helper function
    public void printBalances() throws java.rmi.RemoteException
    {
        Account acc;

        acc = (Account)a.get(1);
        System.out.println("Account 1: " + acc.getBalance());

        acc = (Account)a.get(2);
        System.out.println("Account 2: " + acc.getBalance());

        acc = (Account)a.get(3);
        System.out.println("Account 3: " + acc.getBalance());
    }

    public static void main(String[] args)
    {
        try
        {
//            Bank b = new BankImpl();
//            Account tmp = b.getAccount(3);
//            System.out.println("Balance: " + tmp.getBalance());
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    } 

}
