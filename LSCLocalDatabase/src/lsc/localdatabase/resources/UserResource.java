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

import lsc.localdatabase.dao.GoalDao;
import lsc.localdatabase.dao.NotificationDao;
import lsc.localdatabase.dao.RecordDao;
import lsc.localdatabase.dao.ToDoDao;
import lsc.localdatabase.dao.UserDao;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalCollection;
import lsc.localdatabase.model.Notification;
import lsc.localdatabase.model.NotificationCollection;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.RecordCollection;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoCollection;
import lsc.localdatabase.model.User;
import lsc.localdatabase.parser.Parser;
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
	public User getById() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("http get "+uriInfo.getPath());
		//return User.getById(user_id);
		return Parser.generate( UserDao.getById(User.class, user_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public User put(User user) {
		System.out.println("http put "+uriInfo.getPath());
		user.setId(user_id);
		Parser.parse(user);
		Parser.generate(user);
		return UserDao.update(user);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		UserDao.remove( UserDao.getById(user_id) );
	}
	
	
	
	// --------------
	// user/id/record
	// --------------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordCollection getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Parser.generate( RecordDao.getAll( param ) );
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Record record) {
		System.out.println("http post "+uriInfo.getPath());
		record.setUser( UserDao.getById(user_id) );
		record = Parser.parse(record);
		record = Parser.generate(record);
		record = RecordDao.save(record);
		return Response.created( URI.create( record._getUrl() ) ).build();
	}
	
	
	
	// ------------
	// user/id/goal
	// ------------
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollection getAllGoals() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Parser.generate( GoalDao.getAll( param ) );
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Goal goal) {
		System.out.println("http post "+uriInfo.getPath());
		goal.setUser( UserDao.getById(user_id) );
		goal = Parser.parse(goal);
		goal = Parser.generate(goal);
		goal = GoalDao.save(goal);
		return Response.created( URI.create( goal._getUrl() ) ).build();
	}
	
	
	
	// --------------------
	// user/id/notification
	// --------------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationCollection getAllNotifications() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Parser.generate( NotificationDao.getAll( param ) );
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Notification notification) {
		System.out.println("http post "+uriInfo.getPath());
		notification.setUser( UserDao.getById(user_id) );
		notification = Parser.parse(notification);
		notification = Parser.generate(notification);
		notification = NotificationDao.save(notification);
		return Response.created( URI.create( notification._getUrl() ) ).build();
	}
	
	
	
	// ------------
	// user/id/todo
	// ------------
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoCollection getAllToDos() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Parser.generate( ToDoDao.getAll( param ) );
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(ToDo todo) {
		System.out.println("http post "+uriInfo.getPath());
		todo.setUser( UserDao.getById(user_id) );
		todo = Parser.parse(todo);
		todo = Parser.generate(todo);
		todo = ToDoDao.save(todo);
		return Response.created( URI.create( todo._getUrl() ) ).build();
	}
	
	
}
