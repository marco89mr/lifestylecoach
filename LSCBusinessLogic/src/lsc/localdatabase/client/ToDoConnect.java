package lsc.localdatabase.client;

import java.io.Serializable;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoCollection;


public class ToDoConnect extends LocalDatabaseClient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String path = "todo"; 
	
	
	
	
	// API Calls
	// 
	
	public static ToDo getByUrl(String url) {
		return LocalDatabaseClient.<ToDo>http_get(ToDo.class, url);
	}
	
	public static ToDo getById(int id) {
		return LocalDatabaseClient.<ToDo>http_get(ToDo.class, base_url+"todo/"+id);
	}
	
	public static ToDoCollection getAll() {
		return LocalDatabaseClient.<ToDoCollection>http_get(ToDoCollection.class, base_url+"todo");
	}
	
	public static ToDoCollection getAll(String filters) {
		return LocalDatabaseClient.<ToDoCollection>http_get(ToDoCollection.class, base_url+"todo?"+filters);
	}
	
	public static void post(ToDo x) {
		LocalDatabaseClient.<ToDo>http_post(ToDo.class, base_url+"todo", x);
	}
	
	public static void put(ToDo x) {
		LocalDatabaseClient.<ToDo>http_put(ToDo.class, base_url+"todo/"+x.getId(), x);
	}
	
	public static int delete(ToDo x) {
		return LocalDatabaseClient.http_delete("todo/"+x.getId() );
	}
	
}
