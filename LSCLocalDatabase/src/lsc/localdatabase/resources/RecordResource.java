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

import lsc.localdatabase.dao.DataDao;
import lsc.localdatabase.dao.RecordDao;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataCollection;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.parser.Parser;
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
	public Record getById() {
		System.out.println("http get "+uriInfo.getPath());
		//return Response.ok( Record.getById( record_id ) ).build();
		return Parser.generate( RecordDao.getById( record_id ) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Record put(Record record) {
		System.out.println("http put "+uriInfo.getPath());
		record.setId(record_id);
		Parser.parse(record);
		Parser.generate(record);
		return RecordDao.update(record);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		RecordDao.remove( RecordDao.getById(record_id) );
	}
	
	
	
	// ----------------
	// record/{id}/data
	// ----------------
	
	@Path("/data")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public DataCollection getAll() {
		System.out.println("http get "+uriInfo.getPath());
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		param.putSingle("record_id", String.valueOf(record_id) );
		return Parser.generate( DataDao.getAll( param ) );
	}

	@Path("/data")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Response newEntry(Data data) {
		System.out.println("http post "+uriInfo.getPath());
		data.setRecord( RecordDao.getById(record_id) );
		data = DataDao.save(data);
		data = Parser.parse(data);
		data = Parser.generate(data);
		return Response.created( URI.create( data._getUrl() ) ).build();
	}
	
	
}
