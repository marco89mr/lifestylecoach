package lsc.finalinterface.resources;

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

import lsc.finalinterface.model.Person;


@Stateless
@LocalBean
public class GoalResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String table;
	String field;
	String value;

	
	public GoalResource(UriInfo uriInfo, Request request, String table, String field, String value) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.table = table;
		this.field = field;
		this.value = value;
	}
	
	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Person getPerson() {
		Person person = this.getPersonById(id);
		if (person == null)
			throw new RuntimeException("Get: Person with " + id + " not found");
		System.out.println("Returning person... " + person.getIdPerson());
		return person;
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response putPerson(Person person) {
		System.out.println("--> Updating Person... " +this.id);
		System.out.println("--> "+person.toString());
		Person.updatePerson(person);
		
		Response res;
		
		Person existing = getPersonById(this.id);
		
		if (existing == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
			person.setIdPerson(this.id);
			Person.updatePerson(person);
		}

		return res;

		
	}

	@DELETE
	public void deletePerson() {
		Person c = getPersonById(id);
		if (c == null)
			throw new RuntimeException("Delete: Person with " + id
					+ " not found");

		Person.removePerson(c);
	}
	
}
