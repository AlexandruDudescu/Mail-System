import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Collection of functions to manage and interact with the tree view.
 * @author Alexandru Dudescu
 *
 */
public class GUIAccountTreeManager 
{
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
		newAccount.add(new DefaultMutableTreeNode("local"));
		newAccount.add(new DefaultMutableTreeNode("remote"));
		
		return newAccount;
	}
	
	/**
	 * Removes the indicated email node, and updates the model. If the email is in Inbox or sent
	 * move it to trah. If it is in trash, discard it permanently
	 * @param currentNode
	 * @param model
	 */
	public static void DeleteEmail(DefaultMutableTreeNode currentNode, DefaultTreeModel model)
	{
		//Check if email
		if(currentNode.getLevel() != 5)
			return;
		
		
		DefaultMutableTreeNode parentFolder = (DefaultMutableTreeNode) currentNode.getParent();
		
		//Check if not in trash
		if(!parentFolder.getUserObject().toString().equals("Trash"))
		{
			DefaultMutableTreeNode trashNode = searchChildByName("Trash", (DefaultMutableTreeNode) parentFolder.getParent());
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(currentNode.getUserObject());
			
			model.insertNodeInto(newNode, trashNode, trashNode.getChildCount());
			try
			{
				model.removeNodeFromParent(currentNode);
			}
			catch(Exception e)
			{
				
			}
		}
		else
		{
			try
			{
				model.removeNodeFromParent(currentNode);
			}
			catch(Exception e)
			{
				
			}
		}
	}
}