package lsc.storage.parser;

import lsc.rest.model.Notification;
import lsc.rest.model.NotificationCollection;


public class NotificationParser extends BaseParser<	Notification,
													NotificationCollection	> {
	


	@Override
	public Notification toStorage(Notification entity) {
		return entity;
	}

	@Override
	public Notification toDatabase(Notification entity) {
		return entity;
	}
	
	
	
	
}
