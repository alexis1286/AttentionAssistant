package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;


public class Nav_Bar{
	/*
	 * creates specific great and purple as color objects
	 */
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	JButton toRefresh;
		
	/*
	 * variables
	 */
	private int x_coord;
	private int y_coord;
	private int size;
	private Color iconColor;
	private int iconOpacity;
	private Color circleColor;
	private int circleOpacity;
	private boolean isVert;
	private boolean isCollapsed;
	private boolean pomo_visible;
	private boolean ntb_visible;
	private boolean htb_visible;
	private boolean fts_visible;
	private boolean progress_visible;
	private boolean pomodoro_active;
	private boolean ntb_active;
	private boolean htb_active;
	private boolean fts_active;	
	
	/*
	 * instantiating empty Nav_Bar object
	 */
	public Nav_Bar() {
		this.x_coord = 0;
		this.y_coord = 0;
		this.size = 0;
		this.iconColor = Color.white;
		this.iconOpacity = 100;
		this.circleColor = aa_grey;
		this.circleOpacity = 0;
		this.isVert = false;
		this.isCollapsed = false;
		
		this.pomo_visible = false;
		this.ntb_visible = false;
		this.htb_visible = false;
		this.fts_visible = false;
		this.progress_visible = false;
		this.pomodoro_active = false;
		this.ntb_active = false;
		this.htb_active = false;
		this.fts_active = false;
	}

	/*
	 * create Nav_Bar with variables set by settings
	 */
	public Nav_Bar(Settings set) {
		this.x_coord = set.getXCoord();
		this.y_coord = set.getYCoord();
		this.size = set.getIconSize();
		this.iconColor = set.getIcons();
		this.iconOpacity = set.getOpacityIcons();
		this.circleColor = set.getIconCircles();
		this.circleOpacity =set.getOpacityCircles();
		this.isVert = set.getIsVertical();
		this.isCollapsed = set.getIsCollapsed();
		
		this.pomo_visible = set.getTimerIsVisible();
		this.ntb_visible = set.getNtbIsVisible();
		this.htb_visible = set.getHtbIsVisible();
		this.fts_visible = set.getFtsIsVisible();
		this.progress_visible = set.getProgReportIsVisible();
		
		this.pomodoro_active = set.getPomodoroIsActive();
		this.ntb_active = set.getNtbIsActive();
		this.htb_active = set.getHtbIsActive();
		this.fts_active = set.getFtsIsActive();
	}
	
	
	public void run_nav_bar(DataBase db,Nav_Bar navbar,Settings settings,Observer observer,Priority_Manager pm,Pomodoro_Timer pomo,Negative_Thought_Burner ntb,Happy_Thought_Button htb,Free_Thought_Space fts) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				//removes default title bar from frame 
		        frame.setUndecorated(true);
		        //sets background of frame to transparent
		        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		        //forces frame to stay on top of screen
		        frame.setAlwaysOnTop(true);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        //sets top-left corner coordinate, pulled from settings
		        frame.setLocation(x_coord, y_coord);
		        //makes frame and contents visible
		        frame.setVisible(true);
		        
		        //panel for buttons
		        JPanel iconPanel = iconPanel(db,navbar,settings,observer,pm,pomo,ntb,htb,fts,frame);
		        
		        frame.getContentPane().add(iconPanel);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(true);	
				
				toRefresh = new JButton();
		        toRefresh.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		JPanel newPanel = new JPanel();
		        		newPanel = iconPanel(db, navbar, settings, observer, pm, pomo, ntb, htb, fts, frame);
		        		frame.remove(iconPanel);
		        		frame.add(newPanel);
		        		System.out.println(iconColor);
		        		frame.revalidate();
		        	}
		        });
			}
		});
	}
	
	/*
	 * create panel that houses active & visible feature icons
	 */
	private JPanel iconPanel(DataBase db,Nav_Bar navbar,Settings settings,Observer observer,Priority_Manager pm, Pomodoro_Timer pomo, Negative_Thought_Burner ntb,Happy_Thought_Button htb,Free_Thought_Space fts,JFrame frame) {
		JPanel panel = new JPanel();
				
		//displays buttons vertically if true, horizontally is false
        if(isVert == true) {
        	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        }else {panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));}
        
        //displays only menu button until clicked if false
        if(isCollapsed == false) {
        	//displays all visible and active buttons
        	JButton settingsButton = createButton("images/setting_button.png",panel);
        	settingsButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open settings
	        		settings.open_settings(db, navbar, settings, observer, pm, pomo, ntb, htb, fts);
	        }});
        	panel.add(settingsButton);
        	
        	
        	JButton pmButton = createButton("images/manager.png",panel);
        	pmButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pm
	        		pm.open_pm(db,observer);
	        }});
    		panel.add(pmButton);
    		
    		
        	if(pomo_visible == true && pomodoro_active == true) {
        		JButton pomoButton = createButton("images/timer.png",panel);
        		pomoButton.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent e) {
    	        		//open pomo
    	        		pomo.run_pomo(settings);
    	        }});
        		panel.add(pomoButton);
        	
        	
        	}
			if(ntb_visible == true && ntb_active == true) {
				JButton ntbButton = createButton("images/burner.png",panel);
				ntbButton.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent e) {
    	        		//open ntb
    	        }});
				panel.add(ntbButton);
			
			
			}
			if(htb_visible == true && htb_active == true) {
				JButton htbButton = createButton("images/happy_button.png",panel);
				htbButton.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent e) {
    	        		//open htb
    	        }});
				panel.add(htbButton);
			
			
			}
        	if(fts_visible == true && fts_active == true) {
        		JButton ftsButton = createButton("images/thought_space.png",panel);
        		ftsButton.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent e) {
    	        		//open fts
    	        		fts.runFts(fts);
    	        }});
        		panel.add(ftsButton);
        	
        	
        	}
        	if(progress_visible == true) {
        		JButton progressButton = createButton("images/progress.png",panel);
        		progressButton.addActionListener(new ActionListener() {
    	        	public void actionPerformed(ActionEvent e) {
    	        		//open progress report
    	        }});
        		panel.add(progressButton);
        	
        	
        	}
        }else {
        	JButton menuButton = createButton("images/menu_button.png",panel);
        	menuButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//expand navbar
	        }});
        	panel.add(menuButton);
        
        
        }
        
        //sets background of panel to transparent
        panel.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		return panel;
	}
	
	private JButton createButton(String imgFile,JPanel panel) {
		JButton button = new JButton();
		
		BufferedImage img = null;
		BufferedImage circle = null;
		try {
			img = ImageIO.read(new File(imgFile));
			circle = ImageIO.read(new File("images/circle.png"));
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		colorIcon(img,iconColor,iconOpacity);
		colorCircle(circle,circleColor,circleOpacity);
		
		// create new image of icon image on top of circle image
        BufferedImage newImg = new BufferedImage(
        		70, 70,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = newImg.createGraphics();
        graphic.drawImage(circle, 0, 0, 70, 70, 0, 0, circle.getWidth(), circle.getHeight(), panel);
        graphic.drawImage(img,10,10,60,60,0,0,img.getWidth(),img.getHeight(), panel);
        graphic.dispose();
        
      //creates an ImageIcon
        ImageIcon icon = new ImageIcon(newImg);
        
        //scale and assign icon to button
        Image scaledImg = icon.getImage();
        scaledImg = scaledImg.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);
        button.setIcon(icon);
        //make non-icon area of button invisible
        button.setContentAreaFilled(false);
        //remove button border
        button.setBorderPainted(false);
        button.setFocusPainted(false);
		return button;
	}
	
	/*
	 * adjusts color and/or opacity of specified icon image to specified color/opacity
	 */
	public static BufferedImage colorIcon(BufferedImage image, Color color, int opacity) {
		//get new red, green, blue values from color
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		//get height and width of image to be altered
	    int width = image.getWidth();
	    int height = image.getHeight();
	    WritableRaster raster = image.getRaster();

	    //recolors image to new rgb values
	    for (int xx = 0; xx < width; xx++) {
	      for (int yy = 0; yy < height; yy++) {
	        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
	        pixels[0] = red;
	        pixels[1] = green;
	        pixels[2] = blue;
	        raster.setPixel(xx, yy, pixels);
	      }
	    }
	    
	  //alters opacity of image 
	    float o = (float)opacity / 100;
	    Graphics2D g2d = image.createGraphics();
	    AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o);
		g2d.setComposite(composite);
	    return image;
	  }
	
	/*
	 * adjusts color and/or opacity of circle image to specified color/opacity
	 */
	private static BufferedImage colorCircle(BufferedImage image, Color color, int opacity) {
		//get new red, green, blue values from color
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		//get height and width of image to be altered
	    int width = image.getWidth();
	    int height = image.getHeight();
	    WritableRaster raster = image.getRaster();

	    //recolors image to new rgb values
	    for (int xx = 0; xx < width; xx++) {
	      for (int yy = 0; yy < height; yy++) {
	        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
	        //rgb
	        pixels[0] = red;
	        pixels[1] = green;
	        pixels[2] = blue;
	        raster.setPixel(xx, yy, pixels);
	      }
	    }
	    
	    //alters opacity of image 
	    float o = (float)opacity / 100;
	    Graphics2D g2d = image.createGraphics();
	    AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, o);
		g2d.setComposite(composite);
	    return image;
	  }
	
	
	/*
	 * get changed settings
	 */
	public void refresh(Settings set) {
		this.x_coord = set.getXCoord();
		this.y_coord = set.getYCoord();
		this.size = set.getIconSize();
		this.iconColor = set.getIcons();
		this.iconOpacity = set.getOpacityIcons();
		this.circleColor = set.getIconCircles();
		this.circleOpacity =set.getOpacityCircles();
		this.isVert = set.getIsVertical();
		this.isCollapsed = set.getIsCollapsed();
		
		this.pomo_visible = set.getTimerIsVisible();
		this.ntb_visible = set.getNtbIsVisible();
		this.htb_visible = set.getHtbIsVisible();
		this.fts_visible = set.getFtsIsVisible();
		this.progress_visible = set.getProgReportIsVisible();
		
		this.pomodoro_active = set.getPomodoroIsActive();
		this.ntb_active = set.getNtbIsActive();
		this.htb_active = set.getHtbIsActive();
		this.fts_active = set.getFtsIsActive();
		toRefresh.doClick();
	}
}