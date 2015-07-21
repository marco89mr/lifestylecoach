package lsc.localdatabase.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.NotificationCollectionDao;
import lsc.localdatabase.logic.LocalDatabaseLogic;
import lsc.localdatabase.parser.LocalDatabaseParser;
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
import lsc.utils.MultivaluedMapImpl;


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
		File f = new File("WebContent/localDatabaseWebApp.html");
		return new FileInputStream(f);
	}
	
	
	
	// ----
	// user
	// ----
	
	@Path("/user")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public UserCollection getAllUsers() {
		return LocalDatabaseLogic.user.getAll(uriInfo);
		
		/*
		// Debug for marshalling
		JAXBContext jc = null;
	    Marshaller m = null;
		try {
			jc = JAXBContext.newInstance(UserCollection.class);
			System.out.println("1");
			m = jc.createMarshaller();
			System.out.println("2");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			System.out.println("3");
			m.marshal(list, System.out);
			System.out.println("4");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		*/
	}
	
	@Path("/user")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newUser(User user) {
		return LocalDatabaseLogic.user.post(uriInfo, user);
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
		return LocalDatabaseLogic.record.getAll(uriInfo);
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newRecord(Record record) {
		return LocalDatabaseLogic.record.post(uriInfo, record);
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
		return LocalDatabaseLogic.data.getAll(uriInfo);
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newData(Data data) {
		return LocalDatabaseLogic.data.post(uriInfo, data);
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
		return LocalDatabaseLogic.goal.getAll(uriInfo);
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newGoal(Goal goal) {
		return LocalDatabaseLogic.goal.post(uriInfo, goal);
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
		return LocalDatabaseLogic.deadline.getAll(uriInfo);
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newDeadline(Deadline deadline) {
		return LocalDatabaseLogic.deadline.post(uriInfo, deadline);
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
		

		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		NotificationCollectionDao dao_collection = LocalDatabaseDataAccess.notification.getAll(param);
		System.out.println("A");
		System.out.println(dao_collection.size());
		NotificationCollection model_collection = LocalDatabaseParser.notification.toRest( dao_collection );

		System.out.println("0");
		// Debug for marshalling
		JAXBContext jc = null;
	    Marshaller m = null;
		try {
			jc = JAXBContext.newInstance(NotificationCollection.class);
			System.out.println("1");
			m = jc.createMarshaller();
			System.out.println("2");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			System.out.println("3");
			m.marshal(model_collection, System.out);
			System.out.println("4");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		
		return LocalDatabaseLogic.notification.getAll(uriInfo);
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newNotification(Notification notification) {
		return LocalDatabaseLogic.notification.post(uriInfo, notification);
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
		return LocalDatabaseLogic.todo.getAll(uriInfo);
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newToDo(ToDo todo) {
		return LocalDatabaseLogic.todo.post(uriInfo, todo);
	}
	
	@Path("todo/{todo_id}")
	public ToDoResource getToDo(@PathParam("todo_id") int todo_id) {
		return new ToDoResource(uriInfo, request, todo_id);
	}
	
}
