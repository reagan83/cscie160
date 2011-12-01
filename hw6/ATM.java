package cscie160.hw6;

/**
 * ATM Interface
 * 
 * @author Reagan Williams
 * @version 1.6 (hw6)
 * @since 2011-12-02
 */
public interface ATM 
{
	public void deposit(float amount) throws ATMException;
	public void withdraw(float amount) throws ATMException;
	public Float getBalance() throws ATMException;
}