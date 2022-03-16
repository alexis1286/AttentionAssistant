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
	User_Account nonDefaultUser;
	Parent_Account nonDefaultParent;
	Notification nonDefaultNotify;
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
	
	/**
	 * Set up for nonDefault User_Account 
	 */
	int testUserID= 999;
	String testUserUsername= "TestUser123";
	String testUserPassword= "TestPass123";

	nonDefaultUser= new User_Account(testUserID, testUserUsername, testUserPassword);
	
	/**
	 * Set up for nonDefault Parent_Account 
	 */
	int testParentID= 999;
	String testParentUsername= "TestParentUser123";
	String testParentPassword= "TestParentPass123";

	nonDefaultParent= new Parent_Account(testParentID, testParentUsername, testParentPassword);
	
	/**
	 * Set up for nonDefault Notification_System
	 */
	int testNotificationID= 999;
	String testType = "This is a test type";
	boolean testIgnored= true;
	Date testDT_Notification = new Date(1220227200L * 1000);

	nonDefaultNotify= new Notification(testNotificationID, testType, testIgnored, testDT_Notification);

	db.DatabaseSetUp();	
	}

	/**
	 * Delete All Tables within the Database
	 */
    @Test
    @Order(1)
    @DisplayName("<DataBase> DatabaseDeleteAllTables")
    void DatabaseDeleteAllTables() {
    db.DeleteAllUser_Accounts();
    db.DeleteAllParent_Accounts();
    db.DeleteAllTasks();
    db.DeleteAllHTBs();
    db.DeleteAllObservers();
    db.DeleteAllSettings();
    db.DeleteAllLinkedAccounts();
    db.DeleteAllNotifications();
    db.DeleteAllEventTypes();
    db.DeleteAllEvents();
    db.DeleteAllFTS_Colors();
    }

    /**
     ***************** START OF TEST USER_ACCOUNT CRUD *****************
	 */
	
    @Test
    @Order(2)
    @DisplayName("<DataBase> DatabaseAddNewUserAccount")
    void DatabaseAddNewUserAccount() {
    	db.AddUser_Account(nonDefaultUser);
    	db.AddUser_Account(nonDefaultUser);
    	db.AddUser_Account(nonDefaultUser);
    	db.AddUser_Account(nonDefaultUser);
    }

    @Test
    @Order(3)
    @DisplayName("<DataBase> DatabaseUpdateUserAccount")
    void DatabaseUpdateUserAccount() {
    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
    	UpdatedUser.setUserID(1);
    	UpdatedUser.setUsername("UpdatedUsername123");
    	UpdatedUser.setPassword("UpdatedPassword123");
    	db.UpdateUser_Account(UpdatedUser);
    }
    
    @Test
    @Order(5)
    @DisplayName("<DataBase> DatabaseDeleteUserAccount")
    void DatabaseDeleteUserAccount() {
    	User_Account DeletedUser= new User_Account(nonDefaultUser);
    	DeletedUser.setUserID(3);
    	DeletedUser.setUsername("DeletedUsername123");
    	DeletedUser.setPassword("DeletedPassword123");
    	db.UpdateUser_Account(DeletedUser);
    	
    	db.DeleteUser_Account(3);    
    }
    
    @Test
    @Order(4)
    @DisplayName("<DataBase> DatabaseSelectUserAccount")
    void DatabaseSelectUserAccount() {
    	User_Account SelectedUser = new User_Account(nonDefaultUser);
    	SelectedUser.setUserID(2);
    	SelectedUser.setUsername("SelectedUsername123");
    	SelectedUser.setPassword("SelectedPassword123");
    	db.UpdateUser_Account(SelectedUser);
    	
    	User_Account SelectedUser2 = new User_Account();
    	SelectedUser2 = db.SelectUser_Account(2);
        
        String String1 = "User ID= 2 Username= SelectedUsername123 Password= SelectedPassword123";
        assertEquals(String1, SelectedUser2.toString(), "SelectedUser2 should be set to \"User ID= 2 Username= SelectedUsername123 Password= SelectedPassword123\" but instead returned: " + SelectedUser2.toString());
    }
	
    @Test
    @Order(6)
    @DisplayName("<DataBase> DatabaseSelectUserAccount")
    void DatabaseSearchUserAccount() {
    	User_Account SelectedUser = new User_Account(nonDefaultUser);
    	SelectedUser.setUserID(2);
    	SelectedUser.setUsername("SelectedUsername123");
    	SelectedUser.setPassword("SelectedPassword123");
    	
    	User_Account SelectedUser2 = new User_Account();
    	SelectedUser2 = db.SearchUser_Account(SelectedUser.getUsername(), SelectedUser.getPassword());
        
        String String1 = "User ID= 2 Username= SelectedUsername123 Password= SelectedPassword123";
        assertEquals(String1, SelectedUser2.toString(), "SelectedUser2 should be set to \"User ID= 2 Username= SelectedUsername123 Password= SelectedPassword123\" but instead returned: " + SelectedUser2.toString());
    }
    
    /**
     ***************** END OF TEST USER_ACCOUNT CRUD *****************
	 */
	
    /**
     ***************** START OF TEST USER_ACCOUNT CRUD *****************
	 */
	
    @Test
    @Order(7)
    @DisplayName("<DataBase> DatabaseAddNewParentAccount")
    void DatabaseAddNewParentAccount() {
    	db.AddParent_Account(nonDefaultParent);
    	db.AddParent_Account(nonDefaultParent);
    	db.AddParent_Account(nonDefaultParent);
    	db.AddParent_Account(nonDefaultParent);
    }

    @Test
    @Order(8)
    @DisplayName("<DataBase> DatabaseUpdateParentAccount")
    void DatabaseUpdateParentAccount() {
    	Parent_Account UpdatedParent= new Parent_Account(nonDefaultParent);
    	UpdatedParent.setParentID(1);
    	UpdatedParent.setUsername("UpdatedParentUser123");
    	UpdatedParent.setPassword("UpdatedParentPass123");
    	db.UpdateParent_Account(UpdatedParent);
    }
    
    @Test
    @Order(10)
    @DisplayName("<DataBase> DatabaseDeleteParentAccount")
    void DatabaseDeleteParentAccount() {
    	Parent_Account DeletedParent= new Parent_Account(nonDefaultParent);
    	DeletedParent.setParentID(3);
    	DeletedParent.setUsername("DeletedParentUser123");
    	DeletedParent.setPassword("DeletedParentPass123");
    	db.UpdateParent_Account(DeletedParent);
    	
    	db.DeleteParent_Account(3);    
    }
    
    @Test
    @Order(9)
    @DisplayName("<DataBase> DatabaseSelectParentAccount")
    void DatabaseSelectParentAccount() {
    	Parent_Account SelectedParent = new Parent_Account(nonDefaultParent);
    	SelectedParent.setParentID(2);
    	SelectedParent.setUsername("SelectedParentUser123");
    	SelectedParent.setPassword("SelectedParentPass123");
    	db.UpdateParent_Account(SelectedParent);
    	
    	Parent_Account SelectedParent2 = new Parent_Account();
    	SelectedParent2 = db.SelectParent_Account(2);
        
        String String1 = "Parent ID= 2 Username= SelectedParentUser123 Password= SelectedParentPass123";
        assertEquals(String1, SelectedParent2.toString(), "SelectedUser2 should be set to \"Parent ID= 2 Username= SelectedParentUser123 Password= SelectedParentPass123\" but instead returned: " + SelectedParent2.toString());
    }
	
    @Test
    @Order(11)
    @DisplayName("<DataBase> DatabaseSelectParentAccount")
    void DatabaseSearchParentAccount() {
    	Parent_Account SelectedParent = new Parent_Account(nonDefaultParent);
    	SelectedParent.setParentID(2);
    	SelectedParent.setUsername("SelectedParentUser123");
    	SelectedParent.setPassword("SelectedParentPass123");
    	
    	Parent_Account SelectedParent2 = new Parent_Account();
    	SelectedParent2 = db.SearchParent_Account(SelectedParent.getUsername(), SelectedParent.getPassword());
        
        String String1 = "Parent ID= 2 Username= SelectedParentUser123 Password= SelectedParentPass123";
        assertEquals(String1, SelectedParent2.toString(), "SelectedUser2 should be set to \"Parent ID= 2 Username= SelectedParentUser123 Password= SelectedParentPass123\" but instead returned: " + SelectedParent2.toString());
    }
    
    
    /**
     ***************** END OF TEST PARENT_ACCOUNT CRUD *****************
	 */

    
	/**
     ***************** START OF TEST TASK CRUD *****************
	 */
	
    @Test
    @Order(12)
    @DisplayName("<DataBase> DatabaseAddNewTask")
    void DatabaseAddNewTask() {
    	User_Account UpdatedUser = new User_Account(nonDefaultUser);
    	UpdatedUser.setUserID(1);
    	db.AddTask(nonDefaultTask, UpdatedUser.getUserID());
    	db.AddTask(nonDefaultTask, UpdatedUser.getUserID());
    	db.AddTask(nonDefaultTask, UpdatedUser.getUserID());
    	db.AddTask(nonDefaultTask, UpdatedUser.getUserID());
    }

    @Test
    @Order(13)
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
    @Order(15)
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
    @Order(14)
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
    @Order(16)
    @DisplayName("<DataBase> DatabaseSelectAllTasks")
    void DatabaseSelectAllTasks() {
    	User_Account UpdatedUser = new User_Account(nonDefaultUser);
    	UpdatedUser.setUserID(1);

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
    	
    	test_database_task_List= db.SelectAllTasks(UpdatedUser.getUserID());
  
    	for (int i =0; i< test_database_task_List.size(); i++) {        
    		assertEquals(test_task_List.get(i).toString(), test_database_task_List.get(i).toString(), "test_database_task_List " + i + " should be set to " + test_task_List.get(i).toString() + " but instead returned: " + test_database_task_List.get(i).toString());
        }
    }
    /**
    ***************** END OF TEST TASK CRUD *****************
    */

    /**
    ***************** START OF TEST HTB CRUD *****************
    * @author jmitchel2
    */
    
    @Test
    @Order(17)
    @DisplayName("<DataBase> DatabaseAddNewHTB")
    void DatabaseAddHTB() {
    User_Account Test_User_Account= new User_Account(nonDefaultUser);
    db.AddUser_Account(Test_User_Account);
    Test_User_Account.setUserID(1);
    db.AddHTB(nonDefaultHTB, Test_User_Account.getUserID());
    db.AddHTB(nonDefaultHTB, Test_User_Account.getUserID());
    db.AddHTB(nonDefaultHTB, Test_User_Account.getUserID());
    db.AddHTB(nonDefaultHTB, Test_User_Account.getUserID());
    Test_User_Account.setUserID(5);
    db.AddHTB(nonDefaultHTB, Test_User_Account.getUserID());
    db.DeleteUser_Account(5);
    Test_User_Account.setUserID(3);
    db.AddHTB(nonDefaultHTB, Test_User_Account.getUserID());
    }
    
    @Test
    @Order(18)
    @DisplayName("<DataBase> DatabaseUpdateHTB")
    void DatabaseUpdateHTB() {
    	Happy_Thought_Button UpdatedHTB= new Happy_Thought_Button(nonDefaultHTB);
    	UpdatedHTB.setHTBID(1);
    	UpdatedHTB.setMedia_ID_Tag("I am a updated Media_ID_Tag");
    	UpdatedHTB.setFlagged(false);
    	db.UpdateHTB(UpdatedHTB);
    }
    
    @Test
    @Order(19)
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
    @Order(20)
    @DisplayName("<DataBase> DatabaseSelectHTB")
    void DatabaseSelectHTB() {
    	Happy_Thought_Button SelectedHTB1= new Happy_Thought_Button(nonDefaultHTB);
    	SelectedHTB1.setHTBID(2);
    	SelectedHTB1.setMedia_ID_Tag("I am a Selected Media ID Tag");
    	SelectedHTB1.setFlagged(true);
    	SelectedHTB1.setDT_Executed(null);
    	db.UpdateHTB(SelectedHTB1);
    	
    	Happy_Thought_Button selectedHTB2 = new Happy_Thought_Button();
    	selectedHTB2 = db.SelectHTB(2);
        
    	String String1 = "Happy_Thought_Button ID= 2 Media_ID_Tag= I am a Selected Media ID Tag Flagged= true Date Time Executed= null";
        assertEquals(String1, selectedHTB2.toString(), "selectedHTB2 should be set to Happy_Thought_Button ID= 2 Media_ID_Tag= This is a test Media ID Tag Flagged= true Date Time Executed= null but instead returned: " + selectedHTB2.toString());
        
    }
    
    @Test
    @Order(21)
    @DisplayName("<DataBase> DatabaseSelectAllHTBs")
    void DatabaseSelectAllHTBs() {
    	ArrayList<Happy_Thought_Button> test_HTB_List = new ArrayList<Happy_Thought_Button>();
    	ArrayList<Happy_Thought_Button> test_database_HTB_List = new ArrayList<Happy_Thought_Button>();

    	Happy_Thought_Button UpdatedHTB= new Happy_Thought_Button(nonDefaultHTB);
    	UpdatedHTB.setHTBID(1);
    	UpdatedHTB.setMedia_ID_Tag("I am a updated Media_ID_Tag");
    	UpdatedHTB.setFlagged(false);
    	UpdatedHTB.setDT_Executed(null);
    	
    	Happy_Thought_Button SelectedHTB1= new Happy_Thought_Button(nonDefaultHTB);
    	SelectedHTB1.setHTBID(2);
    	SelectedHTB1.setMedia_ID_Tag("I am a Selected Media ID Tag");
    	SelectedHTB1.setFlagged(true);
    	SelectedHTB1.setDT_Executed(null);
    	
    	Happy_Thought_Button DefaultHTB= new Happy_Thought_Button(nonDefaultHTB);
    	DefaultHTB.setHTBID(4);
    	DefaultHTB.setDT_Executed(null);
    	
    	test_HTB_List.add(UpdatedHTB);
    	test_HTB_List.add(SelectedHTB1);
    	test_HTB_List.add(DefaultHTB);
    	
    	test_database_HTB_List= db.SelectAllHTBs(1);
 
    	for (int i =0; i< test_database_HTB_List.size(); i++) {        
    		assertEquals(test_HTB_List.get(i).toString(), test_database_HTB_List.get(i).toString(), "test_database_HTB_List " + i + " should be set to " + test_HTB_List.get(i).toString() + " but instead returned: " + test_database_HTB_List.get(i).toString());
        }
        
    }
    
    
    /**
    ***************** END OF TEST HTB CRUD *****************
    */

    /**
    ***************** START OF TEST OBSERVER CRUD *****************
     */
    @Test
    @Order(22)
    @DisplayName("<DataBase> DatabaseAddNewObserver")
    void DatabaseAddNewObserver() {
    	Task ForObserver= new Task(nonDefaultTask);
    	ForObserver.setTaskID(4);
    	db.AddObserver(nonDefaultObserver, ForObserver.getTaskID());
    	db.AddObserver(nonDefaultObserver, ForObserver.getTaskID());
    	db.AddObserver(nonDefaultObserver, ForObserver.getTaskID());
    	db.AddObserver(nonDefaultObserver, ForObserver.getTaskID());
    
    }

    @Test
    @Order(23)
    @DisplayName("<DataBase> DatabaseUpdateObserver")
    void DatabaseUpdateObserver() {
    	Observer UpdatedObserver= new Observer(nonDefaultObserver);
    	UpdatedObserver.setObserverID(1);
    	UpdatedObserver.setObserverScore(98);
    	UpdatedObserver.setThreshold(98);
    	db.UpdateObserver(UpdatedObserver);
    
    }
    
    @Test
    @Order(24)
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
    @Order(25)
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
    @Order(26)
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
    		assertEquals(test_Observer_List.get(i).toString(), test_database_Observer_List.get(i).toString(), "test_database_Observerr_List " + i + " should be set to " + test_Observer_List.get(i).toString() + " but instead returned: " + test_database_Observer_List.get(i).toString());
        }

    	
    }

    /**
    ***************** END OF TEST OBSERVER CRUD *****************
    */

    /**
    ***************** START OF TEST SETTINGS CRUD *****************
     */

    
    @Test
    @Order(27)
    @DisplayName("<DataBase> DatabaseAddNewSettings")
    void DatabaseAddNewSettings() {
    	User_Account UpdatedUser = new User_Account(nonDefaultUser);
    	UpdatedUser.setUserID(1);
        db.AddSettings(nonDefaultSettings, UpdatedUser.getUserID());
        db.AddSettings(nonDefaultSettings, 2);
        db.AddSettings(nonDefaultSettings, 3);
        db.AddSettings(nonDefaultSettings, 4);
    }
    
    @Test
    @Order(28)
    @DisplayName("<DataBase> DatabaseUpdateSettings")
    void DatabaseUpdateSettings() {
    	Settings UpdatedSettings= new Settings(nonDefaultSettings);
    	UpdatedSettings.setSettingsID(1);
    	UpdatedSettings.setAvatarFilePath("I am an Updated Avatar File Path");
    	UpdatedSettings.setIconCircles(new Color(51,204,255));
    	db.UpdateSettings(UpdatedSettings);
    }
    
    @Test
    @Order(29)
    @DisplayName("<DataBase> DatabaseDeleteSettings")
    void DatabaseDeleteSettings() {
    	Settings deletedSettings = new Settings(nonDefaultSettings);
    	deletedSettings.setSettingsID(3);
    	deletedSettings.setAvatarFilePath("I AM A DELETED AVATAR FILE PATH");
    	db.UpdateSettings(deletedSettings);
    	db.DeleteSettings(3);
    }

    
    @Test
    @Order(30)
    @DisplayName("<DataBase> DatabaseSelectSettings")
    void DatabaseSelectSettings() {
    	Settings selectedSettings = new Settings(nonDefaultSettings);
    	selectedSettings.setSettingsID(2);
    	selectedSettings.setIcons(new Color(255,51,51));
    	selectedSettings.setOpacityIcons(25);
    	selectedSettings.setIsCollapsed(false);
    	selectedSettings.setAvatarFilePath("I am a selected Avatar File Path");
    	db.UpdateSettings(selectedSettings);
    	
    	Settings selectedSettings2 = new Settings();
    	selectedSettings2 = db.SelectSettings(2);
        
        assertEquals(2, selectedSettings2.getSettingsID(), "selectedSettings2 SettingsID should be set to 2 instead returned: " 
        			+ selectedSettings2.getSettingsID());
        assertEquals(new Color(255,51,51), selectedSettings2.getIcons(), "selectedSettings2 icons should be set to Color(255,51,51) instead returned: " 
    			+ selectedSettings2.getIcons());
        assertEquals(25, selectedSettings2.getOpacityIcons(), "selectedSettings2 OpacityIcons should be set to 75 instead returned: " 
    			+ selectedSettings2.getOpacityIcons());
        assertEquals(false, selectedSettings2.getIsCollapsed(), "selectedSettings2 isCollapsed should be set to false instead returned: " 
    			+ selectedSettings2.getIsCollapsed());
        assertEquals("I am a selected Avatar File Path", selectedSettings2.getAvatarFilePath(), "selectedSettings2 Avatar File Path should be set to \"I am a selected Avatar File Path\" instead returned: " 
    			+ selectedSettings2.getAvatarFilePath());       
    }

    /**
    ***************** END OF TEST SETTINGS CRUD *****************
    */
    
    /**
    ***************** START OF TEST LINKING ACCOUNTS CRUD *****************
    */

    @Test
    @Order(31)
    @DisplayName("<DataBase> DatabaseAddLinked_Account")
    void DatabaseAddLinked_Account() {
    	Parent_Account linkParent1 = new Parent_Account(nonDefaultParent);
    	linkParent1.setParentID(1);
    	Parent_Account linkParent2 = new Parent_Account(nonDefaultParent);
    	linkParent2.setParentID(2);
    	User_Account linkUser1 = new User_Account(nonDefaultUser);
    	linkUser1.setUserID(1);
    	User_Account linkUser2 = new User_Account(nonDefaultUser);
    	linkUser2.setUserID(2);
    	User_Account linkUser4 = new User_Account(nonDefaultUser);
    	linkUser4.setUserID(4);
    	
    	db.AddLinked_Account(linkParent1, linkUser1);
    	db.AddLinked_Account(linkParent1, linkUser2);
    	db.AddLinked_Account(linkParent1, linkUser4);
    	db.AddLinked_Account(linkParent2, linkUser1);
    	db.AddLinked_Account(linkParent2, linkUser4);
    }

    @Test
    @Order(32)
    @DisplayName("<DataBase> DatabaseAddLinked_Account")
    void DatabaseDeleteLinked_Account() {
    	Parent_Account linkParent = new Parent_Account(nonDefaultParent);
    	linkParent.setParentID(1);
    	User_Account DeletedLinkUser = new User_Account(nonDefaultUser);
    	DeletedLinkUser.setUserID(4);    	
    	db.DeleteLinked_Account(linkParent, DeletedLinkUser);
    }

    @Test
    @Order(33)
    @DisplayName("<DataBase> DatabaseAddLinked_Account")
    void DatabaseSelect_All_Users_Linked_Account() {
    	Parent_Account linkParent1 = new Parent_Account(nonDefaultParent);
    	Parent_Account linkParent2 = new Parent_Account(nonDefaultParent);
    	
    	linkParent1.setParentID(1);
    	ArrayList<User_Account> testDBlinkedUsersList1= new ArrayList<User_Account>();
    	testDBlinkedUsersList1= db.Select_All_Users_Linked_Account(linkParent1);
    	linkParent2.setParentID(2);
    	ArrayList<User_Account> testDBlinkedUsersList2= new ArrayList<User_Account>();
    	testDBlinkedUsersList2= db.Select_All_Users_Linked_Account(linkParent2);

    	ArrayList<User_Account> testLinkedUsersList1= new ArrayList<User_Account>();
    	ArrayList<User_Account> testLinkedUsersList2= new ArrayList<User_Account>();

    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
    	UpdatedUser.setUserID(1);
    	UpdatedUser.setUsername("UpdatedUsername123");
    	UpdatedUser.setPassword("UpdatedPassword123");
    	testLinkedUsersList1.add(UpdatedUser);
    	testLinkedUsersList2.add(UpdatedUser);

    	User_Account SelectedUser = new User_Account(nonDefaultUser);
    	SelectedUser.setUserID(2);
    	SelectedUser.setUsername("SelectedUsername123");
    	SelectedUser.setPassword("SelectedPassword123");
    	testLinkedUsersList1.add(SelectedUser);
    	
    	User_Account DefaultUser= new User_Account(nonDefaultUser);
    	DefaultUser.setUserID(4);
    	testLinkedUsersList2.add(DefaultUser);
    	

    	for (int i =0; i< testDBlinkedUsersList1.size(); i++) {        
    		assertEquals(testLinkedUsersList1.get(i).toString(), testDBlinkedUsersList1.get(i).toString(), "testDBlinkedUsersList " + i + " should be set to " + testLinkedUsersList1.get(i).toString() + " but instead returned: " + testDBlinkedUsersList1.get(i).toString());
        }
    	
    	for (int i2 =0; i2< testDBlinkedUsersList2.size(); i2++) {        
    		assertEquals(testLinkedUsersList2.get(i2).toString(), testDBlinkedUsersList2.get(i2).toString(), "testDBlinkedUsersList " + i2 + " should be set to " + testLinkedUsersList2.get(i2).toString() + " but instead returned: " + testDBlinkedUsersList2.get(i2).toString());
        }

    }
    
    /**
    ***************** END OF TEST LINKING ACCOUNTS CRUD *****************
    */

    /**
    ***************** START OF TEST NOTIFICATIONS CRUD *****************
     */

    @Test
    @Order(34)
    @DisplayName("<DataBase> DatabaseAddNewNotification")
    void DatabaseAddNotification() {
    User_Account Test_User_Account= new User_Account(nonDefaultUser);
    db.AddUser_Account(Test_User_Account);
    Test_User_Account.setUserID(1);
    db.AddNotification(nonDefaultNotify, Test_User_Account.getUserID());
    db.AddNotification(nonDefaultNotify, Test_User_Account.getUserID());
    db.AddNotification(nonDefaultNotify, Test_User_Account.getUserID());
    db.AddNotification(nonDefaultNotify, Test_User_Account.getUserID());
    Test_User_Account.setUserID(5);
    db.AddNotification(nonDefaultNotify, Test_User_Account.getUserID());
    db.DeleteUser_Account(5);
    Test_User_Account.setUserID(3);
    db.AddNotification(nonDefaultNotify, Test_User_Account.getUserID());
    }

    @Test
    @Order(35)
    @DisplayName("<DataBase> DatabaseUpdateNotification_System")
    void DatabaseUpdateNotification() {
    	Notification UpdatedNotify= new Notification(nonDefaultNotify);
    	UpdatedNotify.setNotificationID(1);
    	UpdatedNotify.setType("I am a updated type");
    	UpdatedNotify.setIgnored(false);
    	db.UpdateNotification(UpdatedNotify);
    }
    
    @Test
    @Order(36)
    @DisplayName("<DataBase> DatabaseDeleteNotification_System")
    void DatabaseDeleteNotification() {
    	Notification DeletedNotify= new Notification(nonDefaultNotify);
    	DeletedNotify.setNotificationID(3);
    	DeletedNotify.setType("I am supposed to be deleted");
    	DeletedNotify.setIgnored(false);
    	db.UpdateNotification(DeletedNotify);
      	db.DeleteNotification(3);
    }

    @Test
    @Order(37)
    @DisplayName("<DataBase> DatabaseSelectNotification")
    void DatabaseSelectNotification() {
    	Notification SelectedNotify= new Notification(nonDefaultNotify);
    	SelectedNotify.setNotificationID(2);
    	SelectedNotify.setType("I am a Selected Type");
    	SelectedNotify.setIgnored(true);
    	db.UpdateNotification(SelectedNotify);
    	Notification selectedNotify2 = new Notification();
    	selectedNotify2 = db.SelectNotification(2);
    	String String1 = "Notification ID= 2 Type= I am a Selected Type Ignored= true Date and Time of Notification= Sun Aug 31 20:00:00 EDT 2008";
        assertEquals(String1, selectedNotify2.toString(), "selectedHTB2 should be set to \"Notification ID= 2 Type= I am a Selected Type Ignored= true Date and Time of Notification= Sun Aug 31 20:00:00 EDT 2008\" but instead returned: " + selectedNotify2.toString());
    }
    
    @Test
    @Order(38)
    @DisplayName("<DataBase> DatabaseSelectAllNotifications")
    void DatabaseSelectAllNotifications() {
    	ArrayList<Notification> test_Notification_List = new ArrayList<Notification>();
    	ArrayList<Notification> test_database_Notification_List = new ArrayList<Notification>();
    	
    	Notification UpdatedNotify= new Notification(nonDefaultNotify);
    	UpdatedNotify.setNotificationID(1);
    	UpdatedNotify.setType("I am a updated type");
    	UpdatedNotify.setIgnored(false);
    
    	Notification SelectedNotify= new Notification(nonDefaultNotify);
    	SelectedNotify.setNotificationID(2);
    	SelectedNotify.setType("I am a Selected Type");
    	SelectedNotify.setIgnored(true);
    
    	Notification DefaultNotify= new Notification(nonDefaultNotify);
    	DefaultNotify.setNotificationID(4);

    	test_Notification_List.add(UpdatedNotify);
    	test_Notification_List.add(SelectedNotify);
    	test_Notification_List.add(DefaultNotify);    	
    	
    	test_database_Notification_List= db.SelectAllNotifications(1);
    	
    	for (int i =0; i< test_database_Notification_List.size(); i++) {        
    		assertEquals(test_Notification_List.get(i).toString(), test_database_Notification_List.get(i).toString(), "test_database_Notification_List " + i + " should be set to " + test_Notification_List.get(i).toString() + " but instead returned: " + test_database_Notification_List.get(i).toString());
        }
    	
    } 

    /**
     ***************** END OF TEST NOTIFICATION SYSTEM CRUD *****************
     */

    /**
     ***************** START OF TEST EVENTS SYSTEM ADD & COUNT *****************
     */
    
    @Test
    @Order(39)
    @DisplayName("<DataBase> DatabaseAddEvents")
    void DatabaseAddEvents() {
    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
     	UpdatedUser.setUserID(1);
     	Date date1= new Date(1220227200L * 1000);
     	Date date2= new Date(1220227205L * 1000);
     	Date date3= new Date(1220227230L * 1000);
     	db.AddEvent(UpdatedUser.getUserID(), date1, "TestEventType1");
     	db.AddEvent(UpdatedUser.getUserID(), date2, "TestEventType2");
     	db.AddEvent(UpdatedUser.getUserID(), date2, "TestEventType1");
     	db.AddEvent(UpdatedUser.getUserID(), date3, "TestEventType1");
     	

    }

    @Test
    @Order(40)
    @DisplayName("<DataBase> DatabaseCountEvents")
    void DatabaseCountEvents() {
    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
     	UpdatedUser.setUserID(1);
     	int test_CountOfEvents= 0;
     	Date date1= new Date(1220227200L * 1000);
     	Date date2= new Date(1220227205L * 1000);
     	test_CountOfEvents = db.CountEvents(UpdatedUser.getUserID(), date1, date2, "TestEventType1");
		assertEquals(2, test_CountOfEvents, "test_CountOfEvents should be set to 2 but instead returned: " + test_CountOfEvents);
	
    }

    /**
     ***************** END OF TEST EVENTS SYSTEM ADD & COUNT *****************
     */

    /**
     ***************** START OF TEST FTS_COLORS ADD, DELETE, SELECT ALL *****************
     */
    @Test
    @Order(41)
    @DisplayName("<DataBase> DatabaseAddFTS_Color")
    void DatabaseAddFTS_Color() {
    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
     	UpdatedUser.setUserID(1);
     	db.AddFTS_Color(Color.black, UpdatedUser.getUserID());
     	db.AddFTS_Color(Color.blue, UpdatedUser.getUserID());
     	db.AddFTS_Color(Color.red, UpdatedUser.getUserID());
     	db.AddFTS_Color(Color.yellow, UpdatedUser.getUserID());
     	UpdatedUser.setUserID(2);
     	db.AddFTS_Color(Color.white, UpdatedUser.getUserID());
    }
    
    @Test
    @Order(42)
    @DisplayName("<DataBase> DatabaseDeleteFTS_Color")
    void DatabaseDeleteFTS_Color() {
    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
     	UpdatedUser.setUserID(1);
    	db.DeleteFTS_Color(Color.black, UpdatedUser.getUserID());
    	db.DeleteFTS_Color(Color.white, UpdatedUser.getUserID());
    }    
    
    @Test
    @Order(43)
    @DisplayName("<DataBase> DatabaseSelectAllFTS_Color")
    void DatabaseSelectAllFTS_Color() {
    	User_Account UpdatedUser= new User_Account(nonDefaultUser);
     	UpdatedUser.setUserID(1);
     	ArrayList<Color> testColors = new ArrayList<Color>();
     	ArrayList<Color> testDataBaseColors = new ArrayList<Color>();
     	testColors.add(Color.blue);
     	testColors.add(Color.red);
     	testColors.add(Color.yellow);
     	testDataBaseColors= db.SelectAllFTS_Color(UpdatedUser.getUserID());

     	for (int i =0; i< testDataBaseColors.size(); i++) {        
    		assertEquals(testColors.get(i).toString(), testDataBaseColors.get(i).toString(), "testDataBaseColors " + i + " should be set to " + testColors.get(i).toString() + " but instead returned: " + testDataBaseColors.get(i).toString());
        }

    }

    /**
     ***************** END OF TEST EVENTS SYSTEM ADD & COUNT *****************
     */
    
    @Test
    @Order(44)
    @DisplayName("<DataBase> DatabaseTestPlayGround")
    void DatabaseTestPlayground() {
    /**	User_Account UpdatedUser= new User_Account(nonDefaultUser);
     *	UpdatedUser.setUserID(1);
     *	UpdatedUser.setUsername("UpdatedUsername1234");
     *	UpdatedUser.setPassword("UpdatedPassword1234");
     *	db.UpdateUser_Account(UpdatedUser);
    */
    	
    }

}