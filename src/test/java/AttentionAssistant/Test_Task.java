package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_Task {
	/**
	 * Objects used in test
	 */
	Task defaultTask;
	Task nonDefaultTask;
	Task copyTask;
	
	@BeforeEach
	void setup() {
	int testTaskID = 999;
	String testDescription = "This is a test description";
	boolean testObservable = true;
	Status testStatus = Status.OPEN;
	
	defaultTask= new Task();
	
	nonDefaultTask = new Task(testTaskID, testDescription, testObservable, testStatus);
	
	copyTask = new Task(nonDefaultTask);
	
	}
	
    @Test
    @DisplayName("<Task> Default Constructor")
    void TaskDefaultConstructor() {

        /**
         *  Make sure the Task taskID is 0 for the default constructor
         */
        assertEquals(0, defaultTask.getTaskID(), 
        "Default constructor task.taskID should be 0. Returned: " + Integer.toString(defaultTask.getTaskID()));
        
        /**
         *  Make sure the Task description is empty for the default constructor
         */
        assertEquals("" , defaultTask.getDescription(), 
        "Default constructor task.description should be empty. Returned: " + defaultTask.getDescription());
        
        /**
         *  Make sure the Task observable is set to false for the default constructor
         */
        assertEquals(false , defaultTask.getObservable(), 
        "Default constructor task.observable should be false. Returned: " + String.valueOf(defaultTask.getObservable()));

        /**
         *  Make sure the Task status is set to CLOSED for the default constructor
         */
        assertEquals("CLOSED" , defaultTask.getStatus(), 
        "Default constructor task.status should be CLOSED. Returned: " + defaultTask.getStatus());
 
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Task> Parameter Constructor")
    void TaskParameterConstructor() {

        /**
         *  Make sure the Task taskID is 999 for the Parameter constructor
         */
        assertEquals(999, nonDefaultTask.getTaskID(), 
        "Parameter constructor task.taskID should be 999. Returned: " + Integer.toString(nonDefaultTask.getTaskID()));
        
        /**
         *  Make sure the Task description is "This is a test description" for the Parameter constructor
         */
        assertEquals("This is a test description" , nonDefaultTask.getDescription(), 
        "Parameter constructor task.description should be \"This is a test description\". Returned: " + nonDefaultTask.getDescription());
        
        /**
         *  Make sure the Task observable is set to true for the Parameter constructor
         */
        assertEquals(true , nonDefaultTask.getObservable(), 
        "Parameter constructor task.observable should be true. Returned: " + String.valueOf(nonDefaultTask.getObservable()));

        /**
         *  Make sure the Task status is set to OPEN for the Parameter constructor
         */
        assertEquals("OPEN" , nonDefaultTask.getStatus(), 
        "Parameter constructor task.status should be OPEN. Returned: " + nonDefaultTask.getStatus());
 
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Task> Parameter Constructor")
    void TaskCopyConstructor() {

        /**
         *  Make sure the Task taskID is 999 for the Copy constructor
         */
        assertEquals(999, copyTask.getTaskID(), 
        "Copy constructor task.taskID should be 999. Returned: " + Integer.toString(copyTask.getTaskID()));
        
        /**
         *  Make sure the Task description is "This is a test description" for the Copy constructor
         */
        assertEquals("This is a test description" , copyTask.getDescription(), 
        "Copy constructor task.description should be \"This is a test description\". Returned: " + copyTask.getDescription());
        
        /**
         *  Make sure the Task observable is set to true for the Copy constructor
         */
        assertEquals(true , copyTask.getObservable(), 
        "Copy constructor task.observable should be true. Returned: " + String.valueOf(copyTask.getObservable()));

        /**
         *  Make sure the Task status is set to OPEN for the Copy constructor
         */
        assertEquals("OPEN" , copyTask.getStatus(), 
        "Copy constructor task.status should be OPEN. Returned: " + copyTask.getStatus());
 
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Task> SetTaskID")
    void taskSetTaskID() {
    	
    	copyTask.setTaskID(998);
    	assertEquals(998, copyTask.getTaskID(), "copyTask taskID should be set to 998 but instead returned: " + Integer.toString(copyTask.getTaskID()));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Task> SetDescription")
    void taskSetDescription() {
    	
    	copyTask.setDescription("I am still a test");
    	assertEquals("I am still a test", copyTask.getDescription(), "copyTask description should be set to \"I am still a test\" but instead returned: " + copyTask.getDescription());
    }
    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Task> SetObservable")
    void taskSetObservable() {
        	
        copyTask.setObservable(false);
        assertEquals(false, copyTask.getObservable(), "copyTask observable should be set to false but instead returned: " + String.valueOf(copyTask.getObservable()));
    }    	

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */


    @Test
    @DisplayName("<Task> SetStatus")
    void taskSetStatus() {
        	
        copyTask.setStatus(Status.CLOSED);
        assertEquals("CLOSED", copyTask.getStatus(), "copyTask status should be set to CLOSED but instead returned: " + copyTask.getStatus());
    }    	

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
}