/**
 * Tracks the movement of the user mouse to determine if 
 * user is actively engaged with their computer
 */

//To be implemented by Justus and Evan
package AttentionAssistant;


import java.util.concurrent.TimeUnit;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;
import nu.pattern.OpenCV;

/**
 * CLASS IS NOT COMPLETED YET!!!!
 */

public class EyeMovementTracker {
		
	/**
	 * the score that eyeMovementScore is generating.
	 */
	private int eyeMovementScore;
	
	
	/**
	 * Instantiating empty EyeMovementTracker object
	 * @author jmitchel2
	 */
	public EyeMovementTracker(){
		this.eyeMovementScore= 100;

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
	 * Start tracking for the EyeMovementTracker
	 */
	public void startTracking() {
	/**
	 * Still implementing -jmitchel2
	 * Code will print out Whether the user is looking at the screen or not to the command line 
	 * every time a users face is recognized
	 * 
	 * currently ends after 10 seconds of tracking'
	 * 
	 * Interestingly, The first frame for myself is always false. -jmitchel
	 * -(Added a method to grab the boolean every 5 frames to counteract false negatives.)
	 * 
	 */	
		//load OpenCV shared library
	  	OpenCV.loadLocally();
		
	  	//new capture object
		VideoCapture capture= new VideoCapture(0);
        
		//If the camera is opened
		if (capture.isOpened()) {
            
			//print that camera is ready
			System.out.println("Camera is ready!");

			//used for calculating Score
    		double frames5=0;
    		double facesFound=0;
            
    		//grabs the time 10 seconds in the future
    		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(10L, TimeUnit.SECONDS);
    		
    		//while the system clock is not greater than the end time
    		while (System.nanoTime()< endTime){
    			
    			//create new boolean object default false
    			Boolean isFaceDetected=false;
    			
    			//beginning of for loop grab 5 frames
    			for (int i = 0; i<5; i++) {
    				
    				//sets isFaceDetected to the return of continueTracking
    				Boolean isFaceDetectedFrame= this.continueTracking(capture);

    				// prints to the command line whether a face has been detected (true) or if none are found (false)
    				System.out.println("Looking at screen: " + isFaceDetectedFrame);
    	        
    				//if a face is detected in any of the 5 frames set isFaceDetected to true 
    				if (isFaceDetectedFrame == true) {
    					isFaceDetected=true;
    				}
    			
    			//end of for loop
    			}
    			
    			//add 1 to frames 5 for Score Calculator
    			frames5= frames5+1;
    			
    			//if FaceDetected is true add 1 to facesFound for Score Calculator
    			if (isFaceDetected == true) {
    				facesFound= facesFound +1;
    			}
    			
    		//end of while loop
    		}
    		
    		//calculate the eyeMovementScore
    		double eyeMovementScoreCalculator= (facesFound/frames5)*100;
    		
    		//Print when finished along with eyeMovementScore
    	    System.out.println("I have finished EyeMovemtTracking\nEyeMovementScore= " + eyeMovementScoreCalculator);
    	    
    	    //Print number of frames5 and number of facesFound
    	    //System.out.println("Frames5 = " + frames5 + "\nFacesFound = " + facesFound);
    	    
    	    //Sets eyeMovementScore
    	    this.eyeMovementScore= (int)eyeMovementScoreCalculator;		
    		
    	    //close the camera down
    		capture.release();
		}

		//if the camera will not open
		else {
			System.out.println("Camera Error!");
			//set eyeMovementScore to -1
			this.eyeMovementScore= -1;
		}
	
	}
	
	public boolean continueTracking(VideoCapture capture) {
		//creates a boolean object to return default false
		boolean isFaceDetected=false;
		//Creates Mat Object
		Mat mat = new Mat();
		//Grabs a from from capture and stores it in mat
		capture.read(mat);
		//creates facesDetected object
		MatOfRect facesDetected = new MatOfRect();
		//creates cascadeClassifier object
		CascadeClassifier cascadeClassifier = new CascadeClassifier();
		//set minfacesize on image
		int minFaceSize= Math.round(mat.rows()*0.1f);
		//load the cascadeClassifier from resources folder
		cascadeClassifier.load("./src/main/resources/Eye_Movement_Tracking/haarcascade_frontalface_alt.xml");
		//Detects faces from the mat image
        cascadeClassifier.detectMultiScale(mat, facesDetected, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE, new Size(minFaceSize, minFaceSize), new Size());			
        //stores an array of faces from the detectmultiscale
        Rect[] facesArray = facesDetected.toArray();
        
        //I know this warning is here -jmitchel, 
        //just need to check the array to make sure it isn't null if it is null
        for(@SuppressWarnings("unused")Rect face : facesArray) {
        	//if a face is detected it will change to true
        	isFaceDetected=true;
        }
        //return true or false based on if a face was detected.
        return isFaceDetected;
	}
}
