package cscie160.hw6;

import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class Server 
{	
    private ServerSocket serverSocket;
    private ATM atmImplementation;
    private BufferedReader bufferedReader;
    private LinkedList threadPool;

    public Server(int port) throws java.io.IOException
    {
        threadPool = new LinkedList();

        threadPool.add(new Thread(new ATMRunnable()));

        serverSocket = new ServerSocket(port);
        atmImplementation = new ATMImplementation(); 
    }

    /** serviceClient accepts a client connection and reads lines from the socket.
     *  Each line is handed to executeCommand for parsing and execution.
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


                    ATMRunnable a = new ATMRunnable(atmImplementation, printStream);
                    ATMRunnable(commandLine);

                    Float result = executeCommand(commandLine);
                    // Only BALANCE command returns non-null
                    if (result != null)
                    {
                        printStream.println(result);  // Write it back to the client
                    }
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

    /** The logic here is specific to our protocol.  We parse the string
     *  according to that protocol.
     */
    private Float executeCommand(String commandLine) throws ATMException
    {
        // Break out the command line into String[]
        StringTokenizer tokenizer = new StringTokenizer(commandLine);
        String commandAndParam[] = new String[tokenizer.countTokens()];
        int index = 0;

        while (tokenizer.hasMoreTokens())
        {
            commandAndParam[index++] = tokenizer.nextToken();
        }

        String command = commandAndParam[0];
        // Dispatch BALANCE request without further ado.
        if (command.equalsIgnoreCase(Commands.BALANCE.toString()))
        {
            return atmImplementation.getBalance();
        }

        // Must have 2nd arg for amount when processing DEPOSIT/WITHDRAW commands
        if (commandAndParam.length < 2)
        {
            throw new ATMException("Missing amount for command \"" + command + "\"");
        }

        try
        {
            float amount = Float.parseFloat(commandAndParam[1]);

            if (command.equalsIgnoreCase(Commands.DEPOSIT.toString()))
            {
                atmImplementation.deposit(amount);
            }
            else if (command.equalsIgnoreCase(Commands.WITHDRAW.toString()))
            {
                atmImplementation.withdraw(amount);
            }
            else
            {
                throw new ATMException("Unrecognized command: " + command);
            }
        }
        catch (NumberFormatException nfe)
        {
            throw new ATMException("Unable to make float from input: " + commandAndParam[1]);
        }

        // BALANCE command returned result above.  Other commands return null;
        return null;
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