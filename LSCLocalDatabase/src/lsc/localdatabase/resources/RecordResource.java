package lsc.localdatabase.resources;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.logic.LocalDatabaseLogic;
import lsc.rest.filter.LocalDatabaseFilter;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Record;


@Stateless
@LocalBean
public class RecordResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int record_id;
	
	public RecordResource(UriInfo uriInfo, Request request, int record_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.record_id = record_id;
	}
	
	
	
	// -----------
	// record/{id}
	// -----------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Record getById() {
		return LocalDatabaseLogic.record.getById(uriInfo, record_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Record put(Record record) {
		return LocalDatabaseLogic.record.put(uriInfo, record, record_id);
	}
	
	@DELETE
	public void delete() {
		LocalDatabaseLogic.record.delete(uriInfo, record_id);
	}
	
	
	
	// ----------------
	// record/{id}/data
	// ----------------
	
	@Path("/data")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DataCollection getAll() {
		return LocalDatabaseLogic.data.getAll(uriInfo, LocalDatabaseFilter.data.record_id(record_id));
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Data data) {
		return LocalDatabaseLogic.data.post(uriInfo, data, record_id);
	}
	
	
}
