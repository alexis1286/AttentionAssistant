package AttentionAssistant;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
	 * @throws IOException 
	 */
	protected void monitor(Task activeTask) throws IOException {			
			
			ArrayList<String> keyWords = this.keywordsGenerator(activeTask);
/**			for (int i =0; i< keyWords.size(); i++)
 *			{
 *				System.out.println(keyWords.get(i));
 *			}
 */			//new Objects
			MouseTracker mouseTracker = new MouseTracker();
			EyeMovementTracker eyeMovementTracker = new EyeMovementTracker();
			KeyBoardTracker keyBoardTracker = new KeyBoardTracker();
			OSEventsTracker osEventsTracker = new OSEventsTracker();
			InternetTracker internetTracker = new InternetTracker();
			 
			//Start tracking Objects
			mouseTracker.startTracking();
			eyeMovementTracker.startTracking();  
			keyBoardTracker.startTracking(keyWords);
			osEventsTracker.startTracking();
			internetTracker.startTracking(keyWords);
			
/**			System.out.println("\nMouse Tracker: 0 (notImplemented yet)" + 
 *					"\neyeMovementTracker: " + eyeMovementTracker.getEyeMovementScore() +
 *					"\nkeyBoardTracker: 0 (not Implemented yet)" + 
 *					"\nosEventsTracker: " + osEventsTracker.getOSEventsScore() +
 *					"\ninternetTracker: " + internetTracker.getInternetScore());
 */			
			 
			//(insert some timer here in while loop) So we grab all scores at once.
			/**
			{
			this.setObserverScore(calculateObserverScore(mouseMovementTracker.getMouseScore(),
			eyeMovementTracker.getEyeMovementScore(),
			keyBoardTracker.getKeyBoardScore(),
			osEventsTracker.getOSEventsScore(),
			internetTracker.getInternetScore())
			}
			*/
			
			
			/**
			 * Calculate the overall observerScore
			 */
			
			/**
			observerScore = calculateObserverScore(mouseMovementsScore, eyeMovementScore, 
					keyBoardScore, osEventsScore, internetScore);
			
			/**
			 * Check if user is focused on task when they should be working or
			 * hyperfocusing when they should be off task
			 */
			
			/**
			if(observerScore >= threshold && pTimer.getWorkBreakStatus() == Work_Break.WORK ) {
				/**
				 * NO ACTION
				 * User is doing what they should be doing
				 */
			/**
			} else if (observerScore < threshold && pTimer.getWorkBreakStatus() == Work_Break.BREAK) {
				/**
				 * NO ACTION
				 * User is doing what they should be doing
				 */
			/**
			} else if (observerScore < threshold && pTimer.getWorkBreakStatus() == Work_Break.WORK) {
				/**
				 * TAKE ACTION
				 * User should be working but is not
				 * NEED TO IMPLEMENT - Send a message through the Avatar
				 */
			/**
			} else {
				/**
				 * TAKE ACTION
				 * User is hyperforcusing
				 * NEED TO IMPLEMENT - Send a message through the Avatar
				 */
			}

	/**
	 * Returns an ArrayList with keywords based on the task's description
	 * @param activeTask - task that's used to generate keywords
	 * @return ArrayList<String> keywords - ArrayList of keywords
	 * @throws IOException 
	 */
	protected ArrayList<String> keywordsGenerator(Task activeTask) throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		
		//create a instance of the IDictionary Object from the WordNet datasets
		URL location =  new File("./src/main/resources/dict").toURI().toURL();
		//System.out.println("URL of resource " + location);
		IDictionary dict = new Dictionary(location);
		dict.open();
			
		ArrayList<String> taskWords = filterTaskDescription(activeTask);
		setKeywordSynonyms(dict, taskWords, keywords);
		return keywords;
	}
	
	/**
	 * Stores each three or more letter word from the task description into a ArrayList
	 * @param activeTask - task to get description from
	 * @return ArrayList<String> - ArrayList of words from the task description
	 */
	public ArrayList<String> filterTaskDescription(Task activeTask) {
		ArrayList<String> filteredWords = new ArrayList<String>();
		//Splitting apart the task description at each 'space' and storing each word
		String[] words = activeTask.getDescription().split("\\s+");
		
		for(int i = 0; i < words.length; i++) {
			//Removing all non-alphabetical chars from the words 
			words[i] = words[i].replaceAll("[^A-Za-z]", "");
		}
		for(String word : words) {
			if(word.length() >= 3)
				filteredWords.add(word);
		}
		return filteredWords;
	}
	
	/**
	 * Identify and store synonyms into keywords for each word from taskWords
	 * @param dict - dictionary to get synonyms from
	 * @param taskWords - list of words from the task description
	 * @param ArrayList<String> keywords - ArrayList of keywords
	 */
	public void setKeywordSynonyms(IDictionary dict, ArrayList<String> taskWords, ArrayList<String> keywords) {
		for(int i = 0; i < taskWords.size(); i++) {
			for(POS p : POS.values()) {
				IIndexWord idxWord = dict.getIndexWord(taskWords.get(i), p);
				if(idxWord != null) {
					/**
					 * A word can have multiple definitions, 
					 * therefore each will have its own related words
					 */
					List<IWordID> idxWordIDs = idxWord.getWordIDs();
					for (int j = 0; j < idxWordIDs.size(); j++)
					{
					IWordID wordID = idxWordIDs.get(j); 
					IWord word = dict.getWord(wordID);
					//System.out.println("Id = " + wordID);
					//System.out.println("Lemma = " + word.getLemma());
					//System.out.println("Gloss = " + word.getSynset().getGloss());

					ISynset wSynset = word.getSynset();
					for(IWord w : wSynset.getWords()) {
						//Makes sure the word only contains alphabetical chars before adding to keywords list
						if(w.getLemma().matches("[a-zA-Z]+"))
							keywords.add(w.getLemma());
							//System.out.println(w.getLemma());
					}
					}
				}
				else;
			}
		}
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

