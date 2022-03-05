package AttentionAssistant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Tracks the currently running OS processes to determine if the user is using programs that are distracting 
 * @author ehols001
 */

public class OSEventsTracker {
	
	//Set used to store process names without duplicates
	Set<String> names;
	//ArrayList used to store each line from the blacklist text file
	ArrayList<String> blacklist;
	//ArrayList used to store each line from the whitelist text file
	ArrayList<String> whitelist;
	
	private int blProcessCount;
	private int wlProcessCount;
	private int osEventsScore;
		
	/**
	 * ProcessHandling default Constructor
	 */
	public OSEventsTracker() {
		this.blProcessCount = 0;
		this.wlProcessCount = 0;
		this.osEventsScore = 50;
		this.names = new HashSet<>();
		this.blacklist = new ArrayList<String>();
		this.whitelist = new ArrayList<String>();
	}
		
	/**
	 * Collects and stores all currently running processes into a Set
	 * with no duplicates
	 */
	public void startTracking() {
		//Retrieving all currently running processes
		Stream<ProcessHandle> processes = ProcessHandle.allProcesses();
					
		//For each of the processes, add only the process name to the Set
		processes.forEach(process -> names.add(processDetails(text(process.info().command()))));
					
		BufferedReader bl_reader, wl_reader;
		try {
			
			//Storing each line from the blacklist into an array list
			bl_reader = new BufferedReader(new FileReader("src/main/resources/OSBlacklist.txt"));
			String bline = bl_reader.readLine();
			System.out.println("\n~ OS_EVENT_TRACKING - START ~"); //For demonstration purposes
			System.out.println("Blacklisted applications: "); //For demonstration purposes
			while(bline != null) {
				String lcLine = bline.toLowerCase();
				System.out.println(lcLine); //For demonstration purposes
				blacklist.add(lcLine);
				bline = bl_reader.readLine();
			}

			//Storing each line from the whitelist into an array list
			wl_reader = new BufferedReader(new FileReader("src/main/resources/OSWhitelist.txt"));
			String wline = wl_reader.readLine();
			System.out.println("Whitelisted applications: "); //For demonstration purposes
			while(wline != null) {
				String lcLine = wline.toLowerCase();
				System.out.println(lcLine); //For demonstration purposes
				whitelist.add(lcLine);
				wline = wl_reader.readLine();
			}
			
			//Iterate over Set names until a process name is found on the blacklist
			for(String name : names) {
				compareCurrentProcesses(name);
			}
			System.out.println("Blacklist Count: " + blProcessCount); //For demonstration purposes
			System.out.println("Whitelist Count: " + wlProcessCount); //For demonstration purposes
			
			osEventsScore += calculateOSEventsScore();
			System.out.println("OS events score: " + osEventsScore); //For demonstration purposes
			System.out.println("~ OS_EVENT_TRACKING - FINISH ~"); //For demonstration purposes
			bl_reader.close();
			wl_reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Trims and normalizes the file path for each process down to be only the application name 
	 * @param process Process at current index of Set
	 * @return String application name
	 */
	public String processDetails(String process) {
		String trimmedName = "";
		String appName = "";
		appName = process.substring(process.lastIndexOf("\\") + 1).toLowerCase();
		if(appName.contains(".")) {
			trimmedName = appName.substring(0, appName.lastIndexOf('.'));
			return trimmedName;
		}
		else
			return appName;
	}
		
	/**
	 * Compares a process name to a list of whitelisted and blacklisted app names and counts each occurance
	 * @param processName Process name at current index of the Set
	 */
	public void compareCurrentProcesses(String processName) {
		for(String line : blacklist) {
			if(line.equals(processName)) {
				System.out.println("Blacklist application detected: " + processName); //For demonstration purposes
				blProcessCount++;
			}
		}
		for(String line : whitelist) {
			if(line.equals(processName)) {
				System.out.println("Whitelist application detected: " + processName); //For demonstration purposes
				wlProcessCount++;
			}
		}
	}
	
	/**
	 * Calculates a weighted average score given the number of blacklisted and whitelisted
	 * applications currently running
	 * @return int total -> weighted average
	 */
	public int calculateOSEventsScore() { //Calculations may need further adjustment
		int total = 0;
		if(wlProcessCount >= 1 && blProcessCount == 0)
			total = 50;
		else if(wlProcessCount == 0 && blProcessCount >= 1)
			total = -50;
		else {
			double temp = (((0.3 * wlProcessCount) - (0.7 * blProcessCount)) / (blProcessCount + wlProcessCount)) * 100;
			total = (int)temp;
		}
		return total;
	}
		
	/**
	 * get osEventsScore
	 * @return int
	 */
	public int getOSEventsScore() {
		if(osEventsScore > 100) {osEventsScore = 100;}
		else if(osEventsScore < 0) {osEventsScore = 0;}
		return this.osEventsScore;
	}
		
	/**
	 * set osEventsScore
	 * @param int
	 */
	public void setOSEventsScore(int score) {
		this.osEventsScore = score;
	}
		
	 /**
	 * Converts ProcessHandle info to Strings
	 * @param ProcessHandle info
	 * @return String 
	 */
	private static String text(Optional<?> optional) {
		return optional.map(Object::toString).orElse("-");
	}		
}
