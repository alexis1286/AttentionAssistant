package AttentionAssistant;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class AudioHandler {
	
	String audioPath;
	
	public AudioHandler() {
		//will need to figure out implementation for this with settings
		this.audioPath = "src/main/resources/";
	}
	
	/***************************************************************************
	 * 
	 * 							Audio sound functions
	 * 
	 ***************************************************************************/
	
	public void distractedAudio() {
		
	}
	
	public void selfCareAudio() {
		
	}
	
	public void allGoodAudio() {
		
	}
	
	public void dueDateApproachingAudio() {
		
	}
	
	public void taskCompletedAudio() {
		
	}
	
	public void breakTimeAudio() {
		
	}
	
	public void workTimeAudio() {
		
	}
	
	/***************************************************************************
	 * 
	 * 							Text-To-Speech function
	 * 
	 ***************************************************************************/
	
	/**
	 * Translates text for the notification into speech
	 * @param text -> text to be translated into speech
	 */
	public void notificationTTS(String text) {
		try {
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
			Synthesizer synth = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
			
			synth.allocate();
			
			/*
			 * Changing the voice to a higher bit (quality) voice
			 * 
			 * **This is also where we can update other properties for
			 *   the voice and synthesizer further down the road**
			 */
			Voice voice = new Voice();
			voice.setName("kevin16");
			synth.getSynthesizerProperties().setVoice(voice);
			
			synth.resume();
			
			/*
			 * Speaks the passed in text until end of text queue
			 */
			synth.speakPlainText(text, null);
			synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
			
			synth.deallocate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
