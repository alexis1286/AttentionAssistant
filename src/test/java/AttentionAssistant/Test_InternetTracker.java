package AttentionAssistant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * Test File for InternetTracker functions.
 * @author ehols001
 */
public class Test_InternetTracker {
	
	InternetTracker testDefaultIT;
	InternetTracker testParameterizedIT;
	InternetTracker testCopyIT;
	
	int testInternetScore;
	File testTempHistory;
	long testLatestTimestamp;
	
	@BeforeEach
	void setup() {
		testInternetScore = 0;
		testTempHistory = new File(System.getProperty("user.home") + "\\AppData\\Local\\Temp\\tempHistory");
		testLatestTimestamp = 100;
		testDefaultIT = new InternetTracker();
		testParameterizedIT = new InternetTracker(testInternetScore, testTempHistory, testLatestTimestamp);
		testCopyIT = new InternetTracker(testParameterizedIT);
	}
	
	@Test
	@Order(1)
	@DisplayName("<InternetTracker> defaultConstructor")
	void InternetTrackerDefaultConstructor() {
		assertEquals(100, testDefaultIT.getInternetScore(), 
			        "Expected: 100 | Actual: " + testDefaultIT.getInternetScore());
		assertEquals(testTempHistory, testDefaultIT.getTempHistory(), 
					"Expected: " + testTempHistory + " | Actual: " + testDefaultIT.getTempHistory());
		assertEquals(0, testDefaultIT.getLatestTimestamp(), 
		        "Expected: 0 | Actual: " + testDefaultIT.getLatestTimestamp());
	}
	
	@Test
	@Order(2)
	@DisplayName("<InternetTracker> paramaterizedConstructor")
	void InternetTrackerParamaterizedConstructor() {
		assertEquals(0, testParameterizedIT.getInternetScore(), 
			        "Expected: 0 | Actual: " + testParameterizedIT.getInternetScore());
		assertEquals(testTempHistory, testParameterizedIT.getTempHistory(), 
				"Expected: " + testTempHistory + " | Actual: " + testParameterizedIT.getTempHistory());
		assertEquals(100, testParameterizedIT.getLatestTimestamp(), 
				"Expected: 100 | Actual: " + testParameterizedIT.getLatestTimestamp());
	}
	
	@Test
	@Order(3)
	@DisplayName("<InternetTracker> copyConstructor")
	void InternetTrackerCopyConstructor() {
		assertEquals(testParameterizedIT.getInternetScore(), testCopyIT.getInternetScore(), 
			        "Expected: 0 | Actual: " + testCopyIT.getInternetScore());
		assertEquals(testParameterizedIT.getTempHistory(), testCopyIT.getTempHistory(), 
				"Expected: " + testParameterizedIT.getTempHistory() + " | Actual: " + testCopyIT.getTempHistory());
		assertEquals(testParameterizedIT.getLatestTimestamp(), testCopyIT.getLatestTimestamp(), 
				"Expected: " + testParameterizedIT.getLatestTimestamp() + " | Actual: " + testCopyIT.getLatestTimestamp());
	}
	
	@Test
	@Order(4)
	@DisplayName("<InternetTracker> setInternetScore")
	void InternetTrackerSetInternetScore() {
		testDefaultIT.setInternetScore(50);
		assertEquals(50, testDefaultIT.getInternetScore(), 
			        "Expected: 50 | Actual: " + testDefaultIT.getInternetScore());
	}
	
	@Test
	@Order(5)
	@DisplayName("<InternetTracker> setLatestTimestamp")
	void InternetTrackerSetLatestTimestamp() {
		testDefaultIT.setLatestTimestamp(50);
		assertEquals(50, testDefaultIT.getLatestTimestamp(), 
			        "Expected: 50 | Actual: " + testDefaultIT.getLatestTimestamp());
	}
	
	@Test
	@Order(6)
	@DisplayName("<InternetTracker> calculatePageScore")
	void InternetTrackerCalculatePageScore() {
		String testText = "Birds often migrate";
		ArrayList<String> testKeywords = new ArrayList<String>();
		testKeywords.add("bear");
		testKeywords.add("migrate");
		
		int pageScore = testDefaultIT.calculatePageScore(testKeywords, testText);
		
		assertEquals(100, pageScore, 
			        "Expected: 100 | Actual: " + pageScore);
	}
	
	@Test
	@Order(7)
	@DisplayName("<InternetTracker> parseFromOrigin")
	void InternetTrackerParseFromOrigin() throws IOException {
		String url1 = "https://en.wikipedia.org";
		String url2 = "src/test/resources/testPage_forInternetTracking.html";
		
		Document webpage = Jsoup.connect(url1).get();
		String testWikiText = webpage.body().text();
		
		String testText = testDefaultIT.parseFromOrigin(url1);
		assertEquals(testWikiText, testText); //Would print expected results if it wasn't super long.
		
		assertEquals("Welcome to the test page.", testDefaultIT.parseFromOrigin(url2), 
		        "Expected: Welcome to the test page. | Actual: " + testDefaultIT.parseFromOrigin(url2));
	}
	
	@Test
	@Order(8)
	@DisplayName("<InternetTracker> createHistoryCopy")
	void InternetTrackerCreateHistoryCopy() {
		testDefaultIT.createHistoryCopy();
		assertTrue(testTempHistory.exists());
		testTempHistory.delete();
		assertFalse(testTempHistory.exists());
	}
	
	@Test
	@Order(9)
	@DisplayName("<InternetTracker> getInitialTimestamp")
	void InternetTrackerGetInitialTimestamp() {
		long testTimestamp = testDefaultIT.getInitialTimestamp();
		
		/*
		 * Compare the output of this to the most recent url in the chrome history database,
		 * if they are the same this is working the way it should
		 * 
		 * Go to C:\Users\yourUsername\AppData\Local\Google\Chrome\User Data\Default
		 * then scroll down to the file named 'History', right click the file, click 'Open with'
		 * and open the file with DB Browser or whatever SQL viewer you have installed.
		 * 
		 * Once you have the History database opened, click the 'Browse Data' tab and in the 'Table' 
		 * dropdown menu select the 'urls' table. Filter the urls table by last_visit_time descending.
		 */
		System.out.println("\nInitial timestamp for the latest url visited: " + testTimestamp);
	}
	
	@Test
	@Order(10)
	@DisplayName("<InternetTracker> getLatestBrowserHistory")
	void InternetTrackerGetLatestBrowserHistory() {
		testDefaultIT.createHistoryCopy();
		
		/*
		 * Follow the steps in the getInitialTimestamp test above to get to the 'urls' table.
		 * Replace the value of 'sinceThisTimestamp' below with the 'last_visit_time'
		 * value you want to you use as a marker (make sure to keep the 'L' at the end of the value).
		 * GetLatestBrowserHistory will return all urls accessed since the last_visit_time you supply.
		 */
		long sinceThisTimestamp = 13292368130655411L;
		
		ArrayList<String> testLatestUrls = new ArrayList<String>();
		testLatestUrls = testDefaultIT.getLatestBrowserHistory(sinceThisTimestamp);
		
		System.out.println("\nURLs accessed since supplied timestamp:");
		for(int i = 0; i < testLatestUrls.size(); i++) {
			System.out.println(testLatestUrls.get(i));
		}
		
		testDefaultIT.getTempHistory().delete();
	}
	
	@Test
	@Order(11)
	@DisplayName("<InternetTracker> startTracking")
	void InternetTrackerStartTracking() {
		ArrayList<String> testKeywords = new ArrayList<String>();
		testKeywords.add("bear");
		testKeywords.add("migrate");
		testKeywords.add("polar");
		testKeywords.add("travel");
		
		/*
		 * Follow the steps in the getInitialTimestamp test above to get to the 'urls' table.
		 * Replace the value of 'sinceThisTimestamp' below with the 'last_visit_time'
		 * value you want to you use as a marker (make sure to keep the 'L' at the end of the value).
		 * startTracking will calculate an internetScore based off of all urls accessed since the last_visit_time you supply.
		 */
		long sinceThisTimestamp = 13292368130655411L;
		try {
			testDefaultIT.startTracking(testKeywords, sinceThisTimestamp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nFinal InternetScore for all urls sinceThisTimestamp: " + testDefaultIT.getInternetScore());
	}
}
