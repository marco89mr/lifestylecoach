package lsc.storage.parser;


public class StorageParser {


	// predefined factories
	
	public static final UserParser user = new UserParser();

	public static final RecordParser record = new RecordParser();

	public static final DataParser data = new DataParser();
	
	public static final GoalParser goal = new GoalParser();
	
	public static final DeadlineParser deadline = new DeadlineParser();
	
	public static final NotificationParser notification = new NotificationParser();
	
	public static final ToDoParser todo = new ToDoParser();
	
}
