/**
 * Class that contains information on User_Account created by 
 * the user through Account Creation
 */
package AttentionAssistant;

public class Parent_Account{

	// Primary Key for Parent Account
	private int parentID;
	// username for Parent Account
	private String username;
	// password for Parent Account
	private String password;
	
	//Instantiating empty Parent_Account Object
	public Parent_Account() {
		this.parentID= 0;
		this.username= "";
		this.password= "";
	}
	
	/**
	 * Create a class Parent_Account with a specified
	 * parentID, username, password
	 * @param int, String, String
	 */
	public Parent_Account(int parentID, String username, String password) {
		this.parentID= parentID;
		this.username= username;
		this.password= password;
	}
	
	//Copy constructor for Parent_Account
	public Parent_Account(Parent_Account parent) {
		this.parentID= parent.parentID;
		this.username= parent.username;
		this.password= parent.password;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get Parent
	 * @return intID
	 */
	public int getParentID() {
		return this.parentID;
	}
	
	/**
	 * set ParentID
	 * @param int
	 */
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
	/**
	 * Get username
	 * @return String
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * set username
	 * @param String
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get password
	 * @return String
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * set password
	 * @param String
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	 /** 
	   * Display Parent_Account
	   * @return String
	   */
	@Override
	public String toString() {
	 	String ParentString= new String();
	 	ParentString = "Parent ID= " + this.parentID +
	 			" Username= " + this.username +
	 			" Password= " + this.password; 
	 	return ParentString;	 	
	 }

	
	
	
	
	
}