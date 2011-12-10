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
    public static HashMap<Account> a;

    public Bank()
    {
        a.put(1, new Account(1));
        a.get(1).addBalance(0);

        a.put(2, new Account(2));
        a.get(2).addBalance(100);

        a.put(3, new Account(3));
        a.get(3).addBalance(500);
    }

    public Account getAccount(int accountNumber)
    {
        if (a.size() >= accountNumber)
        {
            return a.get(accountNumber);
        }

        System.out.println("Invalid account number.");
        return null;
    }
}
