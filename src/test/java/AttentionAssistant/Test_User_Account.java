package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_User_Account {

	User_Account defaultUser;
	User_Account nonDefaultUser;
	User_Account copyUser;
	
	@BeforeEach
	void setup() {
		int testUserID= 999;
		String testUserUsername= "TestUser123";
		String testUserPassword= "TestPass123";
		String testUserName= "TestName123";
		
		defaultUser= new User_Account();
		nonDefaultUser= new User_Account(testUserID, testUserUsername, testUserPassword, testUserName);
		copyUser= new User_Account(nonDefaultUser);
	}

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> Default Constructor")
    void UserDefaultConstructor() {
    
        /**
         *  Make sure the User_Account userID is 0 for the default constructor
         */
        assertEquals(0, defaultUser.getUserID(), 
        "Default constructor User_Account.UserID should be 0. Returned: " + Integer.toString(defaultUser.getUserID()));

        /**
         *  Make sure the User_Account username is \"\" for the default constructor
         */
        assertEquals("", defaultUser.getUsername(), 
        "Default constructor User_Account.username should be \"\". Returned: " + defaultUser.getUsername());

        /**
         *  Make sure the User_Account password is \"\" for the default constructor
         */
        assertEquals("", defaultUser.getPassword(), 
        "Default constructor User_Account.password should be \"\". Returned: " + defaultUser.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> Parameter Constructor")
    void UserParameterConstructor() {
    
        /**
         *  Make sure the User_Account userID is 999 for the Parameter constructor
         */
        assertEquals(999, nonDefaultUser.getUserID(), 
        "Parameter constructor User_Account.UserID should be 999. Returned: " + Integer.toString(nonDefaultUser.getUserID()));

        /**
         *  Make sure the User_Account username is \"TestUser123\" for the Parameter constructor
         */
        assertEquals("TestUser123", nonDefaultUser.getUsername(), 
        "Parameter constructor User_Account.username should be \"TestUser123\". Returned: " + nonDefaultUser.getUsername());

        /**
         *  Make sure the User_Account password is \"TestPass123\" for the Parameter constructor
         */
        assertEquals("TestPass123", nonDefaultUser.getPassword(), 
        "Parameter constructor User_Account.password should be \"TestPass123\". Returned: " + nonDefaultUser.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> Copy Constructor")
    void UserCopyConstructor() {
    
        /**
         *  Make sure the User_Account userID is 999 for the Copy constructor
         */
        assertEquals(999, copyUser.getUserID(), 
        "Copy constructor User_Account.UserID should be 999. Returned: " + Integer.toString(copyUser.getUserID()));

        /**
         *  Make sure the User_Account username is \"TestUser123\" for the Copy constructor
         */
        assertEquals("TestUser123", copyUser.getUsername(), 
        "Copy constructor User_Account.username should be \"TestUser123\". Returned: " + copyUser.getUsername());

        /**
         *  Make sure the User_Account password is \"TestPass123\" for the Copy constructor
         */
        assertEquals("TestPass123", copyUser.getPassword(), 
        "Copy constructor User_Account.password should be \"TestPass123\". Returned: " + copyUser.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> SetUserID")
    void UserSetUserID() {
    	
    	copyUser.setUserID(998);
    	assertEquals(998, copyUser.getUserID(), "copyUser userID should be set to 998 but instead returned: " + Integer.toString(copyUser.getUserID()));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> SetUsername")
    void UserSetUsername() {
    	
    	copyUser.setUsername("CopyUser123");
    	assertEquals("CopyUser123", copyUser.getUsername(), "copyUser username should be set to \"CopyUser123\" but instead returned: " + copyUser.getUsername());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> SetPassword")
    void UserSetPassword() {
    	
    	copyUser.setPassword("CopyPass123");
    	assertEquals("CopyPass123", copyUser.getPassword(), "copyUser password should be set to \"CopyPass123\" but instead returned: " + copyUser.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<User_Account> toString")
    void UserToString() {
        String String1 = "User ID= 999 Username= TestUser123 Password= TestPass123 Name= TestName123";
        
        assertEquals(String1, nonDefaultUser.toString(), "String1 should be set to \"User ID= 999 Username= TestUser123 Password= TestPass123 Name= TestName123\" but instead returned: " + nonDefaultUser.toString());
    }    	

}