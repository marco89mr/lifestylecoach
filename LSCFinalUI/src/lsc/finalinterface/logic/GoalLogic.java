package lsc.finalinterface.logic;

import lsc.businesslogic.client.BusinessLogicClient;
import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.GoalFilter;
import lsc.rest.model.Goal;
import lsc.rest.model.GoalCollection;
import lsc.storage.rest.client.StorageClient;


public class GoalLogic extends BaseLogic<	Goal,
											GoalCollection,
											GoalFilter,
											StorageClient.GoalClient,
											FinalInterfaceClient.GoalClient > {
	@Override
	protected GoalFilter filter() {return Filter.goal(); }
	@Override
	protected StorageClient.GoalClient storage_client() { return StorageClient.goal; }
	@Override
	protected FinalInterfaceClient.GoalClient final_client() { return FinalInterfaceClient.goal; }
	@Override
	protected int master(Goal x) { return x.getUserId(); }
	
	

	@Override
	protected Goal storage_client_getById(int authenticated_id, int id) {
		BusinessLogicClient.checkExpiredDedline( authenticated_id );
		return storage_client().getById(id);
	}
	@Override
	protected GoalCollection storage_client_getAll(int authenticated_id, GoalFilter filter) {
		BusinessLogicClient.checkExpiredDedline( authenticated_id );
		return storage_client().getAll(filter());
	}
	@Override
	protected Goal storage_client_post(int authenticated_id, Goal m) {
		//Goal n = storage_client().post(m);
		BusinessLogicClient.postAndStartGoal( m );
		return m;
	}
	@Override
	protected Goal storage_client_put(int authenticated_id, Goal m) {
		//Goal n = storage_client().put(m);
		return null;
	}
	
	
	
	
	
}
