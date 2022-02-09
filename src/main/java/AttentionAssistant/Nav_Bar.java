package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class Nav_Bar{
	/*
	 * creates specific great and purple as color objects
	 */
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	
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
	private boolean progress_active;
	
	static JFrame frame = new JFrame();
	
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
		this.progress_active = false;
	}

	/*
	 * create Nav_Bar with variables set by settings
	 */
	public Nav_Bar(Settings set) {
		this.x_coord = 0;//set.getXCoord();
		this.y_coord = 0;//set.getYCoord();
		this.size = 50;//set.getIconSize();
		this.iconColor = Color.white;//set.getIconColor();
		this.iconOpacity = 100;//set.getIconOpacity();
		this.circleColor = aa_grey;//set.getCircleColor();
		this.circleOpacity = 100;//set.getCircleOpacity();
		this.isVert = true;//set.getIsVert();
		this.isCollapsed = false;//set.getIsCollapsed();
		
		this.pomo_visible = true;//set.getTimerIsVisible();
		this.ntb_visible = true;//set.getNtbIsVisible();
		this.htb_visible = true;//set.getHtbIsVisible();
		this.fts_visible = true;//set.getFtsIsVisible();
		this.progress_visible = true;//set.getProgressIsVisible();
		
		this.pomodoro_active = true;//set.getTimerIsActive();
		this.ntb_active = true;//set.getNtbIsActive();
		this.htb_active = true;//set.getHtbIsActive();
		this.fts_active = true;//set.getFtsIsActive();
		this.progress_active = true;//set.getProgressIsActive();
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
	
	public void refresh() {
		frame.revalidate();
	}
	
	/*
	 * creates navbar 
	 */
	public static void run_nav_bar(DataBase db,Nav_Bar navbar,Settings settings,Observer observer,Priority_Manager pm,Pomodoro_Timer pomo,Negative_Thought_Burner ntb,Happy_Thought_Button htb,Free_Thought_Space fts) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
	        //removes default title bar from frame 
	        frame.setUndecorated(true);
	        //sets background of frame to transparent
	        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	        //forces frame to stay on top of screen
	        frame.setAlwaysOnTop(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //sets top-left corner coordinate, pulled from settings
	        frame.setLocation(navbar.getX_coord(), navbar.getY_coord());
	        //makes frame and contents visible
	        frame.setVisible(true);
	        
	        //gets circle and each icon image
	        BufferedImage settings_img = null;
	        BufferedImage pm_img = null;
	        BufferedImage pomo_img = null;
	        BufferedImage ntb_img = null;
	        BufferedImage htb_img = null;
	        BufferedImage fts_img = null;
	        BufferedImage progress_img = null;
	        BufferedImage menu_img = null;
	        BufferedImage circle = null;
	        try
	        {
	        settings_img = ImageIO.read(new File("images/setting_button.png"));
	        pm_img = ImageIO.read(new File("images/manager.png"));
	        pomo_img = ImageIO.read(new File("images/timer.png"));
	        ntb_img = ImageIO.read(new File("images/burner.png"));
	        htb_img = ImageIO.read(new File("images/happy_button.png"));
	        fts_img = ImageIO.read(new File("images/thought_space.png"));
	        progress_img = ImageIO.read(new File("images/progress.png"));
	        menu_img = ImageIO.read(new File("images/menu_button.png"));
	        circle = ImageIO.read(new File("images/circle.png"));
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	          System.exit(1);
	        }
	        
	        //recolors/sets opacity based on settings
	        colorIcon(settings_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(pm_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(pomo_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(ntb_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(htb_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(fts_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(progress_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorIcon(menu_img, navbar.getIconColor(), navbar.getIconOpacity());
	        colorCircle(circle, navbar.getCircleColor(), navbar.getCircleOpacity());
	        
	        // create new image of icon image on top of circle image for each icon
	        BufferedImage sbi = new BufferedImage(
	        		70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D sg = sbi.createGraphics();
	        sg.drawImage(circle, 0, 0, 70, 70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        sg.drawImage(settings_img,10,10,60,60,0,0,settings_img.getWidth(),settings_img.getHeight(), frame);
	    	sg.dispose();
	    	
	    	BufferedImage pmbi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D pmg = pmbi.createGraphics();
	        pmg.drawImage(circle, 0, 0, 70, 70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        pmg.drawImage(pm_img,10,10,60,60,0,0,pm_img.getWidth(),pm_img.getHeight(),null);
	    	pmg.dispose();
	        
	    	BufferedImage pomobi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D pomog = pomobi.createGraphics();
	        pomog.drawImage(circle, 0, 0, 70, 70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        pomog.drawImage(pomo_img,10,9,60,59,0,0,pomo_img.getWidth(),pomo_img.getHeight(),null);
	    	pomog.dispose();
	    	
	    	BufferedImage ntbbi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D ntbg = ntbbi.createGraphics();
	        ntbg.drawImage(circle, 0, 0, 70, 70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        ntbg.drawImage(ntb_img, 12, 9, 62, 59, 0, 0, ntb_img.getWidth(), ntb_img.getHeight(), null);
	    	ntbg.dispose();
	    	
	    	BufferedImage htbbi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D htbg = htbbi.createGraphics();
	        htbg.drawImage(circle, 0, 0, 70,70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        htbg.drawImage(htb_img,10,10,60,60,0,0,htb_img.getWidth(),htb_img.getHeight(),null);
	    	htbg.dispose();
	    	
	    	BufferedImage ftsbi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D ftsg = ftsbi.createGraphics();
	        ftsg.drawImage(circle, 0, 0, 70,70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        ftsg.drawImage(fts_img,15,15,55,55,0,0,fts_img.getWidth(),fts_img.getHeight(),null);
	    	ftsg.dispose();
	    	
	    	BufferedImage probi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D prog = probi.createGraphics();
	        prog.drawImage(circle, 0, 0, 70,70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        prog.drawImage(progress_img,10,10,60,55,0,0,progress_img.getWidth(),progress_img.getHeight(),null);
	    	prog.dispose();
	    	
	    	BufferedImage mbi = new BufferedImage(
	    			70, 70,BufferedImage.TYPE_INT_ARGB);
	        Graphics2D mg = mbi.createGraphics();
	        mg.drawImage(circle, 0, 0, 70,70, 0, 0, circle.getWidth(), circle.getHeight(), frame);
	        mg.drawImage(menu_img,10,10,60,60,0,0,menu_img.getWidth(),menu_img.getHeight(),null);
	    	mg.dispose();
	    	
	    	//creates an ImageIcon for each
	        ImageIcon settings_icon = new ImageIcon(sbi);
	        ImageIcon pm_icon = new ImageIcon(pmbi);
	        ImageIcon pomo_icon = new ImageIcon(pomobi);
	        ImageIcon ntb_icon = new ImageIcon(ntbbi);
	        ImageIcon htb_icon = new ImageIcon(htbbi);
	        ImageIcon fts_icon = new ImageIcon(ftsbi);
	        ImageIcon progress_icon = new ImageIcon(probi);
	        ImageIcon menu_icon = new ImageIcon(mbi);
	        
	        
	        //create button (does for each button)***
	        JButton settings_button = new JButton();
	        //adds on-click function
	        settings_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open settings
	        		settings.open_settings(db);
	        }});
	        //scale and assign icon to button
	        Image si = settings_icon.getImage();
	        si = si.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        settings_icon = new ImageIcon(si);
	        settings_button.setIcon(settings_icon);
	        //make non-icon area of button invisible
	        settings_button.setContentAreaFilled(false);
	        //remove button border
	        settings_button.setBorderPainted(false);
	        settings_button.setFocusPainted(false);//***
	        
	        
	        JButton pm_button = new JButton();
	        pm_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pm
	        		pm.open_pm(db,observer);
	        }});
	        Image pmi = pm_icon.getImage();
	        pmi = pmi.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        pm_icon = new ImageIcon(pmi);
	        pm_button.setIcon(pm_icon);
	        pm_button.setContentAreaFilled(false);
	        pm_button.setBorderPainted(false);
	        pm_button.setFocusPainted(false);
	        
	        
	        JButton pomo_button = new JButton();
	        pomo_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pomo
	        		//Pomodoro_Timer.run_pomo();
	        }});
	        Image pomoi = pomo_icon.getImage();
	        pomoi = pomoi.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        pomo_icon = new ImageIcon(pomoi);
	        pomo_button.setIcon(pomo_icon);
	        pomo_button.setContentAreaFilled(false);
	        pomo_button.setBorderPainted(false);
	        pomo_button.setFocusPainted(false);
	        
	        
	        JButton ntb_button = new JButton();
	        Image ntbi = ntb_icon.getImage();
	        ntbi = ntbi.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        ntb_icon = new ImageIcon(ntbi);
	        ntb_button.setIcon(ntb_icon);
	        ntb_button.setContentAreaFilled(false);
	        ntb_button.setBorderPainted(false);
	        ntb_button.setFocusPainted(false);
	        
	        
	        JButton htb_button = new JButton();
	        Image htbi = htb_icon.getImage();
	        htbi = htbi.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        htb_icon = new ImageIcon(htbi);
	        htb_button.setIcon(htb_icon);
	        htb_button.setContentAreaFilled(false);
	        htb_button.setBorderPainted(false);
	        htb_button.setFocusPainted(false);
	        
	        
	        JButton fts_button = new JButton();
	        fts_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open fts
	        		fts.runFts(fts);
	        }});
	        Image ftsi = fts_icon.getImage();
	        ftsi = ftsi.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        fts_icon = new ImageIcon(ftsi);
	        fts_button.setIcon(fts_icon);
	        fts_button.setContentAreaFilled(false);
	        fts_button.setBorderPainted(false);
	        fts_button.setFocusPainted(false);
	        
	        
	        
	        JButton progress_button = new JButton();
	        Image pri = progress_icon.getImage();
	        pri = pri.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        progress_icon = new ImageIcon(pri);
	        progress_button.setIcon(progress_icon);
	        progress_button.setContentAreaFilled(false);
	        progress_button.setBorderPainted(false);
	        progress_button.setFocusPainted(false);
	        
	        
	        JButton menu_button = new JButton();
	        Image menui = menu_icon.getImage();
	        menui = menui.getScaledInstance(navbar.getSize(), navbar.getSize(), java.awt.Image.SCALE_SMOOTH);
	        menu_icon = new ImageIcon(menui);
	        menu_button.setIcon(menu_icon);
	        menu_button.setContentAreaFilled(false);
	        menu_button.setBorderPainted(false);
	        menu_button.setFocusPainted(false);
	        
	        
	        //create new panel
	        JPanel panel = new JPanel();
	        //displays buttons vertically if true, horizontally is false
	        if(navbar.getIsVert() == true) {
	        	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        }else {panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));}
	        
	        //displays only menu button until clicked if false
	        if(navbar.getIsCollapsed() == false) {
	        	//displays all visible and active buttons
	        	panel.add(settings_button);
        		panel.add(pm_button);
	        	if(navbar.getPomo_visible() == true) {
	        		panel.add(pomo_button);
	        	}
				if(navbar.getNtb_visible() == true) {
					panel.add(ntb_button);
				}
				if(navbar.getHtb_visible() == true) {
					panel.add(htb_button);
				}
	        	if(navbar.getFts_visible() == true) {
	        		panel.add(fts_button);
	        	}
	        	if(navbar.getProgress_visible() == true) {
	        		panel.add(progress_button);
	        	}
	        }else {
	        	panel.add(menu_button);
	        }

	        //sets background of panel to transparent
	        panel.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	        //add panel to frame
	        frame.add(panel);
	        frame.pack();
	      }
	    });
	  }
	
	//**********************************************************************************************************
	//getters and setters for variables
  	private int getX_coord() {
		return x_coord;
	}
	public void setX_coord(int x) {
		x_coord = x;
	}
	private int getY_coord() {
		return y_coord;
	}
	public void setY_coord(int y) {
		y_coord = y;
	}
	private int getSize() {
		return size;
	}
	public void setSize(int s) {
		size =s;
	}
	private Color getIconColor() {
		return iconColor;
	}
	public void setIconColor(Color color) {
		iconColor = color;
		System.out.println(color);
	}
	private int getIconOpacity() {
		return iconOpacity;
	}
	public void setIconOpacity(int io) {
		iconOpacity = io;
	}
	private Color getCircleColor() {
		return circleColor;
	}
	public void setCircleColor(Color color) {
		circleColor = color;
	}
	private int getCircleOpacity() {
		return circleOpacity;
	}
	public void setCircleOpacity(int co) {
		circleOpacity = co;
	}
	private boolean getIsVert() {
		return isVert;
	}
	public void setIsVert(boolean vert) {
		isVert = vert;
	}
	private boolean getIsCollapsed() {
		return isCollapsed;
	}
	public void setIsCollapsed(boolean collapsed) {
		isCollapsed = collapsed;
	}
	private boolean getPomo_visible() {
		return pomo_visible;
	}
	public void setPomo_visible(boolean visible) {
		pomo_visible = visible;
	}
	private boolean getNtb_visible() {
		return ntb_visible;
	}
	public void setNtb_visible(boolean visible) {
		ntb_visible = visible;
	}
	private boolean getHtb_visible() {
		return htb_visible;
	}
	public void setHtb_visible(boolean visible) {
		htb_visible = visible;
	}
	private boolean getFts_visible() {
		return fts_visible;
	}
	public void setFts_visible(boolean visible) {
		fts_visible = visible;
	}
	private boolean getProgress_visible() {
		return progress_visible;
	}
	public void setProgress_visible(boolean visible) {
		progress_visible = visible;
	}
	private boolean getPomodoro_active() {
		return pomodoro_active;
	}
	public void setPomodoro_active(boolean active) {
		pomodoro_active = active;
	}
	private boolean getNtb_active() {
		return ntb_active;
	}
	public void setNtb_active(boolean active) {
		ntb_active = active;
	}
	private boolean getHtb_active() {
		return htb_active;
	}
	public void setHtb_active(boolean active) {
		htb_active = active;
	}
	private boolean getFts_active() {
		return fts_active;
	}
	public void setFts_active(boolean active) {
		fts_active = active;
	}
	private boolean getProgress_active() {
		return progress_active;
	}
}
