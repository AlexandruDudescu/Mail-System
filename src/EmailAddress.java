import java.util.ArrayList;

/**
 * Model class for an Email address.
 * @author Alexandru Dudescu
 *
 */
public class EmailAddress 
{
	private Account account;
	private String serverDomain;
	private ArrayList<Email> inbox;
	private ArrayList<Email> sent;
	private ArrayList<Email> trash;
	
	public EmailAddress()
	{
		
		inbox = new ArrayList<Email>();
		sent = new ArrayList<Email>();
		trash = new ArrayList<Email>();
				
		Email inboxEmail = new Email();
		Email sentEmail = new Email();
		Email trashEmail = new Email();
	}
	
	public EmailAddress(String serverDomain, Account account)
	{
		inbox = new ArrayList<Email>();
		sent = new ArrayList<Email>();
		trash = new ArrayList<Email>();
		
		this.serverDomain = serverDomain;
		this.account = account;
	}
	
	public Account getAccount() {return account;} 
	
	public String getServerDomain() { return serverDomain; }
	public void setServerDomain(String serverDomain) { this.serverDomain = serverDomain; }
	
	public ArrayList<Email> getInbox() { return (ArrayList<Email>) inbox.clone(); }
	public void setInbox(ArrayList<Email> inbox) { this.inbox = inbox; }
	
	public ArrayList<Email> getSent() { return (ArrayList<Email>) sent.clone(); }
	public void setSent(ArrayList<Email> sent) { this.sent = sent; }
	
	public ArrayList<Email> getTrash() { return (ArrayList<Email>) trash.clone(); }
	public void setTrash(ArrayList<Email> trash) { this.trash = trash; }
}