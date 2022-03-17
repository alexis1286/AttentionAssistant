package AttentionAssistant;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * Class that contains GUI and information whenever Settings 
 * is called.
 * @author krchr
 */

public class Settings {
	
	/*
	 * global variables for the Settings GUI
	 */
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = 700; 
	private int width = 550; 
	private int mouseX;
	private int mouseY;
	JLabel displayAvatar;
	final static boolean shouldFill = true; 
	final static boolean shouldWeightX = true; 
	final static boolean RIGHT_TO_LEFT = false; 
	JCheckBox ftsVisibleBox = new JCheckBox("<html><center>Free Thought" + "<br/>Space</center></html>");
	JCheckBox ntbVisibleBox = new JCheckBox("<html><center>Negative Thought" + "<br/>Burner</center></html>");
	JCheckBox htbVisibleBox = new JCheckBox("<html><center>Happy Thought" + "<br/>Button</center></html>");
	JCheckBox timerVisibleBox = new JCheckBox("Pomodoro Timer");
	JButton openPom = new JButton("Open Pomodoro Timer");
	JButton openNTB = new JButton("Open Negative Thought Burner");
	JButton openHTB = new JButton("   Open Happy Thought Button   ");
	JButton openFTS = new JButton("     Open Free Thought Space     ");
		
	//variables for the Settings object
	
	/*
	 * primary key for database
	 */
	private int settingsID;
	
	/*
	 * foreign key for database
	 */
	private int userID;
	
	/*
	 * color of the circles below the icons of the navigation bar
	 */
	private Color iconCircles;
	
	/*
	 * color of the icons of the navigation bar
	 */
	private Color icons;
	
	/*
	 * opacity of the circles in the navigation bar
	 */
	private int opacityCircles; 
	
	/*
	 * opacity of the icons in the navigation bar
	 */
	private int opacityIcons; 
	
	/*
	 * determines if the nav bar is collapsed or expanded 
	 */
	private boolean isCollapsed; 
	
	/*
	 * x coordinate for location of navigation bar
	 */
	private int xCoord;
	
	/*
	 * y coordinate for location of navigation bar 
	 */
	private int yCoord; 
	
	/*
	 * sets nav bar to vertical instead of horizontal 
	 */
	private boolean isVertical; 
	
	/*
	 * size option for icon sizes in navigation bar 
	 */
	private int iconSize; 
	
	/*
	 * sets features visible in the navigation bar 
	 */
	private boolean timerIsVisible; 
	private boolean pmIsVisible; 
	private boolean ftsIsVisible; 
	private boolean htbIsVisible; 
	private boolean ntbIsVisible; 
	private boolean progReportIsVisible; 
	
	/*
	 * determines if avatar is used for notifications
	 */
	private boolean avatarIsActive; 
	
	/*
	 * determines if text is used for notifications
	 */
	private boolean textIsActive; 
	
	/*
	 * determines if audio is used for notifications
	 */
	private boolean audioIsActive;
	
	/*
	 * file path for selected avatar 
	 */
	private String avatarFilePath; 
	
	/*
	 * file path for audio file
	 */
	private String audioFilePath;
	
	/*
	 * determines if avatar is always on screen
	 */
	private boolean alwaysOnScreen; 
	
	/*
	 * size option of avatar 
	 */
	private int avatarSize; 
	
	/*
	 * determines if pomodoro timer is active
	 */
	private boolean pomodoroIsActive; 
	
	/*
	 * work period for pomodoro timer
	 */
	private int workPeriod; 
	
	/*
	 * break period for the Pomodoro Timer
	 */
	private int breakPeriod; 
	
	/*
	 * determines if times remaining on timer shows
	 */
	private boolean timeShowing; 
	
	/*
	 * determines if thought management features are active 
	 */
	private boolean ftsIsActive; 
	private boolean ntbIsActive; 
	private boolean htbIsActive; 
	
	/*
	 * determines if HTB is linked to NTB
	 */
	private boolean isAutoLinked; 
	
	/**
	 * Instantiating empty Settings object
	 */
	public Settings(int userID) {
		this.settingsID = 1;
		this.userID = userID;
		this.iconCircles = aa_grey; 
		this.icons = Color.white;
		this.opacityCircles = 100; 
		this.opacityIcons = 100; 
		this.isCollapsed = false; 
		this.xCoord = 0;
		this.yCoord = 0; 
		this.isVertical = true; 
		this.iconSize = 50; 
		this.timerIsVisible = true; 
		this.pmIsVisible = true; 
		this.ftsIsVisible = true; 
		this.htbIsVisible = true; 
		this.ntbIsVisible = true; 
		this.progReportIsVisible = true; 
		this.avatarIsActive = false; 
		this.textIsActive = true; 
		this.audioIsActive = false;
		this.avatarFilePath = "avatarSelection/avatar_dino.png"; 
		this.audioFilePath = "";
		this.alwaysOnScreen = false; 
		this.avatarSize = 100; 
		this.pomodoroIsActive = true; 
		this.workPeriod = 45; 
		this.breakPeriod = 15; 
		this.timeShowing = true; 
		this.ftsIsActive = true; 
		this.ntbIsActive = true; 
		this.isAutoLinked = true; 
		this.htbIsActive = true; 
	}
	
	/**
	 * Creates a Settings object loaded with settings from database
	 * @param DataBase
	 * @param int
	 *
	 * load settings from database for existing user 
	 */
	public Settings(DataBase db, int userID) {
		
		Settings loadSettings= db.SelectSettings(userID);
		
	}
	
	 /**
	 * Creates a Settings object loaded with all data types specified
	 * 
	 * Written as a placeholder for testing, once database is set up this
	 * constructor can be deleted
	 * 
	 * @param int, Color, Color, int, int, boolean, int, int, boolean, int, boolean, boolean,
	 * 		  boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, 
	 * 		  boolean, int, boolean, int, int, boolean, boolean, boolean, boolean, boolean
	 */
	public Settings(int settingsID, int userID, Color iconCircles, Color icons,	int opacityCircles, int opacityIcons, boolean isCollapsed, 
					int xCoord, int yCoord, boolean isVertical, int iconSize, boolean timerIsVisible, boolean pmIsVisible, 
					boolean ftsIsVisible, boolean htbIsVisible, boolean ntbIsVisible, boolean progReportIsVisible, 
					boolean avatarIsActive, boolean textIsActive, boolean audioIsActive, String avatarFilePath, String audioFilePath, 
					boolean alwaysOnScreen, int avatarSize, boolean pomodoroIsActive, int workPeriod, int breakPeriod, boolean timeShowing, 
					boolean ftsIsActive, boolean ntbIsActive, boolean isAutoLinked, boolean htbIsActive) {
		
		this.settingsID = settingsID;
		this.userID = userID;
		this.iconCircles = iconCircles;
		this.icons = icons;
		this.opacityCircles = opacityCircles; 
		this.opacityIcons = opacityIcons; 
		this.isCollapsed = isCollapsed; 
		this.xCoord = xCoord;
		this.yCoord = yCoord; 
		this.isVertical = isVertical;
		this.iconSize = iconSize;
		this.timerIsVisible = timerIsVisible; 
		this.pmIsVisible = pmIsVisible; 
		this.ftsIsVisible = ftsIsVisible; 
		this.htbIsVisible = htbIsVisible;
		this.ntbIsVisible = ntbIsVisible; 
		this.progReportIsVisible = progReportIsVisible; 
		this.avatarIsActive = avatarIsActive; 
		this.textIsActive = textIsActive; 
		this.audioIsActive = audioIsActive;
		this.avatarFilePath = avatarFilePath; 
		this.audioFilePath = audioFilePath;
		this.alwaysOnScreen = alwaysOnScreen; 
		this.avatarSize = avatarSize; 
		this.pomodoroIsActive = pomodoroIsActive; 
		this.workPeriod = workPeriod; 
		this.breakPeriod = breakPeriod; 
		this.timeShowing = timeShowing; 
		this.ftsIsActive = ftsIsActive; 
		this.ntbIsActive = ntbIsActive; 
		this.isAutoLinked = isAutoLinked; 
		this.htbIsActive = htbIsActive;
	}
	
	/**
	 * Instantiating copy constructor for Settings object
	 * @param Settings object
	 */
	public Settings(Settings stgs) {
		this.settingsID = stgs.settingsID;
		this.userID = stgs.userID;
		this.iconCircles = stgs.iconCircles;
		this.icons = stgs.icons;
		this.opacityCircles = stgs.opacityCircles; 
		this.opacityIcons = stgs.opacityIcons; 
		this.isCollapsed = stgs.isCollapsed; 
		this.xCoord = stgs.xCoord;
		this.yCoord = stgs.yCoord; 
		this.isVertical = stgs.isVertical;
		this.iconSize = stgs.iconSize;
		this.timerIsVisible = stgs.timerIsVisible; 
		this.pmIsVisible = stgs.pmIsVisible; 
		this.ftsIsVisible = stgs.ftsIsVisible; 
		this.htbIsVisible = stgs.htbIsVisible;
		this.ntbIsVisible = stgs.ntbIsVisible; 
		this.progReportIsVisible = stgs.progReportIsVisible; 
		this.avatarIsActive = stgs.avatarIsActive; 
		this.textIsActive = stgs.textIsActive; 
		this.audioIsActive = stgs.audioIsActive;
		this.avatarFilePath = stgs.avatarFilePath; 
		this.audioFilePath = stgs.audioFilePath;
		this.alwaysOnScreen = stgs.alwaysOnScreen; 
		this.avatarSize = stgs.avatarSize; 
		this.pomodoroIsActive = stgs.pomodoroIsActive; 
		this.workPeriod = stgs.workPeriod; 
		this.breakPeriod = stgs.breakPeriod; 
		this.timeShowing = stgs.timeShowing; 
		this.ftsIsActive = stgs.ftsIsActive; 
		this.ntbIsActive = stgs.ntbIsActive; 
		this.isAutoLinked = stgs.isAutoLinked; 
		this.htbIsActive = stgs.htbIsActive;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * get settingsID
	 * @return int
	 * 
	 */
	public int getSettingsID() {
		return this.settingsID;
	}
	
	/**
	 * set settingsID
	 * @param int
	 */
	public void setSettingsID(int settingsID) {
		this.settingsID = settingsID;
	}
	
	/**
	 * get userID
	 * @return int
	 * 
	 */
	public int getUserID() {
		return this.userID;
	}
	
	/**
	 * set userID
	 * @param int
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	 /*
	 * get iconCircles
	 * @return Color 
	 */
	public Color getIconCircles() {
		return this.iconCircles;
	}
	
	/**
	 * set iconCircles 
	 * @param Color
	 */
	public void setIconCircles(Color iconCircles) {
		this.iconCircles = iconCircles;
	}
	
	/**
	 * get icons
	 * @return Color
	 */
	public Color getIcons() {
		return this.icons;
	}
	
	/**
	 * set icons
	 * @param Color
	 */
	public void setIcons(Color icons) {
		this.icons = icons; 
	}
	
	/**
	 * get opacityCircles
	 * @return int
	 */
	public int getOpacityCircles() {
		return this.opacityCircles;
	}
	
	/**
	 * set opacityCircles
	 * @param int
	 */
	public void setOpacityCircles(int opacityCircles) {
		this.opacityCircles = opacityCircles; 
	}
	
	/**
	 * get opacityIcons
	 * @return int
	 */
	public int getOpacityIcons() {
		return this.opacityIcons;
	}
	
	/**
	 * set opacityIcons
	 * @param int
	 */
	public void setOpacityIcons(int opacityIcons) {
		this.opacityIcons = opacityIcons; 
	}
	
	/**
	 * get isCollapsed
	 * @return boolean
	 */
	public boolean getIsCollapsed() {
		return this.isCollapsed; 
	}
	
	/**
	 * set isCollapsed
	 * @param boolean
	 */
	public void setIsCollapsed(boolean isCollapsed) {
		this.isCollapsed = isCollapsed;
	}
	
	/**
	 * get xCoord
	 * @return int
	 */
	public int getXCoord() {
		return this.xCoord; 
	}
	
	/**
	 * set xCoord
	 * @param int
	 */
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	/**
	 * get yCoord
	 * @return int
	 */
	public int getYCoord() {
		return this.yCoord;
	}
	
	/**
	 * set yCoord
	 * @param int
	 */
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	/**
	 * get isVertical
	 * @return boolean
	 */
	public boolean getIsVertical() {
		return this.isVertical;
	}
	
	/**
	 * set isVertical
	 * @param boolean
	 */
	public void setIsVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	
	/**
	 * get iconSize
	 * @return int
	 */
	public int getIconSize() {
		return this.iconSize;
	}
	
	/**
	 * set iconSize
	 * @param int
	 */
	public void setIconSize(int iconSize) {
		this.iconSize = iconSize; 
	}
	
	/**
	 * get timerIsVisible
	 * @return boolean
	 */
	public boolean getTimerIsVisible() {
		return this.timerIsVisible;
	}
	
	/**
	 * set timerIsVisible
	 * @param boolean
	 */
	public void setTimerIsVisible(boolean timerIsVisible) {
		this.timerIsVisible = timerIsVisible;
	}
	
	/**
	 * get pmIsVisible
	 * @return boolean
	 */
	public boolean getPmIsVisible() {
		return this.pmIsVisible;
	}
	
	/**
	 * set pmIsVisible
	 * @param boolean
	 */
	public void setPmIsVisible(boolean pmIsVisible) {
		this.pmIsVisible = pmIsVisible;
	}
	
	/**
	 * get ftsIsVisible
	 * @return boolean
	 */
	public boolean getFtsIsVisible() {
		return this.ftsIsVisible;
	}
	
	/**
	 * set ftsIsVisible
	 * @param boolean
	 */
	public void setFtsIsVisible(boolean ftsIsVisible) {
		this.ftsIsVisible = ftsIsVisible;
	}
	
	/**
	 * get htbIsVisible
	 * @return boolean
	 */
	public boolean getHtbIsVisible() {
		return this.htbIsVisible;
	}
	
	/**
	 * set htbIsVisible
	 * @param boolean
	 */
	public void setHtbIsVisible(boolean htbIsVisible) {
		this.htbIsVisible = htbIsVisible;
	}
	
	/**
	 * get ntbIsVisible
	 * @return boolean
	 */
	public boolean getNtbIsVisible() {
		return this.ntbIsVisible;
	}
	
	/**
	 * set ntbIsVisible
	 * @param boolean
	 */
	public void setNtbIsVisible(boolean ntbIsVisible) {
		this.ntbIsVisible = ntbIsVisible;
	}
	
	/**
	 * get progReportIsVisible
	 * @return boolean
	 */
	public boolean getProgReportIsVisible() {
		return this.progReportIsVisible;
	}
	
	/**
	 * set progReportIsVisible
	 * @param boolean
	 */
	public void setProgReportIsVisible(boolean progReportIsVisible) {
		this.progReportIsVisible = progReportIsVisible;
	}
	
	/**
	 * get avatarIsActive
	 * @return boolean
	 */
	public boolean getAvatarIsActive() {
		return this.avatarIsActive;
	}
	
	/**
	 * set avatarIsActive
	 * @param boolean
	 */
	public void setAvatarIsActive(boolean avatarIsActive) {
		this.avatarIsActive = avatarIsActive;
	}
	
	/**
	 * get textIsActive
	 * @return boolean
	 */
	public boolean getTextIsActive() {
		return this.textIsActive;
	}
	
	/**
	 * set textIsACtive
	 * @param boolean
	 */
	public void setTextIsActive(boolean textIsActive) {
		this.textIsActive = textIsActive;
	}
	
	/**
	 * get audioIsActive
	 * @return boolean
	 */
	public boolean getAudioIsActive() {
		return this.audioIsActive;
	}
	
	/**
	 * set audioIsActive
	 * @param boolean
	 */
	public void setAudioIsActive(boolean audioIsActive) {
		this.audioIsActive = audioIsActive;
	}
	
	/**
	 * get avatarFilePath
	 * @return String
	 */
	public String getAvatarFilePath() {
		return this.avatarFilePath;
	}
	
	/**
	 * set avatarFilePath
	 * @param String
	 */
	public void setAvatarFilePath(String avatarFilePath) {
		this.avatarFilePath = avatarFilePath;
	}
	
	/**
	 * get adudioFilePath
	 * @return String
	 */
	public String getAudioFilePath() {
		return this.audioFilePath;
	}
	
	/**
	 * set audioFilePath
	 * @param String
	 */
	public void setAudioFilePath(String audioFilePath) {
		this.audioFilePath = audioFilePath;
	}
	
	/**
	 * get alwaysOnScreen
	 * @return boolean
	 */
	public boolean getAlwaysOnScreen() {
		return this.alwaysOnScreen;
	}
	
	/**
	 * set alwaysOnScreen
	 * @param boolean
	 */
	public void setAlwaysOnScreen(boolean alwaysOnScreen) {
		this.alwaysOnScreen = alwaysOnScreen;
	}
	
	/**
	 * get avatarSize
	 * @return int
	 */
	public int getAvatarSize() {
		return this.avatarSize;
	}
	
	/**
	 * set avatarSize
	 * @param int
	 */
	public void setAvatarSize(int avatarSize)
	{
		this.avatarSize = avatarSize;
	}
	
	/**
	 * get pomodoroIsActive
	 * @return boolean
	 */
	public boolean getPomodoroIsActive() {
		return this.pomodoroIsActive;
	}
	
	/**
	 * set pomodoroIsActive
	 * @param boolean
	 */
	public void setPomodoroIsActive(boolean pomodoroIsActive) {
		this.pomodoroIsActive = pomodoroIsActive;
	}
	
	/**
	 * get workPeriod
	 * @return int 
	 */
	public int getWorkPeriod() {
		return this.workPeriod;
	}
	
	/**
	 * set workPeriod
	 * @param int
	 */
	public void setWorkPeriod(int workPeriod) {
		this.workPeriod = workPeriod;
	}
	
	/**
	 * get breakPeriod
	 * @return int
	 */
	public int getBreakPeriod() {
		return this.breakPeriod;
	}
	
	/**
	 * set breakPeriod
	 * @param int
	 */
	public void setBreakPeriod(int breakPeriod) {
		this.breakPeriod = breakPeriod;
	}
	
	/**
	 * get timeShowing
	 * @return boolean
	 */
	public boolean getTimeShowing() {
		return this.timeShowing;
	}
	
	/**
	 * set timeShowing
	 * @param boolean
	 */
	public void setTimeShowing(boolean timeShowing) {
		this.timeShowing = timeShowing;
	}
	
	/**
	 * get ftsIsActive
	 * @return boolean
	 */
	public boolean getFtsIsActive() {
		return this.ftsIsActive;
	}
	
	/**
	 * set ftsIsActive
	 * @param boolean
	 */
	public void setFtsIsActive(boolean ftsIsActive) {
		this.ftsIsActive = ftsIsActive;
	}
	
	/**
	 * get ntbIsActive
	 * @return boolean
	 */
	public boolean getNtbIsActive() {
		return this.ntbIsActive;
	}
	
	/**
	 * set ntbIsActive
	 * @param boolean
	 */
	public void setNtbIsActive(boolean ntbIsActive) {
		this.ntbIsActive = ntbIsActive;
	}
	
	/**
	 * get isAutoLinked
	 * @return boolean
	 */
	public boolean getIsAutoLinked() {
		return this.isAutoLinked;
	}
	
	/**
	 * set isAutoLinked
	 * @param isAutoLinked
	 */
	public void setIsAutoLinked(boolean isAutoLinked) {
		this.isAutoLinked = isAutoLinked;
	}
	
	/**
	 * get htbIsActive
	 * @return boolean
	 */
	public boolean getHtbIsActive() {
		return this.htbIsActive;
	}
	
	/**
	 * set htbIsActive
	 * @param boolean
	 */
	public void setHtbIsActive(boolean htbIsActive) {
		this.htbIsActive = htbIsActive;
	}
	
	/**
	 * avatar selection window
	 * @param
	 */
	private void avatarSelectionWindow(Settings settingsChanges) {
		//create window for user to select avatar
		JFrame avatar_window = new JFrame("Select Your Avatar");
		avatar_window.setAlwaysOnTop(true);
		avatar_window.setBackground(Color.black);
		avatar_window.setUndecorated(true);
		avatar_window.setVisible(true);
		
		JPanel title_panel = new JPanel();
		title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		title_panel.setBackground(aa_grey);
		title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
		JLabel title = new JLabel("Select Your Avatar");
		title.setForeground(Color.white);
		title.setFont(new Font("Serif", Font.BOLD, 18));
		
		/*
		 * allows drag and drop of frame
		 */
		title_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				avatar_window.setLocation(avatar_window.getX() + e.getX() - mouseX, avatar_window.getY() + e.getY() - mouseY);
			}
		});
		
		title_panel.addMouseListener(new MouseAdapter(){
			@Override 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		//reads in images for the close and guide buttons
		BufferedImage ci = null;
		BufferedImage gi = null;
		BufferedImage dino = null;
		BufferedImage cat1 = null;
		BufferedImage cat2 = null;
		BufferedImage cat3 = null;
		BufferedImage duck = null;
		BufferedImage pompom = null;
		
		try {
			ci = ImageIO.read(new File("images/exit_circle.png"));
			gi = ImageIO.read(new File("images/guide.png"));
			dino = ImageIO.read(new File("avatarSelection/avatar_dino.png"));
			cat1 = ImageIO.read(new File("avatarSelection/avatar_cat1.png"));
			cat2 = ImageIO.read(new File("avatarSelection/avatar_cat2.png"));
			cat3 = ImageIO.read(new File("avatarSelection/avatar_cat3.png"));
			duck = ImageIO.read(new File("avatarSelection/avatar_duck.png"));
			pompom = ImageIO.read(new File("avatarSelection/avatar_pompom.png"));
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
        		avatar_window.dispose();
        	}
        });
		
		//create guide button with guide icon and no background
		Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon guideIcon = new ImageIcon(g_img);
		JButton guide = new JButton(guideIcon);
		guide.setBorderPainted(false);
		guide.setContentAreaFilled(false);
		guide.setFocusPainted(false);
		
		//adds title JLabel, empty space, then guide button and close button
		title_panel.add(title);
		title_panel.add(Box.createRigidArea(new Dimension(275, 0)));
		title_panel.add(guide);
		title_panel.add(close_window);
		
		JPanel avatarChoices = new JPanel();
		avatarChoices.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, aa_purple));
		avatarChoices.setBackground(Color.black);
		
		GridLayout avatarGrid = new GridLayout(0,3);
		avatarChoices.setLayout(avatarGrid);
		
		/*
		 * make all avatar images into icons
		 */
		Image dino_img = dino.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon dinoOption = new ImageIcon(dino_img);
		Image cat1_img = cat1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon cat1Option = new ImageIcon(cat1_img);
		Image cat2_img = cat2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon cat2Option = new ImageIcon(cat2_img);
		Image cat3_img = cat3.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon cat3Option = new ImageIcon(cat3_img);
		Image duck_img = duck.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon duckOption = new ImageIcon(duck_img);
		Image pompom_img = pompom.getScaledInstance(150, 100, java.awt.Image.SCALE_SMOOTH);
		Icon pompomOption = new ImageIcon(pompom_img);
		
		/*
		 * create all avatar buttons
		 */
		JButton dino_avatar = new JButton(dinoOption);
		JButton cat1_avatar = new JButton(cat1Option);
		JButton cat2_avatar = new JButton(cat2Option);
		JButton cat3_avatar = new JButton(cat3Option);
		JButton duck_avatar = new JButton(duckOption);
		JButton pompom_avatar = new JButton(pompomOption);
		
		/*
		 * dino_avatar button details
		 */
		dino_avatar.setBorderPainted(false);
		dino_avatar.setContentAreaFilled(false);
		if(settingsChanges.avatarFilePath == "avatarSelection/avatar_dino.png") {
			dino_avatar.setBorderPainted(true);
			dino_avatar.setBorder(new LineBorder(aa_purple));
		}else {
			dino_avatar.setBorderPainted(false);
		}
		dino_avatar.setFocusPainted(false);
		dino_avatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//select dino avatar
        		settingsChanges.avatarFilePath = "avatarSelection/avatar_dino.png";
        		cat1_avatar.setBorderPainted(false);
        		cat2_avatar.setBorderPainted(false);
        		cat3_avatar.setBorderPainted(false);
				duck_avatar.setBorderPainted(false);
				pompom_avatar.setBorderPainted(false);
				dino_avatar.setBorderPainted(true);
				dino_avatar.setBorder(new LineBorder(aa_purple));
        	}
        });
		
		/*
		 * cat1_avatar button details
		 */		
		cat1_avatar.setBorderPainted(false);
		cat1_avatar.setContentAreaFilled(false);
		cat1_avatar.setBorderPainted(false);
		if(settingsChanges.avatarFilePath == "avatarSelection/avatar_cat1.png") {
			cat1_avatar.setBorderPainted(true);
			cat1_avatar.setBorder(new LineBorder(aa_purple));
		}else {
			cat1_avatar.setBorderPainted(false);
		}
		cat1_avatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//select cat1 avatar
        		settingsChanges.avatarFilePath = "avatarSelection/avatar_cat1.png";
        		dino_avatar.setBorderPainted(false);
        		cat2_avatar.setBorderPainted(false);
        		cat3_avatar.setBorderPainted(false);
				duck_avatar.setBorderPainted(false);
				pompom_avatar.setBorderPainted(false);
				cat1_avatar.setBorderPainted(true);
				cat1_avatar.setBorder(new LineBorder(aa_purple));
        	}
        });
		
		/*
		 * cat2_avatar button details
		 */
		cat2_avatar.setBorderPainted(false);
		cat2_avatar.setContentAreaFilled(false);
		if(settingsChanges.avatarFilePath == "avatarSelection/avatar_cat2.png") {
			cat2_avatar.setBorderPainted(true);
			cat2_avatar.setBorder(new LineBorder(aa_purple));
		}else {
			cat2_avatar.setBorderPainted(false);
		}
		cat2_avatar.setFocusPainted(false);
		cat2_avatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//select cat2 avatar
        		settingsChanges.avatarFilePath = "avatarSelection/avatar_cat2.png";
        		dino_avatar.setBorderPainted(false);
        		cat1_avatar.setBorderPainted(false);
        		cat3_avatar.setBorderPainted(false);
				duck_avatar.setBorderPainted(false);
				pompom_avatar.setBorderPainted(false);
				cat2_avatar.setBorderPainted(true);
				cat2_avatar.setBorder(new LineBorder(aa_purple));
        	}
        });
		
		/*
		 * cat3_avatar button details
		 */
		cat3_avatar.setBorderPainted(false);
		cat3_avatar.setContentAreaFilled(false);
		if(settingsChanges.avatarFilePath == "avatarSelection/avatar_cat3.png") {
			cat3_avatar.setBorderPainted(true);
			cat3_avatar.setBorder(new LineBorder(aa_purple));
		}else {
			cat3_avatar.setBorderPainted(false);
		}
		cat3_avatar.setFocusPainted(false);
		cat3_avatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//select cat3 avatar
        		settingsChanges.avatarFilePath = "avatarSelection/avatar_cat3.png";
        		dino_avatar.setBorderPainted(false);
        		cat1_avatar.setBorderPainted(false);
        		cat2_avatar.setBorderPainted(false);
				duck_avatar.setBorderPainted(false);
				pompom_avatar.setBorderPainted(false);
				cat3_avatar.setBorderPainted(true);
				cat3_avatar.setBorder(new LineBorder(aa_purple));
        	}
        });
		
		/*
		 * duck_avatar button details
		 */
		duck_avatar.setBorderPainted(false);
		duck_avatar.setContentAreaFilled(false);
		if(settingsChanges.avatarFilePath == "avatarSelection/avatar_duck.png") {
			duck_avatar.setBorderPainted(true);
			duck_avatar.setBorder(new LineBorder(aa_purple));
		}else {
			duck_avatar.setBorderPainted(false);
		}
		duck_avatar.setFocusPainted(false);
		duck_avatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//select duck avatar
        		settingsChanges.avatarFilePath = "avatarSelection/avatar_duck.png";
        		dino_avatar.setBorderPainted(false);
        		cat1_avatar.setBorderPainted(false);
        		cat2_avatar.setBorderPainted(false);
				cat3_avatar.setBorderPainted(false);
				pompom_avatar.setBorderPainted(false);
				duck_avatar.setBorderPainted(true);
				duck_avatar.setBorder(new LineBorder(aa_purple));
        	}
        });
		
		/*
		 * pompom_avatar button details
		 */	
		pompom_avatar.setBorderPainted(false);
		pompom_avatar.setContentAreaFilled(false);
		if(settingsChanges.avatarFilePath == "avatarSelection/avatar_pompom.png") {
			pompom_avatar.setBorderPainted(true);
			pompom_avatar.setBorder(new LineBorder(aa_purple));
		}else {
			pompom_avatar.setBorderPainted(false);
		}
		pompom_avatar.setFocusPainted(false);
		pompom_avatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//select pompom avatar
        		settingsChanges.avatarFilePath = "avatarSelection/avatar_pompom.png";
        		dino_avatar.setBorderPainted(false);
        		cat1_avatar.setBorderPainted(false);
        		cat2_avatar.setBorderPainted(false);
				cat3_avatar.setBorderPainted(false);
				duck_avatar.setBorderPainted(false);
				pompom_avatar.setBorderPainted(true);
				pompom_avatar.setBorder(new LineBorder(aa_purple));
        	}
        });
		
		avatarChoices.add(dino_avatar);
		avatarChoices.add(cat1_avatar);
		avatarChoices.add(cat2_avatar);
		avatarChoices.add(cat3_avatar);
		avatarChoices.add(duck_avatar);
		avatarChoices.add(pompom_avatar);
		
		JButton close_avatarWindow = new JButton("select");
		close_avatarWindow.setForeground(Color.white);
		close_avatarWindow.setFont(new Font("Serif", Font.BOLD, 16));
		close_avatarWindow.setContentAreaFilled(true);
		close_avatarWindow.setBorderPainted(false);
		close_avatarWindow.setFocusPainted(false);
		close_avatarWindow.setBackground(aa_purple);
		close_avatarWindow.setMaximumSize(new Dimension(75,20));
		close_avatarWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * create new icon to display updated avatar selection in frame
				 */
				BufferedImage avatar = null;
				try {
					//will pass string for file path 
					avatar = ImageIO.read(new File(settingsChanges.avatarFilePath));
				}catch(Exception f) {
					f.printStackTrace();
					System.exit(1);
				}
				
				Image av_img = avatar.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
				Icon newAvatarChoice = new ImageIcon(av_img);
				
				/*
				 * updates avatar preview in notifications_panel once selection window is closed
				 * even though preview is updated, changes are *not* sent to database
				 * unless user clicks 'apply' 
				 */
				displayAvatar.setIcon(newAvatarChoice); 
				
				avatar_window.dispose();
			}
		});
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BoxLayout(bottom_panel, BoxLayout.X_AXIS));
		bottom_panel.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, aa_purple));
		bottom_panel.add(Box.createRigidArea(new Dimension(475, 0)));
		bottom_panel.add(close_avatarWindow);
		bottom_panel.setBackground(aa_grey);
		
		//sets location and dimensions of task window
		int x = (int) ((screen.getWidth() - avatar_window.getWidth()) /2);
		int y = (int) ((screen.getHeight() - avatar_window.getHeight()) /2);
		avatar_window.setLocation(x, y);
		
		avatar_window.add(title_panel, BorderLayout.PAGE_START); 
		avatar_window.add(avatarChoices, BorderLayout.CENTER);
		avatar_window.add(bottom_panel, BorderLayout.PAGE_END);
		avatar_window.pack();
	}
	
	/**
	 * RHS display for General Settings sub menu
	 * 
	 */
	private void createGeneralPanel(JPanel card_panel, Settings settingsChanges) {
		
		JPanel general_panel = new JPanel();
		general_panel.setLayout(new BoxLayout(general_panel, BoxLayout.Y_AXIS));
		general_panel.setBackground(aa_grey);
		
		JPanel header_panel = new JPanel();
		header_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		header_panel.setBackground(aa_grey);
		
		JLabel overlayOptions = new JLabel("Overlay Options:");
		overlayOptions.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		overlayOptions.setForeground(Color.white);
		
		header_panel.add(Box.createRigidArea(new Dimension(15, 0)));
		header_panel.add(overlayOptions);
		
		JPanel backgroundColorOptions = new JPanel();
		backgroundColorOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		backgroundColorOptions.setBackground(aa_grey);

		
		JLabel backgroundColor = new JLabel(("<html><center>Navigation Bar" + "<br/>Background Color: </center></html>"));
		backgroundColor.setFont(new Font("Serif", Font.BOLD, 16));
		backgroundColor.setForeground(Color.white);
		
		//JColorChooser API allows color selection to change color of Frame - use to change color of circles and icons of nav_bar
		JButton backgroundColorChooser = new JButton("select color");
		backgroundColorChooser.setBackground(Color.GRAY);
		backgroundColorChooser.setForeground(Color.WHITE);
		backgroundColorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open color chooser dialog
				
				Color initialcolor = settingsChanges.iconCircles;
				Color newBackgroundColor = JColorChooser.showDialog(null,"Select a Background Color", initialcolor);
				
				settingsChanges.iconCircles = newBackgroundColor; 
			}
		});
		
		backgroundColorOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		backgroundColorOptions.add(backgroundColor);
		backgroundColorOptions.add(backgroundColorChooser);
		
		JPanel backgroundOpacityOptions = new JPanel();
		backgroundOpacityOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		backgroundOpacityOptions.setBackground(aa_grey);
		
		JLabel backgroundOpacity = new JLabel("<html><center>Background" + "<br/>Opacity: </center></html>");
		backgroundOpacity.setFont(new Font("Serif", Font.BOLD, 16));
		backgroundOpacity.setForeground(Color.white);
		
		JSlider backgroundOpacitySlider = new JSlider(JSlider.HORIZONTAL, 10, 100, settingsChanges.opacityCircles);
		backgroundOpacitySlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		backgroundOpacitySlider.setBackground(aa_grey);
		backgroundOpacitySlider.setForeground(Color.white);
		backgroundOpacitySlider.setMinorTickSpacing(15);
		backgroundOpacitySlider.setMajorTickSpacing(15);
		backgroundOpacitySlider.setPaintTicks(true);
		backgroundOpacitySlider.setPaintLabels(true);		
		backgroundOpacitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				settingsChanges.opacityCircles = backgroundOpacitySlider.getValue();
			}
		});
		
		backgroundOpacityOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		backgroundOpacityOptions.add(backgroundOpacity);
		backgroundOpacityOptions.add(backgroundOpacitySlider);
		
		JPanel iconColorOptions = new JPanel();
		iconColorOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		iconColorOptions.setBackground(aa_grey);
		
		JLabel iconColor = new JLabel(("<html><center>Navigation Bar" + "<br/>Icon Color: </center></html>"));
		iconColor.setFont(new Font("Serif", Font.BOLD, 16));
		iconColor.setForeground(Color.white);
		
		//JColorChooser API allows color selection to change color of Frame - use to change color of circles and icons of nav_bar
		JButton iconColorChooser = new JButton("select color");
		iconColorChooser.setBackground(Color.GRAY);
		iconColorChooser.setForeground(Color.WHITE);
		iconColorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open color chooser dialog
				
				Color initialcolor = settingsChanges.icons;
				Color newIconColor = JColorChooser.showDialog(null,"Select an Icon Color", initialcolor);
				
				settingsChanges.icons = newIconColor; 
			}
		});
		
		iconColorOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		iconColorOptions.add(iconColor);
		iconColorOptions.add(iconColorChooser);
		
		JPanel iconOpacityOptions = new JPanel();
		iconOpacityOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		iconOpacityOptions.setBackground(aa_grey);
		
		JLabel iconOpacity = new JLabel("<html><center>Icon" + "<br/>Opacity: </center></html>");
		iconOpacity.setFont(new Font("Serif", Font.BOLD, 16));
		iconOpacity.setForeground(Color.white);
		
		JSlider iconOpacitySlider = new JSlider(JSlider.HORIZONTAL, 10, 100, settingsChanges.opacityIcons);
		iconOpacitySlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		iconOpacitySlider.setBackground(aa_grey);
		iconOpacitySlider.setForeground(Color.white);
		iconOpacitySlider.setMinorTickSpacing(15);
		iconOpacitySlider.setMajorTickSpacing(15);
		iconOpacitySlider.setPaintTicks(true);
		iconOpacitySlider.setPaintLabels(true);
		iconOpacitySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				settingsChanges.opacityIcons = iconOpacitySlider.getValue();
			}
		});
		
		iconOpacityOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		iconOpacityOptions.add(iconOpacity);
		iconOpacityOptions.add(iconOpacitySlider);
		
		JPanel navbar_panel = new JPanel();
		navbar_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		navbar_panel.setBackground(aa_grey);
		
		JLabel navbarFormat = new JLabel("Navbar Format:");
		navbarFormat.setFont(new Font("Serif", Font.BOLD, 16));
		navbarFormat.setForeground(Color.white); 
		
		navbar_panel.add(Box.createRigidArea(new Dimension(15, 0)));
		navbar_panel.add(navbarFormat);
		
		JCheckBox collapsed = new JCheckBox("Collapsed", settingsChanges.isCollapsed);
		collapsed.setFont(new Font("Serif", Font.BOLD, 15));
		collapsed.setForeground(Color.white);
		collapsed.setContentAreaFilled(false);
		collapsed.setFocusPainted(false);	
		collapsed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.isCollapsed = collapsed.isSelected(); 
			}
		});
		
		navbar_panel.add(collapsed);
		
		JPanel iconSizeOptions = new JPanel();
		iconSizeOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		iconSizeOptions.setBackground(aa_grey);
		
		JLabel iconSize = new JLabel("<html><center>Icon" + "<br/>Size: </center></html>");
		iconSize.setFont(new Font("Serif", Font.BOLD, 16));
		iconSize.setForeground(Color.white);
		
		JSlider iconSizeSlider = new JSlider(JSlider.HORIZONTAL, 35, 95, settingsChanges.iconSize);
		iconSizeSlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		iconSizeSlider.setBackground(aa_grey);
		iconSizeSlider.setForeground(Color.white);
		iconSizeSlider.setMinorTickSpacing(15);
		iconSizeSlider.setMajorTickSpacing(15);
		iconSizeSlider.setPaintTicks(true);
		iconSizeSlider.setPaintLabels(true);
		iconSizeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				settingsChanges.iconSize = iconSizeSlider.getValue();
			}
		});
		
		iconSizeOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		iconSizeOptions.add(iconSize);
		iconSizeOptions.add(iconSizeSlider);
		
		JPanel orientation_panel = new JPanel();
		orientation_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		orientation_panel.setBackground(aa_grey);
		
		JLabel navbarOrientation = new JLabel("Navbar Orientation:");
		navbarOrientation.setFont(new Font("Serif", Font.BOLD, 16));
		navbarOrientation.setForeground(Color.white); 
		
		orientation_panel.add(Box.createRigidArea(new Dimension(15, 0)));
		orientation_panel.add(navbarOrientation);
		
		JPanel orientationBoxes = new JPanel();
		orientationBoxes.setLayout(new FlowLayout(FlowLayout.LEFT));
		orientationBoxes.setBackground(aa_grey);
		
		JCheckBox vertical = new JCheckBox("Vertical", settingsChanges.isVertical);
		vertical.setFont(new Font("Serif", Font.BOLD, 15));
		vertical.setForeground(Color.white);
		vertical.setContentAreaFilled(false);
		vertical.setFocusPainted(false);
		vertical.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.isVertical = vertical.isSelected(); 
			}
		});
		
		orientationBoxes.add(vertical);		
		orientation_panel.add(orientationBoxes);
		
		JPanel featuresHeader = new JPanel();
		featuresHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
		featuresHeader.setBackground(aa_grey);
		featuresHeader.setMaximumSize(new Dimension(400, 25));
		
		JLabel activeFeatures = new JLabel("Features Displayed in NavBar:");
		activeFeatures.setFont(new Font("Serif", Font.BOLD, 16));
		activeFeatures.setForeground(Color.white); 
		
		featuresHeader.add(Box.createRigidArea(new Dimension(15, 0)));
		featuresHeader.add(activeFeatures);
		
		JPanel navBarBoxes = new JPanel();
		GridLayout grid = new GridLayout(0,2);
		navBarBoxes.setLayout(grid);
		navBarBoxes.setBackground(aa_grey);
		navBarBoxes.setMaximumSize(new Dimension(325, 60));
		
		//JCheckBox timerVisibleBox = new JCheckBox("Pomodoro Timer", settingsChanges.timerIsVisible);
		timerVisibleBox.setSelected(settingsChanges.timerIsVisible); 
		timerVisibleBox.setFont(new Font("Serif", Font.BOLD, 16));
		timerVisibleBox.setForeground(Color.white);
		timerVisibleBox.setContentAreaFilled(false);
		timerVisibleBox.setFocusPainted(false);
		timerVisibleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.timerIsVisible = timerVisibleBox.isSelected(); 
			}
		});
		
		JCheckBox pmVisibleBox = new JCheckBox("Priority Manager", settingsChanges.pmIsVisible);
		pmVisibleBox.setFont(new Font("Serif", Font.BOLD, 16));
		pmVisibleBox.setForeground(Color.white);
		pmVisibleBox.setContentAreaFilled(false);
		pmVisibleBox.setFocusPainted(false);
		pmVisibleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.pmIsVisible = pmVisibleBox.isSelected(); 
			}
		});
		
		//JCheckBox ftsBox = new JCheckBox("<html><center>Free Thought" + "<br/>Space</center></html>", settingsChanges.ftsIsVisible);
		ftsVisibleBox.setSelected(settingsChanges.ftsIsVisible);
		ftsVisibleBox.setFont(new Font("Serif", Font.BOLD, 16));
		ftsVisibleBox.setForeground(Color.white);
		ftsVisibleBox.setContentAreaFilled(false);
		ftsVisibleBox.setFocusPainted(false);
		ftsVisibleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.ftsIsVisible = ftsVisibleBox.isSelected(); 
			}
		});
		
		//JCheckBox ntbBox = new JCheckBox("<html><center>Negative Thought" + "<br/>Burner</center></html>", settingsChanges.ntbIsVisible);
		ntbVisibleBox.setSelected(settingsChanges.ntbIsVisible); 
		ntbVisibleBox.setFont(new Font("Serif", Font.BOLD, 16));
		ntbVisibleBox.setForeground(Color.white);
		ntbVisibleBox.setContentAreaFilled(false);
		ntbVisibleBox.setFocusPainted(false);
		ntbVisibleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.ntbIsVisible = ntbVisibleBox.isSelected(); 
			}
		});
		
		//JCheckBox htbVisibleBox = new JCheckBox("<html><center>Happy Thought" + "<br/>Button</center></html>", settingsChanges.htbIsVisible);
		htbVisibleBox.setSelected(settingsChanges.htbIsVisible); 
		htbVisibleBox.setFont(new Font("Serif", Font.BOLD, 16));
		htbVisibleBox.setForeground(Color.white);
		htbVisibleBox.setContentAreaFilled(false);
		htbVisibleBox.setFocusPainted(false);
		htbVisibleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.htbIsVisible = htbVisibleBox.isSelected(); 
			}
		});
		
		JCheckBox prVisibleBox = new JCheckBox("Progress Report", settingsChanges.progReportIsVisible);
		prVisibleBox.setFont(new Font("Serif", Font.BOLD, 16));
		prVisibleBox.setForeground(Color.white);
		prVisibleBox.setContentAreaFilled(false);
		prVisibleBox.setFocusPainted(false);
		prVisibleBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.progReportIsVisible = prVisibleBox.isSelected(); 
			}
		});
		
		navBarBoxes.add(timerVisibleBox);
		navBarBoxes.add(pmVisibleBox);
		navBarBoxes.add(ftsVisibleBox);
		navBarBoxes.add(ntbVisibleBox);
		navBarBoxes.add(htbVisibleBox);
		navBarBoxes.add(prVisibleBox);
		
		/*
		 * add everything to general_panel
		 */
		general_panel.add(header_panel);
		general_panel.add(backgroundColorOptions);
		general_panel.add(backgroundOpacityOptions);
		general_panel.add(iconColorOptions);
		general_panel.add(iconOpacityOptions);
		general_panel.add(navbar_panel);
		general_panel.add(iconSizeOptions);
		general_panel.add(orientation_panel);
		general_panel.add(featuresHeader);
		general_panel.add(navBarBoxes);
		general_panel.add(Box.createRigidArea(new Dimension(0, 100)));
		
		//add to general_panel to  card_panel
		card_panel.add("general", general_panel);		
	}
	
	/**
	 * RHS display for Notifications System
	 */
	private void createNotificationsPanel(JPanel card_panel, Settings settingsChanges) {
		
		JPanel notifications_panel = new JPanel();
		notifications_panel.setLayout(new BoxLayout(notifications_panel, BoxLayout.Y_AXIS));
		notifications_panel.setBackground(aa_grey);
		
		JPanel optionsAndDisplay = new JPanel();
		optionsAndDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
		optionsAndDisplay.setBackground(aa_grey);
		optionsAndDisplay.setMaximumSize(new Dimension(400, 130));
		
		JPanel optionsRadioButtons = new JPanel();
		optionsRadioButtons.setLayout(new BoxLayout(optionsRadioButtons, BoxLayout.Y_AXIS));
		optionsRadioButtons.setBackground(aa_grey);
		JPanel avatarDisplay = new JPanel();
		avatarDisplay.setBackground(aa_grey);
		avatarDisplay.setLayout(new BoxLayout(avatarDisplay, BoxLayout.Y_AXIS));
		
		JLabel notificationOptions = new JLabel("Notification Options:");
		notificationOptions.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		notificationOptions.setForeground(Color.white);
		
		JRadioButton avatarRB = new JRadioButton("Avatar", settingsChanges.avatarIsActive);
		JRadioButton textRB = new JRadioButton("Text", settingsChanges.textIsActive);
		JRadioButton audioRB = new JRadioButton("Audio", settingsChanges.audioIsActive);
		
		ButtonGroup notificationsOptions = new ButtonGroup();
		notificationsOptions.add(avatarRB);
		notificationsOptions.add(textRB);
		notificationsOptions.add(audioRB);
		
		/*
		 * avatar Selection details
		 */
		avatarRB.setFont(new Font("Serif", Font.BOLD, 16));
		avatarRB.setForeground(Color.white);
		avatarRB.setContentAreaFilled(false);
		avatarRB.setFocusPainted(false);
		avatarRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.avatarIsActive = avatarRB.isSelected(); 
				
				if(settingsChanges.avatarIsActive == true) {
					settingsChanges.textIsActive = false;
					settingsChanges.audioIsActive = false;
				} 
			}
		});
		
		/*
		 * text selection details
		 */
		textRB.setFont(new Font("Serif", Font.BOLD, 16));
		textRB.setForeground(Color.white);
		textRB.setContentAreaFilled(false);
		textRB.setFocusPainted(false);
		textRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.textIsActive = textRB.isSelected(); 
				
				if(settingsChanges.textIsActive == true) {
					settingsChanges.avatarIsActive = false;
					settingsChanges.audioIsActive = false;
				}
			}
		});
		
		/*
		 * audio selection details
		 */
		audioRB.setFont(new Font("Serif", Font.BOLD, 16));
		audioRB.setForeground(Color.white);
		audioRB.setContentAreaFilled(false);
		audioRB.setFocusPainted(false);
		audioRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.audioIsActive = audioRB.isSelected(); 
				
				if(settingsChanges.audioIsActive == true) {
					settingsChanges.avatarIsActive = false;
					settingsChanges.textIsActive = false;
				}
			}
		});
		
		optionsRadioButtons.add(Box.createRigidArea(new Dimension(35, 0)));
		optionsRadioButtons.add(notificationOptions);
		optionsRadioButtons.add(avatarRB);
		optionsRadioButtons.add(textRB);
		optionsRadioButtons.add(audioRB);
				
		/*
		 * displaying sample avatar selection as a placeholder 
		 * will be updated to link directly to the choose button as the action listener
		 */
		BufferedImage avatar = null;
		try {
			//will pass string for file path 
			avatar = ImageIO.read(new File(settingsChanges.avatarFilePath));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image av_img = avatar.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon defaultAvatar = new ImageIcon(av_img);
		displayAvatar = new JLabel(defaultAvatar);
		
		avatarDisplay.add(Box.createRigidArea(new Dimension(50, 0)));
		avatarDisplay.add(displayAvatar);
		optionsAndDisplay.add(optionsRadioButtons);
		optionsAndDisplay.add(avatarDisplay);
		
		JPanel avatarSizeOptions = new JPanel();
		avatarSizeOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		avatarSizeOptions.setBackground(aa_grey);
		avatarSizeOptions.setMaximumSize(new Dimension(500, 60));
		
		JLabel avatarSize = new JLabel("Avatar Size: ");
		avatarSize.setFont(new Font("Serif", Font.BOLD, 16));
		avatarSize.setForeground(Color.white);
		
		JSlider avatarSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, settingsChanges.avatarSize);
		avatarSizeSlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		avatarSizeSlider.setBackground(aa_grey);
		avatarSizeSlider.setForeground(Color.white);
		avatarSizeSlider.setMinorTickSpacing(25);
		avatarSizeSlider.setMajorTickSpacing(25);
		avatarSizeSlider.setPaintTicks(true);
		avatarSizeSlider.setPaintLabels(true);
		avatarSizeSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				settingsChanges.avatarSize = avatarSizeSlider.getValue();
			}
		});
		
		avatarSizeOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		avatarSizeOptions.add(avatarSize);
		avatarSizeOptions.add(avatarSizeSlider);
		
		JPanel voice = new JPanel();
		voice.setLayout(new FlowLayout(FlowLayout.LEFT));
		voice.setBackground(aa_grey);
		voice.setMaximumSize(new Dimension(500, 35));
		
		JLabel voiceSelection = new JLabel("Audio Selection:");
		voiceSelection.setFont(new Font("Serif", Font.BOLD, 16));
		voiceSelection.setForeground(Color.white);
		
		JButton chooseVoice = new JButton("Choose");
		chooseVoice.setMaximumSize(new Dimension(70,20));
		chooseVoice.setBackground(Color.GRAY);
		chooseVoice.setForeground(Color.WHITE);
		chooseVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * needs to point to dedicated pre-loaded file where audio selections will be
				 * leave commented out until if/when audio is an option
				 * 
				 *  JFileChooser audioDirectory = new JFileChooser(settingsChanges.audioFilePath);
					audioDirectory.setFileSelectionMode(JFileChooser.FILES_ONLY);
					FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("MP3 AUDIO", "mp3");
					audioDirectory.setFileFilter(fileFilter);
					int returnVal = audioDirectory.showDialog(null, "Select audio file");
					
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						File audioFile = audioDirectory.getSelectedFile();
						settingsChanges.audioFilePath = audioFile.getAbsolutePath();
					}
				 */
			}
		});
		
		voice.add(Box.createRigidArea(new Dimension(15, 0)));
		voice.add(voiceSelection);
		voice.add(chooseVoice);
		
		JPanel avLabelAndBox = new JPanel();
		avLabelAndBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		avLabelAndBox.setBackground(aa_grey);
		avLabelAndBox.setMaximumSize(new Dimension(500, 35));
		
		JLabel avatarLabel = new JLabel("Avatar:");
		avatarLabel.setFont(new Font("Serif", Font.BOLD, 16));
		avatarLabel.setForeground(Color.white);
		
		JCheckBox onScreenBox = new JCheckBox("Always on Screen", settingsChanges.alwaysOnScreen);
		onScreenBox.setFont(new Font("Serif", Font.BOLD, 16));
		onScreenBox.setForeground(Color.white);
		onScreenBox.setContentAreaFilled(false);
		onScreenBox.setFocusPainted(false);
		onScreenBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.alwaysOnScreen = onScreenBox.isSelected(); 
			}
		});
		
		avLabelAndBox.add(Box.createRigidArea(new Dimension(15, 0)));
		avLabelAndBox.add(avatarLabel);
		avLabelAndBox.add(onScreenBox);
		
		JPanel avatarSelectionOptions = new JPanel();
		avatarSelectionOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		avatarSelectionOptions.setBackground(aa_grey);
		avatarSelectionOptions.setMaximumSize(new Dimension(500, 35));
		
		JLabel avatarSelection = new JLabel("Select Your Avatar: ");
		avatarSelection.setFont(new Font("Serif", Font.BOLD, 16));
		avatarSelection.setForeground(Color.white);
		
		JButton chooseAvatar = new JButton("Choose");
		chooseAvatar.setMaximumSize(new Dimension(70,20));
		chooseAvatar.setBackground(Color.GRAY);
		chooseAvatar.setForeground(Color.WHITE);
		chooseAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				avatarSelectionWindow(settingsChanges);
			}
		});
		
		avatarSelectionOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		avatarSelectionOptions.add(avatarSelection);
		avatarSelectionOptions.add(chooseAvatar);
		
		/*
		 * add everything to notifications_panel
		 */
		notifications_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		notifications_panel.add(optionsAndDisplay);
		notifications_panel.add(avatarSizeOptions);
		notifications_panel.add(avatarSelectionOptions);
		notifications_panel.add(avLabelAndBox);
		notifications_panel.add(voice);
		notifications_panel.add(Box.createRigidArea(new Dimension(0, 100)));
		
		//add notifications panel to card_panel
		card_panel.add("notifications", notifications_panel);
	}
	
	/**
	 * RHS display for Priority Manager
	 */
	private void createPriorityManagerPanel(JPanel card_panel, Settings settingsChanges, Priority_Manager priority_manager, DataBase db, Observer observer) {
		
		JPanel pm_panel = new JPanel();
		pm_panel.setLayout(new BoxLayout(pm_panel, BoxLayout.Y_AXIS));
		pm_panel.setBackground(aa_grey);
		
		JLabel managerOptions = new JLabel("Manager Options:"); 
		managerOptions.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		managerOptions.setForeground(Color.white);
		
		JButton openPM = new JButton("Open Priority Manager");
		openPM.setMaximumSize(new Dimension(200, 30));
		openPM.setBackground(Color.GRAY);
		openPM.setForeground(Color.WHITE);
		openPM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				priority_manager.open_pm(db,observer);
			}
		});
		
		JButton calendar = new JButton("Calendar");
		calendar.setMaximumSize(new Dimension(200, 30));
		calendar.setBackground(Color.GRAY);
		calendar.setForeground(Color.WHITE);
		calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open calendar
				priority_manager.userCalendar();
			}
		});
		
		JButton calendarInt = new JButton("Calendar Integration");
		calendarInt.setMaximumSize(new Dimension(200, 30));
		calendarInt.setBackground(Color.GRAY);
		calendarInt.setForeground(Color.WHITE);
		calendarInt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to integrate calendar
				priority_manager.importCalendar();
			}
		});
		
		pm_panel.add(Box.createRigidArea(new Dimension(25, 10)));
		pm_panel.add(managerOptions);
		pm_panel.add(Box.createRigidArea(new Dimension(0, 25)));
		pm_panel.add(openPM);
		pm_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		pm_panel.add(calendar);
		pm_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		pm_panel.add(calendarInt);
	
		card_panel.add("priority manager", pm_panel);
	}
	
	/**
	 * RHS display for Pomodoro Timer
	 */
	private void createPomodoroTimerPanel(JPanel card_panel, Settings settingsChanges, Pomodoro_Timer pomodoro_timer, Priority_Manager pm) {
		
		JPanel pomodoro_panel = new JPanel();
		pomodoro_panel.setLayout(new BoxLayout(pomodoro_panel, BoxLayout.Y_AXIS));
		pomodoro_panel.setBackground(aa_grey);
		
		JPanel pomodoro_header = new JPanel();
		pomodoro_header.setLayout(new FlowLayout(FlowLayout.LEFT));
		pomodoro_header.setBackground(aa_grey);
		pomodoro_header.setMaximumSize(new Dimension(400, 25));
		
		JLabel pomHeader = new JLabel("Pomodoro Timer:");
		pomHeader.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		pomHeader.setForeground(Color.white);
		
		pomodoro_header.add(Box.createRigidArea(new Dimension(15, 0)));
		pomodoro_header.add(pomHeader);
		
		//JButton openPom = new JButton("Open Pomodoro Timer");
		openPom.setMaximumSize(new Dimension(200, 30));
		openPom.setBackground(Color.GRAY);
		openPom.setForeground(Color.WHITE);
		openPom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//implement chooseAvatar 
				pomodoro_timer.run_pomo(settingsChanges,pm);
			}
		});
		
		JPanel pomToggle = new JPanel();
		pomToggle.setLayout(new FlowLayout(FlowLayout.LEFT));
		pomToggle.setBackground(aa_grey);
		pomToggle.setMaximumSize(new Dimension(400, 35));
		
		JCheckBox toggleTimer = new JCheckBox("Timer Active", settingsChanges.pomodoroIsActive);
		toggleTimer.setFont(new Font("Serif", Font.BOLD, 16));
		toggleTimer.setForeground(Color.white);
		toggleTimer.setContentAreaFilled(false);
		toggleTimer.setFocusPainted(false);
		toggleTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.pomodoroIsActive = toggleTimer.isSelected(); 
			}
		});
		
		pomToggle.add(Box.createRigidArea(new Dimension(15, 0)));
		pomToggle.add(toggleTimer);
		
		JPanel intervalSettings = new JPanel();
		GridLayout grid = new GridLayout(0,3);
		intervalSettings.setLayout(grid);
		intervalSettings.setBackground(aa_grey);
		intervalSettings.setMaximumSize(new Dimension(300, 100));
		
		JLabel workPeriod = new JLabel("<html><center>Enter Work" + "<br/>Period: </center></html>");
		workPeriod.setFont(new Font("Serif", Font.BOLD, 16));
		workPeriod.setForeground(Color.white);
		
		Integer workMinutes[] = {1,15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
		Integer breakMinutes[] = {1,10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60};
		
		JComboBox<Integer> workInterval = new JComboBox<>(workMinutes); 
		AccessibleContext accessCont = workInterval.getAccessibleContext();
		BasicComboPopup pop = (BasicComboPopup) accessCont.getAccessibleChild(0);
		JList workList = pop.getList();
		workList.setSelectionForeground(Color.WHITE);
		workList.setSelectionBackground(aa_purple);
		workInterval.setBackground(Color.black);
		workInterval.setForeground(Color.white);
		workInterval.setFont(new Font("Serif", Font.BOLD, 24));
		workInterval.setSelectedIndex((settingsChanges.workPeriod/5) - 3); 
		((JLabel)workInterval.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		workInterval.setMaximumSize(new Dimension(50,25));
		workInterval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.workPeriod = (int) workInterval.getSelectedItem();	
			} 
		});
		
		JLabel minutesWork = new JLabel(" minutes");
		minutesWork.setFont(new Font("Serif", Font.BOLD, 16));
		minutesWork.setForeground(Color.white);
		
		JLabel breakPeriod = new JLabel("<html><center>Enter Break" + "<br/>Period: </center></html>");
		breakPeriod.setFont(new Font("Serif", Font.BOLD, 16));
		breakPeriod.setForeground(Color.white);
		
		JComboBox<Integer> breakInterval = new JComboBox<>(breakMinutes); 
		AccessibleContext accessCont2 = breakInterval.getAccessibleContext();
		BasicComboPopup pop2 = (BasicComboPopup) accessCont2.getAccessibleChild(0);
		JList breakList = pop2.getList();
		breakList.setSelectionForeground(Color.WHITE);
		breakList.setSelectionBackground(aa_purple);
		breakInterval.setBackground(Color.black);
		breakInterval.setForeground(Color.white);
		breakInterval.setFont(new Font("Serif", Font.BOLD, 24));
		breakInterval.setSelectedIndex((settingsChanges.breakPeriod/5) - 2); 
		((JLabel)breakInterval.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		breakInterval.setMaximumSize(new Dimension(50,25));
		breakInterval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.breakPeriod = (int) breakInterval.getSelectedItem();	
			} 
		});
		
		JLabel minutesBreak = new JLabel(" minutes");
		minutesBreak.setFont(new Font("Serif", Font.BOLD, 16));
		minutesBreak.setForeground(Color.white);
		
		intervalSettings.add(workPeriod);
		intervalSettings.add(workInterval);
		intervalSettings.add(minutesWork);
		intervalSettings.add(breakPeriod);
		intervalSettings.add(breakInterval);
		intervalSettings.add(minutesBreak);
		
		JPanel countdownToggle = new JPanel();
		countdownToggle.setLayout(new FlowLayout(FlowLayout.LEFT));
		countdownToggle.setBackground(aa_grey);
		countdownToggle.setMaximumSize(new Dimension(400, 35));
		
		JCheckBox countDown = new JCheckBox("Show Time Remaining", settingsChanges.timeShowing);
		countDown.setFont(new Font("Serif", Font.BOLD, 16));
		countDown.setForeground(Color.white);
		countDown.setContentAreaFilled(false);
		countDown.setFocusPainted(false);
		countDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.timeShowing = countDown.isSelected(); 
			}
		});
		
		countdownToggle.add(Box.createRigidArea(new Dimension(15, 0)));
		countdownToggle.add(countDown);		
		
		JPanel openPomPanel = new JPanel();
		openPomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		openPomPanel.setBackground(aa_grey);
		openPomPanel.setMaximumSize(new Dimension(500, 35));
		
		openPomPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		openPomPanel.add(openPom);
		
		pomodoro_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		pomodoro_panel.add(pomodoro_header);
		pomodoro_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		pomodoro_panel.add(pomToggle);
		pomodoro_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		pomodoro_panel.add(intervalSettings);
		pomodoro_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		pomodoro_panel.add(countdownToggle);
		pomodoro_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		pomodoro_panel.add(openPomPanel);
	
		card_panel.add("pomodoro timer", pomodoro_panel);
	}
	
	/**
	 * RHS display for Thought Management
	 */
	private void createThoughtPanel(JPanel card_panel, Settings settingsChanges, Negative_Thought_Burner negative_thought_burner,Happy_Thought_Button happy_thought_button, Free_Thought_Space free_thought_space, DataBase db) {
		
		JPanel thought_panel = new JPanel();
		thought_panel.setLayout(new BoxLayout(thought_panel, BoxLayout.Y_AXIS));
		thought_panel.setBackground(aa_grey);
		
		JPanel ftsHeader = new JPanel();
		ftsHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
		ftsHeader.setBackground(aa_grey);
		ftsHeader.setMaximumSize(new Dimension(400, 35));
		
		JLabel activeHeader = new JLabel("Active:"); 
		activeHeader.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		activeHeader.setForeground(Color.white);
		
		ftsHeader.add(Box.createRigidArea(new Dimension(15, 0)));
		ftsHeader.add(activeHeader);
		
		JPanel checkBoxes = new JPanel();
		GridLayout grid = new GridLayout(0,1);
		checkBoxes.setLayout(grid);
		checkBoxes.setBackground(aa_grey);
		checkBoxes.setMaximumSize(new Dimension(350, 150));
		
		JCheckBox ftsBox = new JCheckBox("Free Thought Space", settingsChanges.ftsIsActive);
		ftsBox.setFont(new Font("Serif", Font.BOLD, 16));
		ftsBox.setForeground(Color.white);
		ftsBox.setContentAreaFilled(false);
		ftsBox.setFocusPainted(false);
		ftsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.ftsIsActive = ftsBox.isSelected(); 
			}
		});
		
		
		JCheckBox autoLinkBox = new JCheckBox("<html><center>Auto-Link Negative Thought Burner" + "<br/>to Happy Thought Button</center></html>", settingsChanges.isAutoLinked);
		autoLinkBox.setFont(new Font("Serif", Font.BOLD, 16));
		autoLinkBox.setForeground(Color.white);
		autoLinkBox.setContentAreaFilled(false);
		autoLinkBox.setFocusPainted(false);
		autoLinkBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.isAutoLinked = autoLinkBox.isSelected(); 
			}
		});
		
		JCheckBox ntbBox = new JCheckBox("Negative Thought Burner", settingsChanges.ntbIsActive);
		ntbBox.setFont(new Font("Serif", Font.BOLD, 16));
		ntbBox.setForeground(Color.white);
		ntbBox.setContentAreaFilled(false);
		ntbBox.setFocusPainted(false);
		ntbBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.ntbIsActive = ntbBox.isSelected(); 
				
				if(settingsChanges.ntbIsActive == false) {
					autoLinkBox.setEnabled(false); 
				}else if(settingsChanges.htbIsActive && settingsChanges.ntbIsActive) {
					autoLinkBox.setEnabled(true);
				}
			}
		});
		
		JCheckBox htbBox = new JCheckBox("Happy Thought Button", settingsChanges.htbIsActive);
		htbBox.setFont(new Font("Serif", Font.BOLD, 16));
		htbBox.setForeground(Color.white);
		htbBox.setContentAreaFilled(false);
		htbBox.setFocusPainted(false);
		htbBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.htbIsActive = htbBox.isSelected(); 
				
				if(settingsChanges.htbIsActive == false) {
					autoLinkBox.setEnabled(false); 
				}else if(settingsChanges.htbIsActive && settingsChanges.ntbIsActive) {
					autoLinkBox.setEnabled(true);
				}
			}
		});
		
		checkBoxes.add(ftsBox);
		checkBoxes.add(ntbBox);
		checkBoxes.add(autoLinkBox);
		checkBoxes.add(htbBox);
		
		JPanel htbUploadPanel = new JPanel();
		htbUploadPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		htbUploadPanel.setBackground(aa_grey);
		htbUploadPanel.setMaximumSize(new Dimension(500, 35));
		
		JLabel htbUpload = new JLabel("Upload Happy Thoughts: ");
		htbUpload.setFont(new Font("Serif", Font.BOLD, 16));
		htbUpload.setForeground(Color.white);
		
		JButton upload = new JButton("Upload");
		upload.setMaximumSize(new Dimension(70,20));
		upload.setBackground(Color.GRAY);
		upload.setForeground(Color.WHITE);
		upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * NEEDS TO BE UPDATED when HTB is implemented
				 */
				JFileChooser happyThoughtsDirectory = new JFileChooser();
				happyThoughtsDirectory.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//what file types will we accept? add them here
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("JPEG and PNG Images", "png", "jpeg");
				happyThoughtsDirectory.setFileFilter(fileFilter);
				int returnVal = happyThoughtsDirectory.showDialog(null, "Select Your New Avatar");
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File happyThoughtFile = happyThoughtsDirectory.getSelectedFile();
					//settingsChanges.happyThoughtsDirectory = happyThoughtFile.getAbsolutePath();
					//ask how happy thought media will be stored and implement that storage here 
				}
			}
		});
		
		htbUploadPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		htbUploadPanel.add(htbUpload);
		htbUploadPanel.add(upload);
		
		JPanel ftsButtonPanel = new JPanel();
		ftsButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ftsButtonPanel.setBackground(aa_grey);
		ftsButtonPanel.setMaximumSize(new Dimension(350, 35));
		
		//JButton openFTS = new JButton("     Open Free Thought Space     ");
		openFTS.setBackground(Color.GRAY);
		openFTS.setForeground(Color.WHITE);
		openFTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open Free Thought Space
				free_thought_space.runFts(free_thought_space, db, settingsChanges.userID);
			}
		});
		
		ftsButtonPanel.add(openFTS);
		
		JPanel htbButtonPanel = new JPanel();
		htbButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		htbButtonPanel.setBackground(aa_grey);
		htbButtonPanel.setMaximumSize(new Dimension(350, 35));
		
		//JButton openHTB = new JButton("   Open Happy Thought Button   ");
		openHTB.setBackground(Color.GRAY);
		openHTB.setForeground(Color.WHITE);
		openHTB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open Happy Thought Button 
				happy_thought_button.open_htb();
			}
		});
		
		htbButtonPanel.add(openHTB);
		
		JPanel ntbButtonPanel = new JPanel();
		ntbButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ntbButtonPanel.setBackground(aa_grey);
		ntbButtonPanel.setMaximumSize(new Dimension(350, 35));
		
		//JButton openNTB = new JButton("Open Negative Thought Burner");
		openNTB.setBackground(Color.GRAY);
		openNTB.setForeground(Color.WHITE);
		openNTB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open Negative Thought Burner
			}
		});
		
		ntbButtonPanel.add(openNTB);

		thought_panel.add(Box.createRigidArea(new Dimension(25, 10)));
		thought_panel.add(ftsHeader);
		thought_panel.add(checkBoxes);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		thought_panel.add(htbUploadPanel);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		thought_panel.add(ftsButtonPanel);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		thought_panel.add(htbButtonPanel);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		thought_panel.add(ntbButtonPanel);
		
	
		card_panel.add("thought management", thought_panel);
	}
	
	/**
	 * creates/display Settings GUI
	 * @param db
	 */
	public void open_settings(int UserID, DataBase db,Nav_Bar navbar,Settings settings, Observer observer, Priority_Manager priority_manager,Pomodoro_Timer pomodoro_timer,Negative_Thought_Burner negative_thought_burner,Happy_Thought_Button happy_thought_button, Free_Thought_Space free_thought_space) {
		EventQueue.invokeLater(new Runnable() {
			@Override 
			public void run() {
				
				/*
				 * instantiate a new settings object using the copy constructor 
				 * so that it is a copy of the passed settings object. 
				 */
				Settings settingsChanges = new Settings(userID); 
				
				JFrame settings_frame = new JFrame("Attention Assistant Settings");
				
				settings_frame.setUndecorated(true);
				settings_frame.setPreferredSize(new Dimension(width, height)); 
				
				JPanel masterPanel = new JPanel(new BorderLayout());
				masterPanel.setBackground(Color.black);
				
				
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
						settings_frame.setLocation(settings_frame.getX() + e.getX() - mouseX, settings_frame.getY() + e.getY() - mouseY);
					}
				});
				
				title_panel.addMouseListener(new MouseAdapter(){
					@Override 
					public void mousePressed(MouseEvent e) {
						mouseX = e.getX();
						mouseY = e.getY();
					}
				});

				JLabel title = new JLabel("Settings");
				title.setForeground(Color.white);
				title.setFont(new Font("Serif", Font.BOLD, 20));
				
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
		        		settings_frame.dispose();
		        	
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
				
				//card layout and panel for RHS of settings that displays sub-menu with each button option 
				JPanel card_panel = new JPanel();
				//creates layout for sub-menus and panels for each sub-menu
				CardLayout card_layout = new CardLayout();
				card_panel.setLayout(card_layout);
	
				/*
				 * pass the new settingsChanges object to be modified by all action listeners
				 */
				createGeneralPanel(card_panel, settingsChanges);
				createNotificationsPanel(card_panel, settingsChanges);
				createPriorityManagerPanel(card_panel, settingsChanges, priority_manager, db, observer);
				createPomodoroTimerPanel(card_panel, settingsChanges, pomodoro_timer, priority_manager );
				createThoughtPanel(card_panel, settingsChanges, negative_thought_burner, happy_thought_button, free_thought_space, db);								
				
				/*
				 * buttons for bottom border
				 */
				JButton general = new JButton("<html><center>General" + "<br/>Settings</center></html>");
				JButton notifications = new JButton("<html><center>Notification" + "<br/>System</center></html>");
				JButton pm = new JButton("<html><center>Priority" + "<br/>Manager</center></html>");
				JButton pomTimer = new JButton("<html><center>Pomodoro" + "<br/>Timer</center></html>");
				JButton thoughts = new JButton("<html><center>Thought" + "<br/>Management</center></html>");
				
				/*
				 * specifications for general button
				 */
				general.setForeground(Color.white);
				general.setFont(new Font("Serif", Font.BOLD, 16));
				general.setContentAreaFilled(true);
				general.setBorderPainted(true);
				general.setBorder(new LineBorder(aa_purple));
				general.setFocusPainted(false);
				general.setBackground(aa_grey);
				general.setMaximumSize(new Dimension(175, 47));
				general.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * open general sub-menu
						 * adjusts border of all buttons to put purple border on selected button
						 */
						notifications.setBorderPainted(false);
						pm.setBorderPainted(false);
						pomTimer.setBorderPainted(false);
						thoughts.setBorderPainted(false);
						general.setBorderPainted(true);
						general.setBorder(new LineBorder(aa_purple));
						card_layout.show(card_panel, "general");						
					}
				});
				
				/*
				 * specifications for notifications button
				 */
				notifications.setForeground(Color.white);
				notifications.setFont(new Font("Serif", Font.BOLD, 16));
				notifications.setContentAreaFilled(true);
				notifications.setBorderPainted(false);
				notifications.setFocusPainted(false);
				notifications.setBackground(aa_grey);
				notifications.setMaximumSize(new Dimension(175, 47));
				notifications.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * open notification systems sub-menu
						 * adjusts border of all buttons to put purple border on selected button
						 */
						general.setBorderPainted(false);
						pm.setBorderPainted(false);
						pomTimer.setBorderPainted(false);
						thoughts.setBorderPainted(false);
						notifications.setBorderPainted(true);
						notifications.setBorder(new LineBorder(aa_purple));
						card_layout.show(card_panel, "notifications");
					}
				});
				
				/*
				 * specifications for priority manager button 
				 */
				pm.setForeground(Color.white);
				pm.setFont(new Font("Serif", Font.BOLD, 16));
				pm.setContentAreaFilled(true);
				pm.setBorderPainted(false);
				pm.setFocusPainted(false);
				pm.setBackground(aa_grey);
				pm.setMaximumSize(new Dimension(170, 47));
				pm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * open priority manager sub-menu
						 * adjusts border of all buttons to put purple border on selected button
						 */
						general.setBorderPainted(false);
						notifications.setBorderPainted(false);
						pomTimer.setBorderPainted(false);
						thoughts.setBorderPainted(false);
						pm.setBorderPainted(true);
						pm.setBorder(new LineBorder(aa_purple));
						card_layout.show(card_panel, "priority manager");
					}
				});
				
				/*
				 * specifications for pomodoro timer button
				 */
				pomTimer.setForeground(Color.white);
				pomTimer.setFont(new Font("Serif", Font.BOLD, 16));
				pomTimer.setContentAreaFilled(true);
				pomTimer.setBorderPainted(false);
				pomTimer.setFocusPainted(false);
				pomTimer.setBackground(aa_grey);
				pomTimer.setMaximumSize(new Dimension(170, 47));
				pomTimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * open pomodoro timer sub-menu
						 * adjusts border of all buttons to put purple border on selected button
						 */
						general.setBorderPainted(false);
						notifications.setBorderPainted(false);
						pm.setBorderPainted(false);
						thoughts.setBorderPainted(false);
						pomTimer.setBorderPainted(true);
						pomTimer.setBorder(new LineBorder(aa_purple));
						card_layout.show(card_panel, "pomodoro timer");
					}
				});
				
				/*
				 * specifications for thought management button 
				 */
				thoughts.setForeground(Color.white);
				thoughts.setFont(new Font("Serif", Font.BOLD, 16));
				thoughts.setContentAreaFilled(true);
				thoughts.setBorderPainted(false);
				thoughts.setFocusPainted(false);
				thoughts.setBackground(aa_grey);
				thoughts.setMaximumSize(new Dimension(170, 47));
				thoughts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						 * open free through space sub-menu
						 * adjusts border of all buttons to put purple border on selected button
						 */
						general.setBorderPainted(false);
						notifications.setBorderPainted(false);
						pm.setBorderPainted(false);
						pomTimer.setBorderPainted(false);
						thoughts.setBorderPainted(true);
						thoughts.setBorder(new LineBorder(aa_purple));
						
						card_layout.show(card_panel, "thought management");
					}
				});
				
				JButton progressReport = new JButton("<html><center>Download" + "<br/>Progress Report</center></html>");
				progressReport.setForeground(Color.white);
				progressReport.setFont(new Font("Serif", Font.BOLD, 16));
				progressReport.setContentAreaFilled(true);
				progressReport.setBorderPainted(false);
				progressReport.setFocusPainted(false);
				progressReport.setBackground(aa_purple);
				progressReport.setMaximumSize(new Dimension(170, 47));
				progressReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//download progress report
					}
				});
				
				JButton guideButton = new JButton(guideIcon);
				guideButton.setText("<html><center>Guide" + "<br/>       </center></html>");
				guideButton.setForeground(Color.white);
				guideButton.setFont(new Font("Serif", Font.BOLD, 16));
				guideButton.setContentAreaFilled(true);
				guideButton.setBorderPainted(false);
				guideButton.setFocusPainted(false);
				guideButton.setBackground(aa_purple);
				guideButton.setMaximumSize(new Dimension(170, 30));
				guideButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open guide
					}
				});
				
				Image ex_img = exit.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				Icon exitIcon = new ImageIcon(ex_img);
				
				JButton exit_AA = new JButton(exitIcon);
				exit_AA.setText("EXIT");
				exit_AA.setForeground(Color.white);
				exit_AA.setFont(new Font("Serif", Font.BOLD, 16));
				exit_AA.setContentAreaFilled(true);
				exit_AA.setBorderPainted(false);
				exit_AA.setFocusPainted(false);
				exit_AA.setBackground(aa_purple);
				exit_AA.setMaximumSize(new Dimension(170, 30));
				exit_AA.addActionListener(new ActionListener() {
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
					}
				});
				
				/*
				 * buttons for bottom border
				 */
				JButton apply = new JButton("apply");
				apply.setForeground(Color.white);
				apply.setFont(new Font("Serif", Font.BOLD, 16));
				apply.setContentAreaFilled(true);
				apply.setBorderPainted(false);
				apply.setFocusPainted(false);
				apply.setBackground(aa_grey);
				apply.setMaximumSize(new Dimension(75,25));
				/*
				 * When apply is selected all changes made to settingsChanges are applied to settings
				 */
				apply.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						settings.settingsID = settingsChanges.settingsID;
						settings.userID = settingsChanges.userID; 
						settings.iconCircles = settingsChanges.iconCircles;
						settings.icons = settingsChanges.icons;
						settings.opacityCircles = settingsChanges.opacityCircles; 
						settings.opacityIcons = settingsChanges.opacityIcons; 
						settings.isCollapsed = settingsChanges.isCollapsed; 
						settings.xCoord = settingsChanges.xCoord;
						settings.yCoord = settingsChanges.yCoord; 
						settings.isVertical = settingsChanges.isVertical;
						settings.iconSize = settingsChanges.iconSize;
						settings.timerIsVisible = settingsChanges.timerIsVisible; 
						settings.pmIsVisible = settingsChanges.pmIsVisible; 
						settings.ftsIsVisible = settingsChanges.ftsIsVisible; 
						settings.htbIsVisible = settingsChanges.htbIsVisible;
						settings.ntbIsVisible = settingsChanges.ntbIsVisible; 
						settings.progReportIsVisible = settingsChanges.progReportIsVisible; 
						settings.avatarIsActive = settingsChanges.avatarIsActive; 
						settings.textIsActive = settingsChanges.textIsActive; 
						settings.audioIsActive = settingsChanges.audioIsActive;
						settings.avatarFilePath = settingsChanges.avatarFilePath; 
						settings.audioFilePath = settingsChanges.audioFilePath;
						settings.alwaysOnScreen = settingsChanges.alwaysOnScreen; 
						settings.avatarSize = settingsChanges.avatarSize; 
						settings.pomodoroIsActive = settingsChanges.pomodoroIsActive; 
						settings.workPeriod = settingsChanges.workPeriod; 
						settings.breakPeriod = settingsChanges.breakPeriod; 
						settings.timeShowing = settingsChanges.timeShowing; 
						settings.ftsIsActive = settingsChanges.ftsIsActive; 
						settings.ntbIsActive = settingsChanges.ntbIsActive; 
						settings.isAutoLinked = settingsChanges.isAutoLinked; 
						settings.htbIsActive = settingsChanges.htbIsActive;
						
						if(settings.ftsIsActive == false) {
							ftsVisibleBox.setEnabled(false);
							openFTS.setEnabled(false);
						}else if(settings.ftsIsActive == true) {
							ftsVisibleBox.setEnabled(true);
							openFTS.setEnabled(true); 
						}
						
						if(settings.htbIsActive == false) {
							htbVisibleBox.setEnabled(false);
							openHTB.setEnabled(false);
						}else if(settings.htbIsActive == true) {
							htbVisibleBox.setEnabled(true);
							openHTB.setEnabled(true); 
						}
						
						if(settings.ntbIsActive == false) {
							ntbVisibleBox.setEnabled(false);
							openNTB.setEnabled(false);
						}else if(settings.ntbIsActive == true) {
							ntbVisibleBox.setEnabled(true);
							openNTB.setEnabled(true); 
						}
						
						if(settings.pomodoroIsActive == false) {
							timerVisibleBox.setEnabled(false);
							openPom.setEnabled(false); 
						}else if(settings.pomodoroIsActive == true) {
							timerVisibleBox.setEnabled(true);
							openPom.setEnabled(true);
						}
						
						navbar.refresh(settings);
						pomodoro_timer.refresh(settings);
						db.UpdateSettings(settings);
					}
				});
				
				JButton cancel = new JButton("cancel");
				cancel.setForeground(Color.white);
				cancel.setFont(new Font("Serif", Font.BOLD, 16));
				cancel.setContentAreaFilled(true);
				cancel.setBorderPainted(false);
				cancel.setFocusPainted(false);
				cancel.setBackground(aa_grey);
				cancel.setMaximumSize(new Dimension(80,25));
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//close window without saving info
		        		settings_frame.dispose();
					}
				});
				
				
				/*
				 * creates layout for left side panel and buttons for side-bar sub-menus 
				 */ 
				JPanel sideMenu = new JPanel();
				sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
				sideMenu.setPreferredSize(new Dimension(160, 675));
				sideMenu.add(Box.createRigidArea(new Dimension(0,15)));
				sideMenu.add(general);
				sideMenu.add(Box.createRigidArea(new Dimension(0,15)));
				sideMenu.add(notifications);
				sideMenu.add(Box.createRigidArea(new Dimension(0,15)));
				sideMenu.add(pm);
				sideMenu.add(Box.createRigidArea(new Dimension(0,15)));
				sideMenu.add(pomTimer);
				sideMenu.add(Box.createRigidArea(new Dimension(0,15)));
				sideMenu.add(thoughts);
				sideMenu.add(Box.createRigidArea(new Dimension(0,135)));
				sideMenu.add(progressReport);
				sideMenu.add(Box.createRigidArea(new Dimension(0,15)));
				sideMenu.add(guideButton);
				sideMenu.add(Box.createRigidArea(new Dimension(0,20)));
				sideMenu.add(exit_AA);
				sideMenu.setBackground(Color.black);
				
				/*
				 * creates split center panel
				 */
				JPanel center_panel = new JPanel(new BorderLayout());
				center_panel.setBackground(Color.black);
				center_panel.add(card_panel, BorderLayout.CENTER);
				center_panel.add(sideMenu, BorderLayout.WEST);
			
				/*
				 * creates layout for panel and buttons along bottom of frame
				 */
				JPanel bottomButtons = new JPanel();
				bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));
				bottomButtons.add(Box.createRigidArea(new Dimension(345, 0)));
				bottomButtons.add(apply);
				bottomButtons.add(Box.createRigidArea(new Dimension(30, 0)));
				bottomButtons.add(cancel);
				bottomButtons.setBackground(Color.black);
				
				/*
				 * populates master panel 
				 */
				masterPanel.add(title_panel, BorderLayout.PAGE_START); 
				masterPanel.add(center_panel, BorderLayout.CENTER);
				masterPanel.add(bottomButtons, BorderLayout.PAGE_END);
			
				/*
				 * adds master panel to frame
				 */
				settings_frame.getContentPane().add(masterPanel); 
				settings_frame.getContentPane().setBackground(Color.black);
				settings_frame.pack();
				settings_frame.setAlwaysOnTop(false);
				settings_frame.setVisible(true);
				settings_frame.setResizable(true);
				settings_frame.setLocationRelativeTo(null);
				
			}
		});
	}
}
