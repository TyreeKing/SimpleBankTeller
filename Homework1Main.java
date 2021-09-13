import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: Homework1Main
 * 
 * 
 *           This class provides main method to start problem and all user
 *           interface.
 * 
 *           Purpose: This class provides a starting point for homework 1 which
 *           simulates a simple ATM
 *
 */
public class Homework1Main
{
	/*
	 * This method reads in account.txt, creates Account objects based on the data,
	 * and populates theList with the new Account objects
	 * 
	 * @param ArrayList<Account>
	 * 
	 * @return ArrayList<Account>
	 */
	public static ArrayList<Account> readFile(ArrayList<Account> theList)
	{
		String fileName = "accounts.txt"; // hard coding file name
		File theFile = null;
		Scanner accountReader = null;

		try
		{
			theFile = new File(fileName);
			accountReader = new Scanner(theFile);
		} catch (FileNotFoundException ex)
		{
			System.out.println("Unable to open account file- continuing with empty list");
			return theList;
		}

		// file opened- now let's read from it
		try
		{
			while (accountReader.hasNextLine()) // loop until we get to end of the file
			{

				// read first name
				String accountNumString = accountReader.nextLine();
				int accountNum = Integer.parseInt(accountNumString);
				String accountName = accountReader.nextLine();
				String accountType = accountReader.nextLine();
				String balanceString = accountReader.nextLine();
				double balance = Double.parseDouble(balanceString);

				if (accountType.equalsIgnoreCase("credit card"))
				{
					// read additional credit limit attribute
					String limitString = accountReader.nextLine();
					double creditLimit = Double.parseDouble(limitString);

					// create credit card object and add to list
					Credit c = new Credit(accountNum, accountName, accountType, balance, creditLimit);
					theList.add(c);
				} else
				{
					// it is checking account just create and add to list
					Checking c = new Checking(accountNum, accountName, accountType, balance);
					theList.add(c);
				}

			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Problem reading the account file- continuing with what was read");
		} finally
		{
			accountReader.close();
		}
		return theList;
	}

	/*
	 * This method takes the Account objects in theList and converts them to the
	 * proper format for writing in to the account.txt file. Note existing contents
	 * of account.txt are overwritten.
	 * 
	 * @param theList
	 */
	public static void writeFile(ArrayList<Account> theList)
	{
		// variable for file name
		String fileName = "accounts.txt";

		// variable to hold info about the File
		File theFile;

		// PrintWriter object to open for writing to file
		PrintWriter outputFile = null;

		try
		{
			theFile = new File(fileName);
			outputFile = new PrintWriter(theFile);
		} catch (FileNotFoundException ex)
		{
			System.out.println("Unable to write account.txt in default location- check file permissions and try again");
			// print stack trace for debugging - remove when program is working properly
			ex.printStackTrace();
		}

		// now write to and close the file
		try
		{
			// loop through the entries in ArrayList
			for (int i = 0; i < theList.size(); i++)
			{
				// get a local variable to shorten typing
				Account c = theList.get(i);

				// format of file = accountNumber, name, address - each on own line
				outputFile.println(c.getAccountNumber());
				outputFile.println(c.getAccountHolderName());
				outputFile.println(c.getAccountType());
				outputFile.println(c.getBalance());
				if (c.getAccountType().equalsIgnoreCase("credit card"))
				{
					// cast c to credit type
					Credit cc = (Credit) c;
					outputFile.println(cc.getCreditLimit());
				}
			}
		} catch (Exception ex) // using exception instead of specific exception because many possible wrong
								// things could happen
		{
			ex.printStackTrace(); // print the stack trace for debug purposes.
		} finally
		{
			outputFile.close();
		}
	}

	public static Account findAccount(int num, ArrayList<Account> list)
	{

		for (Account a : list)
		{
			if (a.getAccountNumber() == num)
			{
				return a;
			}
		}

		return null;
	}

	/**
	 * Main method - starting point of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner keyInput = new Scanner(System.in);
		// create ArrayList of accounts and read in accounts from accounts.txt
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts = readFile(accounts);

		// now prompt user for inputs and handle withdrawals from various accounts
		int userInput = 1;
		do
		{
			System.out.println("Enter account number:");
			int accountNum = keyInput.nextInt();

			// check for account in arrayList
			Account a = findAccount(accountNum, accounts);
			
			if  (a == null)
			{
				System.out.println("Account unknown- try again");
				continue;  // this will terminate this loop of the do/while
			}
			
			// if get here- will continue
			System.out.println("Please enter amount of cash desired");
			double amount = keyInput.nextDouble();
			
			boolean success = a.withdraw(amount);
			
			if (success)
			{
				System.out.println("Retrieve cash from slot - Your new balance is $" + a.getBalance());
			}
			else
			{
				System.out.println("Insufficient funds - try another account");
			}
			
			System.out.println("Do you want to continue? (1 for yes)");
			userInput = keyInput.nextInt();

		} while (userInput == 1);
		writeFile(accounts);
	}

}
