import java.util.ArrayList;

/**
 * Class to validate the inputs of the user.
 * @author Reed Schoon
 *
 */

public class ValidateServices 
{
	/**
	 * Validates a server to make sure it is unique.
	 * @author Reed Schoon
	 * @param account
	 * @return true if account is unique, false if account is not unique
	 */
	public static boolean ValidateServer( ArrayList<Account> accountClone, String username, String serverName)
	{
		int i = 0;
		for( i = 0; i < accountClone.size(); i++ )
		{
			if( accountClone.get(i).getEmailName().equals(username) )
			{
				break;
			}
		}
		if( i == accountClone.size() )
		{
			return true;
		}
		
		//find the address to send the email to
		for( int k = 0; k < accountClone.get(i).getLocalAddresses().size(); k++ )
		{
			if( serverName.equals(accountClone.get(i).getLocalAddresses().get(k).getServerDomain()) )
			{
				return false;
			}
		}
		//if not in local, search remote
		for( int k = 0; k < accountClone.get(i).getRemoteAddresses().size(); k++ )
		{
			if( serverName.equals(accountClone.get(i).getRemoteAddresses().get(k).getServerDomain()) )
			{
				return false;
			}
		}							
		return true;
	}

	/**
	 * Validates a user name to make sure it is unique.
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