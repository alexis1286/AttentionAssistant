package AttentionAssistant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Negative_Thought_Burner {
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	private int mouseX;
	private int mouseY;
	int height = 600;
	int width = 600;


	private JPanel titlePanel(JFrame frame) {
		JPanel panel = new JPanel();
		//panel.setBorder(line);
		//aligns buttons in title panel from right -> left
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//makes title panel background grey
		panel.setBackground(aa_grey);
		//creates border and sets to purple
		panel.setBorder(BorderFactory.createLineBorder(aa_purple));
		//creates label 
		JLabel title = new JLabel("Negative Thought Burner");
		//makes font color white
		title.setForeground(Color.white);
		//sets font, size, and bold
		title.setFont(new Font("Serif", Font.BOLD, 18));
		
		//reads in images for the close and guide buttons
		BufferedImage ci = null;
		BufferedImage gi = null;
		try {
			ci = ImageIO.read(new File("images/exit_circle.png"));
			gi = ImageIO.read(new File("images/guide.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		/*
		 * allows drag and drop of frame
		 */
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
			}
		});
		
		panel.addMouseListener(new MouseAdapter(){
			@Override 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		//creates close button with close icon and no background
		Image c_img = ci.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		Icon close = new ImageIcon(c_img);
		JButton close_window = new JButton(close);
		close_window.setBorderPainted(false);
		close_window.setContentAreaFilled(false);
		close_window.setFocusPainted(false);
		close_window.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//close window without saving info
        		frame.dispose();
        }});
		
		//create guide button with guide icon and no background
		Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon guideIcon = new ImageIcon(g_img);
		JButton guide = new JButton(guideIcon);
		guide.setBorderPainted(false);
		guide.setContentAreaFilled(false);
		guide.setFocusPainted(false);
		
		//adds title JLabel, empty space, then guide button and close button
		panel.add(title);
		panel.add(Box.createRigidArea(new Dimension(275, 0)));
		panel.add(guide);
		panel.add(close_window);
		
		//returns panel
		return panel;
	}

	private JPanel thoughtPanel(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
	
		
	
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension(400,200)));
		
		
		
		//panel.add(buttonpanel);
	
		return panel;
	}
	JPanel icon_panel;
	public void run_ntb() {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
		
				
				//set up frame
				JFrame frame = new JFrame();
				frame.setUndecorated(true);
				//sets window width and height
			
				
				JPanel panel = new JPanel();
				//panel.setBounds(1000, 1000, width, height);
				panel.setBackground(aa_grey);
				//build title panel
				JPanel titlePanel = titlePanel(frame);
				titlePanel.setBorder(line);
				//build table panel
				icon_panel = thoughtPanel(frame);
				//icon_panel.setBorder(BorderFactory.createMatteBorder(0,2,2,2,aa_purple));
			    panel.add("PT", icon_panel);
				frame.getContentPane().add(titlePanel,BorderLayout.PAGE_START);
				frame.getContentPane().add(panel,BorderLayout.CENTER);
				frame.setPreferredSize(new Dimension(width, height)); 

				frame.pack();
				frame.setVisible(true);
				frame.setResizable(true);
				frame.setLocationRelativeTo(null);
				//Input(settings);
			
				
			}
		});
	}
	
	
	
}