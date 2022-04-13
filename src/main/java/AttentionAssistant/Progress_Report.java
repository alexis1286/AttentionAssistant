package AttentionAssistant;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.Properties;

import org.jdatepicker.impl.*;
import org.jdatepicker.util.*;
import org.jdatepicker.*;
import org.jdatepicker.graphics.*;

/**
 * Class that encompasses Progress Report whenever Progress Report is opened 
 * from the Navigation Bar, Settings Menu, or Parent Portal. 
 * @author krchr
 *
 */

public class Progress_Report {
	
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = 700; 
	private int width = 625; 
	private int mouseX;
	private int mouseY;
	JLabel displayAvatar;
	final static boolean shouldFill = true; 
	final static boolean shouldWeightX = true; 
	final static boolean RIGHT_TO_LEFT = false; 
	
	/**
	 * creates title bar
	 */
	private JMenuBar createTitlePanel(Frame pr_frame) {
		
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
				pr_frame.setLocation(pr_frame.getX() + e.getX() - mouseX, pr_frame.getY() + e.getY() - mouseY);
			}
		});
		
		title_panel.addMouseListener(new MouseAdapter(){
			@Override 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		JLabel title = new JLabel("Progress Report");
		title.setForeground(Color.white);
		title.setFont(new Font("Serif", Font.BOLD, 20));
		
		/*
		 * create icons to use as buttons for title bar
		 */
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
        		pr_frame.dispose();
        	
        }});
		
		Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon guideIcon = new ImageIcon(g_img);
		
		JButton guide = new JButton(guideIcon);
		guide.setBorderPainted(false);
		guide.setContentAreaFilled(false);
		guide.setFocusPainted(false);
		
		title_panel.add(title);
		title_panel.add(Box.createRigidArea(new Dimension(335, 0)));
		title_panel.add(guide);
		title_panel.add(close_window);
		
		return title_panel;
		
	}
	
	private JPanel createDatePickers(){
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));
		datePanel.setBackground(Color.black);
		datePanel.setMaximumSize(new Dimension(600, 80));
		
		JPanel dateIntervalPanel = new JPanel();
		dateIntervalPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		dateIntervalPanel.setMaximumSize(new Dimension(600, 35));
		
		JLabel startDate = new JLabel("Start Date: ");
		startDate.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 17));
		startDate.setBackground(Color.black);
		
		UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year"); 
		
		JDatePanelImpl beginDatePanel = new JDatePanelImpl(model, properties);
		JDatePickerImpl beginDatePicker = new JDatePickerImpl(beginDatePanel, new DateLabelFormatter());
		
		JLabel endDate = new JLabel("End Date:  ");
		endDate.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 17));
		endDate.setBackground(Color.black);
		
		UtilDateModel model2 = new UtilDateModel();
		Properties properties2 = new Properties();
		properties2.put("text.today", "Today");
		properties2.put("text.month", "Month");
		properties2.put("text.year", "Year"); 
		
		JDatePanelImpl finishDatePanel = new JDatePanelImpl(model2, properties2);
		JDatePickerImpl finishDatePicker = new JDatePickerImpl(finishDatePanel, new DateLabelFormatter());
		
		dateIntervalPanel.add(startDate);
		dateIntervalPanel.add(beginDatePicker);
		dateIntervalPanel.add(endDate);
		dateIntervalPanel.add(finishDatePicker);
		
		JPanel applyPanel = new JPanel();
		applyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		applyPanel.setMaximumSize(new Dimension(600, 35));
		
		JButton apply = new JButton(" apply ");
		apply.setForeground(Color.white);
		apply.setFont(new Font("Serif", Font.BOLD, 16));
		apply.setContentAreaFilled(true);
		apply.setBorderPainted(true);
		apply.setBorder(new LineBorder(aa_grey));
		apply.setFocusPainted(false);
		apply.setBackground(aa_purple);
		apply.setMaximumSize(new Dimension(75,30));
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//refresh frame to reflect selected dates
			}
		});
		
		applyPanel.add(Box.createRigidArea(new Dimension(530, 0)));
		applyPanel.add(apply);
		
		datePanel.add(dateIntervalPanel);	
		datePanel.add(applyPanel);
		
		return datePanel;
	}
	
	private JPanel createSummaryPanel() {
		
		JPanel summaryPanel = new JPanel();
		summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
		summaryPanel.setMaximumSize(new Dimension(600, 550));
		
		JPanel tasks = new JPanel();
		tasks.setLayout(new BoxLayout(tasks, BoxLayout.Y_AXIS));
		tasks.setMaximumSize(new Dimension(550, 500));
		
		JPanel taskHeaderPanel = new JPanel();
		taskHeaderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		taskHeaderPanel.setMaximumSize(new Dimension(550, 50));
		
		JLabel tasksHeader = new JLabel("Tasks:"); 
		tasksHeader.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
		tasksHeader.setBackground(Color.black);
		
		taskHeaderPanel.add(tasksHeader);
		
		JPanel taskTotals = new JPanel();
		taskTotals.setLayout(new FlowLayout(FlowLayout.LEFT));
		taskTotals.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, aa_purple));
		taskTotals.setMaximumSize(new Dimension(550, 140));
		
		BufferedImage star = null;
		try {
			star = ImageIO.read(new File("images/star.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image star_img = star.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Icon starMedal = new ImageIcon(star_img);
		JLabel displayMedal = new JLabel(starMedal);
		
		JPanel taskColumn1 = new JPanel();
		taskColumn1.setLayout(new BoxLayout(taskColumn1, BoxLayout.Y_AXIS));
		taskColumn1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, aa_purple));
		
		JPanel taskRow1 = new JPanel();
		taskRow1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//placeholder until database call is put in.
		JLabel totalCompleted = new JLabel("8");
		totalCompleted.setFont(new Font("Serif", Font.BOLD, 25));
		totalCompleted.setBackground(Color.black);
		
		JLabel taskCompleted = new JLabel("completed");
		taskCompleted.setFont(new Font("Serif", Font.BOLD, 20));
		taskCompleted.setBackground(Color.black);
		
		taskRow1.add(totalCompleted);
		taskRow1.add(taskCompleted);
		
		JPanel taskRow2 = new JPanel();
		taskRow2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//placeholder until database call is put in.
		JLabel totalAdded = new JLabel("25");
		totalAdded.setFont(new Font("Serif", Font.BOLD, 25));
		totalAdded.setBackground(Color.black);
		
		JLabel taskAdded = new JLabel("added");
		taskAdded.setFont(new Font("Serif", Font.BOLD, 20));
		taskAdded.setBackground(Color.black);
		
		taskRow2.add(totalAdded);
		taskRow2.add(taskAdded);
		
		taskColumn1.add(taskRow1);
		taskColumn1.add(taskRow2);
		
		JPanel taskColumn2 = new JPanel();
		taskColumn2.setLayout(new BoxLayout(taskColumn2, BoxLayout.Y_AXIS));
		taskColumn2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, aa_purple));
		
		JPanel taskRow3 = new JPanel();
		taskRow3.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//placeholder until database call is put in.
		JLabel totalStarted = new JLabel("11");
		totalStarted.setFont(new Font("Serif", Font.BOLD, 25));
		totalStarted.setBackground(Color.black);
		
		JLabel taskStarted = new JLabel("started");
		taskStarted.setFont(new Font("Serif", Font.BOLD, 20));
		taskStarted.setBackground(Color.black);
		
		taskRow3.add(totalStarted);
		taskRow3.add(taskStarted);
		
		JPanel taskRow4 = new JPanel();
		taskRow4.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//placeholder until database call is put in.
		JLabel totalInProgress = new JLabel("3");
		totalInProgress.setFont(new Font("Serif", Font.BOLD, 25));
		totalInProgress.setBackground(Color.black);
		
		JLabel taskProgress = new JLabel("in progress");
		taskProgress.setFont(new Font("Serif", Font.BOLD, 20));
		taskProgress.setBackground(Color.black);
		
		taskRow4.add(totalInProgress);
		taskRow4.add(taskProgress);
		
		taskColumn2.add(taskRow3);
		taskColumn2.add(taskRow4);
		
		JPanel taskColumn3 = new JPanel();
		taskColumn3.setLayout(new BoxLayout(taskColumn3, BoxLayout.Y_AXIS));
		
		JPanel taskRow5 = new JPanel();
		taskRow5.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel timeOnTask = new JLabel("on task: ");
		timeOnTask.setFont(new Font("Serif", Font.BOLD, 20));
		timeOnTask.setBackground(Color.black);
		
		//placeholder until database call is put in.
		JLabel totalOnTask = new JLabel("9hr 23min");
		totalOnTask.setFont(new Font("Serif", Font.BOLD, 20));
		totalOnTask.setBackground(Color.black);
		
		taskRow5.add(timeOnTask);
		taskRow5.add(totalOnTask);
		
		JPanel taskRow6 = new JPanel();
		taskRow6.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel timeOffTask = new JLabel("off task: ");
		timeOffTask.setFont(new Font("Serif", Font.BOLD, 20));
		timeOffTask.setBackground(Color.black);
		
		//placeholder until database call is put in.
		JLabel totalOffTask = new JLabel("1hr 5min");
		totalOffTask.setFont(new Font("Serif", Font.BOLD, 20));
		totalOffTask.setBackground(Color.black);
		
		taskRow6.add(timeOffTask);
		taskRow6.add(totalOffTask);
		
		taskColumn3.add(taskRow5);
		taskColumn3.add(taskRow6);
		
		taskTotals.add(displayMedal);
		taskTotals.add(taskColumn1);
		taskTotals.add(taskColumn2);
		taskTotals.add(taskColumn3);
		
		tasks.add(taskHeaderPanel);
		tasks.add(taskTotals);
		
		JPanel features = new JPanel();
		features.setLayout(new BoxLayout(features, BoxLayout.Y_AXIS));
		features.setMaximumSize(new Dimension(550, 500));
		
		JPanel featuresHeaderPanel = new JPanel();
		featuresHeaderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		featuresHeaderPanel.setMaximumSize(new Dimension(550, 50));
		
		JLabel featuresHeader = new JLabel("Features:"); 
		featuresHeader.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
		featuresHeader.setBackground(Color.black);
		
		featuresHeaderPanel.add(featuresHeader);
		
		JPanel featureTotals = new JPanel();
		featureTotals.setLayout(new BoxLayout(featureTotals, BoxLayout.Y_AXIS));
		featureTotals.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, aa_purple));
		featureTotals.setMaximumSize(new Dimension(550, 140));
		
		JPanel featureRow1 = new JPanel();
		featureRow1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		BufferedImage flames = null;
		try {
			flames = ImageIO.read(new File("images/burner.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image flames_img = flames.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Icon ntbIcon = new ImageIcon(flames_img);
		JLabel ntb_Label = new JLabel(ntbIcon);
		
		//placeholder until database call is put in.
		JLabel totalNTB = new JLabel("2 times");
		totalNTB.setFont(new Font("Serif", Font.BOLD, 25));
		totalNTB.setBackground(Color.black);
		
		BufferedImage happyFace = null;
		try {
			happyFace = ImageIO.read(new File("images/happy_button.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image happyFace_img = happyFace.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Icon htbIcon = new ImageIcon(happyFace_img);
		JLabel htb_Label = new JLabel(htbIcon);
		
		//placeholder until database call is put in.
		JLabel totalHTB = new JLabel("51 times");
		totalHTB.setFont(new Font("Serif", Font.BOLD, 25));
		totalHTB.setBackground(Color.black);

		featureRow1.add(Box.createRigidArea(new Dimension(50, 0)));
		featureRow1.add(ntb_Label);
		featureRow1.add(Box.createRigidArea(new Dimension(10, 0)));
		featureRow1.add(totalNTB);
		featureRow1.add(Box.createRigidArea(new Dimension(80, 0)));
		featureRow1.add(htb_Label);
		featureRow1.add(Box.createRigidArea(new Dimension(10, 0)));
		featureRow1.add(totalHTB);
		
		JPanel featureRow2 = new JPanel();
		featureRow2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		BufferedImage paint = null;
		try {
			paint = ImageIO.read(new File("images/thought_space.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image thought_img = paint.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Icon ftsIcon = new ImageIcon(thought_img);
		JLabel fts_Label = new JLabel(ftsIcon);
		
		//placeholder until database call is put in.
		JLabel totalFTS = new JLabel("24 times");
		totalFTS.setFont(new Font("Serif", Font.BOLD, 25));
		totalFTS.setBackground(Color.black);
		
		featureRow2.add(Box.createRigidArea(new Dimension(200, 0)));
		featureRow2.add(fts_Label);
		featureRow2.add(Box.createRigidArea(new Dimension(10, 0)));
		featureRow2.add(totalFTS);
		
		featureTotals.add(featureRow1);
		featureTotals.add(featureRow2);
		
		features.add(featuresHeaderPanel);
		features.add(featureTotals);
		
		JPanel hyperfocus = new JPanel();
		hyperfocus.setLayout(new BoxLayout(hyperfocus, BoxLayout.Y_AXIS));
		hyperfocus.setMaximumSize(new Dimension(550, 500));
		
		JPanel hF_headerPanel = new JPanel();
		hF_headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		hF_headerPanel.setMaximumSize(new Dimension(550, 50));
		
		JLabel hf_Header = new JLabel("Hyper-focusing:"); 
		hf_Header.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));
		hf_Header.setBackground(Color.black);
		
		hF_headerPanel.add(hf_Header);
		
		JPanel hF_Totals = new JPanel();
		hF_Totals.setLayout(new FlowLayout(FlowLayout.LEFT));
		hF_Totals.setMaximumSize(new Dimension(550, 140));
		
		BufferedImage magnifyingGlass = null;
		try {
			magnifyingGlass = ImageIO.read(new File("images/hf_magnifying.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image mag_img = magnifyingGlass.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH);
		Icon hfGlass = new ImageIcon(mag_img);
		JLabel displayMG = new JLabel(hfGlass);
		
		JPanel hF_Row1 = new JPanel();
		hF_Row1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//placeholder until database call is put in.
		JLabel totalHF = new JLabel("5");
		totalHF.setFont(new Font("Serif", Font.BOLD, 35));
		totalHF.setBackground(Color.black);
		totalHF.setForeground(aa_purple);
		
		JLabel hF_notifications = new JLabel("self-care notifications");
		hF_notifications.setFont(new Font("Serif", Font.BOLD, 20));
		hF_notifications.setBackground(Color.black);
		
		hF_Row1.add(totalHF);
		hF_Row1.add(hF_notifications);
		
		hF_Totals.add(Box.createRigidArea(new Dimension(80, 0)));
		hF_Totals.add(displayMG);
		hF_Totals.add(Box.createRigidArea(new Dimension(5, 0)));
		hF_Totals.add(hF_Row1);
		
		hyperfocus.add(hF_headerPanel);
		hyperfocus.add(hF_Totals);
		
		summaryPanel.add(tasks);
		summaryPanel.add(features);
		summaryPanel.add(hyperfocus);
		
		return summaryPanel;
	}
	
	private JPanel createCenterPanel(CardLayout cardLayout) {
	
		JPanel center_panel = new JPanel();
		center_panel.setLayout(new BoxLayout(center_panel, BoxLayout.Y_AXIS));
		center_panel.setBackground(Color.black);
		
		JPanel datePanel = createDatePickers();
		
		//add card layout that will toggle between summary and two tables
		JPanel reportViews = new JPanel();
		reportViews.setLayout(cardLayout);
		reportViews.setMaximumSize(new Dimension(600, 600));
		
		//add scrollable panel to have all the summary stuff
		JPanel summaryPanel = createSummaryPanel();
		
		/*
		JScrollPane summary_pane = new JScrollPane(summaryPanel);
		//summary_pane.setBackground(Color.black);
		Border empty = new EmptyBorder(0,0,0,0);
		summary_pane.setBorder(empty);
		summary_pane.setPreferredSize(new Dimension(600,500));
		*/
		
		reportViews.add("summary", summaryPanel);
		
		//add scrollable panel that will have table for all tasks added
		
		//add scrollable panel that will have table for all tasked completed
		
		
		
		center_panel.add(datePanel);
		center_panel.add(reportViews);
		return center_panel;		
	}
	
	private JPanel createButtonPanel(CardLayout cardLayout) {
		
		JPanel bottomButtons = new JPanel();
		bottomButtons.setLayout(new BoxLayout(bottomButtons, BoxLayout.X_AXIS));
		bottomButtons.setBackground(aa_grey);
		//bottomButtons.setBorder(BorderFactory.createLineBorder(aa_purple));
		
		JLabel view = new JLabel("Select View: ");
		view.setForeground(Color.white);
		view.setFont(new Font("Serif", Font.BOLD, 18));
		
		JButton summary = new JButton("summary");
		summary.setForeground(Color.white);
		summary.setFont(new Font("Serif", Font.BOLD, 16));
		summary.setContentAreaFilled(true);
		summary.setBorderPainted(true);
		summary.setBorder(new LineBorder(Color.GRAY, 2));
		summary.setFocusPainted(false);
		summary.setBackground(aa_purple);
		summary.setMaximumSize(new Dimension(125,50));
		summary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open cardlayout to summary panel
			}
		});
		
		JButton addedTasks = new JButton("tasks added");
		addedTasks.setForeground(Color.white);
		addedTasks.setFont(new Font("Serif", Font.BOLD, 16));
		addedTasks.setContentAreaFilled(true);
		addedTasks.setBorderPainted(true);
		addedTasks.setBorder(new LineBorder(Color.GRAY, 2));
		addedTasks.setFocusPainted(false);
		addedTasks.setBackground(aa_purple);
		addedTasks.setMaximumSize(new Dimension(125,50));
		addedTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open cardLayout to tasks added table
			}
		});
		
		JButton completedTasks = new JButton("tasks completed");
		completedTasks.setForeground(Color.white);
		completedTasks.setFont(new Font("Serif", Font.BOLD, 16));
		completedTasks.setContentAreaFilled(true);
		completedTasks.setBorderPainted(true);
		completedTasks.setBorder(new LineBorder(Color.GRAY, 2));
		completedTasks.setFocusPainted(false);
		completedTasks.setBackground(aa_purple);
		completedTasks.setMaximumSize(new Dimension(125,50));
		completedTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open cardLayout to tasks completed table
			}
		});
		
		bottomButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		bottomButtons.add(view);
		bottomButtons.add(Box.createRigidArea(new Dimension(40, 0)));
		bottomButtons.add(summary);
		bottomButtons.add(Box.createRigidArea(new Dimension(20, 0)));
		bottomButtons.add(addedTasks);
		bottomButtons.add(Box.createRigidArea(new Dimension(20, 0)));
		bottomButtons.add(completedTasks);
		
		return bottomButtons;
	}
	/**
	 * creates/displays Progress Report GUI
	 * @param db, userID
	 */
	public void open_progressReport(int userID, DataBase db) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				JFrame pr_frame = new JFrame("Progress Report");
				
				pr_frame.setUndecorated(true);
				pr_frame.setPreferredSize(new Dimension(width, height));
				
				JPanel masterPanel = new JPanel(new BorderLayout());
				masterPanel.setBackground(Color.black);
				
				CardLayout cardLayout = new CardLayout();
					
				JMenuBar title_panel = createTitlePanel(pr_frame);
				JPanel center_panel = createCenterPanel(cardLayout);
				JPanel bottomButtons = createButtonPanel(cardLayout);
				
				/*
				 * populates master panel 
				 */
				masterPanel.add(title_panel, BorderLayout.PAGE_START); 
				masterPanel.add(center_panel, BorderLayout.CENTER);
				masterPanel.add(bottomButtons, BorderLayout.PAGE_END);
			
				/*
				 * adds master panel to frame
				 */
				pr_frame.getContentPane().add(masterPanel); 
				pr_frame.getContentPane().setBackground(Color.black);
				pr_frame.pack();
				pr_frame.setAlwaysOnTop(false);
				pr_frame.setVisible(true);
				pr_frame.setResizable(true);
				pr_frame.setLocationRelativeTo(null);
			
			}
		});
	}
	
}