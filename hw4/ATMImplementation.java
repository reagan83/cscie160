package cscie160.hw4;

public class ATMImplementation implements ATM {
    Account a;

    public ATMImplementation()
    {
        a = new Account();
    }
	
    public void deposit(float amount) throws ATMException
    {
        a.setBalance(a.getBalance() + amount);
    }
	
    public void withdraw(float amount) throws ATMException
    {
        if (a.getBalance() >= amount)
            a.setBalance(a.getBalance() - amount);
        else
            throw new ATMException("Insufficient funds.");
    }

    public Float getBalance() throws ATMException
    {
        return a.getBalance();
    }
	
}
