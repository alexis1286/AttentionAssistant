package AttentionAssistant;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
        String url = "jdbc:sqlite:C:/sqlite/db/Attention_Assistant.db" ;
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
    		    System.out.println( "1st executeUpdate() returned " + rv );
    		} catch ( SQLException e ) {
    		    e.printStackTrace();
    		    System.exit( 0 );
    		}



    }
    			
}
