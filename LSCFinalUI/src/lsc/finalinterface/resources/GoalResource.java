package lsc.finalinterface.resources;

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
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.finalinterface.logic.FinalInterfaceLogic;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.rest.model.Goal;


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
		return FinalInterfaceLogic.goal.getById(uriInfo, goal_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Goal put(Goal goal) {
		return FinalInterfaceLogic.goal.put( uriInfo, goal, goal_id );
	}
	
	@DELETE
	public void delete() {
		FinalInterfaceLogic.goal.delete( uriInfo, goal_id );
	}
	
	
	
	// ----------------
	// goal/id/deadline
	// ----------------
	
	@Path("/deadline")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DeadlineCollection getAll() {
		return FinalInterfaceLogic.deadline.getAllUnderGoal(uriInfo, goal_id);
	}

	@Path("/deadline")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Deadline deadline) {
		deadline.setGoalId(goal_id);
		return FinalInterfaceLogic.deadline.post(uriInfo, deadline);
	}
	
	
}
