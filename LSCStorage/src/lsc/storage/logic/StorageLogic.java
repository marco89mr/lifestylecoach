package lsc.storage.logic;

import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.DataFilter;
import lsc.rest.filter.Filter.DeadlineFilter;
import lsc.rest.filter.Filter.GoalFilter;
import lsc.rest.filter.Filter.NotificationFilter;
import lsc.rest.filter.Filter.ToDoFilter;
import lsc.rest.filter.Filter.UserFilter;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;
import lsc.storage.parser.DataParser;
import lsc.storage.parser.DeadlineParser;
import lsc.storage.parser.GoalParser;
import lsc.storage.parser.NotificationParser;
import lsc.storage.parser.StorageParser;
import lsc.storage.parser.ToDoParser;
import lsc.storage.parser.UserParser;


public class StorageLogic {
	
	
	
	
	
	public static UserLogic user = new UserLogic();
	public static class UserLogic extends Logic<	User,
													UserCollection,
													UserFilter,
													LocalDatabaseClient.UserClient,
													UserParser	> {
		@Override
		protected UserFilter filter() {return Filter.user(); }
		@Override
		protected LocalDatabaseClient.UserClient client() { return LocalDatabaseClient.user; }
		@Override
		protected UserParser parser() { return StorageParser.user; }
	}
	
	
	
	public static DataLogic data = new DataLogic();
	public static class DataLogic extends Logic<	Data,
													DataCollection,
													DataFilter,
													LocalDatabaseClient.DataClient,
													DataParser	> {
		@Override
		protected DataFilter filter() {return Filter.data(); }
		@Override
		protected LocalDatabaseClient.DataClient client() { return LocalDatabaseClient.data; }
		@Override
		protected DataParser parser() { return StorageParser.data; }
	}
	
	
	
	public static GoalLogic goal = new GoalLogic();
	public static class GoalLogic extends Logic<	Goal,
													GoalCollection,
													GoalFilter,
													LocalDatabaseClient.GoalClient,
													GoalParser	> {
		@Override
		protected GoalFilter filter() {return Filter.goal(); }
		@Override
		protected LocalDatabaseClient.GoalClient client() { return LocalDatabaseClient.goal; }
		@Override
		protected GoalParser parser() { return StorageParser.goal; }
	}
	
	
	
	public static DeadlineLogic deadline = new DeadlineLogic();
	public static class DeadlineLogic extends Logic<	Deadline,
														DeadlineCollection,
														DeadlineFilter,
														LocalDatabaseClient.DeadlineClient,
														DeadlineParser	> {
		@Override
		protected DeadlineFilter filter() {return Filter.deadline(); }
		@Override
		protected LocalDatabaseClient.DeadlineClient client() { return LocalDatabaseClient.deadline; }
		@Override
		protected DeadlineParser parser() { return StorageParser.deadline; }
	}
	
	
	
	public static ToDoLogic todo = new ToDoLogic();
	public static class ToDoLogic extends Logic<	ToDo,
													ToDoCollection,
													ToDoFilter,
													LocalDatabaseClient.ToDoClient,
													ToDoParser	> {
		@Override
		protected ToDoFilter filter() {return Filter.todo(); }
		@Override
		protected LocalDatabaseClient.ToDoClient client() { return LocalDatabaseClient.todo; }
		@Override
		protected ToDoParser parser() { return StorageParser.todo; }
	}
	
	
	
	public static NotificationLogic notification = new NotificationLogic();
	public static class NotificationLogic extends Logic<	Notification,
															NotificationCollection,
															NotificationFilter,
															LocalDatabaseClient.NotificationClient,
															NotificationParser	> {
		@Override
		protected NotificationFilter filter() {return Filter.notification(); }
		@Override
		protected LocalDatabaseClient.NotificationClient client() { return LocalDatabaseClient.notification; }
		@Override
		protected NotificationParser parser() { return StorageParser.notification; }
	}
	
	
}
