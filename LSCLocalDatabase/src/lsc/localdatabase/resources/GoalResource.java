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

import lsc.localdatabase.dao.DeadlineDao;
import lsc.localdatabase.dao.GoalDao;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineCollection;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.parser.Parser;
import lsc.localdatabase.utils.MultivaluedMapImpl;


@Stateless
@LocalBean
public class GoalResource {

	@Context UriInfo uriInfo;
	@Context Request request;
	int goal_id;
	
	public GoalResource(UriInfo uriInfo, Request request, int goal_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.goal_id = goal_id;
	}
	
	
	
	// -------
	// goal/id
	// -------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Goal getById() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( GoalDao.getById(goal_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Goal put(Goal goal) {
		System.out.println("http put "+uriInfo.getPath());
		goal.setId(goal_id);
		Parser.parse(goal);
		Parser.generate(goal);
		return GoalDao.update(goal);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		GoalDao.remove( GoalDao.getById(goal_id) );
	}
	
	
	
	// ----------------
	// goal/id/deadline
	// ----------------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineCollection getAll() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("goal_id", String.valueOf(goal_id) );
		return Parser.generate( DeadlineDao.getAll( param ) );
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Deadline deadline) {
		System.out.println("http post "+uriInfo.getPath());
		deadline.setGoal( GoalDao.getById(goal_id) );
		deadline = DeadlineDao.save(deadline);
		deadline = Parser.parse(deadline);
		deadline = Parser.generate(deadline);
		return Response.created( URI.create( deadline._getUrl() ) ).build();
	}
	
	
}
