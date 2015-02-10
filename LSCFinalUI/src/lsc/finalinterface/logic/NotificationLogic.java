package lsc.finalinterface.logic;

import javax.ws.rs.core.UriInfo;

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
	protected NotificationFilter filter() {return Filter.notification; }
	@Override
	protected StorageClient.NotificationClient storage_client() { return StorageClient.notification; }
	@Override
	protected FinalInterfaceClient.NotificationClient final_client() { return FinalInterfaceClient.notification; }
	@Override
	protected int master(Notification x) { return x.getUserId(); }
	

	
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
}

	
