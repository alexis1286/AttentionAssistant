package AttentionAssistant;

public class AttentionAssistantDriver {
	static DataBase db = new DataBase();
	
	public static void main(String[] args) throws Exception {
		//user login->userID(links db)
		
		db.DatabaseSetUp();
		//get settings
	    new Nav_Bar(db);
	  }
}
