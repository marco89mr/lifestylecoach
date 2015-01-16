package lsc.localdatabase.resources;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import lsc.localdatabase.dao.DataDao;
import lsc.localdatabase.dao.DeadlineDao;
import lsc.localdatabase.dao.GoalDao;
import lsc.localdatabase.dao.NotificationDao;
import lsc.localdatabase.dao.RecordDao;
import lsc.localdatabase.dao.ToDoDao;
import lsc.localdatabase.dao.UserDao;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataCollection;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineCollection;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalCollection;
import lsc.localdatabase.model.Notification;
import lsc.localdatabase.model.NotificationCollection;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.RecordCollection;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoCollection;
import lsc.localdatabase.model.User;
import lsc.localdatabase.model.UserCollection;
import lsc.localdatabase.parser.Parser;


@Stateless
@LocalBean
@Path("/")
public class MainResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	
	// ----
	// user
	// ----
	
	@Path("/user")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public UserCollection getAllUsers() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( UserDao.getAll( uriInfo.getQueryParameters() ) );
	}
	
	@Path("/user")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newUser(User user) {
		System.out.println("http post "+uriInfo.getPath());
		user = Parser.parse(user);
		user = Parser.generate(user);
		user = UserDao.save(user);
		return Response.created( URI.create( user._getUrl() ) ).build();
	}
	
	@Path("/user/{user_id}")
	public UserResource getUser(@PathParam("user_id") int user_id) {
		return new UserResource(uriInfo, request, user_id);
	}
	
	
	
	// ------
	// record
	// ------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordCollection getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( RecordDao.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newRecord(Record record) {
		System.out.println("http post "+uriInfo.getPath());
		record = Parser.parse(record);
		record = Parser.generate(record);
		record = RecordDao.save(record);
		return Response.created( URI.create( record._getUrl() ) ).build();
	}
	
	@Path("record/{record_id}")
	public RecordResource getRecord(@PathParam("record_id") int record_id) {
		return new RecordResource(uriInfo, request, record_id);
	}
	
	
	
	// ----
	// data
	// ----
	
	@Path("/data")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DataCollection getAllDatas() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( DataDao.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newData(Data data) {
		System.out.println("http post "+uriInfo.getPath());
		data = Parser.parse(data);
		data = Parser.generate(data);
		data = DataDao.save(data);
		return Response.created( URI.create( data._getUrl() ) ).build();
	}
	
	@Path("data/{data_id}")
	public DataResource getData(@PathParam("data_id") int data_id) {
		return new DataResource(uriInfo, request, data_id);
	}
	
	
	
	// ----
	// goal
	// ----
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollection getAllGoals() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( GoalDao.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newGoal(Goal goal) {
		System.out.println("http post "+uriInfo.getPath());
		goal = Parser.parse(goal);
		goal = Parser.generate(goal);
		goal = GoalDao.save(goal);
		return Response.created( URI.create( goal._getUrl() ) ).build();
	}
	
	@Path("goal/{goal_id}")
	public GoalResource getGoal(@PathParam("goal_id") int goal_id) {
		return new GoalResource(uriInfo, request, goal_id);
	}
	
	
	
	// --------
	// deadline
	// --------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineCollection getAllDeadlines() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( DeadlineDao.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newDeadline(Deadline deadline) {
		System.out.println("http post "+uriInfo.getPath());
		deadline = Parser.parse(deadline);
		deadline = Parser.generate(deadline);
		deadline = DeadlineDao.save(deadline);
		return Response.created( URI.create( deadline._getUrl() ) ).build();
	}
	
	@Path("deadline/{deadline_id}")
	public DeadlineResource getDeadline(@PathParam("deadline_id") int deadline_id) {
		return new DeadlineResource(uriInfo, request, deadline_id);
	}
	
	
	
	// ------------
	// notification
	// ------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationCollection getAllNotifications() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( NotificationDao.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newNotification(Notification notification) {
		System.out.println("http post "+uriInfo.getPath());
		notification = Parser.parse(notification);
		notification = Parser.generate(notification);
		notification = NotificationDao.save(notification);
		return Response.created( URI.create( notification._getUrl() ) ).build();
	}
	
	@Path("notification/{notification_id}")
	public NotificationResource getNotification(@PathParam("notification_id") int notification_id) {
		return new NotificationResource(uriInfo, request, notification_id);
	}
	
	
	
	// ----
	// todo
	// ----
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoCollection getAllToDos() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( ToDoDao.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newToDo(ToDo todo) {
		System.out.println("http post "+uriInfo.getPath());
		todo = Parser.parse(todo);
		todo = Parser.generate(todo);
		todo = ToDoDao.save(todo);
		return Response.created( URI.create( todo._getUrl() ) ).build();
	}
	
	@Path("todo/{todo_id}")
	public ToDoResource getToDo(@PathParam("todo_id") int todo_id) {
		return new ToDoResource(uriInfo, request, todo_id);
	}
	
}
