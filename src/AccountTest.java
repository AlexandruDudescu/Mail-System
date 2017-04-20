import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * JUnit test for Account Class
 * @author Kevin Wang
 *
 */

public class AccountTest extends TestCase
{

	@Test
	public void testAccountConstructor()
	{
		String tempName1 = "Captain Underpants";
		String tempName2 = "Barrack Obama";
		
		Account test1 = new Account(tempName1);
		Account test2 = new Account(tempName2);
		
		assertEquals(tempName1, test1.getEmailName());
		assertEquals(tempName2, test2.getEmailName());
		
	}
	
	public void testGetEmailName()
	{
		String tempName1 = "Captain Underpants";
		String tempName2 = "Barrack Obama";
		
		Account test1 = new Account(tempName1);
		Account test2 = new Account(tempName2);
		
		assertEquals(tempName1, test1.getEmailName());
		assertNotEquals("fjdiafjdaf", test2.getEmailName());
	}
	
	public void testAddLocalEmailAddress()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		ArrayList<EmailAddress> name1 = new ArrayList<EmailAddress>();
		ArrayList<EmailAddress> name2 = new ArrayList<EmailAddress>();
		
		EmailAddress nameAddress1 = new EmailAddress();
		EmailAddress nameAddress2 = new EmailAddress();
		
		name1.add(nameAddress1);
		name2.add(nameAddress2);
				
		test1.AddLocalAddress(nameAddress1);
		test2.AddLocalAddress(nameAddress2);
		
		assertNotEquals(test1.getLocalAddresses(), test2.getLocalAddresses());
		assertNotEquals(test1.getLocalAddresses(), test2.getLocalAddresses());
	}
	
	public void testRemoveLocalAddress()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		EmailAddress nameAddress1 = new EmailAddress("local", test1);
		EmailAddress nameAddress2 = new EmailAddress();
		
		test1.AddLocalAddress(nameAddress1);
		test2.AddLocalAddress(nameAddress2);
		
		test1.RemoveLocalAddress(0);
		test2.RemoveLocalAddress(0);
		
	}
	
	public void testAddRemoteAddress()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		ArrayList<EmailAddress> name1 = new ArrayList<EmailAddress>();
		ArrayList<EmailAddress> name2 = new ArrayList<EmailAddress>();
		
		EmailAddress nameAddress1 = new EmailAddress();
		EmailAddress nameAddress2 = new EmailAddress();
		
		
		test1.AddRemoteAddress(nameAddress1);
		test2.AddRemoteAddress(nameAddress2);
		
		assertNotEquals(test1.getRemoteAddresses(), test2.getRemoteAddresses());
		assertNotEquals(test1.getRemoteAddresses(), test2.getRemoteAddresses());
	}
	
	public void testRemoveRemoteAddress()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		ArrayList<EmailAddress> name1 = new ArrayList<EmailAddress>();
		ArrayList<EmailAddress> name2 = new ArrayList<EmailAddress>();
		
		EmailAddress nameAddress1 = new EmailAddress();
		EmailAddress nameAddress2 = new EmailAddress();
		
		
	}
	
	public void testGetLocalAddress()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		ArrayList<EmailAddress> name1 = new ArrayList<EmailAddress>();
		ArrayList<EmailAddress> name2 = new ArrayList<EmailAddress>();
		
		EmailAddress nameAddress1 = new EmailAddress("local", test1);
		EmailAddress nameAddress2 = new EmailAddress("remote", test2);
		
		
		name1.add(nameAddress1);
		name2.add(nameAddress2);
		
		assertNotEquals("local", name2.get(0).getServerDomain());
		assertNotEquals("remote", name1.get(0).getServerDomain());
	}
	
	public void testGetRemoteAddress()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		ArrayList<EmailAddress> name1 = new ArrayList<EmailAddress>();
		ArrayList<EmailAddress> name2 = new ArrayList<EmailAddress>();
		
		EmailAddress nameAddress1 = new EmailAddress("local", test1);
		EmailAddress nameAddress2 = new EmailAddress("remote", test2);
		
		
		name1.add(nameAddress1);
		name2.add(nameAddress2);
		
		assertNotEquals("local", name2.get(0).getServerDomain());
		assertNotEquals("remote", name1.get(0).getServerDomain());
	}
	
	public void testToString()
	{
		Account test1 = new Account("TestName1");
		Account test2 = new Account("TestName2");
		
		assertEquals("TestName1", test1.toString());
		assertEquals("TestName2", test2.toString());
	}

}