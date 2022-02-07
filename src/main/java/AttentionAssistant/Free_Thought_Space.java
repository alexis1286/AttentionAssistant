package AttentionAssistant;
/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 *   
 *   ***some edits made
 */

import javax.swing.*;
import java.awt.*;
import java.util.Date;


public class Free_Thought_Space extends JFrame{
	/**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    public int fTSID; // Primary Key for Free_Thought_Space
	public DrawingPanel drawingPanel;
    protected MenuBar menuBar;
    protected ColorPalette colorPalette;
    public PaintToolPanel paintToolPanel;
    public Date dT_Executed; //Date and Time Free_Thought_Space is executed

    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
	/**
	 * Instantiating empty Free_Thought_Space object
	 */
    public Free_Thought_Space()
    {
        super("Free Thought Space");  //overriding JFrame's title
    	this.fTSID=0;
        this.dT_Executed= null;
    }
	
	/**
	 * Create a class Free_Thought_Space with a specified
	 * fTSID, dT_Executed
	 * @param int, Date
	 */
    public Free_Thought_Space(int fTSID, Date dT_Executed) 
    {
    	super("Free Thought Space");
    	this.fTSID= fTSID;
    	this.dT_Executed= dT_Executed;
    }
    
    /**
     * Copy Constructor
     */
    public Free_Thought_Space(Free_Thought_Space fts) {
    	super("Free Thought Space");
    	this.fTSID= fts.fTSID;
    	this.dT_Executed= fts.dT_Executed;
    }
        
    /**
     * Start of encapsulation
     */
    
    public void runFts(Free_Thought_Space paint) {
    	

        ImageIcon ImageIcon = getIcon("/images/IMG_BOJA_48.png");
        Image Image = ImageIcon.getImage();

        drawingPanel = new DrawingPanel();                              //create drawing panel
        menuBar = new MenuBar(paint);                                        //create menu bar
        colorPalette = new ColorPalette(paint);                              //create color palette panel
        paintToolPanel = new PaintToolPanel(new StrokeToolPanel(5,paint),paint);   //create drawing tool panel
        add(menuBar, "North");                                         //add panels to the main JFrame
        add(colorPalette, "South");
        add(paintToolPanel, "West");
        add(new JScrollPane(drawingPanel), "Center");

        this.setIconImage(Image);    //setting JFrame's icon image
        this.setSize(1024, 768);     //set size of the application
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);   //set default close operation
        this.setLocationRelativeTo(null);                               //set locating to the middle of the screen
        this.setVisible(true);                                          //set visible
        setStaringColor();                                             //set the starting color
    }

    /**************************************************************************************************************
     ***************************************************METHODS****************************************************
     **************************************************************************************************************/
    public void setStaringColor()     //set starting color to be used for drawing
    {
        ColorPalette.selectedColorDisplay.setBackground(Color.black);
        ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();
        drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());
        drawingPanel.brushColor = ColorPalette.selectedColor;
    }

    /**
     * Method used to return the ImageIcon object that has the path
     * equal to the one given to the method as an input parameter.
     * In case the inputted imagePath parameter is not valid
     * an exception will be caught in the try catch block
     * and the default image used in the JOptionPane
     * @param imagePath  class path
     * @return ImageIcon
     */
    public static ImageIcon getIcon(String imagePath)
    {
        try
        {
            return new ImageIcon(imagePath);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid image path");
            return null;
        }
    }

    /** 
     * Get fTSID
     * @return int
     */
    public int getFTSID() {
    	return this.fTSID;
    }
    
    /**
     * Set fTSID
     * @param int
     */
    public void setFTSID(int fTSID) {
    	this.fTSID = fTSID;
    }
    
    /**
     * Get dT_Executed
     * @return Date
     */
    public Date getDT_Executed(){
    	return this.dT_Executed;
    }
    
    /**
     * Set dT_Executed
     * @param Date
     */
    public void setDT_Executed(Date dT_Executed) {
    	this.dT_Executed = dT_Executed;
    }
    
	 /** 
	   * Display FTS
	   * @return String
	   */
	@Override
	public String toString() {
	 	String fTSString= new String();
	 	fTSString = "Free Thought Space ID= " + this.fTSID +
	 			" Date Time Executed= " + this.dT_Executed.toString();	 			
	 	return fTSString;
	 }


}