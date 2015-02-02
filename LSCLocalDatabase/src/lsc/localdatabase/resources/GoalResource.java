package lsc.localdatabase.resources;

import java.net.URI;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Deadline;
import lsc.localdatabase.dao.model.Goal;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.DeadlineCollectionRest;
import lsc.localdatabase.rest.model.DeadlineRest;
import lsc.localdatabase.rest.model.GoalRest;
import lsc.localdatabase.rest.path.PathFactory;
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
	public GoalRest getById() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.goal.toRest( DaoFactory.goal.getById(goal_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public GoalRest put(GoalRest goal) {
		System.out.println("http put "+uriInfo.getPath());
		Goal goal_dao = ParserFactory.goal.toDao(goal);
		goal_dao.setId(goal_id);
		return ParserFactory.goal.toRest( DaoFactory.goal.update(goal_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.goal.remove( DaoFactory.goal.getById(goal_id) );
	}
	
	
	
	// ----------------
	// goal/id/deadline
	// ----------------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineCollectionRest getAll() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("goal_id", String.valueOf(goal_id) );
		return ParserFactory.deadline.toRest( DaoFactory.deadline.getAll( param ) );
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(DeadlineRest deadline) {
		System.out.println("http post "+uriInfo.getPath());
		Deadline deadline_dao = ParserFactory.deadline.toDao(deadline);
		deadline_dao.setGoal( DaoFactory.goal.getById(goal_id) );
		DaoFactory.deadline.save(deadline_dao);
		return Response.created( URI.create( PathFactory.deadline().id(deadline_dao.getId()).getCompletePath() ) ).build();
	}
	
	
}
