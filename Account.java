
/**
 * Class:  Account
 * @author : Dr. C. L. Johnson
 * @version: 1.0
 * Course: ITEC 3150, Fall, 2021
 * Written:  August 24, 2021
 * 
 * This class serves as a parent class for the checking account and credit card account classes used in 
 * this program.
 * 
 * Purpose:  This class contains the shared attributes and methods for the two types of accounts including
 * an abstract withdraw method that must be implemented by child classes.
 *
 */
public abstract class Account
{

	// attributes are protected so they can be used by child classes but not other classes
	protected int accountNumber;
	protected String accountHolderName;
	protected String accountType;
	protected double balance;

	/**
	 * Full argument constructor
	 * 
	 * @param accountNumber
	 * @param accountHolderName
	 * @param balance
	 */
	public Account(int accountNumber, String accountHolderName, String type, double balance)
	{
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.accountType = type;
	}

	/**
	 * Getter method for account type
	 * 
	 * @return the accountType
	 */
	public String getAccountType()
	{
		return accountType;
	}

	/**
	 * Getter method for name of account holder
	 * 
	 * @return the accountHolderName
	 */
	public String getAccountHolderName()
	{
		return accountHolderName;
	}

	/**
	 * Setter method for account holder name
	 * 
	 * @param accountHolderName the accountHolderName to set
	 */
	public void setAccountHolderName(String accountHolderName)
	{
		this.accountHolderName = accountHolderName;
	}

	/**
	 * Getter method for account holder
	 * 
	 * @return the accountNumber
	 */
	public int getAccountNumber()
	{
		return accountNumber;
	}

	/**
	 * Getter method for account balance
	 * 
	 * @return the balance
	 */
	public double getBalance()
	{
		return balance;
	}

	/**
	 * Method to handle requests for cash distribution from account
	 * 
	 * @param amount - amount of cash desired
	 * @return - true if able to dispense cash, false otherwise
	 */
	abstract boolean withdraw(double amount);

}
