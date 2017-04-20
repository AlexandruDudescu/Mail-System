import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * JUnit test for AccountManager Class
 * @author Kevin Wang
 *
 */

public class AccountManagerTest extends TestCase
{

	@Test
	public void testConstructor() 
	{
		AccountManager admin = new AccountManager();
		AccountManager worker = new AccountManager();
		
		ArrayList<Account> tempList = new ArrayList<Account>();
		ArrayList<Account> tempList2 = new ArrayList<Account>();
		
		
		assertEquals(admin.getAccountList(), worker.getAccountList());
	}
	
	public void testGetAccountList()
	{
		AccountManager admin = new AccountManager();
		AccountManager worker = new AccountManager();
		
		assertEquals(admin.getAccountList(), worker.getAccountList());
	}
	
	public void testCreateAccount()
	{
		AccountManager admin = new AccountManager();
		AccountManager worker = new AccountManager();
		
		assertEquals(true, admin.CreateAccount("admin"));
		assertEquals(false, admin.CreateAccount("admin"));
	}
	
	public void testDeleteAccount()
	{
		AccountManager admin = new AccountManager();
		AccountManager worker = new AccountManager();
		
		Account tempAccount = new Account("Kevin");
		ArrayList<Account> tempList = new ArrayList<Account>();
		
		tempList.add(tempAccount);
		
		assertEquals(false, admin.DeleteAccount("Kevin"));
		assertNotEquals(true, worker.DeleteAccount("worker"));
	}
	
	public void testCreateServer()
	{
		AccountManager admin = new AccountManager();
		AccountManager worker = new AccountManager();
		
		assertNotEquals(true, admin.CreateServer("Reed", "local", true));
		assertEquals(false, admin.CreateServer("Alex", "remote", true));
		
	}
	
	
	public void testLogin()
	{
		
	}
}
