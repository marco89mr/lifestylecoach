package lsc.rest.filter;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import lsc.rest.model.Deadline;


public class Filter {
	
	
	
	public static UserFilter user() { return new UserFilter(); };
	
	public static RecordFilter record() { return new RecordFilter(); };
	
	public static DataFilter data() { return new DataFilter(); };
	
	public static GoalFilter goal() { return new GoalFilter(); };
	
	public static DeadlineFilter deadline() { return new DeadlineFilter(); };
	
	public static ToDoFilter todo() { return new ToDoFilter(); };
	
	public static NotificationFilter notification() { return new NotificationFilter(); };
	
	
	
	public static abstract class BaseFilter<F extends BaseFilter<?>> {
		public abstract class as{};
		protected Map<String,String> filter = new HashMap<String,String>();

		public Map<String,String> getFilter() { return filter; }
		
		//useless
		public BaseFilter() {
			filter = null;
			filter = new HashMap<String,String>();
		}
		
		//is this the only way?
		@SuppressWarnings("unchecked")
		public F clear() {
			filter = new HashMap<String,String>();
			return (F) this;
		}
		
		public String param_url() {
			String param = ( filter.isEmpty() ? "" : "?");
			for(String key : filter.keySet())
				param = param + key + "=" + filter.get(key) + "&";
			return param;
		}
		
		@SuppressWarnings("unchecked")
		public F addFilter(String key, String value) {
			filter.put(key, value);
			return (F) this;
		}
		
		@SuppressWarnings("unchecked")
		public F addFilter(MultivaluedMap<String,String> param) {
			for(String key : param.keySet())
				filter.put(key, param.getFirst(key));
			return (F) this;
		}
		
		@SuppressWarnings("unchecked")
		public F addFilter(Map<String,String> param) {
			for(String key : param.keySet())
				filter.put(key, param.get(key));
			return (F) this;
		}
		
		@SuppressWarnings("unchecked")
		public <IN extends BaseFilter<?>> F addFilter( F filter ) {
			for(String key : filter.getFilter().keySet())
				this.filter.put(key, filter.getFilter().get(key));
			return (F) this;
		}
		
	}
	
	
	
	public static class UserFilter extends BaseFilter<UserFilter> {		
		public UserFilter name(String s) { return this.addFilter("name", s); }
		public UserFilter mail(String s) { return this.addFilter("mail", s); }
		public UserFilter olderthan(String s) { return this.addFilter("olderthan", s); }
		public UserFilter youngerthan(String s) { return this.addFilter("youngerthan", s); }
		public UserFilter bornbeforedate(String s) { return this.addFilter("bornbeforedate", s); }
		public UserFilter bornafterdate(String s) { return this.addFilter("bornafterdate", s); }
	}
	public static class RecordFilter extends BaseFilter<RecordFilter> {			
		public RecordFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public RecordFilter type(String s) { return this.addFilter("type", s); }
		public RecordFilter last(int i) { return this.addFilter("last", ""+i); }
		public RecordFilter fromdate(String s) { return this.addFilter("fromdate", s); }
		public RecordFilter todate(String s) { return this.addFilter("todate", s); }
	}
	public static class DataFilter extends BaseFilter<DataFilter> {			
		public DataFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public DataFilter record_id(int id) { return this.addFilter("record_id", ""+id); }
		public DataFilter record_type(String s) { return this.addFilter("record_type", s); }
		public DataFilter data_name(String s) { return this.addFilter("data_name", s); }
		public DataFilter last(int i) { return this.addFilter("last", ""+i); }
		public DataFilter fromdate(String s) { return this.addFilter("fromdate", s); }
		public DataFilter todate(String s) { return this.addFilter("todate", s); }
	}
	public static class GoalFilter extends BaseFilter<GoalFilter> {
		public GoalFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public GoalFilter record_type(String s) { return this.addFilter("record_type", s); }
		public GoalFilter data_name(String s) { return this.addFilter("data_name", s); }
		public GoalFilter last(int i) { return this.addFilter("last", ""+i); }
	}
	public static class DeadlineFilter extends BaseFilter<DeadlineFilter> {
		public DeadlineFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public DeadlineFilter goal_id(int id) { return this.addFilter("goal_id", ""+id); }
		public DeadlineFilter record_type(String s) { return this.addFilter("record_type", s); }
		public DeadlineFilter data_name(String s) { return this.addFilter("data_name", s); }
		public DeadlineFilter status(Deadline.Status s) { return this.addFilter("status", s.toString()); }
		public DeadlineFilter by_date(String s) { return this.addFilter("by_date", s); }
		public DeadlineFilter since_date(String s) { return this.addFilter("since_date", s); }
		public DeadlineFilter include_date(String s) { return this.addFilter("include_date", s); }
		public DeadlineFilter last(int i) { return this.addFilter("last", ""+i); }
	}
	public static class ToDoFilter extends BaseFilter<ToDoFilter> {
		public ToDoFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public ToDoFilter last(int i) { return this.addFilter("last", ""+i); }
		public ToDoFilter fromdate(String s) { return this.addFilter("fromdate", s); }
		public ToDoFilter todate(String s) { return this.addFilter("todate", s); }
	}
	public static class NotificationFilter extends BaseFilter<NotificationFilter> {
		public NotificationFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public NotificationFilter record_type(String s) { return this.addFilter("record_type", s); }
		public NotificationFilter data_name(String s) { return this.addFilter("data_name", s); }
		public NotificationFilter last(int i) { return this.addFilter("last", ""+i); }
	}
	
	/*
	public static class UserFilter extends BaseFilter {
		public UserFilter name(String s) { return this.addFilter("name", s); }
		public UserFilter mail(String s) { return this.addFilter("mail", s); }
		public UserFilter olderthan(String s) { return this.addFilter("olderthan", s); }
		public UserFilter youngerthan(String s) { return this.addFilter("youngerthan", s); }
		public UserFilter bornbeforedate(String s) { return this.addFilter("bornbeforedate", s); }
		public UserFilter bornafterdate(String s) { return this.addFilter("bornafterdate", s); }
		public static class name{}
		public static class mail{}
		public static class olderthan{}
		public static class youngerthan{}
		public static class bornbeforedate{}
		public static class bornafterdate{}		
	}
	public static class RecordFilter {			
		public static class user_id{}
		public static class type{}
		public static class last{}
		public static class fromdate{}
		public static class todate{}
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
