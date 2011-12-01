package cscie160.hw6;

import java.util.Vector;

/**
 * ATM Thread class.
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public class ATMThread implements Runnable
{
    Vector requestQueue;

    /**
     * Default ATMThread constructor initiates requestQueue
     *
     * @param rq Request queue object
     */
    public ATMThread(Vector rq)
    {
        this.requestQueue = rq;
    }

    /**
     * Main thread method pops oldest ATMRunnable object off of the Vector and executes it.
     */
    public void run()
    {
        while (true)
        {
            synchronized (requestQueue)
            {
                try
                {
                    if (requestQueue.isEmpty())
                    {
                        System.out.println("Thread " + Thread.currentThread() + " waiting...");
                        requestQueue.wait();
                    }
                }
                catch (InterruptedException e)
                {
                }
                catch (Exception e)
                {
                    System.out.println("Exception caught: " + e);
                }

                if (!requestQueue.isEmpty())
                {
                    System.out.println("Thread " + Thread.currentThread() + " executing...");

                    ATMRunnable a = (ATMRunnable)requestQueue.get(0);
                    try
                    {
                        a.run();
                    }
                    catch (ATMException e)
                    {
                        System.out.println("ATM Exception caught: " + e);
                    }

                    requestQueue.remove(0);
                    System.out.println("Thread " + Thread.currentThread() + " completed.");
                }

                requestQueue.notifyAll();
            }

            try
            {
                Thread.sleep(1000);
            }
            catch (Exception e2)
            {
            }
        }
    }
}
