import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager 
{
	public AccountManager()
	{
		local = new ArrayList<Account>();
		remote = new ArrayList<Account>();
	}
	
	public void CreateUser(Account newAccount)
	{
		System.out.println("Press 1 to add to local address\n");
		System.out.println("Press 2 to add to remote address\n");
		
		sc = new Scanner(System.in);
		int i = sc.nextInt();
		if(i == 1)
		{
			local.add(newAccount);
		}
		else if(i == 2)
		{
			remote.add(newAccount);
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
	
	private ArrayList<Account> local;
	private ArrayList<Account> remote;
	private Scanner sc;
}
