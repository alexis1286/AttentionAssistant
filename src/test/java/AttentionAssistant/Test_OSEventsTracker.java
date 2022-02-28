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

	@BeforeEach
	void setup() {
		testBlacklist.add("Test1");
		testBlacklist.add("test2");
		testNames.add("Test0");
		testNames.add("test2");
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
	@DisplayName("<OSEventsTracker> OSEventsCompareCurrentProcesses")
	void OSEventsCompareCurrentProcesses() {
		testEvent.setOSEventsScore(100);
		for(String testName : testNames) {
			for(String line : testBlacklist) {
				if(line.equals(testName)) {
					testEvent.setOSEventsScore(0);
					assertEquals(0, testEvent.getOSEventsScore(), "Expected: 0, Actual: " + testEvent.getOSEventsScore());
				}
				else
					assertEquals(100, testEvent.getOSEventsScore(), "Expected: 100, Actual: " + testEvent.getOSEventsScore());
			}
		}
	}
	
}
