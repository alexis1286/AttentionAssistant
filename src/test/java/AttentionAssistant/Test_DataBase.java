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
	DataBase db = new DataBase();

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
	db.DatabaseSetUp();	
	db.AddTask(nonDefaultTask);
	}

    @Test
    @DisplayName("<DataBase> DatabaseAddNewTask")
    void DatabaseAddNewTask() {
    	db.AddTask(nonDefaultTask);
    	
    }

    @Test
    @DisplayName("<DataBase> DatabaseUpdateTask")
    void DatabaseUpdateTask() {
    	Task UpdatedTask= new Task(nonDefaultTask);
    	UpdatedTask.setTaskID(1);
    	UpdatedTask.setDescription("I am a updated description1");
    	UpdatedTask.setObservable(false);
    	UpdatedTask.setName("I am an updated name1");
    	db.UpdateTask(UpdatedTask);
    
    }
    
    @Test
    @DisplayName("<DataBase> DatabaseDeleteTask")
    void DatabaseDeleteTask() {
    	Task DeletedTask= new Task(nonDefaultTask);
    	DeletedTask.setTaskID(3);
    	DeletedTask.setDescription("I am supposed to be deleted");
    	DeletedTask.setObservable(false);
    	DeletedTask.setName("I am supposed to be deleted");
    	db.UpdateTask(DeletedTask);
    	
    	db.DeleteTask(3);    
    }
    
    @Test
    @DisplayName("<DataBase> DatabaseSelectTask")
    void DatabaseSelectTask() {
    	Task SelectedTask1= new Task(nonDefaultTask);
    	SelectedTask1.setTaskID(2);
    	SelectedTask1.setDescription("I am a Selected description1");
    	SelectedTask1.setName("I am an Selected name1");
       	SelectedTask1.setObservable(true);
       	SelectedTask1.setPriority(true);
    	db.UpdateTask(SelectedTask1);
    	
    	Task selectedTask2 = new Task();
    	selectedTask2 = db.SelectTask(2);
        
    	//String String1 = "Task ID= 2 Priority= true Name= I am an Selected name1 Description= I am a Selected description1 Due Date= Sun Aug 31 20:00:00 EDT 2008 Observable= true Status= OPEN";
        //assertEquals(String1, selectedTask2.toString(), "selectedTask2 should be set to Task ID= 1 Priority= false Name= This is an Selected name1 Description= I am a Selected description1 Due Date= Sun Aug 31 20:00:00 EDT 2008 Observable= false Status= OPEN but instead returned: " + selectedTask2.toString());
        assertEquals(true, selectedTask2.getPriority(), "PleaseWork priority should be set to true but instead returned: " + selectedTask2.getPriority());
        assertEquals(2, selectedTask2.getTaskID(), "PleaseWork taskid should be set to 1 but instead returned: " + selectedTask2.getTaskID());
        assertEquals("I am a Selected description1", selectedTask2.getDescription(), "PleaseWork description should be set to I am a Selected description1 but instead returned: " + selectedTask2.getDescription());
        assertEquals("I am an Selected name1", selectedTask2.getName(), "PleaseWork name should be set to I am an Selected name1 but instead returned: " + selectedTask2.getName());
        assertEquals(new Date(1220227200L * 1000), selectedTask2.getDueDate(), "PleaseWork duedate should be set to Sun Aug 31 20:00:00 EDT 2008 but instead returned: " + selectedTask2.getDueDate());
        assertEquals(true, selectedTask2.getObservable(), "PleaseWork observable should be set to false but instead returned: " + selectedTask2.getObservable());
        assertEquals(TaskStatus.OPEN , selectedTask2.getStatus(), "PleaseWork status should be set to OPEN but instead returned: " + selectedTask2.getStatus().toString());
        
    }
    
}