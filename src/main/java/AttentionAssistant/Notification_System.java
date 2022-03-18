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
	int timeDistracted=0; 
	int timeFocused=0;
	Settings settings;
	private boolean isAudioActive;
	private boolean isAvatarActive;
	private String avatarPath;
	private String audioPath;
	private int avatarSize;
	private boolean avatarAlwaysOn;
	DataBase db;
	 
	public Notification_System(int userID){
		this.timeDistracted = 0;
		this.timeFocused = 0;
		this.settings = new Settings(userID);
		
		this.isAudioActive = true;
		this.isAvatarActive = true;
		this.audioPath = "";
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
		this.audioPath = set.getAudioFilePath();
		this.avatarPath = set.getAvatarFilePath();
		this.avatarSize = set.getAvatarSize();
		this.avatarAlwaysOn = set.getAlwaysOnScreen();
		this.db = database;
	}
	
	
	private void displayNotif(String text, String type,int notifID) {
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
		db.AddNotification(notif,notifID);
		JOptionPane.showMessageDialog(frame, text);
	}
	
	
	public void distracted(int notifID) {
		String text = "User is distracted, guide back to task";
		timeDistracted += 3;
		displayNotif(text, "distracted",notifID);
		
		/* This will say whatever is in String text */
		//AudioHandler tts = new AudioHandler();
		//if(isAudioActive == true)
			//tts.notificationTTS(text);
	}
	
	
	public void selfCare(int notifID) {
		String text = "User needs a break";
		timeFocused += 3;
		displayNotif(text,"selfCare",notifID);
	}
	
	
	public void allGood(int notifID) {
		String text = "User is on task";
		timeFocused += 3;
		displayNotif(text,"encourage",notifID);
		//add words of encouragement?
	}
	
	
	public void dueDateApproaching(Task task,int notifID) {
		String text = "Due date approaching for task: " + task.getTaskName();
		displayNotif(text,"dueDate",notifID);
	}
	
	
	public void taskCompleted(Task task,int notifID) {
		String text = "Yay! You completed "+task.getTaskName()+", great job!";
		displayNotif(text,"complete",notifID);
	}
	
	
	public void breakTime(int notifID) {
		Task task = new Task();
		//get non-observable task
		String text = "It's time to take a break, why not work on "+task.getTaskName()+"?";
		displayNotif(text,"break",notifID);
	}
	
	
	public void workTime(int notifID) {
		Task activeTask = new Task();
		//get active task
		String text = "It's time to get back on task! "+activeTask.getTaskName()+" is your current goal.";
		displayNotif(text,"work",notifID);
	}
}
