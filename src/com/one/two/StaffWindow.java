package com.one.two;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StaffWindow extends JFrame implements ActionListener{
	public static final int width = 320;
	public static final int height = 420;
	JTextField title = new JTextField(10);
	JTextField author = new JTextField(10);
	JTextField date = new JTextField(10);
	JTextField isbn = new JTextField(10);
	JButton newEntryButton = new JButton("New Entry");
	JLabel saveLabel = new JLabel("Entry Saved.");
	JButton saveButton = new JButton("Save");
	JLabel requiredNote = new JLabel("* : Required fields");
	JLabel requiredUpdate = new JLabel("<html>Please enter all required fields.<br><br>* : Required fields</html>");
	JLabel fullNote = new JLabel("The library database is full.");
	private static String titleInput = "";
	private static String authorInput = "";
	private static String dateInput = "";
	private static String isbnInput = "";
	
	StaffWindow(){
		super("Library Management System");
		setSize(width,height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());	
		
		JPanel entryPanel = new JPanel();
		add(entryPanel, BorderLayout.CENTER);
		entryPanel.setLayout(new BoxLayout(entryPanel,BoxLayout.Y_AXIS));
		entryPanel.setBackground(Color.decode("#DDF5EF"));
        
		JPanel logoffPanel = new JPanel();
		add(logoffPanel, BorderLayout.SOUTH);
		logoffPanel.setBackground(Color.decode("#DDF5EF"));
		
		JPanel titlePanel = new JPanel();
		entryPanel.add(titlePanel);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(Color.decode("#DDF5EF"));

		JLabel requiredLabel1 = new JLabel("*");
		requiredLabel1.setForeground(Color.RED);
		JLabel requiredLabel2 = new JLabel("*");
		requiredLabel2.setForeground(Color.RED);
		JLabel requiredLabel3 = new JLabel("*");
		requiredLabel3.setForeground(Color.RED);
		JLabel requiredLabel4 = new JLabel("*");
		requiredLabel4.setForeground(Color.RED);
		
		JLabel titleLabel = new JLabel("Book Title");
		titlePanel.add(titleLabel);
		titlePanel.add(requiredLabel1);
		titlePanel.add(title);
		
		JPanel authorPanel = new JPanel();
		entryPanel.add(authorPanel);
		authorPanel.setLayout(new FlowLayout());
		authorPanel.setBackground(Color.decode("#DDF5EF"));

		JLabel authorLabel = new JLabel("Author");
		authorPanel.add(authorLabel);
		authorPanel.add(requiredLabel2);
		authorPanel.add(author);
		
		JPanel datePanel = new JPanel();
		entryPanel.add(datePanel);
		datePanel.setLayout(new FlowLayout());
		datePanel.setBackground(Color.decode("#DDF5EF"));

		JLabel dateLabel = new JLabel("Pub/Rel Date");
		datePanel.add(dateLabel);
		datePanel.add(requiredLabel3);
		datePanel.add(date);
		
		JPanel isbnPanel = new JPanel();
		entryPanel.add(isbnPanel);
		isbnPanel.setLayout(new FlowLayout());
		isbnPanel.setBackground(Color.decode("#DDF5EF"));

		JLabel isbnLabel = new JLabel("ISBN-10");
		isbnPanel.add(isbnLabel);
		isbnPanel.add(requiredLabel4);
		isbnPanel.add(isbn);
		
		JPanel savePanel = new JPanel();
		entryPanel.add(savePanel);
		savePanel.add(saveButton);
		saveButton.addActionListener(this);
		savePanel.setBackground(Color.decode("#DDF5EF"));

		JButton logoffButton = new JButton("Log Off");
		logoffPanel.add(logoffButton);
		logoffButton.addActionListener(this);
		
		JPanel notePanel = new JPanel();
		entryPanel.add(notePanel);
		requiredNote.setForeground(Color.RED);
		requiredUpdate.setForeground(Color.RED);
		fullNote.setForeground(Color.RED);
		notePanel.add(requiredNote);
		notePanel.add(requiredUpdate);
		notePanel.add(fullNote);
		requiredUpdate.setVisible(false);
		fullNote.setVisible(false);
		notePanel.setBackground(Color.decode("#DDF5EF"));
		

		savePanel.add(saveLabel);
		saveLabel.setVisible(false);
				
		savePanel.add(newEntryButton);
		newEntryButton.setVisible(false);
		newEntryButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		String buttonString = e.getActionCommand();
		if (buttonString.equals("Log Off")){
			System.out.println("Logging off...");
			dispose();
			new MainWindow();
		}
		//write book to file
		else if (buttonString.equals("Save")){
			if(!(StudentWindow.data[(StudentWindow.data.length)-1][0]==null)){
				System.out.println("The library database is full.");
				requiredNote.setVisible(false);
				requiredUpdate.setVisible(false);
				fullNote.setVisible(true);
			}
			else if((!title.getText().equals("") && !author.getText().equals("") && !date.getText().equals("") && !isbn.getText().equals("")) && (StudentWindow.data[(StudentWindow.data.length)-1][0]==null)){
				System.out.println("Saving...");
				saveButton.setVisible(false);
				saveLabel.setVisible(true);
				newEntryButton.setVisible(true);
				requiredNote.setVisible(false);
				requiredUpdate.setVisible(false);
				
				titleInput = title.getText();
				authorInput = author.getText();
				dateInput = date.getText();
				isbnInput = isbn.getText();
	
//				try {
//					System.out.println("Writing to database...");
//				    File file = new File("records.txt");
//				    file.createNewFile();
//				    FileWriter writer = new FileWriter(file.getName(),true);
//				    BufferedWriter bwriter = new BufferedWriter(writer);
//				    bwriter.newLine();
//				    bwriter.newLine();
//				    bwriter.write(titleInput);
//				    bwriter.newLine();
//				    bwriter.write(authorInput); 
//				    bwriter.newLine();
//				    bwriter.write(dateInput);
//				    bwriter.newLine();
//				    bwriter.write(isbnInput);
//				    bwriter.newLine();
//				    bwriter.write("No");
//				    bwriter.flush();
//				    bwriter.close();
//				    writer.close();
//				}
//				catch (Exception ex) {
//					ex.printStackTrace();
//				}
				
				//append input to array
				for(int row = 0; row<(StudentWindow.arrayLength); row++){
					if (StudentWindow.data[row][0] == null){
						//add next input value
					    System.out.println("add to array");
						StudentWindow.data[row][0]=titleInput;
						StudentWindow.data[row][1]=authorInput;
						StudentWindow.data[row][2]=dateInput;
						StudentWindow.data[row][3]=isbnInput;
						StudentWindow.data[row][4]="No";
						break;
					}
				}
				
			    //overwrites entire file from array
				try {
					System.out.println("create files");
				    File file = new File("records.txt");
				    File tempFile = File.createTempFile("tempFile", "");
				    FileWriter fw = new FileWriter(tempFile);
				    BufferedWriter bw = new BufferedWriter(fw);
	//			    bw.write("3\n"); //rewrites first line "3" into file
				    boolean arrayEnd = false;
				    while(arrayEnd == false){
				    	for(int row = 0; row<StudentWindow.data.length; row++){
			    			if((StudentWindow.data[row][0]==null)){
			    				System.out.println("empty row");
			    				arrayEnd=true;
			    				break;
			    			}
				    		for(int column = 0; column<5; column++){
								System.out.println("write line to new file");
						    	bw.write(StudentWindow.data[row][column].toString());
						    	bw.write("\n");
				    		}
				    		bw.write("\n");
							System.out.println("finish entry");
			    			if(row==(StudentWindow.data.length)-1){
			    				System.out.println("end of array");
			    				arrayEnd=true;
			    				break;
			    			}
				    	}
						System.out.println(arrayEnd);
				    }
				    bw.close();
				    if (file.delete())
				        tempFile.renameTo(file);
						System.out.println("rename file");
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}	
				
			}
			else{
				System.out.println("Please fill all required fields.");
				requiredNote.setVisible(false);
				requiredUpdate.setVisible(true);	
			}
		}
		else if (buttonString.equals("New Entry")){
			title.setText("");
			author.setText("");
			date.setText("");
			isbn.setText("");
			saveButton.setVisible(true);
			saveLabel.setVisible(false);
			newEntryButton.setVisible(false);
			requiredNote.setVisible(true);
		}
		else{
			System.out.println("Unexpected Error.");
		}
	}
}
