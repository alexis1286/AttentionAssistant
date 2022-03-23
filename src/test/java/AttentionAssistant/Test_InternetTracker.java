package AttentionAssistant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
	
	@BeforeEach
	void setup() {
		testInternetScore = 0;
		testTempHistory = new File(System.getProperty("user.home") + "\\AppData\\Local\\Temp\\tempHistory");
		testDefaultIT = new InternetTracker();
		testParameterizedIT = new InternetTracker(testInternetScore, testTempHistory);
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
	}
	
	@Test
	@Order(2)
	@DisplayName("<InternetTracker> paramaterizedConstructor")
	void InternetTrackerParamaterizedConstructor() {
		assertEquals(0, testParameterizedIT.getInternetScore(), 
			        "Expected: 0 | Actual: " + testParameterizedIT.getInternetScore());
		assertEquals(testTempHistory, testParameterizedIT.getTempHistory(), 
				"Expected: " + testTempHistory + " | Actual: " + testParameterizedIT.getTempHistory());
	}
	
	@Test
	@Order(3)
	@DisplayName("<InternetTracker> copyConstructor")
	void InternetTrackerCopyConstructor() {
		assertEquals(testParameterizedIT.getInternetScore(), testCopyIT.getInternetScore(), 
			        "Expected: 0 | Actual: " + testCopyIT.getInternetScore());
		assertEquals(testParameterizedIT.getTempHistory(), testCopyIT.getTempHistory(), 
				"Expected: " + testParameterizedIT.getTempHistory() + " | Actual: " + testCopyIT.getTempHistory());
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
	@Order(6)
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
	@Order(7)
	@DisplayName("<InternetTracker> createHistoryCopy")
	void InternetTrackerCreateHistoryCopy() {
		testDefaultIT.createHistoryCopy();
		assertTrue(testTempHistory.exists());
		testTempHistory.delete();
		assertFalse(testTempHistory.exists());
	}
	
	@Test
	@Order(8)
	@DisplayName("<InternetTracker> getLatestBrowserHistory")
	void InternetTrackerGetLatestBrowserHistory() {
		
		System.out.println("\n~~ BEGIN getLatestBrowserHistory TEST ~~");
		
		long sinceThisTimestamp = ((System.currentTimeMillis() * 1000) + (11644473600000L * 1000));
		System.out.println("Initial Timestamp: " + sinceThisTimestamp);
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15L, TimeUnit.SECONDS);
		while (System.nanoTime()< endTime){}
		
		testDefaultIT.createHistoryCopy();
		
		System.out.println("\nURLs accessed since supplied timestamp:");
		ArrayList<String> testLatestUrls = new ArrayList<String>();
		testLatestUrls = testDefaultIT.getLatestBrowserHistory(sinceThisTimestamp);
		System.out.println("\ntestLatestUrls count: " + testLatestUrls.size());
		
		testDefaultIT.getTempHistory().delete();
		System.out.println("~~ END getLatestBrowserHistory TEST ~~");
	}
	
	@Test
	@Order(9)
	@DisplayName("<InternetTracker> startTracking")
	void InternetTrackerStartTracking() {
		
		System.out.println("\n~~ BEGIN startTracking TEST ~~");
		
		ArrayList<String> testKeywords = new ArrayList<String>();
		testKeywords.add("bear");
		testKeywords.add("migrate");
		testKeywords.add("polar");
		testKeywords.add("travel");
		
		long sinceThisTimestamp = ((System.currentTimeMillis() * 1000) + (11644473600000L * 1000));
		System.out.println("Initial Timestamp: " + sinceThisTimestamp);
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(15L, TimeUnit.SECONDS);
		while (System.nanoTime()< endTime){}
		
		System.out.println("\nURLs accessed since supplied timestamp:");
		try {
			testDefaultIT.startTracking(testKeywords, sinceThisTimestamp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nFinal InternetScore for all urls sinceThisTimestamp: " + testDefaultIT.getInternetScore());
		System.out.println("~~ END startTracking TEST ~~");
	}
}
