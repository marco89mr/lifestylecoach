package lsc.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

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
		T t = ClientUtils.connect( url ).get().<T>readEntity( c );
		if(t==null)
			System.out.println("--> Client.http_get("+url+"): NULL OUTPUT");
		return t;
	}
	
	public static <T> T http_put(Class<T> c, String url, T obj) {
		obj = ClientUtils.connect( url ).put(Entity.entity(obj, MediaType.APPLICATION_XML)).readEntity( c );
		if(obj==null)
			System.out.println("--> Client.http_put("+url+"): NULL OUTPUT");
		return obj;
	}
	
	public static <T> String http_post(Class<T> c, String url, T obj) {
		String location = ClientUtils.connect( url ).post(Entity.entity(obj, MediaType.APPLICATION_XML)).getLocation().toString();
		if(location==null || location.equals(""))
			System.out.println("--> Client.http_post("+url+"): NULL LOCATION");
		return location;
	}
	
	public static int http_delete(String url) {
		if( ClientUtils.connect( url ).delete().getStatus() == 202)
			return 1;
		else{
			System.out.println("--> Client.http_delete("+url+"): FAILED");
			return 0;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
