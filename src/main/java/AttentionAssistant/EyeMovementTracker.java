/**
 * Tracks the movement of the user mouse to determine if 
 * user is actively engaged with their computer
 */

//To be implemented by Justus and Evan

package AttentionAssistant;

public class EyeMovementTracker {
	
	int eyeMovementScore = 100;
	
	/**
	 * Constructor
	 */
	public EyeMovementTracker(){
	}
	
	protected void startTracking() {
		//update eyeMovementScore every second
	}
	
	protected int getEyeMovementScore(){
		return eyeMovementScore;
	}

}
