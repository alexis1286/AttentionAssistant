package AttentionAssistant;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

public class DataBase {
	
	private SQLiteDataSource ds;
	SQLiteConfig sqlCon;

	
	
	/**
	 * Instantiating empty Task object
	 * @author jmitchel2
	 */
	public DataBase() {
		this.ds= new SQLiteDataSource();
		this.sqlCon = new SQLiteConfig();
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
    	String queryTask = "CREATE TABLE IF NOT EXISTS task ( " +
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
   			 "fk_userID INTEGER, " +
  			 "media_ID_Tag TEXT, " +
   			 "flagged BOOLEAN, " +
   			 "dT_Executed DATE, " +
			 "CONSTRAINT fk_userID FOREIGN KEY (\"fk_userID\") REFERENCES \"user\"(\"userID\") ON DELETE CASCADE)";

    	/**
    	 * Set up for Table Observer
    	 */
    	String queryObserver = "CREATE TABLE IF NOT EXISTS observer ( " +
   			 "observerID INTEGER PRIMARY KEY, " +
   			 "fk_taskID INTEGER, " +
   			 "observerScore INTEGER, " +
   			 "threshold INTEGER, " +
   			 "dT_Gathered DATE, " +
   			 "CONSTRAINT fk_taskID FOREIGN KEY (\"fk_taskID\") REFERENCES \"task\"(\"taskID\") ON DELETE CASCADE)";

        /**
         * Set up for Table Settings
         */
    	String querySettings = "CREATE TABLE IF NOT EXISTS settings ( " +
   			 "settingsID INTEGER PRIMARY KEY, " +
   			 "iconCircles INTEGER, " +
   			 "icons INTEGER, " +
   			 "opacityCircles INTEGER, " + 
   			 "opacityIcons INTEGER, " +
   			 "isCollapsed BOOLEAN, " +
   			 "xCoord INTEGER, " +
   			 "yCoord INTEGER, " +
   			 "isVertical BOOLEAN, " +
   			 "iconSize INTEGER, " +
   			 "timerIsVisible BOOLEAN, " +
   			 "pmIsVisible BOOLEAN, " +
   			 "ftsIsVisible BOOLEAN, " +
   			 "htbIsVisible BOOLEAN, " +
   			 "ntbIsVisible BOOLEAN, " +
   			 "progReportIsVisible BOOLEAN, " +
   			 "avatarIsActive BOOLEAN, " +
   			 "textIsActive BOOLEAN, " +
   			 "audioIsActive BOOLEAN, " +
   			 "avatarFilePath TEXT, " +
   			 "audioFilePath TEXT, " +
   			 "alwaysOnScreen BOOLEAN, " +
   			 "avatarSize INTEGER, " +
   			 "pomodoroIsActive BOOLEAN, " +
   			 "workPeriod INTEGER, " +
   			 "breakPeriod INTEGER, " +
   			 "timeShowing BOOLEAN, " +
   			 "ftsIsActive BOOLEAN, " +
   			 "ntbIsActive BOOLEAN, " +
   			 "isAutoLinked BOOLEAN, " +
   			 "htbIsActive BOOLEAN)";
    	
    	/**
    	 * Set up for Table user
    	 */
    	String queryUser = "CREATE TABLE IF NOT EXISTS user ( " +
      		 "userID INTEGER PRIMARY KEY, " +
   			 "username TEXT, " +
   			 "password TEXT)";

    	/**
    	 * Set up for Table parent
    	 */
    	String queryParent = "CREATE TABLE IF NOT EXISTS parent ( " +
      		 "parentID INTEGER PRIMARY KEY, " +
   			 "username TEXT, " +
   			 "password TEXT)";
        
        /**
         * Set up for Table LinkedAccounts
         */
    	String queryLinkedAccounts = "CREATE TABLE IF NOT EXISTS linked_Accounts ( " +
   			 "linkedAccountID INTEGER PRIMARY KEY, " +
   			 "fk_ParentID INTEGER, " +
   			 "fk_UserID INTEGER, " +
			 "CONSTRAINT fk_ParentID FOREIGN KEY (\"fk_ParentID\") REFERENCES \"parent\"(\"parentID\") ON DELETE CASCADE, " +
   			 "CONSTRAINT fk_UserID FOREIGN KEY (\"fk_UserID\") REFERENCES \"user\"(\"userID\") ON DELETE CASCADE)";


	try (Connection conn = this.ds.getConnection();
   			Statement stmt = conn.createStatement(); ){
   		int rv1 = stmt.executeUpdate(queryUser);
   		System.out.println( "CreateUserTable() returned " + rv1 );
   		int rv2 = stmt.executeUpdate(queryParent);
   		System.out.println( "CreateParentTable() returned " + rv2 );
		int rv3 = stmt.executeUpdate(queryTask);
   		System.out.println( "CreateTaskTable() returned " + rv3 );
   		int rv4 = stmt.executeUpdate(queryHappyThoughtButton);
   		System.out.println( "CreateHTBTable() returned " + rv4 );
   		int rv5 = stmt.executeUpdate(queryObserver);
   		System.out.println( "CreateObserverTable() returned " + rv5 );
   		int rv6 = stmt.executeUpdate(querySettings);
   		System.out.println( "CreateSettingsTable() returned " + rv6 );
   		int rv7 = stmt.executeUpdate(queryLinkedAccounts);
   		System.out.println( "CreateLinkedAccountsTable() returned " + rv7 );

       } catch ( SQLException e ) {
           e.printStackTrace();
           System.exit( 0 );
       }



    }
    /**
    ******* START OF USER_ACCOUNT CRUD *******
    * @author jmitchel2
    */
    /**
     * Add a new User to the database.
     * @param User_Account
     */
    public void AddUser_Account(User_Account user) {
    	sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
    	String query1 = "INSERT INTO user " +
    			"( username, password ) Values ( '" +
    			user.getUsername() + "', '" +
    			user.getPassword() + "')";
    	try ( Connection conn = ds.getConnection();
    		    Statement stmt = conn.createStatement(); ) {
    		    int rv = stmt.executeUpdate( query1 );
    		    System.out.println( "AddUser_Account() returned " + rv );
    		} catch ( SQLException e ) {
    		    e.printStackTrace();
    		    System.exit( 0 );
    		}
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
    }

    /**
     * Update a User_Account within the Database
     * @param User_Account
     */
	public void UpdateUser_Account(User_Account user) {
    	sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
		String query1 = "UPDATE user " +
				"SET username = '" + user.getUsername() +
        		"', password = '" + user.getPassword() +
        		"' WHERE userID = '" + user.getUserID() + "'";
        try ( Connection conn = ds.getConnection();
        	Statement stmt = conn.createStatement(); ) {
        	int rv = stmt.executeUpdate( query1 );
        	System.out.println( "UpdateUser_Account() returned " + rv );
        	} catch ( SQLException e ) {
        	e.printStackTrace();
        	System.exit( 0 );
        	}
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);

	}

        /**
         * Delete a User_Account within the Database
         * @param int
         */
	public void DeleteUser_Account(int userID) {
    	sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
		String query1 = "DELETE FROM user WHERE userID = '" + userID + "'";
		try ( Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement(); ) {
			int rv = stmt.executeUpdate( query1 );
			System.out.println( "DeleteUser_Account() returned " + rv );
        	} catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
    }
            
       	/**
       	 * Select a user within the database using the userID
         * @param int
         * @return User_Account
         */
	public User_Account SelectUser_Account(int userID) {
    	sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
		User_Account user1 = new User_Account();
        String query1 = "SELECT * FROM user WHERE userID = '" + userID + "'";
        try ( Connection conn = ds.getConnection();
        	Statement stmt = conn.createStatement(); ) {
        	ResultSet rs = stmt.executeQuery( query1 );
            user1.setUserID(rs.getInt("userID"));
            user1.setUsername(rs.getString("username"));
            user1.setPassword(rs.getString("password"));
            System.out.println( "SelectUser_Account() returned " + rs );
        	} catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
          return user1;
        }
    /**
     * Mainly used for JUNIT testing, deletes the user table at the beginning of testing to remove all test data.
     * 
     */
    public void DeleteAllUser_Accounts(){
    	String query1 = "DROP TABLE IF EXISTS 'user'";
    	try ( Connection conn = this.ds.getConnection();
    		    Statement stmt = conn.createStatement(); ) {
		    int rv = stmt.executeUpdate( query1 );
		    System.out.println( "DeleteAllUser_Accounts() returned " + rv );
    	} catch ( SQLException e ) {
			e.printStackTrace();
		    System.exit( 0 );
    	}
    }

 
       /**
        ******* END OF USER_ACCOUNT CRUD *******
        */
            
       /**
        ******* START OF PARENT_ACCOUNT CRUD *******
        * @author jmitchel2
        */
            
       /**
        * Add a new Parent_Account to the database.
        * @param Parent_Account
        */
	public void AddParent_Account(Parent_Account parent) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
		String query1 = "INSERT INTO parent " +
             "( username, password ) Values ( '" +
             parent.getUsername() + "', '" +
             parent.getPassword() + "')";
        try ( Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {
             int rv = stmt.executeUpdate( query1 );
             System.out.println( "AddParent_Account() returned " + rv );
        	} catch ( SQLException e ) {
             e.printStackTrace();
             System.exit( 0 );
             }
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
        }

        /**
         * Update a Parent_Account within the Database
         * @param User_Account
         */
	public void UpdateParent_Account(Parent_Account parent) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
		String query1 = "UPDATE parent " +
				"SET username = '" + parent.getUsername() +
                "', password = '" + parent.getPassword() +
                "' WHERE parentID = '" + parent.getParentID() + "'";
        try ( Connection conn = ds.getConnection();
        		Statement stmt = conn.createStatement(); ) {
                int rv = stmt.executeUpdate( query1 );
                System.out.println( "UpdateParent_Account() returned " + rv );
        	} catch ( SQLException e ) {
                e.printStackTrace();
                System.exit( 0 );
        	}
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);

        }

		/**
         * Delete a Parent_Account within the Database
         * @param int
         */
	public void DeleteParent_Account(int parentID) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
       	String query1 = "DELETE FROM parent WHERE parentID = '" + parentID + "'";
        try ( Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement(); ) {
             int rv = stmt.executeUpdate( query1 );
             System.out.println( "DeleteParent_Account() returned " + rv );
             } catch ( SQLException e ) {
             e.printStackTrace();
             System.exit( 0 );
             }
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
        }
                     
        /**
         * Select a Parent_Account within the database using the parentID
         * @param int
         * @return Parent_Account
         */
	public Parent_Account SelectParent_Account(int parentID) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
		Parent_Account parent1 = new Parent_Account();
        String query1 = "SELECT * FROM parent WHERE parentID = '" + parentID + "'";
        try ( Connection conn = ds.getConnection();
        		Statement stmt = conn.createStatement(); ) {
                ResultSet rs = stmt.executeQuery( query1 );
                parent1.setParentID(rs.getInt("parentID"));
                parent1.setUsername(rs.getString("username"));
                parent1.setPassword(rs.getString("password"));
                System.out.println( "SelectParent_Account() returned " + rs );
                } catch ( SQLException e ) {
                     e.printStackTrace();
                     System.exit( 0 );
                }
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
         return parent1;
         }

	/**
     * Mainly used for JUNIT testing, deletes the parent table at the beginning of testing to remove all test data.
     * 
     */
    public void DeleteAllParent_Accounts(){
    	String query1 = "DROP TABLE IF EXISTS 'parent'";
    	try ( Connection conn = this.ds.getConnection();
    		    Statement stmt = conn.createStatement(); ) {
		    int rv = stmt.executeUpdate( query1 );
		    System.out.println( "DeleteAllParent_Accounts() returned " + rv );
    	} catch ( SQLException e ) {
			e.printStackTrace();
		    System.exit( 0 );
    	}
    }


   /**
   ******* END OF PARENT_ACCOUNT CRUD *******
   */
        
    /**
    ******* START OF TASK CRUD *******
    */
    /**
     * Add a new task to the database.
     * @param task
     */
    public void AddTask(Task task) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
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
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
    }
    
    /**
     * Update a task within the Database
     * @param task
     */
        public void UpdateTask(Task task) {
    		sqlCon.enforceForeignKeys(true);
            ds.setConfig(sqlCon);
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
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
        	
        }
        
        /**
         * Delete a task within the Database
         * @param int
         */
        public void DeleteTask(int taskid) {
    		sqlCon.enforceForeignKeys(true);
            ds.setConfig(sqlCon);
        	String query1 = "DELETE FROM task WHERE taskID = '" + taskid + "'";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    int rv = stmt.executeUpdate( query1 );
        		    System.out.println( "DeleteTask() returned " + rv );
        		} catch ( SQLException e ) {
        		    e.printStackTrace();
        		    System.exit( 0 );
        		}
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
        	
        }    	
        
        /**
         * Select a task within the database using the taskid
         * @param int
         * @return Task
         */
        public Task SelectTask(int taskid) {
    		sqlCon.enforceForeignKeys(true);
            ds.setConfig(sqlCon);
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
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
        	return task1;
        }
        
        /**
         * Grab all tasks within the Database
         * 
         * This will eventually have a parameter int to grab by user id
         * @return ArrayList<Task>
         */
        public ArrayList<Task> SelectAllTasks(){
    		sqlCon.enforceForeignKeys(true);
            ds.setConfig(sqlCon);
        	ArrayList<Task> tasksOnList = new ArrayList<Task>();
        	Task blankTask = new Task();
        	String query1 = "SELECT * FROM task ORDER BY observable DESC, priority DESC, dueDate ASC";
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
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
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
        public void AddHTB(Happy_Thought_Button hTB, User_Account user) throws Exception {
    		sqlCon.enforceForeignKeys(true);
            ds.setConfig(sqlCon);
        	String DateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(hTB.getDT_Executed());
        	String query1 = "INSERT INTO happy_thought_button " +
        			"(fk_userID, media_ID_Tag, flagged, dT_Executed) Values ( '" +
        			user.getUserID() + "', '" +
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
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
        }
        /**
         * Update a Happy_Thought_Button within the Database
         * 
         * @param Happy_Thought_Button
         */
            public void UpdateHTB(Happy_Thought_Button hTB) {
        		sqlCon.enforceForeignKeys(true);
                ds.setConfig(sqlCon);
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
        		sqlCon.enforceForeignKeys(false);
                ds.setConfig(sqlCon);
            	
            }   
            /**
             * Delete a Happy_Thought_Button within the Database
             *              * 
             * @param int
             */
            public void DeleteHTB(int hTBID) {
        		sqlCon.enforceForeignKeys(true);
                ds.setConfig(sqlCon);
            	String query1 = "DELETE FROM happy_thought_button WHERE hTBID = '" + hTBID + "'";
            	try ( Connection conn = ds.getConnection();
            		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "DeleteHTB() returned " + rv );
            		} catch ( SQLException e ) {
            		    e.printStackTrace();
            		    System.exit( 0 );
            		}
        		sqlCon.enforceForeignKeys(false);
                ds.setConfig(sqlCon);
            	
            }    	
            /**
             * Select a Happy_Thought_Button within the database using the hTBID
             *              * 
             * @param int
             * @return Happy_Thought_Button
             */
            public Happy_Thought_Button SelectHTB(int hTBID) {
        		sqlCon.enforceForeignKeys(true);
                ds.setConfig(sqlCon);
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
        		sqlCon.enforceForeignKeys(false);
                ds.setConfig(sqlCon);
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
             ******* START OF OBSERVER CRUD *******
             * @author jmitchel2, ehols001
             */
 
            
            /**
             * Add a new Observer to the database.
             * @param Observer, Task
             */
            public void AddObserver(Observer observer, Task task) {
            	sqlCon.enforceForeignKeys(true);
                ds.setConfig(sqlCon);
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
        		sqlCon.enforceForeignKeys(false);
                ds.setConfig(sqlCon);
            }

            /**
             * Update a Observer within the Database
             * 
             * @param Observer
             */
                public void UpdateObserver(Observer observer) {
            		sqlCon.enforceForeignKeys(true);
                    ds.setConfig(sqlCon);
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
            		sqlCon.enforceForeignKeys(false);
                    ds.setConfig(sqlCon);
                	
                }
                
                /**
                 * Delete a Observer within the Database
                 * @param int
                 */
                public void DeleteObserver(int observerID) {
            		sqlCon.enforceForeignKeys(true);
                    ds.setConfig(sqlCon);
                	String query1 = "DELETE FROM observer WHERE observerID = '" + observerID + "'";
                	try ( Connection conn = ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
                		    int rv = stmt.executeUpdate( query1 );
                		    System.out.println( "DeleteObserver() returned " + rv );
                		} catch ( SQLException e ) {
                		    e.printStackTrace();
                		    System.exit( 0 );
                		}
            		sqlCon.enforceForeignKeys(false);
                    ds.setConfig(sqlCon);
                	
                }    	
                
                /**
                 * Select a Observer within the database using the observerID
                 * @param int
                 * @return Observer
                 */
                public Observer SelectObserver(int observerID) {
            		sqlCon.enforceForeignKeys(true);
                    ds.setConfig(sqlCon);
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
            		sqlCon.enforceForeignKeys(false);
                    ds.setConfig(sqlCon);
                	return observer1;
                }
            	
                /**
                 * Mainly used for JUNIT testing, deletes the Observer table at the beginning of testing to remove all test data.
                 * 
                 */
                public void DeleteAllObservers(){
            		sqlCon.enforceForeignKeys(true);
                    ds.setConfig(sqlCon);
                	String query1 = "DROP TABLE IF EXISTS 'observer'";
                	try ( Connection conn = this.ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "DeleteAllObservers() returned " + rv );
                	} catch ( SQLException e ) {
            			e.printStackTrace();
            		    System.exit( 0 );
                	}
            		sqlCon.enforceForeignKeys(false);
                    ds.setConfig(sqlCon);
                	
                }

                /**
                 * Grab all observers within the Database
                 * 
                 * @return ArrayList<Observers>
                 */
                public ArrayList<Observer> SelectAllObservers(int task_ID){
            		sqlCon.enforceForeignKeys(true);
                    ds.setConfig(sqlCon);
                	ArrayList<Observer> ObserversOnList = new ArrayList<Observer>();
                	Observer blankObserver = new Observer();
                	String query1 = "SELECT * FROM observer WHERE fk_taskID='" + task_ID + "' ORDER BY dT_Gathered ASC";
                	try ( Connection conn = ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
                		    ResultSet rs = stmt.executeQuery( query1 );
                		    while (rs.next()){
                		    blankObserver = new Observer();
                		    blankObserver.setObserverID(rs.getInt("observerID"));
                		    blankObserver.setObserverScore(rs.getInt("observerScore"));
                		    blankObserver.setThreshold(rs.getInt("threshold"));
                		    Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("dT_Gathered"));
                		    blankObserver.setDTGathered(date1);
                		    ObserversOnList.add(blankObserver);
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
            		sqlCon.enforceForeignKeys(false);
                    ds.setConfig(sqlCon);
                	return ObserversOnList;
                }

                /**
                 ******* END OF OBSERVER CRUD *******
                 */

                 /**
                 ******* START OF SETTINGS CRUD *******
                 * @author jmitchel2
                 */

                /**
                 * Add a new Settings to the database.
                 * @param Settings
                 */
                public void AddSettings(Settings settings) {
            		sqlCon.enforceForeignKeys(true);
                    ds.setConfig(sqlCon);
                	String query1 = "INSERT INTO settings " +
                			"( iconCircles, icons, opacityCircles, opacityIcons, isCollapsed, xCoord, yCoord, isVertical, iconSize, timerIsVisible, pmIsVisible, ftsIsVisible, htbIsVisible, ntbIsVisible, progReportIsVisible, avatarIsActive, textIsActive, audioIsActive, avatarFilePath, audioFilePath, alwaysOnScreen, avatarSize, pomodoroIsActive, workPeriod, breakPeriod, timeShowing, ftsIsActive, ntbIsActive, isAutoLinked, htbIsActive) Values ( '" +
                			settings.getIconCircles().getRGB() + "', '" +
                			settings.getIcons().getRGB() + "', '" +
                			settings.getOpacityCircles() + "', '" +
                			settings.getOpacityIcons() + "', '" +
                			settings.getIsCollapsed() + "', '" +
                			settings.getXCoord() + "', '" +
                			settings.getYCoord() + "', '" +
                			settings.getIsVertical() + "', '" +
                			settings.getIconSize() + "', '" +
                			settings.getTimerIsVisible() + "', '" +
                			settings.getPmIsVisible() + "', '" +
                			settings.getFtsIsVisible() + "', '" +
                			settings.getHtbIsVisible() + "', '" +
                			settings.getNtbIsVisible() + "', '" +
                			settings.getProgReportIsVisible() + "', '" +
                			settings.getAvatarIsActive() + "', '" +
                			settings.getTextIsActive() + "', '" +
                			settings.getAudioIsActive() + "', '" +
                			settings.getAvatarFilePath() + "', '" +
                			settings.getAudioFilePath() + "', '" +
                			settings.getAlwaysOnScreen() + "', '" +
                			settings.getAvatarSize() + "', '" +
                			settings.getPomodoroIsActive() + "', '" +
                			settings.getWorkPeriod() + "', '" +
                			settings.getBreakPeriod() + "', '" +
                			settings.getTimeShowing() + "', '" +
                			settings.getFtsIsActive() + "', '" +
                			settings.getNtbIsActive() + "', '" +
                			settings.getIsAutoLinked() + "', '" +
                			settings.getHtbIsActive() +"')";
                	try ( Connection conn = ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
                		    int rv = stmt.executeUpdate( query1 );
                		    System.out.println( "AddSettings() returned " + rv );
                		} catch ( SQLException e ) {
                		    e.printStackTrace();
                		    System.exit( 0 );
                		}
            		sqlCon.enforceForeignKeys(false);
                    ds.setConfig(sqlCon);
                }

                /**
                 * Update a Settings within the Database
                 * 
                 * @param Settings
                 */
                    public void UpdateSettings(Settings settings) {
                		sqlCon.enforceForeignKeys(true);
                        ds.setConfig(sqlCon);
                    	String query1 = "UPDATE settings " +
                    			"SET iconCircles = '" + settings.getIconCircles().getRGB() + 
                    			"', icons = '" + settings.getIcons().getRGB() + 
                    			"', opacityCircles = '" + settings.getOpacityCircles() + 
                    			"', opacityIcons = '" + settings.getOpacityIcons() + 
                    			"', isCollapsed = '" + settings.getIsCollapsed() + 
                    			"', xCoord = '" + settings.getXCoord() + 
                    			"', yCoord = '" + settings.getYCoord() + 
                    			"', isVertical = '" + settings.getIsVertical() + 
                    			"', iconSize = '" + settings.getIconSize() + 
                    			"', timerIsVisible = '" + settings.getTimerIsVisible() + 
                    			"', pmIsVisible = '" + settings.getPmIsVisible() + 
                    			"', ftsIsVisible = '" + settings.getFtsIsVisible() + 
                    			"', htbIsVisible = '" + settings.getHtbIsVisible() + 
                    			"', ntbIsVisible = '" + settings.getNtbIsVisible() + 
                    			"', progReportIsVisible = '" + settings.getProgReportIsVisible() + 
                    			"', avatarIsActive = '" + settings.getAvatarIsActive() + 
                    			"', textIsActive = '" + settings.getTextIsActive() + 
                    			"', audioIsActive = '" + settings.getAudioIsActive() + 
                    			"', avatarFilePath = '" + settings.getAvatarFilePath() + 
                    			"', audioFilePath = '" + settings.getAudioFilePath() + 
                    			"', alwaysOnScreen = '" + settings.getAlwaysOnScreen() + 
                    			"', avatarSize = '" + settings.getAvatarSize() + 
                    			"', pomodoroIsActive = '" + settings.getPomodoroIsActive() + 
                    			"', workPeriod = '" + settings.getWorkPeriod() + 
                    			"', breakPeriod = '" + settings.getBreakPeriod() + 
                    			"', timeShowing = '" + settings.getTimeShowing() + 
                    			"', ftsIsActive = '" + settings.getFtsIsActive() + 
                    			"', ntbIsActive = '" + settings.getNtbIsActive() + 
                    			"', isAutoLinked = '" + settings.getIsAutoLinked() + 
                    			"', htbIsActive = '" + settings.getHtbIsActive() + 
                    			"' WHERE settingsID = '" + settings.getSettingsID() + "'";
                    	try ( Connection conn = ds.getConnection();
                    		    Statement stmt = conn.createStatement(); ) {
                    		    int rv = stmt.executeUpdate( query1 );
                    		    System.out.println( "UpdateSettings() returned " + rv );
                    		} catch ( SQLException e ) {
                    		    e.printStackTrace();
                    		    System.exit( 0 );
                    		}
                		sqlCon.enforceForeignKeys(false);
                        ds.setConfig(sqlCon);
                    	
                    }
                    
                    /**
                     * Delete a Settings within the Database
                     * @param int
                     */
                    public void DeleteSettings(int settingsID) {
                		sqlCon.enforceForeignKeys(true);
                        ds.setConfig(sqlCon);
                    	String query1 = "DELETE FROM settings WHERE settingsID = '" + settingsID + "'";
                    	try ( Connection conn = ds.getConnection();
                    		    Statement stmt = conn.createStatement(); ) {
                    		    int rv = stmt.executeUpdate( query1 );
                    		    System.out.println( "DeleteSettings() returned " + rv );
                    		} catch ( SQLException e ) {
                    		    e.printStackTrace();
                    		    System.exit( 0 );
                    		}
                		sqlCon.enforceForeignKeys(false);
                        ds.setConfig(sqlCon);
                    	
                    }    	

                
                    /**
                     * Select a Settings within the database using the settingsID
                     * @param int
                     * @return Settings
                     */
                    public Settings SelectSettings(int settingsID) {
                		sqlCon.enforceForeignKeys(true);
                        ds.setConfig(sqlCon);
                    	Settings settings1 = new Settings();
                    	String query1 = "SELECT * FROM settings WHERE settingsID = '" + settingsID + "'";
                    	try ( Connection conn = ds.getConnection();
                    		    Statement stmt = conn.createStatement(); ) {
                    		    ResultSet rs = stmt.executeQuery( query1 );
                    		    settings1.setSettingsID(rs.getInt("settingsID"));
                    		    settings1.setIconCircles(new Color(rs.getInt("iconCircles")));
                    		    settings1.setIcons(new Color(rs.getInt("icons")));
                    		    settings1.setOpacityCircles(rs.getInt("opacityCircles"));
                    		    settings1.setOpacityIcons(rs.getInt("opacityIcons"));
                    		    settings1.setIsCollapsed(Boolean.valueOf(rs.getString("isCollapsed")));
                    		    settings1.setXCoord(rs.getInt("xCoord"));
                    		    settings1.setYCoord(rs.getInt("yCoord"));
                    		    settings1.setIsVertical(Boolean.valueOf(rs.getString("isVertical")));
                    		    settings1.setIconSize(rs.getInt("iconSize"));
                    		    settings1.setTimerIsVisible(Boolean.valueOf(rs.getString("timerIsVisible")));
                    		    settings1.setPmIsVisible(Boolean.valueOf(rs.getString("pmIsVisible")));
                    		    settings1.setFtsIsVisible(Boolean.valueOf(rs.getString("ftsIsVisible")));
                    		    settings1.setHtbIsVisible(Boolean.valueOf(rs.getString("htbIsVisible")));
                    		    settings1.setNtbIsVisible(Boolean.valueOf(rs.getString("ntbIsVisible")));
                    		    settings1.setProgReportIsVisible(Boolean.valueOf(rs.getString("progReportIsVisible")));
                    		    settings1.setAvatarIsActive(Boolean.valueOf(rs.getString("avatarIsActive")));
                    		    settings1.setTextIsActive(Boolean.valueOf(rs.getString("textIsActive")));
                    		    settings1.setAudioIsActive(Boolean.valueOf(rs.getString("audioIsActive")));
                    		    settings1.setAvatarFilePath(rs.getString("avatarFilePath"));
                    		    settings1.setAudioFilePath(rs.getString("audioFilePath"));
                    		    settings1.setAlwaysOnScreen(Boolean.valueOf(rs.getString("alwaysOnScreen")));
                    		    settings1.setAvatarSize(rs.getInt("avatarSize"));
                    		    settings1.setPomodoroIsActive(Boolean.valueOf(rs.getString("pomodoroIsActive")));
                    		    settings1.setWorkPeriod(rs.getInt("workPeriod"));
                    		    settings1.setBreakPeriod(rs.getInt("breakPeriod"));
                    		    settings1.setTimeShowing(Boolean.valueOf(rs.getString("timeShowing")));
                    		    settings1.setFtsIsActive(Boolean.valueOf(rs.getString("ftsIsActive")));
                    		    settings1.setNtbIsActive(Boolean.valueOf(rs.getString("ntbIsActive")));
                    		    settings1.setIsAutoLinked(Boolean.valueOf(rs.getString("isAutoLinked")));
                    		    settings1.setHtbIsActive(Boolean.valueOf(rs.getString("htbIsActive")));                   		    
                    		    System.out.println( "SelectSettings() returned " + rs );
                    		} catch ( SQLException e ) {
                    			e.printStackTrace();
                    		    System.exit( 0 );
                    		}
                		sqlCon.enforceForeignKeys(false);
                        ds.setConfig(sqlCon);
                    	return settings1;
                    }

                
                /**
                 * Mainly used for JUNIT testing, deletes the Settings table at the beginning of testing to remove all test data.
                 */
                public void DeleteAllSettings(){
                	String query1 = "DROP TABLE IF EXISTS 'settings'";
                	try ( Connection conn = this.ds.getConnection();
                		    Statement stmt = conn.createStatement(); ) {
            		    int rv = stmt.executeUpdate( query1 );
            		    System.out.println( "DeleteAllSettings() returned " + rv );
                	} catch ( SQLException e ) {
            			e.printStackTrace();
            		    System.exit( 0 );
                	}
                	
                }

                
     /**
      ******* END OF SETTINGS CRUD *******
      */

     /**
      ******* START OF LINKING ACCOUNTS *******
      * @author jmitchel2
      */
     /**
      * Add a new Linked Account to the database.
      * @param Parent_Account, User_Account
      */
    public void AddLinked_Account(Parent_Account parent, User_Account user) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
         	String query1 = "INSERT INTO linked_Accounts " +
      			"( fk_ParentID, fk_UserID) Values ( '" +
       			parent.getParentID() + "', '" +
       			user.getUserID() + "')";
           	try ( Connection conn = ds.getConnection();
       		    Statement stmt = conn.createStatement(); ) {
       		    int rv = stmt.executeUpdate( query1 );
       		    System.out.println( "AddLinked_Account() returned " + rv );
           		} catch ( SQLException e ) {
           		    e.printStackTrace();
          		    System.exit( 0 );
          		}
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
            }
    /**
     * Delete a new Account the database.
     * @param Parent_Account, User_Account
     */
   public void DeleteLinked_Account(Parent_Account parent, User_Account user) {
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
        	String query1 = "DELETE FROM linked_Accounts WHERE fk_ParentID= '" + parent.getParentID() + "' AND fk_UserID= '" + user.getUserID() +"'";
        	try ( Connection conn = ds.getConnection();
        		    Statement stmt = conn.createStatement(); ) {
        		    int rv = stmt.executeUpdate( query1 );
        		    System.out.println( "DeleteLinked_Account() returned " + rv );
        		} catch ( SQLException e ) {
        		    e.printStackTrace();
        		    System.exit( 0 );
        		}
    		sqlCon.enforceForeignKeys(false);
            ds.setConfig(sqlCon);
           }
   
   /**
    * Returns all user accounts that are related to a parent account.
    * 
    * @param Parent_Account
    * @return ArrayList<User_Account>
    */
   
   public ArrayList<User_Account> Select_All_Users_Linked_Account(Parent_Account parent){
		sqlCon.enforceForeignKeys(true);
        ds.setConfig(sqlCon);
	   ArrayList<User_Account> userAccountList = new ArrayList<User_Account>();
	   User_Account blankUser= new User_Account();
	   String query1 = "SELECT fk_UserID FROM linked_Accounts WHERE fk_ParentID = '" + parent.getParentID() + "'";
   		try ( Connection conn = ds.getConnection();
		    Statement stmt = conn.createStatement(); ) {
		    ResultSet rs = stmt.executeQuery( query1 );
		    while (rs.next()){
    		blankUser = new User_Account();
    		blankUser = this.SelectUser_Account(rs.getInt("fk_UserID"));
    		userAccountList.add(blankUser);
		    }		    
   		}catch ( SQLException e ) {
			e.printStackTrace();
		    System.exit( 0 );
		}
		sqlCon.enforceForeignKeys(false);
        ds.setConfig(sqlCon);
	   return userAccountList;
   }

   /**
    * Mainly used for JUNIT testing, deletes the LinkedAccounts table at the beginning of testing to remove all test data.
    */ 
   public void DeleteAllLinkedAccounts() {
   	String query1 = "DROP TABLE IF EXISTS 'linked_Accounts'";
   	try ( Connection conn = this.ds.getConnection();
   		    Statement stmt = conn.createStatement(); ) {
		    int rv = stmt.executeUpdate( query1 );
		    System.out.println( "DeleteAllLinkedAccounts() returned " + rv );
   	} catch ( SQLException e ) {
			e.printStackTrace();
		    System.exit( 0 );
   	}
   	
	   
   }
   
}
