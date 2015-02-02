package lsc.localdatabase.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import lsc.localdatabase.rest.model.DataCollectionRest;
import lsc.localdatabase.rest.model.DataRest;
import lsc.localdatabase.rest.model.DeadlineCollectionRest;
import lsc.localdatabase.rest.model.DeadlineRest;
import lsc.localdatabase.rest.model.GoalCollectionRest;
import lsc.localdatabase.rest.model.GoalRest;
import lsc.localdatabase.rest.model.NotificationCollectionRest;
import lsc.localdatabase.rest.model.NotificationRest;
import lsc.localdatabase.rest.model.RecordCollectionRest;
import lsc.localdatabase.rest.model.RecordRest;
import lsc.localdatabase.rest.model.ToDoCollectionRest;
import lsc.localdatabase.rest.model.ToDoRest;
import lsc.localdatabase.rest.model.UserCollectionRest;
import lsc.localdatabase.rest.model.UserRest;
import lsc.localdatabase.rest.path.Path;

import org.glassfish.jersey.client.ClientConfig;


public class ClientUtils {
	
	
	// utils: http calls methods
	
	public static Builder connect(String requestUrl) {
		// build end-point link
		URI uri = UriBuilder.fromUri( requestUrl ).build();
		// set up client
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget service = client.target( uri );
		Builder builder = service.request().accept(MediaType.APPLICATION_XML);
		return builder;
	}
	
	public static <T> T http_get(Class<T> c, String url) {
		return ClientUtils.connect( url ).get().<T>readEntity( c );
	}
	
	public static <T> T http_put(Class<T> c, String url, T obj) {
		return ClientUtils.connect( url ).put(Entity.entity(obj, MediaType.APPLICATION_XML)).<T>readEntity(c);
	}
	
	public static <T> String http_post(Class<T> c, String url, T obj) {
		return ClientUtils.connect( url ).post(Entity.entity(obj, MediaType.APPLICATION_XML)).getLocation().toString();
	}
	
	public static int http_delete(String url) {
		if( ClientUtils.connect( url ).delete().getStatus() == 202)
			return 1;
		else
			return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ready to use
	
	
	
	
	public static <P extends Path.UserPath> UserRest get(P path) {
		return ClientUtils.http_get(UserRest.class, path.getCompletePath());
	}
	public static <P extends Path.RecordPath> RecordRest get(P path) {
		return ClientUtils.http_get(RecordRest.class, path.getCompletePath());
	}
	public static <P extends Path.DataPath> DataRest get(P path) {
		return ClientUtils.http_get(DataRest.class, path.getCompletePath());
	}
	public static <P extends Path.GoalPath> GoalRest get(P path) {
		return ClientUtils.http_get(GoalRest.class, path.getCompletePath());
	}
	public static <P extends Path.DeadlinePath> DeadlineRest get(P path) {
		return ClientUtils.http_get(DeadlineRest.class, path.getCompletePath());
	}
	public static <P extends Path.ToDoPath> ToDoRest get(P path) {
		return ClientUtils.http_get(ToDoRest.class, path.getCompletePath());
	}
	public static <P extends Path.NotificationPath> NotificationRest get(P path) {
		return ClientUtils.http_get(NotificationRest.class, path.getCompletePath());
	}
	
	
	
	public static <P extends Path.UserFilter> UserCollectionRest get(P path) {
		return ClientUtils.http_get(UserCollectionRest.class, path.getCompletePath());
	}
	public static <P extends Path.RecordFilter> RecordCollectionRest get(P path) {
		return ClientUtils.http_get(RecordCollectionRest.class, path.getCompletePath());
	}
	public static <P extends Path.DataFilter> DataCollectionRest get(P path) {
		return ClientUtils.http_get(DataCollectionRest.class, path.getCompletePath());
	}
	public static <P extends Path.GoalFilter> GoalCollectionRest get(P path) {
		return ClientUtils.http_get(GoalCollectionRest.class, path.getCompletePath());
	}
	public static <P extends Path.DeadlineFilter> DeadlineCollectionRest get(P path) {
		return ClientUtils.http_get(DeadlineCollectionRest.class, path.getCompletePath());
	}
	public static <P extends Path.ToDoFilter> ToDoCollectionRest get(P path) {
		return ClientUtils.http_get(ToDoCollectionRest.class, path.getCompletePath());
	}
	public static <P extends Path.NotificationFilter> NotificationCollectionRest get(P path) {
		return ClientUtils.http_get(NotificationCollectionRest.class, path.getCompletePath());
	}
	
	
	
	public static <P extends Path.UserFilter> void post(P path, UserRest x) {
		ClientUtils.http_post(UserRest.class, path.getCompletePath(), x);
	}
	
	
	
	
}
