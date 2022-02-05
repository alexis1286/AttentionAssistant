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
	 * @author jmitchel2
	 */
	public DataBase() {
		this.ds= new SQLiteDataSource();
	}

	
	/**
	  * Initial Database Setup
	  * 
      * @author jmitchel2
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
        
        /**
         * Set up for Table Task
         */
    	String queryTable = "CREATE TABLE IF NOT EXISTS task ( " +
   			 "taskID INTEGER PRIMARY KEY, " +
   			 "description TEXT, " +
   			 "observable BOOLEAN, " +
   			 "status TEXT, " + 
   			 "name TEXT, " +
   			 "dueDate DATE, " +
   			 "priority BOOLEAN)";
    	
    	/**
    	 * Set up for Table HappyThoughtButton
    	 */
    	String queryHappyThoughtButton = "CREATE TABLE IF NOT EXISTS happy_thought_button ( " +
   			 "hTBID INTEGER PRIMARY KEY, " +
   			 "media_ID_Tag TEXT, " +
   			 "flagged BOOLEAN, " +
   			 "dT_Executed DATE)";
    	
    	/**
    	 * Set up for Table Observer
    	 */
    	String queryObserver = "CREATE TABLE IF NOT EXISTS observer ( " +
   			 "observerID INTEGER PRIMARY KEY, " +
   			 "fk_taskID INTEGER, " +
   			 "observerScore INTEGER, " +
   			 "threshold INTEGER, " +
   			 "dT_Gathered DATE, " +
   			 "FOREIGN KEY (\"fk_taskID\") REFERENCES \"task\"(\"taskID\"))";

	try (Connection conn = this.ds.getConnection();
   			Statement stmt = conn.createStatement(); ){
   		int rv1 = stmt.executeUpdate(queryTable);
   		System.out.println( "CreateTaskTable() returned " + rv1 );
   		int rv2 = stmt.executeUpdate(queryHappyThoughtButton);
   		System.out.println( "CreateHTBTable() returned " + rv2 );
   		int rv3 = stmt.executeUpdate(queryObserver);
   		System.out.println( "CreateObserverTable() returned " + rv3 );
   		
       } catch ( SQLException e ) {
           e.printStackTrace();
           System.exit( 0 );
       }
    
    }

    
    /**
     * Add a new task to the database.
     * @param task
     */
    public void AddTask(Task task) {

    	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(task.getDueDate());
    	String query1 = "INSERT INTO task " +
    			"( description, observable, status, name, dueDate, priority ) Values ( '" +
    			task.getDescription() + "', '" +
    			task.getObservable() + "', '" +
    			task.getStatus().toString() + "', '" +
    			task.getTaskName() + "', '" +
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
        			"', name = '" + task.getTaskName() +
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
         * @param int
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
         * @param int
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
        		    task1.setTaskName(rs.getString("name"));
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
        		    blankTask.setTaskName(rs.getString("name"));
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
         * Mainly used for JUNIT testing, deletes the task table at the beginning of testing to remove all test data.
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

        /**
        ******* START OF HTB CRUD *******
        * @author jmitchel2
        */
        /**
         * Add a new Happy_Thought_Button to the database.
         * @param Happy_Thought_Button
         */
        public void AddHTB(Happy_Thought_Button hTB) {
        	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(hTB.getDT_Executed());
        	String query1 = "INSERT INTO happy_thought_button " +
        			"( media_ID_Tag, flagged, dT_Executed) Values ( '" +
        			hTB.getMedia_ID_Tag() + "', '" +
        			hTB.getFlagged() + "', '" +
        			DateTime + "')";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    int rv = stmt.executeUpdate( query1 );
        		    System.out.println( "AddhTB() returned " + rv );
        		} catch ( SQLException e ) {
        		    e.printStackTrace();
        		    System.exit( 0 );
        		}
        }
        /**
         * Update a Happy_Thought_Button within the Database
         * 
         * @param Happy_Thought_Button
         */
            public void UpdateHTB(Happy_Thought_Button hTB) {
            	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(hTB.getDT_Executed());
            	String query1 = "UPDATE happy_thought_button " +
            			"SET media_ID_Tag = '" + hTB.getMedia_ID_Tag() + 
            			"', flagged = '" + hTB.getFlagged() + 
            			"', dT_Executed = '" + DateTime + 
            			"' WHERE hTBID = '" + hTB.getHTBID() + "'";
            	try ( Connection conn = ds.getConnection();
            		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "UpdateHTB() returned " + rv );
            		} catch ( SQLException e ) {
            		    e.printStackTrace();
            		    System.exit( 0 );
            		}
            	
            }   
            /**
             * Delete a Happy_Thought_Button within the Database
             *              * 
             * @param int
             */
            public void DeleteHTB(int hTBID) {
            	String query1 = "DELETE FROM happy_thought_button WHERE hTBID = '" + hTBID + "'";
            	try ( Connection conn = ds.getConnection();
            		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "DeleteHTB() returned " + rv );
            		} catch ( SQLException e ) {
            		    e.printStackTrace();
            		    System.exit( 0 );
            		}
            	
            }    	
            /**
             * Select a Happy_Thought_Button within the database using the hTBID
             *              * 
             * @param int
             * @return Happy_Thought_Button
             */
            public Happy_Thought_Button SelectHTB(int hTBID) {
            	Happy_Thought_Button hTB1 = new Happy_Thought_Button();
            	String query1 = "SELECT * FROM happy_thought_button WHERE hTBID = '" + hTBID + "'";
            	try ( Connection conn = ds.getConnection();
            		    Statement stmt = conn.createStatement(); ) {
            		    ResultSet rs = stmt.executeQuery( query1 );
            		    hTB1.setHTBID(rs.getInt("hTBID"));
            		    hTB1.setMedia_ID_Tag(rs.getString("media_ID_Tag"));
            		    hTB1.setFlagged(Boolean.valueOf(rs.getString("flagged")));
            		    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dT_Executed"));
            		    hTB1.setDT_Executed(date1);
            		    System.out.println( "SelectTask() returned " + rs );
            		} catch ( SQLException e ) {
            			e.printStackTrace();
            		    System.exit( 0 );
            		}
      			 catch ( ParseException p ) {
      				 p.printStackTrace();
      				 System.exit( 0 );
      			 }

            	return hTB1;
            }
            
            /**
             * Mainly used for JUNIT testing, deletes the happy_thought_button table at the beginning of testing to remove all test data.
             * 
             */
            public void DeleteAllHTBs(){
            	String query1 = "DROP TABLE IF EXISTS 'happy_thought_button'";
            	try ( Connection conn = this.ds.getConnection();
            		    Statement stmt = conn.createStatement(); ) {
        		    int rv = stmt.executeUpdate( query1 );
        		    System.out.println( "DeleteAllHTBs() returned " + rv );
            	} catch ( SQLException e ) {
        			e.printStackTrace();
        		    System.exit( 0 );
            }
            	
            }
            
            /**
             ******* END OF HTB CRUD *******
             */

             /**
             ******* START OF Observer CRUD *******
             * @author jmitchel2, ehols001
             */
 
            
            /**
             * Add a new Observer to the database.
             * @param Observer, Task
             */
            public void AddObserver(Observer observer, Task task) {

            	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(observer.getDTGathered());
            	String query1 = "INSERT INTO observer " +
            			"( fk_taskID, observerScore, threshold, dT_Gathered) Values ( '" +
            			task.getTaskID() + "', '" +
            			observer.getObserverScore() + "', '" +
            			observer.getThreshold() + "', '" +
            			DateTime + "')"; 
            	try ( Connection conn = ds.getConnection();
            		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "AddObserver() returned " + rv );
            		} catch ( SQLException e ) {
            		    e.printStackTrace();
            		    System.exit( 0 );
            		}
            }

            /**
             * Update a Observer within the Database
             * 
             * @param Observer
             */
                public void UpdateObserver(Observer observer) {
                	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(observer.getDTGathered());
                	String query1 = "UPDATE observer " +
                			"SET observerScore = '" + observer.getObserverScore() + 
                			"', threshold = '" + observer.getThreshold() + 
                			"', dT_Gathered = '" + DateTime + 
                			"' WHERE observerID = '" + observer.getObserverID() + "'";
                	try ( Connection conn = ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
                		    int rv = stmt.executeUpdate( query1 );
                		    System.out.println( "UpdateObserver() returned " + rv );
                		} catch ( SQLException e ) {
                		    e.printStackTrace();
                		    System.exit( 0 );
                		}
                	
                }
                
                /**
                 * Delete a Observer within the Database
                 * @param int
                 */
                public void DeleteObserver(int observerID) {
                	String query1 = "DELETE FROM observer WHERE observerID = '" + observerID + "'";
                	try ( Connection conn = ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
                		    int rv = stmt.executeUpdate( query1 );
                		    System.out.println( "DeleteObserver() returned " + rv );
                		} catch ( SQLException e ) {
                		    e.printStackTrace();
                		    System.exit( 0 );
                		}
                	
                }    	
                
                /**
                 * Select a Observer within the database using the observerID
                 * @param int
                 * @return Observer
                 */
                public Observer SelectObserver(int observerID) {
                	Observer observer1 = new Observer();
                	String query1 = "SELECT * FROM observer WHERE observerID = '" + observerID + "'";
                	try ( Connection conn = ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
                		    ResultSet rs = stmt.executeQuery( query1 );
                		    observer1.setObserverID(rs.getInt("observerID"));
                		    observer1.setObserverScore(rs.getInt("observerScore"));
                		    observer1.setThreshold(rs.getInt("threshold"));
                		    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(rs.getString("dT_Gathered"));
                		    observer1.setDTGathered(date1);
                		    System.out.println( "SelectObserver() returned " + rs );
                		} catch ( SQLException e ) {
                			e.printStackTrace();
                		    System.exit( 0 );
                		}
                		  catch ( ParseException p ) {
                			p.printStackTrace();
                			System.exit( 0 );
                		}
                	return observer1;
                }
            	
                /**
                 * Mainly used for JUNIT testing, deletes the Observer table at the beginning of testing to remove all test data.
                 * 
                 */
                public void DeleteAllObservers(){
                	String query1 = "DROP TABLE IF EXISTS 'observer'";
                	try ( Connection conn = this.ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "DeleteAllObservers() returned " + rv );
                	} catch ( SQLException e ) {
            			e.printStackTrace();
            		    System.exit( 0 );
                	}
                	
                }
}
