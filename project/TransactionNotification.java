package cscie160.project;

import java.io.Serializable;


/**
 * Transaction Notification class
 * 
 * @author Reagan Williams
 * @version 1.7 (project)
 * @since 2011-12-08
 */

public class TransactionNotification implements java.io.Serializable
{
    private String notificationSource;
    private String notificationMessage;

    public TransactionNotification(String s, String m)
    {
        this.notificationSource = s;
        this.notificationMessage = m;
    }

    public String getNotificationSource()
    {
        return this.notificationSource;
    }

    public String getNotificationMessage()
    {
        return this.notificationMessage;
    }
}