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
	private int osEventsScore;
		
	/**
	 * ProcessHandling default Constructor
	 */
	public OSEventsTracker() {
		this.osEventsScore = 100;
		this.names = new HashSet<>();
		this.blacklist = new ArrayList<String>();
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
					
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src/main/resources/OSBlacklist.txt"));
			String line = reader.readLine();
			System.out.print("\nBlacklisted applications: "); //For demonstration purposes
			while(line != null) {
				String lcLine = line.toLowerCase();
				System.out.println(lcLine); //For demonstration purposes
				blacklist.add(lcLine);
				line = reader.readLine();
			}
			//Iterate over Set names until a process name is found on the blacklist
			for(String name : names) {
				osEventsScore = compareCurrentProcesses(name, blacklist);
				//Stops traversing the Set names once a match is found (User is determined off task)
				if(osEventsScore == 0) {
					System.out.println("OS events score: " + osEventsScore); //For demonstration purposes
					break;
				}
			}
			//For demonstration purposes
			if(osEventsScore == 100) {
				System.out.println("app detected: none");
				System.out.println("OS events score: " + osEventsScore);
			}
			reader.close();
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
	 * Compares a process name to a list of blacklisted names read in from a file
	 * @param processName Process name at current index of the Set
	 * @param blacklist array list of the blacklist file
	 * @return int osEventsScore
	 */
	public int compareCurrentProcesses(String processName, ArrayList<String> blacklist) {
		int score = 100;
		for(String line : blacklist) {
			//Stops traversing the blacklist once a match is found (User is determined off task)
			if(line.equals(processName)) {
				System.out.println("app detected: " + processName); //For demonstration purposes
				score = 0;
				break;
			}
		}
		return score;
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
