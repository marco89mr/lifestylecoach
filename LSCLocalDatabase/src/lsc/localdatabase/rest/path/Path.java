package lsc.localdatabase.rest.path;

import java.util.HashMap;
import java.util.Map;


public class Path {
	
	
	
	
	
	public static abstract class BasePath {
		
		protected String completePath;
		
		public String getCompletePath() {
			return completePath;
		}
		
		@SuppressWarnings("unchecked")
		protected <T extends BasePath> T setCompletePath(String p) {
			completePath = p;
			return (T) this;
		}
		
	}
	
	
	
	
	
	public static abstract class BaseFilter extends BasePath {
		
		protected Map<String,String> filter = new HashMap<String,String>();
		
		@SuppressWarnings("unchecked")
		public <T extends BaseFilter> T addFilter(String key, String value) {
			filter.put(key, value);
			return (T) this;
		}
		
		
		
		public String getCompletePath() {
			if(filter.isEmpty())
				return completePath;
			String param = "?";
			for(String key : filter.keySet()){
				param = param + key + "=" + filter.get(key) + "&";
			}
			return completePath + param;
		}
		
		
	}
	
	
	
	
	
	
	public static class UserPath extends BasePath {			
	}
	public static class RecordPath extends BasePath {			
	}
	public static class DataPath extends BasePath {			
	}
	public static class GoalPath extends BasePath {			
	}
	public static class DeadlinePath extends BasePath {			
	}
	public static class ToDoPath extends BasePath {			
	}
	public static class NotificationPath extends BasePath {			
	}
	
		
	public static class UserFilter extends BaseFilter {			
		public UserFilter name(String s) { return this.addFilter("name", s); }
		public UserFilter mail(String s) { return this.addFilter("mail", s); }
		public UserFilter olderthan(String s) { return this.addFilter("olderthan", s); }
		public UserFilter youngerthan(String s) { return this.addFilter("youngerthan", s); }
		public UserFilter bornbeforedate(String s) { return this.addFilter("bornbeforedate", s); }
		public UserFilter bornafterdate(String s) { return this.addFilter("bornafterdate", s); }		
	}
	public static class RecordFilter extends BaseFilter {			
		public RecordFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public RecordFilter type(String s) { return this.addFilter("type", s); }
		public RecordFilter last(int i) { return this.addFilter("last", ""+i); }
		public RecordFilter fromdate(String s) { return this.addFilter("fromdate", s); }
		public RecordFilter todate(String s) { return this.addFilter("todate", s); }		
	}
	public static class DataFilter extends BaseFilter {			
		public DataFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public DataFilter record_id(int id) { return this.addFilter("record_id", ""+id); }
		public DataFilter record_type(String s) { return this.addFilter("record_type", s); }
		public DataFilter data_name(String s) { return this.addFilter("data_name", s); }
		public DataFilter last(int i) { return this.addFilter("last", ""+i); }
		public DataFilter fromdate(String s) { return this.addFilter("fromdate", s); }
		public DataFilter todate(String s) { return this.addFilter("todate", s); }		
	}
	public static class GoalFilter extends BaseFilter {			
		public GoalFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public GoalFilter record_type(String s) { return this.addFilter("record_type", s); }
		public GoalFilter data_name(String s) { return this.addFilter("data_name", s); }
		public GoalFilter last(int i) { return this.addFilter("last", ""+i); }		
	}
	public static class DeadlineFilter extends BaseFilter {			
		public DeadlineFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public DeadlineFilter goal_id(int id) { return this.addFilter("goal_id", ""+id); }
		public DeadlineFilter data_name(String s) { return this.addFilter("data_name", s); }
		public DeadlineFilter last(int i) { return this.addFilter("last", ""+i); }		
	}
	public static class ToDoFilter extends BaseFilter {			
		public ToDoFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public ToDoFilter type(String s) { return this.addFilter("type", s); }
		public ToDoFilter last(int i) { return this.addFilter("last", ""+i); }
		public ToDoFilter fromdate(String s) { return this.addFilter("fromdate", s); }
		public ToDoFilter todate(String s) { return this.addFilter("todate", s); }		
	}
	public static class NotificationFilter extends BaseFilter {			
		public NotificationFilter user_id(int id) { return this.addFilter("user_id", ""+id); }
		public NotificationFilter record_type(String s) { return this.addFilter("record_type", s); }
		public NotificationFilter data_name(String s) { return this.addFilter("data_name", s); }
		public NotificationFilter last(int i) { return this.addFilter("last", ""+i); }		
	}
	
	
	
	
}
