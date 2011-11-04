package cscie160.hw5;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.rmi.Naming;

public class Server 
{
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
        System.out.println("Getting ATM Factory instance...");

        Server s = new Server();
    }
}