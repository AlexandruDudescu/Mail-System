import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * JUnit test for EmailAddress Class
 * @author Alexandru Dudescu
 *
 */

public class EmailAddressTest extends TestCase
{

	@Test
	public void testConstructor() 
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		assertEquals(addressTest1.getServerDomain(), addressTest2.getServerDomain());
		assertNotEquals(addressTest1, addressTest2);		
		
	}
	
	public void testGetAccount()
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		assertEquals(tempAccount, parameterTest.getAccount());
		assertNotEquals(tempAccount, addressTest1.getAccount());
	}
	
	public void testGetServerDomain()
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		assertEquals(addressTest1.getServerDomain(), addressTest2.getServerDomain());
		assertEquals("local", parameterTest.getServerDomain());
	}
	
	public void testSetServerDomain()
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		addressTest1.setServerDomain("local");
		addressTest2.setServerDomain("remote");
		
		assertEquals("local", addressTest1.getServerDomain());
		assertNotEquals(addressTest1.getServerDomain(), addressTest2.getServerDomain());
	}
	
	public void testGetInbox()
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		assertEquals(addressTest1.getInbox(), parameterTest.getInbox());
		assertEquals(addressTest1.getInbox(), addressTest2.getInbox());
	}
	
	public void testSetInbox()
	{
		ArrayList<Email> tempEmail = new ArrayList<Email>();
		ArrayList<Email> tempEmail2 = new ArrayList<Email>();
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		addressTest1.setInbox(tempEmail);
		addressTest2.setInbox(tempEmail);
		parameterTest.setInbox(tempEmail2);
		
		assertEquals(addressTest1.getInbox(), parameterTest.getInbox());
		assertEquals(addressTest1.getInbox(), addressTest2.getInbox());
		
	}
	
	public void testGetSent()
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		assertEquals(addressTest1.getSent(), parameterTest.getSent());
		assertEquals(addressTest1.getSent(), addressTest2.getSent());
	}
	
	public void testSetSent()
	{
		ArrayList<Email> tempEmail = new ArrayList<Email>();
		ArrayList<Email> tempEmail2 = new ArrayList<Email>();
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		addressTest1.setSent(tempEmail);
		addressTest2.setSent(tempEmail);
		parameterTest.setSent(tempEmail2);
		
		assertEquals(addressTest1.getSent(), parameterTest.getSent());
		assertEquals(addressTest1.getSent(), addressTest2.getSent());
	}
	
	public void testGetTrash()
	{
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
				
		assertEquals(addressTest1.getTrash(), parameterTest.getTrash());
		assertEquals(addressTest1.getTrash(), addressTest2.getTrash());
	}
	
	public void testSetTrash()
	{
		ArrayList<Email> tempEmail = new ArrayList<Email>();
		ArrayList<Email> tempEmail2 = new ArrayList<Email>();
		Account tempAccount = new Account("George Washington");
		
		EmailAddress addressTest1 = new EmailAddress();
		EmailAddress addressTest2 = new EmailAddress();
		EmailAddress parameterTest = new EmailAddress("local", tempAccount);
		
		addressTest1.setTrash(tempEmail);
		addressTest2.setTrash(tempEmail);
		parameterTest.setTrash(tempEmail2);
		
		assertEquals(addressTest1.getTrash(), addressTest2.getTrash());
		assertEquals(addressTest2.getTrash(), parameterTest.getTrash());
	}
}
