import java.util.Date;

/**
 * Model Class for an email 
 * @author Reed Schoon
 *
 */

public class Email 
{
	private EmailAddress destinationAddress;
	private EmailAddress senderAddress;
	private String subject;
	private String body;
	private Date timeStamp;
	
	public Email()
	{
		
	}
	
	public Email(EmailAddress destinationAddress, EmailAddress senderAddress, String subject, String body, Date timeStamp)
	{
		this.destinationAddress = destinationAddress;
		this.senderAddress = senderAddress;
		this.subject = subject;
		this.body = body;
		this.timeStamp = timeStamp;
	}
	
	public EmailAddress getDestinationAddress() { return destinationAddress; }
	public void setDestinationAddress(EmailAddress destinationAddress) { this.destinationAddress = destinationAddress; }
	
	public String getSubject() { return subject; }
	public void setSubject(String subject) { this.subject = subject; }
	
	public EmailAddress getSenderAddress() { return senderAddress; }
	public void setSenderAddress(EmailAddress senderAddress) { this.senderAddress = senderAddress; }
	
	public Date getTimeStamp() { return timeStamp; }
	public void setTimeStamp(Date timeStamp) { this.timeStamp = timeStamp; }
	
	public String getBody() { return body; }
	public void setBody(String body) { this.body = body; }
	
	@Override
	public String toString() { return subject; }
	
	/**
	 * Creates and returns a string with the content of the email
	 */
	public String getContent()
	{
		String email = "";
		
		String destinationAddressString = destinationAddress.getAccount().toString() + "@" + destinationAddress.getServerDomain();
		String senderAddressString = senderAddress.getAccount().toString() + "@" + senderAddress.getServerDomain();
		
		email += "To: " + destinationAddressString + "\n";
		email += "From: " + senderAddressString + "\n";
		email += "Time Stamp: " + this.timeStamp + "\n\n";
		
		email += "Subject: " + this.subject + "\n\n";
		
		email += this.body;
		
		return email;
	}
}