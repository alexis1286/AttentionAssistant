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
	private int width = 550; 
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
		title_panel.add(Box.createRigidArea(new Dimension(275, 0)));
		title_panel.add(guide);
		title_panel.add(close_window);
		
		return title_panel;
		
	}
	
	private JPanel createDatePickers(){
		
		JPanel datePanels = new JPanel();
		datePanels.setLayout(new BoxLayout(datePanels, BoxLayout.Y_AXIS));
		datePanels.setBackground(Color.black);
		datePanels.setMaximumSize(new Dimension(400, 50));
		
		JPanel startDatePanel = new JPanel();
		startDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		startDatePanel.setMaximumSize(new Dimension(500, 55));
		
		JLabel startDate = new JLabel("Select Start Date: ");
		startDate.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 17));
		//startDate.setForeground(Color.white);
		startDate.setBackground(Color.black);
		
		UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year"); 
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		startDatePanel.add(startDate);
		startDatePanel.add(datePicker);
		
		datePanels.add(startDatePanel);	
		
		
		return datePanels;
	}
	
	private JPanel createCenterPanel() {
	
		JPanel center_panel = new JPanel(new BorderLayout());
		center_panel.setBackground(Color.black);
		
		JPanel datePanel = createDatePickers();
		
		center_panel.add(datePanel);
		
		return center_panel;		
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
					
				JMenuBar title_panel = createTitlePanel(pr_frame);
				JPanel center_panel = createCenterPanel();
				
				/*
				 * populates master panel 
				 */
				masterPanel.add(title_panel, BorderLayout.PAGE_START); 
				masterPanel.add(center_panel, BorderLayout.CENTER);
				//masterPanel.add(bottomButtons, BorderLayout.PAGE_END);
			
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