import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * JUnit test for EmailManager Class
 * @author Alexandru Dudescu
 *
 */

public class EmailManagerTest extends TestCase
{

	@Test
	public void testCreateEmail() 
	{
		EmailManager tempManager = new EmailManager();
		
		EmailAddress tempDestAddress = new EmailAddress();
		EmailAddress tempSendAddress = new EmailAddress();
		Date now = new Date();
		
		Email tempEmail = new Email();
		Email tempEmail2 = new Email();
		Email tempEmail3 = new Email();
		
		tempEmail = tempManager.CreateEmail(tempDestAddress, tempSendAddress, "Subject", "Body", now);
		tempEmail2 = tempManager.CreateEmail(tempDestAddress, tempSendAddress, "TestingSubject", "TestingBody", now);
		tempEmail3 = tempManager.CreateEmail(tempDestAddress, tempSendAddress, "Subject", "Body", now);
		
		assertNotEquals(tempEmail, tempEmail2);
		assertNotEquals(tempEmail, tempEmail3);
		
		//fail("Not yet implemented");
	}
	
	public void testSendEmail()
	{
		EmailManager tempManager = new EmailManager();
		
		EmailAddress tempDestAddress = new EmailAddress();
		EmailAddress tempSendAddress = new EmailAddress();
		Date now = new Date();
		
		Email tempEmail = new Email(tempDestAddress, tempSendAddress, "Subject", "Body", now);
		Email tempEmail2 = new Email(tempSendAddress, tempDestAddress, "Blah", "Test", now);
		Email tempEmail3 = new Email();
		
		tempManager.sendEmail(tempEmail);
		tempManager.sendEmail(tempEmail2);
		
		
		assertNotNull(tempEmail.getSenderAddress().getSent());
		assertNotNull(tempEmail2.getDestinationAddress().getInbox());
	}
	
	public void testDeleteEmail()
	{
		EmailManager tempManager = new EmailManager();
		
		EmailAddress tempDestAddress = new EmailAddress();
		EmailAddress tempSendAddress = new EmailAddress();
		Date now = new Date();
		
		Email tempEmail = new Email(tempDestAddress, tempSendAddress, "Subject", "Body", now);
		Email tempEmail2 = new Email(tempSendAddress, tempDestAddress, "Blah", "Test", now);
		Email tempEmail3 = new Email();
		
		tempManager.sendEmail(tempEmail);
		tempManager.sendEmail(tempEmail2);
		
		tempManager.deleteEmail(tempEmail);
		tempManager.deleteEmail(tempEmail2);
		
		assertNotNull(tempEmail.getDestinationAddress().getTrash());
		assertNotNull(tempEmail2.getDestinationAddress().getTrash());
	}

}
