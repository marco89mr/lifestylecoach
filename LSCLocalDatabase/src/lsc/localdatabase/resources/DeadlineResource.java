package lsc.localdatabase.resources;

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

import lsc.localdatabase.logic.LocalDatabaseLogic;
import lsc.rest.model.Deadline;


@Stateless
@LocalBean
public class DeadlineResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int deadline_id;
	
	public DeadlineResource(UriInfo uriInfo, Request request, int deadline_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.deadline_id = deadline_id;
	}
	
	
	
	// ------------
	// deadline/{id}
	// ------------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Deadline getById() {
		return LocalDatabaseLogic.deadline.getById(uriInfo, deadline_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Deadline put(Deadline deadline) {
		return LocalDatabaseLogic.deadline.put(uriInfo, deadline, deadline_id);
	}
	
	@DELETE
	public void delete() {
		LocalDatabaseLogic.deadline.delete(uriInfo, deadline_id);
	}
	
	
}
