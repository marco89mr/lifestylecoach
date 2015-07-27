package lsc.finalinterface.logic;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.UserFilter;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;
import lsc.storage.rest.client.StorageClient;


public class UserLogic extends BaseLogic<	User,
											UserCollection,
											UserFilter,
											StorageClient.UserClient,
											FinalInterfaceClient.UserClient > {
	@Override
	protected UserFilter filter() {return Filter.user(); }
	@Override
	protected StorageClient.UserClient storage_client() { return StorageClient.user; }
	@Override
	protected FinalInterfaceClient.UserClient final_client() { return FinalInterfaceClient.user; }
	@Override
	protected int master(User u) { return u.getId(); }// I want 0 if not setted
	
	
	/*
	protected UserCollection storage_client_getAll(UserFilter filter) {
		return storage_client().getAll(filter);
	}
	
	protected User storage_client_getById(int id) {
		return storage_client().getById(id);
	}
	
	protected User storage_client_post(int id) {
		return storage_client().getById(id);
	}
	
	protected User storage_client_put(int id) {
		return storage_client().getById(id);
	}
	
	protected boolean storage_client_delete(int id) {
		return storage_client().getById(id);
	}
	*/
	
	/*
	public Response post(UriInfo uriInfo, User entity) {
		Response r = super.post(uriInfo, entity);
		// TODO call check deadline
		return r;
	}
	*/
}
	
