package lsc.finalinterface.logic;

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
	protected GoalFilter filter() {return Filter.goal; }
	@Override
	protected StorageClient.GoalClient storage_client() { return StorageClient.goal; }
	@Override
	protected FinalInterfaceClient.GoalClient final_client() { return FinalInterfaceClient.goal; }
	@Override
	protected int master(Goal x) { return x.getUserId(); }
}
