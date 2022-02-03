/**
 * Class that contains information on task created by 
 * the user through the priority manager
 */
package AttentionAssistant;

import java.util.Date;

/**
 * Options are 'OPEN' and 'CLOSED'
 * OPEN tasks were not yet completed by the User
 * CLOSED tasks were completed by the User
 */

enum TaskStatus {
	OPEN, CLOSED, OVERDUE;
}


public class Task {
	/** Variables */
	private int taskID;
	private boolean priority;
	private String name;
	private String description;
	private Date dueDate;
	private TaskStatus status;
	
	
	/**
	 * False if not observable, True if observable
	 * Observable tasks will be monitored by the Observer
	 * Non-observable tasks will not be monitored by the Observer
	 */
	private boolean observable;
	
	//private Status status;
	
	/**
	 * Instantiating empty Task object
	 */
	public Task() {
		this.taskID= 0;
		this.description = "";
		this.observable = false;
		this.status = TaskStatus.OPEN;
		this.name = null; 
		this.dueDate = null;
		this.priority = false;
	}
	
	/**
	 * Create a class Task with a specified
	 * taskID, description, whether observable, status
	 * @param int, String, boolean, Status
	 */
	public Task(int taskID, String description, boolean observable, TaskStatus status, String name, Date dueDate, Boolean priority) {
		this.taskID = taskID;
		this.description = description;
		this.observable = observable;
		this.status = status;
		this.name = name; 
		this.dueDate = dueDate;
		this.priority = priority;
	}
	
	/**
	 * Instantiating copy constructor for Task object
	 */
	public Task(Task task) {
		this.taskID= task.taskID;
		this.description = task.description;
		this.observable = task.observable;
		this.status = task.status;
		this.name = task.name; 
		this.dueDate = task.dueDate;
		this.priority = task.priority;
	}
	
	
	public String getTaskName() {
		return this.name;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get taskID
	 * @return int
	 */
	public int getTaskID() {
		return this.taskID;
	}
	
	public void setTaskName(String n) {
		this.name = n;
	}
	
	public void setPriority(Boolean isPriority) {
		this.priority = isPriority;
	}
	
	public boolean getPriority() {
		return this.priority;
	}
	
	public void setDueDate(Date due) {
		this.dueDate = due;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	/**
	 * User should not be able to set the taskID this should be done automatically through the database
	 * comment out once database is working.
	 * 
	 * Set taskID
	 * @param int
	 */
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	/**
	 * Get description
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Set description
	 * @param String
	 */
	public void setDescription(String description) {
		this.description= description;
	}
	
	/**
	 * Get Observable
	 * @return boolean
	 */
	public boolean getObservable() {
		return this.observable;
	}
	
	/**
	 * Set Observable
	 * @param boolean
	 */
	public void setObservable(boolean observable) {
		this.observable = observable;
	}
	
	/**
	 * Get Status
	 * @return String?
	 */
	public TaskStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Set Status
	 * @param Status?
	 */
	public void setStatus(TaskStatus s) {
		this.status = s;
	}
	
	 /** 
	   * Display Task
	   * @return String
	   */
	@Override
	public String toString() {
	 	String taskString= new String();
	 	taskString = "Task ID= " + this.taskID +
	 			" Priority= " + Boolean.toString(this.priority) +
	 			" Name= " + this.name +
	 			" Description= " + this.description +
	 			" Due Date= " + this.dueDate.toString() +
	 			" Observable= " + Boolean.toString(this.observable) +
	 			" Status= " + this.status.toString();
	 			
	 	return taskString;
	 	
	 }
}
