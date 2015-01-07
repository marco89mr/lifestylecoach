package lsc.localdatabase.resources;

import java.io.IOException;
import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.model.User;


@Stateless
@LocalBean
@Path("")
public class MainResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	

	// Defines that the next path parameter after the base url is
	// treated as a parameter and passed to the TableResource
	// Allows to type http://localhost:xxxx/table/
	@Path("{table}")
	public void getTable(@PathParam("table") String table) {
		
		new TableResource(uriInfo, request, table);
	}
}
