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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Data;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.DataCollectionRest;
import lsc.localdatabase.rest.model.DataRest;
import lsc.localdatabase.rest.model.RecordRest;
import lsc.localdatabase.rest.path.PathFactory;
import lsc.localdatabase.utils.MultivaluedMapImpl;


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
	public RecordRest getById() {
		System.out.println("http get "+uriInfo.getPath());
		//return Response.ok( Record.getById( record_id ) ).build();
		return ParserFactory.record.toRest( DaoFactory.record.getById( record_id ) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordRest put(RecordRest record) {
		System.out.println("http put "+uriInfo.getPath());
		Record record_dao = ParserFactory.record.toDao( record );
		record_dao.setId(record_id);
		return ParserFactory.record.toRest( DaoFactory.record.update(record_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.record.remove( DaoFactory.record.getById(record_id) );
	}
	
	
	
	// ----------------
	// record/{id}/data
	// ----------------
	
	@Path("/data")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DataCollectionRest getAll() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("record_id", String.valueOf(record_id) );
		return ParserFactory.data.toRest( DaoFactory.data.getAll( param ) );
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(DataRest data) {
		System.out.println("http post "+uriInfo.getPath());
		Data data_dao = ParserFactory.data.toDao( data );
		data_dao.setRecord( DaoFactory.record.getById(record_id) );
		DaoFactory.data.save(data_dao);
		return Response.created( URI.create( PathFactory.data().id(data_dao.getId()).getCompletePath() ) ).build();
	}
	
	
}
