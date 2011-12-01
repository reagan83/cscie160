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
    private float atmCashBalance;
    private Security security;
    private Bank bank;

    /**
     * ATM Impl constructor that creates 3 account objects with varying initial balances.
     */
    public ATMImpl() throws java.rmi.RemoteException
    {
        addATMCashBalance(500);

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
     * Deposits amount into account
     *
     * @param accountNumber Account number
     * @param amount Amount to deposit
     */
    public boolean deposit(AccountInfo ai, float amount) throws java.rmi.RemoteException
    {
        try
        {
            security.authenticate(ai);
            security.authorizeTransaction("DEPOSIT", ai);

            Account a = (Account)bank.getAccount(ai.getAccountNumber());
            a.addBalance(amount);

            // It might make intuitive sense to perform this operation, but because it is
            //  assumed that deposits are checks (not cash) the ATM cash balance is not increased.
            // this.addATMCashBalance(amount);
        }
        catch (ATMException atme)
        {
            System.out.println("ATM Exception: " + atme);
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Exception caught: " + e);
            return false;
        }

        return true;
    }

    /**
     * Withdraw amount from account
     *
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     */
    public boolean withdraw(AccountInfo ai, float amount) throws java.rmi.RemoteException
    {
        try
        {
            security.authenticate(ai);
            security.authorizeTransaction("WITHDRAW", ai);

            Account a = (Account)bank.getAccount(ai.getAccountNumber());

            if (getATMCashBalance() < amount)
                throw new ATMException("Not enough cash in this ATM to perform this operation.");

            if (a.getBalance() < amount)
                throw new ATMException("Insufficient funds in account to perform this operation.");

            a.subtractBalance(amount);
            this.subtractATMCashBalance(amount);
        }
        catch (ATMException atme)
        {
            System.out.println("ATM Exception: " + atme);
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Exception caught: " + e);
            return false;
        }

        return true;
    }

    /**
     * Transfer balance to another account
     *
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     */
    public boolean transfer(AccountInfo from, AccountInfo to, float amount) throws java.rmi.RemoteException
    {
        // notify all registered listeners of the transaction

        try
        {
            security.authenticate(from);
            security.authenticate(to);
            security.authorizeTransaction("WITHDRAW", from);
            security.authorizeTransaction("DEPOSIT", to);

            bank.printBalances();

            if (this.withdraw(from, amount))
            {
                this.deposit(to, amount);
                System.out.println("Successful transfer!");
            }

            bank.printBalances();
        }
        catch (ATMException atme)
        {
            System.out.println("ATM Exception: " + atme);
            return false;
        }
        catch (Exception e)
        {
            System.out.println("Exception caught: " + e);
            return false;
        }

        return true;
    }

    /**
     * Return the account balance
     *
     * @param accountNumber Account number
     * @return Account balance
     */
    public Float getBalance(AccountInfo ai) throws java.rmi.RemoteException
    {
        Float balance = (float)0.00;

        try
        {
            security.authenticate(ai);
            security.authorizeTransaction("BALANCE", ai);

            Account a = (Account)bank.getAccount(ai.getAccountNumber());
            balance = (Float)a.getBalance();
        }
        catch (ATMException atme)
        {
            System.out.println("ATM Exception: " + atme);
        }
        catch (Exception e)
        {
            System.out.println("Exception caught: " + e);
        }

        return balance;
    }

    private void addATMCashBalance(float amount)
    {
        this.atmCashBalance = amount;
    }

    private void subtractATMCashBalance(float amount)
    {
        this.atmCashBalance -= amount;
    }

    private float getATMCashBalance()
    {
        return atmCashBalance;
    }

    public static void main(String[] args)
    {
        ATM a;

        try
        {
            a = new ATMImpl();
            a.transfer(new AccountInfo(3, 3456), new AccountInfo(2, 2345), (float)50.00);
        }
        catch (RemoteException re)
        {
            System.out.println("Remote Exception: " + re);
        }
    }
}