package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_DataBase {
	/**
	 * Objects used in test
	 */
	Task nonDefaultTask;
	DataBase db;
	
	@BeforeEach
	void setup() {
	int testTaskID = 999;
	String testDescription = "This is a test description";
	boolean testObservable = true;
	TaskStatus testStatus = TaskStatus.OPEN;
	String testName = "This is a test Name";
	Date testDate = new Date(1220227200L * 1000);
	boolean testPriority = true;
	nonDefaultTask = new Task(testTaskID, testDescription, testObservable, testStatus, testName, testDate, testPriority);
	db = new DataBase();
	db.DatabaseSetUp();
	}

    @Test
    @DisplayName("<DataBase> DatabaseAddNewTask")
    void DatabaseAddNewTask() {
    	db.AddTask(nonDefaultTask);
    	}

    @Test
    @DisplayName("<DataBase> DatabaseUpdateTask")
    void DatabaseUpdateTask() {
    	nonDefaultTask.setTaskID(1);
    	nonDefaultTask.setDescription("I am a updated description1 ");
    	nonDefaultTask.setObservable(false);
    	nonDefaultTask.setName("I am an updated name1 ");
    	db.UpdateTask(nonDefaultTask);
    }
    
    @Test
    @DisplayName("<DataBase> DatabaseDeleteTask")
    void DatabaseDeleteTask() {
    	
    	nonDefaultTask.setTaskID(2);
    	nonDefaultTask.setDescription("I am supposed to be deleted ");
    	nonDefaultTask.setObservable(false);
    	nonDefaultTask.setName("I am supposed to be deleted ");
    	db.UpdateTask(nonDefaultTask);
    	db.DeleteTask(nonDefaultTask.getTaskID());    
    }
    
    @Test
    @DisplayName("<DataBase> DatabaseSelectTask")
    void DatabaseSelectTask() {
    	Task PleaseWork = new Task();
    	PleaseWork = db.SelectTask(1);
        String String1 = "Task ID= 1 Priority= true Name= This is an updated Name1 Description= I am a updated description1 Due Date= Sun Aug 31 20:00:00 EDT 2008 Observable= false Status= OPEN";
        assertEquals(String1, PleaseWork.toString(), "String1 should be set to Task ID= 1 Priority= true Name= This is an updated Name1 Description= I am a updated description1 Due Date= Sun Aug 31 20:00:00 EDT 2008 Observable= false Status= OPEN but instead returned: " + PleaseWork.toString());
 
    }
    
}
