package lsc.localdatabase.client;

import lsc.localdatabase.rest.model.DataCollectionRest;
import lsc.localdatabase.rest.model.DataRest;
import lsc.localdatabase.rest.model.DeadlineCollectionRest;
import lsc.localdatabase.rest.model.DeadlineRest;
import lsc.localdatabase.rest.model.GoalCollectionRest;
import lsc.localdatabase.rest.model.GoalRest;
import lsc.localdatabase.rest.model.NotificationCollectionRest;
import lsc.localdatabase.rest.model.NotificationRest;
import lsc.localdatabase.rest.model.RecordCollectionRest;
import lsc.localdatabase.rest.model.RecordRest;
import lsc.localdatabase.rest.model.ToDoCollectionRest;
import lsc.localdatabase.rest.model.ToDoRest;
import lsc.localdatabase.rest.model.UserCollectionRest;
import lsc.localdatabase.rest.model.UserRest;
import lsc.localdatabase.rest.path.Path;
import lsc.localdatabase.rest.path.PathFactory;


public class ClientFactory {
	
	
	
	// predefined factories
	
	public static final BaseClient<Path.UserFilter, UserRest, UserCollectionRest, UserRest.Relation> user =
			new BaseClient<Path.UserFilter, UserRest, UserCollectionRest, UserRest.Relation>(UserRest.class, UserCollectionRest.class, PathFactory.user());

	public static final BaseClient<Path.RecordFilter, RecordRest, RecordCollectionRest, RecordRest.Relation> record =
			new BaseClient<Path.RecordFilter, RecordRest, RecordCollectionRest, RecordRest.Relation>(RecordRest.class, RecordCollectionRest.class, PathFactory.record());
	
	public static final BaseClient<Path.DataFilter, DataRest, DataCollectionRest, DataRest.Relation> data =
			new BaseClient<Path.DataFilter, DataRest, DataCollectionRest, DataRest.Relation>(DataRest.class, DataCollectionRest.class, PathFactory.data());
	
	public static final BaseClient<Path.GoalFilter, GoalRest, GoalCollectionRest, GoalRest.Relation> goal =
			new BaseClient<Path.GoalFilter, GoalRest, GoalCollectionRest, GoalRest.Relation>(GoalRest.class, GoalCollectionRest.class, PathFactory.goal());
	
	public static final BaseClient<Path.DeadlineFilter, DeadlineRest, DeadlineCollectionRest, DeadlineRest.Relation> deadline =
			new BaseClient<Path.DeadlineFilter, DeadlineRest, DeadlineCollectionRest, DeadlineRest.Relation>(DeadlineRest.class, DeadlineCollectionRest.class, PathFactory.deadline());
	
	public static final BaseClient<Path.NotificationFilter, NotificationRest, NotificationCollectionRest, NotificationRest.Relation> notification =
			new BaseClient<Path.NotificationFilter, NotificationRest, NotificationCollectionRest, NotificationRest.Relation>(NotificationRest.class, NotificationCollectionRest.class, PathFactory.notification());
	
	public static final BaseClient<Path.ToDoFilter, ToDoRest, ToDoCollectionRest, ToDoRest.Relation> todo =
			new BaseClient<Path.ToDoFilter, ToDoRest, ToDoCollectionRest, ToDoRest.Relation>(ToDoRest.class, ToDoCollectionRest.class, PathFactory.todo());
	
	
}
