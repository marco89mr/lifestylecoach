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
public class MeasureByFilterResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String user;
	String type;
	String period;
	String date;
	String function;
	String from_date;

	
	public MeasureByFilterResource(UriInfo uriInfo, Request request, String user, String type, String period, String date, String function, String from_date) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.user = user;
		this.type = type;
		this.period = period;
		this.date = date;
		this.function = function;
		this.from_date = from_date;
	}
	
	
	// GET
	
}
