package AttentionAssistant;

public class AttentionAssistantDriver {
	static DataBase db = new DataBase();
	static Settings settings = new Settings();
	
	static Observer observer = new Observer();
	static Priority_Manager priority_manager = new Priority_Manager(observer);
	static Pomodoro_Timer pomodoro_timer = new Pomodoro_Timer();
	static Negative_Thought_Burner negative_thought_burner = new Negative_Thought_Burner();
	static Happy_Thought_Button happy_thought_button = new Happy_Thought_Button();
	static Free_Thought_Space free_thought_space = new Free_Thought_Space();
	
	public static void main(String[] args) throws Exception {
		//user login->userID(links db)
		Nav_Bar navbar = new Nav_Bar(settings);
		db.DatabaseSetUp();
		//get settings
		//this db.AddSettings(settings) call is temporary for testing purposes until user class is created
		db.AddSettings(settings);
		
	   	 navbar.run_nav_bar(db,navbar,settings,observer,priority_manager,pomodoro_timer,negative_thought_burner,happy_thought_button,free_thought_space);
		
		/**
		 * TEST CODE
		 * DELETE BEFORE PRODUCTION
		 */
		Observer testObserver = new Observer();
		Task testTask = new Task();
		
		testObserver.keywordsGenerator(testTask);
		
	  }
}
