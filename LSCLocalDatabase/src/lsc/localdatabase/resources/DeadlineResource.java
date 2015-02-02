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

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Deadline;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.DeadlineRest;


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
	public DeadlineRest getById() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.deadline.toRest( DaoFactory.deadline.getById(deadline_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineRest put(DeadlineRest deadline) {
		System.out.println("http put "+uriInfo.getPath());
		Deadline deadline_dao = ParserFactory.deadline.toDao(deadline);
		deadline_dao.setId(deadline_id);
		return ParserFactory.deadline.toRest( DaoFactory.deadline.update(deadline_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.deadline.remove( DaoFactory.deadline.getById(deadline_id) );
	}
	
	
}
