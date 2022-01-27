package AttentionAssistant;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;


public class Priority_Manager {

private ArrayList<Task> Task_list = new ArrayList<Task> ();

private int id = 100;

	/**
	 * Add Task
	 * @param Description, Observable, Status
	 * @return task
	 */
	private void addTask {
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
	public printList{
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
	public void export{
		//Code to Implement
	}