package cscie160.project;

import java.util.HashMap;
import java.util.Vector;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Security class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class Security extends UnicastRemoteObject
{
    // account pin object
    private HashMap ap;

    // authorization permissions
    private Vector depositAccess, withdrawAccess, balanceAccess;

    public Security() throws java.rmi.RemoteException
    {
        ap = new HashMap();

        ap.put(1, new AccountInfo(1, 1234));
        ap.put(2, new AccountInfo(2, 2345));
        ap.put(3, new AccountInfo(3, 3456));

        depositAccess = new Vector();
        depositAccess.add(1);
        depositAccess.add(2);
        //depositAccess.add(3); // not granted per specs

        withdrawAccess = new Vector();
        withdrawAccess.add(1);
        //withdrawAccess.add(2); // not granted per specs
        withdrawAccess.add(3);

        balanceAccess = new Vector();
        balanceAccess.add(1);
        balanceAccess.add(2);
        balanceAccess.add(3);
        
    }

    public boolean authorizeTransaction(String transactionType, AccountInfo ai) throws java.rmi.RemoteException
    {
        boolean returnval = false;
        int accountNumber = ai.getAccountNumber();

        if (transactionType.equals("BALANCE"))
        {
            if (balanceAccess.contains(accountNumber))
                returnval = true;
        }
        else if (transactionType.equals("WITHDRAW"))
        {
            if (withdrawAccess.contains(accountNumber))
                returnval = true;
        }
        else if (transactionType.equals("DEPOSIT"))
        {
            if (depositAccess.contains(accountNumber))
                returnval = true;
        }
        else
        {
            // security exception
            System.out.println("[" + Security.class.getName() + "] Invalid transaction type.");
        }

        return returnval;
    }

    public boolean authenticate(AccountInfo ai) throws java.rmi.RemoteException
    {
        AccountInfo tmp = (AccountInfo)ap.get(ai.getAccountNumber());

        if (tmp.equals(ai))
        {
            return true;
        }

        return false;
    }

    public static void main(String[] args)
    {
        try
        {
            Security s = new Security();

            System.out.println("Authenticate: " + s.authenticate(new AccountInfo(2, 23435)));

            AccountInfo ainfo = new AccountInfo(2, 2345);
            System.out.println("Withdraw Balance: " + s.authorizeTransaction("WITHDRAW", ainfo));
//        System.out.println("Authorized Balance: " + s.authorizeTransaction("BALANCE", new AccountInfo2));
//        System.out.println("Authorized Balance: " + s.authorizeTransaction("BALANCE", 3));
        }
        catch (RemoteException re)
        {
            System.out.println("Remote exception: " + re);
        }
        catch (Exception e)
        {
            System.out.println("Exception " + e);
        }

    }
}
