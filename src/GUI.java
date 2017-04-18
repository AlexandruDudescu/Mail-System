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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

@SuppressWarnings("serial")
public class GUI extends JComponent implements TreeSelectionListener 
{
//Menu variables
	JMenuBar menuBar;
	JMenu accounts;
	JMenuItem addAccount;
	JMenuItem removeAccount;
//Frame variables
	JFrame frame = new JFrame("Simple Email - Team Void");
	JFrame composeFrame = null;
	JFrame replyFrame = null;
//Panel variables	
	JPanel buttonPanel = new JPanel();
	JPanel textPanel = new JPanel();
	JPanel labelPanel = new JPanel();	
//Button variables	
	JButton composeButton = new JButton("Compose");
	JButton replyButton = new JButton("Reply");
	JButton sendButton = new JButton ("Send");
	JButton removeButton = new JButton("Remove");
//Text Field variables
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
	
    Date now = null;
	
	GUI() 
	{
		//Menu code
		menuBar = new JMenuBar();
		accounts = new JMenu("Account");
		addAccount = new JMenuItem("Add Account");
		removeAccount = new JMenuItem("Remove Account");
		menuBar.add(accounts);
		accounts.add(addAccount);
		accounts.add(removeAccount);
		//Button panel code
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(composeButton);
		buttonPanel.add(replyButton);
		buttonPanel.add(sendButton);
		buttonPanel.add(removeButton);
		buttonPanel.setBackground(Color.lightGray);
		
		//Grid code
		GridLayout gridLayout = new GridLayout(1, 2, 50, 50);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		labelPanel.setLayout(gridLayout);

		screen.setBackground(Color.lightGray);
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
				//TODO: add menu button functionality
				System.out.println("Test: Add");
			}
		});
		
		removeAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//TODO: add menu button functionality
				System.out.println("Test: Remove");
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
				//Prompt the user on close if text was written in any of the fields
				composeFrame.addWindowListener(new java.awt.event.WindowAdapter() 
				{
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
				    {
				    	if( !fromTextField.getText().isEmpty() || !toTextField.getText().isEmpty() || !subjectTextField.getText().isEmpty() || !bodyTextArea.getText().isEmpty() )
				    	{
					        if (JOptionPane.showConfirmDialog(frame, 
					            "Are you sure to close this window? Anything you've written won't be saved or sent.", "Close Confirmation", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					        {
					        	composeFrame.setVisible(false);
					        	composeFrame.dispose();
					        	composeFrame = null;				        	
					        }
				    	}
				    	else
				    	{
				        	composeFrame.setVisible(false);
				        	composeFrame.dispose();
				        	composeFrame = null;
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
				//clear text fields
	        	fromTextField.setText("");
	        	toTextField.setText("");
	        	subjectTextField.setText("");
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
				//Prompt the user on close if text was written in any of the fields
				replyFrame.addWindowListener(new java.awt.event.WindowAdapter() 
				{
				    @Override
				    public void windowClosing(java.awt.event.WindowEvent windowEvent) 
				    {
				    	if( !fromTextField.getText().isEmpty() || !toTextField.getText().isEmpty() || !subjectTextField.getText().isEmpty() || !bodyTextArea.getText().isEmpty() )
				    	{
					        if (JOptionPane.showConfirmDialog(frame, 
					            "Are you sure to close this window? Anything you've written won't be saved or sent.", "Close Confirmation", 
					            JOptionPane.YES_NO_OPTION,
					            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					        {
					        	replyFrame.setVisible(false);
					        	replyFrame.dispose();
					        	replyFrame = null;				        	
					        }
				    	}
				    	else
				    	{
				    		replyFrame.setVisible(false);
				    		replyFrame.dispose();
				    		replyFrame = null;
				    	}
				    }				    
				});
			}
		});
		
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				//check if a frame is open
				if( composeFrame != null || replyFrame != null )
				{
					ArrayList<Account> accountClone = AccountManager.getAccountList();
					String[] toParts = toTextField.getText().split("@", 2);
					String[] fromParts = fromTextField.getText().split("@", 2);
					boolean hasFoundToName = false;
					boolean hasFoundFromName = false;
					int i = 0;
					int j = 0;
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
					
					if( hasFoundToName == false || hasFoundFromName == false || ( !toParts[1].equals("local.com") && !toParts[1].equals("remote.com") ) )
					{
						JOptionPane.showMessageDialog(frame, 
					            "Cannot find the specified email address.", "Error", 
					            JOptionPane.OK_OPTION);
						return;
					}
					//TODO: add dynamic check to make sure the server exists
					//Right now, this deals only with the test cases "local.com" and "remote.com"
					if( ( toParts[1].equals("local.com") || toParts[1].equals("remote.com") ) && (fromParts[1].equals("local.com") || fromParts[1].equals("remote.com") ) )
					{
						if( toParts[1].equals("local.com") )
						{
							toEmailAddress = accountClone.get(i).getLocalAddresses().get(0);
						}
						else //remote.com
						{
							toEmailAddress = accountClone.get(i).getRemoteAddresses().get(0);
						}
						
						if( fromParts[1].equals("local.com") )
						{
							senderEmailAddress = accountClone.get(i).getLocalAddresses().get(0);
						}
						else //remote.com
						{
							senderEmailAddress = accountClone.get(i).getRemoteAddresses().get(0);
						}
						subjectString = subjectTextField.getText();
						bodyString = bodyTextArea.getText();
						now = new Date();
						Email newEmail = EmailManager.CreateEmail(toEmailAddress, senderEmailAddress, subjectString, bodyString, now);
						EmailManager.sendEmail(newEmail);
				        System.out.println(toTextField.getText());
				        System.out.println(subjectString);
				        System.out.println(bodyString);
					}
				}
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
				        	 System.out.println(b);
			         		 //senderField.setServerDomain(b);
			        	}		           
			         }
		         }
		     }
		 };
		
		
		//Tree logic
		 
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Accounts" +
		"                                                                            "); //spacing
		treeView = new JScrollPane();
		
		tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
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
	}
	
	private EmailAddress toEmailAddress;
	private EmailAddress senderEmailAddress;
	private String subjectString;
	private String bodyString;
}