import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class to handle the Accounts in the system
 * @author Kevin Wang
 *
 */

public class AccountManager 
{
	private Scanner sc;
	private static ArrayList<Account> accounts;
	
	public AccountManager()
	{
		accounts = new ArrayList<Account>();
		
		// ----- For debbuging purpose only -----
		Account account1 = new Account("Kevin");
		Account account2 = new Account("Reed");
		Account account3 = new Account("Alex");
		
		accounts.add(account1);
		accounts.add(account2);
		accounts.add(account3);
		// --------------------------------------
	}
	
	public static ArrayList<Account> getAccountList()
	{
		return (ArrayList<Account>) accounts.clone();
	}
	
	public static boolean CreateAccount(String name)
	{
		if( ValidateServices.ValidateUsername( getAccountList(), name) )
		{
			Account newAccount = new Account(name);
			accounts.add(newAccount);
			return true;	
		}
		return false;
	}
	
	public static boolean DeleteAccount(String name)
	{
		int i = 0;
		for( i = 0; i < accounts.size(); i++ )
		{
			if( accounts.get(i).getEmailName().equals(name) )
			{
				accounts.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void CreateUser(Account newAccount)
	{
		System.out.println("Press 1 to add to local address\n");
		System.out.println("Press 2 to add to remote address\n");
		
		sc = new Scanner(System.in);
		int i = sc.nextInt();
		if(i == 1)
		{
			//local.add(newAccount);
		}
		else if(i == 2)
		{
			//remote.add(newAccount);
		}
		else
		{
			System.out.println("Error. Please select 1 or 2\n");
		}
	}
	
	public void DeleteUser()
	{
		
	}
	
	public void Login(Account currentAccount)
	{
		
	}

}