package com.one.two;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StaffLogin extends JFrame implements ActionListener{
	public static final int width = 300;
	public static final int height = 200;
	private static String usernameInput = "";
	private static String passwordInput = "";
	private static String[][] credentials = new String[4][2];
	JTextField username = new JTextField(10);
	JTextField password = new JPasswordField(4);
	boolean containsUser = false;
	JLabel noteLabel = new JLabel("Wrong username or password.");

	StaffLogin(){
		super("Library Management System");
		setSize(width,height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());	
		
		JPanel credentialsPanel = new JPanel();
		add(credentialsPanel, BorderLayout.CENTER);
		credentialsPanel.setLayout(new BoxLayout(credentialsPanel,BoxLayout.Y_AXIS));
        
		JPanel usernamePanel = new JPanel();
		credentialsPanel.add(usernamePanel);
		usernamePanel.setLayout(new FlowLayout());
		
		JLabel usernameLabel = new JLabel("Username");
		usernamePanel.add(usernameLabel);
		usernamePanel.add(username);
		
		JPanel passwordPanel = new JPanel();
		credentialsPanel.add(passwordPanel);
		passwordPanel.setLayout(new FlowLayout());
		
		JLabel passwordLabel = new JLabel("Password");
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		
		JPanel notePanel = new JPanel();
		credentialsPanel.add(notePanel);
		
		noteLabel.setForeground(Color.RED);
		notePanel.add(noteLabel);
		noteLabel.setVisible(false);
		
        JPanel buttonsPanel = new JPanel();
        add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.setLayout(new FlowLayout());
        
		JButton backButton = new JButton("<- Back");
		buttonsPanel.add(backButton);
		backButton.addActionListener(this);
		
		JButton loginButton = new JButton("Log In");
		buttonsPanel.add(loginButton);
		loginButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Logging in...");
		String buttonString = e.getActionCommand();
		if (buttonString.equals("Log In")){
			usernameInput = username.getText();
			passwordInput = password.getText();
			
			try {
				File file = new File("staffUsernamePassword.txt");
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line;
				int counter=0;
				while ((line = bufferedReader.readLine()) != null) { //while file has not ended
					credentials[counter] = line.split("	");
					counter++;
				}
				bufferedReader.close();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}

			for(int i=0; i<credentials.length; i++){
				if (credentials[i][0].equals(usernameInput) && credentials[i][1].equals(passwordInput)){
					containsUser = true;
					break;
				}
			}
			
		    if(containsUser == true){
		    	System.out.println("Success!");
				dispose();
				new StaffWindow();
		    }
		    else{
				noteLabel.setVisible(true);

		    }
		    
		}
		else if (buttonString.equals("<- Back")){
			dispose();
			new MainWindow();
		}
		else
			System.out.println("Unexpected error.");
	}
}
