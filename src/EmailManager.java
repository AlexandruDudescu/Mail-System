import java.util.Date;

public class EmailManager 
{
	public Email CreateEmail(EmailAddress destinationAddress, EmailAddress senderAddress, String Subject, String Body, Date TimeStamp)
	{
		Email newEmail = new Email(destinationAddress, senderAddress, Subject, Body, TimeStamp);
		
		return newEmail;
	}
	
	public void sendEmail()
	{
		
	}
	
	public void deleteEmail()
	{
		
	}
}
