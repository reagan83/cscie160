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

    /**
     * This constructor initializes the source and message strings
     */
    public TransactionNotification(String s, String m)
    {
        this.notificationSource = s;
        this.notificationMessage = m;
    }

    /**
     * This method returns the source of the TransactionNotification object
     *
     * @return notification source string
     */
    public String getNotificationSource()
    {
        return this.notificationSource;
    }

    /**
     * This method returns the message of the TransactionNotification object
     *
     * @return notification message string
     */
    public String getNotificationMessage()
    {
        return this.notificationMessage;
    }
}