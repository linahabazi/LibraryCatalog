package com.one.two;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentWindow extends JFrame implements ActionListener{
	public static final int width = 600;
	public static final int height = 400;
	public static Object data[][] = new Object[20][5];
	public static Object columnName[] = {"Book Title","Author","Pub/Rel Date","ISBN-10", "Checked Out"};
	public static JTable table = new JTable(data,columnName);
	public static boolean opened = false;
	public static int arrayLength = data.length;
	public static JLabel invalidLabel = new JLabel("Please make a selection.");
	public static JLabel borrowLabel = new JLabel("This book is currently checked out");
	public static JLabel returnLabel = new JLabel("This book is already checked in");
	public static JButton borrowButton = new JButton("Borrow Book");
	public static JButton returnButton = new JButton("Return Book");

	StudentWindow(){
			super("Library Management System");
			setSize(width,height);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setVisible(true);
			
			table.setBounds(30, 40, 100, 300); //10, 10, 400, 300?
			table.setAutoCreateRowSorter(true);
			JScrollPane scroll = new JScrollPane(table);
			add(scroll);
			table.setBackground(Color.decode("#f2fffb"));
			table.setGridColor(Color.LIGHT_GRAY);
			table.getTableHeader().setBackground(Color.decode("#DDF5EF"));
		
			JPanel actionPanel = new JPanel();
			add(actionPanel, BorderLayout.SOUTH);
			actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.Y_AXIS));
				
			JPanel savePanel = new JPanel();
			actionPanel.add(savePanel);
			savePanel.setBackground(Color.decode("#DDF5EF"));
				
			savePanel.add(borrowButton);
			borrowButton.addActionListener(this);
			
			savePanel.add(returnButton);
			returnButton.addActionListener(this);
				
			JPanel notePanel = new JPanel();
			actionPanel.add(notePanel);
			notePanel.setBackground(Color.decode("#DDF5EF"));
		
			notePanel.add(borrowLabel);
			borrowLabel.setForeground(Color.RED);
			borrowLabel.setVisible(false);
			
			notePanel.add(returnLabel);
			returnLabel.setForeground(Color.RED);
			returnLabel.setVisible(false);
			
			notePanel.add(invalidLabel);
			invalidLabel.setForeground(Color.RED);
			invalidLabel.setVisible(false);
				
			JPanel logoffPanel = new JPanel();
			actionPanel.add(logoffPanel);
			logoffPanel.setBackground(Color.decode("#DDF5EF"));
		
			JButton logoffButton = new JButton("Main Menu");
			logoffPanel.add(logoffButton);
			logoffButton.addActionListener(this);
			
			if (opened == false){ //first time opening window reads file into array
				try {
					File file = new File("records.txt");
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					String line;
//					int counter = 0; //the counter was used to bypass the first line "3" in the file
					int row=0; //count to 20, change if ""
					int column = 0; //count to 5 then reset to 0
					
					while ((line = bufferedReader.readLine()) != null) { //while file has not ended
//						if (counter>0 && !line.equals("")) { //skips first line "3"
						if (!line.equals("")){
							data[row][column]=line; //read line into array
							column++;
						}
//						counter++;
						if(line.equals("")){ //change to else
							row++;
							column=0;
						}
					}
					bufferedReader.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				opened = true;
			} // add new entry into array at the end or replace specific index for status then rewrite file from array
	}
	
	
	public void actionPerformed(ActionEvent e){
		String buttonString = e.getActionCommand();
		if (buttonString.equals("Borrow Book") && (table.getSelectedRow()==-1 || data[table.getSelectedRow()][0]==null)){
			System.out.println("Borrow Book, No selection");
			borrowLabel.setVisible(false);
			returnLabel.setVisible(false);
			invalidLabel.setVisible(true);
		}
		else if (buttonString.equals("Borrow Book") && data[table.getSelectedRow()][4].equals("Yes")){
			System.out.println("Borrow Book, Yes");
			borrowLabel.setVisible(true);
			returnLabel.setVisible(false);
			invalidLabel.setVisible(false);

		}
		else if (buttonString.equals("Borrow Book") && data[table.getSelectedRow()][4].equals("No")){
			System.out.println("Borrow Book, No");
			data[table.getSelectedRow()][4]= "Yes";
			borrowLabel.setVisible(false);
			returnLabel.setVisible(false);
			invalidLabel.setVisible(false);
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
		
		else if (buttonString.equals("Return Book") && (table.getSelectedRow()==-1 || data[table.getSelectedRow()][0]==null)){
			System.out.println("Return Book, No selection");
			borrowLabel.setVisible(false);
			returnLabel.setVisible(false);
			invalidLabel.setVisible(true);
		}
		else if (buttonString.equals("Return Book") && data[table.getSelectedRow()][4].equals("No")){
			System.out.println("Return Book, No");
			borrowLabel.setVisible(false);
			returnLabel.setVisible(true);
			invalidLabel.setVisible(false);
		}
		else if (buttonString.equals("Return Book") && data[table.getSelectedRow()][4].equals("Yes")){
			System.out.println("Return Book, Yes");
			data[table.getSelectedRow()][4]= "No";
			borrowLabel.setVisible(false);
			returnLabel.setVisible(false);
			invalidLabel.setVisible(false);
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
		
		
		else if(buttonString.equals("Main Menu")){//WORKS
			dispose();
			new MainWindow();
		}
		else{
			System.out.println("Unexpeced Error.");//WORKS
		}
		
	}
}