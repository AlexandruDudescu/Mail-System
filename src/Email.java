import java.util.Date;

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
	
	public EmailAddress getDestinationAddress() 
	{
		return destinationAddress;
	}
	public void setDestinationAddress(EmailAddress destinationAddress) 
	{
		this.destinationAddress = destinationAddress;
	}
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		subject = subject;
	}
	public EmailAddress getSenderAddress() 
	{
		return senderAddress;
	}
	public void setSenderAddress(EmailAddress senderAddress) 
	{
		this.senderAddress = senderAddress;
	}
	public Date getTimeStamp() 
	{
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
	public String getBody() 
	{
		return body;
	}
	public void setBody(String body) 
	{
		body = body;
	}
	
	
}
