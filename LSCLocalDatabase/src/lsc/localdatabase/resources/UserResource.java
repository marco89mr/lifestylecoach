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
		return User.getById(User.class, user_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public User put(User user) {
		System.out.println("http put "+uriInfo.getPath());
		user.setId(user_id);
		return User.update(user);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		User.remove( User.getById(user_id) );
	}
	
	
	
	// --------------
	// user/id/record
	// --------------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordList getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Record.getAll( param );
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Record record) {
		System.out.println("http post "+uriInfo.getPath());
		record.setUser( User.getById(user_id) );
		record = Record.save(record);
		return Response.created( URI.create( record._getUrl() ) ).build();
	}
	
	
	
	// ------------
	// user/id/goal
	// ------------
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalList getAllGoals() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Goal.getAll( param );
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Goal goal) {
		System.out.println("http post "+uriInfo.getPath());
		goal.setUser( User.getById(user_id) );
		goal = Goal.save(goal);
		return Response.created( URI.create( goal._getUrl() ) ).build();
	}
	
	
	
	// --------------------
	// user/id/notification
	// --------------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationList getAllNotifications() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return Notification.getAll( param );
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Notification notification) {
		System.out.println("http post "+uriInfo.getPath());
		notification.setUser( User.getById(user_id) );
		notification = Notification.save(notification);
		return Response.created( URI.create( notification._getUrl() ) ).build();
	}
	
	
	
	// ------------
	// user/id/todo
	// ------------
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoList getAllToDos() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("user_id", String.valueOf(user_id) );
		return ToDo.getAll( param );
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(ToDo todo) {
		System.out.println("http post "+uriInfo.getPath());
		todo.setUser( User.getById(user_id) );
		todo = ToDo.save(todo);
		return Response.created( URI.create( todo._getUrl() ) ).build();
	}
	
	
}
