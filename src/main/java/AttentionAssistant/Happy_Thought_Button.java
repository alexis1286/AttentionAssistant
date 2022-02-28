package AttentionAssistant;

import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Class that contains information whenever Happy_Thought_Button
 * is called.
 * @author jmitchel2
 */
public class Happy_Thought_Button {

	/*
	 * global variables for HTB GUI
	 */
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = 400; 
	private int width = 625; 
	private int mouseX;
	private int mouseY;
	int current = 0; 
	JLabel displayHT;
	final static boolean shouldFill = true; 
	final static boolean shouldWeightX = true; 
	final static boolean RIGHT_TO_LEFT = false; 
	
	/**
	 * Primary Key for Happy Thought Button
	 */
	private int hTB_ID;

	/**
	 * Media ID for the media displayed from the Happy_Thought_Button
	 */
	private String media_ID_Tag;
	
	/**
	 * If the media is flagged by the user or not
	 */
	private boolean flagged;
	
	/**
	 * The Date and Time the Happy_Thought_Button was executed 
	 */
	
	private Date dT_Executed;
	
	/**
	 * Instantiating empty Happy_Thought_Button object
	 * @author jmitchel2
	 */
	public Happy_Thought_Button() {
		this.hTB_ID= 0;
		this.media_ID_Tag = "";
		this.flagged = false;
		this.dT_Executed= null;
	}

	/**
	 * Create a class Happy_Thought_Button with a specified
	 * hTB_ID, media_ID_Tage, flagged
	 * @author jmitchel2
	 * @param int, String, boolean
	 */
	public Happy_Thought_Button(int hTB_ID, String media_ID_Tag, boolean flagged, Date dT_Executed) {
		this.hTB_ID= hTB_ID;
		this.media_ID_Tag= media_ID_Tag;
		this.flagged = flagged;
		this.dT_Executed= dT_Executed;
	}
	
	/**
	 * Instantiating copy constructor for Happy_Thought_Button object
	 */
	public Happy_Thought_Button(Happy_Thought_Button hTB) {
		this.hTB_ID= hTB.hTB_ID;
		this.media_ID_Tag = hTB.media_ID_Tag;
		this.flagged = hTB.flagged;
		this.dT_Executed= hTB.dT_Executed;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get hTB_ID
	 * @author jmitchel2
	 * @return int
	 */
	public int getHTBID() {
		return this.hTB_ID;
	}
	
	/**
	 * User should not be able to set the hTB_ID this should be done automatically through the database
	 * comment out once database is working.
	 * 
	 * Set hTB_ID
	 * @param int
	 */
	public void setHTBID(int hTB_ID) {
		this.hTB_ID= hTB_ID;
	}

	/**
	 * Get media_ID_Tag
	 * 
	 * @return String
	 */
	public String getMedia_ID_Tag() {
		return this.media_ID_Tag;
	}
	
	/**
	 * Set media_ID_Tag
	 * 
	 * @param String
	 */
	public void setMedia_ID_Tag(String media_ID_Tag) {
		this.media_ID_Tag= media_ID_Tag;
	}

	/**
	 * Get flagged
	 * @return boolean
	 */
	public boolean getFlagged() {
		return this.flagged;
	}
	
	/**
	 * Set media_ID_Tag
	 * @param boolean
	 */
	public void setFlagged(boolean flagged) {
		this.flagged= flagged;
	}

	/**
	 * Get dT_Executed
	 * 
	 * @return Date
	 */
	public Date getDT_Executed() {
		return this.dT_Executed;
	}
	
	/**
	 * Set dT_Executed
	 * 
	 * @param Date
	 */
	public void setDT_Executed(Date dT_Executed) {
		this.dT_Executed= dT_Executed;
	}

	  /** 
	   * Display HTB
	   * @return String
	   */
	@Override
	public String toString() {
	 	String hTBString= new String();
	 	hTBString = "Happy_Thought_Button ID= " + this.hTB_ID +
	 			" Media_ID_Tag= " + this.media_ID_Tag +
	 			" Flagged= " + Boolean.toString(this.flagged) +
	 			" Date Time Executed= " + this.dT_Executed;
	 			
	 	return hTBString;
	 	
	 }
	
	/**
	 * creates an array of images to display
	 * will need to be modified for more functionality
	 * right now just loading the test images to an array
	 */
	public void getHappyMedia(ArrayList<String> happyMedia) {
		
		happyMedia.add("happyThoughtMedia/231582875_f219808478_o.jpg");
		happyMedia.add("happyThoughtMedia/alex-vinogradov--wHZZ-cg7rk-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/angel-balashev-qZE61XvCMPQ-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/bailey-burton-o5UlVmTwVz8-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/bob-brewer-urEw5S62mlg-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/bobby-mc-leod-jPJ8BPrGMuU-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/daniel-sessler-9Nn21mIKP1w-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/dipanjan-pal-CbXmfl2t_SQ-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/gratisography-447H-free-stock-photo.jpg");
		happyMedia.add("happyThoughtMedia/josh-gordon-fzHmP6z8OQ4-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/max-lissenden-snYLMKphCf4-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/rod-long-ogWhdXOl5qY-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/sanjoy-saha-jdTmQz0lvpo-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/sascha-bosshard-umRejISHZbo-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/shche_-team-yt9k70OO6XM-unsplash.jpg");
		//happyMedia.add("happyThoughtMedia/tom-briskey-AddAnDkkovM-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/uliana-kopanytsia-7SfBWchOVpw-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/yusuf-onuk-uzZgdFKisng-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/yusuf-onuk-zI3Fr9YlQAI-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/zetong-li-NAP14GEjvh8-unsplash.jpg");
	}
	
	public Icon createNewIcon(String filepath) {
		
		BufferedImage newHappyMedia = null;
		try {
			//will pass string for file path 
			newHappyMedia = ImageIO.read(new File(filepath));
		}catch(Exception f) {
			f.printStackTrace();
			System.exit(1);
		}
		
		Image htb_img = newHappyMedia.getScaledInstance(480, 360, java.awt.Image.SCALE_SMOOTH);
		Icon newHtbDisplay = new ImageIcon(htb_img);
		
		return newHtbDisplay;
	}
	
	/**
	 * creates/displays HTB GUI
	 * @author krchr
	 */
	public void open_htb() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				JFrame htb_frame = new JFrame("Happy Thought Button");
				htb_frame.setUndecorated(true);
				htb_frame.setPreferredSize(new Dimension(width, height));
				
				JPanel media_viewer = new JPanel(new BorderLayout());
				media_viewer.setBackground(Color.black);
				media_viewer.setMaximumSize(new Dimension(width, height));
				
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
						htb_frame.setLocation(htb_frame.getX() + e.getX() - mouseX, htb_frame.getY() + e.getY() - mouseY);
					}
				});
				
				title_panel.addMouseListener(new MouseAdapter(){
					@Override 
					public void mousePressed(MouseEvent e) {
						mouseX = e.getX();
						mouseY = e.getY();
					}
				});

				JLabel title = new JLabel("Happy Thought Button");
				title.setForeground(Color.white);
				title.setFont(new Font("Serif", Font.BOLD, 20));
				
				ArrayList<String> happyMedia = new ArrayList<String>();
				getHappyMedia(happyMedia);
				
				/*
				 * create icons to use as buttons for title bar
				 */
				BufferedImage ci = null;
				BufferedImage gi = null;
				BufferedImage leftScroll = null;
				BufferedImage rightScroll = null;
				BufferedImage flag = null;
				BufferedImage thUp = null;
				BufferedImage thDwn = null;
				//BufferedImage testHappyImage = null;
				
				try {
					ci = ImageIO.read(new File("images/exit_circle.png"));
					gi = ImageIO.read(new File("images/guide.png"));
					leftScroll = ImageIO.read(new File("images/left_arrow.png")); 
					rightScroll = ImageIO.read(new File("images/right_arrow.png")); 
					flag = ImageIO.read(new File("images/flag.png"));
					thUp = ImageIO.read(new File("images/thumb_up.png"));
					thDwn = ImageIO.read(new File("images/thumb_down.png")); 
					//testHappyImage = ImageIO.read(new File(happyMedia.get(0))); 
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
		        		htb_frame.dispose();
		        	}
		        });
				
				Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				Icon guideIcon = new ImageIcon(g_img);
				
				JButton guide = new JButton(guideIcon);
				guide.setBorderPainted(false);
				guide.setContentAreaFilled(false);
				guide.setFocusPainted(false);
				
				title_panel.add(title);
				title_panel.add(Box.createRigidArea(new Dimension(290, 0)));
				title_panel.add(guide);
				title_panel.add(close_window);
				
				JPanel center_panel = new JPanel(new BorderLayout());
				center_panel.setBackground(Color.black);
				center_panel.setMaximumSize(new Dimension(500, 460));
				
				JPanel middle_panel = new JPanel();
				middle_panel.setLayout(new BoxLayout(middle_panel, BoxLayout.Y_AXIS));
				middle_panel.setBackground(Color.black);
				middle_panel.setMaximumSize(new Dimension(480, 360));
				
				Icon initialHappyThoughtImage = createNewIcon(happyMedia.get(current));
				displayHT = new JLabel(initialHappyThoughtImage);
				
				middle_panel.add(displayHT);
				
				JPanel left_panel = new JPanel();
				left_panel.setLayout(new BoxLayout(left_panel, BoxLayout.Y_AXIS));
				left_panel.setMaximumSize(new Dimension(40, 360));
				
				Image la_img = leftScroll.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
				Icon leftArrowIcon = new ImageIcon(la_img);
				JButton left = new JButton(leftArrowIcon); 
				left.setBorderPainted(false); 
				left.setContentAreaFilled(false);
				left.setFocusPainted(false); 
				left.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//scroll image to left	
						current--; 
						if(current >= 0) {
							Icon newDisplay = createNewIcon(happyMedia.get(current));
							displayHT.setIcon(newDisplay); 
						}else if(current < 0){
							System.out.println("Can't scroll left anymore, sorry!");
							current++;
						}
					}
				});
				
				left_panel.add(Box.createRigidArea(new Dimension(0, 100))); 
				left_panel.add(left); 
				left_panel.setBackground(Color.black); 
				
				JPanel right_panel = new JPanel();
				right_panel.setLayout(new BoxLayout(right_panel, BoxLayout.Y_AXIS));
				right_panel.setMaximumSize(new Dimension(40, 360));
				
				Image ra_img = rightScroll.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
				Icon rightArrowIcon = new ImageIcon(ra_img);
				JButton right = new JButton(rightArrowIcon); 
				right.setBorderPainted(false); 
				right.setContentAreaFilled(false);
				right.setFocusPainted(false); 
				right.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						current++;
						//scroll image to the right
						if(current < happyMedia.size() - 1) {
							Icon newDisplay = createNewIcon(happyMedia.get(current));
							displayHT.setIcon(newDisplay);
						}else if(current > happyMedia.size()-1) {
							System.out.println("Can't scroll right anymore, sorry!");
							current--;
						}
					}
				});
				
				right_panel.add(Box.createRigidArea(new Dimension(0, 100))); 
				right_panel.add(right); 
				right_panel.setBackground(Color.black);  
				
				center_panel.add(middle_panel, BorderLayout.CENTER); 
				center_panel.add(left_panel, BorderLayout.WEST); 
				center_panel.add(right_panel, BorderLayout.EAST);
				
				JPanel bottom_panel = new JPanel(); 
				bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.X_AXIS));
				bottom_panel.setBackground(Color.black);
				bottom_panel.setMaximumSize(new Dimension(480, 50));				
				
				Image thumbUp_img = thUp.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
				Icon thumbsUp = new ImageIcon(thumbUp_img);
				
				JButton likeMedia = new JButton(thumbsUp);
				likeMedia.setBorderPainted(false);
				likeMedia.setContentAreaFilled(false);
				likeMedia.setFocusPainted(false);
				likeMedia.setMaximumSize(new Dimension(35, 35)); 
				likeMedia.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//like media actions 
		        	}
		        });
				
				Image thumbDwn_img = thDwn.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
				Icon thumbsDown = new ImageIcon(thumbDwn_img);
				
				JButton dislikeMedia = new JButton(thumbsDown);
				dislikeMedia.setBorderPainted(false);
				dislikeMedia.setContentAreaFilled(false);
				dislikeMedia.setFocusPainted(false);
				dislikeMedia.setMaximumSize(new Dimension(35, 35)); 
				dislikeMedia.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//dislike media actions 
		        	}
		        });
				
				Image flag_img = flag.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				Icon flagIcon = new ImageIcon(flag_img);
				
				JButton flagMedia = new JButton(flagIcon);
				flagMedia.setBorderPainted(false);
				flagMedia.setContentAreaFilled(false);
				flagMedia.setFocusPainted(false);
				flagMedia.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//flag media actions 
		        	}
		        });
				
				bottom_panel.add(Box.createRigidArea(new Dimension(235, 0)));
				bottom_panel.add(likeMedia); 
				bottom_panel.add(Box.createRigidArea(new Dimension(50, 0)));
				bottom_panel.add(dislikeMedia); 
				bottom_panel.add(Box.createRigidArea(new Dimension(155, 0))); 	
				bottom_panel.add(flagMedia);
				/*
				 * populates main panel 
				 */
				media_viewer.add(title_panel, BorderLayout.PAGE_START); 
				media_viewer.add(center_panel, BorderLayout.CENTER);
				media_viewer.add(bottom_panel, BorderLayout.PAGE_END);
			
				/*
				 * adds main panel to frame
				 */
				htb_frame.getContentPane().add(media_viewer); 
				htb_frame.getContentPane().setBackground(Color.black);
				htb_frame.pack();
				htb_frame.setAlwaysOnTop(false);
				htb_frame.setVisible(true);
				htb_frame.setResizable(true);
				htb_frame.setLocationRelativeTo(null);
			}
		});
	}



}