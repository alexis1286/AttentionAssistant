/**
 * Class that contains information on task created by 
 * the user through the priority manager
 */
package AttentionAssistant;

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
	
	/**
	 * Options are 'OPEN' and 'CLOSED'
	 * OPEN tasks were not yet completed by the User
	 * CLOSED tasks were completed by the User
	 */
	private Status status;
	
	/**
	 * Create a class Task with a specified
	 * taskID, description, whether observable, status
	 */
	public Task(int taskID, String description, boolean observable, Status status) {
		this.taskID = taskID;
		this.description = description;
	}
	
}