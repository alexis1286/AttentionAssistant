package AttentionAssistant;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.UIManager.*;
import javax.swing.border.LineBorder;



import java.io.File;


//pomodoro_timer.refresh
//TODO loggin system for how much user is using timer ie. get count/get timer

//TODO get status for timer


public class Pomodoro_Timer 
{
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);

	static int initalbreak = 0, initalmin = 0, breakmin =0,min= 0,sec=0 ,ms=0;
	static boolean MainTimerRunning,BreakTimerRunning,ptrunning = false;
	private JButton lastButtonPressed;  
	//private ArrayList<Task> Task_list = new ArrayList<Task> ();
	LineBorder line = new LineBorder(aa_purple, 2, true);

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
	
	/**
	 * main function, creates custom header, creates the jframe and calls the functions to run the timer
	 */
	public Pomodoro_Timer() {
		
		
	}
	/**
	 * initializes the buttons and adds them to the frame, and initializes the labels that are used depending on what timer is running
	 */
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
		startbut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

    			if(e.getSource()==startbut) {
    				
    				if (b.isVisible()) {
    					t.start();
    				}
    				else {
    				MainTimer();
    				}
    		
    			}	
    			JButton buttonPressed = (JButton) e.getSource();
    			if(lastButtonPressed == buttonPressed)
    			{
    				JFrame frame = new JFrame();
    				JOptionPane.showMessageDialog(frame, "Please do not push the same button twice.");
    			}
    			lastButtonPressed = buttonPressed;
        }});
		pt_frame.add(startbut);
		
		
		pausebut.setBounds(225,400, 120, 40);
		pausebut.setBorderPainted(false);
		pausebut.setBackground(aa_purple);
		pausebut.setForeground(Color.WHITE);
		pausebut.setFont(new Font("San Francisco", Font.BOLD, 15));
		pt_frame.add(pausebut);
		pausebut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JButton buttonPressed = (JButton) e.getSource();
        		if(e.getSource()==pausebut) {
    				if(t == null) {
    					//TODO inital timer begin pressing pause gives error beacuse min != 0
    					JFrame frame = new JFrame();
    					JOptionPane.showMessageDialog(frame, "Timer has not begun.");
    				}
    				else {
    					t.stop();
    				}
    				
    			}
        		if(lastButtonPressed == buttonPressed)
        		{
        			JFrame frame = new JFrame();
        			JOptionPane.showMessageDialog(frame, "Please do not push the same button twice.");
        		}
        		lastButtonPressed = buttonPressed;
        		
        }});
		
		endbut.setBounds(385,400, 120, 40);
		endbut.setBorderPainted(false);
		endbut.setBackground(aa_purple);
		endbut.setForeground(Color.WHITE);
		endbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		endbut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource()==endbut) {
        			JButton buttonPressed = (JButton) e.getSource();
        			if(t == null) {
    					//TODO inital timer begin pressing pause gives error beacuse min != 0
    					JFrame frame = new JFrame();
    					JOptionPane.showMessageDialog(frame, "Timer has not begun.");
    				}
    				else {
    				t.stop();
    				sec=min=0;
    				//settings.setWorkPeriod(0);
    				//settings.setBreakPeriod(0);
    				time.setText(String.valueOf("00m:00s"));
//    				Maininput();
//    				Breakinput(); //TODO redirect user back to settings to edit the timer input
    				c.setVisible(false);
    				b.setVisible(false);
    				
    				if(lastButtonPressed == buttonPressed)
    				{
    					JFrame frame = new JFrame();
    					JOptionPane.showMessageDialog(frame, "Please do not push the same button twice.");
    				}
    				
    				}
        			lastButtonPressed = buttonPressed;
        		
    			}
    			
        		
        }});
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
	/**
	 * input window that prompts the user to input their desired time for the work period
	 */
	public void Input(Settings settings) {
		
		int maintime;
		//int mainTimer = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter desired time in minutes:"));
		maintime = settings.getWorkPeriod();
		min = maintime;
		initalmin = min;
		//int breakTimer = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter desired break time in minutes:"));
		int breaktime;
		breaktime = settings.getBreakPeriod();
		breakmin = breaktime;
		initalbreak = breakmin;

		
	}
	
	public void refresh() {
		pt_frame.revalidate();
	}
	
	
	public boolean GetStatus() {
		if (getpt_active() == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean GetBreakTimerStatus() {
		if(BreakTimerRunning == true) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * main timer function. Creates the main pomodoro timer from user input and also ensures that the timer stops properly at 00:00
	 */
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
					 Object[] options = {"Yes","No"};
					 int initaltask = JOptionPane.showOptionDialog(null,
					             "Have you completed your task?",
					             "Tasks",
					             JOptionPane.YES_NO_CANCEL_OPTION,
					             JOptionPane.DEFAULT_OPTION,
					             null,
					             options,
					             options[1]);  

					 System.out.println(initaltask);  

					

					 if(initaltask==0){  //for yes; the user has finished their initial task and will need to pick/assign a new task, or terminate the timer
						 Object[] NewTask = {"Yes","No"}; //new option button to ask user if they have any other tasks to work on
						 int NewTaskInt = JOptionPane.showOptionDialog(null,
						             "Do you have any other tasks to work on?",
						             "Other Tasks",
						             JOptionPane.YES_NO_CANCEL_OPTION,
						             JOptionPane.DEFAULT_OPTION,
						             null,
						             NewTask,
						             NewTask[1]);  

						 System.out.println(NewTaskInt);  
						 if(NewTaskInt==0){ //for yes; i.e. user has another tasks to work on
							  //ask the user to assign what task they are currently working on (pulled from database) 
							 //TODO add another button where they can select their new task
							 //prompt the user to input a new break and work timespan allotment
							    t.stop();
								sec=min=0;
								time.setText(String.valueOf("00m:00s"));
								//Maininput(); //TODO this is going to need to redirect the user back to the setting
								//TODO add refresh here
								//Breakinput(); //this prompts the user for new work timespan allotments, will need to occur after new task is assigned
								c.setVisible(false);
								b.setVisible(false);
								MainTimer(); //begin timer with new work/break peroids
							 
							 }else if(NewTaskInt==1){ //for no, meaning that they have no new tasks to work on...
								 //(ask the user to assign a new task via priority manager)
								 //or terminate the pomodoro timer
										 Object[] NoNewTask = {"Add New Task","Close Pomodoro Timer"};
										 int NonewTaskInt = JOptionPane.showOptionDialog(null,
										             "Tasks",
										             "Have you completed your task?",
										             JOptionPane.YES_NO_CANCEL_OPTION,
										             JOptionPane.DEFAULT_OPTION,
										             null,
										             NoNewTask,
										             NoNewTask[1]);  
							
										 System.out.println(initaltask);  
								
										
								
										 if(NonewTaskInt==0){  //for yes
											 JFrame frame = new JFrame();
											JOptionPane.showMessageDialog(frame, "Please create a new task.");
											//TODO link priority manager so user can add new task
									      //deploy priority manager to have user assign a new task
										 }else if(NonewTaskInt==1){ //for Close Pomodoro Timer
											 pt_frame.dispose();
										 }else{ //none selected
										     System.out.println("no option choosen");
										 }
									
							 }else{ //none selected
							     System.out.println("no option choosen");
							 }

					
					 }else if(initaltask==1){ //for no
						 //break timer repeats; user has not finished inital task. 
						BreakTimer();
					 }else{ //none selected
					     System.out.println("no option choosen");
					 }
					
				
				}
				
				
				if(sec == -1) {
					sec =59;
					min--;
					ddsecond = dformat.format(sec);
					ddminute = dformat.format(min);
					time.setText(String.valueOf(ddminute+"m:"+ddsecond+"s"));	
					
					
				}
				
		
			}
			
		});


		if(min == 0 || breakmin == 0) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "You have not set your work and break peroids. Please go to settings to enable them.");
			lastButtonPressed = null;
		}else {
			t.start();
		}
	}
	/**
	 * break timer function. Creates the break  timer from user input and also ensures that the timer stops properly at 00:00
	 */
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
					b.setVisible(false);
					MainTimer();
					min = initalmin;
					
				
				}
				
				
				if(sec == -1) {
					sec =59;
					breakmin--;
					ddsecond = dformat.format(sec);
					ddminute = dformat.format(breakmin);
					time.setText(String.valueOf(ddminute+"m:"+ddsecond+"s"));	
					
					
				}
				
		
			}
			
		});

		if(min == 0 || breakmin == 0) {
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "You have not set your work and break peroids. Please go to settings to enable them.");
			lastButtonPressed = null;
			
		}else {
			t.start();
		}
	

	}
	
	public  void run_pomo(Settings settings) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				
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
			   
				//refresh();
				Initialize();
		    	Input(settings);
		    	
			}
		});
	}
	private boolean getpt_active() {
		return ptrunning;
	}
	public void setpt_active(boolean active) {
		ptrunning = active;
	}
}
