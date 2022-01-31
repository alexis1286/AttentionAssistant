package AttentionAssistant;

public class Pomodoro_Timer {
	
	/**
	 * Options are 'WORK' and 'BREAK'
	 * WORK - user should be working
	 * BREAK - user should be taking a break
	 */

	enum Work_Break {
		WORK, BREAK
	}
	
	/**
	 * Variable to tell whether the user is on break or should be working
	 */
	private Work_Break status = Work_Break.BREAK;
	
	/**
	 * Returns to the caller the current status of the user.  Whether the 
	 * user should be working or taking a break
	 * @return Work_Break
	 */
	protected Work_Break getWorkBreakStatus() {
		return status;
	}
	
}