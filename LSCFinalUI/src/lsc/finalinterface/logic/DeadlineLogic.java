package lsc.finalinterface.logic;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.businesslogic.client.BusinessLogicClient;
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
	protected DeadlineFilter filter() {return Filter.deadline; }
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

	
	
	public Response post(UriInfo uriInfo, Deadline entity) {
		Response r = super.post(uriInfo, entity);
		// TODO call check deadline
		BusinessLogicClient.check_deadline( entity );
		return r;
	}
	
	
	
	public Deadline put(UriInfo uriInfo, Deadline entity, int id) {
		Deadline r = super.put(uriInfo, entity, id);
		// TODO call check deadline
		return r;
	}
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		super.delete(uriInfo, id);
		// TODO call check deadline
	}
}


