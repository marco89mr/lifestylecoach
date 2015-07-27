package lsc.finalinterface.logic;

import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.DeadlineFilter;
import lsc.rest.model.Deadline;
import lsc.rest.model.DeadlineCollection;
import lsc.storage.rest.client.StorageClient;


public class DeadlineLogic extends BaseLogic<	Deadline,
												DeadlineCollection,
												DeadlineFilter,
												StorageClient.DeadlineClient,
												FinalInterfaceClient.DeadlineClient > {
	@Override
	protected DeadlineFilter filter() {return Filter.deadline(); }
	@Override
	protected StorageClient.DeadlineClient storage_client() { return StorageClient.deadline; }
	@Override
	protected FinalInterfaceClient.DeadlineClient final_client() { return FinalInterfaceClient.deadline; }
	@Override
	protected int master(Deadline x) { return StorageClient
			.goal
			.getById( x.getGoalId() )
			.getUserId();
	}
	
	
	/*
	@Override
	protected Deadline storage_client_getById(int authenticated_id, int id) {
		//BusinessLogicClient.check_today( authenticated_id );
		return storage_client().getById(id);
	}
	@Override
	protected DeadlineCollection storage_client_getAll(int authenticated_id, DeadlineFilter filter) {
		BusinessLogicClient.check_today( authenticated_id );
		return storage_client().getAll(filter);
	}
	*/
	@Override
	protected Deadline storage_client_post(int authenticated_id, Deadline m) {
		//Deadline n = storage_client().post(m);
		//BusinessLogicClient.check_deadline( m );
		return null;
	}
	@Override
	protected Deadline storage_client_put(int authenticated_id, Deadline m) {
		//Deadline n = storage_client().put(m);
		//BusinessLogicClient.check_deadline( m );
		return null;
	}
	@Override
	protected boolean storage_client_delete(int authenticated_id, int id) {
		// TODO
		// not working!!!!!!!!!!!!!!!!!????????????
		//Deadline r = StorageClient.deadline.getById(id);
		//boolean v = storage_client().delete(id);
		//BusinessLogicClient.check_deadline( r );
		return false;
		
	}

	
	/*
	public Response post(UriInfo uriInfo, Deadline entity) {
		Response r = super.post(uriInfo, entity);
		// TODO call check deadline
		BusinessLogicClient.check_deadline( entity );
		return r;
	}
	
	
	
	public Deadline put(UriInfo uriInfo, Deadline entity, int id) {
		Deadline r = super.put(uriInfo, entity, id);
		// TODO call check deadline
		BusinessLogicClient.check_deadline( entity );
		return r;
	}
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		super.delete(uriInfo, id);
		// TODO call check deadline
	}
	*/
}


