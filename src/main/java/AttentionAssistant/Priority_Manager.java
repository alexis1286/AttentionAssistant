package AttentionAssistant;

import java.awt.Component;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JFrame;

public class Priority_Manager {

	/**
	 * A list of tasks that are to be displayed in the Priority_Manager
	 */
	private ArrayList<Task> Task_list;

	/**
	 * What is this?
	 */
	private int id = 100;

	public void open_pm() {
		JFrame pm_frame = new JFrame("AttentionAssistant Priotity Manager");
		pm_frame.setAlwaysOnTop(true);
		pm_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pm_frame.setVisible(true);
	}
	
	/**
	 * Instantiating empty Priority_Manager object
	 */
	public Priority_Manager() {
		this.Task_list = new ArrayList<Task>();
	}

	/**
	 * Create a class Priority Manager with a specified
	 * Task_list
	 * @param ArrayList<Task>();
	 */
	public Priority_Manager(ArrayList<Task> task_List) {
		this.Task_list = task_List;
	}
	
	
	/**
	 * Grabs all Tasks from the DataBase and stores into this.Task_list
	 * Once we get User class up and running param will be an int
	 * so that it is only grabbing from a certain user
	 */
	public void Get_To_List_DB_Setup() {
	DataBase db = new DataBase();
	db.DatabaseSetUp();
	this.Task_list = db.SelectAllTasks();
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
	private void delTask(Task task) {
		Task_list.remove(task);
	}

	/**
	 * Display task list
	 * @param
	 */
	public void printList() {
		String list = Task_list.toString();
		System.out.println(list);
	}

	/**
	 * Sorting tasks by? What is this doing?
	 * 
	 * Sort Tasks
	 * @param 
	 */
	private void sortTasks(ArrayList<Task> Task_list) {

		ArrayList<Task> NonPriorList = new ArrayList<Task>();

		for (int i = 0; i < Task_list.size(); i++) {
			boolean check = Task_list.get(i).getPriority();
			if (check = false) {
				NonPriorList.add(Task_list.get(i));
			}
		}
		
		Collections.sort(Task_list);
		Collections.sort(NonPriorList);

		for (int i = 0; i < NonPriorList.size(); i++) {
			Task_list.addAll(NonPriorList);
		}
	}
	
	
}