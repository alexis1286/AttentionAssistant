package AttentionAssistant;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * Test File for OSEventsTracker functions.
 * @author ehols001
 */
public class Test_OSEventsTracker {
	
	OSEventsTracker testEvent = new OSEventsTracker();
	Set<String> testNames = new HashSet<>();
	ArrayList<String> testBlacklist = new ArrayList<String>();
	ArrayList<String> testWhitelist = new ArrayList<String>();

	@BeforeEach
	void setup() {
		testBlacklist.add("Test1");
		testBlacklist.add("test2");
		testWhitelist.add("Test3");
		testWhitelist.add("test4");
		testNames.add("Test0");
		testNames.add("test2");
		testNames.add("Test3");
		testNames.add("test4");
	}
	
	@Test
	@Order(1)
	@DisplayName("<OSEventsTracker> OSEventsProcessDetails")
	void OSEventsProcessDetails() {
		String testapp1 = testEvent.processDetails("C:\\usr\\test\\appname1.exe");
		String testapp2 = testEvent.processDetails("\\home\\usr\\test\\Appname2");
		assertEquals(testapp1, "appname1", "Expected: appname1, Actual: " + testapp1);
		assertEquals(testapp2, "appname2", "Expected: Appname2, Actual: " + testapp2);
	}
	
	@Test
	@Order(2)
	@DisplayName("<OSEventsTracker> OSEventsGetBlacklistCount")
	void OSEventsGetBlacklistCount() {
		int blcount = 0;
		for(String testName : testNames) {
			blcount += testEvent.getBlacklistCount(testName, testBlacklist);
		}
		assertEquals(1, blcount, "Expected: 1, Actual: " + blcount);
	}
	
	@Test
	@Order(3)
	@DisplayName("<OSEventsTracker> OSEventsGetWhitelistCount")
	void OSEventsGetWhitelistCount() {
		int wlcount = 0;
		for(String testName : testNames) {
			wlcount += testEvent.getWhitelistCount(testName, testWhitelist);
		}
		assertEquals(2, wlcount, "Expected: 2, Actual: " + wlcount);
	}
	
	@Test
	@Order(4)
	@DisplayName("<OSEventsTracker> OSEventsCalculateOSEventsScore")
	void OSEventsCalculateOSEventsScore() {
		int testTotal = 0;
		testTotal = testEvent.calculateOSEventsScore(1, 2);
		assertEquals(57, testTotal, "Expected: 57, Actual: " + testTotal);
		testTotal = testEvent.calculateOSEventsScore(0, 2);
		assertEquals(100, testTotal, "Expected: 100, Actual: " + testTotal);
		testTotal = testEvent.calculateOSEventsScore(1, 0);
		assertEquals(0, testTotal, "Expected: 0, Actual: " + testTotal);
		testTotal = testEvent.calculateOSEventsScore(0, 0);
		assertEquals(0, testTotal, "Expected: 0, Actual: " + testTotal);
	}
	
}
