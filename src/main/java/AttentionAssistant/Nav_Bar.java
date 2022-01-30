package AttentionAssistant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


public class Nav_Bar{
	private int x_coord = 0,y_coord = 0;
	private int size = 50;
	//deal with color changes
	
	private boolean isVert = true;
	
	private boolean isCollapsed = false;
	private boolean pm_active = true;
	private boolean pomodoro_active = true;
	private boolean ntb_active = true;
	private boolean htb_active = true;
	private boolean fts_active = true;
	private boolean progress_active = true;
	
	JFrame frame = new JFrame();
	public void set_coords(int x, int y) {
		x_coord = x;
		y_coord = y;
	}
	
	public void make_main_button() {
		
	}
	public void make_settings_button() {
		
	}
	
	public static void main(String[] args) throws Exception {
	    new Nav_Bar();
	  }
	
	private static BufferedImage colorIcon(BufferedImage image) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	    WritableRaster raster = image.getRaster();

	    for (int xx = 0; xx < width; xx++) {
	      for (int yy = 0; yy < height; yy++) {
	        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
	        pixels[0] = 255;
	        pixels[1] = 255;
	        pixels[2] = 255;
	        raster.setPixel(xx, yy, pixels);
	      }
	    }
	    return image;
	  }
	
	private static BufferedImage colorCircle(BufferedImage image) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	    WritableRaster raster = image.getRaster();

	    for (int xx = 0; xx < width; xx++) {
	      for (int yy = 0; yy < height; yy++) {
	        int[] pixels = raster.getPixel(xx, yy, (int[]) null);
	        pixels[0] = 56;
	        pixels[1] = 56;
	        pixels[2] = 54;
	        raster.setPixel(xx, yy, pixels);
	      }
	    }
	    return image;
	  }
	
	  public Nav_Bar() throws Exception {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        
	        frame.setUndecorated(true);
	        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	        frame.setAlwaysOnTop(true);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocation(x_coord, y_coord);
	        frame.setVisible(true);
	        
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
	        colorIcon(settings_img);
	        colorIcon(pm_img);
	        colorIcon(pomo_img);
	        colorIcon(ntb_img);
	        colorIcon(htb_img);
	        colorIcon(fts_img);
	        colorIcon(progress_img);
	        colorIcon(menu_img);
	        colorCircle(circle);
	        
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
	    	
	        ImageIcon settings_icon = new ImageIcon(sbi);
	        ImageIcon pm_icon = new ImageIcon(pmbi);
	        ImageIcon pomo_icon = new ImageIcon(pomobi);
	        ImageIcon ntb_icon = new ImageIcon(ntbbi);
	        ImageIcon htb_icon = new ImageIcon(htbbi);
	        ImageIcon fts_icon = new ImageIcon(ftsbi);
	        ImageIcon progress_icon = new ImageIcon(probi);
	        ImageIcon menu_icon = new ImageIcon(mbi);
	        
	        
	        
	        JButton settings_button = new JButton();
	        settings_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open settings
	        		JFrame settings = new JFrame("Attention Assistant Settings");
	        		settings.setVisible(true);
	        		settings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        }});
	        Image si = settings_icon.getImage();
	        si = si.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        settings_icon = new ImageIcon(si);
	        settings_button.setIcon(settings_icon);
	        settings_button.setContentAreaFilled(false);
	        settings_button.setBorderPainted(false);
	        settings_button.setFocusPainted(false);
	        
	        
	        JButton pm_button = new JButton();
	        pm_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pm
	        		Priority_Manager pm = new Priority_Manager();
	        		pm.open_pm();
	        }});
	        Image pmi = pm_icon.getImage();
	        pmi = pmi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        pm_icon = new ImageIcon(pmi);
	        pm_button.setIcon(pm_icon);
	        pm_button.setContentAreaFilled(false);
	        pm_button.setBorderPainted(false);
	        pm_button.setFocusPainted(false);
	        
	        JButton pomo_button = new JButton();
	        pomo_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pomo
	        		Pomodoro_Timer.run_pomo();
	        }});
	        Image pomoi = pomo_icon.getImage();
	        pomoi = pomoi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        pomo_icon = new ImageIcon(pomoi);
	        pomo_button.setIcon(pomo_icon);
	        pomo_button.setContentAreaFilled(false);
	        pomo_button.setBorderPainted(false);
	        pomo_button.setFocusPainted(false);
	        
	        JButton ntb_button = new JButton();
	        Image ntbi = ntb_icon.getImage();
	        ntbi = ntbi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        ntb_icon = new ImageIcon(ntbi);
	        ntb_button.setIcon(ntb_icon);
	        ntb_button.setContentAreaFilled(false);
	        ntb_button.setBorderPainted(false);
	        ntb_button.setFocusPainted(false);
	        
	        JButton htb_button = new JButton();
	        Image htbi = htb_icon.getImage();
	        htbi = htbi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        htb_icon = new ImageIcon(htbi);
	        htb_button.setIcon(htb_icon);
	        htb_button.setContentAreaFilled(false);
	        htb_button.setBorderPainted(false);
	        htb_button.setFocusPainted(false);
	        
	        JButton fts_button = new JButton();
	        Image ftsi = fts_icon.getImage();
	        ftsi = ftsi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        fts_icon = new ImageIcon(ftsi);
	        fts_button.setIcon(fts_icon);
	        fts_button.setContentAreaFilled(false);
	        fts_button.setBorderPainted(false);
	        fts_button.setFocusPainted(false);
	        
	        
	        JButton progress_button = new JButton();
	        Image pri = progress_icon.getImage();
	        pri = pri.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        progress_icon = new ImageIcon(pri);
	        progress_button.setIcon(progress_icon);
	        progress_button.setContentAreaFilled(false);
	        progress_button.setBorderPainted(false);
	        progress_button.setFocusPainted(false);
	        
	        JButton menu_button = new JButton();
	        Image menui = menu_icon.getImage();
	        menui = menui.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        menu_icon = new ImageIcon(menui);
	        menu_button.setIcon(menu_icon);
	        menu_button.setContentAreaFilled(false);
	        menu_button.setBorderPainted(false);
	        menu_button.setFocusPainted(false);
	        
	        JPanel panel = new JPanel();
	        if(isVert == true) {
	        	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        }else {panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));}
	        
	        if(isCollapsed == false) {
	        	panel.add(settings_button);
	        	if(pm_active == true) {
	        		panel.add(pm_button);
	        	}
	        	if(pomodoro_active == true) {
	        		panel.add(pomo_button);
	        	}
				if(ntb_active == true) {
					panel.add(ntb_button);
				}
				if(htb_active == true) {
					panel.add(htb_button);
				}
	        	if(fts_active == true) {
	        		panel.add(fts_button);
	        	}
	        	if(progress_active == true) {
	        		panel.add(progress_button);
	        	}
	        }else {
	        	panel.add(menu_button);
	        }

	        panel.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	        frame.add(panel);
	        frame.pack();
	      }
	    });
	  }
}