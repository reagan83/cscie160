package cscie160.project;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.Naming;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * ATMImpl RMI ready class that interfaces with ATM
 *
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
    private float atmCashBalance;
    private Security security;
    private Bank bank;

    /** Maintain list of ATM listener objects */
    private List<ATMListener> listeners;

    /**
     * ATMImpl constructor that sets default ATM balance of $500.00.
     * Initiates the ATM listeners ArrayList.
     * Connects to the remote Security and Bank instances.
     *
     */
    public ATMImpl() throws java.rmi.RemoteException
    {
        addATMCashBalance(500);

        listeners = new ArrayList<ATMListener>();

        try
        {
            security = (Security)Naming.lookup("//localhost/security");
            bank = (Bank)Naming.lookup("//localhost/bank");
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Registers an ATMListener
     *
     * @param a ATM listener object
     */
    public void registerListener(ATMListener a)
    {
        listeners.add(a);
    }

    /**
     * Iterates through atm listeners and sends notifications.
     *
     * @param notification Transaction notification message
     */
    private void notifyListeners(String notification)
    {
        try
        {
            for (ATMListener l : listeners)
            {
                l.processNotification(new TransactionNotification(this.toString(), notification));
            }
        }
        catch (java.rmi.RemoteException re)
        {
            System.out.println("Remote Exception: " + re);
        }
    }

    /**
     * Deposits amount into account
     *
     * @param ai AccountInfo object
     * @param amount Amount to deposit
     * @return true, unless an exception occurred
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public boolean deposit(AccountInfo ai, float amount) throws java.rmi.RemoteException, ATMException
    {
        notifyListeners("[Account #: " + ai.getAccountNumber() + "] Deposit " + amount + ".");
        security.authenticate(ai);
        security.authorizeTransaction("DEPOSIT", ai);

        Account a = (Account)bank.getAccount(ai.getAccountNumber());
        a.addBalance(amount);

        // It might make intuitive sense to perform this operation, but because it is
        //  assumed that deposits are checks (not cash) the ATM cash balance is not increased.
        // this.addATMCashBalance(amount);

        return true;
    }

    /**
     * Withdraw amount from account
     *
     * @param ai AccountInfo object
     * @param amount Amount to withdraw
     * @return true, unless an exception occurred
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public boolean withdraw(AccountInfo ai, float amount) throws java.rmi.RemoteException, ATMException
    {
        notifyListeners("[Account #: " + ai.getAccountNumber() + "] Withdraw " + amount + ".");
 
        security.authenticate(ai);
        security.authorizeTransaction("WITHDRAW", ai);

        Account a = (Account)bank.getAccount(ai.getAccountNumber());

        if (getATMCashBalance() < amount)
            throw new ATMException("Not enough cash in this ATM to perform this operation.");

        if (a.getBalance() < amount)
            throw new ATMException("Insufficient funds in account to perform this operation.");

        a.subtractBalance(amount);
        this.subtractATMCashBalance(amount);

        return true;
    }

    /**
     * Transfer balance to another account
     *
     * @param from AccountInfo object
     * @param to AccountInfo object
     * @param amount Amount to withdraw
     * @return false if withdraw fails, otherwise true unless an exception occurs
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public boolean transfer(AccountInfo from, AccountInfo to, float amount) throws java.rmi.RemoteException, ATMException
    {
        notifyListeners("[Account #: " + from.getAccountNumber() + ", Account #: " + to.getAccountNumber() + "] Transfer " + amount + ".");

        security.authenticate(from);
        security.authenticate(to);
        security.authorizeTransaction("WITHDRAW", from);
        security.authorizeTransaction("DEPOSIT", to);

        if (this.withdraw(from, amount))
        {
            this.deposit(to, amount);
        }
        else
        {
            // failed withdraw
            return false;
        }

        return true;
    }

    /**
     * Return the account balance
     *
     * @param ai AccountInfo object
     * @return Account balance
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public Float getBalance(AccountInfo ai) throws java.rmi.RemoteException, ATMException
    {
        security.authenticate(ai);
        security.authorizeTransaction("BALANCE", ai);

        Account a = (Account)bank.getAccount(ai.getAccountNumber());
        return (Float)a.getBalance();
    }

    /**
     * Helper method to manage the ATM cash balance.
     *
     * @param amount amount to add
     */
    private void addATMCashBalance(float amount)
    {
        this.atmCashBalance = amount;
    }

    /**
     * Helper method to manage the ATM cash balance.
     *
     * @param amount amount to subtract
     */
    private void subtractATMCashBalance(float amount)
    {
        this.atmCashBalance -= amount;
    }

    /**
     * Helper method to manage the ATM cash balance.
     *
     * @returns ATM cash balance
     */
    private float getATMCashBalance()
    {
        return atmCashBalance;
    }
}