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
	
}
