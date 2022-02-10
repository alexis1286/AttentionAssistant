package AttentionAssistant;

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
	final static boolean shouldFill = true; 
	final static boolean shouldWeightX = true; 
	final static boolean RIGHT_TO_LEFT = false; 
		
	//variables for the Settings object
	
	/*
	 * primary key for database
	 */
	private int settingsID;
	
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
	public Settings() {
		this.settingsID = 1;
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
		this.avatarFilePath = "images/avatar_dino.png"; 
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
	 * @param db
	 *
	 * Placeholder constructor to get an idea of how the
	 * settings could be loaded from the database
	 * 
	 *  assuming DataBase class will have a method called something along
	 *  the lines of SelectUserSettings() that returns an arraylist of all the settings
	 *  in the same order as the constructor -- just an idea to coordinate with
	 *  database team once the table is set up 
	 *
	public Settings(DataBase db) {
		this.settingsID = db.SelectUserSettings().get(0);
		this.iconCircles = db.SelectUserSettings().get(1); 
		this.icons = db.SelectUserSettings().get(2);
		this.opacityCircles = db.SelectUserSettings().get(3); 
		this.opacityIcons = db.SelectUserSettings().get(4); 
		this.isCollapsed = db.SelectUserSettings().get(5); 
		this.xCoord = db.SelectUserSettings().get(6);
		this.yCoord = db.SelectUserSettings().get(7); 
		this.isVertical = db.SelectUserSettings().get(8);
		this.iconSize = db.SelectUserSettings().get(9);
		this.timerIsVisible = db.SelectUserSettings().get(10); 
		this.pmIsVisible = db.SelectUserSettings().get(11); 
		this.ftsIsVisible = db.SelectUserSettings().get(12); 
		this.htbIsVisible = db.SelectUserSettings().get(13);
		this.ntbIsVisible = db.SelectUserSettings().get(14); 
		this.progReportIsVisible = db.SelectUserSettings().get(15); 
		this.avatarIsActive = db.SelectUserSettings().get(16); 
		this.textIsActive = db.SelectUserSettings().get(17); 
		this.audioIsActive = db.SelectUserSettings().get(18);
		this.avatarFilePath = db.SelectUserSettings().get(19); 
		this.audioFilePath = db.SelectUserSettings().get(20);
		this.alwaysOnScreen = db.SelectUserSettings().get(21); 
		this.avatarSize = db.SelectUserSettings().get(22); 
		this.pomodoroIsActive = db.SelectUserSettings().get(23); 
		this.workPeriod = db.SelectUserSettings().get(24); 
		this.breakPeriod = db.SelectUserSettings().get(25); 
		this.timeShowing = db.SelectUserSettings().get(26); 
		this.ftsIsActive = db.SelectUserSettings().get(27); 
		this.ntbIsActive = db.SelectUserSettings().get(28); 
		this.isAutoLinked = db.SelectUserSettings().get(29); 
		this.htbIsActive = db.SelectUserSettings().get(30);
	}
	*/
	
	 /**
	 * Creates a Settings object loaded with all data types specified
	 * 
	 * Written as a placeholder for testing, once database is set up this
	 * constructor should be deleted
	 * 
	 * @param int, Color, Color, int, int, boolean, int, int, boolean, int, boolean, boolean,
	 * 		  boolean, boolean, boolean, boolean, boolean, boolean, boolean, String, String, 
	 * 		  boolean, int, boolean, int, int, boolean, boolean, boolean, boolean, boolean
	 */
	public Settings(int settingsID, Color iconCircles, Color icons,	int opacityCircles, int opacityIcons, boolean isCollapsed, 
					int xCoord, int yCoord, boolean isVertical, int iconSize, boolean timerIsVisible, boolean pmIsVisible, 
					boolean ftsIsVisible, boolean htbIsVisible, boolean ntbIsVisible, boolean progReportIsVisible, 
					boolean avatarIsActive, boolean textIsActive, boolean audioIsActive, String avatarFilePath, String audioFilePath, 
					boolean alwaysOnScreen, int avatarSize, boolean pomodoroIsActive, int workPeriod, int breakPeriod, boolean timeShowing, 
					boolean ftsIsActive, boolean ntbIsActive, boolean isAutoLinked, boolean htbIsActive) {
		
		this.settingsID = settingsID;
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
		
		JSlider backgroundOpacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, settingsChanges.opacityCircles);
		backgroundOpacitySlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		backgroundOpacitySlider.setBackground(aa_grey);
		backgroundOpacitySlider.setForeground(Color.white);
		backgroundOpacitySlider.setMinorTickSpacing(25);
		backgroundOpacitySlider.setMajorTickSpacing(25);
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
		
		JSlider iconOpacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, settingsChanges.opacityIcons);
		iconOpacitySlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		iconOpacitySlider.setBackground(aa_grey);
		iconOpacitySlider.setForeground(Color.white);
		iconOpacitySlider.setMinorTickSpacing(25);
		iconOpacitySlider.setMajorTickSpacing(25);
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
		
		JSlider iconSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, settingsChanges.iconSize);
		iconSizeSlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		iconSizeSlider.setBackground(aa_grey);
		iconSizeSlider.setForeground(Color.white);
		iconSizeSlider.setMinorTickSpacing(25);
		iconSizeSlider.setMajorTickSpacing(25);
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
		
		JCheckBox timerBox = new JCheckBox("Pomodoro Timer", settingsChanges.timerIsVisible);
		timerBox.setFont(new Font("Serif", Font.BOLD, 16));
		timerBox.setForeground(Color.white);
		timerBox.setContentAreaFilled(false);
		timerBox.setFocusPainted(false);
		timerBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.timerIsVisible = timerBox.isSelected(); 
			}
		});
		
		JCheckBox pmBox = new JCheckBox("Priority Manager", settingsChanges.pmIsVisible);
		pmBox.setFont(new Font("Serif", Font.BOLD, 16));
		pmBox.setForeground(Color.white);
		pmBox.setContentAreaFilled(false);
		pmBox.setFocusPainted(false);
		pmBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.pmIsVisible = pmBox.isSelected(); 
			}
		});
		
		JCheckBox ftsBox = new JCheckBox("<html><center>Free Thought" + "<br/>Space</center></html>", settingsChanges.ftsIsVisible);
		ftsBox.setFont(new Font("Serif", Font.BOLD, 16));
		ftsBox.setForeground(Color.white);
		ftsBox.setContentAreaFilled(false);
		ftsBox.setFocusPainted(false);
		ftsBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.ftsIsVisible = ftsBox.isSelected(); 
			}
		});
		
		JCheckBox ntbBox = new JCheckBox("<html><center>Negative Thought" + "<br/>Burner</center></html>", settingsChanges.ntbIsVisible);
		ntbBox.setFont(new Font("Serif", Font.BOLD, 16));
		ntbBox.setForeground(Color.white);
		ntbBox.setContentAreaFilled(false);
		ntbBox.setFocusPainted(false);
		ntbBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.ntbIsVisible = ntbBox.isSelected(); 
			}
		});
		
		JCheckBox htbBox = new JCheckBox("<html><center>Happy Thought" + "<br/>Button</center></html>", settingsChanges.htbIsVisible);
		htbBox.setFont(new Font("Serif", Font.BOLD, 16));
		htbBox.setForeground(Color.white);
		htbBox.setContentAreaFilled(false);
		htbBox.setFocusPainted(false);
		htbBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.htbIsVisible = htbBox.isSelected(); 
			}
		});
		
		JCheckBox prBox = new JCheckBox("Progress Report", settingsChanges.progReportIsVisible);
		prBox.setFont(new Font("Serif", Font.BOLD, 16));
		prBox.setForeground(Color.white);
		prBox.setContentAreaFilled(false);
		prBox.setFocusPainted(false);
		prBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsChanges.progReportIsVisible = prBox.isSelected(); 
			}
		});
		
		navBarBoxes.add(timerBox);
		navBarBoxes.add(pmBox);
		navBarBoxes.add(ftsBox);
		navBarBoxes.add(ntbBox);
		navBarBoxes.add(htbBox);
		navBarBoxes.add(prBox);
		
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
		JLabel displayAvatar = new JLabel(defaultAvatar);
		
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
				/*
				 *  right now this just opens to where we have all the images stored for the project
				 *  will need to update to the pre-loaded avatar directory we give users
				 */
				JFileChooser avatarDirectory = new JFileChooser(settingsChanges.avatarFilePath);
				avatarDirectory.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Only PNG Images", "png");
				avatarDirectory.setFileFilter(fileFilter);
				int returnVal = avatarDirectory.showDialog(null, "Select Your New Avatar");
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File avatarFile = avatarDirectory.getSelectedFile();
					settingsChanges.avatarFilePath = avatarFile.getAbsolutePath();
					
					/*
					 * need to refresh the image on the panel to their new avatar selection. 
					 * the below calls are not working. 
					 */
					//notifications_panel.revalidate();
					//notifications_panel.repaint();
				}
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
	private void createPomodoroTimerPanel(JPanel card_panel, Settings settingsChanges, Pomodoro_Timer pomodoro_timer) {
		
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
		
		JButton openPom = new JButton("Open Pomodoro Timer");
		openPom.setMaximumSize(new Dimension(200, 30));
		openPom.setBackground(Color.GRAY);
		openPom.setForeground(Color.WHITE);
		openPom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//implement chooseAvatar 
				pomodoro_timer.run_pomo(settingsChanges);
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
				
				if(settingsChanges.pomodoroIsActive == false) {
					settingsChanges.timerIsVisible = false; 
				}
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
		
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		attribs.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
		StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontSize(attribs, 30);
		
		JTextPane workInterval = new JTextPane();
		workInterval.setFont(new Font("Serif", Font.BOLD | Font.PLAIN, 16));
		workInterval.setBorder(new LineBorder(Color.black,5,false));
		workInterval.setParagraphAttributes(attribs, true);
		workInterval.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				settingsChanges.workPeriod = Integer.parseInt(workInterval.getText());
			}
		});
		
		JLabel minutesWork = new JLabel("minutes");
		minutesWork.setFont(new Font("Serif", Font.BOLD, 16));
		minutesWork.setForeground(Color.white);
		
		JLabel breakPeriod = new JLabel("<html><center>Enter Break" + "<br/>Period: </center></html>");
		breakPeriod.setFont(new Font("Serif", Font.BOLD, 16));
		breakPeriod.setForeground(Color.white);
		
		JTextPane breakInterval = new JTextPane();
		breakInterval.setFont(new Font("Serif", Font.BOLD | Font.PLAIN, 16));
		breakInterval.setBorder(new LineBorder(Color.black,5,false));
		breakInterval.setParagraphAttributes(attribs, true);
		breakInterval.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {}
			public void focusLost(FocusEvent e) {
				settingsChanges.breakPeriod = Integer.parseInt(breakInterval.getText());
			}
		});
		
		JLabel minutesBreak = new JLabel("minutes");
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
	private void createThoughtPanel(JPanel card_panel, Settings settingsChanges, Negative_Thought_Burner negative_thought_burner,Happy_Thought_Button happy_thought_button, Free_Thought_Space free_thought_space) {
		
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
				
				if(settingsChanges.ftsIsActive == false) {
					settingsChanges.ftsIsVisible = false; 
				}
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
					settingsChanges.ntbIsVisible = false; 
					settingsChanges.isAutoLinked = false;
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
					settingsChanges.htbIsVisible = false; 
					settingsChanges.isAutoLinked = false;
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
		
		JButton openFTS = new JButton("     Open Free Thought Space     ");
		openFTS.setBackground(Color.GRAY);
		openFTS.setForeground(Color.WHITE);
		openFTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open Free Thought Space
				free_thought_space.runFts(free_thought_space);
			}
		});
		
		ftsButtonPanel.add(openFTS);
		
		JPanel htbButtonPanel = new JPanel();
		htbButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		htbButtonPanel.setBackground(aa_grey);
		htbButtonPanel.setMaximumSize(new Dimension(350, 35));
		
		JButton openHTB = new JButton("   Open Happy Thought Button   ");
		openHTB.setBackground(Color.GRAY);
		openHTB.setForeground(Color.WHITE);
		openHTB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open Happy Thought Button 
			}
		});
		
		htbButtonPanel.add(openHTB);
		
		JPanel ntbButtonPanel = new JPanel();
		ntbButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ntbButtonPanel.setBackground(aa_grey);
		ntbButtonPanel.setMaximumSize(new Dimension(350, 35));
		
		JButton openNTB = new JButton("Open Negative Thought Burner");
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
	public void open_settings(DataBase db,Nav_Bar navbar,Settings settings, Observer observer, Priority_Manager priority_manager,Pomodoro_Timer pomodoro_timer,Negative_Thought_Burner negative_thought_burner,Happy_Thought_Button happy_thought_button, Free_Thought_Space free_thought_space) {
		EventQueue.invokeLater(new Runnable() {
			@Override 
			public void run() {
				
				/*
				 * instantiate a new settings object using the copy constructor 
				 * so that it is a copy of the passed settings object. 
				 */
				Settings settingsChanges = new Settings(settings); 
				
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
				createPomodoroTimerPanel(card_panel, settingsChanges, pomodoro_timer);
				createThoughtPanel(card_panel, settingsChanges, negative_thought_burner, happy_thought_button, free_thought_space);								
				
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
				apply.setMaximumSize(new Dimension(70,25));
				/*
				 * When apply is selected all changes made to settingsChanges are applied to settings
				 */
				apply.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						settings.settingsID = settingsChanges.settingsID;
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
						
						navbar.refresh(settings);
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
