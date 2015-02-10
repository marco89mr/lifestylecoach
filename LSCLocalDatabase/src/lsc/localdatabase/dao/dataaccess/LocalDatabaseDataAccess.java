package lsc.localdatabase.dao.dataaccess;


public class LocalDatabaseDataAccess {


	// predefined factories
	
	public static final UserDataAccess user = new UserDataAccess();

	public static final RecordDataAccess record = new RecordDataAccess();
	
	public static final DataDataAccess data = new DataDataAccess();
	
	public static final GoalDataAccess goal = new GoalDataAccess();
	
	public static final DeadlineDataAccess deadline = new DeadlineDataAccess();
	
	public static final NotificationDataAccess notification = new NotificationDataAccess();
	
	public static final ToDoDataAccess todo = new ToDoDataAccess();
	
}
