package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import AttentionAssistant.Pomodoro_Timer.Work_Break;

public class Test_Pomodoro_Timer {
	Pomodoro_Timer defaultPT;
	Pomodoro_Timer nondefaultPT;
	Pomodoro_Timer copyPT;
	
	
	@BeforeEach
	void setup() {
		
		Color aa_grey = new Color(51,51,51);
		Color aa_purple = new Color(137,31,191);

		int initalbreak = 0, initalmin = 0, breakmin = 0, min = 0, sec = 0;
		boolean testMainTimerRunning = true;
		boolean testBreakTimerRunning = true;
		boolean testPaused = true;
		boolean testPomodoro_active = true;
		JButton testLastButtonPressed;  
		JButton testToRefresh;
		LineBorder line = new LineBorder(aa_purple, 2, true);

//		JLabel time = new JLabel("00m:00s");
//		JButton startbut=new JButton("Start");
//		JButton pausebut=new JButton("Pause");
//		JButton endbut=new JButton("Reset");
//		JLabel c=new JLabel("Pomodoro Timer");
//		JLabel b=new JLabel("Break Timer");
//		JFrame pt_frame = new JFrame("AttentionAssistant Pomodoro Timer");

		
		int testMouseX = 0;
		int testMouseY = 0;
		int testHeight = 600;
		int testWidth = 600;

		Timer t;
		
		defaultPT = new Pomodoro_Timer(); 
		
		nondefaultPT = new Pomodoro_Timer();
		
		copyPT = new Pomodoro_Timer();
	}
	
    @Test
    @DisplayName("<Pomodoro_Timer> Default Constructor")
    void PomodoroTimerDefaultConstructor() {
    	
    	/**
         *  Make sure the Pomodoro Timer Boolean MainTimerRunning status is false for the default constructor
         */
        assertEquals(false, defaultPT.getMainTimerRunning(), 
        "Default constructor Pomodoro_Timer.MainTimerRunning should be false. Returned: "
        + String.valueOf(defaultPT.getMainTimerRunning()));
        
        /**
         *  Make sure the Pomodoro Timer Boolean BreakTimerRunning status is false for the default constructor
         */
        assertEquals(false, defaultPT.getBreakTimerRunning(), 
        "Default constructor Pomodoro_Timer.BreakTimerRunning should be false. Returned: "
        + String.valueOf(defaultPT.getBreakTimerRunning()));
        
        /**
         *  Make sure the Pomodoro Timer Boolean Paused Status is false for the default constructor
         */
        assertEquals(false, defaultPT.getPaused(), 
        "Default constructor Pomodoro_Timer.paused should be false. Returned: "
        + String.valueOf(defaultPT.getPaused()));
        
        /**
         *  Make sure the Pomodoro Timer Boolean Pomodoro_Active status is false for the default constructor
         */
        assertEquals(false, defaultPT.getPomodoro_active(), 
        "Default constructor Pomodoro_Timer.pomodoro_active should be false. Returned: "
        + String.valueOf(defaultPT.getPomodoro_active()));  
    
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Pomodoro_Timer> Parameter Constructor")
    void PomodoroTimerParameterConstructor() {
    /**
     * place holder
     */
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Pomodoro_Timer> Copy Constructor")
    void PomodoroTimerCopyConstructor() {
    /**
     * place holder
     */
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Pomodoro_Timer> getWorkBreakStatus")
    void testGetWorkBreakStatus() {
    
    	/**
         *  Set Boolean BreakTimerRunning = true and Boolean MainTimerRunning = false
         */
    	
    	defaultPT.setBreakTimerRunning = true; 
    	defaultPT.setMainTimerRunning = false;
    	
    	/**
         *  Make sure the Break Status is set to Break when BreakTimerRunning = true and MainTimerRunning = false
         */
        assertEquals(Work_Break.Break , defaultPT.getWorkBreakStatus(), 
        "Work_Break status should be Break. Returned: "
        + String.valueOf(defaultPT.getWorkBreakStatus()));
        
        /**
         *  Set Boolean BreakTimerRunning = false and Boolean MainTimerRunning = true
         */
    	
    	defaultPT.setBreakTimerRunning = false; 
    	defaultPT.setMainTimerRunning = true;
    	
    	/**
         *  Make sure the Break Status is set to Break when BreakTimerRunning = false and MainTimerRunning = true
         */
        assertEquals(Work_Break.Work , defaultPT.getWorkBreakStatus(), 
        "Work_Break status should be Work. Returned: "
        + String.valueOf(defaultPT.getWorkBreakStatus()));
        
        /**
         *  Set Boolean paused = true
         */
    	
    	defaultPT.setBreakTimerRunning = null; 
    	defaultPT.setMainTimerRunning = null;
    	
    	defaultPT.setPaused = true;
    	
    	/**
         *  Make sure the Break Status is set to Null when paused = true
         */
        assertEquals(Work_Break.Null , defaultPT.getWorkBreakStatus(), 
        "Work_Break status should be Null. Returned: "
        + String.valueOf(defaultPT.getWorkBreakStatus()));
        
        /**
         *  Set Boolean BreakTimerRunning, MainTimerRunning, and Paused to null
         */
    	
    	defaultPT.setBreakTimerRunning = null; 
    	defaultPT.setMainTimerRunning = null;
    	defaultPT.setPaused = null;
    	
    	/**
         *  Make sure the Break Status is set to Other when BreakTimerRunning, MainTimerRunning and Paused are set to null
         */
        assertEquals(Work_Break.Other , defaultPT.getWorkBreakStatus(), 
        "Work_Break status should be Other. Returned: "
        + String.valueOf(defaultPT.getWorkBreakStatus()));
    	 
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Pomodoro_Timer> Input")
    void testInput() {
    	
    	/**
         *  Set workPeriod = 18 and breakPeriod = 5
         */
    	
    	Settings testSettings = new Settings(999);
    	testSettings.setWorkPeriod(18);
    	testSettings.setBreakPeriod(5);
    	
    	defaultPT.Input(testSettings);
    	
    	/**
         *  Make sure the initialbreak = 5
         */
        assertEquals(5 , defaultPT.getInitialbreak(), 
        "Pomodoro_Timer.initialbreak should be 5. Returned: "
        + Integer.toString(defaultPT.getInitialbreak()));
        
        /**
         *  Make sure the breakmin = 5
         */
        assertEquals(5 , defaultPT.getBreakmin(), 
        "Pomodoro_Timer.breakmin should be 5. Returned: "
        + Integer.toString(defaultPT.getBreakmin()));
        
        /**
         *  Make sure the initialmin = 18
         */
        assertEquals(18 , defaultPT.getInitialMin(), 
        "Pomodoro_Timer.initialmin should be 5. Returned: "
        + Integer.toString(defaultPT.getInitialMin()));
        
        /**
         *  Make sure the min = 18
         */
        assertEquals(18 , defaultPT.getMin(), 
        "Pomodoro_Timer.min should be 5. Returned: "
        + Integer.toString(defaultPT.getMin()));
    	
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    @Test
    @DisplayName("<Pomodoro_Timer> Break Timer")
    void testBreakTimer() {
    /**
     * place holder
     */
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */ 
    
}