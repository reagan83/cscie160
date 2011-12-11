package cscie160.project;

import java.util.HashMap;

/**
 * Security class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-07
 */
public class Security
{
    private HashMap ai;

    public Security()
    {
        ai = new HashMap();
        ai.put(1, new AccountInfo(1, 1234));
        ai.put(2, new AccountInfo(2, 2345));
        ai.put(3, new AccountInfo(3, 3456));
    }

    public boolean authenticate(AccountInfo aiobj)
    {
        for (AccountInfo item : ai.values())
        {
            if (aiobj.equals(item))
            {
                return true;
            }
        }

        return false;
    }
}
