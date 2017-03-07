import java.util.Date;
import java.util.Queue;

public class EmailManager 
{
	public Email CreateEmail(EmailAddress destinationAddress, EmailAddress senderAddress, String subject, String body, Date timeStamp)
	{
		Email newEmail = new Email(destinationAddress, senderAddress, subject, body, timeStamp);
		
		return newEmail;
	}
	
	public void sendEmail(Email email)
	{
		Queue<Email> destinationInbox = email.getDestinationAddress().getInbox();
		Queue<Email> senderSend = email.getSenderAddress().getSent();
		
		destinationInbox.add(email);
		senderSend.add(email);
	}
	
	public void deleteEmail(Email email)
	{
		Queue<Email> inbox = email.getDestinationAddress().getInbox();
		Queue<Email> trash = email.getDestinationAddress().getTrash();
		
		if(inbox.contains(email))
		{
			inbox.remove(email);
			trash.add(email);
		}
		
		if(trash.contains(email))
		{
			trash.remove(email);
		}
	}
}
