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
	
	/**
	 * Function that simulates sending an email. Sender and Recipient
	 * information are included in the email object
	 * @param email Email object to be sent
	 */
	public static void sendEmail(Email email)
	{
		ArrayList<Email> destinationInbox = email.getDestinationAddress().getInbox();
		ArrayList<Email> senderSend = email.getSenderAddress().getSent();
		
		destinationInbox.add(email);
		senderSend.add(email);
		
		//destinationInbox/senderSend won't update unless this happens
		email.getDestinationAddress().setInbox(destinationInbox);
		email.getSenderAddress().setSent(senderSend);
	}
	
	/** 
	 * Function to delete an email existing in the system.
	 * @param email The email to be deleted
	 */
	public static void deleteEmail(Email email)
	{
		ArrayList<Email> inbox = email.getDestinationAddress().getInbox();
		ArrayList<Email> trash = email.getDestinationAddress().getTrash();
		
		if(inbox.contains(email))
		{
			inbox.remove(email);
			trash.add(email);
			
			//inbox/trash won't update unless this happens
			email.getDestinationAddress().setInbox(inbox);
			email.getDestinationAddress().setTrash(trash);
			System.out.println("Moved to trash");
		}
		else if(trash.contains(email))
		{
			trash.remove(email);
			//trash won't update unless this happens
			email.getDestinationAddress().setTrash(trash);
			System.out.println("Deleted from trash");
		}
	}
}