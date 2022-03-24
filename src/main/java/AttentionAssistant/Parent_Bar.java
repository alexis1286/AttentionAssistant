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


public class Parent_Bar{
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	private int height = 700; 
	private int width = 550;
	private int count;
	private int counter;
	JButton toRefresh;
	private ArrayList<User_Account> childAccounts;
	private Parent_Account parent;
	private DataBase db;
	
	public Parent_Bar() {
		this.childAccounts = new ArrayList<User_Account>();
	}
	public Parent_Bar(Parent_Account p, DataBase db) {
		this.parent = p;
		this.childAccounts = db.Select_All_Users_Linked_Account(parent);
		this.db = db;
	}
	
	JPanel child_panel;
	public void run_parent_bar() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				counter = 1;
				count = 0;
				JFrame frame = new JFrame();
				frame.setBounds(0, 0, 1000, 1000);
				//removes default title bar from frame 
		        frame.setUndecorated(true);
		        //sets background of frame to transparent
		        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		        //forces frame to stay on top of screen
		        frame.setAlwaysOnTop(true);
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        //sets top-left corner coordinate, pulled from settings
		        frame.setLocation(0, 0);
		        //makes frame and contents visible
		        frame.setVisible(true);
		        CardLayout cardLayout = new CardLayout();
		        JPanel panel = new JPanel();
		        panel.setBounds(0, 0, 1000, 1000);
		        panel.setLayout(cardLayout);
		        
		        //panel for buttons
		        child_panel = childPanel(db,cardLayout, panel, frame);
		        panel.add("iPanel", child_panel);
		        cardLayout.show(panel, "iPanel");
		        frame.getContentPane().add(panel);
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(true);
				
				toRefresh = new JButton();
		        toRefresh.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		rebuildPanel(db,cardLayout,panel,frame);
		        }});
				
			}
		});
	}
	
	private JPanel childPanel(DataBase db,CardLayout cardLayout,JPanel panel,JFrame frame) {
		JPanel cPanel = new JPanel();
		if(childAccounts.size() != 0) {
			//display button for each linked child - opens child management
	        for(int i=0;i<childAccounts.size();i++) {
	        	String imgString;
	        	int childID = childAccounts.get(i).getUserID();
	        	Settings settings = new Settings(db,childID);
	        	Parent_Child_Menu menu = new Parent_Child_Menu(settings,db);
	        	imgString = settings.getAvatarFilePath();
	        	JButton button = createButton(imgString);
	        	JLabel label = new JLabel(childAccounts.get(i).getName(),SwingConstants.CENTER);
	        	label.setVerticalAlignment(SwingConstants.BOTTOM);
	        	button.add(label);
	        	
	        	button.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		menu.child_menu();
	            	}});
	        	cPanel.add(button);
	        }
		}
		String accountString = "images/am.png";
		JButton account = createButton(accountString);
		
		account.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		accountManage();
        	}});
		cPanel.add(account);
		cPanel.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		return cPanel;
	}
	
	private void accountManage() {
		JFrame frame = new JFrame();
		frame.setBackground(Color.black);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		//set up title panel************************************
		//JPanel titlePanel = titlePanel();
		
		//access guide*************************************
		//change password
		//Link child account
		JPanel linkPanel = linkPanel();
		
		//frame.add(titlePanel);
		frame.getContentPane().add(linkPanel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
	}
	
	private JPanel linkPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel pwChangeO = new JPanel();
		pwChangeO.setLayout(new FlowLayout(FlowLayout.LEFT));
		pwChangeO.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		pwChangeO.setMaximumSize(new Dimension(200,40));
		JLabel oldPassword = new JLabel("Old password: ");
		oldPassword.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		oldPassword.setForeground(aa_purple);
		oldPassword.setBackground(Color.black);
		oldPassword.setMaximumSize(new Dimension(75,30));
		JTextArea oldPw = new JTextArea("                    ");
		oldPw.setBackground(aa_grey);
		oldPw.setForeground(aa_purple);
		oldPw.setMaximumSize(new Dimension(100,30));
		oldPw.setCaretColor(Color.white);
		
		pwChangeO.add(oldPassword);
		pwChangeO.add(oldPw);
		
		JPanel pwChangeN = new JPanel();
		pwChangeN.setLayout(new FlowLayout(FlowLayout.LEFT));
		pwChangeN.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		pwChangeN.setMaximumSize(new Dimension(200,40));
		JLabel newPassword = new JLabel("New password: ");
		newPassword.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		newPassword.setForeground(aa_purple);
		newPassword.setBackground(Color.black);
		newPassword.setMaximumSize(new Dimension(75,30));
		JTextArea newPw = new JTextArea("                    ");
		newPw.setBackground(aa_grey);
		newPw.setForeground(aa_purple);
		newPw.setMaximumSize(new Dimension(100,30));
		newPw.setCaretColor(Color.white);
		
		pwChangeN.add(newPassword);
		pwChangeN.add(newPw);
		
		JButton button = new JButton("change password");
		button.setBackground(aa_purple);
		button.setForeground(Color.white);
		button.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		button.setMaximumSize(new Dimension(160,30));
		button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(oldPw.getText().replaceAll("\\s", "") == parent.getPassword().replaceAll("\\s", "") && newPw != null) {
        			String np = newPw.getText().replaceAll("\\s", "");
        			parent.setPassword(np);
        		}else if(oldPw.getText() != parent.getPassword()){
        			JFrame newFrame = new JFrame();
        			JOptionPane.showMessageDialog(newFrame, "Password incorrect");
        		}else if(newPw == null){
        			JFrame newFrame = new JFrame();
        			JOptionPane.showMessageDialog(newFrame, "New password cannot be empty");
        		}else {
        			JFrame newFrame = new JFrame();
        			JOptionPane.showMessageDialog(newFrame, "ERROR");
        		}
        	}});
		
		//make form 
		//field for username
		JPanel linkingU = new JPanel();
		linkingU.setLayout(new FlowLayout(FlowLayout.LEFT));
		linkingU.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		linkingU.setMaximumSize(new Dimension(200,40));
		JLabel username = new JLabel("Child username: ");
		username.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		username.setForeground(aa_purple);
		username.setMaximumSize(new Dimension(75,30));
		JTextArea un = new JTextArea("                    ");
		un.setBackground(aa_grey);
		un.setForeground(aa_purple);
		un.setMaximumSize(new Dimension(100,30));
		
		linkingU.add(username);
		linkingU.add(un);
		
		//field for password
		JPanel linkingP = new JPanel();
		linkingP.setLayout(new FlowLayout(FlowLayout.LEFT));
		linkingP.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		linkingP.setMaximumSize(new Dimension(200,40));
		JLabel password = new JLabel("Child password: ");
		password.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		password.setForeground(aa_purple);
		password.setMaximumSize(new Dimension(75,30));
		JTextArea pw = new JTextArea("                    ");
		pw.setBackground(aa_grey);
		pw.setForeground(aa_purple);
		pw.setMaximumSize(new Dimension(100,30));
		
		linkingP.add(password);
		linkingP.add(pw);
		
		//button links
		JButton link = new JButton("link account");
		link.setBackground(aa_purple);
		link.setForeground(Color.white);
		link.setFont(new Font("TimesRoman", Font.BOLD | Font.PLAIN, 16));
		link.setMaximumSize(new Dimension(120,30));
		link.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String childUsername = un.getText();
        		String childPassword = pw.getText();
        		User_Account account = db.SearchUser_Account(childUsername, childPassword);
        		db.AddLinked_Account(parent, account);
        		refresh();
        		un.setText("");
        		pw.setText("");
        	}});
		
		
		panel.add(pwChangeO);
		panel.add(pwChangeN);
		panel.add(button);
		panel.add(linkingU);
		panel.add(linkingP);
		panel.add(link);
		
		return panel;
	}
	
	private JButton createButton(String imgString) {
		JButton button = new JButton();
		int size = 50;
		BufferedImage img = null;
		BufferedImage circle = null;
		try {
			//gets image for specific buttons icon
			img = ImageIO.read(new File(imgString));
			img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
			//gets circle image
			circle = ImageIO.read(new File("images/circle.png"));
			circle.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
		
		//colorIcon(img);
		colorCircle(circle);
		
		BufferedImage imgOpac = new BufferedImage(size, size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D gicon = imgOpac.createGraphics();
        gicon.drawImage(img, 0, 0, size, size, 0, 0, img.getWidth(), img.getHeight(), null);
		
        
		BufferedImage circOpac = new BufferedImage(size, size,BufferedImage.TYPE_INT_ARGB);
		Graphics2D gcirc = circOpac.createGraphics();
        gcirc.drawImage(circle, 0, 0, size, size, 0, 0, circle.getWidth(), circle.getHeight(), null);
        
		int m = (int)(size*(15.0f/100.0f));
		// create new image of icon image on top of circle image
        BufferedImage newImg = new BufferedImage(size, size,img.getType());
        Graphics2D graphic = newImg.createGraphics();
        graphic.drawImage(circOpac, 0, 0, size, size, 0, 0, circOpac.getWidth(), circOpac.getHeight(), null);
        graphic.drawImage(imgOpac,m,m,size-m,size-m,0,0,imgOpac.getWidth(),imgOpac.getHeight(), null);
        graphic.dispose();
		
		//creates an ImageIcon
        ImageIcon icon = new ImageIcon(newImg);
        button.setIcon(icon);
        //make non-icon area of button invisible
        button.setContentAreaFilled(false);
        //remove button border
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(false);
        button.setMargin(new Insets(0,0,0,0));
		
		return button;
	}
	
	public BufferedImage colorIcon(BufferedImage image) {
		Color iconColor = Color.white;
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
		Color circleColor = aa_grey;
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
	
	
	private void rebuildPanel(DataBase db,CardLayout cardLayout,JPanel panel,JFrame frame) {
		JPanel new_child_panel = new JPanel();
		if(counter % 2 != 0) {
			new_child_panel = childPanel(db,cardLayout,panel,frame);
			panel.add("newIPanel",new_child_panel);
			cardLayout.show(panel, "newIPanel");
			panel.remove(child_panel);
		}else {
			child_panel = childPanel(db,cardLayout,panel,frame);
			panel.add("iPanel",child_panel);
			cardLayout.show(panel, "iPanel");
			panel.remove(new_child_panel);
		}
		counter++;
		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
	}
	
	public void refresh() {
		
		count=0;
		toRefresh.doClick();
	}
}