package cscie160.hw6;

public class ATMRunnable implements Runnable
{
    ATM atm;
    PrintStream output;

    public ATMRunnable(ATM a, PrintStream o)
    {
        atm = a;
        output = o;
    }

    public static run(String commandLine)
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

        switch (Command.valueOf(command))
        {

        case BALANCE:
            Float result = atm.getBalance();

            if (result != null)
            {
                printStream.println(result);
            }
            break;

        case DEPOSIT:
            if (commandAndParam.length < 2)
            {
                throw new ATMException("Missing amount for command \"" + command + "\"");
            }

            atm.deposit(Float.parseFloat(commandAndParam[1]));

            printStream.prinln("Deposited $" + Float.parseFloat(commandAndParam[1]) + ".");

            break;

        case WITHDRAW:
            if (commandAndParam.length < 2)
            {
                throw new ATMException("Missing amount for command \"" + command + "\"");
            }

            atm.withdraw(Float.parseFloat(commandAndParam[1]));

            printStream.prinln("Withdraw:  $" + Float.parseFloat(commandAndParam[1]) + ".");

            break;

        default:
            throw new ATMException("Unrecognized command: " + command);
            break;

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