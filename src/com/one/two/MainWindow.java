package com.one.two;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener{
	public static final int width = 410;
	public static final int height = 180;
	
	MainWindow(){
		super("Library Management System");
		setSize(width,height);
        setLayout(new GridLayout(2,1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel titlePanel = new JPanel();
		add(titlePanel);
		titlePanel.setBackground(Color.decode("#DDF5EF"));

		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		buttonPanel.setBackground(Color.decode("#DDF5EF"));
		
		ImageIcon icon = new ImageIcon("icon.png");
		
        JLabel title = new JLabel("Library Catalog Access", icon, JLabel.CENTER);
        title.setFont(new Font("sansserif", Font.BOLD, 20));
        title.setForeground(Color.GRAY);
        titlePanel.add(title);
		
		JButton studentButton = new JButton("Student");
        buttonPanel.add(studentButton);
        studentButton.setForeground(Color.BLACK);
        
		studentButton.addActionListener(this);
		JButton staffButton = new JButton("Staff");
        buttonPanel.add(staffButton);
        staffButton.setForeground(Color.BLACK);
		staffButton.addActionListener(this);
		
        setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		String buttonString = e.getActionCommand();
		if (buttonString.equals("Student")){
			dispose();
			new StudentWindow();
		}
		else if (buttonString.equals("Staff")){
			dispose();
			new StaffLogin();
		}
		else
			System.out.println("Unexpected error.");
	}	
}

