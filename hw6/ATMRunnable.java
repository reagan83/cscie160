package cscie160.hw6;

import java.io.PrintStream;
import java.util.StringTokenizer;

public class ATMRunnable
{
    private ATM atm;
    private PrintStream output;
    private String commandLine;

    public ATMRunnable(ATM a, PrintStream o, String cl)
    {
        this.atm = a;
        this.output = o;
        this.commandLine = cl;
    }

    public void run()
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

            output.println("Deposited $" + Float.parseFloat(commandAndParam[1]) + ".");
        }
        else if (command.compareToIgnoreCase("WITHDRAW") == 0)
        {
            if (commandAndParam.length < 2)
            {
                throw new ATMException("Missing amount for command \"" + command + "\"");
            }

            atm.withdraw(Float.parseFloat(commandAndParam[1]));

            output.println("Withdraw:  $" + Float.parseFloat(commandAndParam[1]) + ".");
        }
        else
        {
            throw new ATMException("Unrecognized command: " + command);
        }

        // try
        // {
        // }
        // catch (NumberFormatException nfe)
        // {
        //     throw new ATMException("Unable to make float from input: " + commandAndParam[1]);
        // }
    }
}