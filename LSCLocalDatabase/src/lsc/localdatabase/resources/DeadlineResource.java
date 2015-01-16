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

import lsc.localdatabase.dao.DeadlineDao;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.parser.Parser;


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
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( DeadlineDao.getById(deadline_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Deadline put(Deadline deadline) {
		System.out.println("http put "+uriInfo.getPath());
		deadline.setId(deadline_id);
		Parser.parse(deadline);
		Parser.generate(deadline);
		return DeadlineDao.update(deadline);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DeadlineDao.remove( DeadlineDao.getById(deadline_id) );
	}
	
	
}
