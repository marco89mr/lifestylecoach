package lsc.localdatabase.client;

import java.io.Serializable;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import lsc.localdatabase.model.EntityCollectionModel;
import lsc.localdatabase.model.EntityModel;

import org.glassfish.jersey.client.ClientConfig;


public class LocalDatabaseClient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String base_url = "http://localhost:4000/lsc/";
	
	public static Class<?> model_class;
	public static Class<?> model_collection_class;
	public static String path;
	
	
	
	public static Builder connect(String requestUrl) {
		System.out.println(requestUrl);
		// build end-point link
		URI uri = UriBuilder.fromUri( requestUrl ).build();
		// set up client
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget service = client.target( uri );
		Builder builder = service.request().accept(MediaType.APPLICATION_XML);
		return builder;
	}
	
	public static <T> T http_get(Class<T> c, String url) {;
		return LocalDatabaseClient.connect( url ).get().<T>readEntity( c );
	}
	
	public static <T> T http_put(Class<T> c, String url, T obj) {
		return LocalDatabaseClient.connect( url ).put(Entity.entity(obj, MediaType.APPLICATION_XML)).<T>readEntity(c);
	}
	
	public static <T> String http_post(Class<T> c, String url, T obj) {
		return LocalDatabaseClient.connect( url ).post(Entity.entity(obj, MediaType.APPLICATION_XML)).getLocation().toString();
	}
	
	public static int http_delete(String url) {
		if( LocalDatabaseClient.connect( url ).delete().getStatus() == 202)
			return 1;
		else
			return 0;
	}
	
	
	
	
	// API Calls
	// 
	
	public static <M extends EntityModel> M getByUrl(Class<M> model_class, String url) {
		return LocalDatabaseClient.<M>http_get(model_class, url);
	}
	
	public static <M extends EntityModel> M getById(Class<M> model_class, int id) {
		return LocalDatabaseClient.<M>http_get(model_class, base_url+path+"/"+id);
	}
	
	public static <MM extends EntityCollectionModel> MM getAll(Class<MM> model_collection_class) {
		return LocalDatabaseClient.<MM>http_get(model_collection_class, base_url+path);
	}
	
	public static <MM extends EntityCollectionModel> MM getAll(Class<MM> model_collection_class, String filters) {
		return LocalDatabaseClient.<MM>http_get(model_collection_class, base_url+path+"?"+filters);
	}
	
	public static <M> void post(Class<M> model_class, M x) {
		LocalDatabaseClient.<M>http_post(model_class, base_url+path, x);
	}
	
	public static <M extends EntityModel> void put(Class<M> model_class, M x) {
		LocalDatabaseClient.<M>http_put(model_class, base_url+path+"/"+x.getId(), x);
	}
	
	public static <M extends EntityModel> int delete(Class<M> model_class, M x) {
		return LocalDatabaseClient.http_delete(base_url+path+"/"+x.getId() );
	}
	
	
}
