package cscie160.project;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Remote;
import java.rmi.Naming;

/**
 * BankServer class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class BankServer
{
    /**
     * Sets up BankServer object and registers in the RMI registry.
     */
    public BankServer()
    {
        try
        {
            BankImpl b = new BankImpl();
            Naming.rebind("//localhost/bank", b);
            System.out.println("Bank bound in registry.");

            SecurityImpl s = new SecurityImpl();
            Naming.rebind("//localhost/security", s);
            System.out.println("Security bound in registry.");
        }
        catch (RemoteException re)
        {
            System.out.println("Remote error: " + re.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("BankServer error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        BankServer bs = new BankServer();
    }
}
