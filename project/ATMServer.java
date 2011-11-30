package cscie160.project;

import java.rmi.Naming;

/**
 * ATMServer RMI ready class that registers ATMFactory object with RMIRegistry
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-02
 */
public class ATMServer
{
    /**
     * Sets up ATMServer object and registers ATM object in the RMI registry.
     */
    public ATMServer()
    {
        try
        {
            ATMFactory obj = new ATMFactoryImpl();
            Naming.rebind("//localhost/atmfactory", obj);
            System.out.println("atmfactory bound in registry.");
        }
        catch (Exception e)
        {
            System.out.println("ATMFactory error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        ATMServer s = new ATMServer();
    }
}