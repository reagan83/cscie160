package cscie160.hw5.rmitest;

import java.rmi.*;

public class HelloClient
{
    public static void main(String[] args)
    {
        try
        {
            Hello h = (Hello)Naming.lookup("//localhost/HelloServer");
            System.out.println("Server returned: " + h.sayHello());
            System.out.println("Type of HelloServer: " + h.getClass().getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}