package cscie160.hw5;

import java.rmi.Naming;

/**
 * Server RMI ready class that registers ATMFactory object with RMIRegistry
 * 
 * @author Reagan Williams
 * @version 1.5 (hw5)
 * @since 2011-11-02
 */
public class Server 
{
    /**
     * Sets up Server object and registers ATM object in the RMI registry.
     *
     */
    public Server()
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
        Server s = new Server();
    }
}