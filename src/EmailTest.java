import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * JUnit test for Email Class
 * @author Reed Schoon
 *
 */

public class EmailTest extends TestCase
{

	@Test
	public void testEmailConstructor() 
	{
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		String tempSubject = "JUNIT TEST";
		String tempBody = "Working";
		Date now = new Date();
		
		String subjectTemp = "TEST JUNIT";
		String bodyTemp = "TEST JUNIT";
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempSendEmail, tempDestEmail, subjectTemp, bodyTemp, now);
		
		assertNotEquals(testEmail.getSubject(), testEmail2.getSubject());
		assertNotEquals(testEmail.getBody(), testEmail2.getBody());
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGetDestinationAddress()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
		
		String subjectTemp = "TEST JUNIT";
		String bodyTemp = "TEST JUNIT";
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, subjectTemp, bodyTemp, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, tempBody, now);
		
		assertEquals(testEmail.getDestinationAddress(), testEmail2.getDestinationAddress());
		assertNotEquals(testEmail.getDestinationAddress(), parameterEmail.getDestinationAddress());
	}
	
	@Test
	public void testSetDestinationAddress()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, tempBody, now);
		
		testEmail.setDestinationAddress(tempSendEmail); 
		testEmail2.setDestinationAddress(tempSendEmail);
		
		parameterEmail.setDestinationAddress(tempSendEmail);
		
		assertEquals(testEmail.getDestinationAddress(), testEmail2.getDestinationAddress());
		assertEquals(testEmail.getDestinationAddress(), parameterEmail.getDestinationAddress());
	}
	
	@Test
	public void testGetSubject()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = "Testing!";
		String parameterEmailSubject = "Different!";
		String tempBody = null;
		Date now = new Date();
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, parameterEmailSubject, tempBody, now);
		
		assertEquals(testEmail.getSubject(), testEmail2.getSubject());
		assertNotEquals(testEmail.getSubject(), parameterEmail.getSubject());
	}
	
	public void testSetSubject()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, tempBody, now);
		
		testEmail.setSubject("ABC");
		testEmail2.setSubject("ABC");
		parameterEmail.setSubject("DEF");
		
		assertEquals(testEmail.getSubject(), testEmail2.getSubject());
		assertNotEquals(testEmail.getSubject(), parameterEmail.getSubject());
	}
	
	public void testGetSenderAddress()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		EmailAddress tempSendEmail2 = new EmailAddress("remote", tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail2, tempSubject, tempBody, now);
		
		assertEquals(testEmail.getSenderAddress(), testEmail2.getSenderAddress());
		assertNotEquals(testEmail.getSenderAddress(), parameterEmail.getSenderAddress());
	}
	
	public void testSetSenderAddress()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, tempBody, now);
		
		testEmail.setSenderAddress(tempDestEmail);
		testEmail2.setSenderAddress(tempDestEmail);
		parameterEmail.setSenderAddress(tempDestEmail2);		
		
		assertEquals(testEmail.getSenderAddress(), testEmail2.getSenderAddress());
		assertNotEquals(testEmail.getSenderAddress(), parameterEmail.getSenderAddress());
	}
	
	public void testGetTimeStamp()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		long randomNum = 100000;
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
		
		Date before = new Date(randomNum);
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, tempBody, before);
		
		assertEquals(testEmail.getTimeStamp(), testEmail2.getTimeStamp());
		assertNotEquals(testEmail.getTimeStamp(), parameterEmail.getTimeStamp());
	}
	
	public void testSetTimeStamp()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		long randomNum = 100000;
		long asdf = 1000000000;
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		Date now = new Date();
		Date future = new Date(asdf);
		Date past = new Date(randomNum);
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, tempBody, now);
		
		testEmail.setTimeStamp(past);
		testEmail2.setTimeStamp(past);
		parameterEmail.setTimeStamp(future);
	
		assertEquals(testEmail.getTimeStamp(), testEmail2.getTimeStamp());
		assertNotEquals(testEmail.getTimeStamp(), parameterEmail.getTimeStamp());
	}
	
	public void testGetBody()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		String bodyTemp = "Patrick Star";
		Date now = new Date();
	
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, bodyTemp, now);
		
		assertEquals(testEmail.getBody(), testEmail2.getBody());
		assertNotEquals(testEmail.getBody(), parameterEmail.getBody());
	}
	
	public void testSetBody()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		String bodyTemp = "Patrick Star";
		Date now = new Date();
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, bodyTemp, now);
		
		testEmail.setBody("Everything is awesome");
		testEmail2.setBody("When you're part of a team");

		assertEquals("Everything is awesome", testEmail.getBody());
		assertEquals("When you're part of a team", testEmail2.getBody());
	}
	
	public void testToString()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress();
		EmailAddress tempSendEmail = new EmailAddress();
		EmailAddress tempDestEmail2 = new EmailAddress(serverDomain, tempAccount);
		
		String tempSubject = null;
		String tempBody = null;
		String bodyTemp = "Patrick Star";
		Date now = new Date();
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, bodyTemp, now);
		
		parameterEmail.setSubject("Different");

		assertEquals(testEmail.toString(), testEmail2.toString());
		assertEquals("Different", parameterEmail.toString());
	}
	
	public void testGetContent()
	{
		Account tempAccount = new Account("SpongeBob SquarePants");
		Account tempAccount2 = new Account("Mr.Krabs");
		String serverDomain = "local";
		
		EmailAddress tempDestEmail = new EmailAddress(serverDomain, tempAccount);
		EmailAddress tempSendEmail = new EmailAddress(serverDomain, tempAccount);
		EmailAddress tempDestEmail2 = new EmailAddress("remote", tempAccount2);
		
		String tempSubject = null;
		String tempBody = null;
		String bodyTemp = "Patrick Star";
		Date now = new Date();
		
		Email testEmail = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email testEmail2 = new Email(tempDestEmail, tempSendEmail, tempSubject, tempBody, now);
		Email parameterEmail = new Email(tempDestEmail2, tempSendEmail, tempSubject, bodyTemp, now);
		
		assertEquals(testEmail.getContent(), testEmail.getContent());
		assertNotEquals(testEmail.getContent(), parameterEmail.getContent());
	}
}
