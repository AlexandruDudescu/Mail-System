import java.util.ArrayList;

/**
 * Class to validate the inputs of the user.
 * @author Reed Schoon
 *
 */

public class ValidateServices 
{
	/**
	 * Validates an account to make sure it is unique.
	 * @author Reed Schoon
	 * @param account
	 * @return true if account is unique, false if account is not unique
	 */
	public static boolean ValidateServer( ArrayList<Account> accountClone, String username, String serverName )
	{
		return true;
	}

	/**
	 * Validates a username to make sure it is unique.
	 * @author Reed Schoon
	 * @param account
	 * @return true if account is unique, false if account is not unique
	 */	
	public static boolean ValidateUsername( ArrayList<Account> accountClone, String username ) 
	{
		for( int i = 0; i < accountClone.size(); i++ )
		{
			if( accountClone.get(i).getEmailName().equals(username) )
			{
				return false;
			}
		}
		return true;
	}
	
}