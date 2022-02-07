package AttentionAssistant;

/**
 *   JAVA DRAWING APP
 *   Ivana Zuber
 *   March 2013
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintToolPanel extends JPanel
{
    /**************************************************************************************************************
     *****************************************************VARIABLES************************************************
     **************************************************************************************************************/
    protected ToolButton toolButtons[];
    public StrokeToolPanel strokeToolPanel;
    private JComboBox fillerType;
    private ImageIcon pencil = new ImageIcon("images/pencil.png");
    private ImageIcon roundRectangle = new ImageIcon("images/polygon.png");
    private ImageIcon filledRoundRectangle = new ImageIcon("images/filled-polygon.png");
    private ImageIcon oval = new ImageIcon("images/oval.png");
    private ImageIcon filledOval = new ImageIcon("images/filled-oval.png");
    private ImageIcon rectangle = new ImageIcon("images/rectangle.png");
    private ImageIcon filledRectangle = new ImageIcon("images/filled-rectangle.png");
    private ImageIcon lineTool = new ImageIcon("images/line.png");
    private ImageIcon paintBrush = new ImageIcon("images/paint-brush.png");
    private ImageIcon eraser = new ImageIcon("images/eraser.png");
    private JPanel toolPanel = new JPanel();
    private ToolButton colorPickerButton;
    Free_Thought_Space paint;
    int size = 70;

    /**************************************************************************************************************
     ***************************************************CONSTRUCTOR************************************************
     **************************************************************************************************************/
    public PaintToolPanel(StrokeToolPanel strokeToolPanel,Free_Thought_Space p)
    {
    	paint = p;
        setBackground(Color.black);                          //customize the panel
        setPreferredSize(new Dimension(200, 0));
        setLayout(new BorderLayout(8, 8));

        toolPanel.setLayout(new GridLayout(4, 2));              //customize the tool panel
        toolPanel.setBackground(Color.black);
        toolPanel.setPreferredSize(new Dimension(200, 300));
        this.strokeToolPanel = strokeToolPanel;                 //set stroke panel
        toolButtons = new ToolButton[8];                        //create new array of buttons

        String[] fillerTypes = {"EMPTY SHAPES", "FILLED SHAPES"};             //create String array for the combo box
        fillerType = new JComboBox(fillerTypes);                //create the combo box
        ComboBoxHandler handler = new ComboBoxHandler();        //creating a new instance of the ComboBoxHandler inner class
        fillerType.addActionListener(handler);                  //add combo box listener
        fillerType.setFont(new Font("Cambria", Font.BOLD, 16)); //set font

        //****************************************COLOR PICKER BUTTON TOOL***************************************************************************************
        ImageIcon colorPicker = new ImageIcon("images/color-balls.png");
        Image editColorPicker = colorPicker.getImage();
        editColorPicker = editColorPicker.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newColorPicker = new ImageIcon(editColorPicker);
        
        colorPickerButton = new ToolButton(newColorPicker, ToolFactory.createTool(ToolFactory.PENCILL_TOOL),paint);
        colorPickerButton.addMouseListener(new MouseAdapter() {      //add a mouse listener

            public void mousePressed(MouseEvent event) {                //when the user presses the mouse button
                {                                                       //display the JColorChooser window
                    ColorPalette.selectedColorDisplay.setBackground(JColorChooser.showDialog(paint, "Change Color", paint.drawingPanel.brushColor));
                    ColorPalette.selectedColor = ColorPalette.selectedColorDisplay.getBackground();                                   //change the isSelected color
                    paint.drawingPanel.currentToolDetails.setColor(ColorPalette.selectedColorDisplay.getBackground());     //change the ToolDetails color
                    paint.drawingPanel.setCurrentColor(ColorPalette.selectedColor);                                   //change the DrawingPanel brushColor
                }
            }
        });


        addBasicToolButtons();                //fill basic button tools
        addEmptyShapeToolButtons();           //fill empty shape button tools
        for (ToolButton toolButton : toolButtons) toolPanel.add(toolButton);    //add buttons to tool panel

        add(toolPanel, "North");
        add(fillerType, "Center");
        add(strokeToolPanel, "South");
    }

    private void addBasicToolButtons()
    {
    	Image editPencil = pencil.getImage();
    	editPencil = editPencil.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newPencil = new ImageIcon(editPencil);
    	toolButtons[0] = new ToolButton(newPencil, ToolFactory.createTool(ToolFactory.PENCILL_TOOL),paint);
        
    	Image editBrush = paintBrush.getImage();
    	editBrush = editBrush.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newBrush = new ImageIcon(editBrush);
        toolButtons[1] = new ToolButton(newBrush, ToolFactory.createTool(ToolFactory.AIR_BRUSH_TOOL),paint);
        
        Image editEraser = eraser.getImage();
    	editEraser = editEraser.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newEraser = new ImageIcon(editEraser);
        toolButtons[2] = new ToolButton(newEraser, ToolFactory.createTool(ToolFactory.ERASER_TOOL),paint);
        
        toolButtons[3] = colorPickerButton;
        
        Image editLineTool = lineTool.getImage();
    	editLineTool  = editLineTool.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newLineTool = new ImageIcon(editLineTool);
        toolButtons[4] = new ToolButton(newLineTool, ToolFactory.createTool(ToolFactory.LINE_TOOL),paint);
    }

    private void addEmptyShapeToolButtons()
    {
    	Image editOval = oval.getImage();
    	editOval  = editOval.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newOval = new ImageIcon(editOval);
    	toolButtons[5] = new ToolButton(newOval, ToolFactory.createTool(ToolFactory.OVAL_TOOL),paint);
        
    	Image editRoundRectangle = roundRectangle.getImage();
    	editRoundRectangle  = editRoundRectangle.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newRoundRectangle = new ImageIcon(editRoundRectangle);
    	toolButtons[6] = new ToolButton(newRoundRectangle, ToolFactory.createTool(ToolFactory.ROUND_RECTANGLE_TOOL),paint);
        
    	Image editRectangle = rectangle.getImage();
    	editRectangle  = editRectangle.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newRectangle = new ImageIcon(editRectangle);
    	toolButtons[7] = new ToolButton(newRectangle, ToolFactory.createTool(ToolFactory.RECTANGLE_TOOL),paint);
    }

    private void addFilledShapeToolButtons()
    {
    	Image editFillOval = filledOval.getImage();
    	editFillOval  = editFillOval.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newFilledOval = new ImageIcon(editFillOval);
        toolButtons[5] = new ToolButton(newFilledOval, ToolFactory.createTool(ToolFactory.FILLED_OVAL_TOOL),paint);
        
        Image editFillRoundRectangle = filledRoundRectangle.getImage();
        editFillRoundRectangle  = editFillRoundRectangle .getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newFilledRoundRectangle = new ImageIcon(editFillRoundRectangle);
        toolButtons[6] = new ToolButton(newFilledRoundRectangle, ToolFactory.createTool(ToolFactory.FILLED_ROUND_RECTANGLE_TOOL),paint);
        
        Image editFillRectangle = filledRectangle.getImage();
        editFillRectangle  = editFillRectangle .getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        Icon newFilledRectangle = new ImageIcon(editFillRectangle);
        toolButtons[7] = new ToolButton(newFilledRectangle, ToolFactory.createTool(ToolFactory.FILLED_RECTANGLE_TOOL),paint);
    }

    /**************************************************************************************************************
     **********************************************COMBO BOX HANDLER************************************************
     **************************************************************************************************************/
    private class  ComboBoxHandler implements ActionListener  //combo box (filling type) even handling
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            fillerType = (JComboBox)e.getSource();
            int selectedValue = fillerType.getSelectedIndex();
            if (selectedValue ==0)          //if empty
            {
                for (ToolButton toolButton1 : toolButtons) toolPanel.remove(toolButton1);  //remove buttons
                revalidate();
                repaint();
                addBasicToolButtons();                      //add basic buttons
                addEmptyShapeToolButtons();                 //add empty shape buttons
                for (ToolButton toolButton : toolButtons) toolPanel.add(toolButton);
            }
            else if (selectedValue == 1 )  //if filled
            {
                for (ToolButton toolButton1 : toolButtons) toolPanel.remove(toolButton1); //remove all buttons
                revalidate();
                repaint();
                addBasicToolButtons();                     //add basic buttons
                addFilledShapeToolButtons();               //add filled buttons
                for (ToolButton toolButton : toolButtons) toolPanel.add(toolButton);
            }
        }
    }
}
