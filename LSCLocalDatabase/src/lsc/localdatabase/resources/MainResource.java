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

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Data;
import lsc.localdatabase.dao.model.Deadline;
import lsc.localdatabase.dao.model.Goal;
import lsc.localdatabase.dao.model.Notification;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.dao.model.ToDo;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.dao.model.UserCollection;
import lsc.localdatabase.rest.ParserFactory;
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
import lsc.localdatabase.rest.path.PathFactory;


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
	public UserCollectionRest getAllUsers() {
		System.out.println("http get "+uriInfo.getPath());
		UserCollection list = DaoFactory.user.getAll( uriInfo.getQueryParameters() );
		//System.out.println("hi "+list.get(0).getName());
		
		/*
		// Debug for marshalling
		JAXBContext jc = null;
	    Marshaller m = null;
		try {
			jc = JAXBContext.newInstance(UserCollectionRest.class);
			System.out.println("1");
			m = jc.createMarshaller();
			System.out.println("2");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			System.out.println("3");
			m.marshal(lll, System.out);
			System.out.println("4");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		UserCollectionRest collection = ParserFactory.user.toRest( list );
		//System.out.println("hi "+collection.get(0).getName());
		return collection;
	}
	
	@Path("/user")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newUser(UserRest user) {
		System.out.println("http post "+uriInfo.getPath());
		User user_dao = ParserFactory.user.toDao( user );
		DaoFactory.user.save(user_dao);
		UserRest user_rest = ParserFactory.user.toRest( user_dao );
		return Response.created( URI.create( user_rest.getSelfLink().getHref() ) ).build();
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
	public RecordCollectionRest getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.record.toRest( DaoFactory.record.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newRecord(RecordRest record) {
		System.out.println("http post "+uriInfo.getPath());
		Record record_dao = ParserFactory.record.toDao( record );
		DaoFactory.record.save(record_dao);
		return Response.created( URI.create( PathFactory.record().id(record_dao.getId()).getCompletePath() ) ).build();
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
	public DataCollectionRest getAllDatas() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.data.toRest( DaoFactory.data.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newData(DataRest data) {
		System.out.println("http post "+uriInfo.getPath());
		Data data_dao = ParserFactory.data.toDao(data);
		DaoFactory.data.save(data_dao);
		return Response.created( URI.create( PathFactory.data().id(data_dao.getId()).getCompletePath() ) ).build();
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
	public GoalCollectionRest getAllGoals() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.goal.toRest( DaoFactory.goal.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newGoal(GoalRest goal) {
		System.out.println("http post "+uriInfo.getPath());
		Goal goal_dao = ParserFactory.goal.toDao(goal);
		DaoFactory.goal.save(goal_dao);
		return Response.created( URI.create( PathFactory.goal().id(goal_dao.getId()).getCompletePath() ) ).build();
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
	public DeadlineCollectionRest getAllDeadlines() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.deadline.toRest( DaoFactory.deadline.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newDeadline(DeadlineRest deadline) {
		System.out.println("http post "+uriInfo.getPath());
		Deadline deadline_dao = ParserFactory.deadline.toDao(deadline);
		DaoFactory.deadline.save(deadline_dao);
		return Response.created( URI.create( PathFactory.deadline().id(deadline_dao.getId()).getCompletePath() ) ).build();
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
	public NotificationCollectionRest getAllNotifications() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.notification.toRest( DaoFactory.notification.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newNotification(NotificationRest notification) {
		System.out.println("http post "+uriInfo.getPath());
		Notification notification_dao = ParserFactory.notification.toDao(notification);
		DaoFactory.notification.save(notification_dao);
		return Response.created( URI.create( PathFactory.notification().id(notification_dao.getId()).getCompletePath() ) ).build();
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
	public ToDoCollectionRest getAllToDos() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.todo.toRest( DaoFactory.todo.getAll( uriInfo.getQueryParameters() ) );
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newToDo(ToDoRest todo) {
		System.out.println("http post "+uriInfo.getPath());
		ToDo todo_dao = ParserFactory.todo.toDao(todo);
		DaoFactory.todo.save(todo_dao);
		return Response.created( URI.create( PathFactory.todo().id(todo_dao.getId()).getCompletePath() ) ).build();
	}
	
	@Path("todo/{todo_id}")
	public ToDoResource getToDo(@PathParam("todo_id") int todo_id) {
		return new ToDoResource(uriInfo, request, todo_id);
	}
	
}
