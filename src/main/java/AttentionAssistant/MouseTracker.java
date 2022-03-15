package AttentionAssistant;
import java.awt.Color;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

/**
 * This class checks the position every #DELAY milliseconds and 
 * informs all registered MouseMotionListeners about position updates.
 */
public class MouseTracker implements Runnable, NativeMouseInputListener {
	int mouseScore;
	int currentMovementScore = 1;
	int lastMovementScore = 1;
	
	/**
	 * Instantiating empty MouseTracker object
	 * @author jmitchel2
	 */
	public MouseTracker(){
	this.mouseScore = 100;
	}
	
	/**
	 * Create a class MouseTracker with a specified
	 * mouseScore
	 * @param int 
	 */
	public MouseTracker(int mouseScore) {
		this.mouseScore= mouseScore;
	}
	/**
	 * Start of Encapsulation
	 * 
	 * Get MouseScore
	 * @return int
	 */
	public int getMouseScore() {
		
		//Calculating a tempt 100 point score
		int temptScore = 100 * (currentMovementScore - lastMovementScore)/lastMovementScore;
		
		//Ensuring that the tempt score is not above 100
		if(temptScore < 100) {
			mouseScore = temptScore;
		} else {
			mouseScore = 100;
		}
		
		//Updating the lastMovementScore to the currentMovementScore
		//Reseting the lastMovementScore and currentMovementScore before they get to close to the int limit size
		if(currentMovementScore < 1147483647) {
			lastMovementScore = currentMovementScore;
		} else {
			lastMovementScore = 1;
			currentMovementScore = 1;
		}
		
		
		return this.mouseScore;
	}

	/**
	 * Set internetScore
	 * @param int
	 */
	public void setMouseScore(int mouseScore) {
		this.mouseScore = mouseScore;
	}
	
	/**
	 * Start Tracking Mouse
	 */
	public void startTracking() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());
			System.exit(1);
		}
		
		// Construct the example object.
		MouseTracker mouseTracker = new MouseTracker();

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(mouseTracker);
		GlobalScreen.addNativeMouseMotionListener(mouseTracker);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
		currentMovementScore++;
		System.out.println("current score = " + currentMovementScore);
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
		//currentMovementScore++;
		System.out.println("current score = " + currentMovementScore);
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
		//currentMovementScore++;
		System.out.println("current score = " + currentMovementScore);
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
		currentMovementScore++;
		System.out.println("current score = " + currentMovementScore);
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.paramString());
		//currentMovementScore++;
		System.out.println("current score = " + currentMovementScore);
	}
	
 }