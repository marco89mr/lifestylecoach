package lsc.finalinterface.resources;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.glassfish.jersey.client.ClientConfig;

import lsc.businesslogic.client.BusinessLogicClient;
import lsc.businesslogic.client.JaxWsHandlerResolver;
import lsc.businesslogic.ws.LSCLogic;
import lsc.finalinterface.logic.BaseLogic;
import lsc.finalinterface.logic.FinalInterfaceLogic;
import lsc.rest.client.ClientUtils;
import lsc.rest.filter.Filter;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;
import lsc.storage.rest.client.StorageClient;


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
	public User getById() {
		return FinalInterfaceLogic.user.getById(uriInfo, user_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public User put(User user) {
		return FinalInterfaceLogic.user.put( uriInfo, user, user_id );
	}
	
	@DELETE
	public void delete() {
		FinalInterfaceLogic.user.delete( uriInfo, user_id );
	}
	
	
	
	// --------------
	// user/id/record
	// --------------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordComplexCollection getAllRecords() {
		return FinalInterfaceLogic.record.getAllUnderUser(uriInfo, user_id);
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(RecordComplex recordComplex) {
		recordComplex.setUserId(user_id);
		return FinalInterfaceLogic.record.post(uriInfo, recordComplex);
	}
	
	
	
	// ------------
	// user/id/goal
	// ------------
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollection getAllGoals() {
		return FinalInterfaceLogic.goal.getAllUnderUser(uriInfo, user_id);
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Goal goal) {
		goal.setUserId(user_id);
		return FinalInterfaceLogic.goal.post(uriInfo, goal);
	}
	
	
	
	// ----------------
	// user/id/deadline
	// ----------------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineCollection getAllDeadlines() {
		return FinalInterfaceLogic.deadline.getAllUnderUser(uriInfo, user_id);
	}
	
	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Deadline deadline) {
		return FinalInterfaceLogic.deadline.post(uriInfo, deadline);
	}
	
	
	
	// --------------------
	// user/id/notification
	// --------------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationCollection getAllNotifications() {
		
		/*
		String requestUrl = StorageClient.user.resource_url();
		// build end-point link
		URI uri = UriBuilder.fromUri( requestUrl ).build();
		// set up client
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget service = client.target( uri );
		Builder builder = service.request().accept(MediaType.APPLICATION_XML);
		Response response = builder.get();
		
		UserCollection uu = response.readEntity( UserCollection.class );
		response.close();
		
		//UserCollection uu = StorageClient.user.getAll( Filter.user.name("marco") );
		
		NotificationCollection nn = new NotificationCollection();
		System.out.println("DEBUG A");
		LSCLogic lscLogic = BusinessLogicClient.init();
		System.out.println("DEBUG B");
		nn = lscLogic.checkToday(user_id);
		System.out.println("DEBUG C");
    	return nn;
		*/
		
		return FinalInterfaceLogic.notification.getAllUnderUser(uriInfo, user_id);
	}
	
	/*
	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Notification notification) {
		notification.setUserId(user_id);
		return FinalInterfaceLogic.notification.post(uriInfo, notification);
	}
	*/
	
	
	
	// ------------
	// user/id/todo
	// ------------
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoCollection getAllToDos() {
		return FinalInterfaceLogic.todo.getAllUnderUser(uriInfo, user_id);
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(ToDo todo) {
		todo.setUserId(user_id);
		return FinalInterfaceLogic.todo.post(uriInfo, todo);
	}
	
	
}

	

