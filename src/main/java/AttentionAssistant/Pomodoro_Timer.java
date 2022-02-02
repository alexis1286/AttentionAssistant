package AttentionAssistant;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

import javax.swing.UIManager.*;


@SuppressWarnings("serial")
public class Pomodoro_Timer extends JFrame implements ActionListener
{
	Color aa_grey = new Color(51,51,51);
	Color aa_purple = new Color(137,31,191);
	

	public static int count=0,index=0;
	Container container = getContentPane();
	JLabel time = new JLabel("00:00");
	JButton startbut=new JButton("Start");
	JButton pausebut=new JButton("Pause");
	JButton endbut=new JButton("Reset");

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
	
		
		
	}
	
	
	
	private void addActionEvent() {
		startbut.addActionListener(this);
		endbut.addActionListener(this);
		pausebut.addActionListener(this);
		
	}
	int sec=0,h=0;
	boolean chk=false;
	private JButton lastButtonPressed;  
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
				chk=true;
				t=new Timer(10, new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						time.setText(String.valueOf(h+"m:"+sec+"s"));
						sec++;
					
						
						if(sec==60) {
							h++;
							sec=0;	
						}
					}
					
				});
				
				t.start();
				
			}
			
		}
		lastButtonPressed = buttonPressed;
		if(e.getSource()==endbut) {
			if(chk==true)t.stop();
			sec=h=count=0;
			time.setText(String.valueOf(h+"m:"+sec+"s"));
		}
		if(e.getSource()==pausebut) {
			if(chk==true)t.stop();
		}
			
			
		
		
	}
	public static void run_pomo()
	{
		Pomodoro_Timer tm = new Pomodoro_Timer();
		tm.setVisible(true);
	}
}
