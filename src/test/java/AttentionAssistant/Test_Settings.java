package AttentionAssistant;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.Color;

/**
 * Test File for the Settings functions
 * @author krchr
 */

public class Test_Settings {

	Color aa_purple = new Color(137,31,191);
	
	/**
	 * Objects used in test
	 */
	Settings defaultSettings;
	Settings nonDefaultSettings;
	Settings copySettings;
	
	@BeforeEach
	void setup() {
		Color testIconCircles = Color.RED; 
		Color testIcons = Color.YELLOW;
		int testOpacityCircles = 75; 
		int testOpacityIcons = 75; 
		boolean testIsCollapsed = true; 
		int testXCoord = 15;
		int testYCoord = 15; 
		boolean testIsVertical = false; 
		int testIconSize = 30; 
		boolean testTimerIsVisible = false; 
		boolean testPmIsVisible = false; 
		boolean testFtsIsVisible = false; 
		boolean testHtbIsVisible = false; 
		boolean testNtbIsVisible = false; 
		boolean testProgReportIsVisible = false; 
		boolean testAvatarIsActive = true; 
		boolean testTextIsActive = false; 
		boolean testAudioIsActive = true;
		String testAvatarFilePath = "images/avatar_cat1.png"; 
		String testAudioFilePath = "test";
		boolean testAlwaysOnScreen = true; 
		int testAvatarSize = 75; 
		boolean testPomodoroIsActive = false; 
		int testWorkPeriod = 33; 
		int testBreakPeriod = 23; 
		boolean testTimeShowing = false; 
		boolean testFtsIsActive = false; 
		boolean testNtbIsActive = false; 
		boolean testIsAutoLinked = false; 
		boolean testHtbIsActive = false; 
		
		defaultSettings = new Settings();
		
		nonDefaultSettings = new Settings(testIconCircles, testIcons, testOpacityCircles, testOpacityIcons, testIsCollapsed, testXCoord, 
										  testYCoord, testIsVertical, testIconSize, testTimerIsVisible, testPmIsVisible, testFtsIsVisible, 
										  testHtbIsVisible, testNtbIsVisible, testProgReportIsVisible, testAvatarIsActive, testTextIsActive, 
										  testAudioIsActive, testAvatarFilePath, testAudioFilePath, testAlwaysOnScreen, testAvatarSize, 
										  testPomodoroIsActive, testWorkPeriod, testBreakPeriod, testTimeShowing, testFtsIsActive, 
										  testNtbIsActive, testIsAutoLinked, testHtbIsActive);
		
		copySettings = new Settings(nonDefaultSettings);
		
	}
	
	@Test 
	@DisplayName("<Settings> Default Constructor")
	void SettingsDefaultConstructor() {
		/**
		 * Make sure the Settings iconCircles are set to aa_purple for the default constructor
		 */
		assertEquals(aa_purple, defaultSettings.getIconCircles(), "Default constructor Settings.iconCircles should be aa_purple. Returned: " 
				    + defaultSettings.getIconCircles());
		
		/**
		 * Make sure Settings icons are set to Color.white for the default constructor
		 */
		assertEquals(Color.white, defaultSettings.getIcons(), "Default constructor Settings.icons should be Color.white. Returned: "
					+ defaultSettings.getIcons()); 
		
		/**
		 * Make sure Settings OpacityCircles is set to 100 for the default constructor 
		 */
		assertEquals(100, defaultSettings.getOpacityCircles(), "Default constructor Settings.opacityCircles should be 100. Returned: "
					+ Integer.toString(defaultSettings.getOpacityCircles()));
		
		/**
		 * Make sure Settings OpacityIcons is set to 100 for the default constructor 
		 */
		assertEquals(100, defaultSettings.getOpacityIcons(), "Default constructor Settings.opacityIcons should be 100. Returned: "
					+ Integer.toString(defaultSettings.getOpacityIcons()));
		
		/**
		 * Make sure Settings isCollapsed is set to false for default constructor 
		 */
		assertEquals(false, defaultSettings.getIsCollapsed(), "Default constructor Settings.isCollapsed should be false. Returned: "
					+ defaultSettings.getIsCollapsed());
		
		/**
		 * Make sure Settings xCoord is set to 0 for default constructor
		 */
		assertEquals(0, defaultSettings.getXCoord(), "Default constructor Settings.xCoord should be 0. Returned: "
				    + Integer.toString(defaultSettings.getXCoord()));
		
		/**
		 * Make sure Settings yCoord is set to 0 for default constructor
		 */
		assertEquals(0, defaultSettings.getYCoord(), "Default constructor Settings.yCoord should be 0. Returned: "
				    + Integer.toString(defaultSettings.getYCoord()));
		
		/**
		 * Make sure Settings isVertical is set to true for default Constructor 
		 */
		assertEquals(true, defaultSettings.getIsVertical(), "Default constructor Settings.isVertical should be true. Returned: "
					+ defaultSettings.getIsVertical());
		
		/**
		 * Make sure Settings iconSize is set to 50 for default constructor
		 */
		assertEquals(50, defaultSettings.getIconSize(), "Default constructor Settings.iconSize should be 50. Returned: "
					+ Integer.toString(defaultSettings.getIconSize()));
		
		/**
		 * Make sure Settings timerIsVisible is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getTimerIsVisible(), "Default constructor Settings.timerIsVisible should be true. Returned: "
					+ defaultSettings.getTimerIsVisible());
		
		/**
		 * Make sure Settings pmIsVisible is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getPmIsVisible(), "Default constructor Settings.pmIsVisible should be true. Returned: "
					+ defaultSettings.getPmIsVisible());
		
		/**
		 * Make sure Settings ftsIsVisible is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getFtsIsVisible(), "Default constructor Settings.ftsIsVisible should be true. Returned: "
					+ defaultSettings.getFtsIsVisible());
		
		/**
		 * Make sure Settings htbIsVisible is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getHtbIsVisible(), "Default constructor Settings.htbIsVisible should be true. Returned: "
					+ defaultSettings.getHtbIsVisible());
		
		/**
		 * Make sure Settings ntbIsVisible is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getNtbIsVisible(), "Default constructor Settings.ntbIsVisible should be true. Returned: "
					+ defaultSettings.getNtbIsVisible());
		
		/**
		 * Make sure Settings progReportIsVisible is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getProgReportIsVisible(), "Default constructor Settings.progReportIsVisible should be true. Returned: "
					+ defaultSettings.getProgReportIsVisible());
		
		/**
		 * Make sure Settings avatarIsActive is set to false for default constructor 
		 */
		assertEquals(false, defaultSettings.getAvatarIsActive(), "Default constructor Settings.avatarIsActive should be false. Returned: "
					+ defaultSettings.getAvatarIsActive());
		
		/**
		 * Make sure Settings textIsActive is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getTextIsActive(), "Default constructor Settings.textIsActive should be true. Returned: "
					+ defaultSettings.getTextIsActive());
		/**
		 * Make sure Settings audioIsActive is set to false for default constructor 
		 */
		assertEquals(false, defaultSettings.getAudioIsActive(), "Default constructor Settings.audioIsActive should be false. Returned: "
					+ defaultSettings.getAudioIsActive());
		
		/**
		 * Make sure Settings avatarFilePath is set to "images/avatar_dino.png" for default constructor
		 */
		assertEquals("images/avatar_dino.png", defaultSettings.getAvatarFilePath(), "Default constructor Settings.avatarFilePath should be \"images/avatar_dino.png\". Returned: "
					+ defaultSettings.getAvatarFilePath()); 
		 
		/**
		 * Make sure Settings audioFilePath is set to "" for default constructor
		 */
		assertEquals("", defaultSettings.getAudioFilePath(), "Default constructor Settings.audioFilePath should be \"\". Returned: "
					+ defaultSettings.getAudioFilePath()); 
		
		/**
		 * Make sure alwaysOnScreen is set to false for default constructor
		 */
		assertEquals(false, defaultSettings.getAlwaysOnScreen(), "Default constructor Settings.alwaysOnScreen should be false. Returned: "
					+ defaultSettings.getAlwaysOnScreen());
		
		/**
		 * Make sure avatarSize is set to 100 for default constructor
		 */
		assertEquals(100, defaultSettings.getAvatarSize(), "Default constructor Settings.avatarSize should be 100. Returned: "
					+ Integer.toString(defaultSettings.getAvatarSize()));
		
		/**
		 * Make sure Settings pomodoroIsActive is set to true for default constructor 
		 */
		assertEquals(true, defaultSettings.getPomodoroIsActive(), "Default constructor Settings.pomodoroIsActive should be true. Returned: "
					+ defaultSettings.getPomodoroIsActive());
		
		/**
		 * Make sure Settings workPeriod is set to 45 for default constructor 
		 */
		assertEquals(45, defaultSettings.getWorkPeriod(), "Default constructor Settings.workPeriod should be 45. Returned: "
					+ Integer.toString(defaultSettings.getWorkPeriod()));
		
		/**
		 * Make sure Settings breakPeriod is set to 15 for default constructor 
		 */
		assertEquals(15, defaultSettings.getBreakPeriod(), "Default constructor Settings.breakPeriod should be 15. Returned: "
					+ Integer.toString(defaultSettings.getBreakPeriod()));
		
		/**
		 * Make sure Settings timeShowing is set to true for default constructor
		 */
		assertEquals(true, defaultSettings.getTimeShowing(), "Default constructor Settings.timeShowing should be true. Returned: "
					+ defaultSettings.getTimeShowing());
		
		/**
		 * Make sure Settings ftsIsActive is set to true for default constructor
		 */
		assertEquals(true, defaultSettings.getFtsIsActive(), "Default constructor Settings.ftsIsActive should be true. Returned: "
					+ defaultSettings.getFtsIsActive());
		
		/**
		 * Make sure Settings ntbIsActive is set to true for default constructor
		 */
		assertEquals(true, defaultSettings.getNtbIsActive(), "Default constructor Settings.ntbIsActive should be true. Returned: "
					+ defaultSettings.getNtbIsActive());
		
		/**
		 * Make sure Settings isAutoLinked is set to true for default constructor
		 */
		assertEquals(true, defaultSettings.getIsAutoLinked(), "Default constructor Settings.isAutoLinked should be true. Returned: "
					+ defaultSettings.getIsAutoLinked());
		
		/**
		 * Make sure Settings htbIsActive is set to true for default constructor
		 */
		assertEquals(true, defaultSettings.getHtbIsActive(), "Default constructor Settings.htbIsActive should be true. Returned: "
					+ defaultSettings.getHtbIsActive());
		
	}
	
	 /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> Parameter Constructor")
	void SettingsParameterConstructor() {
		/**
		 * Make sure the Settings iconCircles are set to Color.RED for the nonDefault constructor
		 */
		assertEquals(Color.RED, nonDefaultSettings.getIconCircles(), "nonDefault constructor Settings.iconCircles should be Color.Red. Returned: " 
				    + nonDefaultSettings.getIconCircles());
		
		/**
		 * Make sure Settings icons are set to Color.YELLOW for the nonDefault constructor
		 */
		assertEquals(Color.YELLOW, nonDefaultSettings.getIcons(), "nonDefault constructor Settings.icons should be Color.YELLOW. Returned: "
					+ nonDefaultSettings.getIcons()); 
		
		/**
		 * Make sure Settings OpacityCircles is set to 75 for the nonDefault constructor 
		 */
		assertEquals(75, nonDefaultSettings.getOpacityCircles(), "nonDefault constructor Settings.opacityCircles should be 75. Returned: "
					+ Integer.toString(nonDefaultSettings.getOpacityCircles()));
		
		/**
		 * Make sure Settings OpacityIcons is set to 75 for the nonDefault constructor 
		 */
		assertEquals(75, nonDefaultSettings.getOpacityIcons(), "nonDefault constructor Settings.opacityIcons should be 75. Returned: "
					+ Integer.toString(nonDefaultSettings.getOpacityIcons()));
		
		/**
		 * Make sure Settings isCollapsed is set to true for nonDefault constructor 
		 */
		assertEquals(true, nonDefaultSettings.getIsCollapsed(), "nonDefault constructor Settings.isCollapsed should be true. Returned: "
					+ nonDefaultSettings.getIsCollapsed());
		
		/**
		 * Make sure Settings xCoord is set to 15 for nonDefault constructor
		 */
		assertEquals(15, nonDefaultSettings.getXCoord(), "nonDefault constructor Settings.xCoord should be 15. Returned: "
				    + Integer.toString(nonDefaultSettings.getXCoord()));
		
		/**
		 * Make sure Settings yCoord is set to 15 for nonDefault constructor
		 */
		assertEquals(15, nonDefaultSettings.getYCoord(), "nonDefault constructor Settings.yCoord should be 15. Returned: "
				    + Integer.toString(nonDefaultSettings.getYCoord()));
		
		/**
		 * Make sure Settings isVertical is set to false for nonDefault Constructor 
		 */
		assertEquals(false, nonDefaultSettings.getIsVertical(), "nonDefault constructor Settings.isVertical should be false. Returned: "
					+ nonDefaultSettings.getIsVertical());
		
		/**
		 * Make sure Settings iconSize is set to 30 for nonDefault constructor
		 */
		assertEquals(30, nonDefaultSettings.getIconSize(), "nonDefault constructor Settings.iconSize should be 30. Returned: "
					+ Integer.toString(nonDefaultSettings.getIconSize()));
		
		/**
		 * Make sure Settings timerIsVisible is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getTimerIsVisible(), "nonDefault constructor Settings.timerIsVisible should be false. Returned: "
					+ nonDefaultSettings.getTimerIsVisible());
		
		/**
		 * Make sure Settings pmIsVisible is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getPmIsVisible(), "nonDefault constructor Settings.pmIsVisible should be false. Returned: "
					+ nonDefaultSettings.getPmIsVisible());
		
		/**
		 * Make sure Settings ftsIsVisible is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getFtsIsVisible(), "nonDefault constructor Settings.ftsIsVisible should be false. Returned: "
					+ nonDefaultSettings.getFtsIsVisible());
		
		/**
		 * Make sure Settings htbIsVisible is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getHtbIsVisible(), "nonDefault constructor Settings.htbIsVisible should be false. Returned: "
					+ nonDefaultSettings.getHtbIsVisible());
		
		/**
		 * Make sure Settings ntbIsVisible is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getNtbIsVisible(), "nonDefault constructor Settings.ntbIsVisible should be false. Returned: "
					+ nonDefaultSettings.getNtbIsVisible());
		
		/**
		 * Make sure Settings progReportIsVisible is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getProgReportIsVisible(), "nonDefault constructor Settings.progReportIsVisible should be false. Returned: "
					+ nonDefaultSettings.getProgReportIsVisible());
		
		/**
		 * Make sure Settings avatarIsActive is set to true for nonDefault constructor 
		 */
		assertEquals(true, nonDefaultSettings.getAvatarIsActive(), "nonDefault constructor Settings.avatarIsActive should be true. Returned: "
					+ nonDefaultSettings.getAvatarIsActive());
		
		/**
		 * Make sure Settings textIsActive is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getTextIsActive(), "nonDefault constructor Settings.textIsActive should be false. Returned: "
					+ nonDefaultSettings.getTextIsActive());
		/**
		 * Make sure Settings audioIsActive is set to true for nonDefault constructor 
		 */
		assertEquals(true, nonDefaultSettings.getAudioIsActive(), "nonDefault constructor Settings.audioIsActive should be true. Returned: "
					+ nonDefaultSettings.getAudioIsActive());
		
		/**
		 * Make sure Settings avatarFilePath is set to "images/avatar_cat1.png" for nonDefault constructor
		 */
		assertEquals("images/avatar_cat1.png", nonDefaultSettings.getAvatarFilePath(), "nonDefault constructor Settings.avatarFilePath should be \"images/avatar_cat1.png\". Returned: "
					+ nonDefaultSettings.getAvatarFilePath()); 
		 
		/**
		 * Make sure Settings audioFilePath is set to "test" for nonDefault constructor
		 */
		assertEquals("test", nonDefaultSettings.getAudioFilePath(), "nonDefault constructor Settings.audioFilePath should be \"test\". Returned: "
					+ nonDefaultSettings.getAudioFilePath()); 
		
		/**
		 * Make sure alwaysOnScreen is set to true for nonDefault constructor
		 */
		assertEquals(true, nonDefaultSettings.getAlwaysOnScreen(), "nonDefault constructor Settings.alwaysOnScreen should be true. Returned: "
					+ nonDefaultSettings.getAlwaysOnScreen());
		
		/**
		 * Make sure avatarSize is set to 75 for nonDefault constructor
		 */
		assertEquals(75, nonDefaultSettings.getAvatarSize(), "nonDefault constructor Settings.avatarSize should be 75. Returned: "
					+ Integer.toString(nonDefaultSettings.getAvatarSize()));
		
		/**
		 * Make sure Settings pomodoroIsActive is set to false for nonDefault constructor 
		 */
		assertEquals(false, nonDefaultSettings.getPomodoroIsActive(), "nonDefault constructor Settings.pomodoroIsActive should be false. Returned: "
					+ nonDefaultSettings.getPomodoroIsActive());
		
		/**
		 * Make sure Settings workPeriod is set to 33 for nonDefault constructor 
		 */
		assertEquals(33, nonDefaultSettings.getWorkPeriod(), "nonDefault constructor Settings.workPeriod should be 33. Returned: "
					+ Integer.toString(nonDefaultSettings.getWorkPeriod()));
		
		/**
		 * Make sure Settings breakPeriod is set to 23 for nonDefault constructor 
		 */
		assertEquals(23, nonDefaultSettings.getBreakPeriod(), "nonDefault constructor Settings.breakPeriod should be 23. Returned: "
					+ Integer.toString(nonDefaultSettings.getBreakPeriod()));
		
		/**
		 * Make sure Settings timeShowing is set to false for nonDefault constructor
		 */
		assertEquals(false, nonDefaultSettings.getTimeShowing(), "nonDefault constructor Settings.timeShowing should be trfalseue. Returned: "
					+ nonDefaultSettings.getTimeShowing());
		
		/**
		 * Make sure Settings ftsIsActive is set to false for nonDefault constructor
		 */
		assertEquals(false, nonDefaultSettings.getFtsIsActive(), "nonDefault constructor Settings.ftsIsActive should be false. Returned: "
					+ nonDefaultSettings.getFtsIsActive());
		
		/**
		 * Make sure Settings ntbIsActive is set to false for nonDefault constructor
		 */
		assertEquals(false, nonDefaultSettings.getNtbIsActive(), "nonDefault constructor Settings.ntbIsActive should be false. Returned: "
					+ nonDefaultSettings.getNtbIsActive());
		
		/**
		 * Make sure Settings isAutoLinked is set to false for nonDefault constructor
		 */
		assertEquals(false, nonDefaultSettings.getIsAutoLinked(), "nonDefault constructor Settings.isAutoLinked should be false. Returned: "
					+ nonDefaultSettings.getIsAutoLinked());
		
		/**
		 * Make sure Settings htbIsActive is set to false for nonDefault constructor
		 */
		assertEquals(false, nonDefaultSettings.getHtbIsActive(), "nonDefault constructor Settings.htbIsActive should be false. Returned: "
					+ nonDefaultSettings.getHtbIsActive());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> Copy Constructor")
	void SettingsCopyConstructor() {
		/**
		 * Make sure the Settings iconCircles are set to Color.RED for the copy constructor
		 */
		assertEquals(Color.RED, copySettings.getIconCircles(), "Copy constructor Settings.iconCircles should be Color.Red. Returned: " 
				    + copySettings.getIconCircles());
		
		/**
		 * Make sure Settings icons are set to Color.YELLOW for the copy constructor
		 */
		assertEquals(Color.YELLOW, copySettings.getIcons(), "Copy constructor Settings.icons should be Color.YELLOW. Returned: "
					+ copySettings.getIcons()); 
		
		/**
		 * Make sure Settings OpacityCircles is set to 75 for the copy constructor 
		 */
		assertEquals(75, copySettings.getOpacityCircles(), "Copy constructor Settings.opacityCircles should be 75. Returned: "
					+ Integer.toString(copySettings.getOpacityCircles()));
		
		/**
		 * Make sure Settings OpacityIcons is set to 75 for the copy constructor 
		 */
		assertEquals(75, copySettings.getOpacityIcons(), "Copy constructor Settings.opacityIcons should be 75. Returned: "
					+ Integer.toString(copySettings.getOpacityIcons()));
		
		/**
		 * Make sure Settings isCollapsed is set to true for copy constructor 
		 */
		assertEquals(true, copySettings.getIsCollapsed(), "Copy constructor Settings.isCollapsed should be true. Returned: "
					+ copySettings.getIsCollapsed());
		
		/**
		 * Make sure Settings xCoord is set to 15 for copy constructor
		 */
		assertEquals(15, copySettings.getXCoord(), "Copy constructor Settings.xCoord should be 15. Returned: "
				    + Integer.toString(copySettings.getXCoord()));
		
		/**
		 * Make sure Settings yCoord is set to 15 for copy constructor
		 */
		assertEquals(15, copySettings.getYCoord(), "Copy constructor Settings.yCoord should be 15. Returned: "
				    + Integer.toString(copySettings.getYCoord()));
		
		/**
		 * Make sure Settings isVertical is set to false for copy Constructor 
		 */
		assertEquals(false, copySettings.getIsVertical(), "Copy constructor Settings.isVertical should be false. Returned: "
					+ copySettings.getIsVertical());
		
		/**
		 * Make sure Settings iconSize is set to 30 for copy constructor
		 */
		assertEquals(30, copySettings.getIconSize(), "Copy constructor Settings.iconSize should be 30. Returned: "
					+ Integer.toString(copySettings.getIconSize()));
	
		/**
		 * Make sure Settings timerIsVisible is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getTimerIsVisible(), "Copy constructor Settings.timerIsVisible should be false. Returned: "
					+ copySettings.getTimerIsVisible());
		
		/**
		 * Make sure Settings pmIsVisible is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getPmIsVisible(), "Copy constructor Settings.pmIsVisible should be false. Returned: "
					+ copySettings.getPmIsVisible());
		
		/**
		 * Make sure Settings ftsIsVisible is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getFtsIsVisible(), "Copy constructor Settings.ftsIsVisible should be false. Returned: "
					+ copySettings.getFtsIsVisible());
		
		/**
		 * Make sure Settings htbIsVisible is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getHtbIsVisible(), "Copy constructor Settings.htbIsVisible should be false. Returned: "
					+ copySettings.getHtbIsVisible());
		
		/**
		 * Make sure Settings ntbIsVisible is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getNtbIsVisible(), "Copy constructor Settings.ntbIsVisible should be false. Returned: "
					+ copySettings.getNtbIsVisible());
		
		/**
		 * Make sure Settings progReportIsVisible is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getProgReportIsVisible(), "Copy constructor Settings.progReportIsVisible should be false. Returned: "
					+ copySettings.getProgReportIsVisible());
		
		/**
		 * Make sure Settings avatarIsActive is set to true for copy constructor 
		 */
		assertEquals(true, copySettings.getAvatarIsActive(), "Copy constructor Settings.avatarIsActive should be true. Returned: "
					+ copySettings.getAvatarIsActive());
		
		/**
		 * Make sure Settings textIsActive is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getTextIsActive(), "Copy constructor Settings.textIsActive should be false. Returned: "
					+ copySettings.getTextIsActive());
		/**
		 * Make sure Settings audioIsActive is set to true for copy constructor 
		 */
		assertEquals(true, copySettings.getAudioIsActive(), "Copy constructor Settings.audioIsActive should be true. Returned: "
					+ copySettings.getAudioIsActive());
		
		/**
		 * Make sure Settings avatarFilePath is set to "images/avatar_cat1.png" for copy constructor
		 */
		assertEquals("images/avatar_cat1.png", copySettings.getAvatarFilePath(), "Copy constructor Settings.avatarFilePath should be \"images/avatar_cat1.png\". Returned: "
					+ copySettings.getAvatarFilePath()); 
		 
		/**
		 * Make sure Settings audioFilePath is set to "test" for copy constructor
		 */
		assertEquals("test", copySettings.getAudioFilePath(), "Copy constructor Settings.audioFilePath should be \"test\". Returned: "
					+ copySettings.getAudioFilePath()); 
		
		/**
		 * Make sure alwaysOnScreen is set to true for copy constructor
		 */
		assertEquals(true, copySettings.getAlwaysOnScreen(), "Copy constructor Settings.alwaysOnScreen should be true. Returned: "
					+ copySettings.getAlwaysOnScreen());
		
		/**
		 * Make sure avatarSize is set to 75 for copy constructor
		 */
		assertEquals(75, copySettings.getAvatarSize(), "Copy constructor Settings.avatarSize should be 75. Returned: "
					+ Integer.toString(copySettings.getAvatarSize()));
		
		/**
		 * Make sure Settings pomodoroIsActive is set to false for copy constructor 
		 */
		assertEquals(false, copySettings.getPomodoroIsActive(), "Copy constructor Settings.pomodoroIsActive should be false. Returned: "
					+ copySettings.getPomodoroIsActive());
		
		/**
		 * Make sure Settings workPeriod is set to 33 for copy constructor 
		 */
		assertEquals(33, copySettings.getWorkPeriod(), "Copy constructor Settings.workPeriod should be 33. Returned: "
					+ Integer.toString(copySettings.getWorkPeriod()));
		
		/**
		 * Make sure Settings breakPeriod is set to 23 for copy constructor 
		 */
		assertEquals(23, copySettings.getBreakPeriod(), "Copy constructor Settings.breakPeriod should be 23. Returned: "
					+ Integer.toString(copySettings.getBreakPeriod()));
		
		/**
		 * Make sure Settings timeShowing is set to false for copy constructor
		 */
		assertEquals(false, copySettings.getTimeShowing(), "Copy constructor Settings.timeShowing should be trfalseue. Returned: "
					+ copySettings.getTimeShowing());
		
		/**
		 * Make sure Settings ftsIsActive is set to false for copy constructor
		 */
		assertEquals(false, copySettings.getFtsIsActive(), "Copy constructor Settings.ftsIsActive should be false. Returned: "
					+ copySettings.getFtsIsActive());
		
		/**
		 * Make sure Settings ntbIsActive is set to false for copy constructor
		 */
		assertEquals(false, copySettings.getNtbIsActive(), "Copy constructor Settings.ntbIsActive should be false. Returned: "
					+ copySettings.getNtbIsActive());
		
		/**
		 * Make sure Settings isAutoLinked is set to false for copy constructor
		 */
		assertEquals(false, copySettings.getIsAutoLinked(), "Copy constructor Settings.isAutoLinked should be false. Returned: "
					+ copySettings.getIsAutoLinked());
		
		/**
		 * Make sure Settings htbIsActive is set to false for copy constructor
		 */
		assertEquals(false, copySettings.getHtbIsActive(), "Copy constructor Settings.htbIsActive should be false. Returned: "
					+ copySettings.getHtbIsActive());
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetIconCircles")
	void SettingsSetIconCircles() {
		
		copySettings.setIconCircles(Color.BLUE);
		assertEquals(Color.BLUE, copySettings.getIconCircles(), "copySettings.iconCircles should be set to Color.Blue, but returned: "
					+ copySettings.getIconCircles()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetIcons")
	void SettingsSetIcons() {
		
		copySettings.setIcons(Color.GREEN);
		assertEquals(Color.GREEN, copySettings.getIcons(), "copySettings.icons should be set to Color.GREEN, but returned: "
					+ copySettings.getIcons()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetOpacityCircles")
	void SettingsSetOpacityCircles() {
		
		copySettings.setOpacityCircles(50);
		assertEquals(50, copySettings.getOpacityCircles(), "copySettings.opacityCircles should be set to 50, but returned: "
					+ Integer.toString(copySettings.getOpacityCircles())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetOpacityIcons")
	void SettingsSetOpacityIcons() {
		
		copySettings.setOpacityIcons(50);
		assertEquals(50, copySettings.getOpacityIcons(), "copySettings.opacityIcons should be set to 50, but returned: "
					+ Integer.toString(copySettings.getOpacityIcons())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetIsCollapsed")
	void SettingsSetIsCollapsed() {
		
		copySettings.setIsCollapsed(false);
		assertEquals(false, copySettings.getIsCollapsed(), "copySettings.isCollapsed should be set to false, but returned: "
					+ copySettings.getIsCollapsed()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetXCoord")
	void SettingsSetXCoord() {
		
		copySettings.setXCoord(25);
		assertEquals(25, copySettings.getXCoord(), "copySettings.xCoord should be set to 25, but returned: "
					+ Integer.toString(copySettings.getXCoord())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetYCoord")
	void SettingsSetYCoord() {
		
		copySettings.setYCoord(25);
		assertEquals(25, copySettings.getYCoord(), "copySettings.yCoord should be set to 25, but returned: "
					+ Integer.toString(copySettings.getYCoord())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetIsVertical")
	void SettingsSetIsVertical() {
		
		copySettings.setIsVertical(true);
		assertEquals(true, copySettings.getIsVertical(), "copySettings.isVertical should be set to true, but returned: "
					+ copySettings.getIsVertical()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetIconSize")
	void SettingsSetIconSize() {
		
		copySettings.setIconSize(45);
		assertEquals(45, copySettings.getIconSize(), "copySettings.iconSize should be set to 45, but returned: "
					+ Integer.toString(copySettings.getIconSize())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetTimerIsVisible")
	void SettingsSetTimerIsVisible() {
		
		copySettings.setTimerIsVisible(true);
		assertEquals(true, copySettings.getTimerIsVisible(), "copySettings.timerIsVisible should be set to true, but returned: "
					+ copySettings.getTimerIsVisible()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetPmIsVisible")
	void SettingsSetPmIsVisible() {
		
		copySettings.setPmIsVisible(true);
		assertEquals(true, copySettings.getPmIsVisible(), "copySettings.pmIsVisible should be set to true, but returned: "
					+ copySettings.getPmIsVisible()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetFtsIsVisible")
	void SettingsSetFtsIsVisible() {
		
		copySettings.setFtsIsVisible(true);
		assertEquals(true, copySettings.getFtsIsVisible(), "copySettings.ftsIsVisible should be set to true, but returned: "
					+ copySettings.getFtsIsVisible()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetHtbIsVisible")
	void SettingsSetHtbIsVisible() {
		
		copySettings.setHtbIsVisible(true);
		assertEquals(true, copySettings.getHtbIsVisible(), "copySettings.htbIsVisible should be set to true, but returned: "
					+ copySettings.getHtbIsVisible()); 
	}
	
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetNtbIsVisible")
	void SettingsSetNtbIsVisible() {
		
		copySettings.setNtbIsVisible(true);
		assertEquals(true, copySettings.getNtbIsVisible(), "copySettings.ntbIsVisible should be set to true, but returned: "
					+ copySettings.getNtbIsVisible()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetProgReportIsVisible")
	void SettingsProgReportIsVisible() {
		
		copySettings.setProgReportIsVisible(true);
		assertEquals(true, copySettings.getProgReportIsVisible(), "copySettings.progReportIsVisible should be set to true, but returned: "
					+ copySettings.getProgReportIsVisible()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetAvatarIsActive")
	void SettingsAvatarIsActive() {
		
		copySettings.setAvatarIsActive(false);
		assertEquals(false, copySettings.getAvatarIsActive(), "copySettings.avatarIsActive should be set to false, but returned: "
					+ copySettings.getAvatarIsActive()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetTextIsActive")
	void SettingsTextIsActive() {
		
		copySettings.setTextIsActive(true);
		assertEquals(true, copySettings.getTextIsActive(), "copySettings.textIsActive should be set to true, but returned: "
					+ copySettings.getTextIsActive()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetAudioIsActive")
	void SettingsAudioIsActive() {
		
		copySettings.setAudioIsActive(false);
		assertEquals(false, copySettings.getAudioIsActive(), "copySettings.audioIsActive should be set to false, but returned: "
					+ copySettings.getAudioIsActive()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetAvatarFilePath")
	void SettingsAvatarFilePath() {
		
		copySettings.setAvatarFilePath("images/avatar_duck.png");
		assertEquals("images/avatar_duck.png", copySettings.getAvatarFilePath(), "copySettings.avatarFilePath should be set to \"images/avatar_duck.png\", but returned: "
					+ copySettings.getAvatarFilePath()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetAudioFilePath")
	void SettingsAudioFilePath() {
		
		copySettings.setAudioFilePath("testtesttest");
		assertEquals("testtesttest", copySettings.getAudioFilePath(), "copySettings.audioFilePath should be set to \"testtesttest\", but returned: "
					+ copySettings.getAudioFilePath()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetAlwaysOnScreen")
	void SettingsAlwaysOnScreen() {
		
		copySettings.setAlwaysOnScreen(false);
		assertEquals(false, copySettings.getAlwaysOnScreen(), "copySettings.alwaysOnScreen should be set to false, but returned: "
					+ copySettings.getAlwaysOnScreen()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetAvatarSize")
	void SettingsSetAvatarSize() {
		
		copySettings.setAvatarSize(200);
		assertEquals(200, copySettings.getAvatarSize(), "copySettings.avatarSize should be set to 200, but returned: "
					+ Integer.toString(copySettings.getAvatarSize())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetPomodoroIsActive")
	void SettingsPomodoroIsActive() {
		
		copySettings.setPomodoroIsActive(true);
		assertEquals(true, copySettings.getPomodoroIsActive(), "copySettings.pomodoroIsActive should be set to true, but returned: "
					+ copySettings.getPomodoroIsActive()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetWorkPeriod")
	void SettingsSetWorkPeriod() {
		
		copySettings.setWorkPeriod(75);
		assertEquals(75, copySettings.getWorkPeriod(), "copySettings.workPeriod should be set to 75, but returned: "
					+ Integer.toString(copySettings.getWorkPeriod())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	@Test
	@DisplayName("<Settings> SetBreakPeriod")
	void SettingsSetBreakPeriod() {
		
		copySettings.setBreakPeriod(35);
		assertEquals(35, copySettings.getBreakPeriod(), "copySettings.breakPeriod should be set to 35, but returned: "
					+ Integer.toString(copySettings.getBreakPeriod())); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetTimeShowing")
	void SettingsTimeShowing() {
		
		copySettings.setTimeShowing(true);
		assertEquals(true, copySettings.getTimeShowing(), "copySettings.timeShowing should be set to true, but returned: "
					+ copySettings.getTimeShowing()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetFtsIsActive")
	void SettingsFtsIsActive() {
		
		copySettings.setFtsIsActive(true);
		assertEquals(true, copySettings.getFtsIsActive(), "copySettings.ftsIsActive should be set to true, but returned: "
					+ copySettings.getFtsIsActive()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetNtbIsActive")
	void SettingsNtbIsActive() {
		
		copySettings.setNtbIsActive(true);
		assertEquals(true, copySettings.getNtbIsActive(), "copySettings.ntbIsActive should be set to true, but returned: "
					+ copySettings.getNtbIsActive()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetIsAutoLinked")
	void SettingsIsAutoLinked() {
		
		copySettings.setIsAutoLinked(true);
		assertEquals(true, copySettings.getIsAutoLinked(), "copySettings.isAutoLinked should be set to true, but returned: "
					+ copySettings.getIsAutoLinked()); 
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	@Test
	@DisplayName("<Settings> SetHtbIsActive")
	void SettingsHtbIsActive() {
		
		copySettings.setHtbIsActive(true);
		assertEquals(true, copySettings.getHtbIsActive(), "copySettings.htbIsActive should be set to true, but returned: "
					+ copySettings.getHtbIsActive()); 
	}
}
