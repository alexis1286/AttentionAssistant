/**
 * Class that contains information on User_Account created by 
 * the user through Account Creation
 */
package AttentionAssistant;

public class User_Account{

	// Primary Key for User Account
	private int userID;
	// username for User Account
	private String username;
	// password for User Account
	private String password;
	
	//Instantiating empty User_Account Object
	public User_Account() {
		this.userID= 0;
		this.username= "";
		this.password= "";
	}
	
	/**
	 * Create a class User_Account with a specified
	 * userID, username, password
	 * @param int, String, String
	 */
	public User_Account(int userID, String username, String password) {
		this.userID= userID;
		this.username= username;
		this.password= password;
	}
	
	//Copy constructor for User_Account
	public User_Account(User_Account user) {
		this.userID= user.userID;
		this.username= user.username;
		this.password= user.password;
	}
	
	/**
	 * Start of Encapsulation
	 * 
	 * Get UserID
	 * @return int
	 */
	public int getUserID() {
		return this.userID;
	}
	
	/**
	 * set UserID
	 * @param int
	 */
	public void setUserID(int userID) {
		this.userID = userID;
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
	   * Display User_Account
	   * @return String
	   */
	@Override
	public String toString() {
	 	String UserString= new String();
	 	UserString = "User ID= " + this.userID +
	 			" Username= " + this.username +
	 			" Password= " + this.password; 
	 	return UserString;	 	
	 }

	
	
	
	
	
}