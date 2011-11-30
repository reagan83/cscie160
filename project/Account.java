package cscie160.project;

import java.lang.Float;

/**
 * Account interface to create an Account objects and set/update balances.
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public class Account
{
    private float balance;
    private final int accountNumber;

    /**
     * Performs account setup by initiating the account number and balance.
     *
     * @param n account number
     */
    public Account(int n)
    {
        balance = (float)0.00;
        accountNumber = n;
    }

    /**
     * Provides interface to set the account balance.
     *
     * @param b Amount needing to be updated
     * @return Newly set balance
     */
    public Float subtractBalance(float b)
    {
        if (b > balance)
        {
            // error
            System.out.println("[" + Account.class.getName() + "] Error.  This needs an exception!");
        }
        else
        {
            balance -= b;
        }

        return new Float(balance);
    }

    public float addBalance(float b)
    {
        balance += b;
        return new Float(balance);
    }

    /**
     * Returns the available balance
     *
     * @return Account balance
     */
    public Float getBalance()
    {
        return new Float(balance);
    }

    /**
     * Returns the account number
     *
     * @return Account number
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }
}
