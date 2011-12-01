package cscie160.hw6;

/**
 * ATM Implementation class to provide an implementation to basic ATM operations.
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public class ATMImplementation implements ATM
{
    Account a;

    /**
     * Default constructor, creates Account object.
     */
    public ATMImplementation()
    {
        a = new Account();
    }
	
    /**
     * Deposit amount into account
     *
     * @param amount Amount to deposit
     */
    public void deposit(float amount) throws ATMException
    {
        a.setBalance(a.getBalance() + amount);
    }

    /**
     * Withdraw amount from account
     *
     * @param amount Amount to withdraw
     */
    public void withdraw(float amount) throws ATMException
    {
        if (a.getBalance() >= amount)
            a.setBalance(a.getBalance() - amount);
        else
            throw new ATMException("Insufficient funds.");
    }

    /**
     * Return balance of account
     *
     * @return Account balance
     */
    public Float getBalance() throws ATMException
    {
        return a.getBalance();
    }
	
}
