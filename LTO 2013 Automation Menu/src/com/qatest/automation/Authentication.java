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
import javax.swing.ImageIcon;

public class Authentication extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private String user;
	private String pass;
	private static Authentication frame;
	private JTextField txExecID;
	private JTextField txBuyerID;
	private JTextField textField;
	private JTextField txPhone;
	private JTextField textField_1;
	private JTextField txCity;
	private JTextField textField_2;
	private JTextField textField_3;
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
		setTitle("Buyer Form Data");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 854, 577);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login Name");
		lblNewLabel.setBounds(37, 38, 97, 14);
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(37, 57, 260, 20);
		contentPane.add(username);
		username.setColumns(10);
		if (LTO2013Menu.getUser() != null)
			username.setText(LTO2013Menu.getUser());
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 88, 97, 14);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(37, 102, 260, 20);
		contentPane.add(password);
		if (LTO2013Menu.getPassword() != null)
			password.setText(LTO2013Menu.getPassword());
		
		JButton btnNewButton = new JButton("Set Form Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (username.getText() != null)
					LTO2013Menu.setUser(username.getText());
				if (password.getText() != null)
					LTO2013Menu.setPassword(password.getText());
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(37, 509, 170, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblDistributorId = new JLabel("Executive ID");
		lblDistributorId.setBounds(37, 133, 97, 14);
		contentPane.add(lblDistributorId);
		
		txExecID = new JTextField();
		txExecID.setColumns(10);
		txExecID.setBounds(37, 146, 260, 20);
		contentPane.add(txExecID);
		
		JLabel lblBuyerId = new JLabel("Buyer ID");
		lblBuyerId.setBounds(37, 177, 97, 14);
		contentPane.add(lblBuyerId);
		
		txBuyerID = new JTextField();
		txBuyerID.setColumns(10);
		txBuyerID.setBounds(37, 192, 260, 20);
		contentPane.add(txBuyerID);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(37, 223, 97, 14);
		contentPane.add(lblEmail);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(37, 237, 260, 20);
		contentPane.add(textField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Authentication.class.getResource("/Image/nu-skin_f.png")));
		label.setBounds(395, 38, 431, 395);
		contentPane.add(label);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(37, 268, 97, 14);
		contentPane.add(lblPhone);
		
		txPhone = new JTextField();
		txPhone.setColumns(10);
		txPhone.setBounds(37, 282, 260, 20);
		contentPane.add(txPhone);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(37, 313, 97, 14);
		contentPane.add(lblAddress);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(37, 328, 260, 20);
		contentPane.add(textField_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(37, 356, 97, 14);
		contentPane.add(lblCity);
		
		txCity = new JTextField();
		txCity.setColumns(10);
		txCity.setBounds(37, 372, 260, 20);
		contentPane.add(txCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(37, 403, 97, 14);
		contentPane.add(lblState);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(37, 417, 260, 20);
		contentPane.add(textField_2);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setBounds(37, 448, 97, 14);
		contentPane.add(lblPostalCode);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(37, 467, 260, 20);
		contentPane.add(textField_3);
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
