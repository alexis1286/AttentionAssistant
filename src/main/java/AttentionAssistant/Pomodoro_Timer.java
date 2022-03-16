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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.io.File;


//pomodoro_timer.refresh
//TODO loggin system for how much user is using timer ie. get count/get timer

//TODO get status for timer


public class Pomodoro_Timer 
{
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);

	int initalbreak = 0, initalmin = 0, breakmin =0,min= 0,sec=0;
	private boolean MainTimerRunning;
	private boolean BreakTimerRunning;
	private boolean paused;
	private boolean pomodoro_active;
	private JButton lastButtonPressed;  
	JButton toRefresh;
	LineBorder line = new LineBorder(aa_purple, 2, true);

	JLabel time = new JLabel("00m:00s");
	JButton startbut=new JButton("Start");
	JButton pausebut=new JButton("Pause");
	JButton endbut=new JButton("Reset");
	JLabel c=new JLabel("Pomodoro Timer");
	JLabel b=new JLabel("Break Timer");
	//JFrame pt_frame = new JFrame("AttentionAssistant Pomodoro Timer");

	
	private int mouseX;
	private int mouseY;
	int height = 600;
	int width = 600;

	Timer t;
	
	/*
	 * instantiating empty timer object
	 */
	public Pomodoro_Timer() {
		this.MainTimerRunning = false;
		this.BreakTimerRunning = false;
		this.paused = false;
		this.pomodoro_active = false;
		this.lastButtonPressed = null;
	}
	public boolean GetMainTimerStatus() {
		if (MainTimerRunning == true) {
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
	
	public enum Work_Break {
		Work,
		Break,
		Paused
	}
	public Work_Break getWorkBreakStatus(){
		
		  
		if (BreakTimerRunning == true && MainTimerRunning == false) {
			System.out.println("break");
			return Work_Break.Break;
			 
		}
		else if (MainTimerRunning == true && BreakTimerRunning == false){
			System.out.println("Work");
			return Work_Break.Work;
		}
		else if (paused == true){
			System.out.println("Paused");
			return Work_Break.Paused;	
		}
		else {
			System.out.println("Null");
			return null;
		}

	}

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
		JLabel title = new JLabel("Pomodoro Timer");
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
	
	private JPanel timerPanel(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setBackground(aa_grey);
		
		JPanel taskpanel = new JPanel();
		taskpanel.setBackground(aa_grey);
		//c.setBounds(120,140, 300, 300);
		c.setForeground(Color.white);
		c.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		//c.setHorizontalAlignment(SwingConstants.CENTER);
		taskpanel.add(c);
		//title_panel.setLayout(new FlowLayout(FlowLayout.CENTER));	
		//title_panel.setBackground(aa_grey);
	

		//JLabel title = new JLabel("Task");
	//	title.setForeground(Color.white);
	//	title.setFont(new Font("Serif", Font.BOLD, 20));
		
		
		JPanel timerpanel = new JPanel();
		timerpanel.setBackground(aa_grey);
		
		//time.setBounds(130,80, 300, 300);
		time.setForeground(Color.white);
		time.setFont(new Font("Dosis SemiBold",Font.BOLD,50));
		timerpanel.add(time);
		
		JPanel buttonpanel = new JPanel();
		
		buttonpanel.setBackground(aa_grey);
		//startbut.setBounds(60,400, 120, 40);
		startbut.setBorderPainted(false);
		startbut.setBackground(aa_purple);
		startbut.setForeground(Color.WHITE);
		startbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		startbut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
    		
        		JButton buttonPressed = (JButton) e.getSource();
    			if(lastButtonPressed == buttonPressed)
    			{
    				//JOptionPane.showMessageDialog(frame, "Please do not push the same button twice.");
    				System.out.print(1);
    			
    			}
    			else {

        			if(e.getSource()==startbut) {
        				MainTimerRunning = true;
        				BreakTimerRunning = false;
        				paused = false;
        				if (c.isVisible()) {
        					t.start();
        					getWorkBreakStatus();
        				}
        				else {
        				MainTimer();
        				//getWorkBreakStatus();
        			
        				}
        		
        			}	
    			}
    			
    			lastButtonPressed = buttonPressed;
    			//TODO reset to null as one of the reset functions
        }});
		buttonpanel.add(startbut);

		//pausebut.setBounds(225,400, 120, 40);
		pausebut.setBorderPainted(false);
		pausebut.setBackground(aa_purple);
		pausebut.setForeground(Color.WHITE);
		pausebut.setFont(new Font("San Francisco", Font.BOLD, 15));
		pausebut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JButton buttonPressed = (JButton) e.getSource();
        	
       		if(lastButtonPressed == buttonPressed)
        		{        			
       			System.out.print(1);
        		}
       		else {
       			if(e.getSource()==pausebut) {
       				paused = true;
    				if(t == null) {
    					//TODO inital timer begin pressing pause gives error beacuse min != 0
    					JFrame frame = new JFrame();
    					JOptionPane.showMessageDialog(frame, "Timer has not begun.");
    				}
    				else {
    					t.stop();
    					if(BreakTimerRunning == true) {
    						BreakTimerRunning = false;
    						getWorkBreakStatus();
    					}
    					else if (MainTimerRunning  == true) {
    						MainTimerRunning = false;
    						getWorkBreakStatus();
  
    					}
   
    				}
    				
    			}
       		}
       			
        		paused = false;
        		lastButtonPressed = buttonPressed;
        		
        }});
		buttonpanel.add(pausebut);
		
		//endbut.setBounds(385,400, 120, 40);
		endbut.setBorderPainted(false);
		endbut.setBackground(aa_purple);
		endbut.setForeground(Color.WHITE);
		endbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		endbut.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JButton buttonPressed = (JButton) e.getSource();
   				if(lastButtonPressed == buttonPressed){
   					System.out.print(1);
    				}
   				else {
   					if(e.getSource()==endbut) {
   	        		
   	        			if(t == null) {
   	    					//TODO inital timer begin pressing pause gives error beacuse min != 0
   	    					JFrame frame = new JFrame();
   	    					JOptionPane.showMessageDialog(frame, "Timer has not begun.");
   	    					getWorkBreakStatus();
   	    				}
   	    				else {
   	    				t.stop();
   	    				sec=min=0;
   	    				time.setText(String.valueOf("00m:00s"));
//   	    				Maininput();
//   	    				Breakinput(); //TODO redirect user back to settings to edit the timer input
   	    				c.setVisible(false);
   	    				b.setVisible(false);
   	    				MainTimerRunning = false;
   	    				BreakTimerRunning = false;
   	    				paused = false;
   	    				getWorkBreakStatus();
   	    				
   	    				}	
   				}
    				
    				
        			lastButtonPressed = buttonPressed;
        		
    			}
    			
        		
        }});
		buttonpanel.add(endbut);
		
		JPanel timerwordpanel = new JPanel();
		timerwordpanel.setBackground(aa_grey);
		//c.setBounds(120,140, 300, 300);
		c.setForeground(Color.white);
		c.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		//c.setHorizontalAlignment(SwingConstants.CENTER);
		timerwordpanel.add(c);

		//b.setBounds(120,140, 300, 300);
		b.setForeground(Color.white);
		b.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		//b.setHorizontalAlignment(SwingConstants.CENTER);
		timerwordpanel.add(b);
		
		c.setVisible(false);
		b.setVisible(false);
		
		//FIX THIS
		//title_panel.setLayout(new BoxLayout(title_panel, BoxLayout.Y_AXIS));
		//title_panel.add(title);
		//title_panel.add(Box.createRigidArea(new Dimension(200, 100)));
		//buttonpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//buttonpanel.setMaximumSize(new Dimension(400, 100));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createRigidArea(new Dimension(400,200)));
		
		panel.add(timerpanel);
		panel.add(timerwordpanel);
		panel.add(buttonpanel);
		return panel;
	}
	
	/**
	 * break timer function. Creates the break  timer from user input and also ensures that the timer stops properly at 00:00
	 */
	public void BreakTimer() {
	
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
		if(min == 0 && breakmin == 0) {
			JFrame errorframe = new JFrame();
			JOptionPane.showMessageDialog(errorframe, "You have not set your work and break peroids. Please go to settings to enable them.");
			lastButtonPressed = null;
			BreakTimerRunning = false;
			MainTimerRunning = false;
			b.setVisible(false);
			//getWorkBreakStatus();
			
		}else {
			t.start();
			b.setVisible(true);
			BreakTimerRunning = true;
			MainTimerRunning = false;
			paused = false;
			min = initalmin;
			breakmin = initalbreak;
			
			//getWorkBreakStatus();
		}
	

	}
	/**
	 * main timer function. Creates the main pomodoro timer from user input and also ensures that the timer stops properly at 00:00
	 */
	public void MainTimer() {
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
					MainTimerRunning = false;
					BreakTimerRunning = false;
					paused = false;
					getWorkBreakStatus();
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
							
								//TODO open the priority manager/do drop down
							 
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
							
									 
								
										
								
										 if(NonewTaskInt==0){  //for yes
											 JFrame frame = new JFrame();
											JOptionPane.showMessageDialog(frame, "Please create a new task.");
											//TODO link priority manager so user can add new task
									      //deploy priority manager to have user assign a new task
										 }else if(NonewTaskInt==1){ //for Close Pomodoro Timer
											System.exit(1);
										 }else{ //none selected
										     System.out.println("no option choosen");
										 }
									
							 }else{ //none selected
							     System.out.println("no option choosen");
							 }

					
					 }else if(initaltask==1){ //for no
						 //break timer repeats; user has not finished inital task. 
						BreakTimer();
						MainTimerRunning = false;
						BreakTimerRunning = false;
						paused = false;
						getWorkBreakStatus();
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


		if(min == 0 && breakmin == 0) {
			JFrame errorframe = new JFrame();
			JOptionPane.showMessageDialog(errorframe, "You have not set your work and break peroids. Please go to settings to enable them.");
			lastButtonPressed = null;
			c.setVisible(false);
			BreakTimerRunning = false;
			MainTimerRunning = false;
			paused = false;
			getWorkBreakStatus();
		}else {
			t.start();
			c.setVisible(true);
			BreakTimerRunning = false;
			MainTimerRunning = true;
			paused = false;
			getWorkBreakStatus();
			
		}
	}
	
	JPanel icon_panel;
	int counter;
	public void rebuildPanel( JPanel panel, JFrame frame) {
		JPanel new_icon_panel = new JPanel();	
		if(counter % 2 != 0) {
			new_icon_panel = timerPanel( frame);
			panel.add("newIPanel",new_icon_panel);
			panel.remove(icon_panel);
		}else {
			icon_panel = timerPanel(frame);
			panel.add("iPanel",panel);

			panel.remove(new_icon_panel);
		}
		counter++;
		panel.revalidate();
		panel.repaint();
		frame.revalidate();
		frame.repaint();
	}
	
	/**
	 * initializes the buttons and adds them to the frame, and initializes the labels that are used depending on what timer is running
	 */
	public void run_pomo(Settings settings) {
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				counter = 1;
				
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
				icon_panel = timerPanel(frame);
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
				getWorkBreakStatus();
			
				toRefresh = new JButton();
		        toRefresh.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		rebuildPanel(panel, frame);
		        	}});
			}
		});
	}
	
	public void refresh(Settings settings){
		int maintime;
		int breaktime;
		//TODO stop the timer and reset everything 
		maintime = settings.getWorkPeriod();
		this.min = maintime;
		initalmin = min;
		breaktime = settings.getBreakPeriod();
		this.breakmin = breaktime;
		initalbreak = breakmin;
		this.pomodoro_active = settings.getPomodoroIsActive();
		
		if (counter == 0 ) {
			//do nothing
		}
		else {
		toRefresh.doClick();
		}
	}

}