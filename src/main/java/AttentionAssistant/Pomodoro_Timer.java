package AttentionAssistant;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.UIManager.*;




@SuppressWarnings("serial")
public class Pomodoro_Timer extends JFrame implements ActionListener
{
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	int breakmin =0,min = 0,sec=0 ,ms=0;
	boolean chk=false;
	private JButton lastButtonPressed;  

	public static int count=0,index=0;
	Container container = getContentPane();
	JLabel time = new JLabel("00m:00s");
	JButton startbut=new JButton("Start");
	JButton pausebut=new JButton("Pause");
	JButton endbut=new JButton("Reset");
	JLabel c=new JLabel("Pomodoro Timer");
	JLabel b=new JLabel("Break Timer");
	

	Timer t;
	public Pomodoro_Timer() {
		this.setTitle("Pomodoro Timer");
		this.setBounds(300, 60, 700, 600);
		this.setResizable(false);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(aa_grey);
		Initialize();
        addActionEvent();
    	Maininput();
		Breakinput();
        
		
		
	}
	private void Initialize() {
		
		time.setBounds(185,80, 300, 300);
		time.setForeground(Color.white);
		time.setFont(new Font("Dosis SemiBold",Font.BOLD,50));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(time);
		
		startbut.setBounds(100,400, 120, 40);
		startbut.setBorderPainted(false);
		startbut.setBackground(aa_purple);
		startbut.setForeground(Color.WHITE);
		startbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		container.add(startbut);
		
		
		pausebut.setBounds(260,400, 120, 40);
		pausebut.setBorderPainted(false);
		pausebut.setBackground(aa_purple);
		pausebut.setForeground(Color.WHITE);
		pausebut.setFont(new Font("San Francisco", Font.BOLD, 15));
		container.add(pausebut);
		
		endbut.setBounds(425,400, 120, 40);
		endbut.setBorderPainted(false);
		endbut.setBackground(aa_purple);
		endbut.setForeground(Color.WHITE);
		endbut.setFont(new Font("San Francisco", Font.BOLD, 15));
		container.add(endbut);
		
		
		
		c.setBounds(185,140, 300, 300);
		c.setForeground(Color.white);
		c.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		c.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(c);

		b.setBounds(185,140, 300, 300);
		b.setForeground(Color.white);
		b.setFont(new Font("Dosis SemiBold",Font.BOLD,20));
		b.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(b);
		
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
				MainTimer();
		
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
				
			}
			
		}
			
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
		tm.setVisible(true);
		
	}
}
