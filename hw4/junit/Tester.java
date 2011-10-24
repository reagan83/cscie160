package cscie160.hw4;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Tester
{
    public static void main(String[] args)
    {
        Result r = JUnitCore.runClasses(AccountTest.class);
        for (Failure f : result.getFailures())
        {
            System.out.println(f.toString());
        }
    }
}