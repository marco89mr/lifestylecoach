package lsc.finalinterface.logic;

import lsc.businesslogic.client.BusinessLogicClient;
import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.filter.Filter.NotificationFilter;
import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;
import lsc.storage.rest.client.StorageClient;


public class NotificationLogic extends BaseLogic<	Notification,
													NotificationCollection,
													NotificationFilter,
													StorageClient.NotificationClient,
													FinalInterfaceClient.NotificationClient > {
	@Override
	protected NotificationFilter filter() {return Filter.notification(); }
	@Override
	protected StorageClient.NotificationClient storage_client() { return StorageClient.notification; }
	@Override
	protected FinalInterfaceClient.NotificationClient final_client() { return FinalInterfaceClient.notification; }
	@Override
	protected int master(Notification x) { return x.getUserId(); }
	
	
	
	@Override
	protected Notification storage_client_getById(int authenticated_id, int id) {
		BusinessLogicClient.checkExpiredDedline( authenticated_id );
		return storage_client().getById(id);
	}
	@Override
	protected NotificationCollection storage_client_getAll(int authenticated_id, NotificationFilter filter) {
		BusinessLogicClient.checkExpiredDedline( authenticated_id );
		return storage_client().getAll(filter);
	}
	@Override
	protected Notification storage_client_post(int authenticated_id, Notification m) {
		return null;
	}
	@Override
	protected Notification storage_client_put(int authenticated_id, Notification m) {
		return null;
	}
	@Override
	protected boolean storage_client_delete(int authenticated_id, int id) {
		return storage_client().delete(id);
	}

	
/*
	@Override
	public NotificationCollection getAllUnderUser(UriInfo uriInfo, int userId) {
		System.out.println("http get "+uriInfo.getPath());
		
		int authenticated_id = authenticate(uriInfo);
		if( authenticated_id == 0 )
			//invalid credentials
			return null;
		
		// check deadlines
		NotificationCollection nn = new NotificationCollection();
		LSCLogic lscLogic = BusinessLogicClient.init();
		nn = lscLogic.checkToday(userId);
		
		//NotificationCollection r = super.getAllUnderUser(uriInfo, userId);
		return nn;
	}
	
	
	
	@Override
	public Notification put(UriInfo uriInfo, Notification entity, int id) {
		System.out.println("http put "+uriInfo.getPath());
		Notification original = storage_client().getById(id);
		if( !checkPermission(uriInfo, master(original)) )
			return null;
		original.setStatus( entity.getStatus() );
		storage_client().put( original );
		return original;
	}
	*/
	
	
}

	
