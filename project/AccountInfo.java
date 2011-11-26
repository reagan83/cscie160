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
}