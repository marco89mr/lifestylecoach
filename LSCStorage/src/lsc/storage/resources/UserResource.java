package lsc.storage.resources;

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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.RecordFilter;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
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
import lsc.storage.logic.StorageLogic;
import lsc.storage.parser.StorageParser;


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
		return StorageLogic.user.getById(uriInfo, user_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public User put(User user) {
		return StorageLogic.user.put( uriInfo, user, user_id );
	}
	
	@DELETE
	public void delete() {
		StorageLogic.user.delete( uriInfo, user_id );
	}
	
	
	
	// --------------
	// user/id/record
	// --------------
	
	@Path("/record")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordComplexCollection getAllRecords() {
		System.out.println("http get "+uriInfo.getPath());
		RecordFilter record_filters = Filter.record()
								.user_id( user_id )
								.addFilter( uriInfo.getQueryParameters() );
		RecordCollection recs = LocalDatabaseClient.record.getAll( record_filters );
		RecordComplexCollection recsc = new RecordComplexCollection();
		for(Record r : recs){
			int id = r.getId();
			Filter.DataFilter filter = Filter.data().user_id(id);
			DataCollection datas = LocalDatabaseClient.data.getAll( filter );
			RecordComplex rc = StorageParser.record.toRecordComplex(r, datas);
			recsc.add(rc);
		}
		return recsc;
	}

	@Path("/record")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(RecordComplex recordComplex) {
		System.out.println("http post "+uriInfo.getPath());
		// record
		recordComplex.setUserId( user_id );
		Record record = StorageParser.record.toRecord( recordComplex );
		LocalDatabaseClient.record.post( record );
		// data
		DataCollection datas = StorageParser.record.toData( recordComplex );
		for(Data d : datas)
			LocalDatabaseClient.data.post( d );
		recordComplex = StorageParser.record.toRecordComplex(record, datas);
		URI uri = URI.create( LocalDatabaseClient.record.resource_url() +"/"+ recordComplex.getId() );
		return Response.created(uri).build();
	}
	
	
	
	// ------------
	// user/id/goal
	// ------------
	
	@Path("/goal")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalCollection getAllGoals() {
		return StorageLogic.goal.getAllUnderUser(uriInfo, user_id);
	}

	@Path("/goal")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Goal goal) {
		goal.setUserId(user_id);
		return StorageLogic.goal.post(uriInfo, goal);
	}
	
	
	
	// --------------------
	// user/id/notification
	// --------------------
	
	@Path("/notification")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationCollection getAllNotifications() {
		return StorageLogic.notification.getAllUnderUser(uriInfo, user_id);
	}

	@Path("/notification")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Notification notification) {
		notification.setUserId(user_id);
		return StorageLogic.notification.post(uriInfo, notification);
	}
	
	
	
	// ------------
	// user/id/todo
	// ------------
	
	@Path("/todo")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoCollection getAllToDos() {
		return StorageLogic.todo.getAllUnderUser(uriInfo, user_id);
	}

	@Path("/todo")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(ToDo todo) {
		todo.setUserId(user_id);
		return StorageLogic.todo.post(uriInfo, todo);
	}
	
	
}
