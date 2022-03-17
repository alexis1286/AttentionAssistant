package AttentionAssistant;

public class AttentionAssistantDriver {
	static DataBase db = new DataBase();	
	static Observer observer = new Observer();
	static Priority_Manager priority_manager;
	static Pomodoro_Timer pomodoro_timer = new Pomodoro_Timer();
	static Negative_Thought_Burner negative_thought_burner = new Negative_Thought_Burner();
	static Happy_Thought_Button happy_thought_button = new Happy_Thought_Button();
	static Free_Thought_Space free_thought_space = new Free_Thought_Space();
	
	public static void main(String[] args) throws Exception {
		int userID = 0;
		//user login->userID(links db)
		Settings settings = new Settings(userID);
		Nav_Bar navbar = new Nav_Bar(settings);
		db.DatabaseSetUp();
		//get settings
		//this db.AddSettings(settings) call is temporary for testing purposes until user class is created
		//db.AddSettings(settings, userID) will be called from during the registration process for a new user
		//db.selectSettings(userID) will be called during the login process for a returning user
		db.AddSettings(settings, userID);
		priority_manager = new Priority_Manager(db,observer, pomodoro_timer);
		
		navbar.run_nav_bar(userID,db,navbar,settings,observer,priority_manager,pomodoro_timer,negative_thought_burner,happy_thought_button,free_thought_space);
	   	 
		/**
		 * TEST CODE
		 * DELETE BEFORE PRODUCTION
		 */
		Observer testObserver = new Observer();
		Task testTask = new Task();
		
		MouseTracker testMouse = new MouseTracker();
		//testMouse.startTracking();
		
		testObserver.keywordsGenerator(testTask);
		
	  }
}
