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
	
	private int threashold;// used to determine if user is on task
	
	private Task activeTask = null; //task passed to the observer through the priority manager
	
	/**
	 * Creating instances of the tracking classes
	 */
	MouseTracker mouseTracker;
	EyeMovementTracker eyeTracker = new EyeMovementTracker();
	KeyBoardTracker keyboardTracker = new KeyBoardTracker();
	OSEventsTracker oSTracker = new OSEventsTracker();
	InternetTracker internetTracker = new InternetTracker();
	
	ArrayList<String> keywords = keywordsGenerator(activeTask); //generate keywords from description of task
	
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
		oSTracker.startTracking();
		internetTracker.startTracking(keywords);
	}
	
	
	/**
	 * Main monitoring function
	 * Will calculate the observerScore and
	 * send the appropriate notification
	 */
	protected void monitor(Task activeTask) {
		
		while(activeTask.getStatus() == TaskStatus.OPEN) {
			
			/**
			 * Obtain all scores from the 5 monitoring services
			 */
			mouseMovementsScore = mouseTracker.getMouseMovementScore();
			eyeMovementScore = eyeTracker.getEyeMovementScore();
			keyBoardScore = keyboardTracker.getKeyBoardScore();
			osEventsScore = oSTracker.getOSEventsScore();
			internetScore = internetTracker.getEyeMovementScore();
			
			/**
			 * Calculate the overall observerScore
			 */
			observerScore = calculateObserverScore(mouseMovementsScore, eyeMovementScore, 
					keyBoardScore, osEventsScore, internetScore);
			
			/**
			 * Check if user is focused on task when they should be working or
			 * hyperfocusing when they should be off task
			 */
			if(observerScore > threashold) {} // need to implement
			
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
	
	/**
	 * Calculates the overall observerScore that is to determine if user is on task.
	 * @param mouseMovementsScore
	 * @param eyeMovementScore
	 * @param keyBoardScore
	 * @param osEventsScore
	 * @param internetScore
	 * @return observerScore
	 */
	private int calculateObserverScore(int mouseMovementsScore, int eyeMovementScore, 
			int keyBoardScore, int osEventsScore, int internetScore){
		
		int observerScore = (int) (0.2 * mouseMovementsScore + 0.2 * eyeMovementScore + 
				0.2 * keyBoardScore + 0.2 * osEventsScore +  0.2 * internetScore);
		
		return observerScore;
	}
	
	
}