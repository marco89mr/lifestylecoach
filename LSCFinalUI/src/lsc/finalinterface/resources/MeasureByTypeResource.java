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
public class MeasureByTypeResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	String user;
	String type;

	
	public MeasureByTypeResource(UriInfo uriInfo, Request request, String user, String type) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.user = user;
		this.type = type;
	}
	
	
	// GET
	
	
	
	// Allows to type http://localhost:xxxx/user/measure/#mid/type/#type/ ...
	
	@Path("{period: (everytime | day | week | month | threemonth | year) }/"
		+ "{date}/"
		+ "{function: (last | average | cumulative | max | min) }/"
		+ "{from_date}")
	public void getByFilter(@PathParam("period") String period,
							@PathParam("date") String date,
							@PathParam("function") String function,
							@PathParam("from_date") String from_date ) {
		new MeasureByFilterResource(uriInfo, request, user, type, period, date, function, from_date);
	}
}
