/**
 * Tracks the internet Usage to determine if user is on topic while searching the internet 
 */

//To be implemented by Paul

package AttentionAssistant;

import java.util.ArrayList;

public class InternetTracker {
	
	int internetScore = 100;
	
	/**
	 * Constructor
	 */
	public InternetTracker(){
	}
	
	protected void startTracking(ArrayList<String> keywords) {
		//update internetScore every second
	}
	
	protected int getEyeMovementScore(){
		return internetScore;
	}

}
