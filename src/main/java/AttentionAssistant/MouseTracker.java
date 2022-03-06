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
public class MouseTracker implements Runnable {
	int mouseScore;
	
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
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
 }