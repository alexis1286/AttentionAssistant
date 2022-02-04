package AttentionAssistant;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Test_Observer {
	/**
	 * Objects used in test
	 */
	Observer defaultObserver;
	Observer nonDefaultObserver;
	Observer copyObserver;
	
	@BeforeEach
	void setup() {
	int testObserver_ID= 999;
	int testObserverScore= 100;
	int testThreshold= 100;
	Date testDT_Gathered= new Date(1220227200L * 1000);
	
	defaultObserver= new Observer();
	nonDefaultObserver= new Observer(testObserver_ID, testObserverScore, testThreshold, testDT_Gathered);
	copyObserver= new Observer(nonDefaultObserver);
	}
	
    @Test
    @DisplayName("<Observer> Default Constructor")
    void ObserverDefaultConstructor() {

        /**
         *  Make sure the Observer ObserverID is 0 for the default constructor
         */
        assertEquals(0, defaultObserver.getObserverID(), 
        "Default constructor Observer ObserverID should be 0. Returned: " + Integer.toString(defaultObserver.getObserverID()));
        
        /**
         *  Make sure the Observer ObserverScore is 0 for the default constructor
         */
        assertEquals(0 , defaultObserver.getObserverScore(), 
        "Default constructor task.description should be empty. Returned: " + defaultObserver.getThreshold());
        
        /**
         *  Make sure the Observer Threshold is set to 0 for the default constructor
         */
        assertEquals(0 , defaultObserver.getThreshold(), 
        "Default constructor task.observable should be false. Returned: " + defaultObserver.getThreshold());
 
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> Parameter Constructor")
    void ObserverParameterConstructor() {

        /**
         *  Make sure the Observer ObserverID is 999 for the Parameter constructor
         */
        assertEquals(999, nonDefaultObserver.getObserverID(), 
        "Parameter constructor Observer.ObserverID should be 999. Returned: " + Integer.toString(nonDefaultObserver.getObserverID()));

        /**
         *  Make sure the Observer ObserverScore is 100 for the Parameter constructor
         */
        assertEquals(100 , nonDefaultObserver.getObserverScore(), 
        "Parameter constructor Observer.ObserverScore should be \"This is a test description\". Returned: " + nonDefaultObserver.getObserverScore());
        
        /**
         *  Make sure the Observer Threshold is set to 100 for the Parameter constructor
         */
        assertEquals(100 , nonDefaultObserver.getThreshold(), 
        "Parameter constructor Observer.Threshold should be 100. Returned: " + nonDefaultObserver.getThreshold());

        /**
         *  Make sure the Observer dT_Gathered is set to Date(1220227200L * 1000) for the Parameter constructor
         */
        assertEquals(new Date(1220227200L * 1000) , nonDefaultObserver.getDTGathered(), 
        "Parameter constructor Observer.dt_Gathered should be Sun Aug 31 20:00:00 EDT 2008 Returned: " + nonDefaultObserver.getDTGathered());

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> Copy Constructor")
    void ObserverCopyConstructor() {

        /**
         *  Make sure the Observer ObserverID is 999 for the Copy constructor
         */
        assertEquals(999, copyObserver.getObserverID(), 
        "Copy constructor Observer.ObserverID should be 999. Returned: " + Integer.toString(copyObserver.getObserverID()));

        /**
         *  Make sure the Observer ObserverScore is 100 for the Copy constructor
         */
        assertEquals(100 , copyObserver.getObserverScore(), 
        "Copy constructor Observer.ObserverScore should be \"This is a test description\". Returned: " + copyObserver.getObserverScore());
        
        /**
         *  Make sure the Observer Threshold is set to 100 for the Copy constructor
         */
        assertEquals(100 , copyObserver.getThreshold(), 
        "Copy constructor Observer.Threshold should be 100. Returned: " + copyObserver.getThreshold());

        /**
         *  Make sure the Observer dT_Gathered is set to Date(1220227200L * 1000) for the Copy constructor
         */
        assertEquals(new Date(1220227200L * 1000) , copyObserver.getDTGathered(), 
        "Copy constructor Observer.dt_Gathered should be Sun Aug 31 20:00:00 EDT 2008 Returned: " + copyObserver.getDTGathered());

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> SetObserver_ID")
    void observerSetObserverID() {
        copyObserver.setObserverID(998);
    	assertEquals(998, copyObserver.getObserverID(), 
    	"Copy constructor Observer.ObserverID should be 998. Returned: " + Integer.toString(copyObserver.getObserverID()));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> SetObserverScore")
    void observerSetObserverScore() {
        copyObserver.setObserverScore(99);
    	assertEquals(99, copyObserver.getObserverScore(), 
    	"Copy constructor Observer.ObserverScore should be 99. Returned: " + Integer.toString(copyObserver.getObserverScore()));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> SetObserverThreshold")
    void observerSetThreshold() {
        copyObserver.setThreshold(99);
    	assertEquals(99, copyObserver.getThreshold(), 
    	"Copy constructor Observer.Threshold should be 99. Returned: " + Integer.toString(copyObserver.getThreshold()));
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> SetObserverDTGathered")
    void observerSetDTGathered() {
        copyObserver.setDTGathered(new Date(1220227202L * 1000));
    	assertEquals(new Date(1220227202L * 1000), copyObserver.getDTGathered(), 
    	"Copy constructor Observer.dT_Gathered should be Sun Aug 31 20:00:02 EDT 2008. Returned: " + copyObserver.getDTGathered());
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    @Test
    @DisplayName("<Observer> toString")
    void taskToString() {
        String String1 = "Observer ID= 999 Observer Score= 100 Threshold= 100 Date Time Gathered= Sun Aug 31 20:00:00 EDT 2008";
        
        assertEquals(String1, nonDefaultObserver.toString(), "String1 should be set to Observer ID= 999 Observer Score= 100 Threshold= 100 Date Time Gathered= Sun Aug 31 20:00:00 EDT 2008 but instead returned: " + nonDefaultObserver.toString());
    }    	

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

}