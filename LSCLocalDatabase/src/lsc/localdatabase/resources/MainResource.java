package lsc.localdatabase.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import lsc.localdatabase.App;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataList;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineList;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalList;
import lsc.localdatabase.model.Notification;
import lsc.localdatabase.model.NotificationList;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.RecordList;
import lsc.localdatabase.model.TableEntity;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoList;
import lsc.localdatabase.model.User;
import lsc.localdatabase.model.UserList;
import lsc.localdatabase.utils.MultivaluedMapImpl;


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
	public UserList getAllUsers() {
		System.out.println("http get "+uriInfo.getPath());
		return User.getAll( uriInfo.getQueryParameters() );
	}
	
	@Path("/user")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newUser(User user) {
		System.out.println("http post "+uriInfo.getPath());
		user = User.save(user);
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
	public RecordList getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		return Record.getAll( uriInfo.getQueryParameters() );
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newRecord(Record record) {
		System.out.println("http post "+uriInfo.getPath());
		record = Record.save(record);
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
	public DataList getAllDatas() {
		System.out.println("http get "+uriInfo.getPath());
		return Data.getAll( uriInfo.getQueryParameters() );
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newData(Data data) {
		System.out.println("http post "+uriInfo.getPath());
		data = Data.save(data);
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
	public GoalList getAllGoals() {
		System.out.println("http get "+uriInfo.getPath());
		return Goal.getAll( uriInfo.getQueryParameters() );
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newGoal(Goal goal) {
		System.out.println("http post "+uriInfo.getPath());
		goal = Goal.save(goal);
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
	public DeadlineList getAllDeadlines() {
		System.out.println("http get "+uriInfo.getPath());
		return Deadline.getAll( uriInfo.getQueryParameters() );
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newDeadline(Deadline deadline) {
		System.out.println("http post "+uriInfo.getPath());
		deadline = Deadline.save(deadline);
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
	public NotificationList getAllNotifications() {
		System.out.println("http get "+uriInfo.getPath());
		return Notification.getAll( uriInfo.getQueryParameters() );
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newNotification(Notification notification) {
		System.out.println("http post "+uriInfo.getPath());
		notification = Notification.save(notification);
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
	public ToDoList getAllToDos() {
		System.out.println("http get "+uriInfo.getPath());
		return ToDo.getAll( uriInfo.getQueryParameters() );
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newToDo(ToDo todo) {
		System.out.println("http post "+uriInfo.getPath());
		todo = ToDo.save(todo);
		return Response.created( URI.create( todo._getUrl() ) ).build();
	}
	
	@Path("todo/{todo_id}")
	public ToDoResource getToDo(@PathParam("todo_id") int todo_id) {
		return new ToDoResource(uriInfo, request, todo_id);
	}
	
}
