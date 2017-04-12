import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class GUIAccountTreeManager 
{
	public static void addUserDataToTree(DefaultMutableTreeNode root)
    {
		DefaultMutableTreeNode accountNode = null;
		DefaultMutableTreeNode branchNode = null;
        DefaultMutableTreeNode emailAddressNode = null;
	    DefaultMutableTreeNode emailAddressFolder = null;
		  
		ArrayList<Account> accounts = AccountManager.getAccountList();
		ArrayList<EmailAddress> local;
		ArrayList<EmailAddress> remote;
		  
		for(Account account : accounts)
		{ 
			accountNode = new DefaultMutableTreeNode(account.getEmailName());
			  
			local = account.getLocalAddresses();
			remote = account.getRemoteAddresses();
			  
			//Get emailAddresses from the local branch
			branchNode = new DefaultMutableTreeNode("local");
			  
			for(EmailAddress emailAddress : local)
			{
				//Get emails for each folder
				emailAddressNode = new DefaultMutableTreeNode(emailAddress.getServerDomain());
				  
				//Inbox
				emailAddressFolder = getEmailListNode(emailAddress.getInbox(), "Inbox");
				emailAddressNode.add(emailAddressFolder);
			  
				//Sent
				emailAddressFolder = getEmailListNode(emailAddress.getSent(), "Sent");
				emailAddressNode.add(emailAddressFolder);
				  
				//Trash
				emailAddressFolder = getEmailListNode(emailAddress.getTrash(), "Trash");
				emailAddressNode.add(emailAddressFolder);
			  
				if(emailAddressNode != null)
					branchNode.add(emailAddressNode);
			}
			  
			accountNode.add(branchNode);
			  
			//Get emailAddresses from the remote branch
			branchNode = new DefaultMutableTreeNode("remote");
			  
			for(EmailAddress emailAddress : remote)
			{
				//Get emails for each folder
				emailAddressNode = new DefaultMutableTreeNode(emailAddress.getServerDomain());
				  
				//Inbox
				emailAddressFolder = getEmailListNode(emailAddress.getInbox(), "Inbox");
				emailAddressNode.add(emailAddressFolder);
				
				//Sent
				emailAddressFolder = getEmailListNode(emailAddress.getSent(), "Sent");
				emailAddressNode.add(emailAddressFolder);
				
				//Trash
				emailAddressFolder = getEmailListNode(emailAddress.getTrash(), "Trash");
				emailAddressNode.add(emailAddressFolder);
				
				if(emailAddressNode != null)
					branchNode.add(emailAddressNode);
			}
			  
			accountNode.add(branchNode);
			  
			root.add(accountNode);
		}
		  
	  }
	  
	  private static DefaultMutableTreeNode getEmailListNode(ArrayList<Email> folder, String name)
	  {
		  DefaultMutableTreeNode folderNode = new DefaultMutableTreeNode(name);
		  DefaultMutableTreeNode emailNode = null;
		  
		  for(Email email : folder)
		  {
			emailNode = new DefaultMutableTreeNode(email.getSubject());
			
			if(emailNode != null)
				folderNode.add(emailNode);
		  }
		  
		  return folderNode;
	  }
}