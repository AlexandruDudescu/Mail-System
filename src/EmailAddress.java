import java.util.Queue;

/**
 * Model class for an Email address.
 * @author Alexandru Dudescu
 *
 */
public class EmailAddress 
{
	private String serverDomain;
	private Queue<Email> inbox;
	private Queue<Email> sent;
	private Queue<Email> trash;
	
	public EmailAddress()
	{
		
	}
	
	public EmailAddress(String serverDomain)
	{
		this.serverDomain = serverDomain;
	}
	
	public String getServerDomain() { return serverDomain; }
	public void setServerDomain(String serverDomain) { this.serverDomain = serverDomain; }
	
	public Queue<Email> getInbox() { return inbox; }
	public void setInbox(Queue<Email> inbox) { this.inbox = inbox; }
	
	public Queue<Email> getSent() { return sent; }
	public void setSent(Queue<Email> sent) { this.sent = sent; }
	
	public Queue<Email> getTrash() { return trash; }
	public void setTrash(Queue<Email> trash) { this.trash = trash; }
}
