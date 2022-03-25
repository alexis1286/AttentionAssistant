package AttentionAssistant;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.sqlite.SQLiteDataSource;

/**
 * Tracks the internet Usage to determine if user is on topic while searching the internet 
 */
public class InternetTracker {
	
	int internetScore;
	File tempHistory;
	
	/**
	 * Instantiating empty InternetTracker object
	 * @author jmitchel2, ehols001
	 */
	public InternetTracker() {
		this.internetScore = 100;
		this.tempHistory = new File(System.getProperty("user.home") + "\\AppData\\Local\\Temp\\tempHistory");
	}
	
	/**
	 * Create a class InternetTracker with a specified
	 * internetScore, tempHistory
	 * @param int, File
	 */
	public InternetTracker(int internetScore, File tempHistory) {
		this.internetScore = internetScore;
		this.tempHistory = tempHistory;
	}
	
	/**
	 * Copy constructor for InternetTracker
	 * @param InternetTracker object
	 */
	public InternetTracker(InternetTracker it) {
		this.internetScore = it.internetScore;
		this.tempHistory = it.tempHistory;
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
	 * Get the absolute path of the temp history
	 * @return File
	 */
	public File getTempHistory() {
		return this.tempHistory;
	}
	
	/**
	 * Starts tracking relevance of Internet activity 
	 * 
	 * @param ArrayList<String>
	 * @param long startTime -> timestamp of when the Observer monitor function begins
	 * @author ehols001
	 */
	public void startTracking(ArrayList<String> keywords, long startTime) throws IOException {
		createHistoryCopy();
		
		ArrayList<String> latestUrls = new ArrayList<String>();
		latestUrls = getLatestBrowserHistory(startTime);
		
		if(latestUrls.size() != 0) {
			int nullUrls = 0; //For demo purposes
			int urlCount = 0;
			int combinedScore = 0;
			String text = "";
			for(int i = 0; i < latestUrls.size(); i++) {
				if(parseFromOrigin(latestUrls.get(i)) == null) {
					//Do nothing, go to next URL
					
					/*
					 * For demo purposes
					 */
					nullUrls += 1;
					urlCount += 1;
					System.out.println("\nURL #" + urlCount);
					System.out.println("This URL is either not found or private");
				}
				else {
					urlCount += 1;
					text = parseFromOrigin(latestUrls.get(i)).toLowerCase();
					System.out.println("\nURL #" + urlCount); //For demo purposes
					combinedScore += calculatePageScore(keywords, text);
				}
			}
			urlCount -= nullUrls; //For demo purposes
			
			//Accounting for the case where all latestUrls throw an Http 404 error
			if(urlCount != 0)
				this.internetScore = combinedScore / urlCount;
			else
				this.internetScore = -1;
		}
		else {
			this.internetScore = -1;
		}
		tempHistory.delete();
	}
	
	/**
	 * Iterates through a list of provided keywords checking if the 
	 * current keyword exists within the webpage, totaling up an average score.
	 * 
	 * @param ArrayList<String> keywords
	 * @param String text - text from a web page
	 * @return int -> internet score for the given text from a single url
	 * @author ehols001, jmitchel2
	 */
	public int calculatePageScore(ArrayList<String> keywords, String text) {
		//declaration
		double keywordsAppear=0;
		double calculatedScore=0;
		
		//Split the text on a page by each space
		String[] wordsOnAPage = text.split("\\s+");
		
		//turn each word into only A-Z characters
		for(int i = 0; i < wordsOnAPage.length; i++) {
			wordsOnAPage[i] = wordsOnAPage[i].replaceAll("[^A-Za-z]", "");
		}
		
		//search keywords for the words on a page.
		for(String keyword : keywords) {
			for (int j=0; j < wordsOnAPage.length; j++) {
				if (wordsOnAPage[j].equals(keyword)) {
					keywordsAppear = keywordsAppear + 1;
				}
			}
		}
		
		System.out.println("Keywords on a Page= " + keywordsAppear);
		System.out.println("Total Words On a Page= " + wordsOnAPage.length);
		
		calculatedScore = (keywordsAppear/Double.valueOf(wordsOnAPage.length))*7500;
		System.out.println("Total Calculated Score= " +calculatedScore);
		
		int pageScore = 0;
		//If calculatedScore is greater than 100
		if (calculatedScore > 100) {
			pageScore = 100;
		}
		else {
			pageScore = (int)calculatedScore;
		}
		
		return pageScore;
	}
	
	/**
	 * Parses the contents of a web page from live or local origin
	 * and returns all text within the body.
	 * 
	 * @param String - Can be either a live url or a local file path 
	 * @return String - The parsed contents of the page
	 * @throws IOException
	 * @author ehols001
	 */
	public String parseFromOrigin(String uri) throws IOException {
		String text = "";
		if (uri.startsWith("http")) {
			try {
				Document webpage = Jsoup.connect(uri).get();
				text = webpage.body().text();
				return text;
			} catch (HttpStatusException e) {
				return null;
			}
		}
		else {
			File input = new File(uri);
			Document localpage = Jsoup.parse(input, "UTF-8");
			text = localpage.body().text();
			return text;
		}
	}
	
	/**
	 * Creates a copy of the chrome browser history in a different location.
	 * This is necessary to allow read access while chrome is open.
	 * 
	 * @author ehols001
	 */
	public void createHistoryCopy() {
		File source = new File(System.getProperty("user.home") 
				+ "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History");
		try {
			FileUtils.copyFile(source, tempHistory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves the latest urls visited from the user's chrome browser history
	 * 
	 * @param long startTime -> timestamp of when the Observer monitor function begins
	 * @return ArrayList<String> -> list of urls accessed since previous latest visit
	 * @author ehols001
	 */
	public ArrayList<String> getLatestBrowserHistory(long startTime) {
		SQLiteDataSource ds = new SQLiteDataSource();
		String history = "jdbc:sqlite:" + tempHistory.toString();
		ds.setUrl(history);
		
		ArrayList<String> latestUrls = new ArrayList<String>();
    	String query = "SELECT url, last_visit_time FROM urls WHERE last_visit_time > " + startTime;
    	try (Connection conn = ds.getConnection(); 
    			Statement stmt = conn.createStatement();) {
    		    ResultSet rs = stmt.executeQuery(query);
    		    while (rs.next()) {
    		    	latestUrls.add(rs.getString("url"));
    		    }
    		    
    		    /*
    		     * For demo purposes
    		     */
    			for(int i = 0; i < latestUrls.size(); i++) {
    				System.out.println(latestUrls.get(i));
    			}
    			
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return latestUrls;
	}
	
}
