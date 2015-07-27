package lsc.finalinterface.logic;

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
	protected RecordFilter filter() {return Filter.record(); }
	@Override
	protected StorageClient.RecordClient storage_client() { return StorageClient.record; }
	@Override
	protected FinalInterfaceClient.RecordClient final_client() { return FinalInterfaceClient.record; }
	@Override
	protected int master(RecordComplex x) { return x.getUserId(); }
	
	
	
	@Override
	protected RecordComplex storage_client_post(int authenticated_id, RecordComplex m) {
		RecordComplex n = storage_client().post(m);
		BusinessLogicClient.checkRecordsUnder(m.getUserId(), m.getType() , m.getDate() );
		return n;
	}
	@Override
	protected RecordComplex storage_client_put(int authenticated_id, RecordComplex m) {
		RecordComplex n = storage_client().put(m);
		BusinessLogicClient.checkRecordsUnder(m.getUserId(), m.getType() , m.getDate() );
		return n;
	}
	@Override
	protected boolean storage_client_delete(int authenticated_id, int id) {
		// TODO
		// not working!!!!!!!!!!!!!!!!!????????????
		RecordComplex r = StorageClient.record.getById(id);
		boolean v = storage_client().delete(id);
		BusinessLogicClient.checkRecordsUnder(r.getUserId(), r.getType() , r.getDate() );
		return v;
		
	}
	
	
	/*
	public Response post(UriInfo uriInfo, RecordComplex entity) {
		Response r = super.post(uriInfo, entity);
		// TODO call check deadline
		BusinessLogicClient.check_record( entity );
		return r;
	}
	
	
	
	public RecordComplex put(UriInfo uriInfo, RecordComplex entity, int id) {
		RecordComplex r = super.put(uriInfo, entity, id);
		// TODO call check deadline
		BusinessLogicClient.check_record( entity );
		return r;
	}
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		RecordComplex r = StorageClient.record.getById(id);
		//super.delete(uriInfo, id);
		// TODO call check deadline
		BusinessLogicClient.check_record( r );
	}
	*/
	
}
