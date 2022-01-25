/**
 * Class that contains information on task created by 
 * the user through the priority manager
 */
package AttentionAssistant;

/**
 * Options are 'OPEN' and 'CLOSED'
 * OPEN tasks were not yet completed by the User
 * CLOSED tasks were completed by the User
 */

enum Status {
	OPEN, CLOSED
}

public class Task {
	/** Variables */
	private int taskID;
	private String description;
	
	/**
	 * False if not observable, True if observable
	 * Observable tasks will be monitored by the Observer
	 * Non-observerable tasks will not be monitored by the Observer
	 */
	private boolean observable;
	
	private Status status;
	
	/**
	 * Instantiating empty Task object
	 */
	public Task() {
		this.taskID= 0;
		this.description = "";
		this.observable = false;
		this.status = Status.CLOSED;
	}
	
	/**
	 * Create a class Task with a specified
	 * taskID, description, whether observable, status
	 * @param int, String, boolean, Status
	 */
	public Task(int taskID, String description, boolean observable, Status status) {
		this.taskID = taskID;
		this.description = description;
		this.observable = observable;
		this.status = status;
	}
	
	/**
	 * Instantiating copy constructor for Task object
	 */
	public Task(Task task) {
		this.taskID= task.taskID;
		this.description = task.description;
		this.observable = task.observable;
		this.status = task.status;
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
	public Status getStatus() {
		return this.status;
	}
	
	/**
	 * Set Status
	 * @param Status?
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
}