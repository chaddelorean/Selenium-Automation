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
    private String login = "";
    private String pass = "";
    private String buyer = "";
    private String distID = "";
    private String email = "";
    private String phone = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String postalcode = "";
	private static Authentication frame;
	private JTextField txExecID;
	private JTextField txBuyerID;
	private JTextField txEmail;
	private JTextField txPhone;
	private JTextField txAddress;
	private JTextField txCity;
	private JTextField txState;
	private JTextField txPostal;
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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
		if (LTO2013Menu.getlogin() != "" && LTO2013Menu.getData() != null)
			username.setText(LTO2013Menu.getData().getLogin());
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(37, 88, 97, 14);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(37, 102, 260, 20);
		contentPane.add(password);
        if (LTO2013Menu.getPassword() != "" && LTO2013Menu.getData() != null)
            password.setText(LTO2013Menu.getData().getPassword());

		JLabel lblDistributorId = new JLabel("Executive ID");
		lblDistributorId.setBounds(37, 133, 97, 14);
		contentPane.add(lblDistributorId);
		
		txExecID = new JTextField();
		txExecID.setColumns(10);
		txExecID.setBounds(37, 146, 260, 20);
		contentPane.add(txExecID);
        if (LTO2013Menu.getDistID() != "" && LTO2013Menu.getData() != null)
            txExecID.setText(LTO2013Menu.getData().getDistID());
		
		JLabel lblBuyerId = new JLabel("Buyer ID");
		lblBuyerId.setBounds(37, 177, 97, 14);
		contentPane.add(lblBuyerId);
		
		txBuyerID = new JTextField();
		txBuyerID.setColumns(10);
		txBuyerID.setBounds(37, 192, 260, 20);
		contentPane.add(txBuyerID);
        if (LTO2013Menu.getBuyer() != "" && LTO2013Menu.getData() != null)
            txBuyerID.setText(LTO2013Menu.getData().getBuyerID());
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(37, 223, 97, 14);
		contentPane.add(lblEmail);
		
		txEmail = new JTextField();
		txEmail.setColumns(10);
		txEmail.setBounds(37, 237, 260, 20);
		contentPane.add(txEmail);
        if (LTO2013Menu.getEmail() != "" && LTO2013Menu.getData() != null)
            txEmail.setText(LTO2013Menu.getData().getEmail());
		
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
        if (LTO2013Menu.getPhone() != "" && LTO2013Menu.getData() != null)
            txPhone.setText(LTO2013Menu.getData().getPhone());
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(37, 313, 97, 14);
		contentPane.add(lblAddress);

		
		txAddress = new JTextField();
		txAddress.setColumns(10);
		txAddress.setBounds(37, 328, 260, 20);
		contentPane.add(txAddress);
        if (LTO2013Menu.getAddress() != "" && LTO2013Menu.getData() != null)
            txAddress.setText(LTO2013Menu.getData().getAddress());
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(37, 356, 97, 14);
		contentPane.add(lblCity);
		
		txCity = new JTextField();
		txCity.setColumns(10);
		txCity.setBounds(37, 372, 260, 20);
		contentPane.add(txCity);
        if (LTO2013Menu.getCity() != "" && LTO2013Menu.getData() != null)
            txCity.setText(LTO2013Menu.getData().getCity());
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(37, 403, 97, 14);
		contentPane.add(lblState);
		
		txState = new JTextField();
		txState.setColumns(10);
		txState.setBounds(37, 417, 260, 20);
		contentPane.add(txState);
        if (LTO2013Menu.getstate() != "" && LTO2013Menu.getData() != null)
            txState.setText(LTO2013Menu.getData().getState());
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setBounds(37, 448, 97, 14);
		contentPane.add(lblPostalCode);
		
		txPostal = new JTextField();
		txPostal.setColumns(10);
		txPostal.setBounds(37, 467, 260, 20);
		contentPane.add(txPostal);
        if (LTO2013Menu.getPostalcode() != "" && LTO2013Menu.getData() != null)
            txPostal.setText(LTO2013Menu.getData().getPostalcode());

        JButton btnNewButton = new JButton("Set Form Data");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (username.getText() != null)
                    LTO2013Menu.setLogin(username.getText());
                if (password.getText() != null)
                    LTO2013Menu.setPassword(password.getText());
                if (txExecID.getText() != null)
                    LTO2013Menu.setDistID(txExecID.getText());
                if (txBuyerID.getText() != null)
                    LTO2013Menu.setBuyer(txBuyerID.getText());
                if (txCity.getText() != null)
                    LTO2013Menu.setCity(txCity.getText());
                if (txPhone.getText() != null)
                    LTO2013Menu.setPhone(txPhone.getText());
                if (txEmail.getText() != null)
                    LTO2013Menu.setEmail((txEmail.getText()));
                if (txAddress.getText() != null)
                    LTO2013Menu.setAddress(txAddress.getText());
                if (txState.getText() != null)
                    LTO2013Menu.setState(txState.getText());
                if (txPostal.getText() != null)
                    LTO2013Menu.setPostalcode(txPostal.getText());
                LTO2013Menu.setBuyerDataForm();
                frame.setVisible(false);

            }
        });
        btnNewButton.setBounds(37, 509, 170, 23);
        contentPane.add(btnNewButton);

        JButton resetDefault = new JButton("Reset Defaults");
        resetDefault.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
                LTO2013Menu.resetBuyerDataForm();
                txAddress.setText("");
                txBuyerID.setText("");
                txCity.setText("");
                txEmail.setText("");
                txExecID.setText("");
                txPhone.setText("");
                txPostal.setText("");
                txState.setText("");
                username.setText("");
                password.setText("");
                frame.setVisible(false);
            }
        });
        resetDefault.setBounds(220, 509, 170, 23);
        contentPane.add(resetDefault);
	}
	
	public String getUserName()
	{
		return login;
	}
	
	public String getPassword()
	{
		return pass;
	}

}
