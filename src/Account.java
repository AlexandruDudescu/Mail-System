import java.util.ArrayList;

/**
 * Account model class
 * @author Kevin Wang
 *
 */

public class Account 
{
	public Account(String name)
	{
		local = new ArrayList<EmailAddress>();
		remote = new ArrayList<EmailAddress>();
		
		emailName = name;
		
		// ----- For debbuging purpose only -----
		EmailAddress localAddress1 = new EmailAddress("local.com");
		EmailAddress remoteAddress1 = new EmailAddress("remote.com");
		
		local.add(localAddress1);
		remote.add(remoteAddress1);
		// --------------------------------------
	}
	
	public String getEmailName()
	{
		return emailName;
	}
	
	public void AddLocalAddress(EmailAddress newAddres)
	{
		local.add(newAddres);
	}
	
	public void AddRemoteAddress(EmailAddress remoteAddress)
	{
		remote.add(remoteAddress);
	}
	
	public ArrayList<EmailAddress> getLocalAdresses()
	{
		return (ArrayList<EmailAddress>) local.clone();
	}
	
	public ArrayList<EmailAddress> getRemoteAdresses()
	{
		return (ArrayList<EmailAddress>) remote.clone();
	}
	
	private String emailName;
	private ArrayList<EmailAddress> local;
	private ArrayList<EmailAddress> remote;
}
