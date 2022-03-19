package AttentionAssistant;

import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Class that contains information whenever Happy_Thought_Button
 * is called.
 * @author krchr
 */
public class Happy_Thought_Button {

	/*
	 * global variables for HTB GUI
	 */
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	ArrayList<String> happyMedia = new ArrayList<String>();
	private int height = 400; 
	private int width = 625; 
	private int mouseX;
	private int mouseY;
	JButton dislikeMedia;
	JButton likeMedia;
	JButton flagMedia;
	int current = 0; 
	final static boolean shouldFill = true; 
	final static boolean shouldWeightX = true; 
	final static boolean RIGHT_TO_LEFT = false; 
	
	
	/**
	 * If the media is flagged by the user or not
	 */
	private boolean flagged;
	
	/**
	 * Records rating of media by the user
	 * 0 = thumbs down
	 * 1 = unrated
	 * 2 = thumbs up
	 */
	
	private int rating;
	
	
	/**
	 * Instantiating empty Happy_Thought_Button object
	 * @author jmitchel2
	 */
	public Happy_Thought_Button() {
		this.flagged = false;
		this.rating = 1;
	}

	/**
	 * Create a class Happy_Thought_Button with a specified
	 * hTB_ID, media_ID_Tage, flagged
	 * @author jmitchel2
	 * @author krchr
	 * @param int, String, boolean
	 */
	public Happy_Thought_Button(boolean flagged, int rating) {
		this.flagged = flagged;
		this.rating = rating;
	}
	
	/**
	 * Instantiating copy constructor for Happy_Thought_Button object
	 */
	public Happy_Thought_Button(Happy_Thought_Button hTB) {
		this.flagged = hTB.flagged;
		this.rating = hTB.rating;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get hTB_ID
	 * @author jmitchel2
	 * @author krchr
	 * @return int
	 */
	

	/**
	 * Get flagged
	 * @return boolean
	 */
	public boolean getFlagged() {
		return this.flagged;
	}
	
	/**
	 * Set flagged
	 * @param boolean
	 */
	public void setFlagged(boolean flagged) {
		this.flagged= flagged;
	}
	
	/**
	 * Get rating
	 * @return int
	 */
	public int getRating() {
		return this.rating;
	}
	
	/**
	 * Set rating
	 * @param int
	 */
	public void setRating(int rating) {
		this.rating= rating;
	}
	
	/**
	 * creates an array of images to display
	 * will need to be modified for more functionality
	 * right now just loading the test images to an array
	 * 
	 * @author krchr
	 */
	public void getHappyMedia() {
		
		happyMedia.add("happyThoughtMedia/gratisography-447H-free-stock-photo.jpg");
		happyMedia.add("happyThoughtMedia/78nF.gif");
		happyMedia.add("happyThoughtMedia/231582875_f219808478_o.jpg");
		happyMedia.add("happyThoughtMedia/jVo.gif");
		happyMedia.add("happyThoughtMedia/alex-vinogradov--wHZZ-cg7rk-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/PB35.gif");
		happyMedia.add("happyThoughtMedia/daniel-sessler-9Nn21mIKP1w-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/QHa.gif");
		happyMedia.add("happyThoughtMedia/max-lissenden-snYLMKphCf4-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/Z2QS.gif");
		happyMedia.add("happyThoughtMedia/rod-long-ogWhdXOl5qY-unsplash.jpg");
		happyMedia.add("happyThoughtMedia/4OKm.gif");
		happyMedia.add("happyThoughtMedia/yusuf-onuk-uzZgdFKisng-unsplash.jpg");
	}
	
	/*
	 * create new Graphics for each image
	 * @author krchr
	 */
	public JPanel generateNewJPG(String filepath) {
		JPanel mediaPanel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				try {
					g.drawImage(ImageIO.read(new File(filepath)), 0, 0, 480, 360, this);
				}catch (IOException f) {
					f.printStackTrace();
					System.exit(1);
				}
			}
		};
		return mediaPanel;
	}
	
	public JPanel generateNewGIF(String filepath) {
		JPanel mediaPanel = new JPanel();
		mediaPanel.setLayout(new BoxLayout(mediaPanel, BoxLayout.X_AXIS)); 
		mediaPanel.setBackground(Color.black);
		mediaPanel.setMaximumSize(new Dimension(480, 360));
		
		JLabel label = new JLabel();
		label.setMaximumSize(new Dimension(480, 360));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(filepath).getImage().getScaledInstance(420, 300, Image.SCALE_FAST));
		label.setIcon(imageIcon);
		mediaPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		mediaPanel.add(label);
		
		return mediaPanel;
	}
	
	public JMenuBar generateTitlePanel(JFrame htb_frame) {
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
		
		BufferedImage ci = null;
		BufferedImage gi = null;
		
		try {
			ci = ImageIO.read(new File("images/exit_circle.png"));
			gi = ImageIO.read(new File("images/guide.png")); 
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
		
		return title_panel;
	}
	
	public JPanel generateCenterPanel(CardLayout cardLayout, JPanel middle_panel) {
		
		getHappyMedia();
		
		BufferedImage leftScroll = null;
		BufferedImage rightScroll = null;
		
		try {
			leftScroll = ImageIO.read(new File("images/left_arrow.png")); 
			rightScroll = ImageIO.read(new File("images/right_arrow.png")); 
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		JPanel center_panel = new JPanel(new BorderLayout());
		center_panel.setBackground(Color.black);
		center_panel.setMaximumSize(new Dimension(500, 460));
		
		middle_panel.setBackground(Color.black);
		middle_panel.setMaximumSize(new Dimension(480, 360));
		middle_panel.setLayout(cardLayout);
		populateMiddlePanel(middle_panel, cardLayout); 
			
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
				
				//temporary for borders until connected to database, adjust to reflect stored value in database later
				dislikeMedia.setBorderPainted(false);
				likeMedia.setBorderPainted(false);
				
				current--; 
				if(current >= 0) {
					populateMiddlePanel(middle_panel, cardLayout); 
				}else if(current < 0){
					
					UIManager.put("Button.foreground", aa_purple);
					UIManager.put("Button.background", aa_grey);
					UIManager.put("OptionPane.background", Color.black);
					UIManager.put("Panel.setOpaque", true);
					UIManager.put("Panel.background", aa_grey);
					UIManager.put("TextField.selectionBackground", Color.WHITE);
					UIManager.put("OptionPane.messageForeground", Color.WHITE);
					
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Scroll right to see more Happy Thoughts!");
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
				//scroll image to the right
				
				//temporary for borders until connected to database, adjust to reflect stored value in database later
				dislikeMedia.setBorderPainted(false);
				likeMedia.setBorderPainted(false);
				
				current++;
				if(current < happyMedia.size()) {
					populateMiddlePanel(middle_panel, cardLayout); 
				}else if(current > happyMedia.size()) {
					
					UIManager.put("Button.foreground", aa_purple);
					UIManager.put("Button.background", aa_grey);
					UIManager.put("OptionPane.background", Color.black);
					UIManager.put("Panel.setOpaque", true);
					UIManager.put("Panel.background", aa_grey);
					UIManager.put("TextField.selectionBackground", Color.WHITE);
					UIManager.put("OptionPane.messageForeground", Color.WHITE);
					
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Scroll left to see more Happy Thoughts!");
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
		
		return center_panel;
	}
	
	public void populateMiddlePanel(JPanel middle_panel, CardLayout cardLayout) {
		
		JPanel media_panel = new JPanel();
		media_panel.setLayout(new BoxLayout(media_panel, BoxLayout.Y_AXIS));
		media_panel.setBackground(Color.black);
		media_panel.setMaximumSize(new Dimension(480, 360));
		
		String fileName = happyMedia.get(current);
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		if(fileExtension.equals("jpg")) {
			media_panel = generateNewJPG(fileName);
		}else if(fileExtension.equals("gif")) {
			media_panel = generateNewGIF(fileName);
		}
		
		middle_panel.add("Media", media_panel); 
		cardLayout.show(middle_panel, "Media"); 
	}
	
	public JPanel generateBottomPanel(JPanel middle_panel, CardLayout cardLayout) {
		
		BufferedImage flag = null;
		BufferedImage thUp = null;
		BufferedImage thDwn = null;
		
		try {
			flag = ImageIO.read(new File("images/flag.png"));
			thUp = ImageIO.read(new File("images/thumb_up.png"));
			thDwn = ImageIO.read(new File("images/thumb_down.png")); 
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		JPanel bottom_panel = new JPanel(); 
		bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.X_AXIS));
		bottom_panel.setBackground(Color.black);
		bottom_panel.setMaximumSize(new Dimension(480, 50));				
		
		Image thumbUp_img = thUp.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		Icon thumbsUp = new ImageIcon(thumbUp_img);
		
		likeMedia = new JButton(thumbsUp);
		likeMedia.setBorderPainted(false);
		likeMedia.setContentAreaFilled(false);
		likeMedia.setFocusPainted(false);
		likeMedia.setMaximumSize(new Dimension(35, 35)); 
		likeMedia.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//like media actions 
        		if(likeMedia.isBorderPainted() == false) {
        			dislikeMedia.setBorderPainted(false); 
        			likeMedia.setBorderPainted(true);
        			likeMedia.setBorder(new LineBorder(Color.GREEN));
        		}else if(likeMedia.isBorderPainted() == true) {
        			likeMedia.setBorderPainted(false);
        		}
        	}
        });
		
		Image thumbDwn_img = thDwn.getScaledInstance(35, 35, java.awt.Image.SCALE_SMOOTH);
		Icon thumbsDown = new ImageIcon(thumbDwn_img);
		
		dislikeMedia = new JButton(thumbsDown);
		dislikeMedia.setBorderPainted(false);
		dislikeMedia.setContentAreaFilled(false);
		dislikeMedia.setFocusPainted(false);
		dislikeMedia.setMaximumSize(new Dimension(35, 35)); 
		dislikeMedia.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//dislike media actions
        		if(dislikeMedia.isBorderPainted() == false) {
        			likeMedia.setBorderPainted(false); 
        			dislikeMedia.setBorderPainted(true);
        			dislikeMedia.setBorder(new LineBorder(Color.RED));
        		}else if(dislikeMedia.isBorderPainted() == true) {
        			dislikeMedia.setBorderPainted(false);
        		}
        	}
        });
		
		Image flag_img = flag.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon flagIcon = new ImageIcon(flag_img);
		
		flagMedia = new JButton(flagIcon);
		flagMedia.setBorderPainted(false);
		flagMedia.setContentAreaFilled(false);
		flagMedia.setFocusPainted(false);
		flagMedia.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//flag media actions 
        		UIManager.put("Button.foreground", aa_purple);
				UIManager.put("Button.background", aa_grey);
				UIManager.put("OptionPane.background", Color.black);
				UIManager.put("Panel.setOpaque", true);
				UIManager.put("Panel.background", aa_grey);
				UIManager.put("TextField.selectionBackground", Color.WHITE);
				UIManager.put("OptionPane.messageForeground", Color.WHITE);
        		
        		JFrame flagFrame = new JFrame();
        		JOptionPane.showMessageDialog(flagFrame,"Media has been flagged! It will no longer appear in the Happy Thought Button.","Alert",JOptionPane.WARNING_MESSAGE);
        		
        		//current++;
				//scroll image to the right
				if(current < happyMedia.size() - 1) {
					current++;
					populateMiddlePanel(middle_panel, cardLayout); 
					happyMedia.remove(current - 1);
				}else if(current == happyMedia.size() - 1) {
					current--;
					populateMiddlePanel(middle_panel, cardLayout); 
					happyMedia.remove(current + 1);
				}
        	}
        });
		
		bottom_panel.add(Box.createRigidArea(new Dimension(235, 0)));
		bottom_panel.add(likeMedia); 
		bottom_panel.add(Box.createRigidArea(new Dimension(50, 0)));
		bottom_panel.add(dislikeMedia); 
		bottom_panel.add(Box.createRigidArea(new Dimension(145, 0))); 	
		bottom_panel.add(flagMedia);
		
		return bottom_panel; 
	}
	
	public JPanel generateMediaViewer(JMenuBar title_panel, JPanel center_panel, JPanel bottom_panel) {
		
		JPanel media_viewer = new JPanel(new BorderLayout());
		media_viewer.setBackground(Color.black);
		media_viewer.setMaximumSize(new Dimension(width, height));
		
		media_viewer.add(title_panel, BorderLayout.PAGE_START); 
		media_viewer.add(center_panel, BorderLayout.CENTER);
		media_viewer.add(bottom_panel, BorderLayout.PAGE_END);
		
		return media_viewer; 
	}
	
	/**
	 * creates/displays HTB GUI
	 * @author krchr
	 */
	public void open_htb() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				CardLayout cardLayout = new CardLayout();
				JPanel middle_panel = new JPanel(); 
				
				JFrame htb_frame = new JFrame("Happy Thought Button");
				htb_frame.setUndecorated(true);
				htb_frame.setPreferredSize(new Dimension(width, height));
							
				JMenuBar title_panel = generateTitlePanel(htb_frame);				
				JPanel center_panel = generateCenterPanel(cardLayout, middle_panel);
				JPanel bottom_panel = generateBottomPanel(middle_panel, cardLayout);
				JPanel media_viewer = generateMediaViewer(title_panel, center_panel, bottom_panel);
			
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