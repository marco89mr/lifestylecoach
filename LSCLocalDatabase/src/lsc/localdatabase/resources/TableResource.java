package lsc.localdatabase.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
public class TableResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String table;

	
	public TableResource(UriInfo uriInfo, Request request, String table) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.table = table;
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public User newEntry() {
		User user = this.getPersonById(id);
		if (user == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		System.out.println("Returning person... " + user.getIdPerson());
		return user;
	}
	
	
	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the TableResource
	// Allows to type http://localhost:xxxx/table/field
	@Path("{field}")
	public void getField(@PathParam("field") String field) {
		new FieldResource(uriInfo, request, table, field);
	}
}
