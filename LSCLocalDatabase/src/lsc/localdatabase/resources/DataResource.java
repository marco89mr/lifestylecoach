package lsc.localdatabase.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataList;


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
		return Data.getById(data_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Data put(Data data) {
		System.out.println("http put "+uriInfo.getPath());
		data.setId(data_id);
		return Data.update(data);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		Data.remove( Data.getById(data_id) );
	}
	
	
}
