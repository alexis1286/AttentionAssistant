package AttentionAssistant;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import AttentionAssistant.Pomodoro_Timer.Work_Break;
import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.ISynset;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;


public class Observer implements Runnable {
	
	private int observer_ID; // The unique key identifier for Observer Objects
	private int observerScore; // The observer Score Generated by the Observer
	private int threshold; //used to determine if user is on task
	private Date dT_Gathered; //The Date and Time that an observer Score is generated
	private int defaultEyeScore; //The EyeMovement Tracker Default Score (used for eyeMovement Tracker Profile) 

	private Task activeTask;
	private DataBase db; 
	private Notification_System notification_System;
	private Pomodoro_Timer pTimer;

	/**
	 * Instantiating empty Observer object
	 * @author jmitchel2, PaulFracz, ehols001
	 */
	public Observer() {
		this.observer_ID= 0;
		this.observerScore= 0;
		this.threshold= 0;
		this.dT_Gathered= null;
		this.defaultEyeScore=0;
	}
	
	/**
	 * Create a class Observer with a specified
	 * activeTask, db, notification_System, pTimer
	 * @param Task, DataBase, Notification_System, Pomodoro_Timer
	 */
	public Observer(Task activeTask, DataBase db, Notification_System notification_System, Pomodoro_Timer pTimer) {
		this.activeTask = activeTask;
		this.db = db;
		this.notification_System = notification_System;
		this.pTimer = pTimer;
	}
	
	/**
	 * Create a class Observer with a specified
	 * observer_ID, observerScore, threshold, dT_Gathered
	 * @param int, int, int, Date
	 */
	public Observer(int observer_ID,int observerScore, int threshold, Date dT_Gathered, int defaultEyeScore) {
		this.observer_ID= observer_ID;
		this.observerScore= observerScore;
		this.threshold = threshold;
		this.dT_Gathered = dT_Gathered;
		this.defaultEyeScore = defaultEyeScore;
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
		this.defaultEyeScore= observer.defaultEyeScore;
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
	 * Get Default Eye_Score
	 * @return int
	 */
	public int getDefaultEyeScore() {
		return this.defaultEyeScore;
	}
	
	/**
	 * Set Default Eye_Score
	 * @param int
	 */
	public void setDefaultEyeScore(int defaultEyeScore) {
		this.defaultEyeScore= defaultEyeScore;
	}
	
	@Override
	public void run() {
		try {
			this.monitor(activeTask, db, notification_System, pTimer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main monitoring function
	 * Will calculate the observerScore and
	 * send the appropriate notification
	 * @throws IOException 
	 */
	protected void monitor(Task activeTask ,DataBase db, Notification_System notification_System, Pomodoro_Timer pTimer
			) throws IOException {

			//Generate the keywords for the active task
			ArrayList<String> keyWords = this.keywordsGenerator(activeTask);

			MouseTracker mouseTracker = new MouseTracker();
			EyeMovementTracker eyeMovementTracker = new EyeMovementTracker();
			KeyBoardTracker keyBoardTracker = new KeyBoardTracker();
			OSEventsTracker osEventsTracker = new OSEventsTracker();
			InternetTracker internetTracker = new InternetTracker();
			
			long startTime = ((System.currentTimeMillis() * 1000) + (11644473600000L * 1000));
			
			mouseTracker.startTracking();
			
			//Start tracking Objects
			eyeMovementTracker.startTracking();  
			
			//Will uncomment later -jmitchell
			//keyBoardTracker.startTracking(keyWords);
			
			osEventsTracker.startTracking();
			internetTracker.startTracking(keyWords, startTime);

			//CODE FOR TESTING WEIGHTS:
			//Sets the mouseTrackerScore to 100
			//mouseTracker.setMouseScore(-1);
			
			//Sets the eyeMovementTrackerScore to 100
			//eyeMovementTracker.setEyeMovementScore(100);
			
			//Sets the keyboardTrackerScore to 100
			keyBoardTracker.setKeyBoardScore(-1);
			
			//Sets the osEventsScore to 100
			//osEventsTracker.setOSEventsScore(100);
			
			//Sets the internetTrackerScore to 100
			//internetTracker.setInternetScore(100);

			//Sets the threshold
			this.setThreshold(50);
			
			//Set up for Eye-movement-tracker profile
			this.setDefaultEyeScore(eyeMovementTracker.getEyeMovementScore());
			
			//calculation of the observer score
			this.setObserverScore(calculateObserverScore(
			//mouseTracker.getMouseScore(),
			mouseTracker.getMouseScore(),
			eyeMovementTracker.calculateWeightedEyeMovementScore(activeTask, db),
			keyBoardTracker.getKeyBoardScore(),
			osEventsTracker.getOSEventsScore(),
			internetTracker.getInternetScore()));
			
			//set the Date and Time the score was Gathered
			this.setDTGathered(new Date(System.currentTimeMillis()));

			//write to database
			db.AddObserver(this, activeTask.getTaskID());
			
			
			/**
			 * Check if user is focused on task when they should be working or
			 * hyperfocusing when they should be off task
			 */
			//Primarily used for testing
			//Work_Break observerWB = Work_Break.valueOf("Null");
			
			//Stores the pTimerWork_Break status into the Work_Break observerWB
			Work_Break observerWB = pTimer.getWorkBreakStatus();
			
			//pomo is on work timer
			if(observerWB.toString() == "Work") {
				//on task
				if(this.observerScore >= this.threshold){
					notification_System.allGood();
				}
				//off task
				else{
					notification_System.distracted();
				}
			}
			//pomo is on break timer
			else if (observerWB.toString() == "Break") {
				//on task
				if(this.observerScore >= this.threshold){
					notification_System.selfCare();
				}
				//off task
				else{
					notification_System.isNull();
				}
			}
			//pomo is null/paused
			else{
				//on task
				if(this.observerScore >= this.threshold){
					notification_System.resumePomo();
				}
				//off task
				else{
					notification_System.isNull();
				}
			}
			
			//Function call to display the Observer Monitor data GUI
			displayObserver(activeTask, keyWords, mouseTracker, eyeMovementTracker, 
					keyBoardTracker, osEventsTracker, internetTracker);
	}
	
	/**
	 * Displays Observer Monitor Details
	 * 
	 * @param activeTask
	 * @param keyWords
	 * @param mouseTracker 
	 * @param eyeMovementTracker
	 * @param keyBoardTracker
	 * @param osEventsTracker
	 * @param internetTracker
	 * @author ehols001
	 */
	public void displayObserver(Task activeTask, ArrayList<String> keyWords, MouseTracker mouseTracker, EyeMovementTracker eyeMovementTracker, 
					KeyBoardTracker keyBoardTracker, OSEventsTracker osEventsTracker, InternetTracker internetTracker) {
		
		ObserverInfo monitorInfo = new ObserverInfo();
		ObserverDisplay monitorDisplay = new ObserverDisplay();
		
		monitorInfo.setTask(activeTask);
		monitorInfo.setTaskDescription(activeTask);
		monitorInfo.setTaskKeywords(keyWords);
		monitorInfo.setObserverScore(this.observerScore);
		
		monitorInfo.setGroupsOfFrames(eyeMovementTracker.getTotalGroupsOfFrames());
		monitorInfo.setNumFaceDetected(eyeMovementTracker.getnumberOfTimesFaceDetected());
		monitorInfo.setDefaultEyeScore(eyeMovementTracker.getEyeMovementScore());
		monitorInfo.setThresholdGathered(eyeMovementTracker.getThresholdScore());
		monitorInfo.setWeightedEyeScore(eyeMovementTracker.getweightedScore());
		
		monitorInfo.setBlApps(osEventsTracker.getBlacklist());
		monitorInfo.setWlApps(osEventsTracker.getWhitelist());
		monitorInfo.setBlAppsOpen(osEventsTracker.getBlistOpen());
		monitorInfo.setWlAppsOpen(osEventsTracker.getWlistOpen());
		monitorInfo.setOsEventsScore(osEventsTracker.getOSEventsScore());
		
		monitorInfo.setUrls(internetTracker.getLatestUrls());
		monitorInfo.setNumKeywordsPerURL(internetTracker.getKeywordCounts());
		monitorInfo.setNumTotalWordsURL(internetTracker.getWordCounts());
		monitorInfo.setScorePerURL(internetTracker.getUrlScores());
		monitorInfo.setInternetScore(internetTracker.getInternetScore());
		
		//monitorInfo.setWordsTyped(keyBoardTracker.getKeystrokes());
		//monitorInfo.setNumKeywordsTyped(keyBoardTracker.getKeywordCount());
		//monitorInfo.setKeyboardScore(keyBoardTracker.getKeyBoardScore());
		
		int currentMScore = mouseTracker.currentMovementScore;
		int lastMScore = mouseTracker.lastMovementScore;
		monitorInfo.setCurrentMouseScore(currentMScore);
		monitorInfo.setLastMouseScore(lastMScore);
		monitorInfo.setMouseScore(mouseTracker.getMouseScore());
		
		monitorDisplay.monitorDetails(monitorInfo);
	}
	
	/**
	 * Returns an ArrayList with keywords based on the task's description
	 * @param activeTask - task that's used to generate keywords
	 * @return ArrayList<String> keywords - ArrayList of keywords
	 * @throws IOException 
	 * @author ehols001
	 */
	protected ArrayList<String> keywordsGenerator(Task activeTask) throws IOException {
		ArrayList<String> keywords = new ArrayList<String>();
		
		//create a instance of the IDictionary Object from the WordNet datasets
		URL location =  new File("./src/main/resources/dict").toURI().toURL();
		IDictionary dict = new Dictionary(location);
		dict.open();
			
		ArrayList<String> taskWords = filterTaskDescription(activeTask);
		setKeywordSynonyms(dict, taskWords, keywords);
		keywords = removeDuplicateKeywords(keywords);
		return keywords;
	}
	
	/**
	 * Stores each three or more letter word from the task description into a ArrayList
	 * @param activeTask - task to get description from
	 * @return ArrayList<String> - ArrayList of words from the task description
	 * @author ehols001
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
	 * @param taskWords - list of filtered words from the task description
	 * @param ArrayList<String> keywords - ArrayList of keywords
	 * @author ehols001
	 */
	public void setKeywordSynonyms(IDictionary dict, ArrayList<String> taskWords, ArrayList<String> keywords) {
		for(int i = 0; i < taskWords.size(); i++) {
			for(POS p : POS.values()) {
				IIndexWord idxWord = dict.getIndexWord(taskWords.get(i), p);
				if(idxWord != null) {
					
					//Gets all definitions of the word
					List<IWordID> idxWordIDs = idxWord.getWordIDs();
					
					for (int j = 0; j < idxWordIDs.size(); j++) {
						IWordID wordID = idxWordIDs.get(j);
						IWord word = dict.getWord(wordID);

						ISynset wSynset = word.getSynset();
						for(IWord w : wSynset.getWords()) {
							//Makes sure the word only contains alphabetical chars before adding to keywords list
							if(w.getLemma().matches("[a-zA-Z]+")) {
								keywords.add(w.getLemma());
							}
						}
					}
				}
				else;
			}
		}
	}
	
	/**
	 * Removes any duplicate keywords from the keyword list
	 * @param ArrayList<String> keywords
	 * @return ArrayList<String> keywords
	 * @author ehols001
	 */
	public ArrayList<String> removeDuplicateKeywords(ArrayList<String> keywords) {
		Set<String> temp = new LinkedHashSet<>();
		temp.addAll(keywords);
		keywords.clear();
		keywords.addAll(temp);
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
		
		
		double mouseWeight = 5;
		double eyeWeight = 25;
		double keyBoardWeight = 20;
		double OSWeight = 25;
		double internetWeight = 25;

		double totalWeight= (mouseWeight) + 
				(eyeWeight) + 
				(keyBoardWeight) + 
				(OSWeight) + 
				(internetWeight);

		if (mouseMovementsScore == -1)
		{
			totalWeight= totalWeight - (mouseWeight);
			mouseMovementsScore = 0;
		}
		
		if (eyeMovementScore == -1)
		{
			totalWeight= totalWeight - (eyeWeight);
			eyeMovementScore = 0;
		}
		
		if (keyBoardScore == -1)
		{
			totalWeight= totalWeight - (keyBoardWeight);
			keyBoardScore = 0;
		}
		
		if (osEventsScore == -1)
		{
			totalWeight= totalWeight - (OSWeight);
			osEventsScore = 0;
		}
		
		if (internetScore == -1)
		{
			totalWeight= totalWeight - (internetWeight);
			internetScore = 0;
		}
		
		if (totalWeight ==0) {
			totalWeight= 1;
		}
		
		int calcObserverScore = (int) (((mouseWeight * Double.valueOf(mouseMovementsScore)) 
				+ (eyeWeight * Double.valueOf(eyeMovementScore)) 
				+ (keyBoardWeight * Double.valueOf(keyBoardScore)) 
				+ (OSWeight * Double.valueOf(osEventsScore)) 
				+  (internetWeight * Double.valueOf(internetScore)))/totalWeight);
		
		return calcObserverScore;
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