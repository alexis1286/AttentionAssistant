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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
	public String userinput = "";
	
	JFrame frame;
    JPanel WelcomePanel, LoginPanel, RegisterPanel,cardPane;
    CardLayout card;
	public Login() {
	
	}
	private JPanel titlePanel() {
		JPanel panel = new JPanel();
		//panel.setBorder(line);
		//aligns buttons in title panel from right -> left
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//makes title panel background grey
		panel.setBackground(aa_grey);
		//creates border and sets to purple
		panel.setBorder(BorderFactory.createLineBorder(aa_purple));
		//creates label 
		JLabel title = new JLabel("Pomodoro Timer");
		//makes font color white
		title.setForeground(Color.white);
		//sets font, size, and bold
		title.setFont(new Font("Serif", Font.BOLD, 18));
		
		//reads in images for the close and guide buttons
		BufferedImage ci = null;
		BufferedImage gi = null;
		try {
			ci = ImageIO.read(new File("images/exit_circle.png"));
			gi = ImageIO.read(new File("images/guide.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		/*
		 * allows drag and drop of frame
		 */
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
			}
		});
		
		panel.addMouseListener(new MouseAdapter(){
			@Override 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		//creates close button with close icon and no background
		Image c_img = ci.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		Icon close = new ImageIcon(c_img);
		JButton close_window = new JButton(close);
		close_window.setBorderPainted(false);
		close_window.setContentAreaFilled(false);
		close_window.setFocusPainted(false);
		close_window.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//close window without saving info
        		frame.dispose();
        }});
		
		//create guide button with guide icon and no background
		Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon guideIcon = new ImageIcon(g_img);
		JButton guide = new JButton(guideIcon);
		guide.setBorderPainted(false);
		guide.setContentAreaFilled(false);
		guide.setFocusPainted(false);
		
		//adds title JLabel, empty space, then guide button and close button
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(275, 0)));
		panel.add(guide);
		panel.add(close_window);
		
		//returns panel
		return panel;
		
	}
	private JPanel initializepage(CardLayout card) {
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
		 signbut.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	card.next(cardPane);
	        }});
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

	     //SearchUser_account feed in the username and password they inptu as string db.searchuser_Acc doesnt already exist - esp the username
	     //SearchParent_accountr same thing -  return null if its not there 
	     //if it doesnt return from either - make an account
	     //checkbox for user or parent acc
	   
	     ////adduser_Account for adolecencts create user acc object construcotr at line 31 in useraccount.java try to pass null in userID if not passing 1 or soemthing and work from there 
	     
	     
	     //once a user has been added, either login or w autologin, it has to search for that user via the userid so they can open the parent or child portal 
	     //parent acc does not have a section for a name so just disregaurd for checking 
	  
	     
	     //forgot password registration form needs to have sercuirty questions that they can choose from around 6 can do 2-3 and they have a key organized store the hashes from the look for hacking alogrithms 
	     
	     //maybe make parent/child button to push into the parent or child portals 
		return panel;
	}
	
	private JPanel welcomepage(CardLayout card) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		panel.setLayout(null);
		
		AttentionAssistantDriver ad  = new AttentionAssistantDriver();
	
		//TODO add logo
		JLabel Label = new JLabel("The Attention Assistant");
		JLabel option = new JLabel("Please login or register for an account");
		JButton login=new JButton("Login");
		JButton register= new JButton("Register"); 
		JButton parentportal=new JButton("Parent Portal");
		JButton ChildPortal= new JButton("Child Portal"); 
		
		Label.setBounds(225, 50, 280, 100);
		Label.setForeground(new Color(255, 255, 255));
		Label.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		panel.add(Label);
		
		
		

		option.setBounds(200, 150, 280, 100);
		option.setForeground(new Color(255, 255, 255));
		option.setFont(new Font("Dosis SemiBold",Font.BOLD,15));
		panel.add(option);
		
	
		
		parentportal.setBounds(225, 400, 97, 35);
		parentportal.setHorizontalTextPosition(SwingConstants.CENTER);
		parentportal.setVerticalTextPosition(SwingConstants.CENTER);
		parentportal.setFont(new Font("Dosis SemiBold", Font.BOLD, 17));
		parentportal.setBorderPainted(false);
		parentportal.setBackground(aa_purple);
		parentportal.setForeground(Color.WHITE);
		parentportal.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open paernt portal
	        }});
		 panel.add(parentportal);
		 
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
		 
		 
		 ChildPortal.setBounds(350, 400, 95, 35);
		 ChildPortal.setHorizontalTextPosition(SwingConstants.CENTER);
		 ChildPortal.setVerticalTextPosition(SwingConstants.CENTER);
		 ChildPortal.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
		 ChildPortal.setBorderPainted(false);
		 ChildPortal.setBackground(Color.white);
		 ChildPortal.setForeground(aa_purple);
		 ChildPortal.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	//open child portal
	        
	        }});
		 panel.add(ChildPortal);
		 
		 
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
	private JPanel Registerpage(CardLayout card, DataBase db) {
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
		
		JButton Child = new JButton("Child");
	    JButton Parent = new JButton("Parent");
		JButton accButton = new JButton("Create Account");
	    JButton backButton = new JButton("Back");
	    JButton loginButton = new JButton("Login");
		
		register.setBounds(225, 0, 280, 100);
		register.setForeground(aa_purple);
		register.setFont(new Font("Dosis SemiBold",Font.BOLD,40));
		register.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(register);

		
		Child.setBounds(397, 100, 78, 40);
		Child.setBorderPainted(false);
		Child.setBackground(aa_purple);
		Child.setForeground(Color.WHITE);
		Child.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
		Child.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userinput = "Child";
        }});
        panel.add(Child);
        
        Parent.setBounds(525, 100, 95, 40);
        Parent.setBorderPainted(false);
        Parent.setBackground(aa_purple);
        Parent.setForeground(Color.WHITE);
        Parent.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
        Parent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userinput = "Parent";
        }});
        panel.add(Parent);
	        
		firstName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		firstName.setBounds(300, 182, 270, 28);
		firstName.setForeground(aa_purple);
		firstName.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(firstName);
        
		firstnametext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		firstnametext.setBounds(398, 182, 270, 28);
	    firstnametext.setForeground(Color.BLACK);
        firstnametext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        panel.add(firstnametext);
        
        Username.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        Username.setBounds(300, 242, 270, 28);
        Username.setForeground(aa_purple);
        Username.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        panel.add(Username);
        
        
        usernametext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        usernametext.setBounds(398, 242, 270, 28);
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
        reenterpasswordtext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        panel.add(reenterpasswordtext);
        
        
        accButton.setBounds(397, 425, 200, 37);
        accButton.setBorderPainted(false);
        accButton.setBackground(aa_purple);
        accButton.setForeground(Color.WHITE);
        accButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
        accButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		String pwd = new String(passwordtext.getPassword());
        		String usr = new String(usernametext.getText());
        		String fn = new String(firstnametext.getText());
        		User_Account UserAccount = db.SearchUser_Account(usr, pwd);
        		Parent_Account ParentAccount = db.SearchParent_Account(usr,pwd);
        		User_Account addChildUser = new User_Account();
        
        		if(userinput == "Child") {
        			if(UserAccount.getUsername() == "" && UserAccount.getPassword() == "" &&  ParentAccount.getPassword() == "" && ParentAccount.getUsername() == "") {
        				  //User_Account account = User_Account();
        				addChildUser.setUsername(usr);
        				addChildUser.setPassword(pwd);
        				addChildUser.setName(fn);
        				db.AddUser_Account(addChildUser);
        				  JOptionPane.showMessageDialog(null, "Successfully Registered Child Account!","Confirmation", JOptionPane.WARNING_MESSAGE);
        			} 
        			else {
        				
        				 JOptionPane.showMessageDialog(null, "Username in use!","Confirmation", JOptionPane.WARNING_MESSAGE);
        			}
        		}
        		else if(userinput == "Parent") {
        			if(UserAccount.getUsername() == "" && UserAccount.getPassword() == "" &&  ParentAccount.getPassword() == "" && ParentAccount.getUsername() == "") {
            			  //User_Account account = User_Account();
      				
      				  JOptionPane.showMessageDialog(null, "Successfully Registered Child Account!","Confirmation", JOptionPane.WARNING_MESSAGE);
      			} 
      			else {
      				
      				 JOptionPane.showMessageDialog(null, "Username in use!","Confirmation", JOptionPane.WARNING_MESSAGE);
      			}
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "You must select a child or parent account to register!","Confirmation", JOptionPane.WARNING_MESSAGE);
        		}
        		System.out.print(UserAccount.getUsername());
        	
        }});
        panel.add(accButton);
        
        loginButton.setBounds(500, 490, 78, 40);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(aa_purple);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	//redirect the user to the login page
        	card.previous(cardPane);
        }});
        panel.add(loginButton);
       
        
        backButton.setBounds(397, 490, 78, 40);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(aa_purple);
        backButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	card.first(cardPane);
        }});
        panel.add(backButton);
        
        
    
        //TODO add a login button by the back button
		return panel;
	}
	public void run_login(DataBase db) {
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
        LoginPanel = initializepage(card);
        RegisterPanel = Registerpage(card,db);
        WelcomePanel = welcomepage(card);
     
    	
    	
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
    
        frame.getContentPane().add(cardPane);
        frame.setPreferredSize(new Dimension(700, 600)); 
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

			}
	

}