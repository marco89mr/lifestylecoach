package lsc.finalinterface.logic;

import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.ToDoFilter;
import lsc.rest.model.ToDo;
import lsc.rest.model.ToDoCollection;
import lsc.storage.rest.client.StorageClient;


public class ToDoLogic extends BaseLogic<	ToDo,
											ToDoCollection,
											ToDoFilter,
											StorageClient.ToDoClient,
											FinalInterfaceClient.ToDoClient > {
	@Override
	protected ToDoFilter filter() {return Filter.todo(); }
	@Override
	protected StorageClient.ToDoClient storage_client() { return StorageClient.todo; }
	@Override
	protected FinalInterfaceClient.ToDoClient final_client() { return FinalInterfaceClient.todo; }
	@Override
	protected int master(ToDo x) { return x.getUserId(); }
}


