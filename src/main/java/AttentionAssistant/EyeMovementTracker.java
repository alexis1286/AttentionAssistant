/**
 * Tracks the movement of the user mouse to determine if 
 * user is actively engaged with their computer
 */

//To be implemented by Justus and Evan
package AttentionAssistant;


//Errors will disappear once code is uncommented
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
	 * Still implementing uncomment at your own risk. -jmitchel2
	 * Code will print out Whether you are looking at the screen or not to the command line 
	 * every time a users face is recognized
	 * 
	 * currently no way to terminate.
	 *
	  	boolean i = true;
		OpenCV.loadShared();
		//new capture object
		VideoCapture capture= new VideoCapture(0);
		while (i==true) {	 
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
        //don't do anything, calculations will be made in separately
        for(Rect face : facesArray) {
        isFaceDetected=true;
        }
        System.out.println("Looking at screen: " + isFaceDetected);
		}
	*/
	}
}
