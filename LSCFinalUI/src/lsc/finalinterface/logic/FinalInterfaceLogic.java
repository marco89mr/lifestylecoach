package lsc.finalinterface.logic;


public class FinalInterfaceLogic {
	
	
	
	public static UserLogic user = new UserLogic();
	public static RecordLogic record = new RecordLogic();
	public static GoalLogic goal = new GoalLogic();
	public static DeadlineLogic deadline = new DeadlineLogic();
	public static ToDoLogic todo = new ToDoLogic();
	public static NotificationLogic notification = new NotificationLogic();
	
	
	
	/*
	public static class UserLogic extends BaseLogic<	User,
													UserCollection,
													UserFilter,
													StorageClient.UserClient,
													UserParser	> {
		@Override
		protected UserFilter filter() {return Filter.user; }
		@Override
		protected StorageClient.UserClient client() { return StorageClient.user; }
		@Override
		protected UserParser parser() { return FinalInterfaceParser.user; }
		@Override
		protected int master(User u) { return u.getId(); }// i want 0 if not setted
	}
	
	
	
	public static class RecordLogic extends BaseLogic<	RecordComplex,
													RecordComplexCollection,
													RecordFilter,
													StorageClient.RecordClient,
													RecordParser	> {
		@Override
		protected RecordFilter filter() {return Filter.record; }
		@Override
		protected StorageClient.RecordClient client() { return StorageClient.record; }
		@Override
		protected RecordParser parser() { return FinalInterfaceParser.record; }
		@Override
		protected int master(RecordComplex x) { return x.getUserId(); }
	}
	
	
	
	public static class DataLogic extends BaseLogic<	Data,
													DataCollection,
													DataFilter,
													StorageClient.DataClient,
													DataParser	> {
		@Override
		protected DataFilter filter() {return Filter.data; }
		@Override
		protected StorageClient.DataClient client() { return StorageClient.data; }
		@Override
		protected DataParser parser() { return FinalInterfaceParser.data; }
		protected int master(Data x) { return StorageClient
												.record
												.getById( x.getRecordId() )
												.getUserId();
		}
	}
	
	
	
	public static class GoalLogic extends BaseLogic<	Goal,
													GoalCollection,
													GoalFilter,
													StorageClient.GoalClient,
													GoalParser	> {
		@Override
		protected GoalFilter filter() {return Filter.goal; }
		@Override
		protected StorageClient.GoalClient client() { return StorageClient.goal; }
		@Override
		protected GoalParser parser() { return FinalInterfaceParser.goal; }
		protected int master(Goal x) { return x.getUserId(); }
	}
	
	
	
	public static class DeadlineLogic extends BaseLogic<	Deadline,
														DeadlineCollection,
														DeadlineFilter,
														StorageClient.DeadlineClient,
														DeadlineParser	> {
		@Override
		protected DeadlineFilter filter() {return Filter.deadline; }
		@Override
		protected StorageClient.DeadlineClient client() { return StorageClient.deadline; }
		@Override
		protected DeadlineParser parser() { return FinalInterfaceParser.deadline; }
		protected int master(Deadline x) { return StorageClient
				.goal
				.getById( x.getGoalId() )
				.getUserId();
		}
	}
	
	
	
	public static class ToDoLogic extends BaseLogic<	ToDo,
													ToDoCollection,
													ToDoFilter,
													StorageClient.ToDoClient,
													ToDoParser	> {
		@Override
		protected ToDoFilter filter() {return Filter.todo; }
		@Override
		protected StorageClient.ToDoClient client() { return StorageClient.todo; }
		@Override
		protected ToDoParser parser() { return FinalInterfaceParser.todo; }
		protected int master(ToDo x) { return x.getUserId(); }
	}
	
	
	
	public static class NotificationLogic extends BaseLogic<	Notification,
															NotificationCollection,
															NotificationFilter,
															StorageClient.NotificationClient,
															NotificationParser	> {
		@Override
		protected NotificationFilter filter() {return Filter.notification; }
		@Override
		protected StorageClient.NotificationClient client() { return StorageClient.notification; }
		@Override
		protected NotificationParser parser() { return FinalInterfaceParser.notification; }
		protected int master(Notification x) { return x.getUserId(); }
	}
	*/
	
}
