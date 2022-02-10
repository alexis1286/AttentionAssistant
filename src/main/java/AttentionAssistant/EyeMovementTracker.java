/**
 * Tracks the movement of the user mouse to determine if 
 * user is actively engaged with their computer
 */

//To be implemented by Justus and Evan
package AttentionAssistant;

import java.util.Timer;
import java.util.TimerTask;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;
import nu.pattern.OpenCV;
//This error is fixed once OpenCV.loadShared(); ln 59. Is applied.

/**
 * CLASS IS NOT COMPLETED YET, Still need to implement functions
 */

public class EyeMovementTracker {
	
	/**
	 * the score that eyeMovementScore is generating.
	 */
	private int eyeMovementScore;
	
	/**
	 * Boolean for whether the Eye movement tracker should continue or remain.
	 */
	//I know this warning is here, will be using this to implement the do/while loop
	private boolean continueTracking;
	
	/**
	 * Boolean for if a face has been detected. mainly used in the 
	 * Capture Faces class with the do while loop
	 */
	private boolean faceDetected;

	
	/**
	 * Instantiating empty EyeMovementTracker object
	 * @author jmitchel2
	 */
	public EyeMovementTracker(){
		this.eyeMovementScore= 100;
		this.continueTracking=false;
		
		/**
		 * Need to look more into this function. I'm not sure if this function will break
		 * anything already implemented. (From what I know, it opens the OpenCV library allowing for
		 * functions in the OpenCV to be implemented in Java.
		 */
		//OpenCV.loadShared();
	}	
	
	/**
	 * Create a class EyeMovementTracker with a specified
	 * eyeMovementScore
	 * @param int 
	 */
	public EyeMovementTracker(int eyeMovementScore) {
		this.eyeMovementScore= eyeMovementScore;
	}
	
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get EyeMovementScore
	 * @return int
	 */
	public int getEyeMovementScore() {
		return eyeMovementScore;
	}
	
	
	/**
	 * Set EyeMovementScore
	 * @param int
	 */
	public void setEyeMovementScore(int eyeMovementScore) {
		this.eyeMovementScore = eyeMovementScore;
	}

	
	/**
	 * Get face detected
	 * @return boolean
	 */
	public boolean getFaceDetected() {
		return this.faceDetected;
	}

	
	/**
	 * Set faceDetected
	 * @param boolean
	 */
	public void setFaceDetected(boolean faceDetected) {
		this.faceDetected = faceDetected;
	}
	
	
	/**
	 * Start tracking for the EyeMovementTracker
	 */
	void startTracking() {

		//Sets continueTracking to true
		this.continueTracking=true;
		
		//Captures the WebCamera with the ID of 0 (should be the default WebCamera
		VideoCapture capture = new VideoCapture(0); 
		
		//creates a new Timer object
		Timer getFaces = new Timer();
		
		/**
		 * STILL IMPLEMENTING!!!!! -jmitchel, goal is to do a loop that checks if
		 * continue tracking is still set to true, should be set to false once stopTracking() is called
		 */
		
		//Start while loop here
		//Still not implemented yet
		
		//turns faceDetected to false
		this.faceDetected= false;
		
		//runs the getFaces timer every second to Capture Faces
		getFaces.schedule(new CaptureFaces(capture, this), 1000);
		
		if (faceDetected == false){
			//lower EyeMovement Score
			//Still not implemented yet
		}
		
		
		//End while loop here
		//Still not implemented yet

	}

	void stopTracking() {
		this.continueTracking=false;
	}
	
	}
	
	class CaptureFaces extends TimerTask{
		
		private VideoCapture capture;
		private EyeMovementTracker eyetracker;
		
		CaptureFaces(VideoCapture capture, EyeMovementTracker eyetracker){
			this.capture = capture;
			this.eyetracker = eyetracker;
		}
		
		public void run() {

			//image object for OpenCV
			Mat mat = new Mat();
			//Grabs a from from capture and stores it in mat
			this.capture.read(mat);
			//creates facesDetected object
			MatOfRect facesDetected = new MatOfRect();
			//creates cascadeClassifier object
			CascadeClassifier cascadeClassifier = new CascadeClassifier();
			//set minfacesize on image
			int minFaceSize= Math.round(mat.rows()*0.1f);
			//load the cascadeClassifier from resources folder
			cascadeClassifier.load("src/main/resources/Eye_Movement_Tracking/haarcascade_frontalface_alt.xml");
			//Detects faces from the mat image
	        cascadeClassifier.detectMultiScale(mat, facesDetected, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE, new Size(minFaceSize, minFaceSize), new Size());			
	        
	        //stores an array of faces from the detectmultiscale
	        Rect[] facesArray = facesDetected.toArray();
	        
	        
	        //I know this warning is here -jmitchel, 
	        //just need to check the array to make sure it isn't null if it is null
	        //don't do anything, calculations will be made in the while loop.
	        for(Rect face : facesArray) {
	        
	        	this.eyetracker.setFaceDetected(true);	
	        }
	        
	}

}
