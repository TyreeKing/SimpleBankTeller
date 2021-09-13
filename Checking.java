/**
 * Class: Checking
 * 
 * 
 *           This class inherits from Account and implements checking only
 *           behavior
 * 
 *           Purpose: This class contains an appropriate constructor and a
 *           withdraw method properly showing checking account logic for cash
 *           dispersal
 *
 */
public class Checking extends Account
{

	/**
	 * All arg constructor since parent does not provide no-arg constructor - must
	 * have a constructor
	 * 
	 * @param accountNumber
	 * @param accountHolderName
	 * @param balance
	 */
	public Checking(int accountNumber, String accountHolderName, String type, double balance)
	{
		super(accountNumber, accountHolderName, type, balance);

	}

	@Override
	/**
	 * Method to handle requests for cash distribution from account
	 * 
	 * @param amount - amount of cash desired
	 * @return - true if able to dispense cash, false otherwise
	 */
	public boolean withdraw(double amount)
	{
		if ((balance - amount) >= 0)
		{
			balance = balance - amount;
			return true;
		} else
		{
			return false;
		}

	}

}
