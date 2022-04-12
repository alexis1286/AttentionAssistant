package AttentionAssistant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyBoardTracker implements Runnable, NativeKeyListener {

	int keyBoardScore; //Final keyboard score
	
	/**
 	 * Instantiating empty KeyBoardTracker object
 	 * @author jmitchel2
 	 */
	public KeyBoardTracker(){
		this.keyBoardScore= 0;
	}
	
	/**
	 * Create a class KeyBoardTracker with a specified
	 * KeyBoardScore
	 * @param int 
	 */
	public KeyBoardTracker(int keyBoardScore) {
		this.keyBoardScore= keyBoardScore;
	}

	/**
	 * Start of Encapsulation
	 * 
	 * Get KeyBoardScore
	 * @return int
	 */
	public int getKeyBoardScore() {
		return this.keyBoardScore;
	}

	/**
	 * Set keyBoardScore
	 * @param int
	 */
	public void setKeyBoardScore(int keyBoardScore) {
		this.keyBoardScore = keyBoardScore;
	}

	
	/**
	 * Start Tracking Keyboard
	 * @param ArrayList<String>
	 */
	protected void startTracking(ArrayList<String> keywords) {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ey) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ey.getMessage());
			System.exit(1);
		}
		
		GlobalScreen.addNativeKeyListener(this);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	//Ignored for the most part
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Same Something!");
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		
		try {
			File outPutFile = new File("KeyBoardPresses.txt");
			if(!outPutFile.exists()) {
				outPutFile.createNewFile();
			}
			FileWriter keyBoardPresses = new FileWriter(outPutFile.getName(),true);
			BufferedWriter bufferFile = new BufferedWriter(keyBoardPresses);
			
			if(NativeKeyEvent.getKeyText(e.getKeyCode()).length() > 1) {
				bufferFile.append("\n");
			} else {
				bufferFile.append(NativeKeyEvent.getKeyText(e.getKeyCode()));
			}
			bufferFile.close();
		} catch (IOException e1) {
			System.out.println("An error occurred.");
		    e1.printStackTrace();
		}
	}

	//Ignored for the most part
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * method that captures all keys pressed
	 */
	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
	}
}
