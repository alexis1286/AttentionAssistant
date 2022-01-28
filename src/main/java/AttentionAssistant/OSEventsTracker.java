/**
 * Tracks the OS Events to determine if user is using programs that are distracting 
 */

//To be implemented by Evan

package AttentionAssistant;

public class OSEventsTracker {
	
	int osEventsScore = 100;
	
	/**
	 * Constructor
	 */
	public OSEventsTracker(){
	}
	
	protected void startTracking() {
		//update eyeMovementScore every second
	}
	
	protected int getOSEventsScore(){
		return osEventsScore;
	}

}
