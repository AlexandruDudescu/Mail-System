import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
			emailNode = new DefaultMutableTreeNode(email);
			
			if(emailNode != null)
				folderNode.add(emailNode);
		  }
		  
		  return folderNode;
	  }
	
	public static DefaultMutableTreeNode getNodeByEmail(String addressString, DefaultMutableTreeNode root)
	{
		String user;
		String serverDomain;
		
		//Get user and server domain
		String splitAddress[] = addressString.split("@");
		
		user = splitAddress[0];
		serverDomain = splitAddress[1];
		
		//Parse the tree and find the node
		DefaultMutableTreeNode accountNode = null;
		
		for(int i = 0; i < root.getChildCount(); i++)
		{
			DefaultMutableTreeNode n =  (DefaultMutableTreeNode) root.getChildAt(i);
			
			if(n.getUserObject().toString().equals(user))
			{
				accountNode = n;
				break;
			}
		}
		
		if(accountNode == null)
			return null;
		
		boolean addressFound = false;
		DefaultMutableTreeNode serverDomainNode = null;
		
		for(int branch = 0; branch < 2 && !addressFound; branch++)
			for(int addressNumber = 0; addressNumber < accountNode.getChildAt(branch).getChildCount() && !addressFound; addressNumber++)
			{
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) accountNode.getChildAt(branch).getChildAt(addressNumber);
				
				if(n.getUserObject().toString().equals(serverDomain))
				{
					serverDomainNode = n;
					addressFound = true;
				}
			}
		
		return serverDomainNode;
	}

	public static void addEmailToNode(Object emailObject, DefaultMutableTreeNode serverDomainNode, DefaultTreeModel model, String folderName)
	{		
		for(int i = 0; i < serverDomainNode.getChildCount(); i++)
		{
			
			DefaultMutableTreeNode n = (DefaultMutableTreeNode) serverDomainNode.getChildAt(i);
			
			if(n.getUserObject().toString().equals(folderName))
			{
				model.insertNodeInto(new DefaultMutableTreeNode(emailObject), n, n.getChildCount());
			}
		}
	}

	public static String getSelectedEmailBody(DefaultMutableTreeNode selectedNode)
	{

		if(selectedNode.getLevel() != 5)
			return null;
		
		Email selectedEmail = (Email) selectedNode.getUserObject();
		
		return selectedEmail.getContent();
	}

	public static DefaultMutableTreeNode getNodeByAccountBranch(String accountName, boolean isLocal, DefaultMutableTreeNode root)
	{
		
		//Search for the account
		DefaultMutableTreeNode accountNode = null;

		for(int i = 0; i < root.getChildCount(); i++)
		{
			DefaultMutableTreeNode n =  (DefaultMutableTreeNode) root.getChildAt(i);
			
			if(n.getUserObject().toString().equals(accountName))
			{
				accountNode = n;
				break;
			}
		}
		

		return (DefaultMutableTreeNode) accountNode.getChildAt((isLocal)? 0 : 1);

	}
	
	public static DefaultMutableTreeNode createEmailAddressNode(String newEmailAddressName)
	{
		//Create the new addres node
		DefaultMutableTreeNode newEmailAddress = new DefaultMutableTreeNode(newEmailAddressName);
		
		newEmailAddress.add(new DefaultMutableTreeNode("Inbox"));
		newEmailAddress.add(new DefaultMutableTreeNode("Sent"));
		newEmailAddress.add(new DefaultMutableTreeNode("Trash"));
		
		return newEmailAddress;
	}
	
	public static void removeEmailAddressFromNode(String emailAddress, DefaultMutableTreeNode accountBranch)
	{
		DefaultMutableTreeNode emailNode = null;
		
		for(int i = 0; i < accountBranch.getChildCount(); i++)
		{
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) accountBranch.getChildAt(i);
			
			if(child.getUserObject().equals(emailAddress))
			{
				emailNode = child;
				break;
			}
		}
		
		accountBranch.remove(emailNode);
	}

	public static DefaultMutableTreeNode createNewAccountNode(String accountName)
	{
		DefaultMutableTreeNode newAccount = new DefaultMutableTreeNode(accountName);
		
		//Generate accountStructure
		newAccount.add(new DefaultMutableTreeNode("Local"));
		newAccount.add(new DefaultMutableTreeNode("Remore"));
		
		return newAccount;
	}

	public static DefaultMutableTreeNode getAccountNode(String accountName, DefaultMutableTreeNode root)
	{
		DefaultMutableTreeNode accountNode = null;
		
		for(int i = 0; i < root.getChildCount(); i++)
		{
			DefaultMutableTreeNode n =  (DefaultMutableTreeNode) root.getChildAt(i);
			
			if(n.getUserObject().toString().equals(accountName))
			{
				accountNode = n;
				break;
			}
		}
		
		return accountNode;
	}
}