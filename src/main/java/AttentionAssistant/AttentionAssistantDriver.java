package AttentionAssistant;

public class AttentionAssistantDriver {
	static DataBase db = new DataBase();	
	static Observer observer = new Observer();
	static Priority_Manager priority_manager;
	static Pomodoro_Timer pomodoro_timer = new Pomodoro_Timer();
	static Negative_Thought_Burner negative_thought_burner = new Negative_Thought_Burner();
	static Happy_Thought_Button happy_thought_button = new Happy_Thought_Button(db);
	static Free_Thought_Space free_thought_space = new Free_Thought_Space();
	
	public static void main(String[] args) throws Exception {
		int userID = 1; // changes from 0 to 1 for database
		//user login->userID(links db)
		Settings settings = new Settings(userID);
		Nav_Bar navbar = new Nav_Bar(settings);
		db.DatabaseSetUp();
		
		/**
		 * loading default happy media library into database 
		 * this will take place just once during user registration
		 * will remove from driver after user registration is created
		 */
		Media media1 = new Media("happyThoughtMedia/gratisography-447H-free-stock-photo.jpg");
		db.AddMedia(media1, userID);
		Media media2 = new Media("happyThoughtMedia/78nF.gif");
		db.AddMedia(media2, userID);
		Media media3 = new Media("happyThoughtMedia/231582875_f219808478_o.jpg");
		db.AddMedia(media3, userID);
		Media media4 = new Media("happyThoughtMedia/jVo.gif");
		db.AddMedia(media4, userID);
		Media media5 = new Media("happyThoughtMedia/alex-vinogradov--wHZZ-cg7rk-unsplash.jpg");
		db.AddMedia(media5, userID);
		Media media6 = new Media("happyThoughtMedia/PB35.gif");
		db.AddMedia(media6, userID);
		Media media7 = new Media("happyThoughtMedia/daniel-sessler-9Nn21mIKP1w-unsplash.jpg");
		db.AddMedia(media7, userID);
		Media media8 = new Media("happyThoughtMedia/QHa.gif");
		db.AddMedia(media8, userID);
		Media media9 = new Media("happyThoughtMedia/max-lissenden-snYLMKphCf4-unsplash.jpg");
		db.AddMedia(media9, userID);
		Media media10 = new Media("happyThoughtMedia/Z2QS.gif");
		db.AddMedia(media10, userID);
		Media media11 = new Media("happyThoughtMedia/rod-long-ogWhdXOl5qY-unsplash.jpg");
		db.AddMedia(media11, userID);
		Media media12 = new Media("happyThoughtMedia/4OKm.gif");
		db.AddMedia(media12, userID);
		Media media13 = new Media("happyThoughtMedia/yusuf-onuk-uzZgdFKisng-unsplash.jpg");
		db.AddMedia(media13, userID);
		
		/**
		 * get settings
		 * this db.AddSettings(settings) call is temporary for testing purposes until user class is created
		 * 
		 * db.AddSettings(settings, userID) will be called from during the registration process for a new user
		 * db.selectSettings(userID) will be called during the login process for a returning user
		 */
		db.AddSettings(settings, userID); 
		priority_manager = new Priority_Manager(db,observer, pomodoro_timer);
		
		navbar.run_nav_bar(userID,db,navbar,settings,observer,priority_manager,pomodoro_timer,negative_thought_burner,happy_thought_button,free_thought_space);
	   	 
		/**
		 * TEST CODE
		 * DELETE BEFORE PRODUCTION
		 */
		//Observer testObserver = new Observer();
		//Task testTask = new Task();
		
		//MouseTracker testMouse = new MouseTracker();
		//testMouse.startTracking();
		
		//testObserver.keywordsGenerator(testTask);
		
		//KeyBoardTracker testKeyboard = new KeyBoardTracker();
		//testKeyboard.startTracking(null);
		
	  }
}
