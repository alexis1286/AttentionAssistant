/**
 * Tracks the internet Usage to determine if user is on topic while searching the internet 
 */

//To be implemented by Paul

package AttentionAssistant;

import java.util.ArrayList;

public class InternetTracker {
	
	int internetScore;
	
	/**
	 * Instantiating empty InternetTracker object
	 * @author jmitchel2
	 */
	public InternetTracker(){
	this.internetScore = 100;
	}
	
	/**
	 * Create a class InternetTracker with a specified
	 * internetScore
	 * @param int 
	 */
	public InternetTracker(int internetScore) {
		this.internetScore= internetScore;
	}
	/**
	 * Start of Encapsulation
	 * 
	 * Get internetScore
	 * @return int
	 */
	public int getInternetScore() {
		return this.internetScore;
	}

	/**
	 * Set internetScore
	 * @param int
	 */
	public void setInternetScore(int internetScore) {
		this.internetScore = internetScore;
	}
	
	/**
	 * Start Tracking Internet
	 * @param ArrayList<String>
	 */
	public void startTracking(ArrayList<String> keywords) {
		//update internetScore every second
	}
	
}
