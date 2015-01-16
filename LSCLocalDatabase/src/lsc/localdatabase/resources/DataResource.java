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

import lsc.localdatabase.dao.DataDao;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.parser.Parser;


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
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( DataDao.getById(data_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Data put(Data data) {
		System.out.println("http put "+uriInfo.getPath());
		data.setId(data_id);
		Parser.parse(data);
		Parser.generate(data);
		return DataDao.update(data);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DataDao.remove( DataDao.getById(data_id) );
	}
	
	
}
