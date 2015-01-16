package lsc.localdatabase.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import lsc.localdatabase.model.Base;
import lsc.localdatabase.model.BaseCollection;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataCollection;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineCollection;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalCollection;
import lsc.localdatabase.model.Notification;
import lsc.localdatabase.model.NotificationCollection;
import lsc.localdatabase.model.Record;
import lsc.localdatabase.model.RecordCollection;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoCollection;
import lsc.localdatabase.model.User;
import lsc.localdatabase.model.UserCollection;

import org.glassfish.jersey.client.ClientConfig;


public class ClientApp <M extends Base, MM extends BaseCollection>{
	
	public final String base_url = "http://localhost:5900/lsc/";
	public String path;
	public Class<M> model_class;
	public Class<MM> model_collection_class;
	
	
	public ClientApp(String path, Class<M> model_class, Class<MM> model_collection_class){
		this.path = path; 
		this.model_class = model_class; 
		this.model_collection_class = model_collection_class; 
	}
	
	
	// API Calls
	// 
	
	public M getByUrl(String url) {
		return ClientApp.<M>http_get(this.model_class, url);
	}
	
	public M getById(int id) {
		return ClientApp.<M>http_get(model_class, base_url+path+"/"+id);
	}
	
	public MM getAll() {
		return ClientApp.<MM>http_get(model_collection_class, base_url+path);
	}
	
	public MM getAll(String filters) {
		return ClientApp.<MM>http_get(model_collection_class, base_url+path+"?"+filters);
	}
	
	public void post(M x) {
		ClientApp.<M>http_post(model_class, base_url+path, x);
	}
	
	public void put(M x) {
		ClientApp.<M>http_put(model_class, base_url+path+"/"+x.getId(), x);
	}
	
	public int delete(M x) {
		return ClientApp.http_delete(base_url+path+"/"+x.getId() );
	}
	
	

	// objects generators

	public static ClientApp<User, UserCollection> user(){
		return new ClientApp<User, UserCollection>("user", User.class, UserCollection.class);
	}

	public static ClientApp<Record, RecordCollection> record(){
		return new ClientApp<Record, RecordCollection>("record", Record.class, RecordCollection.class);
	}

	public static ClientApp<Data, DataCollection> data(){
		return new ClientApp<Data, DataCollection>("data", Data.class, DataCollection.class);
	}

	public static ClientApp<Goal, GoalCollection> goal(){
		return new ClientApp<Goal, GoalCollection>("goal", Goal.class, GoalCollection.class);
	}

	public static ClientApp<Deadline, DeadlineCollection> deadline(){
		return new ClientApp<Deadline, DeadlineCollection>("deadline", Deadline.class, DeadlineCollection.class);
	}

	public static ClientApp<Notification, NotificationCollection> notification(){
		return new ClientApp<Notification, NotificationCollection>("notification", Notification.class, NotificationCollection.class);
	}

	public static ClientApp<ToDo, ToDoCollection> todo(){
		return new ClientApp<ToDo, ToDoCollection>("todo", ToDo.class, ToDoCollection.class);
	}
	
	

	
	// http calls methods
	
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
	
	public static <T> T http_get(Class<T> c, String url) {;
		return ClientApp.connect( url ).get().<T>readEntity( c );
	}
	
	public static <T> T http_put(Class<T> c, String url, T obj) {
		return ClientApp.connect( url ).put(Entity.entity(obj, MediaType.APPLICATION_XML)).<T>readEntity(c);
	}
	
	public static <T> String http_post(Class<T> c, String url, T obj) {
		return ClientApp.connect( url ).post(Entity.entity(obj, MediaType.APPLICATION_XML)).getLocation().toString();
	}
	
	public static int http_delete(String url) {
		if( ClientApp.connect( url ).delete().getStatus() == 202)
			return 1;
		else
			return 0;
	}
	
}
