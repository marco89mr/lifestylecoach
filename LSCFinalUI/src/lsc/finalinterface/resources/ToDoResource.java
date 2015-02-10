package lsc.finalinterface.resources;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import lsc.finalinterface.logic.FinalInterfaceLogic;
import lsc.rest.model.ToDo;


@Stateless
@LocalBean
public class ToDoResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int todo_id;
	
	public ToDoResource(UriInfo uriInfo, Request request, int todo_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.todo_id = todo_id;
	}
	
	
	
	// ---------
	// todo/{id}
	// ---------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDo getById() {
		return FinalInterfaceLogic.todo.getById(uriInfo, todo_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDo put(ToDo todo) {
		return FinalInterfaceLogic.todo.put( uriInfo, todo, todo_id );
	}
	
	@DELETE
	public void delete() {
		FinalInterfaceLogic.todo.delete( uriInfo, todo_id );
	}
	
	
}
