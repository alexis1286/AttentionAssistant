package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_EyeMovementTracker {
	
	
	EyeMovementTracker defaultEyeMovementTracker;
	EyeMovementTracker nonDefaultEyeMovementTracker;
	
	
	@BeforeEach
	void setup() {
		int testEyeMovementScore = 80;
		defaultEyeMovementTracker= new EyeMovementTracker();
		nonDefaultEyeMovementTracker= new EyeMovementTracker(testEyeMovementScore);
	}
	
	@Test
	@DisplayName("<EyeMovementTracker> DefaultConstructor Testing")
	void EyeMovementTrackerDefaultConstructor(){
		assertEquals(100, defaultEyeMovementTracker.getEyeMovementScore(), "Default constructor EyeMovementTracker.EyemovementScore should be 100. Returned: "
				+ Integer.toString(defaultEyeMovementTracker.getEyeMovementScore())); 
	}

	@Test
	@DisplayName("<EyeMovementTracker> NonDefaultConstructor Testing")
	void EyeMovementTrackernonDefaultConstructor(){
		assertEquals(80, nonDefaultEyeMovementTracker.getEyeMovementScore(), "nonDefault constructor EyeMovementTracker.EyemovementScore should be 80. Returned: "
				+ Integer.toString(nonDefaultEyeMovementTracker.getEyeMovementScore())); 
	}
	
	@Test
	@DisplayName("<EyeMovementTracker> setScore")
	void EyeMovementTrackerGetEMScore(){
		nonDefaultEyeMovementTracker.setEyeMovementScore(75);
		assertEquals(75, nonDefaultEyeMovementTracker.getEyeMovementScore(), "nonDefault constructor EyeMovementTracker.EyemovementScore should be 75. Returned: "
				+ Integer.toString(nonDefaultEyeMovementTracker.getEyeMovementScore())); 
		
	}
	
	@Test
	@DisplayName("<EyeMovementTracker> Setup Testing")
	void EyeMovementTrackerSetupTesting(){
		defaultEyeMovementTracker.startTracking();
	}

}