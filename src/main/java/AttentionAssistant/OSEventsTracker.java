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
	
	private int osEventsScore;
		
	/**
	 * ProcessHandling default Constructor
	 */
	public OSEventsTracker() {
		this.osEventsScore = 100;
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
			
			int blCount = 0;
			int wlCount = 0;
			//Iterate over Set names until a process name is found on the blacklist
			for(String name : names) {
				blCount += getBlacklistCount(name, blacklist);
				wlCount += getWhitelistCount(name, whitelist);
			}
			System.out.println("Blacklist Count: " + blCount); //For demonstration purposes
			System.out.println("Whitelist Count: " + wlCount); //For demonstration purposes
			
			osEventsScore = calculateOSEventsScore(blCount, wlCount);
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
	 * Compares a process name to a list of blacklisted app names and counts each occurrence
	 * @param processName Process name at current index of the Set
	 * @param blist -> array list of each blacklisted app
	 * @return int
	 */
	public int getBlacklistCount(String processName, ArrayList<String> blist) {
		int count = 0;
		for(String line : blist) {
			if(line.equals(processName)) {
				System.out.println("Blacklist application detected: " + processName); //For demonstration purposes
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Compares a process name to a list of whitelisted app names and counts each occurrence
	 * @param processName Process name at current index of the Set
	 * @param wlist -> array list of each whitelisted app
	 * @return int
	 */
	public int getWhitelistCount(String processName, ArrayList<String> wlist) {
		int count = 0;
		for(String line : wlist) {
			if(line.equals(processName)) {
				System.out.println("Whitelist application detected: " + processName); //For demonstration purposes
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Calculates a weighted average score given the number of blacklisted and whitelisted
	 * applications currently running
	 * @param blCount -> total blacklist app occurances
	 * @param wlCount -> total whitelist app occurances
	 * @return int
	 */
	public int calculateOSEventsScore(int blCount, int wlCount) { //Calculations may need further adjustment
		if(wlCount == 0)
			return 0;
		float weight = 3.50f;
		return (int)(blCount == 0 ? 100 : Math.min(100, (wlCount / (blCount * weight)) * 100));
	}
		
	/**
	 * get osEventsScore
	 * @return int
	 */
	public int getOSEventsScore() {
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
