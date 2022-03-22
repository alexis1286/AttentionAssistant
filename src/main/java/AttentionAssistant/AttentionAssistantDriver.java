package AttentionAssistant;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AttentionAssistantDriver {
	static int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	static int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	static Color aa_grey = new Color(51,51,51);
	static Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	static DataBase db = new DataBase();	
	static Observer observer = new Observer();
	static Priority_Manager priority_manager;
	static Pomodoro_Timer pomodoro_timer = new Pomodoro_Timer();
	static Negative_Thought_Burner negative_thought_burner = new Negative_Thought_Burner();
	static Happy_Thought_Button happy_thought_button = new Happy_Thought_Button(db);
	static Free_Thought_Space free_thought_space = new Free_Thought_Space();
	private static String userType;
	
	public static void main(String[] args) throws Exception {
		int userID = 1; // changes from 0 to 1 for database
		//create task window
		JFrame frame = new JFrame("User type");
		//pin to top of screen
		frame.setAlwaysOnTop(true);
		//set window background to black
		frame.setBackground(Color.black);
		//remove default title bar
		frame.setUndecorated(true);
		//makes custom title panel
		JPanel title_panel = titlePanel(frame);
		
		//creates panel for parent/child selection
		JPanel selectionPane = new JPanel();
		selectionPane.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, aa_purple));
		JPanel buttons = new JPanel();
		buttons.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, aa_purple));
				
		//sets up grid to line up labels with text areas
		GridLayout grid = new GridLayout(0,2);
		selectionPane.setLayout(grid);
		//create check box for if task is to be observed
		JCheckBox childBox = new JCheckBox("Child");
		childBox.setSelected(false);
		childBox.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		childBox.setForeground(aa_purple);
		childBox.setContentAreaFilled(false);
		childBox.setFocusPainted(false);
		
		//create check box for if task is a priority task
		JCheckBox parentBox = new JCheckBox("parent");
		parentBox.setSelected(false);
		parentBox.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		parentBox.setForeground(aa_purple);
		parentBox.setContentAreaFilled(false);
		parentBox.setFocusPainted(false);
		
		childBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				parentBox.setSelected(false);
				userType = "child";
			}
		});
		parentBox.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				childBox.setSelected(false);
				userType = "parent";
			}
		});
		
		JButton okay = new JButton(" okay ");
		okay.setBackground(aa_grey);
		okay.setForeground(Color.white);
		okay.setFocusPainted(false);
		okay.setBorder(new LineBorder(Color.black, 3, true));
		okay.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		okay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(parentBox.isSelected()==true||childBox.isSelected()==true) {
        			if(childBox.isSelected()==true) {
        				childPortal(userID, db,observer,priority_manager,pomodoro_timer,negative_thought_burner,happy_thought_button,free_thought_space);
        			}else if(parentBox.isSelected()==true) {
        				parentPortal(userID);
        			}
	        		frame.dispose();
        		}else {
        			JFrame f = new JFrame();
        			JOptionPane.showMessageDialog(f,"Please select account type");
        		}
        	}});
		
		JButton exit = new JButton(" Exit ");
		exit.setBackground(aa_grey);
		exit.setForeground(Color.white);
		exit.setFocusPainted(false);
		exit.setBorder(new LineBorder(Color.black, 3, true));
		exit.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// exit entire application 
				UIManager.put("Button.foreground", aa_purple);
				UIManager.put("Button.background", aa_grey);
				UIManager.put("OptionPane.background", Color.black);
				UIManager.put("Panel.setOpaque", true);
				UIManager.put("Panel.background", aa_grey);
				UIManager.put("TextField.selectionBackground", Color.WHITE);
				UIManager.put("TextField.selectionForeground", Color.WHITE);
				
				JLabel warning = new JLabel("<html><center>Are you sure you want to EXIT" + "<br/>The Attention Assistant?</center></html>");
				warning.setFont(new Font("Serif", Font.BOLD, 16));
				warning.setForeground(Color.white);
				
				int response = JOptionPane.showConfirmDialog(null, warning, "The Attention Assistant", JOptionPane.OK_CANCEL_OPTION);
				switch (response) {
				case JOptionPane.OK_OPTION:
					System.exit(0); 
				case JOptionPane.CANCEL_OPTION:
					break; 
				}
        	}});
		
		selectionPane.add(parentBox);
		selectionPane.add(childBox);
		selectionPane.add(okay);
		selectionPane.add(exit);
		 
		frame.setLocation(width/2-frame.getWidth(),height/2-frame.getHeight());
		frame.add(title_panel);
		frame.add(selectionPane);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
	}
	
	private static void parentPortal(int userID) {
		Parent_Account parent = new Parent_Account(userID,"username1","pw1");
		Parent_Bar parentBar = new Parent_Bar(parent,db);
		parentBar.run_parent_bar();
	}
	
	private static void childPortal(int userID,DataBase db,Observer observer,Priority_Manager pm,Pomodoro_Timer pomo,Negative_Thought_Burner ntb,Happy_Thought_Button htb,Free_Thought_Space fts) {
		Settings settings = new Settings(userID);
		Nav_Bar navbar = new Nav_Bar(settings);
		db.DatabaseSetUp();
				
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
			priority_manager = new Priority_Manager(db,observer, pomodoro_timer);
			navbar.run_nav_bar(userID,db,navbar,settings,observer,priority_manager,pomodoro_timer,negative_thought_burner,happy_thought_button,free_thought_space);
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
		JLabel title = new JLabel("Priority Manager");
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
}
