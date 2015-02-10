package lsc.finalinterface.resources;

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

import lsc.finalinterface.logic.FinalInterfaceLogic;
import lsc.rest.model.RecordComplex;


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
	public RecordComplex getById() {
		return FinalInterfaceLogic.record.getById(uriInfo, record_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordComplex put(RecordComplex recordcomplex) {
		return FinalInterfaceLogic.record.put( uriInfo, recordcomplex, record_id );
	}
	
	@DELETE
	public void delete() {
		FinalInterfaceLogic.record.delete( uriInfo, record_id );
	}
	
	
}
