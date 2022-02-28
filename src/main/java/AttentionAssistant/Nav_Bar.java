package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.*;
import java.text.DecimalFormat;
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
	DecimalFormat df = new DecimalFormat("#.#"); 
	private int mouseX;
	private int mouseY;
		
	/*
	 * variables
	 */
	private int x_coord;
	private int y_coord;
	private int size;
	private Color iconColor;
	private float iconOpacity;
	private Color circleColor;
	private float circleOpacity;
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
	
	JPanel icon_panel;
	int counter;
	public void run_nav_bar(DataBase db,Nav_Bar navbar,Settings settings,Observer observer,Priority_Manager pm,Pomodoro_Timer pomo,Negative_Thought_Burner ntb,Happy_Thought_Button htb,Free_Thought_Space fts) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				counter = 1;
				JFrame frame = new JFrame();
				frame.setBounds(x_coord, y_coord, 1000, 1000);
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
		        
		        CardLayout cardLayout = new CardLayout();
		        JPanel panel = new JPanel();
		        panel.setBounds(x_coord, y_coord, 1000, 1000);
		        panel.setLayout(cardLayout);
		        
		        //panel for buttons
		        icon_panel = iconPanel(db,navbar,settings,observer,pm,pomo,ntb,htb,fts,frame);
		        panel.add("iPanel", icon_panel);
		        cardLayout.show(panel, "iPanel");
		        frame.getContentPane().add(panel);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(true);
				
				toRefresh = new JButton();
		        toRefresh.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		JPanel new_icon_panel = new JPanel();
		        		if(counter % 2 != 0) {
		        			new_icon_panel = iconPanel(db, navbar, settings, observer, pm, pomo, ntb, htb, fts, frame);
		        			panel.add("newIPanel",new_icon_panel);
		        			cardLayout.show(panel, "newIPanel");
		        			panel.remove(icon_panel);
		        		}else {
		        			icon_panel = iconPanel(db, navbar, settings, observer, pm, pomo, ntb, htb, fts, frame);
		        			panel.add("iPanel",icon_panel);
		        			cardLayout.show(panel, "iPanel");
		        			panel.remove(new_icon_panel);
		        		}
		        		counter++;
		        		panel.revalidate();
		        		panel.repaint();
		        		frame.revalidate();
		        		frame.repaint();
		        	}
		        });
			}
		});
	}
	
	private BoxLayout vertlayout(JPanel pan) {
		BoxLayout bl;
		bl = new BoxLayout(pan,BoxLayout.Y_AXIS);
		return bl;
	}
	
	private BoxLayout horizlayout(JPanel pan) {
		System.out.println(isVert);
		BoxLayout bl;
		bl = new BoxLayout(pan,BoxLayout.X_AXIS);
		return bl;
	}
	
	/*
	 * create panel that houses active & visible feature icons
	 */
	private JPanel iconPanel(DataBase db,Nav_Bar navbar,Settings settings,Observer observer,Priority_Manager pm, Pomodoro_Timer pomo, Negative_Thought_Burner ntb,Happy_Thought_Button htb,Free_Thought_Space fts,JFrame frame) {
		JPanel panel = new JPanel();
		BoxLayout layout;
		//displays buttons vertically if true, horizontally is false
		if(isVert == true) {
			layout = vertlayout(panel);
		}else {layout = horizlayout(panel);}
        panel.setLayout(layout);
        
        //displays only menu button until clicked if false
        if(isCollapsed == false) {
        	//displays all visible and active buttons
        	JButton settingsButton = createButton("images/setting_button.png",panel);
        	settingsButton.setFocusPainted(false);
        	settingsButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open settings
	        		settings.open_settings(db, navbar, settings, observer, pm, pomo, ntb, htb, fts);
	        }});
        	settingsButton.addMouseListener(new MouseAdapter() {
        		public void mouseReleased(MouseEvent e) {
        			
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
    	        		htb.open_htb();
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
		
		
		float iconOpac = iconOpacity/100;
		colorIcon(img);
		BufferedImage imgOpac = new BufferedImage(size, size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D gicon = imgOpac.createGraphics();
        gicon.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, iconOpac));
        gicon.drawImage(img, 0, 0, size, size, 0, 0, img.getWidth(), img.getHeight(), panel);
		
        
        float circleOpac = circleOpacity/100;
		colorCircle(circle);
		BufferedImage circOpac = new BufferedImage(size, size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D gcirc = circOpac.createGraphics();
        gcirc.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, circleOpac));
        gcirc.drawImage(circle, 0, 0, size, size, 0, 0, circle.getWidth(), circle.getHeight(), panel);
		
		
		
		// create new image of icon image on top of circle image
        BufferedImage newImg = new BufferedImage(
        		size, size,BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = newImg.createGraphics();
        graphic.drawImage(circOpac, 0, 0, size, size, 0, 0, circOpac.getWidth(), circOpac.getHeight(), panel);
        graphic.drawImage(imgOpac,10,10,(size-10),(size-10),0,0,imgOpac.getWidth(),imgOpac.getHeight(), panel);
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
        button.setRolloverEnabled(false);
		return button;
	}
	
	/*
	 * adjusts color and/or opacity of specified icon image to specified color/opacity
	 */
	public BufferedImage colorIcon(BufferedImage image) {
		//get new red, green, blue values from color
		int red = iconColor.getRed();
		int green = iconColor.getGreen();
		int blue = iconColor.getBlue();
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
	    return image;
	  }
	
	/*
	 * adjusts color and/or opacity of circle image to specified color/opacity
	 */
	private BufferedImage colorCircle(BufferedImage image) {
		//get new red, green, blue values from color
		int red = circleColor.getRed();
		int green = circleColor.getGreen();
		int blue = circleColor.getBlue();
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