package com.qatest.automation;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.io.BufferedReader;


public class LTO2013Menu extends JFrame {

	private JPanel contentPane;
	private String fileLocation = "c:\\LTOScreenShot\\";
	private static String login = "";
	private static String password = "";
    private static String buyer = "";
    private static String distID = "";
    private static String email = "";
    private static String phone = "";
    private static String address = "";
    private static String city = "";
    private static String state = "";
    private static String postalcode = "";
	private static LTO2013Menu frame;
	private static JTextArea output;
    private String omniLocation = "";
    private String omniLoadLocation = "";
    private static BuyerDataForm data;
    private Authentication auth = new Authentication();
    private static JCheckBox stopOnBuyer;
	/**                                                                               l
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
		screenshots.setBounds(170, 460, 176, 34);
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
		placeorders.setBounds(26, 460, 145, 34);
		contentPane.add(placeorders);

        stopOnBuyer = new JCheckBox("Stop on Buyer", false);
        stopOnBuyer.setFont(new Font("Tahoma", Font.PLAIN, 17));
        stopOnBuyer.setBackground(Color.WHITE);
        stopOnBuyer.setBounds(26, 500, 145, 34);
        contentPane.add(stopOnBuyer);
		
		final JCheckBox chckbxCheckOmniture = new JCheckBox("Check Omniture", false);
		chckbxCheckOmniture.setFont(new Font("Tahoma", Font.PLAIN, 17));
		chckbxCheckOmniture.setBackground(Color.WHITE);
		chckbxCheckOmniture.setBounds(170, 500, 176, 34);
		contentPane.add(chckbxCheckOmniture);
				
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
                                HongKong hongkong;
								if (data != null)
                                {
                                    hongkong = new HongKong(data);
                                }
                                else
                                {
                                    hongkong = new HongKong();
                                }

                                if (chckbxCheckOmniture.isSelected())
                                {
                                    hongkong.setupOmniture(omniLocation, omniLoadLocation);
                                }
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

                /*(new Thread(new Runnable() {
                    public void run() {
                        Brunei brunei = new Brunei(username, password);
                        try {
                            brunei.setUp();
                            String[] result = brunei.testBrunei(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Brunei: Passed")) {
                                bnbutton.setBackground(green);
                            } else {
                                bnbutton.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                brunei.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();  */
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
                new Thread(new Runnable() {
                    public void run() {
                        UnitedStates united;
                        if (data != null)
                        {
                            united = new UnitedStates(data);
                        }
                        else
                        {
                            united = new UnitedStates();
                        }
                        try {
                            united.setUp();
                            String result[] = united.testUnitedStates(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("United States: Passed")) {
                                us.setBackground(green);
                            } else {
                                us.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                united.tearDown();
                        } catch (Exception e1) {
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
		
		final JButton canada = new JButton("Canada");
        canada.setFont(new Font("Dialog", Font.BOLD, 11));
        canada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                output.append("Executing: Canada\n\n");
                new Thread(new Runnable() {
                    public void run() {
                        Canada cn;
                        if (data != null)
                        {
                            cn = new Canada(data);
                        }
                        else
                        {
                            cn = new Canada();
                        }
                        try {
                            cn.setUp();
                            String result[] = cn.testCanada(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Canada: Passed")) {
                                canada.setBackground(green);
                            } else {
                                canada.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                cn.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
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
                new Thread(new Runnable() {
                    public void run() {
                        Germany de;
                        if (data != null)
                        {
                            de = new Germany(data);
                        }
                        else
                        {
                            de = new Germany();
                        }

                        try {
                            de.setUp();
                            String result[] = de.testGermany(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Germany: Passed")) {
                                germany.setBackground(green);
                            } else {
                                germany.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                de.tearDown();
                        } catch (Exception e1) {
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
                new Thread(new Runnable() {
                    public void run() {
                        UnitedKingdom uKingdom;
                        if (data != null)
                        {
                           uKingdom = new UnitedKingdom(data);
                        }
                        else
                        {
                            uKingdom = new UnitedKingdom();
                        }
                        try {
                            uKingdom.setUp();
                            String result[] = uKingdom.testUnitedKingdom(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("United Kingdom: Passed")) {
                                uk.setBackground(green);
                            } else {
                                uk.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                uKingdom.tearDown();
                        } catch (Exception e1) {
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
						Austrailia au;
                        if (data != null)
                        {
                            au = new Austrailia(data);
                        }
                        else
                        {
                            au = new Austrailia();
                        }
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
                new Thread(new Runnable() {
                    public void run() {
                        Singapore sg;
                        if (data != null)
                        {
                            sg = new Singapore(data);
                        }
                        else
                        {
                            sg = new Singapore();
                        }
                        try {
                            sg.setUp();
                            if (chckbxCheckOmniture.isSelected())
                                sg.setupOmniture(omniLocation, omniLoadLocation);
                            String result[] = sg.testSingapore(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Singapore: Passed")) {
                                btnSingapore.setBackground(green);
                            } else {
                                btnSingapore.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                sg.tearDown();
                        } catch (Exception e1) {
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
		menuBar.setBounds(0, 0, 1500, 21);
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
		
		JMenuItem mntmAuthenication = new JMenuItem("Buyer Data Form");
		mntmAuthenication.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                auth.main();
            }
        });
		mnFile.add(mntmAuthenication);
		mnFile.add(mntmClearLog);
		mnFile.add(mntmExit);
		
		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);
		
		
		
		final JButton TH = new JButton("Thailand");
		TH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                output.append("Executing: Thailand\n\n");
                new Thread(new Runnable() {
                    public void run() {
                        Thailand th;
                        if (data != null)
                        {
                            th = new Thailand(data);
                        }
                        else
                        {
                            th = new Thailand();
                        }
                        try {
                            th.setUp();
                            String result[] = th.testThailand(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Thailand: Passed")) {
                                TH.setBackground(green);
                            } else {
                                TH.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                th.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
		TH.setBounds(680, 271, 126, 42);
		contentPane.add(TH);
		
		final JButton ID = new JButton("Indonesia");
		ID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.append("Executing: Indonesia\n\n");
                new Thread(new Runnable() {
                    public void run() {
                        Indonesia indo;
                        if (data != null)
                        {
                            indo = new Indonesia(data);
                        }
                        else
                        {
                            indo = new Indonesia();
                        }
                        try {
                            indo.setUp();
                            String result[] = indo.testIndonesia(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Indonesia: Passed")) {
                                ID.setBackground(green);
                            } else {
                                ID.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                indo.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
		ID.setBounds(680, 324, 126, 42);
		contentPane.add(ID);
		
		final JButton VN = new JButton("Vietnam");
		VN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.append("Executing: Vietnam\n\n");
                new Thread(new Runnable() {
                    public void run() {
                        Vietnam vn;
                        if (data != null)
                        {
                            vn = new Vietnam(data);
                        }
                        else
                        {
                            vn = new Vietnam();
                        }
                        try {
                            vn.setUp();
                            String result[] = vn.testVietnam(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Vietnam: Passed")) {
                                VN.setBackground(green);
                            } else {
                                VN.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                vn.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
		VN.setBounds(680, 379, 126, 42);
		contentPane.add(VN);
		
		final JButton MY = new JButton("Malaysia");
		MY.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.append("Executing: Malaysia\n\n");
                new Thread(new Runnable() {
                    public void run() {
                        Malaysia my;
                        if (data != null)
                        {
                            my = new Malaysia(data);
                        }
                        else
                        {
                            my = new Malaysia();
                        }
                        try {
                            my.setUp();
                            String result[] = my.testMalaysia(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Malaysia: Passed")) {
                                MY.setBackground(green);
                            } else {
                                MY.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                my.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
		MY.setBounds(680, 432, 126, 42);
		contentPane.add(MY);
		
		final JButton PH = new JButton("Philippines");
		PH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.append("Executing: Philippines\n\n");
                new Thread(new Runnable() {
                    public void run() {
                        Philippines ph;
                        if (data != null)
                        {
                            ph = new Philippines(data);
                        }
                        else
                        {
                            ph = new Philippines();
                        }
                        try {
                            ph.setUp();
                            String result[] = ph.testPhilippines(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++) {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("Philippines: Passed")) {
                                PH.setBackground(green);
                            } else {
                                PH.setBackground(red);
                            }
                            if (placeorders.isSelected())
                                ph.tearDown();
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
		PH.setBounds(680, 485, 126, 42);
		contentPane.add(PH);
		
		JMenuItem menuItem = new JMenuItem("Run All");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				us.doClick();
                canada.doClick();
				uk.doClick();
				germany.doClick();
				australia.doClick();
				bnbutton.doClick();
				hkbutton.doClick();
				btnSingapore.doClick();
				TH.doClick();
				ID.doClick();
				VN.doClick();
				MY.doClick();
				PH.doClick();
			}
		});
		mnAction.add(menuItem);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Run North America");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				us.doClick();
                canada.doClick();
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
				TH.doClick();
				ID.doClick();
				VN.doClick();
				MY.doClick();
				PH.doClick();
			}
		});
		mnAction.add(mntmRunAsia);
		
		JMenu mnOmniture = new JMenu("Omniture");
		menuBar.add(mnOmniture);
		
		JMenuItem mntmCreateCsvFile = new JMenuItem("Create CSV File");
		mntmCreateCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Save Omniture .CSV File");
                fc.setMultiSelectionEnabled(false);
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Values (CSV)", "csv");
                fc.setFileFilter(filter);
                int option = fc.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    omniLocation = fc.getSelectedFile().getAbsolutePath();
                }
			}
		});
		mnOmniture.add(mntmCreateCsvFile);
		
		JMenuItem mntmLoadCsvFile = new JMenuItem("Load CSV File");
		mntmLoadCsvFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Open Omniture .CSV File");
                fc.setMultiSelectionEnabled(false);
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Comma Separated Values (CSV)", "csv");
                fc.setFileFilter(filter);
                int option = fc.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION)
                {
                    omniLoadLocation = fc.getSelectedFile().getAbsolutePath();
                }
			}
		});
		mnOmniture.add(mntmLoadCsvFile);
		
		JMenuItem mntmResetOmnitureVariables = new JMenuItem("Reset Omniture CSV");
		mntmResetOmnitureVariables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				omniLocation = "";
				omniLoadLocation = "";
			}
		});
		mnOmniture.add(mntmResetOmnitureVariables);
		
		
			
	}

    public static boolean stopBuyer()
    {
        return stopOnBuyer.isSelected();
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
	
	public static void setLogin(String user)
	{
		login = user;
	}
	
	public static String getlogin()
	{
		return login;
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

    public static String getBuyer() {
        return buyer;
    }

    public static void setBuyer(String buyer) {
        LTO2013Menu.buyer = buyer;
    }

    public static String getDistID() {
        return distID;
    }

    public static void setDistID(String distID) {
        LTO2013Menu.distID = distID;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        LTO2013Menu.email = email;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        LTO2013Menu.phone = phone;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        LTO2013Menu.address = address;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        LTO2013Menu.city = city;
    }

    public static String getstate() {
        return state;
    }

    public static void setState(String state) {
        LTO2013Menu.state = state;
    }

    public static String getPostalcode() {
        return postalcode;
    }

    public static void setPostalcode(String postalcode) {
        LTO2013Menu.postalcode = postalcode;
    }

    public static void setBuyerDataForm()
    {
        data = new BuyerDataForm(login, password, distID, buyer, email, phone, address, city, state, postalcode);
    }

    public static BuyerDataForm getData()
    {
        return data;
    }

    public static void resetBuyerDataForm()
    {
        data = null;
    }
}
