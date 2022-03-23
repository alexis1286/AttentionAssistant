package AttentionAssistant;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.UIManager.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.io.File;

public class Login {
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	JLabel appLabel = new JLabel("The Attention Assistant");
	JLabel loglbl = new JLabel("LOGIN");
	JLabel userlbl = new JLabel("Username: ");
	JLabel passlbl = new JLabel("Password: ");
	JLabel newlbl = new JLabel("New Here? ");
	
	JTextField user = new JTextField();
	JPasswordField pass = new JPasswordField();
	
	JButton loginbut=new JButton("Sign In");
	JButton signbut= new JButton("Sign Up");
	private int mouseX;
	private int mouseY;
	
	
	JFrame frame;
    JPanel WelcomePanel, LoginPanel, RegisterPanel,cardPane;
    CardLayout card;
	public Login() {
	
	}
	
	private JPanel initializepage() {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		panel.setLayout(null);

		JButton backButton = new JButton("Back");
		
		
		appLabel.setBounds(225, 0, 280, 100);
		appLabel.setForeground(new Color(255, 255, 255));
		appLabel.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		//appLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(appLabel);
		
		
		loglbl.setBounds(305, 80, 280, 100);
		loglbl.setForeground(new Color(210, 220, 230));
		loglbl.setFont(new Font("Dosis SemiBold",Font.PLAIN,20));
		panel.add(loglbl);
		
		
		
		 userlbl.setBounds(170, 180, 200, 100); 
		 userlbl.setForeground(new Color(255, 255, 255));
		 userlbl.setFont(new Font("Dosis SemiBold",Font.BOLD,18));
		 panel.add(userlbl);
		 
		 user.setBounds(290, 220, 180, 23);
		 user.setBackground(aa_purple);
		 panel.add(user);
        

		 passlbl.setBounds(173, 230, 200, 100); 
		 passlbl.setForeground(new Color(255, 255, 255));
		 passlbl.setFont(new Font("Dosis SemiBold",Font.BOLD,18));
		 panel.add(passlbl);
		 
		 pass.setBounds(290, 268, 180, 23);
		 pass.setBackground(aa_purple);
		 panel.add(pass);
		 
		 loginbut.setBounds(300, 340, 97, 35);
		 loginbut.setHorizontalTextPosition(SwingConstants.CENTER);
		 loginbut.setVerticalTextPosition(SwingConstants.CENTER);
		 loginbut.setFont(new Font("Dosis SemiBold", Font.BOLD, 17));
		 loginbut.setBorderPainted(false);
		 loginbut.setBackground(aa_purple);
		 loginbut.setForeground(Color.WHITE);
		 panel.add(loginbut);
		 
	     newlbl.setBounds(230, 430, 200, 100); 
	     newlbl.setForeground(new Color(255, 255, 255));
	     newlbl.setFont(new Font("Dosis SemiBold",Font.BOLD,15));
	     panel.add(newlbl);
		 
		 signbut.setBounds(315, 463, 95, 35);
		 signbut.setHorizontalTextPosition(SwingConstants.CENTER);
		 signbut.setVerticalTextPosition(SwingConstants.CENTER);
		 signbut.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
		 signbut.setBorderPainted(false);
		 signbut.setBackground(Color.white);
		 signbut.setForeground(aa_purple);
		 panel.add(signbut);

		 
		 backButton.setBounds(50, 500, 78, 40);
	     backButton.setBorderPainted(false);
	     backButton.setBackground(Color.WHITE);
	     backButton.setForeground(new Color(159,89,155));
	     backButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
	     backButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	card.previous(cardPane);
	        }});
	     panel.add(backButton);

		return panel;
	}
	
	
	private JPanel welcomepage(CardLayout card) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		panel.setLayout(null);
		
		
	
		//TODO add logo
		JLabel Label = new JLabel("The Attention Assistant");
		JLabel option = new JLabel("Please login or regiser for an account");
		JButton login=new JButton("Login");
		JButton register= new JButton("Register"); 
		
		Label.setBounds(225, 50, 280, 100);
		Label.setForeground(new Color(255, 255, 255));
		Label.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		panel.add(Label);
		
		
		

		option.setBounds(200, 150, 280, 100);
		option.setForeground(new Color(255, 255, 255));
		option.setFont(new Font("Dosis SemiBold",Font.BOLD,15));
		panel.add(option);
		
	
		 
		 login.setBounds(225, 300, 97, 35);
		 login.setHorizontalTextPosition(SwingConstants.CENTER);
		 login.setVerticalTextPosition(SwingConstants.CENTER);
		 login.setFont(new Font("Dosis SemiBold", Font.BOLD, 17));
		 login.setBorderPainted(false);
		 login.setBackground(aa_purple);
		 login.setForeground(Color.WHITE);
		 login.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	card.next(cardPane);
	        }});
		 panel.add(login);
		 
		 
		 register.setBounds(350, 300, 95, 35);
		 register.setHorizontalTextPosition(SwingConstants.CENTER);
		 register.setVerticalTextPosition(SwingConstants.CENTER);
		 register.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
		 register.setBorderPainted(false);
		 register.setBackground(Color.white);
		 register.setForeground(aa_purple);
		 register.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	card.last(cardPane);
	        }});
		 panel.add(register);
		 

		return panel;
	}
	private JPanel Registerpage(CardLayout card) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		panel.setLayout(null);
		
		//TODO add logo
		JLabel firstName = new JLabel("First Name: ");
		JLabel Username = new JLabel("Username: ");
		JLabel password = new JLabel("Password: ");
		JLabel password2 = new JLabel("Re-enter Password: ");
	
		JTextField firstnametext = new JTextField();
		JTextField usernametext = new JTextField();

		
		JLabel register = new JLabel("Register");
		
		JPasswordField passwordtext = new JPasswordField();
		JPasswordField reenterpasswordtext = new JPasswordField();
		
		JButton accButton = new JButton("Create Account");
	    JButton backButton = new JButton("Back");
		
		
		register.setBounds(225, 0, 280, 100);
		register.setForeground(aa_purple);
		register.setFont(new Font("Dosis SemiBold",Font.BOLD,40));
		register.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(register);

		
		firstName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		firstName.setBounds(300, 182, 270, 28);
		firstName.setForeground(aa_purple);
		firstName.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(firstName);
        
		firstnametext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		firstnametext.setBounds(398, 182, 270, 28);
	    firstnametext.setForeground(Color.BLACK);
        firstnametext.setToolTipText("First name");
        firstnametext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        panel.add(firstnametext);
        
        Username.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Username.setBounds(300, 242, 270, 28);
        Username.setForeground(aa_purple);
        Username.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(Username);
        
        
        usernametext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        usernametext.setBounds(398, 242, 270, 28);
        usernametext.setToolTipText("Last name");
        usernametext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        usernametext.setForeground(Color.BLACK);
        panel.add(usernametext);
        
        password.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        password.setBounds(300, 302, 270, 28);
        password.setForeground(aa_purple);
        password.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(password);
        
        passwordtext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        passwordtext.setBounds(398, 302, 270, 28);
        passwordtext.setToolTipText("Email address");
        passwordtext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        passwordtext.setForeground(Color.BLACK);
        panel.add(passwordtext);
        
        
        password2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        password2.setBounds(250, 362, 270, 28);
        password2.setForeground(aa_purple);
        password2.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(password2);
        
        reenterpasswordtext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        reenterpasswordtext.setBounds(398, 362, 270, 28);
        reenterpasswordtext.setForeground(Color.BLACK);
        reenterpasswordtext.setToolTipText("Password");
        reenterpasswordtext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        panel.add(reenterpasswordtext);
        
        
        accButton.setBounds(397, 425, 200, 37);
        accButton.setBorderPainted(false);
        accButton.setBackground(new Color(159,89,155));
        accButton.setForeground(Color.WHITE);
        accButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(accButton);
        
        backButton.setBounds(397, 490, 78, 40);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(159,89,155));
        backButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	card.first(cardPane);
        }});
        panel.add(backButton);
       


		return panel;
	}
	public void run_login() {
		//EventQueue.invokeLater(new Runnable(){
			//@Override
			//public void run() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// TODO add title panel frame.setUndecorated(true);
		frame.setTitle("The Attention Assistant");
        frame.setBackground(aa_grey);
        frame.setPreferredSize(new Dimension(700, 600)); 
        //makes frame and contents visible
    
        card = new CardLayout();
        LoginPanel = new JPanel();
        RegisterPanel = new JPanel();
        cardPane = new JPanel();
        LoginPanel = initializepage();
        RegisterPanel = Registerpage(card);
        WelcomePanel = welcomepage(card);
        
       //initializes the welcome panel
        
    
        
        
    	
    	
    	cardPane.setLayout(card);
        cardPane.setBounds(300, 50, 700, 600);
    	cardPane.add(WelcomePanel, "Welcome Panel");
        cardPane.add(LoginPanel, "Login Panel");
        cardPane.add(RegisterPanel, "Register Panel");
            
        
        cardPane.addMouseListener(new MouseAdapter(){
			@Override 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
    
    	
//    	welcomepanel = new JPanel(new CardLayout());
//        //panel for login page
//        welcomepanel = initializewelcome();
//        loginPanel.add("WelcomePanel", welcomepanel);
//        logincardlayout.show(loginPanel, "WelcomePanel");
        frame.getContentPane().add(cardPane);
        frame.setPreferredSize(new Dimension(700, 600)); 
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//addActionEvent();
			}
		//});
	//}

}