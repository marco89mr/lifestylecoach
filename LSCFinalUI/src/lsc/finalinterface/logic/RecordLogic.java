package lsc.finalinterface.logic;

import java.net.MalformedURLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.businesslogic.client.BusinessLogicClient;
import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.RecordFilter;
import lsc.rest.model.RecordComplex;
import lsc.rest.model.RecordComplexCollection;
import lsc.storage.rest.client.StorageClient;


public class RecordLogic extends BaseLogic<	RecordComplex,
											RecordComplexCollection,
											RecordFilter,
											StorageClient.RecordClient,
											FinalInterfaceClient.RecordClient > {
	@Override
	protected RecordFilter filter() {return Filter.record; }
	@Override
	protected StorageClient.RecordClient storage_client() { return StorageClient.record; }
	@Override
	protected FinalInterfaceClient.RecordClient final_client() { return FinalInterfaceClient.record; }
	@Override
	protected int master(RecordComplex x) { return x.getUserId(); }

	
	
	public Response post(UriInfo uriInfo, RecordComplex entity) {
		Response r = super.post(uriInfo, entity);
		// TODO call check deadline
		try {
			BusinessLogicClient.check_record( entity );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	
	
	public RecordComplex put(UriInfo uriInfo, RecordComplex entity, int id) {
		RecordComplex r = super.put(uriInfo, entity, id);
		// TODO call check deadline
		try {
			BusinessLogicClient.check_record( entity );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		super.delete(uriInfo, id);
		// TODO call check deadline
		try {
			BusinessLogicClient.check_record( StorageClient.record.getById(id) );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
