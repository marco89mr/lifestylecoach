package lsc.finalinterface.logic;

import java.net.MalformedURLException;

import javax.ws.rs.core.UriInfo;

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
	protected NotificationFilter filter() {return Filter.notification; }
	@Override
	protected StorageClient.NotificationClient storage_client() { return StorageClient.notification; }
	@Override
	protected FinalInterfaceClient.NotificationClient final_client() { return FinalInterfaceClient.notification; }
	@Override
	protected int master(Notification x) { return x.getUserId(); }

	
	
	public NotificationCollection getAllUnderUser(UriInfo uriInfo, int userId) {
		System.out.println("http get "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		if( authenticated_id == 0 )
			//invalid credentials
			return null;
		
		// check deadlines
		System.out.println("DEBUG NotificationLogic.getAllUnderUser BusinessLogicClient.check_today for userId:" + userId);
		NotificationCollection nn = new NotificationCollection();
		try {
			nn = BusinessLogicClient.check_today(userId);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DEBUG NotificationLogic.getAllUnderUser BusinessLogicClient.check_today DONE");
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
}

	
