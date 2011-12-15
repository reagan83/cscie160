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
    /** HashMap object to manage Account pins */
    private HashMap<Integer, AccountInfo> ap;

    /** Vector to manage authorization permissions */
    private Vector<Integer> depositAccess, withdrawAccess, balanceAccess;

    /**
     * SecurityImpl constructor initializes the Accounts with a set security pin
     * This also initializes account authorization types. 
     */
    public SecurityImpl() throws java.rmi.RemoteException
    {
        ap = new HashMap<Integer, AccountInfo>();

        ap.put(1, new AccountInfo(1, 1234));
        ap.put(2, new AccountInfo(2, 2345));
        ap.put(3, new AccountInfo(3, 3456));

        depositAccess = new Vector<Integer>();
        depositAccess.add(1);
        depositAccess.add(2);
        //depositAccess.add(3); // not granted per specs

        withdrawAccess = new Vector<Integer>();
        withdrawAccess.add(1);
        //withdrawAccess.add(2); // not granted per specs
        withdrawAccess.add(3);

        balanceAccess = new Vector<Integer>();
        balanceAccess.add(1);
        balanceAccess.add(2);
        balanceAccess.add(3);
        
    }

    /**
     * This method performs transaction-type authorizations for Accounts
     *
     * @param transactionType Type of transaction
     * @param ai Account Info object
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
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

    /**
     * This method performs the authentication for Accounts
     *
     * @param ai Account Info object
     * @throws java.rmi.RemoteException
     * @throws ATMException
     */
    public void authenticate(AccountInfo ai) throws java.rmi.RemoteException, ATMException
    {
        AccountInfo tmp = (AccountInfo)ap.get(ai.getAccountNumber());

        if (!tmp.equals(ai))
        {
            throw new ATMException("Security authenication failed.");
        }
    }

}
