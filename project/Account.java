package cscie160.project;

import java.lang.Float;

/**
 * Account interface to create an Account objects and set/update balances.
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
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
    public Float setBalance(float b)
    {
        balance = b;
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
