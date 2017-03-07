import java.awt.GridLayout;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GUI {
	JFrame frame = new JFrame("Voice Mail System - GUI Prototype");
	JPanel buttonPanel = new JPanel();
	JButton composeButton = new JButton("Compose");
	JButton replyButton = new JButton("Reply");
	JButton sendButton = new JButton ("Send");
	JButton removeButton = new JButton("Remove");
	JTextArea screen = new JTextArea(10, 10);
	JPanel textPanel = new JPanel();
	JPanel labelPanel = new JPanel();
	JScrollPane screenScroller = new JScrollPane(screen);
	
	GUI () 
	{
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(composeButton);
		buttonPanel.add(replyButton);
		buttonPanel.add(sendButton);
		buttonPanel.add(removeButton);
		buttonPanel.setBackground(Color.lightGray);
		
		GridLayout gridLayout = new GridLayout(1, 2, 50, 50);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		labelPanel.setLayout(gridLayout);
	
		screen.setBackground(Color.yellow);
		screen.setBorder(BorderFactory.createBevelBorder(1));
		
		textPanel.setBorder(new EmptyBorder(10,10,10,10));
		textPanel.setLayout(gridLayout);
		textPanel.add(screenScroller);

		frame.setSize(1000, 500);
		frame.add(buttonPanel, BorderLayout.NORTH);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.add(labelPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
