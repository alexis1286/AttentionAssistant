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
import javax.swing.JMenuBar;
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
	static JPanel ForgotPanel;
	static JPanel cardPane;
    static CardLayout card;
    
	static DataBase db = new DataBase();	
	static Observer observer = new Observer();
	static Priority_Manager pm;
	static Pomodoro_Timer pomo = new Pomodoro_Timer();
	static Negative_Thought_Burner ntb = new Negative_Thought_Burner();
	static Happy_Thought_Button htb = new Happy_Thought_Button(db);
	static Free_Thought_Space fts = new Free_Thought_Space();
	static LineBorder line = new LineBorder(aa_purple, 2, true);
	
	public static void main(String[] args) throws Exception {
		run_login();
	
	}
		private static JMenuBar titlePanel(JFrame frame) {
			JMenuBar title_panel = new JMenuBar();
			title_panel.setBorder(line);
			title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));	
			title_panel.setBackground(aa_grey);
			title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
			
			/*
			 * allows drag and drop of frame
			 */
			title_panel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
				}
			});
			
			title_panel.addMouseListener(new MouseAdapter(){
				@Override 
				public void mousePressed(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();
				}
			});

			JLabel title = new JLabel("The Attention Assistant");
			title.setForeground(Color.white);
			title.setBounds(0,0,200,200);
			title.setFont(new Font("Dosis SemiBold", Font.BOLD, 20));
			
			/*
			 * create icons to use as buttons for title bar
			 */
			BufferedImage ci = null;
			BufferedImage gi = null;
			BufferedImage exit = null;
			
			try {
				ci = ImageIO.read(new File("images/exit_circle.png"));
				gi = ImageIO.read(new File("images/guide.png"));
				exit = ImageIO.read(new File("images/AA_exit.png"));
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			Image c_img = ci.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
			Icon close = new ImageIcon(c_img);
			
			JButton close_window = new JButton(close);
			close_window.setBorderPainted(false);
			close_window.setContentAreaFilled(false);
			close_window.setFocusPainted(false);
			close_window.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//close window without saving 
	        		frame.dispose();
	        	
	        }});
			
			Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
			Icon guideIcon = new ImageIcon(g_img);
			
			JButton guide = new JButton(guideIcon);
			guide.setBorderPainted(false);
			guide.setContentAreaFilled(false);
			guide.setFocusPainted(false);
			
			title_panel.add(title);
			title_panel.add(Box.createRigidArea(new Dimension(350, 0)));
			title_panel.add(guide);
			title_panel.add(close_window);
			
			//returns panel
			return title_panel;
			
		}
		private static JPanel loginpage(CardLayout card, JFrame frame) {
			JPanel panel = new JPanel();
			panel.setBackground(aa_grey);
			panel.setLayout(null);

			
			BufferedImage cat2 = null;
			 BufferedImage cat3 = null;
			    try {
			    	cat2 = ImageIO.read(new File("avatarSelection/avatar_cat2.png"));
			    	cat3 = ImageIO.read(new File("avatarSelection/avatar_cat3.png"));
				
				}catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			    
			    JLabel catpic = new JLabel(new ImageIcon(cat2));
			    catpic.setBounds(500, 50, 138, 150);
			    panel.add(catpic);
			    
			    JLabel cat2pic = new JLabel(new ImageIcon(cat3));
			    cat2pic.setBounds(60, 50, 132, 150);
			    panel.add(cat2pic);
			
			
			JLabel loglbl = new JLabel("Login");
			JLabel userlbl = new JLabel("Username: ");
			JLabel passlbl = new JLabel("Password: ");
			JLabel newlbl = new JLabel("New Here? ");
			
			JTextField user = new JTextField();
			JPasswordField pass = new JPasswordField();
			
			JButton loginbut=new JButton("Sign In");
			JButton forgotpassbut=new JButton("Forgot Password?");
			JButton signbut= new JButton("Sign Up");
			JButton backButton = new JButton("Back");
			
			
//			appLabel.setBounds(230, 40, 280, 100);
//			appLabel.setForeground(new Color(255, 255, 255));
//			appLabel.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
//			//appLabel.setHorizontalAlignment(SwingConstants.CENTER);
//			panel.add(appLabel);
			
			
			loglbl.setBounds(300, 80, 280, 100);
			loglbl.setForeground(aa_purple);
			loglbl.setFont(new Font("Dosis SemiBold",Font.BOLD,40));
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
			 
			 loginbut.setBounds(300, 320, 97, 35);
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
			 
			 forgotpassbut.setBounds(260, 390, 200, 35);
			 forgotpassbut.setHorizontalTextPosition(SwingConstants.CENTER);
			 forgotpassbut.setVerticalTextPosition(SwingConstants.CENTER);
			 forgotpassbut.setFont(new Font("Dosis SemiBold", Font.BOLD, 17));
			 forgotpassbut.setBorderPainted(false);
			 forgotpassbut.setBackground(Color.WHITE);
			 forgotpassbut.setForeground(aa_purple);
			 forgotpassbut.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		card.last(cardPane);
		        		
		        		//switch to other card w forgotpass
		        	
		        }});
			 panel.add(forgotpassbut);
			 
		     newlbl.setBounds(230, 430, 200, 100); 
		     newlbl.setForeground(new Color(255, 255, 255));
		     newlbl.setFont(new Font("Dosis SemiBold",Font.BOLD,15));
		     panel.add(newlbl);
			 
			 signbut.setBounds(315, 463, 95, 35);
			 signbut.setHorizontalTextPosition(SwingConstants.CENTER);
			 signbut.setVerticalTextPosition(SwingConstants.CENTER);
			 signbut.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
			 signbut.setBorderPainted(false);
			 signbut.setBackground(aa_purple);
			 signbut.setForeground(Color.white);
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
		     //to make sure they are the user, do secruity questions store the hash of the answers and store a key of the users answers questions key and answers 2-3 secruity questons
		     //also add a hash for their password and store them into the db 
		     //maybe make parent/child button to push into the parent or child portals -DONE
		     
		     //TODO ADD BORDER WITHIN THE PM
			return panel;
		}
		private static JPanel forgetPass(CardLayout card, JFrame frame) {
			JPanel panel = new JPanel();
			panel.setBackground(aa_grey);
			panel.setLayout(null);
		

			JLabel username = new JLabel("Username: ");
			JLabel oldpass = new JLabel("Old Password: ");
			JLabel password = new JLabel("New Password: ");
			JLabel password2 = new JLabel("Re-enter Password: ");
		
			JTextField usernametext = new JTextField();
			JTextField oldpasstext = new JTextField();

			
			JLabel register = new JLabel("Forgot your password?");
			
			JPasswordField passwordtext = new JPasswordField();
			JPasswordField reenterpasswordtext = new JPasswordField();
			
			JButton Child = new JButton("Child");
		    JButton Parent = new JButton("Parent");
			JButton accButton = new JButton("Change Password");
		    JButton backButton = new JButton("Back");
		    JButton loginButton = new JButton("Login");
		    
		    BufferedImage cat3 = null;
		    try {
				
		    	cat3 = ImageIO.read(new File("avatarSelection/avatar_cat3.png"));
			
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		    
		    JLabel dinopic = new JLabel(new ImageIcon(cat3));
		    dinopic.setBounds(100, 182, 127, 150);
		    panel.add(dinopic);
		
			register.setBounds(200, 0, 360, 100);
			register.setForeground(aa_purple);
			register.setFont(new Font("Dosis SemiBold",Font.BOLD,30));
			register.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(register);

			
			Child.setBounds(397, 100, 78, 40);
			Child.setBorderPainted(false);
			Child.setBackground(aa_purple);
			Child.setForeground(Color.WHITE);
			Child.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
			Child.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//declares the string depending on who is registering
	        		userinput = "Child";
	        		//changes the color of button upon click
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
	        		//declares the string depending on who is registering
	        		userinput = "Parent";
	        		//changes the color of button upon click
	        		Parent.setBackground(Color.WHITE);
	        		 Parent.setForeground(aa_purple);
	        		 Child.setBackground(aa_purple);
	     			Child.setForeground(Color.WHITE);
	        		
	        }});
	        panel.add(Parent);
		        
	       
			username.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			username.setBounds(310, 182, 270, 28);
			username.setForeground(aa_purple);
			username.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
	        panel.add(username);
	        
			usernametext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			usernametext.setBounds(398, 182, 270, 28);
		    usernametext.setForeground(Color.BLACK);
	        usernametext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
	        panel.add(usernametext);
	        
	        oldpass.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        oldpass.setBounds(290, 242, 270, 28);
	        oldpass.setForeground(aa_purple);
	        oldpass.setFont(new Font("Dosis SemiBold", Font.BOLD, 15));
	        panel.add(oldpass);
	        
	        
	        oldpasstext.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        oldpasstext.setBounds(398, 242, 270, 28);
	        oldpasstext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
	        oldpasstext.setForeground(Color.BLACK);
	        panel.add(oldpasstext);
	        
	        password.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        password.setBounds(280, 302, 270, 28);
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
	        	
	        		String usr = new String(usernametext.getText());
	        		String oldpasst = new String(oldpasstext.getText());
	        		String pwd = new String(passwordtext.getPassword());
	        		String reenterPwd = new String(reenterpasswordtext.getPassword());
	        		
	        		
	        		db.DatabaseSetUp();
	        		User_Account UserAccount = db.SearchUser_Account(usr,oldpasst);
	        		Parent_Account ParentAccount = db.SearchParent_Account(usr,oldpasst);
	        		User_Account addChildUser = new User_Account();
	        		Parent_Account addParentUser = new Parent_Account();
	        	
	        		
	        		if(userinput == "Child") {
	        			if(pwd.equals(reenterPwd) == false) {
	        				JOptionPane.showMessageDialog(null, "Password do not match! Please reenter your password!","Confirmation", JOptionPane.WARNING_MESSAGE);
		        			
		        		}
	        			else {
	        				if(UserAccount.getPassword().equals(oldpasst) == true && UserAccount.getUsername().equals(usr) == true) {
	        					String firstname = UserAccount.getName();
		        				int userid = UserAccount.getUserID();
		        				
		        				addChildUser.setName(firstname);
		        				addChildUser.setUserID(userid);
		        				addChildUser.setUsername(usr);
		        				addChildUser.setPassword(pwd);
		        				
		        				db.UpdateUser_Account(addChildUser);
		        				
		        				JFrame success = new JFrame();
		        				JOptionPane.showMessageDialog(success, "Successfully Updated Child Password! Logging in now...");
		        				
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
	        				if(ParentAccount.getPassword().equals(oldpasst) == true && ParentAccount.getUsername().equals(usr) == true) {
	        					
		        				int parentid = ParentAccount.getParentID();
		        						
		        				addParentUser.setParentID(parentid);
		        				addParentUser.setUsername(usr);
		        				addParentUser.setPassword(pwd);
		        				
		        				db.UpdateParent_Account(addParentUser);
		        				
		        				JFrame success = new JFrame();
		        				JOptionPane.showMessageDialog(success, "Successfully Updated Parent Password! Logging in now...");
		        				
		        				frame.dispose();
		        				success.dispose();
		        				parentPortal(parentid);
	        					
	        				}
		        			else  {
		        			
		        			
		        				JOptionPane.showMessageDialog(null, "Username in use!","Confirmation", JOptionPane.WARNING_MESSAGE);
		        			}
	        		}
	        				
	        		
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(null, "You must select a child or parent account to register!","Confirmation", JOptionPane.WARNING_MESSAGE);
	        		}
	        	
	        	
	        }});
	        panel.add(accButton);
	        
//	        loginButton.setBounds(500, 490, 78, 40);
//	        loginButton.setBorderPainted(false);
//	        loginButton.setBackground(aa_purple);
//	        loginButton.setForeground(Color.WHITE);
//	        loginButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
//	        loginButton.addActionListener(new ActionListener() {
//	        	public void actionPerformed(ActionEvent e) {
//	        	//redirect the user to the login page
//	        	card.previous(cardPane);
//	        }});
//	        panel.add(loginButton);
	       
	        
	        backButton.setBounds(397, 490, 78, 40);
	        backButton.setBorderPainted(false);
	        backButton.setBackground(Color.WHITE);
	        backButton.setForeground(aa_purple);
	        backButton.setFont(new Font("Dosis SemiBold", Font.BOLD, 14));
	        backButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
		        card.previous(cardPane);
	        	card.previous(cardPane);
	        }});
	        panel.add(backButton);
	        
	        
	    
	        //TODO add a login button by the back button
			return panel;
		
		}
		private static JPanel welcomepage(CardLayout card, JFrame frame) {
			JPanel panel = new JPanel();
			panel.setBackground(aa_grey);
			panel.setLayout(null);
		
			//TODO add logo
			JLabel Label = new JLabel("Welcome to");
			JLabel Label2 = new JLabel("The Attention Assistant!");
			JLabel option = new JLabel("Please login or register for an account");
			JButton login=new JButton("Login");
			JButton register= new JButton("Register"); 
			JButton parentportal=new JButton("Parent Portal");
			JButton ChildPortal= new JButton("Child Portal"); 
			
			BufferedImage cat1 = null;
			 BufferedImage duck = null;
			    try {
			    	cat1 = ImageIO.read(new File("avatarSelection/avatar_cat1.png"));
			    	duck = ImageIO.read(new File("avatarSelection/avatar_duck.png"));
				
				}catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			    
			    JLabel catpic = new JLabel(new ImageIcon(cat1));
			    catpic.setBounds(500, 50, 138, 150);
			    panel.add(catpic);
			    
			    JLabel duckpic = new JLabel(new ImageIcon(duck));
			    duckpic.setBounds(60, 50, 132, 150);
			    panel.add(duckpic);
			
			
			Label.setBounds(285, 75, 280, 100);
			Label.setForeground(new Color(255, 255, 255));
			Label.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
			panel.add(Label);
			
			Label2.setBounds(225, 110, 280, 100);
			Label2.setForeground(new Color(255, 255, 255));
			Label2.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
			panel.add(Label2);
			

			option.setBounds(200, 200, 280, 100);
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

		        		String pwd = new String("1");
		        		String usr = new String("Test");
		        		
		        		
		        		db.DatabaseSetUp();
		        		
		        		Parent_Account ParentAccount = db.SearchParent_Account(usr,pwd);
		        		Parent_Account addParentUser = new Parent_Account();
		        	
		        		//this is looking for an acc with this exact password and username, so i have to check the pass and user
		        		//keep user name
		        		//change password to security 1 &2 
		        		//add one more to display question
		        		//if questoins are right, do popup for change new passwrod
		        		//another popup saying 1-2 questions wrong
		        		
		        		//TODO dispose frame when sucessful login
		        		
		        		if(ParentAccount.getPassword().equals(pwd) == true && ParentAccount.getUsername().equals(usr) == true) {
		        			JFrame success = new JFrame();
	        				JOptionPane.showMessageDialog(success, "Test Parent Account Logging In!");
	        				
	        				int ParentID = ParentAccount.getParentID();
	        				frame.dispose();
	        				success.dispose();
	        				parentPortal(ParentID);
		        		}
		        		else {
		        			
		        			addParentUser.setUsername(usr);
		        			addParentUser.setPassword(pwd);
		        			db.AddParent_Account(addParentUser);
	        				
	        				int ParentID = ParentAccount.getParentID();
	        				
	        			
	        				JFrame success = new JFrame();
	        				JOptionPane.showMessageDialog(success, "Successfully Registered Test Parent Account! Logging in now...");
	        				
	        				frame.dispose();
	        				success.dispose();
	        				parentPortal(ParentID);
		        		}
		        		
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
		        		String pwd = new String("1");
		        		String usr = new String("Test");
		        		String first = new String("TestChild");
		        		
		        		
		        		db.DatabaseSetUp();
		        		User_Account UserAccount = db.SearchUser_Account(usr, pwd);
		        		//Parent_Account ParentAccount = db.SearchParent_Account(usr,pwd);
		        		User_Account addChildUser = new User_Account();
		        		//Parent_Account addParentUser = new Parent_Account();
		        	
		        		//this is looking for an acc with this exact password and username, so i have to check the pass and user
		        		//keep user name
		        		//change password to security 1 &2 
		        		//add one more to display question
		        		//if questoins are right, do popup for change new passwrod
		        		//another popup saying 1-2 questions wrong
		        		
		        		//TODO dispose frame when sucessful login
		        		
		        		if(UserAccount.getPassword().equals(pwd) == true && UserAccount.getUsername().equals(usr) == true) {
		        			JFrame success = new JFrame();
	        				JOptionPane.showMessageDialog(success, "Test Child Account Logging In!");
	        				
	        				int userid = UserAccount.getUserID();
	        				frame.dispose();
	        				success.dispose();
	        				childPortal(userid);
		        		}
		        		else {
		        			addChildUser.setUsername(usr);
	        				addChildUser.setPassword(pwd);
	        				addChildUser.setName(first);
	        				db.AddUser_Account(addChildUser);
	        				
	        				int userid = UserAccount.getUserID();
	        				
	        			
	        				JFrame success = new JFrame();
	        				JOptionPane.showMessageDialog(success, "Successfully Registered Test Child Account! Logging in now...");
	        				
	        				frame.dispose();
	        				success.dispose();
	        				childPortal(userid);
		        		}
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
		        	card.next(cardPane);
		        	card.next(cardPane);
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
		    
		    BufferedImage dino = null;
		    try {
				
				dino = ImageIO.read(new File("avatarSelection/avatar_dino.png"));
			
			}catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		    
		    JLabel dinopic = new JLabel(new ImageIcon(dino));
		    dinopic.setBounds(100, 182, 127, 150);
		    panel.add(dinopic);
		
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
	        		//declares the string depending on who is registering
	        		userinput = "Child";
	        		//changes the color of button upon click
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
	        		//declares the string depending on who is registering
	        		userinput = "Parent";
	        		//changes the color of button upon click
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
	        		User_Account UserAccount = db.SearchUser_Account(usr,pwd);
	        		Parent_Account ParentAccount = db.SearchParent_Account(usr,pwd);
	        		User_Account addChildUser = new User_Account();
	        		Parent_Account addParentUser = new Parent_Account();
	        	
	        		System.out.print("Here is the user acc " + UserAccount);
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
				pm = new Priority_Manager(userID,db,observer, pomo);
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
	    
	        
	        JMenuBar titlePanel = titlePanel(frame);
	        
	        card = new CardLayout();
	        WelcomePanel = new JPanel();
	        LoginPanel = new JPanel();
	        RegisterPanel = new JPanel();
	        ForgotPanel = new JPanel();
	        cardPane = new JPanel();
	        
	        LoginPanel = loginpage(card,frame);
	        RegisterPanel = Registerpage(card,frame);
	        WelcomePanel = welcomepage(card,frame);
	        ForgotPanel = forgetPass(card,frame);
	     
	    	
	    	
	    	cardPane.setLayout(card);
	        cardPane.setBounds(300, 50, 700, 600);
	    	cardPane.add(WelcomePanel, "Welcome Panel");
	        cardPane.add(LoginPanel, "Login Panel");
	        cardPane.add(RegisterPanel, "Register Panel");
	        cardPane.add(ForgotPanel, "Forgot Password Panel");
	            
	        
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
