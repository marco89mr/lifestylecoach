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

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Data;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.DataRest;


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
	public DataRest getById() {
		System.out.println("http get "+uriInfo.getPath());
		System.out.println("hi "+DaoFactory.data.getById(data_id).getName());
		return ParserFactory.data.toRest( DaoFactory.data.getById(data_id) );
		//return Parser.generate( DaoFactory.data.getById(data_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DataRest put(DataRest data) {
		System.out.println("http put "+uriInfo.getPath());
		Data data_dao = ParserFactory.data.toDao(data);
		data_dao.setId(data_id);
		return ParserFactory.data.toRest( DaoFactory.data.update(data_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.data.remove( DaoFactory.data.getById(data_id) );
	}
	
	
}
