package cscie160.hw6;

import java.net.*;
import java.io.*;

/**
 * ATM Client-side proxy class that manages the connection to the
 * server and forwards the client's requests to the server by writing
 * the text of requests to the stream on top of the sockect established
 * at creation time when the constructor is called.
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public class ATMProxy implements ATM 
{
    private Socket socket;
    private PrintStream  printStream;
    BufferedReader inputReader;

    /**
     * Default ATMProxy constructor initiates Socket and stream objects
     */
    public ATMProxy(String host, int port) throws UnknownHostException, java.io.IOException 
	{
		socket = new Socket(host, port);
		OutputStream outputStream = socket.getOutputStream();
		printStream = new PrintStream(outputStream);
		InputStream inputStream = socket.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		inputReader = new BufferedReader(inputStreamReader);
    }

    /**
     * Disconnects an ATM session
     */
    public void disconnect() throws ATMException
    {
        printStream.println("EXIT");
    }

    /**
     * Sends the command to deposit an amount to server
     *
     * @param amount Amount to deposit
     */
    public void deposit(float amount) throws ATMException 
	{
		// Commands is an enum in this package
		System.out.println("ATMProxy writing command to server: " + Commands.DEPOSIT);
		printStream.println(Commands.DEPOSIT + " " + amount);
    }

    /**
     * Sends the command to withdraw funds to server
     *
     * @param amount Amount to withdraw
     */
    public void withdraw(float amount) throws ATMException 
	{
		System.out.println("ATMProxy writing command to server: " + Commands.WITHDRAW);
		printStream.println(Commands.WITHDRAW +  " " +  amount);
    }

    /**
     * Sends the command to retrieve balance to server
     *
     * @return Account balance.
     */
    public Float getBalance() throws ATMException 
	{
		System.out.println("ATMProxy writing command to server: " + Commands.BALANCE);
		printStream.println(Commands.BALANCE);
		try 
		{
			String response = inputReader.readLine();
			if (response != null)
			{
                System.out.println("Server returned: " + response);
                return Float.parseFloat(response.trim());
			} 
			else 
			{
				throw new ATMException("ATMProxy: Unexpected end of stream reading commands in getBalance()");
			}
				
		} 
		catch (Exception ex) 
		{
			throw new ATMException(ex.toString());
		}
    }
}
