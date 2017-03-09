import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class GUI implements TreeSelectionListener
{
	JFrame frame = new JFrame("Voice Mail System - GUI Prototype");
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
	
	GUI () 
	{
		//Button pannel code
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
		
		//tree logic
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Accounts");
		treeView = new JScrollPane();
		
		tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        GUIAccountTreeManager.addUserDataToTree(root);
        tree.addTreeSelectionListener(this);
        treeView = new JScrollPane(tree);
        treeView.setSize(new Dimension(1000,500));
		
		frame.setSize(1000, 500);
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
}
