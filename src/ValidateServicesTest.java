import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * JUnit test for ValidateServices Class
 * @author Reed Schoon
 *
 */

public class ValidateServicesTest extends TestCase
{

	@Test
	public void testValidateServer() 
	{
		ValidateServices tempValidate = new ValidateServices();
		ArrayList<Account> tempAccount = new ArrayList<Account>();
		Account temp = new Account("Name");
	
		tempAccount.add(temp);
		
		assertEquals(true, tempValidate.ValidateServer(tempAccount, "Barney", "local"));
		assertNotEquals(false, tempValidate.ValidateServer(tempAccount, "Name", "local"));
		
	}
	
	public void testValidateUserName()
	{
		ValidateServices tempValidate = new ValidateServices();
		ArrayList<Account> tempAccount = new ArrayList<Account>();
		Account temp = new Account("Name");
		
		tempAccount.add(temp);
		
		assertEquals(false, tempValidate.ValidateUsername(tempAccount, "Name"));
		assertEquals(true, tempValidate.ValidateUsername(tempAccount, "Not Here"));
		
	}

}
