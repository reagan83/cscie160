package cscie160.project;

import java.lang.Float;

/**
 * AccountInfo class that stores account numbers and associated security pins
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class AccountInfo implements java.io.Serializable
{
    int accountNumber;
    int accountPin;

    public AccountInfo(int n, int p)
    {
        accountNumber = n;
        accountPin = p;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public int getPinNumber()
    {
        return accountPin;
    }

    public boolean equals(Object o)
    {
        if (o instanceof AccountInfo)
        {
            AccountInfo tmp = (AccountInfo)o;

            return (this.getAccountNumber() == tmp.getAccountNumber() && this.getPinNumber() == tmp.getPinNumber());
        }

        return false;
    }
}