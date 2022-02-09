package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class Priority_Manager {
	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	JTable table;
	int selectedTask;
	int height = 700;
	int width = 550;
	
	private ArrayList<Task> Task_List;
	private Task working_task;
	
	
	public Priority_Manager(Observer observe) {
		this.Task_List = new ArrayList<Task>();
		this.working_task = taskToObserve();
		
		if(this.working_task != null) {
			observe.monitor(working_task);
		}
	}
	
	private Task taskToObserve() {
		Task task = new Task();
		//if active task is stored
		//set task = to active task
		
		
		return task;
	}
	
	
	public void open_pm(DataBase db,Observer observer) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				//set up frame
				JFrame frame = new JFrame();
				frame.setUndecorated(true);
				//sets window width and height
				frame.setPreferredSize(new Dimension(width, height));
				
				//build title panel
				JPanel titlePanel = titlePanel(frame);
				titlePanel.setBorder(line);
				
				//build table panel
				JPanel taskPanel = taskPanel(db,frame);
				taskPanel.setBorder(BorderFactory.createMatteBorder(0,2,2,2,aa_purple));
				
				//build button panel
				JPanel buttonPanel = buttonPanel(frame);
				buttonPanel.setBorder(BorderFactory.createMatteBorder(0,2,2,2,aa_purple));
				
				frame.getContentPane().add(titlePanel,BorderLayout.PAGE_START);
				frame.getContentPane().add(taskPanel,BorderLayout.CENTER);
				frame.getContentPane().add(buttonPanel,BorderLayout.PAGE_END);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(true);
				frame.setLocationRelativeTo(null);
			}
		});
	}
	
	//******************************************************************************************************************
	private JPanel buttonPanel(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//calendar integration button
		//make cancel button, closes task window without adding
		JButton integration = new JButton("  calendar integration  ");
		integration.setBackground(aa_grey);
		integration.setForeground(Color.white);
		integration.setFocusPainted(false);
		integration.setBorder(new LineBorder(Color.black,10,false));
		integration.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		integration.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//initiate calendar integration
        		
        }});

		//close button
		//make cancel button, closes task window without adding
		JButton close = new JButton("  close  ");
		close.setBackground(aa_grey);
		close.setForeground(Color.white);
		close.setFocusPainted(false);
		close.setBorder(new LineBorder(Color.black,10,false));
		close.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		close.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//close window without saving info
        		frame.dispose();
        }});
		
		panel.setBackground(Color.black);
		panel.add(integration);
		panel.add(close);
		return panel;
	}
	
	//*****************************************************************************************************************
	/*
	 * create panel that contains task list and buttons to edit/interact with task list
	 */
	private JPanel taskPanel(DataBase db,JFrame frame) {
		JPanel panel = new JPanel();
		
		/*
		 * populate task list from database
		 */
		Task_List.removeAll(Task_List);
		for(int i=0; i<db.SelectAllTasks().size();i++) {
			System.out.println(db.SelectAllTasks().get(i));
			Task_List.add(db.SelectAllTasks().get(i));
		}
		
		/*
		 * Create JTable to display task
		 */
		DefaultTableModel model = new DefaultTableModel(Task_List.size(),0);
		//create table model and add columns
		table = new JTable(model);
		model.addColumn("Task");
		model.addColumn("Description");
		model.addColumn("Priority");
		model.addColumn("Due Date");
		model.addColumn("Observable");
		
		//set table visuals
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createEmptyBorder());
        table.getTableHeader().setBackground(aa_grey);
        table.getTableHeader().setForeground(Color.white);
        table.setGridColor(aa_purple);
        table.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
        
        //iterates through each task in list
        for(int i=0;i<Task_List.size();i++) {
        	//iterate through table columns
        	for(int j=0;j<model.getColumnCount();j++) {
        		//set data for each column from the current task
        		if(j==0) {
        			table.setValueAt(Task_List.get(i).getTaskName(),i,j);
        		}else if(j==1){
        			table.setValueAt(Task_List.get(i).getDescription(),i,j);
        		}else if(j==2){
        			//change to show icon*********************************************
        			table.setValueAt(Task_List.get(i).getPriority(),i,j);
        		}else if(j==3) {
        			table.setValueAt(Task_List.get(i).getDueDate(),i,j);
        		}else if(j==4) {
        			//change to show icon*********************************************
        			table.setValueAt(Task_List.get(i).getObservable(),i,j);
        		}
        	}
        }
        
        //set table background color, font color, and border
        table.setBackground(Color.black);
        table.setForeground(Color.white);
		table.setBorder(null);
		
		/*
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
        		deleteTask(db,model,table);
        }});
		
		/*
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
        		addTask(db,model,table);
        }});
		
		
		/*
		 * Edit task
		 */
		Icon edit_icon;
		edit_icon = new ImageIcon("images/edit.png");
		JButton edit_button = new JButton(edit_icon);
		edit_button.setContentAreaFilled(false);
		edit_button.setBorderPainted(false);
		edit_button.setFocusPainted(false);
		edit_button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editTask(db,model,table);
        }});
		
		
		/*
		 * Edit task
		 */
		Icon calendar_icon;
		calendar_icon = new ImageIcon("images/calendar.png");
		JButton calendar_button = new JButton(calendar_icon);
		calendar_button.setContentAreaFilled(false);
		calendar_button.setBorderPainted(false);
		calendar_button.setFocusPainted(false);
		calendar_button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editTask(db,model,table);
        }});
		
		
		//puts table in scrollable panel
		JScrollPane tpane = new JScrollPane(table);
		tpane.setBackground(Color.black);
		
		Border empty = new EmptyBorder(0,0,0,0);
		tpane.setBorder(empty);
		//sets dimensions for table panel
		tpane.setPreferredSize(new Dimension(300,400));
		//create button pane
		JPanel bpane = new JPanel();
		bpane.setBackground(Color.black);
		//set layout so buttons display across x-axis
		bpane.setLayout(new BoxLayout(bpane,BoxLayout.X_AXIS));
		
		//add calendar button to bpane
		bpane.add(calendar_button);
		//add delete button to bpane
		bpane.add(delete_button);
		//add check button to bpane
		bpane.add(check_button);
		//add add button to bpane
		bpane.add(add_button);
		//add edit button to bpane
		bpane.add(edit_button);
		
		//set layout of panel so components display vertically
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		//add task table panel to panel
		panel.add(tpane);
		//add button panel to panel
		panel.add(bpane);
		panel.setBackground(Color.black);
		return panel;
	}
	
	//*****************************************************************************************************************
	/*
	 * Delete task
	 */
	private void deleteTask(DataBase db,DefaultTableModel model,JTable table) {
		//deletes task from database
		db.DeleteTask(Task_List.get(table.getSelectedRow()).getTaskID());
		//deletes task from table
		model.removeRow(table.getSelectedRow());
		Task_List.remove(table.getSelectedRow());
		//gets table to display changes
		table.revalidate();
	}
	
	//*****************************************************************************************************************
	/*
	 * Edit task
	 */
	private void editTask(DataBase db,DefaultTableModel model,JTable table) {
		//get task info and pass it to the task window
		Task task = Task_List.get(table.getSelectedRow());
		db.UpdateTask(task);
		boolean isAnEdit = true;
		taskWindow(task,isAnEdit,db,model,table);
		db.DeleteTask(task.getTaskID());
		Task_List.remove(task.getTaskID());
		model.removeRow(table.getSelectedRow());
		table.revalidate();
	}
	
	/*
	 * Add Task
	 */
	private void addTask(DataBase db,DefaultTableModel model,JTable table) {
		Task task = new Task();
		boolean isAnEdit = false;
		taskWindow(task,isAnEdit,db,model,table);
	}
	
	//******************************************************************************************************************
	/*
	 * Task Window
	 * @param Description, Observable, Status
	 * @return task
	 */
	private void taskWindow(Task task,boolean isAnEdit,DataBase database,DefaultTableModel model,JTable table) {
		//create task window
		JFrame task_window = new JFrame("Add Task");
		//pin to top of screen
		task_window.setAlwaysOnTop(true);
		//set window background to black
		task_window.setBackground(Color.black);
		//remove default title bar
		task_window.setUndecorated(true);
		task_window.setVisible(true);
		
		//makes custom title panel
		JPanel title_panel = titlePanel(task_window);
		
		//creates panel for task information form
		JPanel tpane = new JPanel();
		tpane.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, aa_purple));
		JPanel buttons = new JPanel();
		buttons.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, aa_purple));
				
		//sets up grid to line up labels with text areas
		GridLayout grid = new GridLayout(0,2);
		tpane.setLayout(grid);
		
		//sets up grid for check boxes
		GridLayout grid2 = new GridLayout(0,3);
		buttons.setLayout(grid2);
		
		//creates label for task name input
		JLabel n = new JLabel("   Task: ");
		n.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		n.setForeground(aa_purple);
		
		//creates text area for name input
		JTextArea name = new JTextArea(task.getTaskName());
		name.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		name.setBorder(new LineBorder(Color.black,5,false));
		
		//creates label for description input
		JLabel d = new JLabel("   Descrition: (key words, separated by commas (,))");
		d.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		d.setForeground(aa_purple);
		
		//creates text area for description input
		JTextArea descrpt = new JTextArea(task.getDescription());
		descrpt.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		descrpt.setBorder(new LineBorder(Color.black,5,false));
		
		//creates label for date input
		JLabel dd = new JLabel("   Due Date: (mm/dd/yyyy)");
		dd.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		dd.setForeground(aa_purple);
		
		//creates text area for date input
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		String stringDate;
		if(isAnEdit == true) {
			stringDate = f.format(task.getDueDate());
		}else {stringDate = "";}
		JTextArea date = new JTextArea(stringDate);
		date.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		date.setBorder(new LineBorder(Color.black,5,false));
		
		//create check box for if task is to be observed
		JCheckBox observe = new JCheckBox("observable");
		observe.setSelected(task.getObservable());
		observe.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		observe.setForeground(aa_purple);
		observe.setContentAreaFilled(false);
		observe.setFocusPainted(false);
		
		//create check box for if task is a priority task
		JCheckBox priority = new JCheckBox("priority");
		priority.setSelected(task.getPriority());
		priority.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		priority.setForeground(aa_purple);
		priority.setContentAreaFilled(false);
		priority.setFocusPainted(false);
		
		//create check box for if task is complete
		JCheckBox status = new JCheckBox("complete");
		if(task.getStatus() == TaskStatus.CLOSED) {
			status.setSelected(true);
		}else {status.setSelected(false);}
		
		status.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		status.setForeground(aa_purple);
		status.setContentAreaFilled(false);
		status.setFocusPainted(false);
		
		Task new_task = new Task();
		//creates save button, adds task to database and table
		JButton save = new JButton("add");
		save.setBackground(aa_grey);
		save.setForeground(Color.white);
		save.setFocusPainted(false);
		save.setBorder(new LineBorder(Color.black, 3, true));
		save.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//create task from entered info
        		String n = name.getText();
        		new_task.setTaskName(n);
        		String d = descrpt.getText();
        		new_task.setDescription(d);
        		String dd = date.getText();
        		try {
					Date due = new SimpleDateFormat("dd/MM/yyyy").parse(dd);
					new_task.setDueDate(due);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		if(observe.getSelectedObjects() != null) {
        			new_task.setObservable(true);
        		}else {new_task.setObservable(false);}
        		if(priority.getSelectedObjects()!=null) {
        			new_task.setPriority(true);
        		}else {new_task.setPriority(false);}
        		if(status.getSelectedObjects()!=null) {
        			new_task.setStatus(TaskStatus.CLOSED);
        		}else {new_task.setStatus(TaskStatus.OPEN);}
        		
        		//adds task to database
        		database.AddTask(new_task);
        		//creates object v populated with the new tasks details
        		Vector<Object> v = new Vector<Object>();
        		v.add(new_task.getTaskName());
        		v.add(new_task.getDescription());
        		v.add(new_task.getPriority());
        		v.add(new_task.getDueDate());
        		v.add(new_task.getObservable());
        		//add row and populate it with object v
        		model.addRow(v);
        		//gets table to display changes
        		table.revalidate();
        		task_window.dispose();
        }});
		
		//make cancel button, closes task window without adding
		JButton cancel = new JButton("cancel");
		cancel.setBackground(aa_grey);
		cancel.setForeground(Color.white);
		cancel.setFocusPainted(false);
		cancel.setBorder(new LineBorder(Color.black,3,true));
		cancel.setFont(new Font ("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		cancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//close window without saving info
        		if(isAnEdit == true) {
        			database.AddTask(task);
        			Vector<Object> v = new Vector<Object>();
            		v.add(task.getTaskName());
            		v.add(task.getDescription());
            		v.add(task.getPriority());
            		v.add(task.getDueDate());
            		v.add(task.getObservable());
            		//add row and populate it with object v
            		model.addRow(v);
            		//gets table to display changes
            		table.revalidate();
        		}
        		task_window.dispose();
        }});
		
		//create blank button for spacing (visual only)
		JButton blank = new JButton();
		blank.setContentAreaFilled(false);
		blank.setFocusPainted(false);
		blank.setBorderPainted(false);
		
		//add components to task information panel
		tpane.add(n);
		tpane.add(name);
		tpane.add(d);
		tpane.add(descrpt);
		tpane.add(dd);
		tpane.add(date);
		//add components to button panel
		buttons.add(observe);
		buttons.add(priority);
		buttons.add(status);
		buttons.add(blank);
		buttons.add(save);
		buttons.add(cancel);
		
		//sets location and dimensions of task window
		int x = (int) ((screen.getWidth() - task_window.getWidth()) /2);
		int y = (int) ((screen.getHeight() - task_window.getHeight()) /2);
		task_window.setLocation(x, y);
		
		//sets background of panels
		tpane.setBackground(Color.black);
		buttons.setBackground(Color.black);
		buttons.setForeground(Color.white);
		
		//add title panel, task information panel, and button panel to window
		task_window.add(title_panel, BorderLayout.PAGE_START);
		task_window.add(tpane, BorderLayout.CENTER);
		task_window.add(buttons, BorderLayout.PAGE_END);
		task_window.pack();
	}
	
	
	//****************************************************************************************************************
	/*creates title panel with close and guide buttons
	 * 
	 */
	private JPanel titlePanel(JFrame frame) {
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
	
	//***************************************************************************************************************
	//FUNCTIONALITY TO ADD
	//***************************************************************************************************************
	/**
	 * Sort Tasks
	 * @param
	 */
	private void sortTasks (){
		//observable then non
		//by priority
		//by date
		
		
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
	 * function to import calendar
	 */
	public void importCalendar() {
		
	}
	
	/*
	 * function that generates/displays user tasks in calendar view 
	 */
	public void userCalendar() {
		
	}
}