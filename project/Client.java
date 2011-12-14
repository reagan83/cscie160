package cscie160.project;

import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;

public class Client extends UnicastRemoteObject implements ATMListener
{
    public Client() throws java.rmi.RemoteException
    {
        super();
    }

    public static void main(String[] args)
    {
        Client c;

        try
        {
            c = new Client();
            c.start();
        }
        catch (java.rmi.RemoteException re)
        {
            System.out.println("Remote exception : " + re);
        }
    }

    public void start() throws java.rmi.RemoteException
    {
        ATM atm = null;

        try
        {
            ATMFactory factory = (ATMFactory)Naming.lookup("//localhost/atmfactory");
            atm = factory.getATM();
        }
        catch (MalformedURLException mue)
        {
            mue.printStackTrace();
        }
        catch (NotBoundException nbe)
        {
            nbe.printStackTrace();
        }
        catch (UnknownHostException uhe)
        {
            uhe.printStackTrace();
        }
        catch (RemoteException re)
        {
            re.printStackTrace();
        }

        if (atm != null)
        {
            try
            {
                atm.registerListener(this);
                testATM(atm);
            }
            catch (Exception re)
            {
                System.out.println("An exception occurred while communicating with the ATM");
                re.printStackTrace();
            }
        }
        
    }

    private static AccountInfo getAccountInfo(int number, int pin)
    {
        return new AccountInfo(number, pin);
    }

    public void processNotification(TransactionNotification tn)
    {
        System.out.println("Transaction Notification: " + tn.getNotificationMessage());
    }

    public static void testATM(ATM atm)
    {
        if (atm != null)
        {
            printBalances(atm);
            System.out.println("");
            performTestOne(atm);
            System.out.println("");
            performTestTwo(atm);
            System.out.println("");
            performTestThree(atm);
            System.out.println("");
            performTestFour(atm);
            System.out.println("");
            performTestFive(atm);
            System.out.println("");
            performTestSix(atm);
            System.out.println("");
            performTestSeven(atm);
            System.out.println("");
            performTestEight(atm);
            System.out.println("");
            performTestNine(atm);
            System.out.println("");
            printBalances(atm);
            System.out.println("");
        }
    }

    public static void printBalances(ATM atm)
    {
        try
        {
            System.out.println("Balance(0000001): "+atm.getBalance(getAccountInfo(0000001, 1234)));
            System.out.println("Balance(0000002): "+atm.getBalance(getAccountInfo(0000002, 2345)));
            System.out.println("Balance(0000003): "+atm.getBalance(getAccountInfo(0000003, 3456)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void performTestOne(ATM atm)
    {
        try
        {
            atm.getBalance(getAccountInfo(0000001, 5555));
        }
        catch (Exception e)
        {
            System.out.println("Failed as expected: " + e);
        }
    }

    public static void performTestTwo(ATM atm)
    {
        try
        {
            atm.withdraw(getAccountInfo(0000002, 2345), 500);
        }
        catch (Exception e)
        {
            System.out.println("Failed as expected: " + e);
        }
    }

    public static void performTestThree(ATM atm)
    {
        try
        {
            atm.withdraw(getAccountInfo(0000001, 1234), 50);
        }
        catch (Exception e)
        {
            System.out.println("Failed as expected: " + e);
        }
    }

    public static void performTestFour(ATM atm)
    {
        try
        {
            atm.deposit(getAccountInfo(0000001, 1234), 500);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error: " + e);
        }
    }

    public static void performTestFive(ATM atm)
    {
        try
        {
            atm.deposit(getAccountInfo(0000002, 2345), 100);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error: " + e);
        }
    }

    public static void performTestSix(ATM atm)
    {
        try
        {
            atm.withdraw(getAccountInfo(0000001, 1234), 100);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error: " + e);
        }
    }

    public static void performTestSeven(ATM atm)
    {
        try
        {
            atm.withdraw(getAccountInfo(0000003, 3456), 300);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error: " + e);
        }
    }

    public static void performTestEight(ATM atm)
    {
        try
        {
            atm.withdraw(getAccountInfo(0000001, 1234), 200);
        }
        catch (Exception e)
        {
            System.out.println("Failed as expected: " + e);
        }
    }

    public static void performTestNine(ATM atm)
    {
        try
        {
            atm.transfer(getAccountInfo(0000001, 1234),getAccountInfo(0000002, 2345), 100);
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error: " + e);
        }
    }
}
