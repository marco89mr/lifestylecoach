package lsc.storage.rest.client;

import java.net.URI;
import java.net.URISyntaxException;

import lsc.rest.client.ClientUtils;
import lsc.rest.filter.Filter;
import lsc.rest.model.Base;
import lsc.rest.model.BaseCollection;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;


public class StorageClient {
	

	
	public static UserClient user = new UserClient();
	
	public static RecordClient record = new RecordClient();
	
	public static DataClient data = new DataClient();
	
	public static GoalClient goal = new GoalClient();
	
	public static DeadlineClient deadline = new DeadlineClient();
	
	public static ToDoClient todo = new ToDoClient();
	
	public static NotificationClient notification = new NotificationClient();
	
	
	
	public static abstract class BaseClient<M extends Base, MM extends BaseCollection<M>, F extends Filter.BaseFilter<F> > {

		protected static final String protocol = "http";
		protected static final String host = "//localhost";
		protected static final String port = ":4000";
		protected static final String base_path = "/lsc";
		public static final String main_url() {
			URI uri = null;
			try {
				uri = new URI(protocol, host	+port	+base_path	, null);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return uri.toASCIIString();
		}
		public abstract String resource_url();
		
		protected abstract Class<M> model_class();
		protected abstract Class<MM> model_collection_class();
		
		public MM getAll(F filter) {
			return ClientUtils.<MM>http_get(model_collection_class(), resource_url()+filter.param_url() );
		}
		public M getById(int id) {
			return ClientUtils.<M>http_get(model_class(), resource_url() + "/" + id );
		}
		public M post(M x) {
			String url = ClientUtils.<M>http_post(model_class(), resource_url(), x);
			System.out.println(url);
			x = ClientUtils.<M>http_get(this.model_class(), url);
			//x.setId(y.getId());
			return x;
		}
		public void put(M x) {
			ClientUtils.<M>http_put(model_class(), resource_url() + "/" + x.getId(), x);
		}
		public int delete(int id) {
			return ClientUtils.http_delete( resource_url() + "/" + id );
		}
		
	}
	
	
	
	public static class UserClient extends BaseClient<User, UserCollection, Filter.UserFilter> {		
		@Override
		protected Class<User> model_class() { return User.class; }
		@Override
		protected Class<UserCollection> model_collection_class() { return UserCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/user"; }
	}
	public static class RecordClient extends BaseClient<RecordComplex, RecordComplexCollection, Filter.RecordFilter> {			
		@Override
		protected Class<RecordComplex> model_class() { return RecordComplex.class; }
		@Override
		protected Class<RecordComplexCollection> model_collection_class() { return RecordComplexCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/record"; }		
	}
	public static class DataClient extends BaseClient<Data, DataCollection, Filter.DataFilter> {			
		@Override
		protected Class<Data> model_class() { return Data.class; }
		@Override
		protected Class<DataCollection> model_collection_class() { return DataCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/data"; }
	}
	public static class GoalClient extends BaseClient<Goal, GoalCollection, Filter.GoalFilter> {
		@Override
		protected Class<Goal> model_class() { return Goal.class; }
		@Override
		protected Class<GoalCollection> model_collection_class() { return GoalCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/goal"; }	
	}
	public static class DeadlineClient extends BaseClient<Deadline, DeadlineCollection, Filter.DeadlineFilter> {
		@Override
		protected Class<Deadline> model_class() { return Deadline.class; }
		@Override
		protected Class<DeadlineCollection> model_collection_class() { return DeadlineCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/deadline"; }	
	}
	public static class ToDoClient extends BaseClient<ToDo, ToDoCollection, Filter.ToDoFilter> {
		@Override
		protected Class<ToDo> model_class() { return ToDo.class; }
		@Override
		protected Class<ToDoCollection> model_collection_class() { return ToDoCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/todo"; }		
	}
	public static class NotificationClient extends BaseClient<Notification, NotificationCollection, Filter.NotificationFilter> {
		@Override
		protected Class<Notification> model_class() { return Notification.class; }
		@Override
		protected Class<NotificationCollection> model_collection_class() { return NotificationCollection.class; }
		@Override
		public String resource_url() { return main_url() + "/notification"; }	
	}
	
	
	
	/*
	public static class UserFilter {
		public static class name{}
		public static class mail{}
		public static class olderthan{}
		public static class youngerthan{}
		public static class bornbeforedate{}
		public static class bornafterdate{}		
	}
	public static enum RecordFilter {			
		user_id, type, last, fromdate, todate;
	}
	public static enum DataFilter {
		user_id, record_id, record_type, data_name, last, fromdate, todate;
	}
	public static enum GoalFilter {
		user_id, record_type, data_name, last;
	}
	public static enum DeadlineFilter {
		user_id, goal_id, record_type, data_name, status, by_date, last;	
	}
	public static enum ToDoFilter {
		user_id, type, last, fromdate, todate;
	}
	public static enum NotificationFilter {
		user_id, record_type, data_name, last;
	}
	*/
	
	
	
}
