package lsc.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
		Builder builder = service.request().accept(MediaType.APPLICATION_JSON);
		return builder;
	}
	
	public static <T> T http_get(Class<T> c, String url) {
		System.out.println("--> Client.http_get("+url+")");
		Response r = ClientUtils.connect( url ).get();
		T t = r.<T>readEntity( c );
		r.close();
		//T t = ClientUtils.connect( url ).get().<T>readEntity( c );
		if(t==null)
			System.out.println("--> Client.http_get("+url+"): NULL OUTPUT");
		return t;
	}
	
	public static <T> T http_put(Class<T> c, String url, T obj) {
		System.out.println("--> Client.http_put("+url+")");
		obj = ClientUtils.connect( url ).put(Entity.entity(obj, MediaType.APPLICATION_JSON)).readEntity( c );
		if(obj==null)
			System.out.println("--> Client.http_put("+url+"): NULL OUTPUT");
		return obj;
	}
	
	public static <T> String http_post(Class<T> c, String url, T obj) {
		System.out.println("--> Client.http_post("+url+")");
		String location = ClientUtils.connect( url ).post(Entity.entity(obj, MediaType.APPLICATION_JSON)).getLocation().toString();
		if(location==null || location.equals(""))
			System.out.println("--> Client.http_post("+url+"): NULL LOCATION");
		return location;
	}
	
	public static boolean http_delete(String url) {
		System.out.println("--> Client.http_delete("+url+")");
		if( ClientUtils.connect( url ).delete().getStatus() == 204)
			return true;
		else{
			System.out.println("--> Client.http_delete("+url+"): FAILED");
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
