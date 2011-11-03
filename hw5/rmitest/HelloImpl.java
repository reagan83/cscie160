package cscie160.hw5.rmitest;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello
{
    private String name;

    public HelloImpl(String s) throws RemoteException
    {
        super();
        name = s;
    }

    public String sayHello() throws RemoteException
    {
        return "Hello world!";
    }

    public static void main(String[] args)
    {
        try
        {
            HelloImpl o = new HelloImpl("HelloServer");
            Naming.rebind("//localhost/HelloServer", o);
            System.out.println("HelloServer bound in registry");
        }
        catch (Exception e)
        {
            System.out.println("HelloImpl error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}