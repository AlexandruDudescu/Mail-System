import java.util.ArrayList;
import java.util.Date;
/**
 * Controller class to manage Email model flow within the system
 * @author Alexandru Dudescu
 *
 */
public class EmailManager 
{
	/**
	 * Creates and returns a new email.
	 * @param destinationAddress the email address of destination
	 * @param senderAddress the sender's address
	 * @param subject email subject
	 * @param body email body
	 * @param timeStamp time email was sent
	 * @return
	 */
	public static Email CreateEmail(EmailAddress destinationAddress, EmailAddress senderAddress, String subject, String body, Date timeStamp)
	{	
		Email newEmail = new Email(destinationAddress, senderAddress, subject, body, timeStamp);
		
		return newEmail;
	}
	
	public static void sendEmail(Email email)
	{
		ArrayList<Email> destinationInbox = email.getDestinationAddress().getInbox();
		ArrayList<Email> senderSend = email.getSenderAddress().getSent();
		
		destinationInbox.add(email);
		senderSend.add(email);
	}
	
	public void deleteEmail(Email email)
	{
		ArrayList<Email> inbox = email.getDestinationAddress().getInbox();
		ArrayList<Email> trash = email.getDestinationAddress().getTrash();
		
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