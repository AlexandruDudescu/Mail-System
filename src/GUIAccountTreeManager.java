import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


/**
 * Collection of functions to manage and interact with the tree view.
 * @author Alexandru Dudescu
 *
 */
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
				emailAddressFolder = populateEmailFolder(emailAddress.getInbox(), "Inbox");
				emailAddressNode.add(emailAddressFolder);
			  
				//Sent
				emailAddressFolder = populateEmailFolder(emailAddress.getSent(), "Sent");
				emailAddressNode.add(emailAddressFolder);
				  
				//Trash
				emailAddressFolder = populateEmailFolder(emailAddress.getTrash(), "Trash");
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
				emailAddressFolder = populateEmailFolder(emailAddress.getInbox(), "Inbox");
				emailAddressNode.add(emailAddressFolder);
				
				//Sent
				emailAddressFolder = populateEmailFolder(emailAddress.getSent(), "Sent");
				emailAddressNode.add(emailAddressFolder);
				
				//Trash
				emailAddressFolder = populateEmailFolder(emailAddress.getTrash(), "Trash");
				emailAddressNode.add(emailAddressFolder);
				
				if(emailAddressNode != null)
					branchNode.add(emailAddressNode);
			}
			  
			accountNode.add(branchNode);
			  
			root.add(accountNode);
		}
		  
	  }

	/**
	 * Creates a folder node with the specified name and populates it with the 
	 * Email provided in the folder ArrayList
	 * @param folder ArrayList containing the Email objects
	 * @param name The name of the node to be created
	 * @return The created node
	 */
	private static DefaultMutableTreeNode populateEmailFolder(ArrayList<Email> folder, String name)
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
	
	/**
	 * Search for a child node by its name in the parent nodes. If fount returns the name,
	 * null otherwise.
	 * @param childName name of the child to be searched
	 * @param parentNode the parent node
	 * @return child node or null
	 */
	public static DefaultMutableTreeNode searchChildByName(String childName, DefaultMutableTreeNode parentNode)
	{
		
		DefaultMutableTreeNode childNode = null;
		
		for(int i = 0; i < parentNode.getChildCount(); i++)
		{
			DefaultMutableTreeNode n =  (DefaultMutableTreeNode) parentNode.getChildAt(i);
			
			if(n.getUserObject().toString().equals(childName))
			{
				childNode = n;
				break;
			}
		}
		
		return childNode;
	}
	
	/**
	 * Provided a full email address (Eg. "alex@local.com") searches the root node and returns
	 * the node corresponding to the server domain.
	 * @param addressString
	 * @param root
	 * @return
	 */
	public static DefaultMutableTreeNode getNodeByEmail(String addressString, DefaultMutableTreeNode root)
	{
		String user;
		String serverDomain;
		
		//Get user and server domain
		String splitAddress[] = addressString.split("@");
		
		user = splitAddress[0];
		serverDomain = splitAddress[1];
		
		//Parse the tree and find the node
		DefaultMutableTreeNode accountNode = searchChildByName(user, root);
		
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

	/**
	 * Adds an email object as a node to the specified serverDomainNode
	 * @param emailObject the email object
	 * @param serverDomainNode the server domain node
	 * @param model tree view model
	 * @param folderName the name of the folder the email should belong to
	 */
	public static void addEmailToNode(Object emailObject, DefaultMutableTreeNode serverDomainNode, DefaultTreeModel model, String folderName)
	{	
		DefaultMutableTreeNode folderNode = searchChildByName(folderName, serverDomainNode);
		model.insertNodeInto(new DefaultMutableTreeNode(emailObject), folderNode, folderNode.getChildCount());
	}

	/**
	 * Check to see if the selected node it's an email and retrieve its contents.
	 * @param selectedNode
	 * @return
	 */
	public static String getSelectedEmailContent(DefaultMutableTreeNode selectedNode)
	{

		if(selectedNode.getLevel() != 5)
			return null;
		
		Email selectedEmail = (Email) selectedNode.getUserObject();
		
		return selectedEmail.getContent();
	}
	
	/**
	 * Searches the root node for the account named accountName. Returns the local or remote
	 * branch of the account depending on the isLocal variable
	 * @param accountName The name of the account to be searched
	 * @param isLocal Specifies the required branch.
	 * @param root The root node of the tree
	 * @return Node containing local or remote branch of the account
	 */
	public static DefaultMutableTreeNode getNodeByAccountBranch(String accountName, boolean isLocal, DefaultMutableTreeNode root)
	{

		DefaultMutableTreeNode accountNode = searchChildByName(accountName, root);
		return (DefaultMutableTreeNode) accountNode.getChildAt((isLocal)? 0 : 1);

	}
	
	/**
	 * Creates and returns a domain server node with the specified name
	 * @param newEmailAddressName
	 * @return
	 */
	public static DefaultMutableTreeNode createEmailAddressNode(String newEmailAddressName)
	{
		//Create the new addres node
		DefaultMutableTreeNode newEmailAddress = new DefaultMutableTreeNode(newEmailAddressName);
		
		newEmailAddress.add(new DefaultMutableTreeNode("Inbox"));
		newEmailAddress.add(new DefaultMutableTreeNode("Sent"));
		newEmailAddress.add(new DefaultMutableTreeNode("Trash"));
		
		return newEmailAddress;
	}
	
	/**
	 * Removes the specified email address from the specified accountBranch
	 * @param emailAddress name of the email address to be removed
	 * @param accountBranch the account branch to remove the address from
	 * @param model tree view of the model
	 */
	public static void removeEmailAddressFromNode(String emailAddress, DefaultMutableTreeNode accountBranch, DefaultTreeModel model)
	{
		DefaultMutableTreeNode emailNode = searchChildByName(emailAddress, accountBranch);
		model.removeNodeFromParent(emailNode);

	}

	/**
	 * Creates the structure for an account node, attributes it the specified account name
	 * and returns it.
	 * @param accountName
	 * @return
	 */
	public static DefaultMutableTreeNode createNewAccountNode(String accountName)
	{
		DefaultMutableTreeNode newAccount = new DefaultMutableTreeNode(accountName);
		
		//Generate accountStructure
		newAccount.add(new DefaultMutableTreeNode("Local"));
		newAccount.add(new DefaultMutableTreeNode("Remore"));
		
		return newAccount;
	}

}