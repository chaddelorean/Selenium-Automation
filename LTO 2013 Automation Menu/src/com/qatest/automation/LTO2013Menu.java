package com.qatest.automation;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuBar;


public class LTO2013Menu extends JFrame {

	private JPanel contentPane;
	private String fileLocation = "c:\\LTOScreenShot\\";
	private static String username = "";
	private static String password = "";
	private static LTO2013Menu frame;
	private static JTextArea output;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LTO2013Menu();
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
	
	public LTO2013Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/Image/4217299_1297336025.png"));
		//setIconImage(ResourceLoader.load("Image/4217299_1297336025.png"));
		setTitle("Nu Skin LTO 2013 Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1408, 792);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 569, 821, 174);
		contentPane.add(scrollPane);
		
		output = new JTextArea();
		scrollPane.setViewportView(output);
		output.setWrapStyleWord(true);
		
		final JCheckBox screenshots = new JCheckBox("Take Screenshots", false);
		screenshots.setFont(new Font("Tahoma", Font.PLAIN, 17));
		screenshots.setBackground(Color.WHITE);
		screenshots.setBounds(154, 487, 176, 34);
		contentPane.add(screenshots);
		
		final JButton hkbutton = new JButton("Hong Kong");
		hkbutton.setBounds(680, 165, 126, 42);
		hkbutton.setOpaque(true);
		contentPane.add(hkbutton);
		final Color green = new Color (0,255,0);
		final Color red = new Color (255,0,0);
		
		final JCheckBox placeorders = new JCheckBox("Place Orders", true);
		placeorders.setFont(new Font("Tahoma", Font.PLAIN, 17));
		placeorders.setBackground(Color.WHITE);
		placeorders.setBounds(26, 487, 126, 34);
		contentPane.add(placeorders);
				
		hkbutton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				output.append("Executing: Hong Kong\n\n");				

					new Thread(new Runnable()
					{
						public void run()
						{
								
							try 
							{
								HongKong hongkong = new HongKong(username, password);
								hongkong.setUp();
								String result[] = hongkong.testHongKong(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
								for (int i = 0; i < result.length; i++)
								{
									if (result[i] != null)
										output.append(result[i] + "\n");
								}
								output.append("\n");
								output.setCaretPosition(output.getDocument().getLength());
								if (result[0].equals("Hong Kong: Passed"))
								{
									hkbutton.setBackground(green);
								}
								
								else
								{
									hkbutton.setBackground(red);
								}
								if (placeorders.isSelected())
									hongkong.tearDown();
							}
							catch (Exception e1) 
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}).start();
					
				} 
				
		});
		
		
		
		final JButton bnbutton = new JButton("Brunei");
		
		bnbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.append("Executing: Brunei\n\n");
				
				new Thread(new Runnable()
				{
					public void run()
					{
						Brunei brunei = new Brunei(username, password);
						try {
							brunei.setUp();
							String[] result = brunei.testBrunei(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("Brunei: Passed"))
							{
								bnbutton.setBackground(green);
							}
							
							else
							{
								bnbutton.setBackground(red);
							}
							if (placeorders.isSelected())
								brunei.tearDown();	
						} 
						
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		
		bnbutton.setBounds(680, 112, 126, 42);
		contentPane.add(bnbutton);
		
		
		JLabel lblNewLabel = new JLabel("Automation Log");
		lblNewLabel.setBounds(26, 543, 116, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblLtoSelenium = new JLabel("LTO 2013 Selenium Automation Menu");
		lblLtoSelenium.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLtoSelenium.setBounds(409, 11, 595, 56);
		contentPane.add(lblLtoSelenium);
		
		JButton cancel = new JButton("Quit");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancel.setBounds(1123, 661, 259, 82);
		contentPane.add(cancel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("res/Image/nu-skin_f.png"));//new ImageIcon("C:\\Users\\cowens\\workspace\\LTO 2013 Automation Menu\\Image\\nu-skin_f.png"));
		lblNewLabel_1.setBounds(951, 30, 431, 395);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Click on a market to run its automation script. You may run multiple markets concurrently.");
		lblNewLabel_2.setBounds(26, 59, 780, 22);
		contentPane.add(lblNewLabel_2);
		
		JButton clearlog = new JButton("Clear Log");
		clearlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText("");
			}
		});
		clearlog.setBounds(857, 720, 111, 23);
		contentPane.add(clearlog);
		
		final JButton us = new JButton("United States");
		us.setFont(new Font("Dialog", Font.BOLD, 11));
		us.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				output.append("Executing: United States\n\n");
				new Thread(new Runnable()
				{
					public void run()
					{
						UnitedStates united = new UnitedStates(username, password);
						try {
							united.setUp();
							String result[] = united.testUnitedStates(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("United States: Passed"))
							{
								us.setBackground(green);
							}
							
							else
							{
								us.setBackground(red);
							}
							if (placeorders.isSelected())
								united.tearDown();	
						} 
						
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		
		us.setBounds(26, 112, 126, 42);
		contentPane.add(us);
		
		JLabel lblNewLabel_3 = new JLabel("North America");
		lblNewLabel_3.setBounds(26, 92, 129, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton canada = new JButton("Canada");
		canada.setBounds(26, 162, 126, 42);
		contentPane.add(canada);
		
		JLabel lblNewLabel_4 = new JLabel("Latin America");
		lblNewLabel_4.setBounds(26, 221, 129, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton colombia = new JButton("Colombia");
		colombia.setBounds(26, 246, 126, 42);
		contentPane.add(colombia);
		
		JButton mexico = new JButton("Mexico");
		mexico.setBounds(26, 299, 126, 42);
		contentPane.add(mexico);
		
		JLabel lblNewLabel_5 = new JLabel("Africa");
		lblNewLabel_5.setBounds(26, 352, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton southafrica = new JButton("South Africa");
		southafrica.setBounds(26, 369, 126, 42);
		contentPane.add(southafrica);
		
		JLabel lblNewLabel_6 = new JLabel("Europe");
		lblNewLabel_6.setBounds(201, 92, 129, 14);
		contentPane.add(lblNewLabel_6);
		
		JButton belgium = new JButton("Belgium");
		belgium.setBounds(201, 112, 126, 42);
		contentPane.add(belgium);
		
		final JButton germany = new JButton("Germany");
		germany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.append("Executing: Germany\n\n");
				new Thread(new Runnable()
				{
					public void run()
					{
						Germany de = new Germany(username, password);
						try {
							de.setUp();
							String result[] = de.testGermany(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("Germany: Passed"))
							{
								germany.setBackground(green);
							}
							
							else
							{
								germany.setBackground(red);
							}
							if (placeorders.isSelected())
								de.tearDown();	
						} 
						
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		germany.setBounds(201, 162, 126, 42);
		contentPane.add(germany);
		
		JButton netherlands = new JButton("The Netherlands");
		netherlands.setFont(new Font("Tahoma", Font.BOLD, 9));
		netherlands.setBounds(201, 218, 126, 42);
		contentPane.add(netherlands);
		
		final JButton uk = new JButton("United Kingdom");
		uk.setFont(new Font("Dialog", Font.BOLD, 10));
		uk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				output.append("Executing: United Kingdom\n\n");
				new Thread(new Runnable()
				{
					public void run()
					{
						UnitedKingdom uKingdom = new UnitedKingdom(username, password);
						try {
							uKingdom.setUp();
							String result[] = uKingdom.testUnitedKingdom(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("United Kingdom: Passed"))
							{
								uk.setBackground(green);
							}
							
							else
							{
								uk.setBackground(red);
							}
							if (placeorders.isSelected())
								uKingdom.tearDown();	
						} 
						
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		uk.setBounds(337, 112, 126, 42);
		contentPane.add(uk);
		
		JLabel lblNewLabel_7 = new JLabel("South Pacific");
		lblNewLabel_7.setBounds(505, 92, 126, 14);
		contentPane.add(lblNewLabel_7);
		
		final JButton australia = new JButton("Australia");
		australia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				output.append("Executing: Australia\n\n");
				new Thread(new Runnable()
				{
					public void run()
					{
						Austrailia au = new Austrailia(username, password);
						try {
							au.setUp();
							String result[] = au.testAustrailia(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("Austrailia: Passed"))
							{
								australia.setBackground(green);
							}
							
							else
							{
								australia.setBackground(red);
							}
							if (placeorders.isSelected())
								au.tearDown();	
						} 
						
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		australia.setBounds(505, 112, 126, 42);
		contentPane.add(australia);
		
		JButton newzealand = new JButton("New Zealand");
		newzealand.setFont(new Font("Dialog", Font.BOLD, 11));
		newzealand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		newzealand.setBounds(505, 162, 126, 42);
		contentPane.add(newzealand);
		
		JButton frenchpolynesia = new JButton("French Polynesia");
		frenchpolynesia.setFont(new Font("Tahoma", Font.BOLD, 9));
		frenchpolynesia.setBounds(505, 217, 126, 42);
		contentPane.add(frenchpolynesia);
		
		JLabel lblNewLabel_8 = new JLabel("Asia");
		lblNewLabel_8.setBounds(680, 92, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Middle East");
		lblNewLabel_9.setBounds(842, 92, 126, 14);
		contentPane.add(lblNewLabel_9);
		
		JButton israel = new JButton("Israel");
		israel.setBounds(842, 112, 126, 42);
		contentPane.add(israel);
		
		final JButton btnSingapore = new JButton("Singapore");
		btnSingapore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.append("Executing: Singapore\n\n");
				new Thread(new Runnable()
				{
					public void run()
					{
						Singapore sg = new Singapore(username, password);
						try {
							sg.setUp();
							String result[] = sg.testSingapore(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("Singapore: Passed"))
							{
								btnSingapore.setBackground(green);
							}
							
							else
							{
								btnSingapore.setBackground(red);
							}
							if (placeorders.isSelected())
								sg.tearDown();	
						} 
						
						catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}).start();
			}
		});
		btnSingapore.setBounds(680, 218, 126, 42);
		contentPane.add(btnSingapore);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1392, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(ABORT);
			}
		});
		
		JMenuItem mntmClearLog = new JMenuItem("Clear Log");
		mntmClearLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText("");
			}
		});
		
		JMenuItem mntmScreenShotLocation = new JMenuItem("Screen Shot Location");
		mntmScreenShotLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileLocation = FileChooserEx.getFileLocation();
			}
		});
		mnFile.add(mntmScreenShotLocation);
		
		JMenuItem mntmAuthenication = new JMenuItem("Authenication ");
		mntmAuthenication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Authentication auth = new Authentication();
				auth.main();
			}
		});
		mnFile.add(mntmAuthenication);
		mnFile.add(mntmClearLog);
		mnFile.add(mntmExit);
		
		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);
		
		JMenuItem menuItem = new JMenuItem("Run All");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				us.doClick();
				uk.doClick();
				germany.doClick();
				australia.doClick();
				bnbutton.doClick();
				hkbutton.doClick();
				btnSingapore.doClick();
			}
		});
		mnAction.add(menuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Run North America");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				us.doClick();
			}
		});
		mnAction.add(mntmNewMenuItem);
		
		JMenuItem mntmRunSouthPacific = new JMenuItem("Run South Pacific");
		mntmRunSouthPacific.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				australia.doClick();
			}
		});
		
		JMenuItem mntmRunEurope = new JMenuItem("Run Europe");
		mntmRunEurope.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				germany.doClick();
				uk.doClick();
			}
		});
		mnAction.add(mntmRunEurope);
		mnAction.add(mntmRunSouthPacific);
		
		JMenuItem mntmRunAsia = new JMenuItem("Run Asia");
		mntmRunAsia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bnbutton.doClick();
				hkbutton.doClick();
				btnSingapore.doClick();
			}
		});
		mnAction.add(mntmRunAsia);
			
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	public static void setUser(String user)
	{
		username = user;
	}
	
	public static String getUser()
	{
		return username;
	}
	
	public static void setPassword(String pass)
	{
		password = pass;
	}
	
	public static String getPassword()
	{
		return password;
	}
	
	public static void setOutput(String out)
	{
		output.append(out);
	}
	
}
