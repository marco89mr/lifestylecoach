package lsc.finalinterface.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.finalinterface.model.Person;


@Stateless
@LocalBean
public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String user;

	
	public UserResource(UriInfo uriInfo, Request request, String user) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.user = user;
	}
	
	
	// GET
	
	
	// DELETE
	
	
	// PUT
	
	
	
	// Allows to type http://localhost:xxxx/user/#uid/ ...
	
	@Path("measure")
	public void getMeasures() {
		new MeasureCollectionResource(uriInfo, request, user);
	}
	
	@Path("goal")
	public void getGoals() {
		new GoalCollectionResource(uriInfo, request, user);
	}
	
	@Path("deadline")
	public void Deadlines() {
		new DeadlineCollectionResource(uriInfo, request, user);
	}
	
	@Path("notification")
	public void getNotifications() {
		new NotificationCollectionResource(uriInfo, request, user);
	}
	
	@Path("todo")
	public void getToDos()) {
		new ToDoCollectionResource(uriInfo, request, user);
	}
}
