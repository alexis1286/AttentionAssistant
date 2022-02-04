package AttentionAssistant;

import java.util.Date;

/**
 * Class that contains information whenever Happy_Thought_Button
 * is called.
 * @author jmitchel2
 */
public class Happy_Thought_Button {

	/**
	 * Primary Key for Happy Thought Button
	 */
	private int hTB_ID;

	/**
	 * Media ID for the media displayed from the Happy_Thought_Button
	 */
	private String media_ID_Tag;
	
	/**
	 * If the media is flagged by the user or not
	 */
	private boolean flagged;
	
	/**
	 * The Date and Time the Happy_Thought_Button was executed 
	 */
	
	private Date dT_Executed;
	
	/**
	 * Instantiating empty Happy_Thought_Button object
	 * @author jmitchel2
	 */
	public Happy_Thought_Button() {
		this.hTB_ID= 0;
		this.media_ID_Tag = "";
		this.flagged = false;
		this.dT_Executed= null;
	}

	/**
	 * Create a class Happy_Thought_Button with a specified
	 * hTB_ID, media_ID_Tage, flagged
	 * @author jmitchel2
	 * @param int, String, boolean
	 */
	public Happy_Thought_Button(int hTB_ID, String media_ID_Tag, boolean flagged, Date dT_Executed) {
		this.hTB_ID= hTB_ID;
		this.media_ID_Tag= media_ID_Tag;
		this.flagged = flagged;
		this.dT_Executed= dT_Executed;
	}
	
	/**
	 * Instantiating copy constructor for Happy_Thought_Button object
	 */
	public Happy_Thought_Button(Happy_Thought_Button hTB) {
		this.hTB_ID= hTB.hTB_ID;
		this.media_ID_Tag = hTB.media_ID_Tag;
		this.flagged = hTB.flagged;
		this.dT_Executed= hTB.dT_Executed;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get hTB_ID
	 * @author jmitchel2
	 * @return int
	 */
	public int getHTBID() {
		return this.hTB_ID;
	}
	
	/**
	 * User should not be able to set the hTB_ID this should be done automatically through the database
	 * comment out once database is working.
	 * 
	 * Set hTB_ID
	 * @param int
	 */
	public void setHTBID(int hTB_ID) {
		this.hTB_ID= hTB_ID;
	}

	/**
	 * Get media_ID_Tag
	 * 
	 * @return String
	 */
	public String getMedia_ID_Tag() {
		return this.media_ID_Tag;
	}
	
	/**
	 * Set media_ID_Tag
	 * 
	 * @param String
	 */
	public void setMedia_ID_Tag(String media_ID_Tag) {
		this.media_ID_Tag= media_ID_Tag;
	}

	/**
	 * Get flagged
	 * @return boolean
	 */
	public boolean getFlagged() {
		return this.flagged;
	}
	
	/**
	 * Set media_ID_Tag
	 * @param boolean
	 */
	public void setFlagged(boolean flagged) {
		this.flagged= flagged;
	}

	/**
	 * Get dT_Executed
	 * 
	 * @return Date
	 */
	public Date getDT_Executed() {
		return this.dT_Executed;
	}
	
	/**
	 * Set dT_Executed
	 * 
	 * @param Date
	 */
	public void setDT_Executed(Date dT_Executed) {
		this.dT_Executed= dT_Executed;
	}

	  /** 
	   * Display HTB
	   * @return String
	   */
	@Override
	public String toString() {
	 	String hTBString= new String();
	 	hTBString = "Happy_Thought_Button ID= " + this.hTB_ID +
	 			" Media_ID_Tag= " + this.media_ID_Tag +
	 			" Flagged= " + Boolean.toString(this.flagged) +
	 			" Date Time Executed= " + this.dT_Executed;
	 			
	 	return hTBString;
	 	
	 }



}