package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_Parent_Account {

	Parent_Account defaultParent;
	Parent_Account nonDefaultParent;
	Parent_Account copyParent;
	
	@BeforeEach
	void setup() {
		int testParentID= 999;
		String testParentUsername= "TestParentUser123";
		String testParentPassword= "TestParentPass123";
		
		defaultParent= new Parent_Account();
		nonDefaultParent= new Parent_Account(testParentID, testParentUsername, testParentPassword);
		copyParent= new Parent_Account(nonDefaultParent);
	}

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> Default Constructor")
    void ParentDefaultConstructor() {
    
        /**
         *  Make sure the Parent_Account ParentID is 0 for the default constructor
         */
        assertEquals(0, defaultParent.getParentID(), 
        "Default constructor Parent_Account.ParentID should be 0. Returned: " + Integer.toString(defaultParent.getParentID()));

        /**
         *  Make sure the Parent_Account username is \"\" for the default constructor
         */
        assertEquals("", defaultParent.getUsername(), 
        "Default constructor Parent_Account.username should be \"\". Returned: " + defaultParent.getUsername());

        /**
         *  Make sure the Parent_Account password is \"\" for the default constructor
         */
        assertEquals("", defaultParent.getPassword(), 
        "Default constructor Parent_Account.password should be \"\". Returned: " + defaultParent.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> Parameter Constructor")
    void ParentParameterConstructor() {
    
        /**
         *  Make sure the Parent_Account ParentID is 999 for the Parameter constructor
         */
        assertEquals(999, nonDefaultParent.getParentID(), 
        "Parameter constructor Parent_Account.ParentID should be 999. Returned: " + Integer.toString(nonDefaultParent.getParentID()));

        /**
         *  Make sure the Parent_Account username is \"TestParentUser123\" for the Parameter constructor
         */
        assertEquals("TestParentUser123", nonDefaultParent.getUsername(), 
        "Parameter constructor Parent_Account.username should be \"TestParentUser123\". Returned: " + nonDefaultParent.getUsername());

        /**
         *  Make sure the Parent_Account password is \"TestParentPass123\" for the Parameter constructor
         */
        assertEquals("TestParentPass123", nonDefaultParent.getPassword(), 
        "Parameter constructor Parent_Account.password should be \"TestParentPass123\". Returned: " + nonDefaultParent.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> Copy Constructor")
    void ParentCopyConstructor() {
    
        /**
         *  Make sure the Parent_Account ParentID is 999 for the Copy constructor
         */
        assertEquals(999, copyParent.getParentID(), 
        "Copy constructor Parent_Account.ParentID should be 999. Returned: " + Integer.toString(copyParent.getParentID()));

        /**
         *  Make sure the Parent_Account username is \"TestParentUser123\" for the Copy constructor
         */
        assertEquals("TestParentUser123", copyParent.getUsername(), 
        "Copy constructor Parent_Account.username should be \"TestParentUser123\". Returned: " + copyParent.getUsername());

        /**
         *  Make sure the Parent_Account password is \"TestPass123\" for the Copy constructor
         */
        assertEquals("TestParentPass123", copyParent.getPassword(), 
        "Copy constructor Parent_Account.password should be \"TestParentPass123\". Returned: " + copyParent.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> SetUserID")
    void ParentSetUserID() {
    	
    	copyParent.setParentID(998);
    	assertEquals(998, copyParent.getParentID(), "copyParent userID should be set to 998 but instead returned: " + Integer.toString(copyParent.getParentID()));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> SetUsername")
    void ParentSetUsername() {
    	
    	copyParent.setUsername("CopyParentUser123");
    	assertEquals("CopyParentUser123", copyParent.getUsername(), "copyParent username should be set to \"CopyParentUser123\" but instead returned: " + copyParent.getUsername());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> SetPassword")
    void ParentSetPassword() {
    	
    	copyParent.setPassword("CopyParentPass123");
    	assertEquals("CopyParentPass123", copyParent.getPassword(), "copyParent password should be set to \"CopyParentPass123\" but instead returned: " + copyParent.getPassword());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Parent_Account> toString")
    void ParentToString() {
        String String1 = "Parent ID= 999 Username= TestParentUser123 Password= TestParentPass123";
        
        assertEquals(String1, nonDefaultParent.toString(), "String1 should be set to \"Parent ID= 999 Username= TestParentUser123 Password= TestParentPass123\" but instead returned: " + nonDefaultParent.toString());
    }    	

}