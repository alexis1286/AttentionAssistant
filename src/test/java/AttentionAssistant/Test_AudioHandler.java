package AttentionAssistant;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test File for AudioHandler functions.
 * @author ehols001
 */
public class Test_AudioHandler {

	AudioHandler tts;
	
	@BeforeEach
	void setup() {
		tts = new AudioHandler();
	}
	
	@Test
	@DisplayName("<AudioHandler> notificationTTS")
	void AudioHandlerNotificationTTS() {
		String text = "I'm a robot, I'm stuck inside your computer... please help me";
		tts.notificationTTS(text);
	}
}
