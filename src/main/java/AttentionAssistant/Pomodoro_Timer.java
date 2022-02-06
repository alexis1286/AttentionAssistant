package AttentionAssistant;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.UIManager.*;
import javax.swing.border.LineBorder;
import java.io.File;




@SuppressWarnings("serial")
public class Pomodoro_Timer implements ActionListener
{
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	int breakmin =0,min = 0,sec=0 ,ms=0;
	boolean chk=false;
	private JButton lastButtonPressed;  
	LineBorder line = new LineBorder(aa_purple, 2, true);

	public static int count=0,index=0;
	//Container container = getContentPane();
	JLabel time = new JLabel("00m:00s");
	JButton startbut=new JButton("Start");
	JButton pausebut=new JButton("Pause");
	JButton endbut=new JButton("Reset");
	JLabel c=new JLabel("Pomodoro Timer");
	JLabel b=new JLabel("Break Timer");
	JFrame pt_frame = new JFrame("AttentionAssistant Pomodoro Timer");

	int height = 600;
	int width = 600;

	Timer t;
	public Pomodoro_Timer() {
		
 		pt_frame.setUndecorated(true);
		
		JMenuBar title_panel = new JMenuBar();
        title_panel.setBorder(line);
        //aligns buttons in title panel from right -> left
        title_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //makes title panel background grey
        title_panel.setBackground(aa_grey);
        //creates border and sets to purple
        title_panel.setBorder(BorderFactory.createLineBorder(aa_purple));
        //creates label 
        JLabel title = new JLabel("Attention Assistant Pomodoro Timer");
        //makes font color white
        title.setForeground(Color.white);
        //sets font, size, and bold
        title.setFont(new Font("Serif", Font.BOLD, 14));
        
        BufferedImage ci = null;
        BufferedImage gi = null;
        try {
            ci = ImageIO.read(new File("images/exit_circle.png"));
            gi = ImageIO.read(new File("images/guide.png"));
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
        	
        			pt_frame.dispose();
        		
        		//close window without saving info
        }});
    
        Image g_img = gi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        Icon guideIcon = new ImageIcon(g_img);
        JButton guide = new JButton(guideIcon);
        guide.setBorderPainted(false);
        guide.setContentAreaFilled(false);
        guide.setFocusPainted(false);
        
        
        
        title_panel.add(title);
        title_panel.add(Box.createRigidArea(new Dimension(200, 0)));
        title_panel.add(guide);
        title_panel.add(close_window);
        
      
        //sets window width and height
        pt_frame.setPreferredSize(new Dimension(width, height));
        pt_frame.getContentPane().add(title_panel, BorderLayout.PAGE_START);
        pt_frame.getContentPane().setBackground(aa_grey);
        pt_frame.pack();
        pt_frame.setAlwaysOnTop(false);
		pt_frame.setVisible(true);
		pt_frame.setResizable(true);
		pt_frame.setLocationRelativeTo(null);
	   
	
		Initialize();
        addActionEvent();
    	Maininput();
		Breakinput();
        
		
		
	}
	private void Initialize() {
		
		time.setBounds(130,80, 300, 300);
		time.setForeground(Color.white);
		time.setFont(new Font("Dosis SemiBold",Font.BOLD,50));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		pt_frame.add(time);
		
		startbut.setBounds(60,400, 120, 40);
		startbut.setBorderPainted(false);
		startbut.setBackground(aa_purple);
		startbut.setForeground(Color.WHITE);
		startbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		pt_frame.add(startbut);
		
		
		pausebut.setBounds(225,400, 120, 40);
		pausebut.setBorderPainted(false);
		pausebut.setBackground(aa_purple);
		pausebut.setForeground(Color.WHITE);
		pausebut.setFont(new Font("San Francisco", Font.BOLD, 15));
		pt_frame.add(pausebut);
		
		endbut.setBounds(385,400, 120, 40);
		endbut.setBorderPainted(false);
		endbut.setBackground(aa_purple);
		endbut.setForeground(Color.WHITE);
		endbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		pt_frame.add(endbut);
		
		
		
		c.setBounds(120,140, 300, 300);
		c.setForeground(Color.white);
		c.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		c.setHorizontalAlignment(SwingConstants.CENTER);
		pt_frame.add(c);

		b.setBounds(120,140, 300, 300);
		b.setForeground(Color.white);
		b.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		b.setHorizontalAlignment(SwingConstants.CENTER);
		pt_frame.add(b);
		
		c.setVisible(false);
		b.setVisible(false);

		
	}
	
	
	
	private void addActionEvent() {
		startbut.addActionListener(this);
		endbut.addActionListener(this);
		pausebut.addActionListener(this);
	
		
	}
	
	public void Maininput() {
		int mainTimer = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter desired time in minutes:"));
		min = mainTimer;
		
		if(mainTimer == 0) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.");
		}
	}
	public void Breakinput() {
		int breakTimer = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter desired break time in minutes:"));
		breakmin = breakTimer;
		
		if(breakTimer == 0) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Invalid input. Please try again.");
		}
	}
	
	
	
	public void MainTimer() {
			
		c.setVisible(true);
		t = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String  ddsecond,ddminute;
				DecimalFormat dformat = new DecimalFormat("00");
				
				
				sec--;
				
				ddsecond = dformat.format(sec);
				ddminute = dformat.format(min);
				time.setText(String.valueOf(ddminute+"m:"+ddsecond+"s"));
				if(min == 0 && sec==0) {
					
					t.stop();
					c.setVisible(false);
					BreakTimer();
				
				}
				
				
				if(sec == -1) {
					sec =59;
					min--;
					ddsecond = dformat.format(sec);
					ddminute = dformat.format(min);
					time.setText(String.valueOf(ddminute+"m:"+ddsecond+"s"));	
					chk = false;
					
				}
				
		
			}
			
		});

		t.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		JButton buttonPressed = (JButton) e.getSource();
		if(lastButtonPressed == buttonPressed)
		{
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Please do not push the same button twice.");
		}
		else
		{
			if(e.getSource()==startbut) {
				
				if (b.isVisible()) {
					t.start();
				}
				else {
				MainTimer();
				}
		
			}
			if(e.getSource()==pausebut) {
				if(min == 0 && sec==0) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Timer has ended.");
				}
				else {
					t.stop();
				}
				
			}
			if(e.getSource()==endbut) {
				t.stop();
				sec=min=count=0;
				time.setText(String.valueOf("00m:00s"));
				Maininput();
				Breakinput();
				c.setVisible(false);
				b.setVisible(false);
				
			}
			
		}
		lastButtonPressed = buttonPressed;
	}
		
	public void BreakTimer() {
		b.setVisible(true);
		
		t = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String  ddsecond,ddminute;
				DecimalFormat dformat = new DecimalFormat("00");
				
				
				sec--;
				
				ddsecond = dformat.format(sec);
				ddminute = dformat.format(breakmin);
				time.setText(String.valueOf(ddminute+"m:"+ddsecond+"s"));
				if(breakmin == 0 && sec==0) {
					
					t.stop();
				
				
				}
				
				
				if(sec == -1) {
					sec =59;
					breakmin--;
					ddsecond = dformat.format(sec);
					ddminute = dformat.format(breakmin);
					time.setText(String.valueOf(ddminute+"m:"+ddsecond+"s"));	
					chk = false;
					
				}
				
		
			}
			
		});

		t.start();

	}
	public static void run_pomo()
	{
	
		Pomodoro_Timer tm = new Pomodoro_Timer();

		
	}
	
}
