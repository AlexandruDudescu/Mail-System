import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class to handle the Accounts in the system
 * @author Kevin Wang
 *
 */

public class AccountManager 
{
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
	
	@SuppressWarnings("unchecked")
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
		for( int i = 0; i < accounts.size(); i++ )
		{
			if( accounts.get(i).getEmailName().equals(name) )
			{
				accounts.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public static boolean CreateServer(String userName, String serverName, boolean isLocal)
	{
		if( ValidateServices.ValidateServer( getAccountList(), userName, serverName ) )
		{
			int i = 0;
			for( i = 0; i < accounts.size(); i++ )
			{
				if( accounts.get(i).getEmailName().equals(userName) )
				{
					break;
				}
			}
			
			if( i == accounts.size() )
			{
				return false;
			}
			
			if( isLocal )
			{
				EmailAddress newAddress = new EmailAddress(serverName, accounts.get(i));
				accounts.get(i).AddLocalAddress(newAddress);
			}
			else
			{
				EmailAddress newAddress = new EmailAddress(serverName, accounts.get(i));
				accounts.get(i).AddRemoteAddress(newAddress);					
			}
			
			return true;	
		}
		return false;
	}
	
	public static boolean DeleteServer(String userName, String serverName)
	{
		int i = 0;
		for( i = 0; i < accounts.size(); i++ )
		{
			if( accounts.get(i).getEmailName().equals(userName) )
			{
				break;
			}
		}
		if( i == accounts.size() )
		{
			return false;
		}
		if( accounts.get(i).getEmailName().equals(userName) ) //make sure the for loop found the specified user
		{
			//search for the specified serverName in the local
			for( int k = 0; k < accounts.get(i).getLocalAddresses().size(); k++ )
			{
				if( serverName.equals(accounts.get(i).getLocalAddresses().get(k).getServerDomain()) )
				{
					accounts.get(i).RemoveLocalAddress(k);
					return true;
				}
			}
			//if not in local, search remote
			for( int k = 0; k < accounts.get(i).getRemoteAddresses().size(); k++ )
			{
				if( serverName.equals(accounts.get(i).getRemoteAddresses().get(k).getServerDomain()) )
				{
					accounts.get(i).RemoveRemoteAddress(k);
					return true;
				}
			}					
		}
		else //if not, exit
		{
			return false;	
		}
		
		return false; //only get here if the two for loops didn't return true
	}
	
	public void Login(Account currentAccount)
	{
		
	}

}