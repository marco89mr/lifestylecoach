package lsc.finalinterface.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.finalinterface.model.Person;


@Stateless
@LocalBean
public class MeasureResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String user;
	String mid;

	
	public MeasureResource(UriInfo uriInfo, Request request, String user, String mid) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.user = user;
		this.mid = mid;
	}
	
	
	// GET
	
	// PUT
}
