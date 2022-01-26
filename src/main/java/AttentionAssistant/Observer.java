package AttentionAssistant;
import java.awt.Component;
import java.util.ArrayList;

public class Observer{
	
	/**
	 * Score variables used to determine if user is off topic.
	 * Range is from 1 to 100
	 * 1 to 33 - Bad Score
	 * 34 to 67 - Neutral Score
	 * 68 to 100 - Good Score
	 */
	private int mouseMovementsScore, eyeMovementScore, keyBoardScore, osEventsScore, internetScore, observerScore;
	
	private Task activeTask = null;
	
	/**
	 * Creating instances of the tracking classes
	 */
	MouseTracker mouseTracker;
	EyeMovementTracker eyeTracker = new EyeMovementTracker();
	KeyBoardTracker keyboardTracker = new KeyBoardTracker();
	
	ArrayList<String> keywords = keywordsGenerator(activeTask);
	
	/**
	 * Constructor for Observer
	 * Will start the monitoring of user's activity
	 * by initiating the tracking functions
	 */
	public Observer(){
		Component mt_Component = mouseTracker.MouseTrackerSetup();
		mouseTracker = new MouseTracker(mt_Component);
		eyeTracker.startTracking();
		keyboardTracker.startTracking(keywords);
	}
	
	
	/**
	 * Main monitoring function
	 * Will calculate the observerScore and
	 * send the appropriate notification
	 */
	protected void monitor(Task activeTask) {
		
		while(activeTask.getStatus() == Status.OPEN) {
			mouseMovementsScore = mouseTracker.getMouseMovementScore();
			eyeMovementScore = eyeTracker.getEyeMovementScore();
			keyBoardScore = keyboardTracker.getKeyBoardScore();
		}
	}
	
	/**
	 * Returns an ArrayList with keywords based on the task's description
	 * @param activeTask - task that used to generator keywords
	 * @return keywordsList - ArrayList of keywords
	 */
	private ArrayList<String> keywordsGenerator(Task activeTask){
		//CODE TO IMPLEMENT
		return keywords;
	}
	
	
}