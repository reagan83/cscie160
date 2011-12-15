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

    /**
     * AccountInfo object setup by initiating the account number and pin.
     *
     * @param n account number
     */
    public AccountInfo(int n, int p)
    {
        accountNumber = n;
        accountPin = p;
    }

    /**
     * Returns the account number
     *
     * @return Account number
     */
    public int getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Returns the account pin
     *
     * @return Account pin
     */
    public int getPinNumber()
    {
        return accountPin;
    }

    /**
     * Tests for object equality based on pin and account number
     *
     * @return boolean based on test outcome
     */
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