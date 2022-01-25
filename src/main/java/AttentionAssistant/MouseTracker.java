/**
 * Tracks the movement of the user mouse to determine if 
 * user is actively engaged with their computer
 */

//To be implemented by Justus

package AttentionAssistant;

public class MouseTracker {
	int mouseMovementScore = 100;
	
	/**
	 * Constructor
	 */
	public MouseTracker(){
	}
	
	protected void startTracking() {
		//update mouseMovementScore every second
	}
	
	protected int getMouseMovementScore(){
		return mouseMovementScore;
	}

}
