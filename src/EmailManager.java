import java.util.Date;

public class EmailManager 
{
	public Email CreateEmail(EmailAddress destinationAddress, EmailAddress senderAddress, String subject, String body, Date timeStamp)
	{
		Email newEmail = new Email(destinationAddress, senderAddress, subject, body, timeStamp);
		
		return newEmail;
	}
	
	public void sendEmail()
	{
		
	}
	
	public void deleteEmail()
	{
		
	}
}
