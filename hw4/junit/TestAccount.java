package cscie160.hw4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestAccount
{
    @Test public void testSetBalanceSuccess()
    {
        Account a = new Account();
        assertEquals((float)50, (float)a.setBalance(50), 0.001);
    }

    @Test public void testSetBalanceFailure()
    {
        Account a = new Account();
        assertEquals((float)51, (float)a.setBalance(50), 0.001);
    }

    @Test public void testGetBalanceSuccess()
    {
        Account a = new Account();
        a.setBalance(100);
        assertEquals((float)100, a.getBalance(), 0.001);
    }

    @Test public void testGetBalanceFailure()
    {
        Account a = new Account();
        a.setBalance(100);
        assertEquals((float)101, (float)a.getBalance(), 0.001);
    }

}
