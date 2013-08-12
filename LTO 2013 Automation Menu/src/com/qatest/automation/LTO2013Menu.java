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

        final Color green = new Color (0,255,0);
        final Color red = new Color (255,0,0);
        final Color nuskinbutton = new Color (0,138, 176);
        final Color orangebutton = new Color (248, 164, 93);
		
		final JCheckBox screenshots = new JCheckBox("Take Screenshots", false);
		screenshots.setFont(new Font("Tahoma", Font.PLAIN, 17));
		screenshots.setBackground(Color.WHITE);
		screenshots.setBounds(170, 460, 176, 34);
		contentPane.add(screenshots);
		
		final JButton hkbutton = new JButton("Hong Kong");
        hkbutton.setBackground(nuskinbutton);
        hkbutton.setForeground(Color.white);
		hkbutton.setBounds(680, 165, 126, 42);
		hkbutton.setOpaque(true);
		contentPane.add(hkbutton);

		
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
                                    hkbutton.setForeground(Color.black);
								}
								
								else
								{
									hkbutton.setBackground(red);
                                    hkbutton.setForeground(Color.black);
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
        bnbutton.setBackground(nuskinbutton);
        bnbutton.setForeground(Color.white);
		
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
        cancel.setBackground(orangebutton);
        cancel.setForeground(Color.white);
		cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(ABORT);
            }
        });
		cancel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancel.setBounds(1123, 661, 259, 82);
		contentPane.add(cancel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("res/Image/nu-skin_f.png"));
		lblNewLabel_1.setBounds(951, 30, 431, 395);
		contentPane.add(lblNewLabel_1);

        JLabel tr90 = new JLabel("");
        tr90.setIcon(new ImageIcon(Authentication.class.getResource("/Image/tr90.png")));
        tr90.setBounds(1150, 300, 431, 395);
        contentPane.add(tr90);
		
		JLabel lblNewLabel_2 = new JLabel("Click on a market to run its automation script. You may run multiple markets concurrently.");
		lblNewLabel_2.setBounds(26, 59, 780, 22);
		contentPane.add(lblNewLabel_2);
		
		JButton clearlog = new JButton("Clear Log");
        clearlog.setBackground(orangebutton);
        clearlog.setForeground(Color.white);
		clearlog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.setText("");
			}
		});
		clearlog.setBounds(857, 720, 111, 23);
		contentPane.add(clearlog);
		
		final JButton us = new JButton("United States");
        us.setBackground(nuskinbutton);
        us.setForeground(Color.white);
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
                                us.setForeground(Color.black);
                            } else {
                                us.setBackground(red);
                                us.setForeground(Color.black);
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
        canada.setBackground(nuskinbutton);
        canada.setForeground(Color.white);
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
                                canada.setForeground(Color.black);
                            } else {
                                canada.setBackground(red);
                                canada.setForeground(Color.black);
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
        colombia.setBackground(nuskinbutton);
        colombia.setForeground(Color.white);
		colombia.setBounds(26, 246, 126, 42);
		contentPane.add(colombia);
		
		JButton mexico = new JButton("Mexico");
        mexico.setBackground(nuskinbutton);
        mexico.setForeground(Color.white);
		mexico.setBounds(26, 299, 126, 42);
		contentPane.add(mexico);
		
		JLabel lblNewLabel_5 = new JLabel("Africa");
		lblNewLabel_5.setBounds(26, 352, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton southafrica = new JButton("South Africa");
        southafrica.setBackground(nuskinbutton);
        southafrica.setForeground(Color.white);
		southafrica.setBounds(26, 369, 126, 42);
		contentPane.add(southafrica);
		
		JLabel lblNewLabel_6 = new JLabel("Europe");
		lblNewLabel_6.setBounds(201, 92, 129, 14);
		contentPane.add(lblNewLabel_6);
		
		final JButton btnbelgium = new JButton("Belgium");
        btnbelgium.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnbelgium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                output.append("Executing: Belgium\n\n");
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
                                btnbelgium.setBackground(green);
                                btnbelgium.setForeground(Color.black);
                            } else {
                                btnbelgium.setBackground(red);
                                btnbelgium.setForeground(Color.black);
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
        btnbelgium.setBackground(nuskinbutton);
        btnbelgium.setForeground(Color.white);
		btnbelgium.setBounds(201, 112, 79, 27);
		contentPane.add(btnbelgium);
		
		final JButton germany = new JButton("Germany");
		germany.setFont(new Font("Tahoma", Font.PLAIN, 9));
        germany.setBackground(nuskinbutton);
        germany.setForeground(Color.white);
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
                                germany.setForeground(Color.black);
                            } else {
                                germany.setBackground(red);
                                germany.setForeground(Color.black);
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
		germany.setBounds(201, 143, 79, 27);
		contentPane.add(germany);
		
		final JButton btnnetherlands = new JButton("Netherlands");
		btnnetherlands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: The Netherlands\n\n");
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
                                btnnetherlands.setBackground(green);
                                btnnetherlands.setForeground(Color.black);
                            } else {
                                btnnetherlands.setBackground(red);
                                btnnetherlands.setForeground(Color.black);
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
        btnnetherlands.setBackground(nuskinbutton);
        btnnetherlands.setForeground(Color.white);
        btnnetherlands.setFont(new Font("Tahoma", Font.BOLD, 7));
		btnnetherlands.setBounds(201, 174, 79, 27);
		contentPane.add(btnnetherlands);
		
		final JButton uk = new JButton("UK");
        uk.setBackground(nuskinbutton);
        uk.setForeground(Color.white);
		uk.setFont(new Font("Dialog", Font.BOLD, 9));
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
                                uk.setForeground(Color.black);
                            } else {
                                uk.setBackground(red);
                                uk.setForeground(Color.black);
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
		uk.setBounds(290, 112, 79, 27);
		contentPane.add(uk);
		
		JLabel lblNewLabel_7 = new JLabel("South Pacific");
		lblNewLabel_7.setBounds(505, 92, 126, 14);
		contentPane.add(lblNewLabel_7);
		
		final JButton australia = new JButton("Australia");
        australia.setBackground(nuskinbutton);
        australia.setForeground(Color.white);
		australia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				output.append("Executing: Australia\n\n");
				new Thread(new Runnable()
				{
					public void run()
					{
						Australia au;
                        if (data != null)
                        {
                            au = new Australia(data);
                        }
                        else
                        {
                            au = new Australia();
                        }
						try {
							au.setUp();
							String result[] = au.testAustralia(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
							for (int i = 0; i < result.length; i++)
							{
								if (result[i] != null)
									output.append(result[i] + "\n");
							}
							output.append("\n");
							output.setCaretPosition(output.getDocument().getLength());
							if (result[0].equals("Australia: Passed"))
							{
								australia.setBackground(green);
                                australia.setForeground(Color.black);
							}
							
							else
							{
								australia.setBackground(red);
                                australia.setForeground(Color.black);
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
		
		final JButton newzealand = new JButton("New Zealand");
        newzealand.setBackground(nuskinbutton);
        newzealand.setForeground(Color.white);
		newzealand.setFont(new Font("Dialog", Font.BOLD, 11));
		newzealand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                output.append("Executing: New Zealand\n\n");
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        NewZealand nz;
                        if (data != null)
                        {
                            nz = new NewZealand(data);
                        }
                        else
                        {
                            nz = new NewZealand();
                        }
                        try {
                            nz.setUp();
                            String result[] = nz.testNewZealand(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++)
                            {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("New Zealand: Passed"))
                            {
                                newzealand.setBackground(green);
                                newzealand.setForeground(Color.black);
                            }

                            else
                            {
                                newzealand.setBackground(red);
                                newzealand.setForeground(Color.black);
                            }
                            if (placeorders.isSelected())
                                nz.tearDown();
                        }

                        catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
		newzealand.setBounds(505, 162, 126, 42);
		contentPane.add(newzealand);
		
		final JButton frenchpolynesia = new JButton("French Polynesia");
        frenchpolynesia.setBackground(nuskinbutton);
        frenchpolynesia.setForeground(Color.white);
        frenchpolynesia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                output.append("Executing: French Polynesia\n\n");
                new Thread(new Runnable()
                {
                    public void run()
                    {
                        FrenchPolynesia fp;
                        if (data != null)
                        {
                            fp = new FrenchPolynesia(data);
                        }
                        else
                        {
                            fp = new FrenchPolynesia();
                        }
                        try {
                            fp.setUp();
                            String result[] = fp.testFrenchPolynesia(placeorders.isSelected(), screenshots.isSelected(), fileLocation);
                            for (int i = 0; i < result.length; i++)
                            {
                                if (result[i] != null)
                                    output.append(result[i] + "\n");
                            }
                            output.append("\n");
                            output.setCaretPosition(output.getDocument().getLength());
                            if (result[0].equals("French Polynesia: Passed"))
                            {
                                frenchpolynesia.setBackground(green);
                                frenchpolynesia.setForeground(Color.black);
                            }

                            else
                            {
                                frenchpolynesia.setBackground(red);
                                frenchpolynesia.setForeground(Color.black);
                            }
                            if (placeorders.isSelected())
                                fp.tearDown();
                        }

                        catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }).start();
            }
        });
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
        israel.setBackground(nuskinbutton);
        israel.setForeground(Color.white);
		israel.setBounds(842, 112, 126, 42);
		contentPane.add(israel);
		
		final JButton btnSingapore = new JButton("Singapore");
        btnSingapore.setBackground(nuskinbutton);
        btnSingapore.setForeground(Color.white);
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
                                btnSingapore.setForeground(Color.black);
                            } else {
                                btnSingapore.setBackground(red);
                                btnSingapore.setForeground(Color.black);
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
        TH.setBackground(nuskinbutton);
        TH.setForeground(Color.white);
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
                                TH.setForeground(Color.black);
                            } else {
                                TH.setBackground(red);
                                TH.setForeground(Color.black);
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
        ID.setBackground(nuskinbutton);
        ID.setForeground(Color.white);
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
                                ID.setForeground(Color.black);
                            } else {
                                ID.setBackground(red);
                                ID.setForeground(Color.black);
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
        VN.setBackground(nuskinbutton);
        VN.setForeground(Color.white);
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
                                VN.setForeground(Color.black);
                            } else {
                                VN.setBackground(red);
                                VN.setForeground(Color.black);
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
        MY.setBackground(nuskinbutton);
        MY.setForeground(Color.white);
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
                                MY.setForeground(Color.black);
                            } else {
                                MY.setBackground(red);
                                MY.setForeground(Color.black);
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
        PH.setBackground(nuskinbutton);
        PH.setForeground(Color.white);
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
                                PH.setForeground(Color.black);
                            } else {
                                PH.setBackground(red);
                                PH.setForeground(Color.black);
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
                newzealand.doClick();
                frenchpolynesia.doClick();
			}
		});

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
		
		final JButton btnAustria = new JButton("Austria");
		btnAustria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Austria\n\n");
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
                                btnAustria.setBackground(green);
                                btnAustria.setForeground(Color.black);
                            } else {
                                btnAustria.setBackground(red);
                                btnAustria.setForeground(Color.black);
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
		btnAustria.setForeground(Color.WHITE);
		btnAustria.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnAustria.setBackground(new Color(0, 138, 176));
		btnAustria.setBounds(201, 205, 79, 27);
		contentPane.add(btnAustria);
		
		final JButton btnDenmark = new JButton("Denmark");
		btnDenmark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Denmark\n\n");
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
                                btnDenmark.setBackground(green);
                                btnDenmark.setForeground(Color.black);
                            } else {
                                btnDenmark.setBackground(red);
                                btnDenmark.setForeground(Color.black);
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
		btnDenmark.setForeground(Color.WHITE);
		btnDenmark.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnDenmark.setBackground(new Color(0, 138, 176));
		btnDenmark.setBounds(201, 236, 79, 27);
		contentPane.add(btnDenmark);
		
		final JButton btnCzech = new JButton("Czech");
		btnCzech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Czech Republic\n\n");
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
                                btnCzech.setBackground(green);
                                btnCzech.setForeground(Color.black);
                            } else {
                                btnCzech.setBackground(red);
                                btnCzech.setForeground(Color.black);
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
		btnCzech.setForeground(Color.WHITE);
		btnCzech.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnCzech.setBackground(new Color(0, 138, 176));
		btnCzech.setBounds(201, 268, 79, 27);
		contentPane.add(btnCzech);
		
		final JButton btnFinland = new JButton("Finland");
		btnFinland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Finland\n\n");
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
                                btnFinland.setBackground(green);
                                btnFinland.setForeground(Color.black);
                            } else {
                                btnFinland.setBackground(red);
                                btnFinland.setForeground(Color.black);
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
		btnFinland.setForeground(Color.WHITE);
		btnFinland.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnFinland.setBackground(new Color(0, 138, 176));
		btnFinland.setBounds(201, 300, 79, 27);
		contentPane.add(btnFinland);
		
		final JButton btnHungary = new JButton("Hungary");
		btnHungary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Hungary\n\n");
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
                                btnHungary.setBackground(green);
                                btnHungary.setForeground(Color.black);
                            } else {
                                btnHungary.setBackground(red);
                                btnHungary.setForeground(Color.black);
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
		btnHungary.setForeground(Color.WHITE);
		btnHungary.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnHungary.setBackground(new Color(0, 138, 176));
		btnHungary.setBounds(201, 331, 79, 27);
		contentPane.add(btnHungary);
		
		final JButton btnIceland = new JButton("Iceland");
		btnIceland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Iceland\n\n");
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
                                btnIceland.setBackground(green);
                                btnIceland.setForeground(Color.black);
                            } else {
                                btnIceland.setBackground(red);
                                btnIceland.setForeground(Color.black);
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
		btnIceland.setForeground(Color.WHITE);
		btnIceland.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnIceland.setBackground(new Color(0, 138, 176));
		btnIceland.setBounds(201, 362, 79, 27);
		contentPane.add(btnIceland);
		
		final JButton btnIreland = new JButton("Ireland");
		btnIreland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Ireland\n\n");
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
                                btnIreland.setBackground(green);
                                btnIreland.setForeground(Color.black);
                            } else {
                                btnIreland.setBackground(red);
                                btnIreland.setForeground(Color.black);
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
		btnIreland.setForeground(Color.WHITE);
		btnIreland.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnIreland.setBackground(new Color(0, 138, 176));
		btnIreland.setBounds(201, 394, 79, 27);
		contentPane.add(btnIreland);
		
		final JButton btnItaly = new JButton("Italy");
		btnItaly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Italy\n\n");
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
                                btnItaly.setBackground(green);
                                btnItaly.setForeground(Color.black);
                            } else {
                                btnItaly.setBackground(red);
                                btnItaly.setForeground(Color.black);
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
		btnItaly.setForeground(Color.WHITE);
		btnItaly.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnItaly.setBackground(new Color(0, 138, 176));
		btnItaly.setBounds(290, 143, 79, 27);
		contentPane.add(btnItaly);
		
		final JButton btnLuxembourg = new JButton("LU");
		btnLuxembourg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Luxembourg\n\n");
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
                                btnLuxembourg.setBackground(green);
                                btnLuxembourg.setForeground(Color.black);
                            } else {
                                btnLuxembourg.setBackground(red);
                                btnLuxembourg.setForeground(Color.black);
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
		btnLuxembourg.setForeground(Color.WHITE);
		btnLuxembourg.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnLuxembourg.setBackground(new Color(0, 138, 176));
		btnLuxembourg.setBounds(290, 174, 79, 27);
		contentPane.add(btnLuxembourg);
		
		final JButton btnNorway = new JButton("Norway");
		btnNorway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Norway\n\n");
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
                                btnNorway.setBackground(green);
                                btnNorway.setForeground(Color.black);
                            } else {
                                btnNorway.setBackground(red);
                                btnNorway.setForeground(Color.black);
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
		btnNorway.setForeground(Color.WHITE);
		btnNorway.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnNorway.setBackground(new Color(0, 138, 176));
		btnNorway.setBounds(290, 205, 79, 27);
		contentPane.add(btnNorway);
		
		final JButton btnPoland = new JButton("Poland");
		btnPoland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Poland\n\n");
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
                                btnPoland.setBackground(green);
                                btnPoland.setForeground(Color.black);
                            } else {
                                btnPoland.setBackground(red);
                                btnPoland.setForeground(Color.black);
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
		btnPoland.setForeground(Color.WHITE);
		btnPoland.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnPoland.setBackground(new Color(0, 138, 176));
		btnPoland.setBounds(290, 236, 79, 27);
		contentPane.add(btnPoland);
		
		final JButton btnPortugal = new JButton("Portugal");
		btnPortugal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Portugal\n\n");
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
                                btnPortugal.setBackground(green);
                                btnPortugal.setForeground(Color.black);
                            } else {
                                btnPortugal.setBackground(red);
                                btnPortugal.setForeground(Color.black);
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
		btnPortugal.setForeground(Color.WHITE);
		btnPortugal.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnPortugal.setBackground(new Color(0, 138, 176));
		btnPortugal.setBounds(290, 268, 79, 27);
		contentPane.add(btnPortugal);
		
		final JButton btnRomania = new JButton("Romania");
		btnRomania.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Romania\n\n");
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
                                btnRomania.setBackground(green);
                                btnRomania.setForeground(Color.black);
                            } else {
                                btnRomania.setBackground(red);
                                btnRomania.setForeground(Color.black);
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
		btnRomania.setForeground(Color.WHITE);
		btnRomania.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnRomania.setBackground(new Color(0, 138, 176));
		btnRomania.setBounds(290, 300, 79, 27);
		contentPane.add(btnRomania);
		
		final JButton btnRussia = new JButton("Russia");
		btnRussia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Russia\n\n");
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
                                btnRussia.setBackground(green);
                                btnRussia.setForeground(Color.black);
                            } else {
                                btnRussia.setBackground(red);
                                btnRussia.setForeground(Color.black);
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
		btnRussia.setForeground(Color.WHITE);
		btnRussia.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnRussia.setBackground(new Color(0, 138, 176));
		btnRussia.setBounds(290, 331, 79, 27);
		contentPane.add(btnRussia);
		
		final JButton btnSlovakia = new JButton("Slovakia");
		btnSlovakia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Slovakia\n\n");
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
                                btnSlovakia.setBackground(green);
                                btnSlovakia.setForeground(Color.black);
                            } else {
                                btnSlovakia.setBackground(red);
                                btnSlovakia.setForeground(Color.black);
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
		btnSlovakia.setForeground(Color.WHITE);
		btnSlovakia.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnSlovakia.setBackground(new Color(0, 138, 176));
		btnSlovakia.setBounds(290, 362, 79, 27);
		contentPane.add(btnSlovakia);
		
		final JButton btnSpain = new JButton("Spain");
		btnSpain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Spain\n\n");
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
                                btnSpain.setBackground(green);
                                btnSpain.setForeground(Color.black);
                            } else {
                                btnSpain.setBackground(red);
                                btnSpain.setForeground(Color.black);
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
		btnSpain.setForeground(Color.WHITE);
		btnSpain.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnSpain.setBackground(new Color(0, 138, 176));
		btnSpain.setBounds(290, 394, 79, 27);
		contentPane.add(btnSpain);
		
		final JButton btnSweden = new JButton("Sweden");
		btnSweden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Sweden\n\n");
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
                                btnSweden.setBackground(green);
                                btnSweden.setForeground(Color.black);
                            } else {
                                btnSweden.setBackground(red);
                                btnSweden.setForeground(Color.black);
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
		btnSweden.setForeground(Color.WHITE);
		btnSweden.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnSweden.setBackground(new Color(0, 138, 176));
		btnSweden.setBounds(377, 112, 79, 27);
		contentPane.add(btnSweden);
		
		final JButton btnSwitzerland = new JButton("CH");
		btnSwitzerland.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Switzerland\n\n");
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
                                btnSwitzerland.setBackground(green);
                                btnSwitzerland.setForeground(Color.black);
                            } else {
                                btnSwitzerland.setBackground(red);
                                btnSwitzerland.setForeground(Color.black);
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
		btnSwitzerland.setForeground(Color.WHITE);
		btnSwitzerland.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnSwitzerland.setBackground(new Color(0, 138, 176));
		btnSwitzerland.setBounds(377, 143, 79, 27);
		contentPane.add(btnSwitzerland);
		
		final JButton btnUkraine = new JButton("Ukraine");
		btnUkraine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                output.append("Executing: Ukraine\n\n");
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
                                btnUkraine.setBackground(green);
                                btnUkraine.setForeground(Color.black);
                            } else {
                                btnUkraine.setBackground(red);
                                btnUkraine.setForeground(Color.black);
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

        final JButton btnFrance = new JButton("France");
        btnFrance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.append("Executing: France\n\n");
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
                                btnFrance.setBackground(green);
                                btnFrance.setForeground(Color.black);
                            } else {
                                btnFrance.setBackground(red);
                                btnFrance.setForeground(Color.black);
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

		btnFrance.setForeground(Color.WHITE);
		btnFrance.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnFrance.setBackground(new Color(0, 138, 176));
		btnFrance.setBounds(377, 174, 79, 27);
		contentPane.add(btnFrance);

        JMenuItem mntmRunEurope = new JMenuItem("Run Europe");
        mntmRunEurope.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                germany.doClick();
                uk.doClick();
                btnAustria.doClick();
                btnbelgium.doClick();
                btnCzech.doClick();
                btnDenmark.doClick();
                btnFinland.doClick();
                btnFrance.doClick();
                btnHungary.doClick();
                btnIceland.doClick();
                btnIreland.doClick();
                btnItaly.doClick();
                btnLuxembourg.doClick();
                btnnetherlands.doClick();
                btnNorway.doClick();
                btnPoland.doClick();
                btnPortugal.doClick();
                btnRomania.doClick();
                btnRussia.doClick();
                btnSlovakia.doClick();
                btnSpain.doClick();
                btnSweden.doClick();
                btnSwitzerland.doClick();
                btnUkraine.doClick();

            }
        });
        mnAction.add(mntmRunEurope);

        JMenuItem menuItem = new JMenuItem("Run All");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                us.doClick();
                canada.doClick();
                australia.doClick();
                newzealand.doClick();
                frenchpolynesia.doClick();
                bnbutton.doClick();
                hkbutton.doClick();
                btnSingapore.doClick();
                TH.doClick();
                ID.doClick();
                VN.doClick();
                MY.doClick();
                PH.doClick();
                germany.doClick();
                uk.doClick();
                btnAustria.doClick();
                btnbelgium.doClick();
                btnCzech.doClick();
                btnDenmark.doClick();
                btnFinland.doClick();
                btnFrance.doClick();
                btnHungary.doClick();
                btnIceland.doClick();
                btnIreland.doClick();
                btnItaly.doClick();
                btnLuxembourg.doClick();
                btnnetherlands.doClick();
                btnNorway.doClick();
                btnPoland.doClick();
                btnPortugal.doClick();
                btnRomania.doClick();
                btnRussia.doClick();
                btnSlovakia.doClick();
                btnSpain.doClick();
                btnSweden.doClick();
                btnSwitzerland.doClick();
                btnUkraine.doClick();
            }
        });
        mnAction.add(menuItem);
			
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
