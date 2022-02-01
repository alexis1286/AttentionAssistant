package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_HTB {
	Happy_Thought_Button defaultHTB;
	Happy_Thought_Button nonDefaultHTB;
	Happy_Thought_Button copyHTB;
	
	
	@BeforeEach
	void setup() {
		int testHTBID = 999;
		String testMediaIDTag = "This is a test Media ID Tag";
		boolean testFlagged = true;

		defaultHTB= new Happy_Thought_Button();
		
		nonDefaultHTB = new Happy_Thought_Button(testHTBID, testMediaIDTag, testFlagged);
		
		copyHTB = new Happy_Thought_Button(nonDefaultHTB);

	}
	
    @Test
    @DisplayName("<Happy_Thought_Button> Default Constructor")
    void HappyThoughtButtonDefaultConstructor() {
        /**
         *  Make sure the Happy_Thought_Button hTBID is 0 for the default constructor
         */
        assertEquals(0, defaultHTB.getHTBID(), 
        "Default constructor HTB.HTB_ID should be 0. Returned: " + Integer.toString(defaultHTB.getHTBID()));

        /**
         *  Make sure the Happy_Thought_Button media_ID_Tag is "" for the default constructor
         */
        assertEquals("", defaultHTB.getMedia_ID_Tag(), 
        "Default constructor HTB.Media_ID_Tag should be . Returned: " + defaultHTB.getMedia_ID_Tag());

        /**
         *  Make sure the Happy_Thought_Button Flagged is false for the default constructor
         */
        assertEquals(false, defaultHTB.getFlagged(), 
        "Default constructor HTB.flagged should be false. Returned: " + defaultHTB.getFlagged());
    
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Happy_Thought_Button> Parameter Constructor")
    void HappyThoughtButtonParameterConstructor() {
        /**
         *  Make sure the Happy_Thought_Button hTBID is 999 for the Parameter constructor
         */
        assertEquals(999, nonDefaultHTB.getHTBID(), 
        "nonDefault constructor HTB.HTB_ID should be 999. Returned: " + Integer.toString(nonDefaultHTB.getHTBID()));

        /**
         *  Make sure the Happy_Thought_Button media_ID_Tag is "This is a test Media ID Tag" for the Parameter constructor
         */
        assertEquals("This is a test Media ID Tag", nonDefaultHTB.getMedia_ID_Tag(), 
        "nonDefault constructor HTB.Media_ID_Tag should be This is a test Media ID Tag. Returned: " + nonDefaultHTB.getMedia_ID_Tag());

        /**
         *  Make sure the Happy_Thought_Button Flagged is true for the Parameter constructor
         */
        assertEquals(true, nonDefaultHTB.getFlagged(), 
        "nonDefault constructor HTB.flagged should be false. Returned: " + nonDefaultHTB.getFlagged());
    
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Happy_Thought_Button> Copy Constructor")
    void HappyThoughtButtonCopyConstructor() {
        /**
         *  Make sure the Happy_Thought_Button hTBID is 999 for the copy constructor
         */
        assertEquals(999, copyHTB.getHTBID(), 
        "copy constructor HTB.HTB_ID should be 999. Returned: " + Integer.toString(copyHTB.getHTBID()));

        /**
         *  Make sure the Happy_Thought_Button media_ID_Tag is "This is a test Media ID Tag" for the copy constructor
         */
        assertEquals("This is a test Media ID Tag", copyHTB.getMedia_ID_Tag(), 
        "copy constructor HTB.Media_ID_Tag should be This is a test Media ID Tag. Returned: " + copyHTB.getMedia_ID_Tag());

        /**
         *  Make sure the Happy_Thought_Button Flagged is true for the copy constructor
         */
        assertEquals(true, copyHTB.getFlagged(), 
        "copy constructor HTB.flagged should be false. Returned: " + copyHTB.getFlagged());

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<HTB> SetHTBID")
    void HTBSetHTBID() {
    	
    	copyHTB.setHTBID(998);
    	assertEquals(998, copyHTB.getHTBID(), "copyHTB hTBID should be set to 998 but instead returned: " + Integer.toString(copyHTB.getHTBID()));
    }
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<HTB> SetHTBMedia_ID_Tag")
    void HTBSetMedia_ID_Tag() {
    	
    	copyHTB.setMedia_ID_Tag("I AM A COPY MEDIA_ID_TAG");
    	assertEquals("I AM A COPY MEDIA_ID_TAG", copyHTB.getMedia_ID_Tag(), "copyHTB media_ID_Tag should be set to \"I AM A COPY MEDIA_ID_TAG\" but instead returned: " + copyHTB.getMedia_ID_Tag());
    }
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<HTB> SetFlagged")
    void HTBSetFlagged() {
    	
    	copyHTB.setFlagged(false);
    	assertEquals(false, copyHTB.getFlagged(), "copyHTB flagged should be set to false but instead returned: " + Boolean.toString(copyHTB.getFlagged()));
    }
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<HTB> ToString")
    void HTBToString() {
    String String1 = "Happy_Thought_Button ID= 999 Media_ID_Tag= This is a test Media ID Tag Flagged= true";

    	assertEquals(String1, copyHTB.toString(), "copyHTB toString should be set to \"Happy_Thought_Button ID= 999 Media_ID_Tag= This is a test Media ID Tag Flagged= true\" but instead returned: " + copyHTB.toString());
    }
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

}