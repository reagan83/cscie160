package cscie160.project;

import java.lang.Float;

/**
 * Account Info
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class AccountInfo
{
    Account account;
    int accountPin;

    public AccountInfo(Account a, int p)
    {
        account = a;
        accountPin = p;
    }

    private int getAccountNumber()
    {
        return account.getAccountNumber();
    }

    private int getPinNumber()
    {
        return accountPin;
    }

    public boolean equals(Object o)
    {
        if (o instanceof AccountInfo)
        {
            return (this.getAccountNumber() == o.getAccountNumber() && this.getPinNumber() == o.getPinNumber());
        }

        return false;
    }
}