package AttentionAssistant;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;


public class Observer{
	
	private int observer_ID; // The unique key identifier for Observer Objects
	private int observerScore; // The observer Score Generated by the Observer
	private int threshold; //used to determine if user is on task
	private Date dT_Gathered; //The Date and Time that an observer Score is generated


	/**
	 * Instantiating empty Observer object
	 * @author jmitchel2 PaulFracz
	 */
	public Observer() {
		this.observer_ID= 0;
		this.observerScore= 0;
		this.threshold= 0;
		this.dT_Gathered= null;
	}
	
	/**
	 * Create a class Observer with a specified
	 * observer_ID, observerScore, threshold, dT_Gathered
	 * @param int, int, int, Date 
	 */
	public Observer(int observer_ID,int observerScore, int threshold, Date dT_Gathered) {
		this.observer_ID= observer_ID;
		this.observerScore= observerScore;
		this.threshold = threshold;
		this.dT_Gathered = dT_Gathered;
	}

	/**
	 * Create a class Observer Copy Constructor
	 * @param Observer 
	 */
	public Observer(Observer observer) {
		this.observer_ID= observer.observer_ID;
		this.observerScore= observer.observerScore;
		this.threshold = observer.threshold;
		this.dT_Gathered= observer.dT_Gathered;
	}

	
	/**
	 * Start of Encapsulation
	 * 
	 * Get observerID
	 * @return int
	 */
	public int getObserverID() {
		return this.observer_ID;
	}
	
	/**
	 * Set observerID
	 * @param int
	 */
	public void setObserverID(int observer_ID) {
		this.observer_ID= observer_ID;
	}
	
	/**
	 * Get observerScore
	 * @return int
	 */
	public int getObserverScore() {
		return this.observerScore;
	}
	
	/**
	 * Set observerScore
	 * @param int
	 */
	public void setObserverScore(int observer_Score) {
		this.observerScore= observer_Score;
	}
	
	/**
	 * Get threshold
	 * @return int
	 */
	public int getThreshold() {
		return this.threshold;
	}
	
	/**
	 * Set threshold
	 * @param int
	 */
	public void setThreshold(int threshold) {
		this.threshold= threshold;
	}
	
	/**
	 * Get dT_Gathered
	 * @return Date
	 */
	public Date getDTGathered() {
		return this.dT_Gathered;
	}
	
	/**
	 * Set dT_Gathered
	 * @param int
	 */
	public void setDTGathered(Date dT_Gathered) {
		this.dT_Gathered= dT_Gathered;
	}
	
	/**
	 * Main monitoring function
	 * Will calculate the observerScore and
	 * send the appropriate notification
	 */
	protected void monitor(Task activeTask) {			
			/**
			 * ArrayList<String> keyWords = this.keywordsGenerator(activeTask);
			 * //new Objects
			 * MouseTracker mouseTracker = new MouseTracker();
			 * EyeMovementTracker eyeMovementTracker = new EyeMovementTracker();
			 * KeyBoardTracker keyBoardTracker = new KeyBoardTracker();
			 * OSEventsTracker osEventsTracker = new OSEventsTracker();
			 * InternetTracker internetTracker = new InternetTracker();
			 * 
			 * //Start tracking Objects
			 * mouseTracker.startTracking();
			 * eyeMovementTracker.startTracking();  
			 * keyBoardTracker.startTracking(keyWords);
			 * osEventsTracker.startTracking(activeTask.getTaskID);
			 * internetTracker.startTracking(keyWords);
			 * 
			 * //(insert some timer here in while loop) So we grab all scores at once.
			 *  {
			 *  this.setObserverScore(calculateObserverScore(mouseMovementTracker.getMouseScore(),
			 *  eyeMovementTracker.getEyeMovementScore(),
			 *  keyBoardTracker.getKeyBoardScore(),
			 *  osEventsTracker.getOSEventsScore(),
			 *  internetTracker.getInternetScore())
			 *  }
			 */
	}

	/**
	 * Returns an ArrayList with keywords based on the task's description
	 * @param activeTask - task that used to generator keywords
	 * @return keywordsList - ArrayList of keywords
	 * @throws IOException 
	 */
	
	
	protected ArrayList<String> keywordsGenerator(Task activeTask) throws IOException{
		ArrayList<String> keywords = new ArrayList<String>();
		
		//create a instance of the IDictionary Object from the WordNet datasets
		@SuppressWarnings("deprecation")
		URL location =  new File("src/main/resources/dict").toURL();
		System.out.println("URL of resource " + location);
		IDictionary dict = new Dictionary(location);
		dict.open();
				
		// look up first sense of the word "dog"
		IIndexWord idxWord = dict.getIndexWord("wish", POS.NOUN);
				
		/**
		 * A word can have multiple definitions, 
		 * therefore each will have its own related words
		 */
		IWordID wordID = idxWord.getWordIDs().get(0); 
		IWord word = dict.getWord(wordID);
		System.out.println("Id = " + wordID);
		System.out.println("Lemma = " + word.getLemma());
		System.out.println("Gloss = " + word.getSynset().getGloss());
				
		/**
		 * iterate over words associated with the synset
		 */
		ISynset wSynset = word.getSynset();
		for(IWord w : wSynset.getWords()) {
			System.out.println(w.getLemma());
		}
		
		return keywords;
	}

	/**
	 * Calculates the overall observerScore that is to determine if user is on task.
	 * mouseMovementsScore, eyeMovementScore, keyBoardScore, osEventsScore, internetScore
	 * @param int, int, int, int, int
	 * @return int
	 */
	private int calculateObserverScore(int mouseMovementsScore, int eyeMovementScore, 
			int keyBoardScore, int osEventsScore, int internetScore){
		
		int observerScore = (int) (0.2 * mouseMovementsScore + 0.2 * eyeMovementScore + 
				0.2 * keyBoardScore + 0.2 * osEventsScore +  0.2 * internetScore);
		
		return observerScore;
	}
	
	  /** 
	   * Display Observer
	   * @return String
	   */
	@Override
	public String toString() {
	 	String ObserverString= new String();
	 	ObserverString = "Observer ID= " + this.observer_ID +
	 			" Observer Score= " + this.observerScore +
	 			" Threshold= " + this.threshold +
	 			" Date Time Gathered= " + this.dT_Gathered.toString();
	 	return ObserverString;
	 }

}

