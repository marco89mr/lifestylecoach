package lsc.businesslogic.logic;

import java.util.Date;
import lsc.rest.model.Base;
import lsc.rest.model.Notification;
import lsc.storage.rest.client.StorageClient;


public class NotificationLogic {

	
	public static void postNewNotification(int user_id, int deadline_id, String type, String message ) {
		Notification notification = new Notification();
		notification.setDate( Base._formatDate( new Date() ) );
		notification.setUserId( user_id );
		notification.setDeadlineId( deadline_id );
		notification.setType(type);
		notification.setMessage(message);
		notification.setStatus(Notification.Status.unread);
		StorageClient.notification.post(notification);
	}
	
	
}
