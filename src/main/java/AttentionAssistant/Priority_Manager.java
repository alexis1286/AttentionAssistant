package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.Border;


public class Priority_Manager {

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
		id++;
		
		Task new_task = new Task();
		Task_list.add(new_task);
		System.out.println(Task_list);
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
	 * creates/displays UI
	 */
	public void open_pm() {
		Color aa_grey = new Color(51,51,51);
		Color aa_purple = new Color(137,31,191);
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame pm_frame = new JFrame("AttentionAssistant Priotity Manager");
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int height = screenSize.height * 1 / 2;
				int width = screenSize.width * 1 / 2;
				pm_frame.setPreferredSize(new Dimension(width, height));
				pm_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}catch(Exception e) {
					e.printStackTrace();
				}
				JPanel panel = new JPanel();
				panel.setOpaque(true);
				
				//display task list from database
				JLabel task_display = new JLabel("Display task list here");
				task_display.setBackground(aa_grey);;
				JScrollPane sp = new JScrollPane(task_display);
				
				
				/**
				 * creates button and calls function for deleting a task upon click
				 */
				Icon delete_icon;
				delete_icon = new ImageIcon("images/minus.png");
				JButton delete_button = new JButton(delete_icon);
				delete_button.setContentAreaFilled(false);
		        delete_button.setBorderPainted(false);
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
				add_button.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		//addTask();
		        }});
				
				/**
				 * adds task list and buttons to frame
				 */
				JPanel list_panel = new JPanel();
				list_panel.add(sp);
				panel.add(delete_button);
				panel.add(check_button);
				panel.add(add_button);
				panel.setBackground(Color.black);
				list_panel.setBackground(Color.black);
				pm_frame.getContentPane().add(list_panel, BorderLayout.PAGE_START);
				pm_frame.getContentPane().add(panel, BorderLayout.PAGE_END);
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