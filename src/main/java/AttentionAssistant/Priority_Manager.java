package AttentionAssistant;

import java.awt.Component;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JFrame;

public class Priority_Manager {

	private ArrayList<Task> Task_list = new ArrayList<Task>();

	private int id = 100;

	public void open_pm() {
		JFrame pm_frame = new JFrame("AttentionAssistant Priotity Manager");
		pm_frame.setAlwaysOnTop(true);
		pm_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pm_frame.setVisible(true);
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