package lsc.localdatabase.resources;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.logic.LocalDatabaseLogic;
import lsc.rest.model.Data;


@Stateless
@LocalBean
public class DataResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int data_id;
	
	public DataResource(UriInfo uriInfo, Request request, int data_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.data_id = data_id;
	}
	
	
	
	// ---------
	// data/{id}
	// ---------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Data getById() {
		return LocalDatabaseLogic.data.getById(uriInfo, data_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Data put(Data data) {
		return LocalDatabaseLogic.data.put(uriInfo, data, data_id);
	}
	
	@DELETE
	public void delete() {
		LocalDatabaseLogic.data.delete(uriInfo, data_id);
	}
	
	
}
