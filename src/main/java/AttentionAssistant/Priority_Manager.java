package AttentionAssistant;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Priority_Manager {

	private ArrayList<Task> Task_list = new ArrayList<Task> ();

	private int id = 100;
	
	public static void open_pm() {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame pm_frame = new JFrame("AttentionAssistant Priotity Manager");
				pm_frame.setLocationRelativeTo(null);
				pm_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				}catch(Exception e) {
					e.printStackTrace();
				}
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setOpaque(true);
				
				
				
				pm_frame.getContentPane().add(panel, BorderLayout.CENTER);
				pm_frame.pack();
				pm_frame.setAlwaysOnTop(true);
				pm_frame.setVisible(true);
				pm_frame.setResizable(true);
			}
		});
	}
	
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
}
