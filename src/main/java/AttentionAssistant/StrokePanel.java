package AttentionAssistant;

/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import java.awt.*;

public class StrokePanel extends JPanel
{
    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
    public StrokePanel()
    {
        setPreferredSize(new Dimension(84, 84));   //set size of the stroke panel
    }

    /**************************************************************************************************************
     ***************************************************METHODS*****************************************************
     **************************************************************************************************************/
    public void paintComponent(Graphics g,Free_Thought_Space paint)   //overrides method in JComponent and paints the stroke
    {
        g.setColor(paint.drawingPanel.currentToolDetails.getColor());
        g.fillRect(getWidth() / 2 - paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2,
                getHeight() / 2 - paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2,
                paint.drawingPanel.currentToolDetails.getStrokeWidth(),
                paint.drawingPanel.currentToolDetails.getStrokeWidth());
        g.setColor(Color.black);
        g.drawRect(getWidth() / 2 - paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2,
                getHeight() / 2 - paint.drawingPanel.currentToolDetails.getStrokeWidth() / 2,
                paint.drawingPanel.currentToolDetails.getStrokeWidth(),
                paint.drawingPanel.currentToolDetails.getStrokeWidth());
  }
}