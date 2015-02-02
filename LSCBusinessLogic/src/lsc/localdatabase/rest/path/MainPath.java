package lsc.localdatabase.rest.path;

import lsc.localdatabase.rest.path.Path.BasePath;
import lsc.localdatabase.rest.path.Path.DataFilter;
import lsc.localdatabase.rest.path.Path.DataPath;
import lsc.localdatabase.rest.path.Path.DeadlineFilter;
import lsc.localdatabase.rest.path.Path.DeadlinePath;
import lsc.localdatabase.rest.path.Path.GoalFilter;
import lsc.localdatabase.rest.path.Path.GoalPath;
import lsc.localdatabase.rest.path.Path.NotificationFilter;
import lsc.localdatabase.rest.path.Path.NotificationPath;
import lsc.localdatabase.rest.path.Path.RecordFilter;
import lsc.localdatabase.rest.path.Path.ToDoFilter;
import lsc.localdatabase.rest.path.Path.ToDoPath;
import lsc.localdatabase.rest.path.Path.UserFilter;
import lsc.localdatabase.rest.path.Path.UserPath;


public class MainPath extends BasePath {
	
	
	
	
	public User user() { return new User().setCompletePath(completePath + "/user"); }
	public static class User extends UserFilter {
		
		//public UserFilter filter() { return new UserFilter().setCompletePath(completePath); }
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends UserPath {
			
			public Record record() { return new Record().setCompletePath(completePath + "/record");	}
			public class Record extends RecordFilter {
			}
			
			public Goal goal() { return new Goal().setCompletePath(completePath + "/goal");	}
			public class Goal extends GoalFilter {
			}
			
			public ToDo todo() { return new ToDo().setCompletePath(completePath + "/todo");	}
			public class ToDo extends ToDoFilter {
			}
			
			public Notification notification() { return new Notification().setCompletePath(completePath + "/notification"); }
			public class Notification extends NotificationFilter {
			}
			
		}
		
	}
	
	public Record record() { return new Record().setCompletePath(completePath + "/record"); }
	public static class Record extends RecordFilter {
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends RecordFilter {
			
			public Data data() { return new Data().setCompletePath(completePath + "/data");	}
			public class Data extends DataFilter {
			}
			
		}
		
	}
	
	public Data data() { return new Data().setCompletePath(completePath + "/data"); }
	public static class Data extends DataFilter {
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends DataPath {
		}
		
		
	}
	
	public Goal goal() { return new Goal().setCompletePath(completePath + "/goal"); }
	public static class Goal extends GoalFilter {
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends GoalPath {
			
			public Deadline deadline() { return new Deadline().setCompletePath(completePath + "/deadline");	}
			public class Deadline extends DeadlineFilter {
			}
			
		}
		
	}
	
	public Deadline deadline() { return new Deadline().setCompletePath(completePath + "/deadline"); }
	public static class Deadline extends DeadlineFilter {
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends DeadlinePath {
		}
		
	}
	
	public ToDo todo() { return new ToDo().setCompletePath(completePath); }
	public static class ToDo extends ToDoFilter {
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends ToDoPath {
		}
		
	}
	
	public Notification notification() { return new Notification().setCompletePath(completePath + "/notification"); }
	public static class Notification extends NotificationFilter {
		
		public Id id(int id) { return new Id().setCompletePath(completePath + "/" + id); }
		public class Id extends NotificationPath {
		}
		
	}
	
	
	
	
	
	
}
