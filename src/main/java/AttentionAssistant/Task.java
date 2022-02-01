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
 * 
 * Keep this as TaskStatus, Status breaks the database
 */

enum TaskStatus {
	OPEN, CLOSED
}


//public class Task {

public class Task implements Comparable<Task>{
	/** Variables */
	private int taskID;
	private boolean priority;
	private String name;
	private String description;
	private Date dueDate;
	
	
	/**
	 * False if not observable, True if observable
	 * Observable tasks will be monitored by the Observer
	 * Non-observerable tasks will not be monitored by the Observer
	 */
	private boolean observable;
	
	
	private TaskStatus status;
	
	/**
	 * Instantiating empty Task object
	 */
	public Task() {
		this.taskID= 0;
		this.description = "";
		this.observable = false;
		this.status = TaskStatus.CLOSED;
		this.name = null; 
		this.dueDate = null;
		this.priority = false;
	}
	
	/**
	 * Create a class Task with a specified
	 * taskID, description, whether observable, status 
	 * @param int, String, boolean, Status, priority, dueDate, name
	 */
	public Task(int taskID, String description, boolean observable, TaskStatus status, String name, Date dueDate, boolean priority) {
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
		
	/**
	 * Start of Encapsulation
	 * 
	 * Get taskID
	 * @return int
	 */
	public int getTaskID() {
		return this.taskID;
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
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	/**
	 * Get DueDate
	 * @return dueDate
	 */
	public Date getDueDate() {
		return this.dueDate;
	}
	
	/**
	 * Set DueDate
	 * @param Date
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * Get Name
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set Name
	 * @param String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get Priority Status
	 * @return priority
	 */
	public boolean getPriority () {
		return this.priority;
	}
	
	/**
	 * Set DueDate???? Shouldn't this be setPriorty? if this is different I'm leaving it here to not delete others work.
	 * 
	 * @param boolean
	 */
	public void setdueDate(boolean priority) {
		this.priority = priority;
	}
	
	/**
	 * Set Priority
	 * 
	 * @param boolean
	 */
	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	/**
	 * What is this doing???
	 * @param Task
	 * 
	 * If wanting to use the compareTo for dates, call it using: task0.getDueDate().compareTo(t1.getDueDate());
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(Task t1) {
		return getDueDate().compareTo(t1.getDueDate());
	}
	
	/**
	 * Where did toString function go?
	 * 	@Override
	 * Display Task
	 * @return String
	 * public String toString() {
	 *	String taskString= new String();
	 *	taskString = "Task ID= " + this.taskID +
	 *			" Priority= " + Boolean.toString(this.priority) +
	 *			" Name= " + this.name +
	 *			" Description= " + this.description +
	 *			" Due Date= " + this.dueDate.toString() +
	 *			" Observable= " + Boolean.toString(this.observable) +
	 *			" Status= " + this.status.toString();
	 *			
	 *	return taskString;
	 *	
	 *}
	 */
}