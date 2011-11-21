package cscie160.hw6;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Server 
{	
    private ServerSocket serverSocket;
    private ATM atmImplementation;
    private BufferedReader bufferedReader;
    private Vector requestQueue;

    public Server(int port) throws java.io.IOException
    {
        requestQueue = new Vector();

        Thread t1 = new Thread(new ATMThread(requestQueue));

        t1.start();

        serverSocket = new ServerSocket(port);
        atmImplementation = new ATMImplementation();
    }

    /** serviceClient accepts a client connection and reads lines from the socket.
     *  Each line is handedt weo executeCommand for parsing and execution.
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
                try
                {
                    if (commandLine.equals("EXIT"))
                    {
                        System.out.println("Disconnect requested.");
                        break;
                    }

                    ATMRunnable a = new ATMRunnable(atmImplementation, printStream, commandLine);

                    requestQueue.add(a);
                    notifyAll();
                }
                catch (ATMException atmex)
                {
                    System.out.println("ERROR: " + atmex);
                }
            }
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