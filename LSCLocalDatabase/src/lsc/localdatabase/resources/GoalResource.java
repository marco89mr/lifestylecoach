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
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineList;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalList;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.RecordList;
import lsc.localdatabase.model.User;
import lsc.localdatabase.model.UserList;
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
		return Goal.getById(goal_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Goal put(Goal goal) {
		System.out.println("http put "+uriInfo.getPath());
		goal.setId(goal_id);
		return Goal.update(goal);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		Goal.remove( Goal.getById(goal_id) );
	}
	
	
	
	// ----------------
	// goal/id/deadline
	// ----------------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineList getAll() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("goal_id", String.valueOf(goal_id) );
		return Deadline.getAll( param );
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Deadline deadline) {
		System.out.println("http post "+uriInfo.getPath());
		deadline.setGoal( Goal.getById(goal_id) );
		deadline = Deadline.save(deadline);
		return Response.created( URI.create( deadline._getUrl() ) ).build();
	}
	
	
}
