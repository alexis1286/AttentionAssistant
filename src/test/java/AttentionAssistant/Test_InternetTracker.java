package AttentionAssistant;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test File for InternetTracker functions.
 * @author ehols001
 */
public class Test_InternetTracker {
	
	InternetTracker testDefaultIT;
	InternetTracker testParameterizedIT;
	
	@BeforeEach
	void setup() {
		int testInternetScore = 0;
		testDefaultIT = new InternetTracker();
		testParameterizedIT = new InternetTracker(testInternetScore);
	}
	
	@Test
	@DisplayName("<InternetTracker> defaultConstructor")
	void InternetTrackerDefaultConstructor() {
		assertEquals(100, testDefaultIT.getInternetScore(), 
			        "Expected: 100 | Actual: " + testDefaultIT.getInternetScore());
	}
	
	@Test
	@DisplayName("<InternetTracker> paramaterizedConstructor")
	void InternetTrackerParamaterizedConstructor() {
		assertEquals(0, testParameterizedIT.getInternetScore(), 
			        "Expected: 0 | Actual: " + testParameterizedIT.getInternetScore());
	}
	
	@Test
	@DisplayName("<InternetTracker> setInternetScore")
	void InternetTrackerSetInternetScore() {
		testDefaultIT.setInternetScore(50);
		assertEquals(50, testDefaultIT.getInternetScore(), 
			        "Expected: 50 | Actual: " + testDefaultIT.getInternetScore());
	}
	
	@Test
	@DisplayName("<InternetTracker> calculateInternetScore")
	void InternetTrackerCalculateInternetScore() {
		String testText = "Birds often migrate";
		ArrayList<String> testKeywords = new ArrayList<String>();
		testKeywords.add("bear");
		testKeywords.add("migrate");
		
		testDefaultIT.calculateInternetScore(testKeywords, testText);
		
		assertEquals(100, testDefaultIT.getInternetScore(), 
			        "Expected: 100 | Actual: " + testDefaultIT.getInternetScore());
	}
	
	@Test
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
}
