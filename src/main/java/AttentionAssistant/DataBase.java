package AttentionAssistant;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.sqlite.SQLiteDataSource;

public class DataBase {
	
	private SQLiteDataSource ds;
	
	
	/**
	 * Instantiating empty Task object
	 */

	public DataBase() {
		this.ds= new SQLiteDataSource();
	}	
	
	/**
	  * Initial Database Setup
	  * 
      * @return Connection
      */
    public void DatabaseSetUp() {
        String url = "jdbc:sqlite:bin/Attention_Assistant.db" ;
        ds.setUrl(url);
        try (Connection conn1 = ds.getConnection()) {
            if (conn1 != null) {
                DatabaseMetaData meta = conn1.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            }
    
    }
    
    /**
     ******* START OF TASK CRUD *******
     */
    
    
    /**
     * Add a new task to the database.
     * @param task
     */
    public void AddTask(Task task) {
    	String query = "CREATE TABLE IF NOT EXISTS task ( " +
    			 "taskID INTEGER PRIMARY KEY, " +
    			 "description TEXT, " +
    			 "observable BOOLEAN, " +
    			 "status TEXT, " + 
    			 "name TEXT, " +
    			 "dueDate DATE, " +
    			 "priority BOOLEAN)";
    	try (Connection conn = this.ds.getConnection();
    			Statement stmt = conn.createStatement(); ){
    		int rv = stmt.executeUpdate(query);
    		System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(task.getDueDate());
    	String query1 = "INSERT INTO task " +
    			"( description, observable, status, name, dueDate, priority ) Values ( '" +
    			task.getDescription() + "', '" +
    			task.getObservable() + "', '" +
    			task.getStatus().toString() + "', '" +
    			task.getName() + "', '" +
    			DateTime + "', '" +
    			task.getPriority() +"')";
    	try ( Connection conn = ds.getConnection();
    		    Statement stmt = conn.createStatement(); ) {
    		    int rv = stmt.executeUpdate( query1 );
    		    System.out.println( "AddTask() returned " + rv );
    		} catch ( SQLException e ) {
    		    e.printStackTrace();
    		    System.exit( 0 );
    		}
    }
    
    /**
     * Update a task within the Database
     * @param task
     */
        public void UpdateTask(Task task) {
        	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(task.getDueDate());
        	String query1 = "UPDATE task " +
        			"SET description = '" + task.getDescription() + 
        			"', observable = '" + task.getObservable() + 
        			"', status = '" + task.getStatus().toString() +
        			"', name = '" + task.getName() +
        			"', dueDate = '" + DateTime +
        			"', priority = '" + task.getPriority() +
        			"' WHERE taskID = '" + task.getTaskID() + "'";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    int rv = stmt.executeUpdate( query1 );
        		    System.out.println( "UpdateTask() returned " + rv );
        		} catch ( SQLException e ) {
        		    e.printStackTrace();
        		    System.exit( 0 );
        		}
        	
        }   
        /**
         * Delete a task within the Database
         * @param taskid
         */
        public void DeleteTask(int taskid) {
        	String query1 = "DELETE FROM task WHERE taskID = '" + taskid + "'";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    int rv = stmt.executeUpdate( query1 );
        		    System.out.println( "DeleteTask() returned " + rv );
        		} catch ( SQLException e ) {
        		    e.printStackTrace();
        		    System.exit( 0 );
        		}
        	
        }    	
        /**
         * Select a task within the database using the taskid
         * @param taskid
         * @return Task
         */
        public Task SelectTask(int taskid) {
        	Task task1 = new Task();
        	String query1 = "SELECT * FROM task WHERE taskID = '" + taskid + "'";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    ResultSet rs = stmt.executeQuery( query1 );
        		    task1.setTaskID(rs.getInt("taskID"));
        		    task1.setDescription(rs.getString("description"));
        		    task1.setObservable(Boolean.valueOf(rs.getString("observable")));
        		    task1.setStatus(TaskStatus.valueOf(rs.getString("status")));
        		    task1.setName(rs.getString("name"));
        		    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dueDate"));
        		    task1.setDueDate(date1);
        		    task1.setPriority(Boolean.valueOf(rs.getString("priority")));
        		    System.out.println( "SelectTask() returned " + rs );
        		} catch ( SQLException e ) {
        			e.printStackTrace();
        		    System.exit( 0 );
        		}
        		  catch ( ParseException p ) {
        			p.printStackTrace();
        			System.exit( 0 );
        		}
        	return task1;
        }
        
        /**
         * Grab all tasks within the Database
         * 
         * This will eventually have a parameter int to grab by user id
         * 
         * @return ArrayList<Task>
         */
        public ArrayList<Task> SelectAllTasks(){
        	ArrayList<Task> tasksOnList = new ArrayList<Task>();
        	Task blankTask = new Task();
        	String query1 = "SELECT * FROM task";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    ResultSet rs = stmt.executeQuery( query1 );
        		    while (rs.next()){
        		    blankTask = new Task();
        		    blankTask.setTaskID(rs.getInt("taskID"));
        		    blankTask.setDescription(rs.getString("description"));
        		    blankTask.setObservable(Boolean.valueOf(rs.getString("observable")));
        		    blankTask.setStatus(TaskStatus.valueOf(rs.getString("status")));
        		    blankTask.setName(rs.getString("name"));
        		    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dueDate"));
        		    blankTask.setDueDate(date1);
        		    blankTask.setPriority(Boolean.valueOf(rs.getString("priority")));
        		    tasksOnList.add(blankTask);
        		    }
        		    System.out.println( "SelectAllTasks() returned " + rs );
        		} catch ( SQLException e ) {
        			e.printStackTrace();
        		    System.exit( 0 );
        		}
        		  catch ( ParseException p ) {
        			p.printStackTrace();
        			System.exit( 0 );
        		}
        	return tasksOnList;
        }
        
        /**
         * Mainly used for JUNIT testing, deletes the task table at the end of testing to remove all test data.
         * 
         * 
         */
        public void DeleteAllTasks(){
        	String query1 = "DROP TABLE IF EXISTS 'task'";
        	try ( Connection conn = this.ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
    		    int rv = stmt.executeUpdate( query1 );
    		    System.out.println( "DeleteAllTasks() returned " + rv );
        	} catch ( SQLException e ) {
    			e.printStackTrace();
    		    System.exit( 0 );
        }

        }
        /**
        ******* END OF TASK CRUD *******
        */
}
