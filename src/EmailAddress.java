import java.util.ArrayList;

/**
 * Model class for an Email address.
 * @author Alexandru Dudescu
 *
 */
public class EmailAddress 
{
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
		
		// ----- For debbuging purpose only -----
		inboxEmail.setSubject("Inbox email subject");
		sentEmail.setSubject("Sent email subject");
		trashEmail.setSubject("Trash email subject");
		
		inbox.add(inboxEmail);
		sent.add(sentEmail);
		trash.add(trashEmail);
		// --------------------------------------
	}
	
	public EmailAddress(String serverDomain)
	{
		inbox = new ArrayList<Email>();
		sent = new ArrayList<Email>();
		trash = new ArrayList<Email>();
		
		this.serverDomain = serverDomain;
		
		// ----- For debbuging purpose only -----
		Email inboxEmail = new Email();
		Email sentEmail = new Email();
		Email trashEmail = new Email();
		
		inboxEmail.setSubject("Inbox email subject");
		sentEmail.setSubject("Sent email subject");
		trashEmail.setSubject("Trash email subject");
		
		inbox.add(inboxEmail);
		sent.add(sentEmail);
		trash.add(trashEmail);
		// --------------------------------------
	}
	
	public String getServerDomain() { return serverDomain; }
	public void setServerDomain(String serverDomain) { this.serverDomain = serverDomain; }
	
	public ArrayList<Email> getInbox() { return (ArrayList<Email>) inbox.clone(); }
	public void setInbox(ArrayList<Email> inbox) { this.inbox = inbox; }
	
	public ArrayList<Email> getSent() { return (ArrayList<Email>) sent.clone(); }
	public void setSent(ArrayList<Email> sent) { this.sent = sent; }
	
	public ArrayList<Email> getTrash() { return (ArrayList<Email>) trash.clone(); }
	public void setTrash(ArrayList<Email> trash) { this.trash = trash; }
}
