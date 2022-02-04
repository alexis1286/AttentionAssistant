/**
 * Tracks the movement of the user mouse to determine if 
 * user is actively engaged with their computer
 */

//To be implemented by Justus and Evan
package AttentionAssistant;


public class EyeMovementTracker {
	
	/**
	 * the score that eyeMovementScore is generating.
	 */
	private int eyeMovementScore;
	
	/**
	 * Instantiating empty EyeMovementTracker object
	 * @author jmitchel2
	 */
	public EyeMovementTracker(){
		this.eyeMovementScore= 100;
	}

	/**
	 * Create a class EyeMovementTracker with a specified
	 * eyeMovementScore
	 * @param int 
	 */
	public EyeMovementTracker(int eyeMovementScore) {
		this.eyeMovementScore= eyeMovementScore;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get EyeMovementScore
	 * @return int
	 */
	public int getEyeMovementScore() {
		return eyeMovementScore;
	}
	
	/**
	 * Set EyeMovementScore
	 * @param int
	 */
	public void setEyeMovementScore(int eyeMovementScore) {
		this.eyeMovementScore = eyeMovementScore;
	}
	
	/**
	 * Start tracking for the EyeMovementTracker
	 */
	protected void startTracking() {
		//update eyeMovementScore every second
	}
	
}
