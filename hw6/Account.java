package cscie160.hw6;

import java.lang.Float;

/**
 * Account class with synchronized methods.
 *
 * Provides an interface to create an Account and set/update balances.
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public class Account
{
    private float balance;

    /**
     * Default constructor initiates balance to 0.
     */
    public Account()
    {
        balance = (float)0.00;
    }

    /**
     * Provides interface to set the account balance.
     *
     * @param b Amount needing to be updated
     * @return Newly set balance
     */
    public synchronized Float setBalance(float b)
    {
        balance = b;
        notifyAll();
        return new Float(balance);
    }

    /**
     * Returns the available balance.
     *
     * @return Account balance
     */
    public synchronized Float getBalance()
    {
        return new Float(balance);
    }
}
