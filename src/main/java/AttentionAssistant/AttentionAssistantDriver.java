package AttentionAssistant;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;

public class AttentionAssistantDriver {
	static Color aa_grey = new Color(51,51,51);
	static Color aa_purple = new Color(137,31,191);
	static JLabel appLabel = new JLabel("The Attention Assistant");
	private static int mouseX;
	private static int mouseY;
	public static String userinput = "";
	
	static JFrame frame;
    static JPanel WelcomePanel;
	static JPanel LoginPanel;
	static JPanel RegisterPanel;
	static JPanel cardPane;
    static CardLayout card;
    
	static DataBase db = new DataBase();	
	static Observer observer = new Observer();
	static Priority_Manager pm;
	static Pomodoro_Timer pomo = new Pomodoro_Timer();
	static Negative_Thought_Burner ntb = new Negative_Thought_Burner();
	static Happy_Thought_Button htb = new Happy_Thought_Button(db);
	static Free_Thought_Space fts = new Free_Thought_Space();

	
	public static void main(String[] args) throws Exception {
		run_login();
	
	}
		private static JPanel titlePanel(JFrame frame) {
			JPanel panel = new JPanel();
			//panel.setBorder(line);
			//aligns buttons in title panel from right -> left
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

			//makes title panel background grey
			panel.setBackground(aa_grey);
			//creates border and sets to purple
			panel.setBorder(BorderFactory.createLineBorder(aa_purple));
			//creates label 
			JLabel title = new JLabel("The Attention Assistant",SwingConstants.RIGHT);
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
		private static JPanel initializepage(CardLayout card, JFrame frame) {
			JPanel panel = new JPanel();
			panel.setBackground(aa_grey);
			panel.setLayout(null);

			
			JLabel loglbl = new JLabel("LOGIN");
			JLabel userlbl = new JLabel("Username: ");
			JLabel passlbl = new JLabel("Password: ");
			JLabel newlbl = new JLabel("New Here? ");
			
			JTextField user = new JTextField();
			JPasswordField pass = new JPasswordField();
			
			JButton loginbut=new JButton("Sign In");
			JButton signbut= new JButton("Sign Up");
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
			 loginbut.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		
		        		
		        		String pwd = new String(pass.getPassword());
		        		String usr = new String(user.getText());
		        	
		        		
		        		db.DatabaseSetUp();
		        		User_Account UserAccount = db.SearchUser_Account(usr, pwd);
		        		Parent_Account ParentAccount = db.SearchParent_Account(usr,pwd);
		        		//this is looking for an acc with this exact password and username, so i have to check the pass and user
		        
		        		
		        		//TODO dispose frame when sucessful login
		        		if(UserAccount.getPassword().equals(pwd) == true && UserAccount.getUsername().equals(usr) == true) {
		        			JFrame success = new JFrame();
	        				JOptionPane.showMessageDialog(success, "Sucessfully Logged into Child account!! Logging in now...");
	        				
	        				int userid = UserAccount.getUserID();
	        				frame.dispose();
	        				success.dispose();
	        				childPortal(userid);
		        		}
		        		else if (ParentAccount.getPassword().equals(pwd) == true && ParentAccount.getUsername().equals(usr) == true) {
		        			JFrame success = new JFrame();
	        				JOptionPane.showMessageDialog(success, "Sucessfully Logged into Parent account!! Logging in now...");
	        				
	        				int parentid = ParentAccount.getParentID();
	        				frame.dispose();
	        				success.dispose();
	        				parentPortal(parentid);
		        		}
		        		else {
		        			JFrame errorframe = new JFrame();
	        				JOptionPane.showMessageDialog(errorframe, "Incorrect Username/Password! Please try again!");
		        		}
		        	
		        }});
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
		     //if it doesnt return from either - make an account -DONE
		     //checkbox for user or parent acc -DONE
		   
		     ////adduser_Account for adolecencts create user acc object construcotr at line 31 in useraccount.java try to pass null in userID if not passing 1 or soemthing and work from there 
		     
		     
		     //once a user has been added, either login or w autologin, it has to search for that user via the userid so they can open the parent or child portal 
		     //parent acc does not have a section for a name so just disregaurd for checking 
		  
		     
		     //forgot password registration form needs to have sercuirty questions that they can choose from around 6 can do 2-3 and they have a key organized store the hashes from the look for hacking alogrithms 
		     
		     //maybe make parent/child button to push into the parent or child portals -DONE
			return panel;
		}
		
		private static JPanel welcomepage(CardLayout card, JFrame frame) {
			JPanel panel = new JPanel();
			panel.setBackground(aa_grey);
			panel.setLayout(null);
		
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
			
		
			
			parentportal.setBounds(185, 400, 140, 35);
			parentportal.setHorizontalTextPosition(SwingConstants.CENTER);
			parentportal.setVerticalTextPosition(SwingConstants.CENTER);
			parentportal.setFont(new Font("Dosis SemiBold", Font.BOLD, 17));
			parentportal.setBorderPainted(false);
			parentportal.setBackground(aa_purple);
			parentportal.setForeground(Color.WHITE);
			parentportal.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//open paernt portal
		        		parentPortal(1);
		        		frame.dispose();
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
			 
			 
			 ChildPortal.setBounds(350, 400, 125, 35);
			 ChildPortal.setHorizontalTextPosition(SwingConstants.CENTER);
			 ChildPortal.setVerticalTextPosition(SwingConstants.CENTER);
			 ChildPortal.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
			 ChildPortal.setBorderPainted(false);
			 ChildPortal.setBackground(Color.white);
			 ChildPortal.setForeground(aa_purple);
			 ChildPortal.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        	//open child portal
		        	childPortal(1);
		        	frame.dispose();
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
		private static JPanel Registerpage(CardLayout card,JFrame frame) {
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
	        		Child.setBackground(Color.WHITE);
	        		Child.setForeground(aa_purple);
	        		 Parent.setBackground(aa_purple);
	      	        Parent.setForeground(Color.WHITE);
	      	        
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
	        		Parent.setBackground(Color.WHITE);
	        		 Parent.setForeground(aa_purple);
	        		 Child.setBackground(aa_purple);
	     			Child.setForeground(Color.WHITE);
	        		
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
	        		String reenterPwd = new String(reenterpasswordtext.getPassword());
	        		String usr = new String(usernametext.getText());
	        		String fn = new String(firstnametext.getText());
	        		
	        		
	        		
	        		db.DatabaseSetUp();
	        		User_Account UserAccount = db.SearchUser_Account(usr, pwd);
	        		Parent_Account ParentAccount = db.SearchParent_Account(usr,pwd);
	        		//this is looking for an acc with this exact password and username, so i have to check the pass and user
	        		User_Account addChildUser = new User_Account();
	        		Parent_Account addParentUser = new Parent_Account();
	        	
	        		
	        		if(userinput == "Child") {
	        			if(pwd.equals(reenterPwd) == false) {
	        				JOptionPane.showMessageDialog(null, "Password do not match! Please reenter your password!","Confirmation", JOptionPane.WARNING_MESSAGE);
		        			
		        		}
	        			else {
	        				if(UserAccount.getUsername().isEmpty() == true  &&  ParentAccount.getUsername().isEmpty() == true ) {
	        					addChildUser.setUsername(usr);
		        				addChildUser.setPassword(pwd);
		        				addChildUser.setName(fn);
		        				db.AddUser_Account(addChildUser);
		        				
		        				int userid = UserAccount.getUserID();
		        				
		        			
		        				JFrame success = new JFrame();
		        				JOptionPane.showMessageDialog(success, "Successfully Registered Child Account! Logging in now...");
		        				
		        				frame.dispose();
		        				success.dispose();
		        				childPortal(userid);
	        					
	        				}
		        			else  {
		        			
		        			
		        				JOptionPane.showMessageDialog(null, "Username in use!","Confirmation", JOptionPane.WARNING_MESSAGE);
		        			}
	        			}
	        				
	        
	        		}
	        		else if(userinput == "Parent") {
	        			if(pwd.equals(reenterPwd) == false) {
	        				JOptionPane.showMessageDialog(null, "Password do not match! Please reenter your password!","Confirmation", JOptionPane.WARNING_MESSAGE);
		        			
		        		}
	        			else {
	        					if(UserAccount.getUsername().isEmpty() == true &&  ParentAccount.getUsername().isEmpty() == true) {
		        				
		        				addParentUser.setUsername(usr);
		        				addParentUser.setPassword(pwd);
		        				db.AddParent_Account(addParentUser);
		        				int Parentid = ParentAccount.getParentID();
		      				
		        				JFrame errorframe = new JFrame();
		        				JOptionPane.showMessageDialog(errorframe, "Successfully Registered Parent Account! Logging in now...");
		        				
		        				frame.dispose();
		        				errorframe.dispose();
		        				parentPortal(Parentid);
	        				} 
			      			else {
				      				
				      				 JOptionPane.showMessageDialog(null, "Username in use!","Confirmation", JOptionPane.WARNING_MESSAGE);
				      			}
	        		}
	        				
	        		
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(null, "You must select a child or parent account to register!","Confirmation", JOptionPane.WARNING_MESSAGE);
	        		}
	        	
	        	
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
		
		private static void parentPortal(int userID) {
			Parent_Account parent = new Parent_Account(userID,"username1","pw1");
			Parent_Bar parentBar = new Parent_Bar(parent,db);
			parentBar.run_parent_bar();
		}
		
		private static void childPortal(int userID) {
			Settings settings = new Settings(userID);
			Nav_Bar navbar = new Nav_Bar(settings);
			//db.DatabaseSetUp();
					
			/**
			 * loading default happy media library into database 
			 * this will take place just once during user registration
			 * will remove from driver after user registration is created
			 */
			Media media1 = new Media("happyThoughtMedia/gratisography-447H-free-stock-photo.jpg");
			db.AddMedia(media1, userID);
			Media media2 = new Media("happyThoughtMedia/78nF.gif");
			db.AddMedia(media2, userID);
			Media media3 = new Media("happyThoughtMedia/231582875_f219808478_o.jpg");
			db.AddMedia(media3, userID);
			Media media4 = new Media("happyThoughtMedia/jVo.gif");
			db.AddMedia(media4, userID);
			Media media5 = new Media("happyThoughtMedia/alex-vinogradov--wHZZ-cg7rk-unsplash.jpg");
			db.AddMedia(media5, userID);
			Media media6 = new Media("happyThoughtMedia/PB35.gif");
			db.AddMedia(media6, userID);
			Media media7 = new Media("happyThoughtMedia/daniel-sessler-9Nn21mIKP1w-unsplash.jpg");
			db.AddMedia(media7, userID);
			Media media8 = new Media("happyThoughtMedia/QHa.gif");
			db.AddMedia(media8, userID);
			Media media9 = new Media("happyThoughtMedia/max-lissenden-snYLMKphCf4-unsplash.jpg");
			db.AddMedia(media9, userID);
			Media media10 = new Media("happyThoughtMedia/Z2QS.gif");
			db.AddMedia(media10, userID);
			Media media11 = new Media("happyThoughtMedia/rod-long-ogWhdXOl5qY-unsplash.jpg");
			db.AddMedia(media11, userID);
			Media media12 = new Media("happyThoughtMedia/4OKm.gif");
			db.AddMedia(media12, userID);
			Media media13 = new Media("happyThoughtMedia/yusuf-onuk-uzZgdFKisng-unsplash.jpg");
			db.AddMedia(media13, userID);
			
			/**
			 * get settings
			 * this db.AddSettings(settings) call is temporary for testing purposes until user class is created
			 * 
			 * db.AddSettings(settings, userID) will be called from during the registration process for a new user
			 * db.selectSettings(userID) will be called during the login process for a returning user
			 */
			db.AddSettings(settings, userID); 
			
			try {
				pm = new Priority_Manager(db,observer, pomo);
				navbar.run_nav_bar(userID,db,navbar,settings,observer,pm,pomo,ntb,htb,fts);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   	 
			/**
			 * TEST CODE
			 * DELETE BEFORE PRODUCTION
			 */
			//Observer testObserver = new Observer();
			//Task testTask = new Task();
			
			//MouseTracker testMouse = new MouseTracker();
			//testMouse.startTracking();
			
			//testObserver.keywordsGenerator(testTask);
			
			//KeyBoardTracker testKeyboard = new KeyBoardTracker();
			//testKeyboard.startTracking(null);
		}
		
		public static void run_login() {
			//EventQueue.invokeLater(new Runnable(){
				//@Override
				//public void run() {
			JFrame frame = new JFrame();
			frame.setUndecorated(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// TODO add title panel frame.setUndecorated(true);
			frame.setTitle("The Attention Assistant");
	        frame.setBackground(aa_grey);
	        frame.setPreferredSize(new Dimension(700, 600)); 
	        //makes frame and contents visible
	    
	        
	        JPanel titlePanel = titlePanel(frame);
	        
	        card = new CardLayout();
	        LoginPanel = new JPanel();
	        RegisterPanel = new JPanel();
	        cardPane = new JPanel();
	        LoginPanel = initializepage(card,frame);
	        RegisterPanel = Registerpage(card,frame);
	        WelcomePanel = welcomepage(card,frame);
	     
	    	
	    	
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
	    	frame.getContentPane().add(titlePanel,BorderLayout.PAGE_START);
	        frame.getContentPane().add(cardPane);
	        frame.setPreferredSize(new Dimension(700, 600)); 
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);

		}
	
}
