package lsc.localdatabase.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.model.User;


@Stateless
@LocalBean
public class FinalResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String table;
	String field;
	String value;

	
	public FinalResource(UriInfo uriInfo, Request request, String table, String field, String value) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.table = table;
		this.field = field;
		this.value = value;
	}
	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public User getPerson() {
		User user = this.getPersonById(id);
		if (user == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		System.out.println("Returning person... " + user.getIdPerson());
		return user;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putPerson(User user) {
		System.out.println("--> Updating Person... " +this.id);
		System.out.println("--> "+user.toString());
		User.updatePerson(user);
		
		Response res;
		
		User existing = getPersonById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			user.setIdPerson(this.id);
			User.updatePerson(user);
		}

		return res;

		
	}

	@DELETE
	public void deletePerson() {
		User c = getPersonById(id);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		User.removePerson(c);
	}
	
}
