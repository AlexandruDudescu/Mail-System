import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
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

public class GUI extends JComponent implements TreeSelectionListener 
{
	JFrame frame = new JFrame("Voice Mail System - GUI Prototype");
	//JFrame messageFrame = new JFrame();
	JFrame composeFrame = new JFrame("Compose Email");
	JFrame replyFrame = new JFrame("Reply Email");
	JPanel buttonPanel = new JPanel();
	JButton composeButton = new JButton("Compose");
	JTree tree;
	JButton replyButton = new JButton("Reply");
	JButton sendButton = new JButton ("Send");
	JButton removeButton = new JButton("Remove");
	JTextArea screen = new JTextArea(10, 10);
	JPanel textPanel = new JPanel();
	JPanel labelPanel = new JPanel();
	JScrollPane treeView;
	JScrollPane screenScroller = new JScrollPane(screen);
	//String data = null;
	JTextField firstField = new JTextField(30);
	JTextField secondField = new JTextField(30);
    JTextArea thirdField = new JTextArea(10,30);
    Date now = new Date();
	
	GUI () 
	{		
		//Button panel code
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(composeButton);
		buttonPanel.add(replyButton);
		buttonPanel.add(sendButton);
		buttonPanel.add(removeButton);
		buttonPanel.setBackground(Color.lightGray);
		
		//
		GridLayout gridLayout = new GridLayout(1, 2, 50, 50);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		labelPanel.setLayout(gridLayout);
	
		screen.setBackground(Color.yellow);
		screen.setBorder(BorderFactory.createBevelBorder(1));
		
		textPanel.setBorder(new EmptyBorder(10,10,10,10));
		textPanel.setLayout(gridLayout);
		textPanel.add(screenScroller);
		
		
		//Button functionality
		composeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				thirdField.setLineWrap(true);
				composeFrame.setLayout(new FormLayout());
				composeFrame.setTitle("Compose Email");
				composeFrame.add(new JLabel("To"));
				composeFrame.add(firstField);
				composeFrame.add(new JLabel("Subject"));
				composeFrame.add(secondField);
				composeFrame.add(new JLabel(""));
				composeFrame.add(thirdField);
				composeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				composeFrame.pack();
				composeFrame.setVisible(true);
			}
			
		});
		
		replyButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				thirdField.setLineWrap(true);
				replyFrame.setLayout(new FormLayout());
				replyFrame.setTitle("Reply Email");
				replyFrame.add(new JLabel("To"));
				replyFrame.add(new JTextField(30));
				replyFrame.add(new JLabel("Subject"));
				replyFrame.add(new JTextField(30));
				replyFrame.add(new JLabel(""));
				replyFrame.add(thirdField);
				replyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				replyFrame.pack();
				replyFrame.setVisible(true);
			}
		});
		
		sendButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				Email newEmail = new Email();
				toField.setServerDomain(firstField.getText());		//Probably have to change this
				subjectField = secondField.getText();
				bodyField = thirdField.getText();
				newEmail = EmailManager.CreateEmail(toField, senderField, subjectField, bodyField, now);
				EmailManager.sendEmail(newEmail);
		        System.out.println(toField);
		        System.out.println(subjectField);
		        System.out.println(bodyField);
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
		 };
		
		
		//tree logic
		
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Accounts");
		treeView = new JScrollPane();
		
		tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        GUIAccountTreeManager.addUserDataToTree(root);
        tree.addTreeSelectionListener(this);
        treeView = new JScrollPane(tree);
        treeView.setSize(new Dimension(1000,500));
        
        tree.addMouseListener(ml);
        
        
		frame.setSize(1000, 500);
		//frame.add(composeButton);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.add(labelPanel, BorderLayout.SOUTH);
		frame.add(treeView, BorderLayout.WEST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	@Override
	public void valueChanged(TreeSelectionEvent arg0) 
	{
	}
	
	private EmailAddress toField;
	private EmailAddress senderField;
	private String subjectField;
	private String bodyField;
	//private Point mousePoint;
}