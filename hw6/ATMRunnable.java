package cscie160.hw6;

import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * ATM Runnable object handles the executable code for an ATM object.
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public class ATMRunnable
{
    private ATM atm;
    private PrintStream output;
    private String commandLine;

    /**
     * Default constructor that initiates private variables
     *
     */
    public ATMRunnable(ATM a, PrintStream o, String cl)
    {
        this.atm = a;
        this.output = o;
        this.commandLine = cl;
    }

    /** 
     * This method handles the execution operations.
     *
     * @throws ATMException is thrown when required parameters are not supplied. 
     */
    public void run() throws ATMException
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

        if (command.compareToIgnoreCase("BALANCE") == 0)
        {
            Float result = atm.getBalance();

            if (result != null)
            {
                output.println(result);
            }
        }
        else if (command.compareToIgnoreCase("DEPOSIT") == 0)
        {
            if (commandAndParam.length < 2)
            {
                throw new ATMException("Missing amount for command \"" + command + "\"");
            }

            atm.deposit(Float.parseFloat(commandAndParam[1]));
        }
        else if (command.compareToIgnoreCase("WITHDRAW") == 0)
        {
            if (commandAndParam.length < 2)
            {
                throw new ATMException("Missing amount for command \"" + command + "\"");
            }

            atm.withdraw(Float.parseFloat(commandAndParam[1]));
        }
        else
        {
            throw new ATMException("Unrecognized command: " + command);
        }
    }
}