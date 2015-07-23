package lsc.storage.resources;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.Filter;
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
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;
import lsc.storage.logic.StorageLogic;
import lsc.storage.parser.StorageParser;


@Stateless
@LocalBean
@Path("/")
public class MainResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	
	@Path("/web")
	@GET
	@Produces({MediaType.TEXT_HTML})
	public InputStream web() throws IOException
	{
		System.out.println("GET /web");
		File f = new File("WebContent/storageWebApp.html");
		return new FileInputStream(f);
	}
	
	
	
	// ----
	// user
	// ----
	
	@Path("/user")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public UserCollection getAllUsers() {
		return StorageLogic.user.getAll(uriInfo);
	}
	
	@Path("/user")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newUser(User user) {
		return StorageLogic.user.post(uriInfo, user);
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
	public RecordComplexCollection getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		Filter.RecordFilter record_filters = Filter.record.addFilter(uriInfo.getQueryParameters());
		RecordCollection recs = LocalDatabaseClient.record.getAll( record_filters );
		RecordComplexCollection recsc = new RecordComplexCollection();
		for(Record r : recs){
			int record_id = r.getId();
			Filter.DataFilter data_filters = Filter.data.record_id(record_id);
			DataCollection datas = LocalDatabaseClient.data.getAll( data_filters );
			RecordComplex rc = StorageParser.record.toRecordComplex(r, datas);
			recsc.add(rc);
		}
		System.out.println("http get end");
		return recsc;
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newRecord(RecordComplex recordComplex) {
		System.out.println("http post "+uriInfo.getPath());
		// record
		Record record = StorageParser.record.toRecord( recordComplex );
		record = LocalDatabaseClient.record.post( record );
		//System.out.println("assigned record id:"+record.getId());
		// data
		recordComplex.setId(record.getId()); //important!!!
		DataCollection datas = StorageParser.record.toData( recordComplex );
		for(Data d : datas)
			LocalDatabaseClient.data.post( d );
		recordComplex = StorageParser.record.toRecordComplex(record, datas);
		URI uri = URI.create( LocalDatabaseClient.record.resource_url() +"/"+ recordComplex.getId() );
		return Response.created(uri).build();
	}
	
	@Path("record/{record_id}")
	public RecordResource getRecord(@PathParam("record_id") int record_id) {
		return new RecordResource(uriInfo, request, record_id);
	}
	
	
	
	// ------
	// data
	// ------
	
	@Path("/data")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DataCollection getAllDatas() {
		return StorageLogic.data.getAll(uriInfo);
	}
	
	
	
	// ----
	// goal
	// ----
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollection getAllGoals() {
		return StorageLogic.goal.getAll(uriInfo);
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newGoal(Goal goal) {
		return StorageLogic.goal.post(uriInfo, goal);
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
		return StorageLogic.deadline.getAll(uriInfo);
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newDeadline(Deadline deadline) {
		return StorageLogic.deadline.post(uriInfo, deadline);
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
		return StorageLogic.notification.getAll(uriInfo);
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newNotification(Notification notification) {
		return StorageLogic.notification.post(uriInfo, notification);
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
		return StorageLogic.todo.getAll(uriInfo);
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newToDo(ToDo todo) {
		return StorageLogic.todo.post(uriInfo, todo);
	}
	
	@Path("todo/{todo_id}")
	public ToDoResource getToDo(@PathParam("todo_id") int todo_id) {
		return new ToDoResource(uriInfo, request, todo_id);
	}
	
}
