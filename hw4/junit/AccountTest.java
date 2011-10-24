package cscie160.hw4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AccountTest
{
    @Test
    public void testSomething()
    {
        Account a = new Account();
        assertEquals("Result", 50, a.setBalance(50));
    }
}
