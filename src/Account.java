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
		EmailAddress localAddress1 = new EmailAddress("local.com", this);
		EmailAddress remoteAddress1 = new EmailAddress("remote.com", this);
		
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
	
	public void RemoveLocalAddress(int index)
	{
		local.remove(index);
	}
	
	public void RemoveRemoteAddress(int index)
	{
		remote.remove(index);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<EmailAddress> getLocalAddresses()
	{
		return (ArrayList<EmailAddress>) local.clone();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<EmailAddress> getRemoteAddresses()
	{
		return (ArrayList<EmailAddress>) remote.clone();
	}
	
	@Override
	public String toString()
	{
		return emailName;
	}
	
	private String emailName;
	private ArrayList<EmailAddress> local;
	private ArrayList<EmailAddress> remote;
}