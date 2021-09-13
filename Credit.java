/**
 * Class: Checking
 * 
 * 
 *           This class inherits from Account and implements credit card only
 *           behavior
 * 
 *           Purpose: This class contains an appropriate constructor and a
 *           withdraw method properly showing credit card account logic for cash
 *           dispersal
 *
 */
public class Credit extends Account
{

	private double creditLimit;

	/**
	 * Getter method for credit limit
	 * 
	 * @return the creditLimit
	 */
	public double getCreditLimit()
	{
		return creditLimit;
	}

	public Credit(int accountNumber, String accountHolderName, String type, double balance, double creditLimit)
	{
		super(accountNumber, accountHolderName, type, balance);
		this.creditLimit = creditLimit;
	}

	@Override
	/**
	 * Method to handle requests for cash distribution from account
	 * 
	 * @param amount - amount of cash desired
	 * @return - true if able to dispense cash, false otherwise
	 */
	boolean withdraw(double amount)
	{
		if ((balance + amount) <= creditLimit)
		{
			balance= balance + amount;
			return true;
		}
		return false;
	}

}
