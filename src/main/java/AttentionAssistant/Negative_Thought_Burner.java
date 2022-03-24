package AttentionAssistant;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Negative_Thought_Burner {
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	LineBorder line = new LineBorder(aa_purple, 2, true);
	private int mouseX;
	private int mouseY;
	int height = 600;
	int width = 600;
	JFrame frame;
	JPanel WelcomePanel;
	CardLayout card;
	JPanel cardPane;
	 
	private JMenuBar titlePanel(JFrame frame) {
		JMenuBar title_panel = new JMenuBar();
		title_panel.setBorder(line);
		title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));	
		title_panel.setBackground(aa_grey);
		title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
		
		/*
		 * allows drag and drop of frame
		 */
		title_panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
			}
		});
		
		title_panel.addMouseListener(new MouseAdapter(){
			@Override 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});

		JLabel title = new JLabel("Negative Thought Burner");
		title.setForeground(Color.white);
		title.setBounds(0,0,200,200);
		title.setFont(new Font("Dosis SemiBold", Font.BOLD, 20));
		
		/*
		 * create icons to use as buttons for title bar
		 */
		BufferedImage ci = null;
		BufferedImage gi = null;
		BufferedImage exit = null;
		
		try {
			ci = ImageIO.read(new File("images/exit_circle.png"));
			gi = ImageIO.read(new File("images/guide.png"));
			exit = ImageIO.read(new File("images/AA_exit.png"));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		Image c_img = ci.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);
		Icon close = new ImageIcon(c_img);
		
		JButton close_window = new JButton(close);
		close_window.setBorderPainted(false);
		close_window.setContentAreaFilled(false);
		close_window.setFocusPainted(false);
		close_window.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//close window without saving 
        		frame.dispose();
        	
        }});
		
		Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		Icon guideIcon = new ImageIcon(g_img);
		
		JButton guide = new JButton(guideIcon);
		guide.setBorderPainted(false);
		guide.setContentAreaFilled(false);
		guide.setFocusPainted(false);
		
		title_panel.add(title);
		title_panel.add(Box.createRigidArea(new Dimension(275, 0)));
		title_panel.add(guide);
		title_panel.add(close_window);
		
		//returns panel
		return title_panel;
		
	}

	private JPanel welcomepage(CardLayout card, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		panel.setLayout(null);
		JButton burnbut=new JButton("Burn!");
		JLabel burn1 = new JLabel("Something bugging you?");
		JLabel burn2 = new JLabel("Watch it burn away!");

		burn1.setBounds(240, 50, 280, 100);
		burn1.setForeground(Color.WHITE);
		burn1.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		panel.add(burn1);
		
		
		burn2.setBounds(260, 80, 280, 100);
		burn2.setForeground(Color.WHITE);
		burn2.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		panel.add(burn2);
		
		
		
		JTextArea firstnametext = new JTextArea(500, 250);
		firstnametext.setBorder(BorderFactory.createLineBorder(Color.RED));
		//firstnametext.setBounds(100, 200, 500, 250);
	    firstnametext.setBackground(new Color(237,221,246));
        firstnametext.setFont(new Font("Dosis SemiBold", Font.PLAIN, 15));
        firstnametext.setLineWrap(true);
        firstnametext.setWrapStyleWord(true);
        panel.add(firstnametext);
        
        
        JScrollPane scroll = new JScrollPane(firstnametext,
	                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        panel.add(scroll);

        burnbut.setBounds(300, 480, 97, 35);
        burnbut.setHorizontalTextPosition(SwingConstants.CENTER);
		 burnbut.setVerticalTextPosition(SwingConstants.CENTER);
		 burnbut.setFont(new Font("Dosis SemiBold", Font.BOLD, 17));
		 burnbut.setBorderPainted(false);
		 burnbut.setBackground(Color.WHITE);
		 burnbut.setForeground(aa_purple);
		 burnbut.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	//burn thought 
	        	
	        }});
		 panel.add(burnbut);
		 
        
        //TODO if autolink is enabled in settings it opens right into htb 
		return panel;
	}
	JPanel icon_panel;
	public void run_ntb() {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
		
				
				JFrame frame = new JFrame();
				frame.setUndecorated(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// TODO add title panel frame.setUndecorated(true);
				frame.setTitle("The Attention Assistant");
		        frame.setBackground(aa_grey);
		        frame.setPreferredSize(new Dimension(700, 600)); 
		        //makes frame and contents visible
		    
		        
		        JMenuBar titlePanel = titlePanel(frame);
		        
		        card = new CardLayout();
		        WelcomePanel = new JPanel();
		        cardPane = new JPanel();
		        
		        WelcomePanel = welcomepage(card,frame);
		    	
		    	
		    	cardPane.setLayout(card);
		        cardPane.setBounds(300, 50, 700, 600);
		    	cardPane.add(WelcomePanel, "Welcome Panel");
		        
		            
		        
		        cardPane.addMouseListener(new MouseAdapter(){
					@Override 
					public void mousePressed(MouseEvent e) {
						mouseX = e.getX();
						mouseY = e.getY();
					}
				});
		    	frame.getContentPane().add(titlePanel,BorderLayout.PAGE_START);
		        frame.getContentPane().add(cardPane);
		        frame.setPreferredSize(new Dimension(700, 600)); 
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);

				
			}
		});
	}
	
	
	
}