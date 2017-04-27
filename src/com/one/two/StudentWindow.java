package com.one.two;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentWindow extends JFrame{
	public static final int width = 600;
	public static final int height = 400;
	
	StudentWindow(){
		super("Library Management System");
		setSize(width,height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		setVisible(true);
		
		Object data[][] = new Object[5][10];
		Object columnName[] = {"Book Title","Author","Pub/Rel Date","ISBN-10", "Checked Out"};
		
		JTable table = new JTable(data,columnName);
		table.setBounds(30, 40, 100, 300);
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
		
		JPanel actionPanel = new JPanel();
		add(actionPanel, BorderLayout.SOUTH);
		actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.Y_AXIS));
		
		JPanel savePanel = new JPanel();
		actionPanel.add(savePanel);
		
		JButton borrowButton = new JButton("Borrow");
		savePanel.add(borrowButton);
		
		JPanel notePanel = new JPanel();
		actionPanel.add(notePanel);
		
		JLabel borrowNote = new JLabel("You may not check out this book. Please make another selection.");
		notePanel.add(borrowNote);
		borrowNote.setVisible(false);
		
		JPanel logoffPanel = new JPanel();
		actionPanel.add(logoffPanel);
		
		JButton logoffButton = new JButton("Log Off");
		logoffPanel.add(logoffButton);
	}
}