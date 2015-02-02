package lsc.localdatabase.resources;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Goal;
import lsc.localdatabase.dao.model.Notification;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.dao.model.ToDo;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.GoalCollectionRest;
import lsc.localdatabase.rest.model.GoalRest;
import lsc.localdatabase.rest.model.NotificationCollectionRest;
import lsc.localdatabase.rest.model.NotificationRest;
import lsc.localdatabase.rest.model.RecordCollectionRest;
import lsc.localdatabase.rest.model.RecordRest;
import lsc.localdatabase.rest.model.ToDoCollectionRest;
import lsc.localdatabase.rest.model.ToDoRest;
import lsc.localdatabase.rest.model.UserRest;
import lsc.localdatabase.rest.path.PathFactory;
import lsc.localdatabase.utils.MultivaluedMapImpl;


@Stateless
@LocalBean
public class UserResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	int user_id;
	
	public UserResource(UriInfo uriInfo, Request request, int user_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.user_id = user_id;
	}
	
	
	
	// -------
	// user/id
	// -------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public UserRest getById() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("http get "+uriInfo.getPath());
		User user = DaoFactory.user.getById(user_id);
		System.out.println("hi there :"+user.getName());
		
		// Debug for marshalling
		/*
		JAXBContext jc = null;
        Marshaller m = null;
		try {
			jc = JAXBContext.newInstance(UserRest.class);
			System.out.println("1");
			m = jc.createMarshaller();
			System.out.println("2");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			System.out.println("3");
			m.marshal(user, System.out);
			System.out.println("4");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		return ParserFactory.user.toRest( user );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public UserRest put(UserRest user) {
		System.out.println("http put "+uriInfo.getPath());
		User user_dao = ParserFactory.user.toDao(user);
		user_dao.setId(user_id);
		return ParserFactory.user.toRest( DaoFactory.user.update(user_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.user.remove( DaoFactory.user.getById(user_id) );
	}
	
	
	
	// --------------
	// user/id/record
	// --------------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordCollectionRest getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return ParserFactory.record.toRest( DaoFactory.record.getAll( param ) );
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(RecordRest record) {
		System.out.println("http post "+uriInfo.getPath());
		Record record_dao = ParserFactory.record.toDao(record);
		record_dao.setUser( DaoFactory.user.getById(user_id) );
		DaoFactory.record.save(record_dao);
		return Response.created( URI.create( PathFactory.record().id(record_dao.getId()).getCompletePath() ) ).build();
	}
	
	
	
	// ------------
	// user/id/goal
	// ------------
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollectionRest getAllGoals() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return ParserFactory.goal.toRest( DaoFactory.goal.getAll( param ) );
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(GoalRest goal) {
		System.out.println("http post "+uriInfo.getPath());
		Goal goal_dao = ParserFactory.goal.toDao(goal);
		goal_dao.setUser( DaoFactory.user.getById(user_id) );
		DaoFactory.goal.save(goal_dao);
		return Response.created( URI.create( PathFactory.goal().id(goal_dao.getId()).getCompletePath() ) ).build();
	}
	
	
	
	// --------------------
	// user/id/notification
	// --------------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationCollectionRest getAllNotifications() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return ParserFactory.notification.toRest( DaoFactory.notification.getAll( param ) );
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(NotificationRest notification) {
		System.out.println("http post "+uriInfo.getPath());
		Notification notification_dao = ParserFactory.notification.toDao(notification);
		notification_dao.setUser( DaoFactory.user.getById(user_id) );
		DaoFactory.notification.save(notification_dao);
		return Response.created( URI.create( PathFactory.goal().id(notification_dao.getId()).getCompletePath() ) ).build();
	}
	
	
	
	// ------------
	// user/id/todo
	// ------------
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoCollectionRest getAllToDos() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return ParserFactory.todo.toRest( DaoFactory.todo.getAll( param ) );
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(ToDoRest todo) {
		System.out.println("http post "+uriInfo.getPath());
		ToDo todo_dao = ParserFactory.todo.toDao(todo);
		todo_dao.setUser( DaoFactory.user.getById(user_id) );
		DaoFactory.todo.save(todo_dao);
		return Response.created( URI.create( PathFactory.todo().id(todo_dao.getId()).getCompletePath() ) ).build();
	}
	
	
}
