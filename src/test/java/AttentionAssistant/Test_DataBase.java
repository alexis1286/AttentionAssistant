package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;
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
	nonDefaultTask = new Task(testTaskID, testDescription, testObservable, testStatus);
	db = new DataBase();
	db.DatabaseSetUp();
	}

    @Test
    @DisplayName("<DataBase> DatabaseAddNewTask")
    void DatabaseAddNewTask() {
    	db.AddTask(nonDefaultTask);
    	}
	
}
