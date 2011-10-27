package cscie160.hw4;

import java.lang.Float;

public class Account {
    private float balance;
	
    public Account()
	{
        balance = (float)0.00;
    }

	public void setBalance(float b)
    {
        balance = b;
    }

    public Float getBalance()
    {
        return new Float(balance);
    }
}
