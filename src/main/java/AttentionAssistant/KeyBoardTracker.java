package AttentionAssistant;

import java.util.ArrayList;

//To be implemented by Paul

public class KeyBoardTracker {
	
int keyBoardScore;
	
	/**
 	 * Instantiating empty KeyBoardTracker object
 	 * @author jmitchel2
 	 */
	public KeyBoardTracker(){
		this.keyBoardScore= 100;
	}
	
	/**
	 * Create a class KeyBoardTracker with a specified
	 * KeyBoardScore
	 * @param int 
	 */
	public KeyBoardTracker(int keyBoardScore) {
		this.keyBoardScore= keyBoardScore;
	}

	/**
	 * Start of Encapsulation
	 * 
	 * Get KeyBoardScore
	 * @return int
	 */
	public int getKeyBoardScore() {
		return this.keyBoardScore;
	}

	/**
	 * Set keyBoardScore
	 * @param int
	 */
	public void setKeyBoardScore(int keyBoardScore) {
		this.keyBoardScore = keyBoardScore;
	}

	
	/**
	 * Start Tracking Keyboard
	 * @param ArrayList<String>
	 */
	protected void startTracking(ArrayList<String> keywords) {
		//update keyBoardScore every second
	}
	

}
