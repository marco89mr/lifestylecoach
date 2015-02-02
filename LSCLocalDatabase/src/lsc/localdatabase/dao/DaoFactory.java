package lsc.localdatabase.dao;


public class DaoFactory {


	// predefined factories
	
	public static final UserDao user = new UserDao();

	public static final RecordDao record = new RecordDao();
	
	public static final DataDao data = new DataDao();
	
	public static final GoalDao goal = new GoalDao();
	
	public static final DeadlineDao deadline = new DeadlineDao();
	
	public static final NotificationDao notification = new NotificationDao();
	
	public static final ToDoDao todo = new ToDoDao();
	
}
