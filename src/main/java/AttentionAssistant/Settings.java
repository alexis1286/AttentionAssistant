package AttentionAssistant;

import java.awt.*;
//import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;


//borders and padding and margins

public class Settings {
	
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	
	JFrame settings_frame = new JFrame("Attention Assistant Settings");
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	
	//creates card layout and panel for RHS settings that display with each button option 
	CardLayout card_layout = new CardLayout();
	JPanel card_panel = new JPanel();
	
	int height = 700; 
	int width = 550; 
	final static boolean shouldFill = true; 
	final static boolean shouldWeightX = true; 
	final static boolean RIGHT_TO_LEFT = false; 
	
	/*
	 * RHS display for General Settings 
	 */
	private void createGeneralPanel() {
		
		JPanel general_panel = new JPanel();
		general_panel.setLayout(new BoxLayout(general_panel, BoxLayout.Y_AXIS));
		general_panel.setBackground(aa_grey);
		
		JPanel header_panel = new JPanel();
		header_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		header_panel.setBackground(aa_grey);
		header_panel.setMaximumSize(new Dimension(400, 25));
		
		JLabel overlayOptions = new JLabel("Overlay options:");
		overlayOptions.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		overlayOptions.setForeground(Color.white);
		
		header_panel.add(Box.createRigidArea(new Dimension(15, 0)));
		header_panel.add(overlayOptions);
		
		JPanel colorOptions = new JPanel();
		colorOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		colorOptions.setBackground(aa_grey);
		colorOptions.setMaximumSize(new Dimension(400, 35));
		
		JLabel color = new JLabel("Color:");
		color.setFont(new Font("Serif", Font.BOLD, 16));
		color.setForeground(Color.white);
		
		//can use JColorChooser API to allow color selection to change color of Frame
		//right now it's a dead button
		JButton colorChooser = new JButton("choose color");
		colorChooser.setMaximumSize(new Dimension(108,20));
		colorChooser.setBackground(Color.GRAY);
		colorChooser.setForeground(Color.WHITE);
		colorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open color chooser dialog
				//needs to be able to change icons on navbar, right? 
			}
		});
		
		colorOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		colorOptions.add(color);
		colorOptions.add(colorChooser);
		
		JPanel opacityOptions = new JPanel();
		opacityOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		opacityOptions.setBackground(aa_grey);
		opacityOptions.setMaximumSize(new Dimension(400, 35));
		
		/*
		 * Adjust opacity if we are going to do that for prototype 
		 * currently just a slider as a placeholder 
		 * can't get it to the right position in line with the rest
		 */
		JLabel opacity = new JLabel("Opacity:");
		opacity.setFont(new Font("Serif", Font.BOLD, 16));
		opacity.setForeground(Color.white);
		
		JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		opacitySlider.setAlignmentX(JSlider.CENTER_ALIGNMENT);
		opacitySlider.setBackground(Color.black);
		opacitySlider.setForeground(Color.white);
		opacitySlider.setMaximumSize(new Dimension(200, 25));
		
		opacityOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		opacityOptions.add(opacity);
		opacityOptions.add(opacitySlider);
		
		JPanel toolbar_panel = new JPanel();
		toolbar_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbar_panel.setBackground(aa_grey);
		toolbar_panel.setMaximumSize(new Dimension(400, 25));
		
		JPanel toolbarOptions = new JPanel();
		toolbarOptions.setBackground(aa_grey);
		toolbarOptions.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolbarOptions.setMaximumSize(new Dimension(400, 25));
		
		/*
		 * Are we offering collapsed toolbar in the prototype?
		 * check boxes are placeholders for now
		 */
		JLabel toolbarFormat = new JLabel("Toolbar format:");
		toolbarFormat.setFont(new Font("Serif", Font.BOLD, 16));
		toolbarFormat.setForeground(Color.white); 
		
		toolbar_panel.add(Box.createRigidArea(new Dimension(15, 0)));
		toolbar_panel.add(toolbarFormat);
		
		JCheckBox collapsed = new JCheckBox("Collapsed", false);
		collapsed.setFont(new Font("Serif", Font.BOLD, 14));
		collapsed.setForeground(Color.white);
		collapsed.setContentAreaFilled(false);
		collapsed.setFocusPainted(false);
		
		JCheckBox expanded = new JCheckBox("Expanded", true);
		expanded.setFont(new Font("Serif", Font.BOLD, 14));
		expanded.setForeground(Color.white);
		expanded.setContentAreaFilled(false);
		expanded.setFocusPainted(false);
		
		JPanel moveAndResizePanel = new JPanel();
		moveAndResizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		moveAndResizePanel.setBackground(aa_grey);
		moveAndResizePanel.setMaximumSize(new Dimension(400, 25));
		
		//move and resize toolbar option available for prototype?
		//right now it's a dead button
		JButton moveAndResize= new JButton("Move & Resize Toolbar");
		moveAndResize.setMaximumSize(new Dimension(170,20));
		moveAndResize.setBackground(Color.GRAY);
		moveAndResize.setForeground(Color.WHITE);
		moveAndResize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//implement move and resize action 
			}
		});
		
		toolbarOptions.add(Box.createRigidArea(new Dimension(15, 0)));
		toolbarOptions.add(collapsed);
		toolbarOptions.add(expanded);
		moveAndResizePanel.add(Box.createRigidArea(new Dimension(15, 0)));
		moveAndResizePanel.add(moveAndResize);
		
		JPanel featuresHeader = new JPanel();
		featuresHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
		featuresHeader.setBackground(aa_grey);
		featuresHeader.setMaximumSize(new Dimension(400, 25));
		
		/*
		 * active features check boxes -- currently just placeholder check boxes
		 */
		JLabel activeFeatures = new JLabel("Active toolbar features:");
		activeFeatures.setFont(new Font("Serif", Font.BOLD, 16));
		activeFeatures.setForeground(Color.white); 
		
		featuresHeader.add(Box.createRigidArea(new Dimension(15, 0)));
		featuresHeader.add(activeFeatures);
		
		JCheckBox timerBox = new JCheckBox("Timer", true);
		timerBox.setFont(new Font("Serif", Font.BOLD, 14));
		timerBox.setForeground(Color.white);
		timerBox.setContentAreaFilled(false);
		timerBox.setFocusPainted(false);
		
		JCheckBox pmBox = new JCheckBox("Priority Manager", true);
		pmBox.setFont(new Font("Serif", Font.BOLD, 14));
		pmBox.setForeground(Color.white);
		pmBox.setContentAreaFilled(false);
		pmBox.setFocusPainted(false);
		
		JCheckBox ftsBox = new JCheckBox("Free Thought Space", true);
		ftsBox.setFont(new Font("Serif", Font.BOLD, 14));
		ftsBox.setForeground(Color.white);
		ftsBox.setContentAreaFilled(false);
		ftsBox.setFocusPainted(false);
		
		JCheckBox ntbBox = new JCheckBox("Negative Thought Burner", true);
		ntbBox.setFont(new Font("Serif", Font.BOLD, 14));
		ntbBox.setForeground(Color.white);
		ntbBox.setContentAreaFilled(false);
		ntbBox.setFocusPainted(false);
		
		JCheckBox htbBox = new JCheckBox("Happy Thought Button", true);
		htbBox.setFont(new Font("Serif", Font.BOLD, 14));
		htbBox.setForeground(Color.white);
		htbBox.setContentAreaFilled(false);
		htbBox.setFocusPainted(false);
		
		JCheckBox prBox = new JCheckBox("Progress Report", true);
		prBox.setFont(new Font("Serif", Font.BOLD, 14));
		prBox.setForeground(Color.white);
		prBox.setContentAreaFilled(false);
		prBox.setFocusPainted(false);
		
		
		JPanel displayOptionsHeader = new JPanel();
		displayOptionsHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
		displayOptionsHeader.setBackground(aa_grey);
		displayOptionsHeader.setMaximumSize(new Dimension(400, 25));
		/*
		 * Are we offering light mode/dark mode in the prototype?
		 * check boxes are placeholders for now
		 */
		JLabel settingsOptions = new JLabel("Settings options:");
		settingsOptions.setFont(new Font("Serif", Font.BOLD, 16));
		settingsOptions.setForeground(Color.white); 
		
		displayOptionsHeader.add(Box.createRigidArea(new Dimension(15, 0)));
		displayOptionsHeader.add(settingsOptions);
		
		JPanel displayBoxes = new JPanel();
		displayBoxes.setLayout(new FlowLayout(FlowLayout.LEFT));
		displayBoxes.setBackground(aa_grey);
		
		JCheckBox lightMode = new JCheckBox("Light mode", false);
		lightMode.setFont(new Font("Serif", Font.BOLD, 14));
		lightMode.setForeground(Color.white);
		lightMode.setContentAreaFilled(false);
		lightMode.setFocusPainted(false);
		
		JCheckBox darkMode = new JCheckBox("Dark mode", true);
		darkMode.setFont(new Font("Serif", Font.BOLD, 14));
		darkMode.setForeground(Color.white);
		darkMode.setContentAreaFilled(false);
		darkMode.setFocusPainted(false);
		
		displayBoxes.add(Box.createRigidArea(new Dimension(15, 0)));
		displayBoxes.add(lightMode);
		displayBoxes.add(Box.createRigidArea(new Dimension(25, 0)));
		displayBoxes.add(darkMode);
		
		/*
		 * add everything to general_panel
		 */
		general_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		general_panel.add(header_panel);
		general_panel.add(colorOptions);
		general_panel.add(opacityOptions);
		general_panel.add(toolbar_panel);
		general_panel.add(toolbarOptions);
		general_panel.add(moveAndResizePanel);
		//general_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		general_panel.add(featuresHeader);
		general_panel.add(timerBox);
		general_panel.add(pmBox);
		general_panel.add(ftsBox);
		general_panel.add(htbBox);
		general_panel.add(ntbBox);
		general_panel.add(prBox);
		//general_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		general_panel.add(displayOptionsHeader);
		general_panel.add(displayBoxes);
		
		//add to general_panel to  card_panel
		card_panel.add("general", general_panel);
	}
	
	/*
	 * RHS display for Notification System
	 */
	private void createNotificationsPanel() {
		
		JPanel notifications_panel = new JPanel();
		notifications_panel.setLayout(new BoxLayout(notifications_panel, BoxLayout.Y_AXIS));
		notifications_panel.setBackground(aa_grey);
		
		JPanel optionsAndDisplay = new JPanel();
		optionsAndDisplay.setLayout(new FlowLayout(FlowLayout.LEFT));
		optionsAndDisplay.setBackground(aa_grey);
		optionsAndDisplay.setMaximumSize(new Dimension(400, 130));
		
		JPanel optionsBoxes = new JPanel();
		optionsBoxes.setLayout(new BoxLayout(optionsBoxes, BoxLayout.Y_AXIS));
		optionsBoxes.setBackground(aa_grey);
		JPanel avatarDisplay = new JPanel();
		avatarDisplay.setBackground(aa_grey);
		avatarDisplay.setLayout(new BoxLayout(avatarDisplay, BoxLayout.Y_AXIS));
		
		JLabel notificationOptions = new JLabel("Notification options:");
		notificationOptions.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		notificationOptions.setForeground(Color.white);
		
		/*
		 * notifications check boxes -- currently just placeholder check boxes
		 */
		
		JCheckBox avatarBox = new JCheckBox("Avatar", false);
		avatarBox.setFont(new Font("Serif", Font.BOLD, 14));
		avatarBox.setForeground(Color.white);
		avatarBox.setContentAreaFilled(false);
		avatarBox.setFocusPainted(false);
		
		JCheckBox textBox = new JCheckBox("Text", true);
		textBox.setFont(new Font("Serif", Font.BOLD, 14));
		textBox.setForeground(Color.white);
		textBox.setContentAreaFilled(false);
		textBox.setFocusPainted(false);
		
		JCheckBox audioBox = new JCheckBox("Audio", false);
		audioBox.setFont(new Font("Serif", Font.BOLD, 14));
		audioBox.setForeground(Color.white);
		audioBox.setContentAreaFilled(false);
		audioBox.setFocusPainted(false);
		
		optionsBoxes.add(Box.createRigidArea(new Dimension(35, 0)));
		optionsBoxes.add(notificationOptions);
		optionsBoxes.add(avatarBox);
		optionsBoxes.add(textBox);
		optionsBoxes.add(audioBox);
				
		// displaying sample avatar selection as a placeholder 
		BufferedImage avatar = null;
		try {
			avatar = ImageIO.read(new File("images/avatar.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image av_img = avatar.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon avatarSample = new ImageIcon(av_img);
		JLabel displayAvatar = new JLabel(avatarSample);
		
		avatarDisplay.add(Box.createRigidArea(new Dimension(50, 0)));
		avatarDisplay.add(displayAvatar);
		optionsAndDisplay.add(optionsBoxes);
		optionsAndDisplay.add(avatarDisplay);
		
		JPanel frequency = new JPanel();
		frequency.setLayout(new FlowLayout(FlowLayout.LEFT));
		frequency.setBackground(aa_grey);
		frequency.setMaximumSize(new Dimension(400, 40));
		
		JLabel frequencyLabel = new JLabel("Frequency:");
		frequencyLabel.setFont(new Font("Serif", Font.BOLD, 16));
		frequencyLabel.setForeground(Color.white);
		
		JSlider frequenceySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		frequenceySlider.setBackground(Color.black);
		frequenceySlider.setForeground(Color.white);
		frequenceySlider.setMaximumSize(new Dimension(200, 25));
		
		frequency.add(Box.createRigidArea(new Dimension(15, 0)));
		frequency.add(frequencyLabel);
		frequency.add(frequenceySlider);
		
		JPanel text = new JPanel();
		text.setLayout(new FlowLayout(FlowLayout.LEFT));
		text.setBackground(aa_grey);
		text.setMaximumSize(new Dimension(400, 40));
		
		JLabel textSize = new JLabel("Text size:  ");
		textSize.setFont(new Font("Serif", Font.BOLD, 16));
		textSize.setForeground(Color.white);
		
		JSlider textSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
		textSizeSlider.setBackground(Color.black);
		textSizeSlider.setForeground(Color.white);
		textSizeSlider.setMaximumSize(new Dimension(200, 25));
		
		text.add(Box.createRigidArea(new Dimension(15, 0)));
		text.add(textSize);
		text.add(textSizeSlider);
		
		JPanel voice = new JPanel();
		voice.setLayout(new FlowLayout(FlowLayout.LEFT));
		voice.setBackground(aa_grey);
		voice.setMaximumSize(new Dimension(400, 40));
		
		JLabel voiceSelection = new JLabel("Voice selection:");
		voiceSelection.setFont(new Font("Serif", Font.BOLD, 16));
		voiceSelection.setForeground(Color.white);
		
		JButton chooseVoice = new JButton("Choose");
		chooseVoice.setMaximumSize(new Dimension(70,20));
		chooseVoice.setBackground(Color.GRAY);
		chooseVoice.setForeground(Color.WHITE);
		chooseVoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//implement chooseVoice 
			}
		});
		
		voice.add(Box.createRigidArea(new Dimension(15, 0)));
		voice.add(voiceSelection);
		voice.add(chooseVoice);
		
		JPanel avLabelAndBox = new JPanel();
		avLabelAndBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		avLabelAndBox.setBackground(aa_grey);
		avLabelAndBox.setMaximumSize(new Dimension(400, 40));
		
		JLabel avatarLabel = new JLabel("Avatar:");
		avatarLabel.setFont(new Font("Serif", Font.BOLD, 16));
		avatarLabel.setForeground(Color.white);
		
		JCheckBox onScreenBox = new JCheckBox("Always on screen", false);
		onScreenBox.setFont(new Font("Serif", Font.BOLD, 14));
		onScreenBox.setForeground(Color.white);
		onScreenBox.setContentAreaFilled(false);
		onScreenBox.setFocusPainted(false);
		
		avLabelAndBox.add(Box.createRigidArea(new Dimension(15, 0)));
		avLabelAndBox.add(avatarLabel);
		avLabelAndBox.add(onScreenBox);
		
		JPanel avatarSelectionButtons = new JPanel();
		avatarSelectionButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		avatarSelectionButtons.setBackground(aa_grey);
		
		JButton chooseAvatar = new JButton("Choose");
		chooseAvatar.setMaximumSize(new Dimension(70,20));
		chooseAvatar.setBackground(Color.GRAY);
		chooseAvatar.setForeground(Color.WHITE);
		chooseAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//implement chooseAvatar 
			}
		});
		
		//pretty sure we are not implementing this part
		JButton uploadAvatar = new JButton("Upload");
		uploadAvatar.setMaximumSize(new Dimension(70,20));
		uploadAvatar.setBackground(Color.GRAY);
		uploadAvatar.setForeground(Color.WHITE);
		uploadAvatar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//implement chooseVoice 
			}
		});
		
		avatarSelectionButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		avatarSelectionButtons.add(chooseAvatar);
		avatarSelectionButtons.add(Box.createRigidArea(new Dimension(25, 0)));
		avatarSelectionButtons.add(uploadAvatar);
		
		/*
		 * add everything to notifications_panel
		 */
		notifications_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		notifications_panel.add(optionsAndDisplay);
		notifications_panel.add(frequency);
		notifications_panel.add(text);
		notifications_panel.add(voice);
		notifications_panel.add(avLabelAndBox);
		notifications_panel.add(avatarSelectionButtons);
		
		//add notifications panel to card_panel
		card_panel.add("notifications", notifications_panel);
	}
	
	/*
	 * RHS display for Priority Manager
	 */
	private void createPriorityManagerPanel() {
		
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
				//call to open PM 
				Priority_Manager pm = new Priority_Manager();
        		//pm.open_pm();
			}
		});
		
		JButton calendar = new JButton("Calendar");
		calendar.setMaximumSize(new Dimension(200, 30));
		calendar.setBackground(Color.GRAY);
		calendar.setForeground(Color.WHITE);
		calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to open calendar
			}
		});
		
		JButton calendarInt = new JButton("Calendar Integration");
		calendarInt.setMaximumSize(new Dimension(200, 30));
		calendarInt.setBackground(Color.GRAY);
		calendarInt.setForeground(Color.WHITE);
		calendarInt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//call to integrate calendar
			}
		});
		
		pm_panel.add(Box.createRigidArea(new Dimension(25, 10)));
		pm_panel.add(managerOptions);
		pm_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		pm_panel.add(openPM);
		pm_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		pm_panel.add(calendar);
		pm_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		pm_panel.add(calendarInt);
	
		card_panel.add("priority manager", pm_panel);
	}
	
	/*
	 * RHS display for Pomodoro Timer
	 */
	private void createPomodoroTimerPanel() {
		
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
		
		JPanel pomToggle = new JPanel();
		pomToggle.setLayout(new FlowLayout(FlowLayout.LEFT));
		pomToggle.setBackground(aa_grey);
		pomToggle.setMaximumSize(new Dimension(400, 35));
		
		JCheckBox toggleOn = new JCheckBox("On", true);
		toggleOn.setFont(new Font("Serif", Font.BOLD, 14));
		toggleOn.setForeground(Color.white);
		toggleOn.setContentAreaFilled(false);
		toggleOn.setFocusPainted(false);
		
		JCheckBox toggleOff = new JCheckBox("Off", false);
		toggleOff.setFont(new Font("Serif", Font.BOLD, 14));
		toggleOff.setForeground(Color.white);
		toggleOff.setContentAreaFilled(false);
		toggleOff.setFocusPainted(false);
		
		pomToggle.add(Box.createRigidArea(new Dimension(15, 0)));
		pomToggle.add(toggleOn);
		pomToggle.add(Box.createRigidArea(new Dimension(25, 0)));
		pomToggle.add(toggleOff);
		
		JPanel intervalSettings = new JPanel();
		GridLayout grid = new GridLayout(0,2);
		intervalSettings.setLayout(grid);
		intervalSettings.setBackground(aa_grey);
		intervalSettings.setMaximumSize(new Dimension(325, 60));
		
		//can't figure out how to make text boxes lest wide wit this layout
		JTextArea workInterval = new JTextArea();
		workInterval.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		workInterval.setBorder(new LineBorder(Color.black,5,false));
		
		JLabel workMinutes = new JLabel("minute work periods");
		workMinutes.setFont(new Font("Serif", Font.BOLD, 16));
		workMinutes.setForeground(Color.white);
		
		//can't figure out how to make text boxes lest wide wit this layout
		JTextArea breakInterval = new JTextArea();
		breakInterval.setFont(new Font("TimesRoman", Font.BOLD, 16));
		breakInterval.setBorder(new LineBorder(Color.black,5,false));
		
		JLabel breakMinutes = new JLabel("minute work periods");
		breakMinutes.setFont(new Font("Serif", Font.BOLD, 16));
		breakMinutes.setForeground(Color.white);
		
		intervalSettings.add(workInterval);
		intervalSettings.add(workMinutes);
		intervalSettings.add(breakInterval);
		intervalSettings.add(breakMinutes);
		
		// we don't need time remaining display settings anymore, right?
		JPanel timeRemaining = new JPanel();
		timeRemaining.setLayout(new FlowLayout(FlowLayout.LEFT));
		timeRemaining.setBackground(aa_grey);
		timeRemaining.setMaximumSize(new Dimension(400, 25));
		
		JLabel showTime = new JLabel("Show time remaining:");
		showTime.setFont(new Font("Serif", Font.BOLD, 16));
		showTime.setForeground(Color.white);
		
		timeRemaining.add(Box.createRigidArea(new Dimension(15, 0)));
		timeRemaining.add(showTime);
		
		JPanel countdownToggle = new JPanel();
		countdownToggle.setLayout(new FlowLayout(FlowLayout.LEFT));
		countdownToggle.setBackground(aa_grey);
		countdownToggle.setMaximumSize(new Dimension(400, 35));
		
		JCheckBox toggleTimerOn = new JCheckBox("On", true);
		toggleTimerOn.setFont(new Font("Serif", Font.BOLD, 14));
		toggleTimerOn.setForeground(Color.white);
		toggleTimerOn.setContentAreaFilled(false);
		toggleTimerOn.setFocusPainted(false);
		
		JCheckBox toggleTimerOff = new JCheckBox("Off", false);
		toggleTimerOff.setFont(new Font("Serif", Font.BOLD, 14));
		toggleTimerOff.setForeground(Color.white);
		toggleTimerOff.setContentAreaFilled(false);
		toggleTimerOff.setFocusPainted(false);
		
		countdownToggle.add(Box.createRigidArea(new Dimension(15, 0)));
		countdownToggle.add(toggleTimerOn);
		countdownToggle.add(Box.createRigidArea(new Dimension(25, 0)));
		countdownToggle.add(toggleTimerOff);		
		
		pomodoro_panel.add(Box.createRigidArea(new Dimension(0, 10)));
		pomodoro_panel.add(pomodoro_header);
		pomodoro_panel.add(pomToggle);
		pomodoro_panel.add(intervalSettings);
		pomodoro_panel.add(timeRemaining);
		pomodoro_panel.add(countdownToggle);
	
		card_panel.add("pomodoro timer", pomodoro_panel);
	}
	
	/*
	 * RHS display for Thought Management
	 */
	private void createThoughtPanel() {
		
		JPanel thought_panel = new JPanel();
		thought_panel.setLayout(new BoxLayout(thought_panel, BoxLayout.Y_AXIS));
		thought_panel.setBackground(aa_grey);
		
		JLabel activeHeader = new JLabel("Active:"); 
		activeHeader.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 16));
		activeHeader.setForeground(Color.white);
		
		JCheckBox ftsBox = new JCheckBox("Free Thought Space", true);
		ftsBox.setFont(new Font("Serif", Font.BOLD, 14));
		ftsBox.setForeground(Color.white);
		ftsBox.setContentAreaFilled(false);
		ftsBox.setFocusPainted(false);
		
		JCheckBox ntbBox = new JCheckBox("Negative Thought Burner", true);
		ntbBox.setFont(new Font("Serif", Font.BOLD, 14));
		ntbBox.setForeground(Color.white);
		ntbBox.setContentAreaFilled(false);
		ntbBox.setFocusPainted(false);
		
		JCheckBox autoLinkBox = new JCheckBox("Auto-link NTB to Happy Thought Button", true);
		autoLinkBox.setFont(new Font("Serif", Font.BOLD, 14));
		autoLinkBox.setForeground(Color.white);
		autoLinkBox.setContentAreaFilled(false);
		autoLinkBox.setFocusPainted(false);
		
		JCheckBox htbBox = new JCheckBox("Happy Thought Button", true);
		htbBox.setFont(new Font("Serif", Font.BOLD, 14));
		htbBox.setForeground(Color.white);
		htbBox.setContentAreaFilled(false);
		htbBox.setFocusPainted(false);
		
		thought_panel.add(Box.createRigidArea(new Dimension(25, 10)));
		thought_panel.add(activeHeader);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 15)));
		thought_panel.add(ftsBox);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 5)));
		thought_panel.add(ntbBox);
		thought_panel.add(Box.createRigidArea(new Dimension(50, 5)));
		thought_panel.add(autoLinkBox);
		thought_panel.add(Box.createRigidArea(new Dimension(0, 5)));
		thought_panel.add(htbBox);
	
		card_panel.add("thought management", thought_panel);
	}
	
	/**
	 * creates/displays UI
	 */
	public void open_settings(DataBase db) {
		EventQueue.invokeLater(new Runnable() {
			@Override 
			public void run() {
				//gets rid of normal title bar
				settings_frame.setUndecorated(true);
				//sets window width and height
				settings_frame.setPreferredSize(new Dimension(width, height)); 
				
				JPanel masterPanel = new JPanel(new BorderLayout());
				masterPanel.setBackground(Color.black);
				
				//creates panel for custom title bar
				JMenuBar title_panel = new JMenuBar();
				title_panel.setBorder(line);
				//aligns buttons in title panel from right -> left
				title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));	
				//makes title panel background grey
				title_panel.setBackground(aa_grey);
				//creates border and sets to purple
				title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
				//creates label
				JLabel title = new JLabel("Settings");
				//makes font color white
				title.setForeground(Color.white);
				//sets font, size, and bold
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
		        		//close window without saving info
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
				
				/*
				 * creates layout for sub-menus and panels for each sub-menu
				 */
				card_panel.setLayout(card_layout);
				//move these calls to their respective action listeners 
				createGeneralPanel(); 
				createNotificationsPanel();
				createPriorityManagerPanel();
				createPomodoroTimerPanel();
				createThoughtPanel();								
				
				
		
				/*
				 * create buttons for sideMenu
				 */
				JButton general = new JButton("General");
				//makes font color white
				general.setForeground(Color.white);
				//sets font, size, and bold
				general.setFont(new Font("Serif", Font.BOLD, 12));
				general.setContentAreaFilled(true);
				general.setBorderPainted(false);
				general.setFocusPainted(false);
				general.setBackground(aa_grey);
				general.setMaximumSize(new Dimension(180,20));
				general.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open general sub-menu
						card_layout.show(card_panel, "general");						
					}
				});
				
				JButton notifications = new JButton("Notification System");
				//makes font color white
				notifications.setForeground(Color.white);
				//sets font, size, and bold
				notifications.setFont(new Font("Serif", Font.BOLD, 12));
				notifications.setContentAreaFilled(true);
				notifications.setBorderPainted(false);
				notifications.setFocusPainted(false);
				notifications.setBackground(aa_grey);
				notifications.setMaximumSize(new Dimension(180,20));
				notifications.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open notification systems sub-menu
						card_layout.show(card_panel, "notifications");
					}
				});
				
				JButton pm = new JButton("Priority Manager");
				//makes font color white
				pm.setForeground(Color.white);
				//sets font, size, and bold
				pm.setFont(new Font("Serif", Font.BOLD, 12));
				pm.setContentAreaFilled(true);
				pm.setBorderPainted(false);
				pm.setFocusPainted(false);
				pm.setBackground(aa_grey);
				pm.setMaximumSize(new Dimension(180,20));
				pm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open priority manager sub-menu
						card_layout.show(card_panel, "priority manager");
					}
				});
				
				JButton pomTimer = new JButton("Pomodoro Timer");
				//makes font color white
				pomTimer.setForeground(Color.white);
				//sets font, size, and bold
				pomTimer.setFont(new Font("Serif", Font.BOLD, 12));
				pomTimer.setContentAreaFilled(true);
				pomTimer.setBorderPainted(false);
				pomTimer.setFocusPainted(false);
				pomTimer.setBackground(aa_grey);
				pomTimer.setMaximumSize(new Dimension(180,20));
				pomTimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open pomodoro timer sub-menu
						card_layout.show(card_panel, "pomodoro timer");
					}
				});
				
				JButton thoughts = new JButton("Thought Management");
				//makes font color white
				thoughts.setForeground(Color.white);
				//sets font, size, and bold
				thoughts.setFont(new Font("Serif", Font.BOLD, 12));
				thoughts.setContentAreaFilled(true);
				thoughts.setBorderPainted(false);
				thoughts.setFocusPainted(false);
				thoughts.setBackground(aa_grey);
				thoughts.setMaximumSize(new Dimension(180,20));
				thoughts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open free through space sub-menu
						card_layout.show(card_panel, "thought management");
					}
				});
				
				
				/*
				 * create buttons for bottom border
				 */
				JButton progressReport = new JButton("Download Progress Report");
				//makes font color white
				progressReport.setForeground(Color.white);
				//sets font, size, and bold
				progressReport.setFont(new Font("Serif", Font.BOLD, 12));
				progressReport.setContentAreaFilled(true);
				progressReport.setBorderPainted(false);
				progressReport.setFocusPainted(false);
				progressReport.setBackground(aa_purple);
				progressReport.setMaximumSize(new Dimension(180,20));
				progressReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//download progress report
					}
				});
				
				JButton guideButton = new JButton("Guide");
				//makes font color white
				guideButton.setForeground(Color.white);
				//sets font, size, and bold
				guideButton.setFont(new Font("Serif", Font.BOLD, 12));
				guideButton.setContentAreaFilled(true);
				guideButton.setBorderPainted(false);
				guideButton.setFocusPainted(false);
				guideButton.setBackground(aa_purple);
				guideButton.setMaximumSize(new Dimension(175,20));
				guideButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//open guide
					}
				});
				
				JButton apply = new JButton("apply");
				//makes font color white
				apply.setForeground(Color.white);
				//sets font, size, and bold
				apply.setFont(new Font("Serif", Font.BOLD, 12));
				apply.setContentAreaFilled(true);
				apply.setBorderPainted(false);
				apply.setFocusPainted(false);
				apply.setBackground(aa_grey);
				apply.setMaximumSize(new Dimension(70,20));
				apply.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//call to apply changes 
					}
				});
				
				JButton cancel = new JButton("cancel");
				//makes font color white
				cancel.setForeground(Color.white);
				//sets font, size, and bold
				cancel.setFont(new Font("Serif", Font.BOLD, 12));
				cancel.setContentAreaFilled(true);
				cancel.setBorderPainted(false);
				cancel.setFocusPainted(false);
				cancel.setBackground(aa_grey);
				cancel.setMaximumSize(new Dimension(70,20));
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
				sideMenu.add(Box.createRigidArea(new Dimension(0,390)));
				sideMenu.add(progressReport);
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
				bottomButtons.add(guideButton);
				bottomButtons.add(Box.createRigidArea(new Dimension(175, 0)));
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
				 * add master panel to frame
				 */
				settings_frame.getContentPane().add(masterPanel); 
				settings_frame.getContentPane().setBackground(Color.black);
				settings_frame.pack();
				settings_frame.setAlwaysOnTop(true);
				settings_frame.setVisible(true);
				settings_frame.setResizable(true);
				settings_frame.setLocationRelativeTo(null);
			}
		});
	}
}
