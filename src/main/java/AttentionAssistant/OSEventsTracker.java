/**
 * Tracks the OS Events to determine if user is using programs that are distracting 
 */

//To be implemented by Evan

package AttentionAssistant;

import java.util.*;
import java.lang.Process;

public class OSEventsTracker {
	
	int osEventsScore;
	
	/**
	 * Instantiating empty OSEventsTracker object
	 */
	public OSEventsTracker(){
		this.osEventsScore = 100;
	}

	/**
	 * Create a class OSEventsTracker with a specified
	 * osEventsScore
	 * @param int 
	 */
	public OSEventsTracker(int osEventsScore)
	{
		this.osEventsScore = osEventsScore;
	}

	/**
	 * Start of Encapsulation
	 * 
	 * Get OSEventsScore
	 * @return int
	 */
	public int getOSEventsScore(){
		return this.osEventsScore;
	}
	
	/**
	 * Set osEventsScore
	 * @param int
	 */
	public void setOSEventsScore(int osEventsScore) {
		this.osEventsScore = osEventsScore;
	}
	
	/**
	 * Compares the current processID to the taskID every x seconds
	 * @param comparableID a comparable task id
	 */
	public void startTracking(int comparableID) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				compareCurrentProcess(comparableID);
			}
		}, 0, 5000); //0 ms before taking action, repeat the action every 5000 ms
		timer.cancel(); //not sure if this is needed or not yet
	}
	
	/**
	 * ~~~NEED TO FIGURE OUT INFO FROM TASK~~~
	 * Compares the current process ID to the 
	 * designated Task process ID 
	 */
	public void compareCurrentProcess(int comparableID) {
		int pid = (int)getCurrentProcessID();
		if(comparableID != pid) //currently comparableID will always != pid 
			osEventsScore = 0;
		else
			osEventsScore = 100;
	}
	
	/**
	 * Retrieve the current process ID
	 * @return current process ID
	 */
	public static long getCurrentProcessID() {
		Process.class
		return ProcessHandle.current().pid();
	}
	
}
