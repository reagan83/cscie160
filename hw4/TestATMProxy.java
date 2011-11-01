package cscie160.hw4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestATMProxy
{
    private static String serverHost = "localhost";
    private static String serverHostUnknown = "noresolve.google.com";
    private static int serverPort = 7777;
    private static double balanceDelta = 0.001;

    ATMProxy a;

    private boolean connectATMProxy(ATMProxy atm, String sh, int p)
    {
        try
        {
            atm = new ATMProxy(sh, p);
            return true;
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }

        return false;
    }

    private void disconnectATMProxy(ATMProxy atm)
    {
        try
        {
            atm.disconnect();
        }
        catch (ATMException e)
        {
            System.out.println("ATM Exception: " + e);
        }
    }

    private void failTestWithException(String errorMsg)
    {
        System.out.println("Exception caught: " + errorMsg);
        assertTrue(false);
    }

    @Test public void testConstructorFailure()
    {
        ATMProxy a = null;

        if (connectATMProxy(a, serverHostUnknown, serverPort))
        {
            System.out.println("Error! This should not happen!");
        }
        else
        {
            assertTrue(false);
        }
    }

    @Test public void testConstructorSuccess()
    {
        ATMProxy a = null;

        try
        {
            a = new ATMProxy(serverHost, serverPort);
            assertTrue(true);
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
            assertTrue(false);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
            assertTrue(false);
        }

        disconnectATMProxy(a);
    }

    @Test public void testDepositFailure()
    {
        ATMProxy a = null;

        try
        {
            a = new ATMProxy(serverHost, serverPort);
            try
            {
                a.deposit(50);
                float balance = (float)a.getBalance();
                a.withdraw(50);
                assertEquals((float)51, balance, balanceDelta);
            }
            catch (ATMException e)
            {
                failTestWithException(e.toString());
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }
    }

    @Test public void testDepositSuccess()
    {
        ATMProxy a = null;

        try
        {
            a = new ATMProxy(serverHost, serverPort);

            try
            {
                a.deposit(50);
                float balance = (float)a.getBalance();
                a.withdraw(50);
                assertEquals((float)50, balance, balanceDelta);
            }
            catch (ATMException e)
            {
                failTestWithException(e.toString());
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }

    }

    @Test public void testWithdrawSuccess()
    {
        ATMProxy a = null;

        try
        {
            a = new ATMProxy(serverHost, serverPort);

            try
            {
                a.deposit(100);
                a.withdraw(100);
                assertEquals((float)0, (float)a.getBalance(), balanceDelta);
            }
            catch (ATMException e)
            {
                failTestWithException(e.toString());
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }
    }

    @Test public void testWithdrawOverflow()
    {
        ATMProxy a = null;
       
        try
        {
            a = new ATMProxy(serverHost, serverPort);

            try
            {
                a.deposit(100);
                a.withdraw(101);

                if (a.getBalance() != -1)
                {
                    a.withdraw(100);
                    assertTrue(false);
                }
            }
            catch (ATMException e)
            {
                try
                {
                    float balance = (float)a.getBalance();
                    a.withdraw(100);
                    assertEquals((float)-1, balance, balanceDelta);
                }
                catch (ATMException e2)
                {
                    failTestWithException(e2.toString());
                }
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }
    }

    @Test public void testWithdrawFailure()
    {
        ATMProxy a = null;
       
        try
        {
            a = new ATMProxy(serverHost, serverPort);

            try
            {
                a.deposit(100);
                a.withdraw(99);
                float balance = (float)a.getBalance();
                a.withdraw(1);
                assertEquals((float)0, balance, balanceDelta);
            }
            catch (ATMException e)
            {
                failTestWithException(e.toString());
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }
    }

    @Test public void testGetBalanceSuccess()
    {
        ATMProxy a = null;
       
        try
        {
            a = new ATMProxy(serverHost, serverPort);

            try
            {
                assertEquals((float)0, (float)a.getBalance(), balanceDelta);
            }
            catch (ATMException e)
            {
                failTestWithException(e.toString());
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }
    }

    @Test public void testGetBalanceFailure()
    {
        ATMProxy a = null;
       
        try
        {
            a = new ATMProxy(serverHost, serverPort);

            try
            {
                assertEquals((float).50, (float)a.getBalance(), balanceDelta);
            }
            catch (ATMException e)
            {
                failTestWithException(e.toString());
            }
        }
        catch (java.net.UnknownHostException he)
        {
            System.out.println("Unknown host: " + he);
        }
        catch (java.io.IOException ioe)
        {
            System.out.println("IO Exception: " + ioe);
        }
        finally
        {
            disconnectATMProxy(a);
        }
    }

}
