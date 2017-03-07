import java.util.Date;

public class Email 
{
	private EmailAddress destinationAddress;
	private EmailAddress senderAddress;
	private String Subject;
	private String Body;
	private Date timeStamp;
	
	public Email()
	{
		
	}
	
	public Email(EmailAddress destinationAddress, EmailAddress senderAddress, String Subject, String Body, Date TimeStamp)
	{
		this.destinationAddress = destinationAddress;
		this.senderAddress = senderAddress;
		this.Subject = Subject;
		this.Body = Body;
		this.timeStamp = TimeStamp;
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
		return Subject;
	}
	public void setSubject(String subject) 
	{
		Subject = subject;
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
		return Body;
	}
	public void setBody(String body) 
	{
		Body = body;
	}
	
	
}
