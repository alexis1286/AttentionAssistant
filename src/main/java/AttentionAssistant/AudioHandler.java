package AttentionAssistant;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class AudioHandler {
	
	static String audioPath;
	
	public AudioHandler() {
		audioPath = "src/main/resources/AudioSounds/";
	}
	
	public void AudioPlayer(String soundFilePath) {
		Clip clip;
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(new File(soundFilePath));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} 
		catch (UnsupportedAudioFileException a) {
			a.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		catch (LineUnavailableException l) {
			l.printStackTrace();
		}
	}
	
	/***************************************************************************
	 * 
	 * 							Audio sound functions
	 * 
	 ***************************************************************************/
	
	/**
	 * Still need to create and add in all these .wav files
	 */
	
	public void resumePomoAudio() {
		String soundPath = audioPath + "resumePomo.wav";
		AudioPlayer(soundPath);
	}
	
	public void pomoIsNullAudio() {
		String soundPath = audioPath + "pomoIsNull.wav";
		AudioPlayer(soundPath);
	}
	
	public void distractedAudio() {
		String soundPath = audioPath + "distracted.wav";
		AudioPlayer(soundPath);
	}
	
	public void selfCareAudio() {
		String soundPath = audioPath + "selfCare.wav";
		AudioPlayer(soundPath);
	}
	
	public void allGoodAudio() {
		String soundPath = audioPath + "allGood.wav";
		AudioPlayer(soundPath);
	}
	
	public void dueDateApproachingAudio() {
		String soundPath = audioPath + "dueDateApproaching.wav";
		AudioPlayer(soundPath);
	}
	
	public void taskCompletedAudio() {
		String soundPath = audioPath + "taskCompleted.wav";
		AudioPlayer(soundPath);
	}
	
	public void breakTimeAudio() {
		String soundPath = audioPath + "breakTime.wav";
		AudioPlayer(soundPath);
	}
	
	public void workTimeAudio() {
		String soundPath = audioPath + "workTime.wav";
		AudioPlayer(soundPath);
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
