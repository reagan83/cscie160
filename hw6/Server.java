package cscie160.hw6;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Server class for the ATM.
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public class Server 
{	
    private ServerSocket serverSocket;
    private ATM atmImplementation;
    private BufferedReader bufferedReader;
    private Vector requestQueue;
    private ATMThread atmThread;
    private Thread t1, t2, t3, t4, t5;

    /**
     * Server constructor initiates Threads and request queue
     *
     * @param port Port number for server
     */
    public Server(int port) throws java.io.IOException
    {
        requestQueue = new Vector();
        atmThread = new ATMThread(requestQueue);

        t1 = new Thread(atmThread);
        t2 = new Thread(atmThread);
        t3 = new Thread(atmThread);
        t4 = new Thread(atmThread);
        t5 = new Thread(atmThread);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        serverSocket = new ServerSocket(port);
        atmImplementation = new ATMImplementation();
    }

    /**
     * serviceClient accepts a client connection and reads lines from the socket.
     * In addition, commands are submitted to the Thread requestQueue to be handled by an ATMThread.
     *
     * @throws java.io.IOException
     */
    public void serviceClient() throws java.io.IOException
    {
        System.out.println("Accepting clients now");
        Socket clientConnection = serverSocket.accept();

        // Arrange to read input from the Socket
        InputStream inputStream = clientConnection.getInputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        // Arrange to write result across Socket back to client
        OutputStream outputStream = clientConnection.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        System.out.println("Client acquired on port #" + serverSocket.getLocalPort() + ", reading from socket");

        try
        {
            String commandLine;

            while ((commandLine = bufferedReader.readLine()) != null)
            {
                if (commandLine.equals("EXIT"))
                {
                    System.out.println("Disconnect requested.");
                    break;
                }

                ATMRunnable a = new ATMRunnable(atmImplementation, printStream, commandLine);

                synchronized (requestQueue)
                {
                    requestQueue.add(a);
                    requestQueue.notifyAll();
                }
            }

            System.out.println("Finished while loop...");
        }
        catch (SocketException sException)
        {
            // client has stopped sending commands.  Exit gracefully.
            System.out.println("done");
        }
    }

    public static void main(String argv[])
    {
        int port = 1099;

        if(argv.length > 0)
        {
            try
            {
                port = Integer.parseInt(argv[0]);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            Server server = new Server(port);

            // added unlimited while loop (RWilliams)
            while (true)
            {
                server.serviceClient();
                System.out.println("Client serviced");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}