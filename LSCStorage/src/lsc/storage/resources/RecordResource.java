package lsc.storage.resources;

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

import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.Filter;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;
import lsc.rest.model.Record;
import lsc.rest.model.RecordComplex;
import lsc.storage.parser.StorageParser;


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
		System.out.println("http get "+uriInfo.getPath());
		//record
		Record r = LocalDatabaseClient.record.getById( record_id );
		//datas
		Filter.DataFilter filter = Filter.data.record_id( record_id );
		DataCollection datas = LocalDatabaseClient.data.getAll( filter );
		//unify
		RecordComplex rc = StorageParser.record.toRecordComplex(r, datas);
		return rc;
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public RecordComplex put(RecordComplex recordcomplex) {
		System.out.println("http put "+uriInfo.getPath());
		//put record
		Record new_record = StorageParser.record.toRecord(recordcomplex);
		LocalDatabaseClient.record.put( new_record );
		//delete old datas
		Filter.DataFilter filter = Filter.data.record_id( record_id );
		DataCollection old_datas = LocalDatabaseClient.data.getAll( filter );
		for(Data d : old_datas)
			LocalDatabaseClient.data.delete( d.getId() );
		//create new datas
		DataCollection new_datas = StorageParser.record.toData(recordcomplex);
		for(Data d : new_datas)
			LocalDatabaseClient.data.post( d );
		//unify
		DataCollection datas = LocalDatabaseClient.data.getAll( filter );
		recordcomplex = StorageParser.record.toRecordComplex(new_record, datas);
		return recordcomplex;
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		//record
		LocalDatabaseClient.record.delete( record_id );
		//datas
		Filter.DataFilter filter = Filter.data.record_id( record_id );
		DataCollection datas = LocalDatabaseClient.data.getAll( filter );
		for(Data d : datas)
			LocalDatabaseClient.data.delete( d.getId() );
	}
	
	
}
