package cscie160.hw6;

/**
 * Implements an ATM interface to perform basic ATM operations.
 */
public class ATMImplementation implements ATM {
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
