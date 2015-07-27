package lsc.storage.parser;

import lsc.andrux.rest.client.AndruxClient;
import lsc.andrux.rest.model.Quote;
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
		String message = entity.getMessage();
		Quote quote = AndruxClient.famous.getRandom();
		entity.setMessage(
			message + " <br/>(" + quote.getQuote() + "[" + quote.getAuthor() + "]" + ")"
		);
		return entity;
	}
	
	
	
	
}
