package lsc.localdatabase.resources;

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

import lsc.localdatabase.model.User;


@Stateless
@LocalBean
public class FieldResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String table;
	String field;

	
	public FieldResource(UriInfo uriInfo, Request request, String table, String field) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.table = table;
		this.field = field;
	}
	
	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the TableResource
	// Allows to type http://localhost:xxxx/table/field/value
	@Path("{value}")
	public void getField(@PathParam("value") String value) {
		new FinalResource(uriInfo, request, table, field, value);
	}
}
