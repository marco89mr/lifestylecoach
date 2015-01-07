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
public class MeasureCollectionResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String user;
	
	public MeasureCollectionResource(UriInfo uriInfo, Request request, String user) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.user = user;
	}
	
	
	// POST
	
	
	
	// Allows to type http://localhost:xxxx/user/#uid/measure/ ...
	
	@Path("mid/{mid}")
	public void getById(@PathParam("mid") String mid) {
		new MeasureResource(uriInfo, request, user, mid);
	}
	
	@Path("type/{type}")
	public void getByType(@PathParam("type") String type) {
		new MeasureByTypeResource(uriInfo, request, user, type);
	}
	
}
