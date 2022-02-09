package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
/**
 * Test File for the database functions.
 * @author jmitchel2
 */
@TestMethodOrder(OrderAnnotation.class)
public class Test_DataBase {
	/**
	 * Objects used in test
	 */

	Task nonDefaultTask;
	Happy_Thought_Button nonDefaultHTB;
	Observer nonDefaultObserver;
	Settings nonDefaultSettings;
	DataBase db = new DataBase();
	
	@BeforeEach
	void setup() {
	/**
	 * Set up for nonDefault Task	
	 */
	int testTaskID = 999;
	String testDescription = "This is a test description";
	boolean testObservable = true;
	TaskStatus testStatus = TaskStatus.OPEN;
	String testName = "This is a test Name";
	Date testDateTask = new Date(1220227200L * 1000);
	boolean testPriority = true;
	nonDefaultTask = new Task(testTaskID, testDescription, testObservable, testStatus, testName, testDateTask, testPriority);
	
	/**
	 * Set up for nonDefault HTB
	 */
	int testHTBID = 999;
	String testMediaIDTag = "This is a test Media ID Tag";
	boolean testHTBFlagged = true;
	Date testDateHTB= new Date(1220227200L * 1000);

	nonDefaultHTB = new Happy_Thought_Button(testHTBID, testMediaIDTag, testHTBFlagged, testDateHTB);

	/**
	 * Set up for nonDefault Observer
	 */
	int testObserver_ID= 999;
	int testObserverScore= 100;
	int testThreshold= 100;
	Date testDT_Gathered= new Date(1220227200L * 1000);

	nonDefaultObserver= new Observer(testObserver_ID, testObserverScore, testThreshold, testDT_Gathered);
	
	/**
	 * Set up for nonDefault Settings 
	 */
	int testSettingsID = 999;
	Color testIconCircles = Color.RED; 
	Color testIcons = Color.YELLOW;
	int testOpacityCircles = 75; 
	int testOpacityIcons = 75; 
	boolean testIsCollapsed = true; 
	int testXCoord = 15;
	int testYCoord = 15; 
	boolean testIsVertical = false; 
	int testIconSize = 30; 
	boolean testTimerIsVisible = false; 
	boolean testPmIsVisible = false; 
	boolean testFtsIsVisible = false; 
	boolean testHtbIsVisible = false; 
	boolean testNtbIsVisible = false; 
	boolean testProgReportIsVisible = false; 
	boolean testAvatarIsActive = true; 
	boolean testTextIsActive = false; 
	boolean testAudioIsActive = true;
	String testAvatarFilePath = "images/avatar_cat1.png"; 
	String testAudioFilePath = "test";
	boolean testAlwaysOnScreen = true; 
	int testAvatarSize = 75; 
	boolean testPomodoroIsActive = false; 
	int testWorkPeriod = 33; 
	int testBreakPeriod = 23; 
	boolean testTimeShowing = false; 
	boolean testFtsIsActive = false; 
	boolean testNtbIsActive = false; 
	boolean testIsAutoLinked = false; 
	boolean testHtbIsActive = false; 

	nonDefaultSettings = new Settings(testSettingsID, testIconCircles, testIcons, testOpacityCircles, testOpacityIcons, testIsCollapsed, testXCoord, 
			  testYCoord, testIsVertical, testIconSize, testTimerIsVisible, testPmIsVisible, testFtsIsVisible, 
			  testHtbIsVisible, testNtbIsVisible, testProgReportIsVisible, testAvatarIsActive, testTextIsActive, 
			  testAudioIsActive, testAvatarFilePath, testAudioFilePath, testAlwaysOnScreen, testAvatarSize, 
			  testPomodoroIsActive, testWorkPeriod, testBreakPeriod, testTimeShowing, testFtsIsActive, 
			  testNtbIsActive, testIsAutoLinked, testHtbIsActive);
	
	db.DatabaseSetUp();	
	}

    @Test
    @Order(1)
    @DisplayName("<DataBase> DatabaseDeleteAllTables")
    void DatabaseDeleteAllTables() {
    db.DeleteAllTasks();
    db.DeleteAllHTBs();
    db.DeleteAllObservers();
    }

	
	/**
     ******* START OF TEST TASK CRUD *******
	 */
	
    @Test
    @Order(2)
    @DisplayName("<DataBase> DatabaseAddNewTask")
    void DatabaseAddNewTask() {
    	db.AddTask(nonDefaultTask);
    	db.AddTask(nonDefaultTask);
    	db.AddTask(nonDefaultTask);
    	db.AddTask(nonDefaultTask);
    }

    @Test
    @Order(3)
    @DisplayName("<DataBase> DatabaseUpdateTask")
    void DatabaseUpdateTask() {
    	Task UpdatedTask= new Task(nonDefaultTask);
    	UpdatedTask.setTaskID(1);
    	UpdatedTask.setDescription("I am a updated description1");
    	UpdatedTask.setObservable(false);
    	UpdatedTask.setTaskName("I am an updated name1");
    	db.UpdateTask(UpdatedTask);
    
    }
    
    @Test
    @Order(5)
    @DisplayName("<DataBase> DatabaseDeleteTask")
    void DatabaseDeleteTask() {
    	Task DeletedTask= new Task(nonDefaultTask);
    	DeletedTask.setTaskID(3);
    	DeletedTask.setDescription("I am supposed to be deleted");
    	DeletedTask.setObservable(false);
    	DeletedTask.setTaskName("I am supposed to be deleted");
    	db.UpdateTask(DeletedTask);
    	
    	db.DeleteTask(3);    
    }
    
    @Test
    @Order(4)
    @DisplayName("<DataBase> DatabaseSelectTask")
    void DatabaseSelectTask() {
    	Task SelectedTask1= new Task(nonDefaultTask);
    	SelectedTask1.setTaskID(2);
    	SelectedTask1.setDescription("I am a Selected description1");
    	SelectedTask1.setTaskName("I am an Selected name1");
       	SelectedTask1.setObservable(true);
       	SelectedTask1.setPriority(true);
    	db.UpdateTask(SelectedTask1);
    	
    	Task selectedTask2 = new Task();
    	selectedTask2 = db.SelectTask(2);
        
    	String String1 = "Task ID= 2 Priority= true Name= I am an Selected name1 Description= I am a Selected description1 Due Date= Sun Aug 31 20:00:00 EDT 2008 Observable= true Status= OPEN";
        assertEquals(String1, selectedTask2.toString(), "selectedTask2 should be set to Task ID= 1 Priority= false Name= This is an Selected name1 Description= I am a Selected description1 Due Date= Sun Aug 31 20:00:00 EDT 2008 Observable= false Status= OPEN but instead returned: " + selectedTask2.toString());
        assertEquals(true, selectedTask2.getPriority(), "selectedTask2 priority should be set to true but instead returned: " + selectedTask2.getPriority());
        assertEquals(2, selectedTask2.getTaskID(), "selectedTask2 taskid should be set to 1 but instead returned: " + selectedTask2.getTaskID());
        assertEquals("I am a Selected description1", selectedTask2.getDescription(), "selectedTask2 description should be set to I am a Selected description1 but instead returned: " + selectedTask2.getDescription());
        assertEquals("I am an Selected name1", selectedTask2.getTaskName(), "selectedTask2 name should be set to I am an Selected name1 but instead returned: " + selectedTask2.getTaskName());
        assertEquals(new Date(1220227200L * 1000), selectedTask2.getDueDate(), "selectedTask2 duedate should be set to Sun Aug 31 20:00:00 EDT 2008 but instead returned: " + selectedTask2.getDueDate());
        assertEquals(true, selectedTask2.getObservable(), "selectedTask2 observable should be set to false but instead returned: " + selectedTask2.getObservable());
        assertEquals(TaskStatus.OPEN , selectedTask2.getStatus(), "selectedTask2 status should be set to OPEN but instead returned: " + selectedTask2.getStatus().toString());
        
    }

 
    @Test
    @Order(6)
    @DisplayName("<DataBase> DatabaseSelectAllTasks")
    void DatabaseSelectAllTasks() {
    	ArrayList<Task> test_task_List = new ArrayList<Task>();
    	ArrayList<Task> test_database_task_List = new ArrayList<Task>();
    	
    	Task SelectedTask1= new Task(nonDefaultTask);
    	SelectedTask1.setTaskID(2);
    	SelectedTask1.setDescription("I am a Selected description1");
    	SelectedTask1.setTaskName("I am an Selected name1");
       	SelectedTask1.setObservable(true);
       	SelectedTask1.setPriority(true);
       	
    	Task UpdatedTask= new Task(nonDefaultTask);
    	UpdatedTask.setTaskID(1);
    	UpdatedTask.setDescription("I am a updated description1");
    	UpdatedTask.setObservable(false);
    	UpdatedTask.setTaskName("I am an updated name1");
    	
    	Task nonDefaultAddedTask = new Task(nonDefaultTask); 
    	nonDefaultAddedTask.setTaskID(4);
    	
    	test_task_List.add(SelectedTask1);
    	test_task_List.add(nonDefaultAddedTask);
    	test_task_List.add(UpdatedTask);
    	
    	test_database_task_List= db.SelectAllTasks();
    	
    	for (int i =0; i< test_database_task_List.size(); i++) {        
    		assertEquals(test_task_List.get(i).toString(), test_database_task_List.get(i).toString(), "test_database_task_List " + i + " should be set to " + test_task_List.get(i).toString() + " but instead returned: " + test_database_task_List.get(i).toString());
        }
    }
    /**
    ******* END OF TEST TASK CRUD *******
    */

    /**
    ******* START OF TEST HTB CRUD *******
    * @author jmitchel2
    */
    
    @Test
    @Order(7)
    @DisplayName("<DataBase> DatabaseAddNewHTB")
    void DatabaseAddHTB() {
    db.AddHTB(nonDefaultHTB);
    db.AddHTB(nonDefaultHTB);
    db.AddHTB(nonDefaultHTB);
    db.AddHTB(nonDefaultHTB);
    }
    
    @Test
    @Order(8)
    @DisplayName("<DataBase> DatabaseUpdateHTB")
    void DatabaseUpdateHTB() {
    	Happy_Thought_Button UpdatedHTB= new Happy_Thought_Button(nonDefaultHTB);
    	UpdatedHTB.setHTBID(1);
    	UpdatedHTB.setMedia_ID_Tag("I am a updated Media_ID_Tag");
    	UpdatedHTB.setFlagged(false);
    	db.UpdateHTB(UpdatedHTB);
    
    }
    
    @Test
    @Order(9)
    @DisplayName("<DataBase> DatabaseDeleteHTB")
    void DatabaseDeleteHTB() {
    	Happy_Thought_Button DeletedHTB= new Happy_Thought_Button(nonDefaultHTB);
    	DeletedHTB.setHTBID(3);
    	DeletedHTB.setMedia_ID_Tag("I am supposed to be deleted");
    	DeletedHTB.setFlagged(false);
    	db.UpdateHTB(DeletedHTB);
    	
    	db.DeleteHTB(3);
    }

    @Test
    @Order(10)
    @DisplayName("<DataBase> DatabaseSelectHTB")
    void DatabaseSelectHTB() {
    	Happy_Thought_Button SelectedHTB1= new Happy_Thought_Button(nonDefaultHTB);
    	SelectedHTB1.setHTBID(2);
    	SelectedHTB1.setMedia_ID_Tag("I am a Selected Media ID Tag");
    	SelectedHTB1.setFlagged(true);
    	db.UpdateHTB(SelectedHTB1);
    	
    	Happy_Thought_Button selectedHTB2 = new Happy_Thought_Button();
    	selectedHTB2 = db.SelectHTB(2);
        
    	String String1 = "Happy_Thought_Button ID= 2 Media_ID_Tag= I am a Selected Media ID Tag Flagged= true Date Time Executed= Sun Aug 31 20:00:00 EDT 2008";
        assertEquals(String1, selectedHTB2.toString(), "selectedHTB2 should be set to Happy_Thought_Button ID= 2 Media_ID_Tag= This is a test Media ID Tag Flagged= true Date Time Executed= Sun Aug 31 20:00:00 EDT 2008 but instead returned: " + selectedHTB2.toString());
        
    }

    
    /**
    ******* END OF TEST HTB CRUD *******
    */

    /**
    ******* START OF TEST OBSERVER CRUD *******
     */
    @Test
    @Order(11)
    @DisplayName("<DataBase> DatabaseAddNewObserver")
    void DatabaseAddNewObserver() {
    	Task ForObserver= new Task(nonDefaultTask);
    	ForObserver.setTaskID(4);
    	db.AddObserver(nonDefaultObserver, ForObserver);
    	db.AddObserver(nonDefaultObserver, ForObserver);
    	db.AddObserver(nonDefaultObserver, ForObserver);
    	db.AddObserver(nonDefaultObserver, ForObserver);
    }

    @Test
    @Order(12)
    @DisplayName("<DataBase> DatabaseUpdateObserver")
    void DatabaseUpdateObserver() {
    	Observer UpdatedObserver= new Observer(nonDefaultObserver);
    	UpdatedObserver.setObserverID(1);
    	UpdatedObserver.setObserverScore(98);
    	UpdatedObserver.setThreshold(98);
    	db.UpdateObserver(UpdatedObserver);
    
    }
    
    @Test
    @Order(13)
    @DisplayName("<DataBase> DatabaseDeleteObserver")
    void DatabaseDeleteObserver() {
    	Observer DeletedObserver = new Observer(nonDefaultObserver);
    	DeletedObserver.setObserverID(3);
    	DeletedObserver.setObserverScore(0);
    	DeletedObserver.setThreshold(0);
    	db.UpdateObserver(DeletedObserver);
    	
    	db.DeleteObserver(3);
    }
    
    @Test
    @Order(14)
    @DisplayName("<DataBase> DatabaseSelectObserver")
    void DatabaseSelectObserver() {
    	Observer selectedObserver = new Observer(nonDefaultObserver);
    	selectedObserver.setObserverID(2);
    	selectedObserver.setObserverScore(0);
    	selectedObserver.setThreshold(0);
    	db.UpdateObserver(selectedObserver);
    	
    	Observer selectedObserver2 = new Observer();
    	selectedObserver2 = db.SelectObserver(2);
        
    	String String1 = "Observer ID= 2 Observer Score= 0 Threshold= 0 Date Time Gathered= Sun Aug 31 20:00:00 EDT 2008";
        assertEquals(String1, selectedObserver2.toString(), "selectedObserver2 should be set to Observer ID= 2 Observer Score= 0 Threshold= 0 Date Time Gathered= Sun Aug 31 20:00:00 EDT 2008" 
        			+ selectedObserver2.toString());
        
    }

    @Test
    @Order(15)
    @DisplayName("<DataBase> DatabaseSelectAllObservers")
    void DatabaseSelectAllObservers() {
    	ArrayList<Observer> test_Observer_List = new ArrayList<Observer>();
    	ArrayList<Observer> test_database_Observer_List = new ArrayList<Observer>();
    	
    	Observer UpdatedObserver= new Observer(nonDefaultObserver);
    	UpdatedObserver.setObserverID(1);
    	UpdatedObserver.setObserverScore(98);
    	UpdatedObserver.setThreshold(98);
       	
    	Observer selectedObserver = new Observer(nonDefaultObserver);
    	selectedObserver.setObserverID(2);
    	selectedObserver.setObserverScore(0);
    	selectedObserver.setThreshold(0);

    	
    	Observer nonDefaultAddedObserver = new Observer(nonDefaultObserver); 
    	nonDefaultAddedObserver.setObserverID(4);

    	test_Observer_List.add(UpdatedObserver);
    	test_Observer_List.add(selectedObserver);
    	test_Observer_List.add(nonDefaultAddedObserver);

    	test_database_Observer_List= db.SelectAllObservers(4);
    	
    	for (int i =0; i< test_database_Observer_List.size(); i++) {        
    		assertEquals(test_Observer_List.get(i).toString(), test_database_Observer_List.get(i).toString(), "test_database_task_List " + i + " should be set to " + test_Observer_List.get(i).toString() + " but instead returned: " + test_database_Observer_List.get(i).toString());
        }

    	
    }

    /**
    ******* END OF TEST OBSERVER CRUD *******
    */

    /**
    ******* START OF TEST SETTINGS CRUD *******
     */

    
    @Test
    @Order(16)
    @DisplayName("<DataBase> DatabaseAddNewSettings")
    void DatabaseAddNewSettings() {
        db.AddSettings(nonDefaultSettings);
        db.AddSettings(nonDefaultSettings);
        db.AddSettings(nonDefaultSettings);
        db.AddSettings(nonDefaultSettings);
    }
}