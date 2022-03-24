package AttentionAssistant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.WindowConstants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_Progress_Report_TaskGraph{
	/**
	 * Objects used in test
	 */

	Task nonDefaultTask;
	Observer nonDefaultObserver;
	User_Account nonDefaultUser;
	DataBase db = new DataBase();
	
	@BeforeEach
	void setup() {
	/**
	 * Set up for nonDefault Task	
	 */
	int testTaskID = 5;
	String testDescription = "Test description for Test_Task_Graph";
	boolean testObservable = true;
	TaskStatus testStatus = TaskStatus.OPEN;
	String testName = "Test_Task_Graph";
	Date testDateTask = new Date(1220227200L * 1000);
	boolean testPriority = true;
	nonDefaultTask = new Task(testTaskID, testDescription, testObservable, testStatus, testName, testDateTask, testPriority);
	
	/**
	 * Set up for nonDefault Observer
	 */
	int testObserver_ID= 999;
	int testObserverScore= 100;
	int testThreshold= 100;
	int testEyeScore=50;
	Date testDT_Gathered= new Date(1220227200L * 1000);

	nonDefaultObserver= new Observer(testObserver_ID, testObserverScore, testThreshold, testDT_Gathered, testEyeScore);

	
	DataBase db = new DataBase();
	db.DatabaseSetUp();
	db.DeleteTask(5);

	db.AddTask(nonDefaultTask, 1);
	
	Observer testObserver= new Observer(nonDefaultObserver);
	
	testObserver.setObserverScore(75);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220227200L * 1000));
	db.AddObserver(testObserver, 5);

	testObserver.setObserverScore(70);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220227500L * 1000));
	db.AddObserver(testObserver, 5);
	
	testObserver.setObserverScore(65);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220227800L * 1000));
	db.AddObserver(testObserver, 5);
	
	testObserver.setObserverScore(55);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220228100L * 1000));
	db.AddObserver(testObserver, 5);
	
	testObserver.setObserverScore(40);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220228400L * 1000));
	db.AddObserver(testObserver, 5);
	
	testObserver.setObserverScore(45);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220228700L * 1000));
	db.AddObserver(testObserver, 5);
	
	testObserver.setObserverScore(55);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220229000L * 1000));
	db.AddObserver(testObserver, 5);
	
	testObserver.setObserverScore(60);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220229300L * 1000));
	db.AddObserver(testObserver, 5);

	testObserver.setObserverScore(75);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220229600L * 1000));
	db.AddObserver(testObserver, 5);

	testObserver.setObserverScore(85);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220229900L * 1000));
	db.AddObserver(testObserver, 5);

	testObserver.setObserverScore(65);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220230200L * 1000));
	db.AddObserver(testObserver, 5);

	testObserver.setObserverScore(55);
	testObserver.setThreshold(50);
	testObserver.setDTGathered(new Date(1220230500L * 1000));
	db.AddObserver(testObserver, 5);
	}
	
	@Test
    @DisplayName("<Test_Progress_Report_TaskGraph> Display_Task_Graph")
    void Display_Task_Graph() {
		db.DatabaseSetUp();
		Progress_Report_TaskGraph example = new Progress_Report_TaskGraph("Observer Score during " + nonDefaultTask.getTaskName() + " Graph");
		example.Make_TaskGraph(db, nonDefaultTask);
		example.setAlwaysOnTop(true);  
		example.pack();
		example.setSize(600, 400);  
		example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
		example.setVisible(true);
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(20L, TimeUnit.SECONDS);
		while (System.nanoTime()< endTime)
			{
			
			}
		db.DeleteTask(5);
    }

	
}