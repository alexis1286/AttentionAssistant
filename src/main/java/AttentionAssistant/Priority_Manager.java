package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Priority_Manager {
	
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	
	JFrame pm_frame = new JFrame("AttentionAssistant Priotity Manager");
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); 
	
	int height = 700;
	int width = 550;

	private ArrayList<Task> Task_list = new ArrayList<Task> ();

	private int id = 100;
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
	/**
	 * Add Task
	 * @param Description, Observable, Status
	 * @return task
	 */
	private void addTask() {
		JDialog task_window = new JDialog(pm_frame, "Add Task");
		
		task_window.setAlwaysOnTop(true);
		task_window.setBackground(Color.black);
		task_window.setUndecorated(true);
		task_window.setVisible(true);
		
		JMenuBar title_panel = new JMenuBar();
		title_panel.setBorder(line);
		//aligns buttons in title panel from right -> left
		title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//makes title panel background grey
		title_panel.setBackground(aa_grey);
		//creates border and sets to purple
		title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
		//creates label 
		JLabel title = new JLabel("Add Task                                                                                   ");
		//makes font color white
		title.setForeground(Color.white);
		//sets font, size, and bold
		title.setFont(new Font("Serif", Font.BOLD, 14));
		
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
        		task_window.dispose();
        }});
		
		Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon guideIcon = new ImageIcon(g_img);
		JButton guide = new JButton(guideIcon);
		guide.setBorderPainted(false);
		guide.setContentAreaFilled(false);
		guide.setFocusPainted(false);
		
		
		
		title_panel.add(title);
		title_panel.add(guide);
		title_panel.add(close_window);
		
		//end of title_pane set up*************
		
		JPanel tpane = new JPanel();
		tpane.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, aa_purple));
		JPanel buttons = new JPanel();
		buttons.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, aa_purple));
				
		GridLayout grid = new GridLayout(0,2);
		tpane.setLayout(grid);
		
		GridLayout grid2 = new GridLayout(0,3);
		buttons.setLayout(grid2);
		
		JLabel n = new JLabel("   Task: ");
		n.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		n.setForeground(aa_purple);
		JTextArea name = new JTextArea();
		name.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		name.setBorder(new LineBorder(Color.black,5,false));
		
		
		JLabel d = new JLabel("   Descrition: ");
		d.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		d.setForeground(aa_purple);
		JTextArea descrpt = new JTextArea("key words, separated by commas(,)");
		descrpt.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		descrpt.setBorder(new LineBorder(Color.black,5,false));
		
		
		JLabel dd = new JLabel("   Due Date: ");
		dd.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		dd.setForeground(aa_purple);
		JTextArea date = new JTextArea("mm/dd/yy");
		date.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		date.setBorder(new LineBorder(Color.black,5,false));
		
		
		JCheckBox observe = new JCheckBox("observable");
		observe.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		observe.setForeground(aa_purple);
		observe.setContentAreaFilled(false);
		observe.setFocusPainted(false);
		JCheckBox priority = new JCheckBox("priority");
		priority.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		priority.setForeground(aa_purple);
		priority.setContentAreaFilled(false);
		priority.setFocusPainted(false);
		JCheckBox status = new JCheckBox("complete");
		status.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		status.setForeground(aa_purple);
		status.setContentAreaFilled(false);
		status.setFocusPainted(false);
		
		Task new_task = new Task();
		
		JButton save = new JButton("add");
		save.setBackground(aa_grey);
		save.setForeground(Color.white);
		save.setFocusPainted(false);
		save.setBorder(new LineBorder(Color.black, 3, true));
		save.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 14));
		save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//create task from entered info
        		new_task.setTaskID(id);
        		String n = name.getText();
        		new_task.setTaskName(n);
        		String d = descrpt.getText();
        		new_task.setDescription(d);
        		//set up date 
        		if(observe.getSelectedObjects() != null) {
        			new_task.setObservable(true);
        		}else {new_task.setObservable(false);}
        		if(priority.getSelectedObjects()!=null) {
        			new_task.setPriority(true);
        		}else {new_task.setPriority(false);}
        		if(status.getSelectedObjects()!=null) {
        			new_task.setStatus(true);
        		}else {new_task.setStatus(false);}
        }});
		
		JButton cancel = new JButton("cancel");
		cancel.setBackground(aa_grey);
		cancel.setForeground(Color.white);
		cancel.setFocusPainted(false);
		cancel.setBorder(new LineBorder(Color.black,3,true));
		cancel.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 14));
		cancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//close window without saving info
        		task_window.dispose();
        }});
		
		JButton blank = new JButton();
		blank.setContentAreaFilled(false);
		blank.setFocusPainted(false);
		blank.setBorderPainted(false);
		
		tpane.add(n);
		tpane.add(name);
		tpane.add(d);
		tpane.add(descrpt);
		tpane.add(dd);
		tpane.add(date);
		buttons.add(observe);
		buttons.add(priority);
		buttons.add(status);
		buttons.add(blank);
		buttons.add(save);
		buttons.add(cancel);
		
		int x = (int) ((screen.getWidth() - task_window.getWidth()) /2);
		int y = (int) ((screen.getHeight() - task_window.getHeight()) /2);
		task_window.setLocation(x, y);
		
		tpane.setBackground(Color.black);
		buttons.setBackground(Color.black);
		buttons.setForeground(Color.white);
		
		task_window.add(title_panel, BorderLayout.PAGE_START);
		task_window.add(tpane, BorderLayout.CENTER);
		task_window.add(buttons, BorderLayout.PAGE_END);
		task_window.pack();
		
		Task_list.add(new_task);
		System.out.println(Task_list);
		id++;
	}
	
	/**
	 * Delete Task
	 * @param Task
	 */
	private void delTask (Task task) {
		Task_list.remove(task);
	}
	
	/**
	 * Display task list
	 * @param
	 */
	public void printList(){
		String list = Task_list.toString();
		System.out.println(list);
		}
	
	/**
	 * Sort Tasks
	 * @param
	 */
	private void sortTasks (ArrayList<Task> Task_list){
		
		//Task_list.sort(priority);
		
		//Task_list.sort(dueDate);
		
		//Task_list.set(index, task);
	}
	
	/**
	 * Export Task List to Parent Portal
	 * @param
	 */
	public void export(){
		//Code to Implement
	}
	
	/**
	 * create JTable to display tasks
	 */
	private JScrollPane  display_tasks() {
		DefaultTableModel model = new DefaultTableModel();
				
		JTable table = new JTable(model);
		model.addColumn("Task");
		model.addColumn("Description");
		model.addColumn("Priority");
		model.addColumn("Due Date");
		model.addColumn("Observable");
		
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.getTableHeader().setBackground(aa_grey);
        table.getTableHeader().setForeground(Color.white);
        
        for(int i=0;i<Task_list.size();i++) {
        	for(int j=0;j<model.getColumnCount();j++) {
        		Object[] objs = null;
        		if(j==0) {
        			objs = new Object[] {Task_list.get(i).getName()};
        		}else if(j==1){
        			objs = new Object[] {Task_list.get(i).getDescription()};
        		}else if(j==2){
        			objs = new Object[] {Task_list.get(i).getPriority()};
        		}else if(j==3) {
        			objs = new Object[] {Task_list.get(i).getDate()};
        		}else if(j==4) {
        			objs = new Object[] {Task_list.get(i).getObservable()};
        		}
        		table.setValueAt(objs, i, j);
        	}
        	Object[] objs = new Object[] {Task_list.get(i).getName(), Task_list.get(i).getDescription(), Task_list.get(i).getPriority(), Task_list.get(i).getDate(), Task_list.get(i).getObservable()};
        	table.setValueAt(objs, i, 0);
        }
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        //table.add(scrollPane);
        table.setBackground(aa_grey);
        table.setForeground(Color.white);
        table.setSize(width-50, 300);
        table.setBorder(line);
        scrollPane.setBorder(new LineBorder(aa_purple, 1, true));
        return scrollPane;
	}
    
	
	/**
	 * creates/displays UI
	 */
	public void open_pm() {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				//gets rid of normal title bar
				pm_frame.setUndecorated(true);
				//sets window width and height
				pm_frame.setPreferredSize(new Dimension(width, height));
				//creates panel for custom title bar
				JMenuBar title_panel = new JMenuBar();
				title_panel.setBorder(line);
				//aligns buttons in title panel from right -> left
				title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT)); //****************************************************************
				//creates panel for buttons
				JPanel buttons_panel = new JPanel();
				buttons_panel.setBorder(line);
				buttons_panel.setOpaque(true);
				//buttons_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
				
				//makes title panel background grey
				title_panel.setBackground(aa_grey);
				//creates border and sets to purple
				title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
				//creates label 
				JLabel title = new JLabel("Priority Manager                                                                                         ");
				//makes font color white
				title.setForeground(Color.white);
				//sets font, size, and bold
				title.setFont(new Font("Serif", Font.BOLD, 14));
				
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
		        		pm_frame.dispose();
		        }});
				
				Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
				Icon guideIcon = new ImageIcon(g_img);
				JButton guide = new JButton(guideIcon);
				guide.setBorderPainted(false);
				guide.setContentAreaFilled(false);
				guide.setFocusPainted(false);
				
				
				
				title_panel.add(title);
				title_panel.add(guide);
				title_panel.add(close_window);
				
				//display task list from database*******************************************************************************
				JScrollPane task_display = display_tasks();
				
				
				/**
				 * creates button and calls function for deleting a task upon click
				 */
				Icon delete_icon;
				delete_icon = new ImageIcon("images/minus.png");
				JButton delete_button = new JButton(delete_icon);
				delete_button.setContentAreaFilled(false);
		        delete_button.setBorderPainted(false);
		        delete_button.setFocusPainted(false);
				delete_button.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//delTask();
		        }});
				
				/**
				 * creates button and calls function for marking a task as complete upon click
				 */
				Icon check_icon;
				check_icon = new ImageIcon("images/check.png");
				JButton check_button = new JButton(check_icon);
				check_button.setContentAreaFilled(false);
		        check_button.setBorderPainted(false);
		        check_button.setFocusPainted(false);
				check_button.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//markComplete();
		        }});
				
				/**
				 * creates button and call function for adding a task upon click
				 */
				Icon add_icon;
				add_icon = new ImageIcon("images/plus.png");
				JButton add_button = new JButton(add_icon);
				add_button.setContentAreaFilled(false);
				add_button.setBorderPainted(false);
				add_button.setFocusPainted(false);
				add_button.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		addTask();
		        }});
				
				/**
				 * creates button and call function to import calendar
				 */
				
				/**
				 * creates button to close pm
				 */
				
				
				/**
				 * create custom title bar with close & guide link
				 */
				
				
				/**
				 * adds task list and buttons to frame
				 */
				JPanel list_panel = new JPanel();
				list_panel.setBorder(line);
				list_panel.add(task_display);
				buttons_panel.add(delete_button);
				buttons_panel.add(check_button);
				buttons_panel.add(add_button);
				buttons_panel.setBackground(Color.black);
				list_panel.setBackground(Color.black);
				pm_frame.getContentPane().add(title_panel, BorderLayout.PAGE_START);
				pm_frame.getContentPane().add(list_panel, BorderLayout.CENTER);
				pm_frame.getContentPane().add(buttons_panel, BorderLayout.PAGE_END);
				pm_frame.getContentPane().setBackground(Color.black);
				pm_frame.pack();
				pm_frame.setAlwaysOnTop(true);
				pm_frame.setVisible(true);
				pm_frame.setResizable(true);
				pm_frame.setLocationRelativeTo(null);
			}
		});
	}
}