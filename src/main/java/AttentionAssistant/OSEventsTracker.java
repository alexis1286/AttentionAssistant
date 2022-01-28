/**
 * Tracks the OS Events to determine if user is using programs that are distracting 
 */

//To be implemented by Evan

package AttentionAssistant;

import java.util.*;

public class OSEventsTracker {
	
	int osEventsScore;
	
	/**
	 * Constructor
	 */
	public OSEventsTracker(){
		osEventsScore = 100;
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
		return ProcessHandle.current().pid();
	}
	
	protected int getOSEventsScore(){
		return osEventsScore;
	}

}
