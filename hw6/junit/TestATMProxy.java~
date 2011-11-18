package cscie160.hw4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestATMImplementation
{
    @Test public void testDepositSuccess()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            a.deposit(50);
            assertEquals((float)50, (float)a.getBalance(), 0.001);
        }
        catch (ATMException e)
        {
            System.out.println("Exception caught: " + e);
        }
    }

    @Test public void testDepositFailure()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            a.deposit(50);
            assertEquals((float)51, (float)a.getBalance(), 0.001);
        }
        catch (ATMException e)
        {
            System.out.println("Exception caught: " + e);
        }
    }

    @Test public void testWithdrawSuccess()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            a.deposit(100);
            a.withdraw(100);
            assertEquals((float)0, (float)a.getBalance(), 0.001);
        }
        catch (ATMException e)
        {
            System.out.println("Exception caught: " + e);
        }
    }

    @Test public void testWithdrawOverflow()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            a.deposit(100);
            a.withdraw(101);
        }
        catch (ATMException e)
        {
            try
            {
                assertEquals((float)-1, (float)a.getBalance(), 0.001);
            }
            catch (ATMException e2)
            {
                System.out.println("Exception caught: " + e2);
            }
        }
    }

    @Test public void testWithdrawFailure()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            a.deposit(100);
            a.withdraw(99);
            assertEquals((float)0, (float)a.getBalance(), 0.001);
        }
        catch (ATMException e)
        {
            System.out.println("Exception caught: " + e);
        }
    }

    @Test public void testGetBalanceSuccess()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            assertEquals((float)0, (float)a.getBalance(), 0.001);
        }
        catch (ATMException e)
        {
            System.out.println("Exception caught: " + e);
        }
    }

    @Test public void testGetBalanceFailure()
    {
        ATMImplementation a = new ATMImplementation();

        try
        {
            assertEquals((float).50, (float)a.getBalance(), 0.001);
        }
        catch (ATMException e)
        {
            System.out.println("Exception caught: " + e);
        }
    }

}
