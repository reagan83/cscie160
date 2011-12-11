package cscie160.project;

import java.util.HashMap;

/**
 * Bank class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class Bank
{
    private HashMap a;

    public Bank()
    {
        a = new HashMap();
        Account acc;

        a.put(1, new Account(1));

        acc = (Account)a.get(1);
        acc.addBalance(0);

        a.put(2, new Account(2));

        acc = (Account)a.get(2);
        acc.addBalance(100);

        a.put(3, new Account(3));

        acc = (Account)a.get(2);
        acc.addBalance(500);
    }

    public Account getAccount(int accountNumber)
    {
        if (a.size() >= accountNumber)
        {
            return (Account)a.get(accountNumber);
        }

        System.out.println("Invalid account number.");
        return null;
    }

}
