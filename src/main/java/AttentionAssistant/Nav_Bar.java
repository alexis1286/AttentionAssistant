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
	private int size = 70;
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
	        
	        ImageIcon settings_icon = new ImageIcon(settings_img);
	        ImageIcon pm_icon = new ImageIcon(pm_img);
	        ImageIcon pomo_icon = new ImageIcon(pomo_img);
	        ImageIcon ntb_icon = new ImageIcon(ntb_img);
	        ImageIcon htb_icon = new ImageIcon(htb_img);
	        ImageIcon fts_icon = new ImageIcon(fts_img);
	        ImageIcon progress_icon = new ImageIcon(progress_img);
	        ImageIcon menu_icon = new ImageIcon(menu_img);
	        
	        
	        
	        JButton settings_button = new JButton();
	        settings_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open settings
	        		JFrame settings = new JFrame("Attention Assistant Settings");
	        		settings.setVisible(true);
	        		settings.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }});
	        Image si = settings_icon.getImage();
	        si = si.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        settings_icon = new ImageIcon(si);
	        settings_button.setIcon(settings_icon);
	        settings_button.setContentAreaFilled(true);
	        settings_button.setBorderPainted(false);
	        
	        
	        JButton pm_button = new JButton();
	        settings_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pm
	        		Priority_Manager pm = new Priority_Manager();
	        		pm.open_pm();
	        }});
	        Image pmi = pm_icon.getImage();
	        pmi = pmi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        pm_icon = new ImageIcon(pmi);
	        pm_button.setIcon(pm_icon);
	        pm_button.setContentAreaFilled(true);
	        pm_button.setBorderPainted(false);
	        
	        JButton pomo_button = new JButton();
	        pomo_button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		//open pm
	        		Pomodoro_Timer.run_pomo();
	        }});
	        Image pomoi = pomo_icon.getImage();
	        pomoi = pomoi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        pomo_icon = new ImageIcon(pomoi);
	        pomo_button.setIcon(pomo_icon);
	        pomo_button.setContentAreaFilled(true);
	        pomo_button.setBorderPainted(false);
	        
	        JButton ntb_button = new JButton();
	        Image ntbi = ntb_icon.getImage();
	        ntbi = ntbi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        ntb_icon = new ImageIcon(ntbi);
	        ntb_button.setIcon(ntb_icon);
	        ntb_button.setContentAreaFilled(false);
	        ntb_button.setBorderPainted(false);
	        
	        JButton htb_button = new JButton();
	        Image htbi = htb_icon.getImage();
	        htbi = htbi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        htb_icon = new ImageIcon(htbi);
	        htb_button.setIcon(htb_icon);
	        htb_button.setContentAreaFilled(false);
	        htb_button.setBorderPainted(false);
	        
	        JButton fts_button = new JButton();
	        Image ftsi = fts_icon.getImage();
	        ftsi = ftsi.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        fts_icon = new ImageIcon(ftsi);
	        fts_button.setIcon(fts_icon);
	        fts_button.setContentAreaFilled(false);
	        fts_button.setBorderPainted(false);
	        
	        
	        JButton progress_button = new JButton();
	        Image pri = progress_icon.getImage();
	        pri = pri.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        progress_icon = new ImageIcon(pri);
	        progress_button.setIcon(progress_icon);
	        progress_button.setContentAreaFilled(false);
	        progress_button.setBorderPainted(false);
	        
	        JButton menu_button = new JButton();
	        Image menui = menu_icon.getImage();
	        menui = menui.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
	        menu_icon = new ImageIcon(menui);
	        menu_button.setIcon(menu_icon);
	        menu_button.setContentAreaFilled(false);
	        menu_button.setBorderPainted(false);
	        
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