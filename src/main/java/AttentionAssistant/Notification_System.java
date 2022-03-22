/**
 * Class that contains information on Notification System created by 
 * events throughout the Attention Assistant
 */
package AttentionAssistant;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
public class Notification_System {
	//observe runs monitoring every 3 minutes, allows for time tracking 
	private int userID;
	
	int timeDistracted=0; 
	int timeFocused=0;
	Settings settings;
	private boolean isAudioActive;
	private boolean isAvatarActive;
	private String avatarPath;
	private int avatarSize;
	private boolean avatarAlwaysOn;
	DataBase db;
	 
	public Notification_System(int userID){
		this.timeDistracted = 0;
		this.timeFocused = 0;
		this.settings = new Settings(userID);
		this.userID = userID;
		this.isAudioActive = true;
		this.isAvatarActive = true;
		this.avatarPath = "avatarSelection/avatar_dino.png";
		this.avatarSize = 100;
		this.avatarAlwaysOn = false;
	}
	
	public Notification_System(Settings set,DataBase database){
		this.timeDistracted = 0;
		this.timeFocused = 0;
		this.settings = set;
		this.isAudioActive = set.getAudioIsActive();
		this.isAvatarActive = set.getAvatarIsActive();
		this.avatarPath = set.getAvatarFilePath();
		this.avatarSize = set.getAvatarSize();
		this.avatarAlwaysOn = set.getAlwaysOnScreen();
		this.db = database;
	}
	
	
	private void displayNotif(String text, String type) {
		JFrame frame = new JFrame();
		boolean isIgnored=false;
		Date date = new Date();
		if(isAudioActive == true) {
			//audio for notification (based on type of notif?)
			text = "DING! "+text;
		}
		if(isAvatarActive == true) {
			//display text in speech bubble
			String avatar = avatarPath;
			//Display avatar
			//string to place in text bubble
			text = text+" (speech bubble)";
		}else {
			//display text in text bubble
			text = text+" (text bubble)";
		}
		//display notification, return isIgnored
		Notification notif = new Notification(1,type,isIgnored,date);
		db.AddNotification(notif,userID);
		JOptionPane.showMessageDialog(frame, text);
	}
	
	
	public void resumePomo() {
		//on-task & null
		String text = "You're working but your timer is paused!";
		timeFocused += 3;
		displayNotif(text,"resume");
	}
	
	
	public void isNull() {
		//if off-task & null OR off-task & in break period
		String text = "we are paused...";
		displayNotif(text,"paused");
	}
	
	
	public void distracted() {
		//in work period & off-task
		
		String text = "User is distracted, guide back to task";
		timeDistracted += 3;
		displayNotif(text, "distracted");
		
		/* This will say whatever is in String text */
		//AudioHandler tts = new AudioHandler();
		//if(isAudioActive == true)
			//tts.notificationTTS(text);
	}
	
	
	public void selfCare() {
		//on-task for too long with no break
		
		String text = "User needs a break";
		timeFocused += 3;
		displayNotif(text,"selfCare");
	}
	
	
	public void allGood() {
		//in work period & on-task
		
		String text = "User is on task";
		timeFocused += 3;
		displayNotif(text,"encourage");
		//add words of encouragement?
	}
	
	
	public void dueDateApproaching(Task task) {
		//due date happening soon
		
		String text = "Due date approaching for task: " + task.getTaskName();
		displayNotif(text,"dueDate");
	}
	
	
	public void taskCompleted(Task task) {
		//task is completed
		
		String text = "Yay! You completed "+task.getTaskName()+", great job!";
		displayNotif(text,"complete");
	}
	
	
	public void breakTime() {
		//break period start
		
		Task task = new Task();
		//get non-observable task
		String text = "It's time to take a break, why not work on "+task.getTaskName()+"?";
		displayNotif(text,"break");
	}
	
	
	public void workTime() {
		//work period start
		
		Task activeTask = new Task();
		//get active task
		String text = "It's time to get back on task! "+activeTask.getTaskName()+" is your current goal.";
		displayNotif(text,"work");
	}
}
