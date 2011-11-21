package cscie160.hw6;

import java.util.Vector;

public class ATMThread implements Runnable
{
    Vector requestQueue;

    public ATMThread(Vector rq)
    {
        this.requestQueue = rq;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                if (requestQueue.isEmpty())
                {
                    System.out.println("Thread waiting...");
                    wait();
                }
            }
            catch (InterruptedException e)
            {
            }

            System.out.println("Thread executing...");

            ATMRunnable a = (ATMRunnable)requestQueue.get(0);
            a.run();

            requestQueue.remove(0);

            notifyAll();
        }
    }

}