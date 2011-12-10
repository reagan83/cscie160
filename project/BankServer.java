package cscie160.project;

/**
 * BankServer class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class BankServer
{
    public static Bank b;
    public static Security s;

    public BankServer()
    {
        b = new Bank();
        s = new Security();

        // register both with the rmiregistry
    }
}
