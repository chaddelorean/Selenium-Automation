package com.qatest.automation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Authentication extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private String user;
	private String pass;
	private static Authentication frame;
	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Authentication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Authentication() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 324, 283);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(37, 38, 97, 14);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(37, 57, 260, 20);
		contentPane.add(username);
		username.setColumns(10);
		if (LTO2013Menu.getUser() != null)
			username.setText(LTO2013Menu.getUser());
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 109, 97, 14);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(37, 127, 260, 20);
		contentPane.add(password);
		if (LTO2013Menu.getPassword() != null)
			password.setText(LTO2013Menu.getPassword());
		
		JButton btnNewButton = new JButton("Set Authentication");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (username.getText() != null)
					LTO2013Menu.setUser(username.getText());
				if (password.getText() != null)
					LTO2013Menu.setPassword(password.getText());
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(37, 187, 170, 23);
		contentPane.add(btnNewButton);
	}
	
	public String getUserName()
	{
		return user;
	}
	
	public String getPassword()
	{
		return pass;
	}
}
