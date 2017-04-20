import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

@SuppressWarnings("serial")
public class GUI extends JComponent implements TreeSelectionListener 
{
//Menu variables
	JMenuBar menuBar = null;
	JMenu accountsMenu = null;
	JMenu userMenu = null;
	JMenuItem addAccount = null;
	JMenuItem removeAccount = null;
	JMenuItem addUser = null;
	JMenuItem removeUser = null;
//Frame variables
	JFrame frame = new JFrame("Simple Email - Team Void");
	JFrame composeFrame = null;
	JFrame replyFrame = null;
	JFrame accountMenuFrame = null;
	JFrame userMenuFrame = null;
//Panel variables	
	JPanel buttonPanel = new JPanel();
	JPanel textPanel = new JPanel();
	JPanel labelPanel = new JPanel();	
//Radio Button variables
	JRadioButton accountMenuLocalRadio;
	JRadioButton accountMenuRemoteRadio;
//Button variables	
	JButton accountMenuButton = null;
	JButton userMenuButton = null;
	JButton composeButton = new JButton("Compose");
	JButton replyButton = new JButton("Reply");
	JButton sendButton = new JButton ("Send");
	JButton removeButton = new JButton("Remove");
//Text Field variables
	JTextField accountMenuTextField = new JTextField(30);
	JTextField accountMenuTextField02 = new JTextField(30);
	JTextField userMenuTextField = new JTextField(30);
	JTextField fromTextField = new JTextField(30);
	JTextField toTextField = new JTextField(30);
	JTextField subjectTextField = new JTextField(30);
//Text Area variables
	JTextArea screen = new JTextArea(10, 10);
    JTextArea bodyTextArea = new JTextArea(10,30);
//Tree & Scroll Pane variables
	JTree tree;
	JScrollPane treeView;
	JScrollPane screenScroller = new JScrollPane(screen);
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode currentlySelectedNode;
	DefaultTreeModel model;
	
    Date now = null;
	
	GUI() 
	{
		//Menu code
		menuBar = new JMenuBar();
		accountsMenu = new JMenu("Account");
		userMenu = new JMenu("User");
		addAccount = new JMenuItem("Add Account");
		removeAccount = new JMenuItem("Remove Account");
		addUser = new JMenuItem("Add User");
		removeUser = new JMenuItem("Remove User");
		menuBar.add(accountsMenu);
		menuBar.add(userMenu);
		accountsMenu.add(addAccount);
		accountsMenu.add(removeAccount);
		userMenu.add(addUser);
		userMenu.add(removeUser);
		//Button panel code
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(composeButton);
		buttonPanel.add(replyButton);
		buttonPanel.add(sendButton);
		buttonPanel.add(removeButton);
		buttonPanel.setBackground(Color.lightGray);
		sendButton.setEnabled(false);
		removeButton.setEnabled(false);
		replyButton.setEnabled(false);
		
		//Grid code
		GridLayout gridLayout = new GridLayout(1, 2, 50, 50);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		labelPanel.setLayout(gridLayout);

		screen.setBackground(Color.white);
		screen.setBorder(BorderFactory.createBevelBorder(1));
		
		textPanel.setBorder(new EmptyBorder(10,10,10,10));
		textPanel.setLayout(gridLayout);
		textPanel.add(screenScroller);
		
		bodyTextArea.setLineWrap(true);
		
		//Menu functionality
		addAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if( accountMenuFrame == null )
				{
					accountMenuFrame = new JFrame("Add Account");
					accountMenuButton = new JButton("Add");
					accountMenuLocalRadio = new JRadioButton("Local");
					accountMenuRemoteRadio = new JRadioButton("Remote");
					ButtonGroup radioButtonGroup = new ButtonGroup();
					radioButtonGroup.add(accountMenuLocalRadio);
					radioButtonGroup.add(accountMenuRemoteRadio);
					accountMenuLocalRadio.setSelected(true);
					accountMenuFrame.setLayout(new FormLayout());
					accountMenuFrame.add(new JLabel("User Name:"));
					accountMenuFrame.add(accountMenuTextField);
					accountMenuFrame.add(new JLabel("Email Server:"));
					accountMenuFrame.add(accountMenuTextField02);
					accountMenuFrame.add(new JLabel(""));
					accountMenuFrame.add(accountMenuLocalRadio);
					accountMenuFrame.add(new JLabel(""));
					accountMenuFrame.add(accountMenuRemoteRadio);
					accountMenuFrame.add(new JLabel(""));
					accountMenuFrame.add(accountMenuButton);
					accountMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
					//DO_NOTHING_ON_CLOSE is needed for the following addWindowListener
					accountMenuFrame.setResizable(false);
					accountMenuFrame.pack();
					accountMenuFrame.setVisible(true);
					accountMenuFrame.setAlwaysOnTop(true);
					accountMenuFrame.addWindowListener(new java.awt.event.WindowAdapter() 
					{
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
					    {
					    	accountMenuTextField.setText("");
					    	accountMenuTextField02.setText("");
					    	accountMenuFrame.setVisible(false);
					    	accountMenuFrame.dispose();
					    	accountMenuFrame = null;
					    }
					});
					
					accountMenuButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							if( accountMenuFrame != null )
							{
								if( !accountMenuTextField.getText().isEmpty() && !accountMenuTextField02.getText().isEmpty() )
								{
									boolean isLocal;
									if( accountMenuLocalRadio.isSelected() )
									{
										isLocal = true;
									}
									else //accountMenuRemoteRadio is selected
									{
										isLocal = false;
									}
									
									if( AccountManager.CreateServer(accountMenuTextField.getText(), accountMenuTextField02.getText(), isLocal ))
									{
										//Update GUI 
										DefaultMutableTreeNode AccountBranchNode = GUIAccountTreeManager.getNodeByAccountBranch(accountMenuTextField.getText(), isLocal, root);
										model.insertNodeInto(GUIAccountTreeManager.createEmailAddressNode(accountMenuTextField02.getText()), AccountBranchNode, AccountBranchNode.getChildCount());
										
										accountMenuTextField.setText("");
										accountMenuTextField02.setText("");
										accountMenuFrame.setVisible(false);
										accountMenuFrame.dispose();
										accountMenuFrame = null;
									}
									else
									{
										JOptionPane.showMessageDialog(userMenuFrame, 
									            "That account already exists. Please choose a different account name.", "Error", 
									            JOptionPane.OK_OPTION);
									}
								}
							}
						}
					});
				}
			}
		});
		
		removeAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if( accountMenuFrame == null )
				{
					accountMenuFrame = new JFrame("Remove Account");
					accountMenuButton = new JButton("Remove");
					accountMenuFrame.setLayout(new FormLayout());
					accountMenuFrame.add(new JLabel("User Name:"));
					accountMenuFrame.add(accountMenuTextField);
					accountMenuFrame.add(new JLabel("Email Server:"));
					accountMenuFrame.add(accountMenuTextField02);
					accountMenuFrame.add(new JLabel(""));
					accountMenuFrame.add(accountMenuButton);
					accountMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
					//DO_NOTHING_ON_CLOSE is needed for the following addWindowListener
					accountMenuFrame.setResizable(false);
					accountMenuFrame.pack();
					accountMenuFrame.setVisible(true);
					accountMenuFrame.setAlwaysOnTop(true);
					accountMenuFrame.addWindowListener(new java.awt.event.WindowAdapter() 
					{
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
					    {
					    	accountMenuTextField.setText("");
					    	accountMenuTextField02.setText("");
					    	accountMenuFrame.setVisible(false);
					    	accountMenuFrame.dispose();
					    	accountMenuFrame = null;
					    }
					});
					
					accountMenuButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							if( accountMenuFrame != null )
							{
								if( !accountMenuTextField.getText().isEmpty() && !accountMenuTextField02.getText().isEmpty() )
								{
									if( AccountManager.DeleteServer(accountMenuTextField.getText(), accountMenuTextField02.getText() ))
									{
										//Update GUI 
										DefaultMutableTreeNode AccountBranchNode = GUIAccountTreeManager.getNodeByAccountBranch(accountMenuTextField.getText(), true, root);
										GUIAccountTreeManager.removeEmailAddressFromNode(accountMenuTextField02.getText(), AccountBranchNode, model);
										
										accountMenuTextField.setText("");
										accountMenuTextField02.setText("");
										accountMenuFrame.setVisible(false);
										accountMenuFrame.dispose();
										accountMenuFrame = null;
										
										//Debug -- outputs a list of all accounts
										System.out.println("------------------------------------");
										for( int i = 0; i < AccountManager.getAccountList().size(); i++ )
										{
											AccountManager.getAccountList().get(i).getLocalAddresses();
											for( int j = 0; j < AccountManager.getAccountList().get(i).getLocalAddresses().size(); j++ )
											{
												System.out.println(AccountManager.getAccountList().get(i).getLocalAddresses().get(j).getAccount() + "@" + AccountManager.getAccountList().get(i).getLocalAddresses().get(j).getServerDomain() );
											}
										}
									}
									else
									{
										JOptionPane.showMessageDialog(userMenuFrame, 
									            "Cannot find the specified account.", "Error", 
									            JOptionPane.OK_OPTION);
									}
								}
							}
						}
					});
				}
			}
		});
		
		addUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if( userMenuFrame == null )
				{
					userMenuFrame = new JFrame("Add User");
					userMenuButton = new JButton("Add");
					userMenuFrame.setLayout(new FormLayout());
					userMenuFrame.add(new JLabel("User Name:"));
					userMenuFrame.add(userMenuTextField);
					userMenuFrame.add(new JLabel(""));
					userMenuFrame.add(userMenuButton);
					userMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
					//DO_NOTHING_ON_CLOSE is needed for the following addWindowListener
					userMenuFrame.setResizable(false);
					userMenuFrame.pack();
					userMenuFrame.setVisible(true);
					userMenuFrame.setAlwaysOnTop(true);
					userMenuFrame.addWindowListener(new java.awt.event.WindowAdapter() 
					{
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
					    {
					    	userMenuTextField.setText("");
					    	userMenuFrame.setVisible(false);
					    	userMenuFrame.dispose();
					    	userMenuFrame = null;
					    }
					});
					
					userMenuButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							if( userMenuFrame != null )
							{
								if( !userMenuTextField.getText().isEmpty() )
								{
									if( AccountManager.CreateAccount(userMenuTextField.getText()) )
									{
										//GUI logic
										DefaultMutableTreeNode newAccount = GUIAccountTreeManager.createNewAccountNode(userMenuTextField.getText());
										model.insertNodeInto(newAccount, root, root.getChildCount());
										
										userMenuTextField.setText("");
										userMenuFrame.setVisible(false);
								    	userMenuFrame.dispose();
								    	userMenuFrame = null;
									}
									else
									{
										JOptionPane.showMessageDialog(userMenuFrame, 
									            "That user name already exists. Please choose a different user name.", "Error", 
									            JOptionPane.OK_OPTION);
									}
								}
							}
						}
					});
				}
			}
		});
		
		removeUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if( userMenuFrame == null )
				{
					userMenuFrame = new JFrame("Remove User");
					userMenuButton = new JButton("Remove");
					userMenuFrame.setLayout(new FormLayout());
					userMenuFrame.add(new JLabel("User Name:"));
					userMenuFrame.add(userMenuTextField);
					userMenuFrame.add(new JLabel(""));
					userMenuFrame.add(userMenuButton);
					userMenuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
					//DO_NOTHING_ON_CLOSE is needed for the following addWindowListener
					userMenuFrame.setResizable(false);
					userMenuFrame.pack();
					userMenuFrame.setVisible(true);
					userMenuFrame.setAlwaysOnTop(true);
					userMenuFrame.addWindowListener(new java.awt.event.WindowAdapter() 
					{
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
					    {
					    	userMenuTextField.setText("");
					    	userMenuFrame.setVisible(false);
					    	userMenuFrame.dispose();
					    	userMenuFrame = null;
					    }
					});
					
					userMenuButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							if( userMenuFrame != null )
							{
								if( !userMenuTextField.getText().isEmpty() )
								{
									if( AccountManager.DeleteAccount(userMenuTextField.getText()) )
									{
										DefaultMutableTreeNode accountToDelete = GUIAccountTreeManager.searchChildByName(userMenuTextField.getText(), root);
										model.removeNodeFromParent(accountToDelete);
										
										userMenuTextField.setText("");
										userMenuFrame.setVisible(false);
								    	userMenuFrame.dispose();
								    	userMenuFrame = null;
									}
									else
									{
										JOptionPane.showMessageDialog(userMenuFrame, 
									            "Cannot find the specified user name.", "Error", 
									            JOptionPane.OK_OPTION);
									}
								}
							}
						}
					});
				}
			}
		});
		
		//Button functionality
		composeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if( composeFrame != null ) //if frame is open
				{
					//close frame
					composeFrame.setVisible(false);
					composeFrame.dispose();
					composeFrame = null;
				}
						
				if( replyFrame != null ) //if frame is open
				{
					//close frame
					replyFrame.setVisible(false);
					replyFrame.dispose();	
					replyFrame = null;
				}
				//clear text fields
	        	fromTextField.setText("");
	        	toTextField.setText("");
	        	subjectTextField.setText("");
	        	bodyTextArea.setText("");
	        	
	        	//create new frame
				composeFrame = new JFrame("Compose Email");
				composeFrame.setLayout(new FormLayout());
				composeFrame.add(new JLabel("From:"));
				composeFrame.add(fromTextField);
				composeFrame.add(new JLabel("To:"));
				composeFrame.add(toTextField);
				composeFrame.add(new JLabel("Subject:"));
				composeFrame.add(subjectTextField);
				composeFrame.add(new JLabel(""));
				composeFrame.add(bodyTextArea);
				composeFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
				//DO_NOTHING_ON_CLOSE is needed for the following addWindowListener
				composeFrame.setResizable(false);
				composeFrame.pack();
				composeFrame.setVisible(true);
				composeFrame.setAlwaysOnTop(true);
				//enable the send button
				sendButton.setEnabled(true);
				//Prompt the user on close if text was written in any of the fields
				composeFrame.addWindowListener(new java.awt.event.WindowAdapter() 
				{
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
				    {
				    	if( !fromTextField.getText().isEmpty() || !toTextField.getText().isEmpty() || !subjectTextField.getText().isEmpty() || !bodyTextArea.getText().isEmpty() )
				    	{
					        if (JOptionPane.showConfirmDialog(composeFrame, 
					            "Are you sure to close this window? Anything you've written won't be saved or sent.", "Close Confirmation", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					        {
					        	composeFrame.setVisible(false);
					        	composeFrame.dispose();
					        	composeFrame = null;
					        	sendButton.setEnabled(false);
					        }
				    	}
				    	else
				    	{
				        	composeFrame.setVisible(false);
				        	composeFrame.dispose();
				        	composeFrame = null;
				        	sendButton.setEnabled(false);
				    	}
				    }
				});
			}
		});
		
		replyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if( composeFrame != null ) //if frame is open
				{
					//close frame
					composeFrame.setVisible(false);
					composeFrame.dispose();
					composeFrame = null;
				}
						
				if( replyFrame != null ) //if frame is open
				{
					//close frame
					replyFrame.setVisible(false);
					replyFrame.dispose();
					replyFrame = null;
				}
				//Set fields
				currentlySelectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(currentlySelectedNode.getLevel() ==  5)
				{
					Email replyEmail = (Email)currentlySelectedNode.getUserObject();
					
		        	fromTextField.setText(replyEmail.getDestinationAddress().getAccount() + "@" + replyEmail.getDestinationAddress().getServerDomain());
		        	toTextField.setText(replyEmail.getSenderAddress().getAccount() + "@" + replyEmail.getSenderAddress().getServerDomain());
		        	subjectTextField.setText("Re: " + replyEmail.getSubject());
		        	bodyTextArea.setText("");
		        	
		        	//create new frame
					replyFrame = new JFrame("Reply Email");
					replyFrame.setLayout(new FormLayout());
					replyFrame.add(new JLabel("From:"));
					replyFrame.add(fromTextField);
					replyFrame.add(new JLabel("To:"));
					replyFrame.add(toTextField);
					replyFrame.add(new JLabel("Subject:"));
					replyFrame.add(subjectTextField);
					replyFrame.add(new JLabel(""));
					replyFrame.add(bodyTextArea);
					replyFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					//DO_NOTHING_ON_CLOSE is needed for the following addWindowListener
					replyFrame.setResizable(false);
					replyFrame.pack();
					replyFrame.setVisible(true);
					replyFrame.setAlwaysOnTop(true);
					//enable the send button
					sendButton.setEnabled(true);
					//Prompt the user on close if text was written in any of the fields
					replyFrame.addWindowListener(new java.awt.event.WindowAdapter() 
					{
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
					    {
					    	if( !fromTextField.getText().isEmpty() || !toTextField.getText().isEmpty() || !subjectTextField.getText().isEmpty() || !bodyTextArea.getText().isEmpty() )
					    	{
						        if (JOptionPane.showConfirmDialog(replyFrame, 
						            "Are you sure to close this window? Anything you've written won't be saved or sent.", "Close Confirmation", 
						            JOptionPane.YES_NO_OPTION,
						            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
						        {
						        	replyFrame.setVisible(false);
						        	replyFrame.dispose();
						        	replyFrame = null;		
						        	sendButton.setEnabled(false);
						        }
					    	}
					    	else
					    	{
					    		replyFrame.setVisible(false);
					    		replyFrame.dispose();
					    		replyFrame = null;
					    		sendButton.setEnabled(false);
					    	}
					    }				    
					});
				}
			}
		});
		
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//check if a frame is open
				if( composeFrame != null || replyFrame != null )
				{
					EmailAddress toEmailAddress = null;
					EmailAddress senderEmailAddress = null;
					ArrayList<Account> accountClone = AccountManager.getAccountList();
					String[] toParts = toTextField.getText().split("@", 2);
					String[] fromParts = fromTextField.getText().split("@", 2);
					boolean hasFoundToName = false;
					boolean hasFoundFromName = false;
					int i = 0; //contains To user name index
					int j = 0; //contains From user name index
					for( i = 0; i < accountClone.size(); i++ )
					{
						if( accountClone.get(i).getEmailName().equals(toParts[0]) )
						{
							hasFoundToName = true;
							break;
						}
					}
					
					for( j = 0; j < accountClone.size(); j++ )
					{
						if( accountClone.get(j).getEmailName().equals(fromParts[0]) )
						{
							hasFoundFromName = true;
							break;
						}
					}
					
					if( hasFoundToName == false || hasFoundFromName == false )
					{
						JOptionPane.showMessageDialog(frame, 
					            "Cannot find the specified user name.", "Error", 
					            JOptionPane.OK_OPTION);
						return;
					}
					
					//find the address to send the email to
					for( int k = 0; k < accountClone.get(i).getLocalAddresses().size(); k++ )
					{
						if( toParts[1].equals(accountClone.get(i).getLocalAddresses().get(k).getServerDomain()) )
						{
							toEmailAddress = accountClone.get(i).getLocalAddresses().get(k);
							break;
						}
					}
					
					//if not in local, search remote
					if( toEmailAddress == null )
					{
						for( int k = 0; k < accountClone.get(i).getRemoteAddresses().size(); k++ )
						{
							if( toParts[1].equals(accountClone.get(i).getRemoteAddresses().get(k).getServerDomain()) )
							{
								toEmailAddress = accountClone.get(i).getRemoteAddresses().get(k);
								break;
							}
						}						
					}
					
					//find the address to send the email from
					for( int k = 0; k < accountClone.get(j).getLocalAddresses().size(); k++ )
					{
						if( fromParts[1].equals(accountClone.get(j).getLocalAddresses().get(k).getServerDomain()) )
						{
							senderEmailAddress = accountClone.get(j).getLocalAddresses().get(k);
							break;
						}
					}
					
					//if not in local, search remote
					if( senderEmailAddress == null )
					{
						for( int k = 0; k < accountClone.get(j).getRemoteAddresses().size(); k++ )
						{
							if( fromParts[1].equals(accountClone.get(j).getRemoteAddresses().get(k).getServerDomain()) )
							{
								senderEmailAddress = accountClone.get(j).getRemoteAddresses().get(k);
								break;
							}
						}						
					}
					
					if( toEmailAddress == null || senderEmailAddress == null )
					{
						JOptionPane.showMessageDialog(frame, 
					            "Cannot find the specified email address.", "Error", 
					            JOptionPane.OK_OPTION);
						return;
					}
					
					now = new Date();
					System.out.println( "Before To size: " + toEmailAddress.getInbox().size() );
					System.out.println( "Before From size: " + senderEmailAddress.getSent().size());
					Email newEmail = EmailManager.CreateEmail(toEmailAddress, senderEmailAddress, subjectTextField.getText(), bodyTextArea.getText(), now);
					EmailManager.sendEmail(newEmail);
					System.out.println( "After To size: " + toEmailAddress.getInbox().size() );
					System.out.println( "After From size: " + senderEmailAddress.getSent().size());
			        
			        //GUI logic
			        DefaultMutableTreeNode toNode = GUIAccountTreeManager.getNodeByEmail(toTextField.getText(), root);
			        DefaultMutableTreeNode fromNode = GUIAccountTreeManager.getNodeByEmail(fromTextField.getText(), root);
			        
			        GUIAccountTreeManager.addEmailToNode(newEmail, toNode, model, "Inbox");
			        GUIAccountTreeManager.addEmailToNode(newEmail, fromNode, model, "Sent");
			        
			        //Close the compose/reply windows
			        if( composeFrame != null )
			        {
			        	composeFrame.setVisible(false);
			        	composeFrame.dispose();
			        	composeFrame = null;
			        	sendButton.setEnabled(false);				        	
			        }
			        else if( replyFrame != null )
			        {
			        	replyFrame.setVisible(false);
			        	replyFrame.dispose();
			        	replyFrame = null;		
			        	sendButton.setEnabled(false);				        	
			        }
				}
			}
		});
		
		removeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				currentlySelectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(currentlySelectedNode.getLevel() ==  5)
				{
					Email removeEmail = (Email)currentlySelectedNode.getUserObject();
					EmailManager.deleteEmail(removeEmail);
				}
				
				GUIAccountTreeManager.DeleteEmail(currentlySelectedNode, model);
				screen.setText(""); 
				removeButton.setEnabled(false);
			}
		});
		
		
		//Mouse Listener
		
		MouseListener ml = new MouseAdapter() 
		{
		     public void mousePressed(MouseEvent e) 
		     {
		    	 String temp = null;
		         int selRow = tree.getRowForLocation(e.getX(), e.getY());
		         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		         if( selPath != null )
		         {
			         temp = selPath.toString();
			         char[] charArray = temp.toCharArray();
			         char[] tempArray = new char[30];
			         int commaCount = 0;
			         int j = 0;
			         if(selRow != -1 && e.getClickCount() == 2) 
			         {
			        	 for(int i = 0; i < charArray.length; i++)
			        	 {
			        		 String charHolder = Character.toString(charArray[i]);
			        		 if(commaCount == 3)
			        		 {
			        			 if(!charHolder.equals("]"))
			        			 {
				        			 tempArray[j] = charArray[i];
				        			 j++;
			        			 }
			        		 }
			        		 if(charHolder.equals(","))
			        		 {
			        			 commaCount++;		        		
			        		 }
			        	 }
			        	if(commaCount == 3)
			        	{
				        	 String b = new String(tempArray);
				        	 //System.out.println(b);
			         		 //senderField.setServerDomain(b);
			        	}		           
			         }
		         }
		     }
		 };
		
		
		//Tree logic
		 
		root = new DefaultMutableTreeNode("Users" +
		"                                                                            "); //spacing
		treeView = new JScrollPane();
		
		tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        model = (DefaultTreeModel)tree.getModel();
        GUIAccountTreeManager.addUserDataToTree(root);
        tree.addTreeSelectionListener(this);
        treeView = new JScrollPane(tree);
        treeView.setSize(new Dimension(1000,500));
        
        tree.addMouseListener(ml);
        
        //Frame logic
        
		frame.setSize(1000, 500);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.add(labelPanel, BorderLayout.SOUTH);
		frame.add(treeView, BorderLayout.WEST);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	@Override
	public void valueChanged(TreeSelectionEvent arg0) 
	{
		currentlySelectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if( currentlySelectedNode == null ) //fixed an exception where the user selected an email, then deleted the account with the email
		{
			return;
		}
		
		if(currentlySelectedNode.getLevel() ==  5)
		{
			if( currentlySelectedNode.getParent().toString().equals("Inbox"))
			{
				replyButton.setEnabled(true);
				removeButton.setEnabled(true);
			}
			else if( currentlySelectedNode.getParent().toString().equals("Trash")) 
			{
				removeButton.setEnabled(true);
			}
			
			screen.setText(((Email) currentlySelectedNode.getUserObject()).getContent()); 
		}
		else
		{
			removeButton.setEnabled(false);
			replyButton.setEnabled(false);
		}
			
	}
}