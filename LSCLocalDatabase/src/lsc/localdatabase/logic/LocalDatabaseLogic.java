package lsc.localdatabase.logic;

import lsc.localdatabase.dao.dataaccess.DataDataAccess;
import lsc.localdatabase.dao.dataaccess.DeadlineDataAccess;
import lsc.localdatabase.dao.dataaccess.GoalDataAccess;
import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.dataaccess.NotificationDataAccess;
import lsc.localdatabase.dao.dataaccess.RecordDataAccess;
import lsc.localdatabase.dao.dataaccess.ToDoDataAccess;
import lsc.localdatabase.dao.dataaccess.UserDataAccess;
import lsc.localdatabase.dao.model.DataCollectionDao;
import lsc.localdatabase.dao.model.DataDao;
import lsc.localdatabase.dao.model.DeadlineCollectionDao;
import lsc.localdatabase.dao.model.DeadlineDao;
import lsc.localdatabase.dao.model.GoalCollectionDao;
import lsc.localdatabase.dao.model.GoalDao;
import lsc.localdatabase.dao.model.NotificationCollectionDao;
import lsc.localdatabase.dao.model.NotificationDao;
import lsc.localdatabase.dao.model.RecordCollectionDao;
import lsc.localdatabase.dao.model.RecordDao;
import lsc.localdatabase.dao.model.ToDoCollectionDao;
import lsc.localdatabase.dao.model.ToDoDao;
import lsc.localdatabase.dao.model.UserCollectionDao;
import lsc.localdatabase.dao.model.UserDao;
import lsc.localdatabase.parser.DataParser;
import lsc.localdatabase.parser.DeadlineParser;
import lsc.localdatabase.parser.GoalParser;
import lsc.localdatabase.parser.LocalDatabaseParser;
import lsc.localdatabase.parser.NotificationParser;
import lsc.localdatabase.parser.RecordParser;
import lsc.localdatabase.parser.ToDoParser;
import lsc.localdatabase.parser.UserParser;
import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.DataClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.DeadlineClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.GoalClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.NotificationClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.RecordClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.ToDoClient;
import lsc.localdatabase.rest.client.LocalDatabaseClient.UserClient;
import lsc.rest.filter.LocalDatabaseFilter;
import lsc.rest.filter.LocalDatabaseFilter.DataFilter;
import lsc.rest.filter.LocalDatabaseFilter.DeadlineFilter;
import lsc.rest.filter.LocalDatabaseFilter.GoalFilter;
import lsc.rest.filter.LocalDatabaseFilter.NotificationFilter;
import lsc.rest.filter.LocalDatabaseFilter.RecordFilter;
import lsc.rest.filter.LocalDatabaseFilter.ToDoFilter;
import lsc.rest.filter.LocalDatabaseFilter.UserFilter;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.Record;
import lsc.rest.model.RecordCollection;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;


public class LocalDatabaseLogic {
	
	
	
	

	public static UserLogic user = new LocalDatabaseLogic().new UserLogic();
	public class UserLogic extends BaseLogic<	User,
												UserCollection,
												UserFilter,
												UserDao,
												UserCollectionDao,
												UserDataAccess,
												UserParser,
												UserClient			> {
		@Override
		protected UserClient client() { return LocalDatabaseClient.user; }
		@Override
		protected UserDataAccess data_access() { return LocalDatabaseDataAccess.user; }
		@Override
		protected UserParser parser() { return LocalDatabaseParser.user; }
		@Override
		protected UserFilter filter() { return LocalDatabaseFilter.user; }
	}
	
	
	
	public static RecordLogic record = new LocalDatabaseLogic().new RecordLogic();
	public class RecordLogic extends BaseLogic<	Record,
												RecordCollection,
												RecordFilter,
												RecordDao,
												RecordCollectionDao,
												RecordDataAccess,
												RecordParser,
												RecordClient		> {
		@Override
		protected RecordClient client() { return LocalDatabaseClient.record; }
		@Override
		protected RecordDataAccess data_access() { return LocalDatabaseDataAccess.record; }
		@Override
		protected RecordParser parser() { return LocalDatabaseParser.record; }
		@Override
		protected RecordFilter filter() { return LocalDatabaseFilter.record; }
	}
	
	
	
	public static DataLogic data = new LocalDatabaseLogic().new DataLogic();
	public class DataLogic extends BaseLogic<	Data,
												DataCollection,
												DataFilter,
												DataDao,
												DataCollectionDao,
												DataDataAccess,
												DataParser,
												DataClient	>		{
		@Override
		protected DataClient client() { return LocalDatabaseClient.data; }
		@Override
		protected DataDataAccess data_access() { return LocalDatabaseDataAccess.data; }
		@Override
		protected DataParser parser() { return LocalDatabaseParser.data; }
		@Override
		protected DataFilter filter() { return LocalDatabaseFilter.data; }
	}
	
	
	
	public static GoalLogic goal = new LocalDatabaseLogic().new GoalLogic();
	public class GoalLogic extends BaseLogic<	Goal,
												GoalCollection,
												GoalFilter,
												GoalDao,
												GoalCollectionDao,
												GoalDataAccess,
												GoalParser,
												GoalClient		>		{
		@Override
		protected GoalClient client() { return LocalDatabaseClient.goal; }
		@Override
		protected GoalDataAccess data_access() { return LocalDatabaseDataAccess.goal; }
		@Override
		protected GoalParser parser() { return LocalDatabaseParser.goal; }
		@Override
		protected GoalFilter filter() { return LocalDatabaseFilter.goal; }
	}
	
	
	
	public static DeadlineLogic deadline = new LocalDatabaseLogic().new DeadlineLogic();
	public class DeadlineLogic extends BaseLogic<	Deadline,
													DeadlineCollection,
													DeadlineFilter,
													DeadlineDao,
													DeadlineCollectionDao,
													DeadlineDataAccess,
													DeadlineParser,
													DeadlineClient	>		{
		@Override
		protected DeadlineClient client() { return LocalDatabaseClient.deadline; }
		@Override
		protected DeadlineDataAccess data_access() { return LocalDatabaseDataAccess.deadline; }
		@Override
		protected DeadlineParser parser() { return LocalDatabaseParser.deadline; }
		@Override
		protected DeadlineFilter filter() { return LocalDatabaseFilter.deadline; }
	}
	
	
	
	public static ToDoLogic todo = new LocalDatabaseLogic().new ToDoLogic();
	public class ToDoLogic extends BaseLogic<	ToDo,
												ToDoCollection,
												ToDoFilter,
												ToDoDao,
												ToDoCollectionDao,
												ToDoDataAccess,
												ToDoParser,
												ToDoClient	>		{
		@Override
		protected ToDoClient client() { return LocalDatabaseClient.todo; }
		@Override
		protected ToDoDataAccess data_access() { return LocalDatabaseDataAccess.todo; }
		@Override
		protected ToDoParser parser() { return LocalDatabaseParser.todo; }
		@Override
		protected ToDoFilter filter() { return LocalDatabaseFilter.todo; }
	}
	
	
	
	public static NotificationLogic notification = new LocalDatabaseLogic().new NotificationLogic();
	public class NotificationLogic extends BaseLogic<	Notification,
														NotificationCollection,
														NotificationFilter,
														NotificationDao,
														NotificationCollectionDao,
														NotificationDataAccess,
														NotificationParser,
														NotificationClient	>		{
		@Override
		protected NotificationClient client() { return LocalDatabaseClient.notification; }
		@Override
		protected NotificationDataAccess data_access() { return LocalDatabaseDataAccess.notification; }
		@Override
		protected NotificationParser parser() { return LocalDatabaseParser.notification; }
		@Override
		protected NotificationFilter filter() { return LocalDatabaseFilter.notification; }
	}
	
	
	
	
	
}
