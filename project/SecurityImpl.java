package cscie160.project;

import java.util.HashMap;
import java.util.Vector;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * SecurityImpl class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class SecurityImpl extends UnicastRemoteObject implements Security
{
    // account pin object
    private HashMap ap;

    // authorization permissions
    private Vector depositAccess, withdrawAccess, balanceAccess;

    public SecurityImpl() throws java.rmi.RemoteException
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

    public void authorizeTransaction(String transactionType, AccountInfo ai) throws java.rmi.RemoteException, ATMException
    {
        boolean auth = false;
        int accountNumber = ai.getAccountNumber();

        if (transactionType.equals("BALANCE"))
        {
            if (balanceAccess.contains(accountNumber))
                auth = true;
        }
        else if (transactionType.equals("WITHDRAW"))
        {
            if (withdrawAccess.contains(accountNumber))
                auth = true;
        }
        else if (transactionType.equals("DEPOSIT"))
        {
            if (depositAccess.contains(accountNumber))
                auth = true;
        }
        else
        {
            throw new ATMException("Invalid security authorization transaction type.");
        }

        if (auth == false)
        {
            throw new ATMException("Account not authorized to perform this transaction type (" + transactionType + ").");
        }
    }

    public void authenticate(AccountInfo ai) throws java.rmi.RemoteException, ATMException
    {
        AccountInfo tmp = (AccountInfo)ap.get(ai.getAccountNumber());

        if (!tmp.equals(ai))
        {
            throw new ATMException("Security authenication failed.");
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Security s = new SecurityImpl();

            AccountInfo ainfo = new AccountInfo(2, 2345);
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
