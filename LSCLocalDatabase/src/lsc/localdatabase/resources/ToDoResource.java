package lsc.localdatabase.resources;

import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.ToDo;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.ToDoRest;


@Stateless
@LocalBean
public class ToDoResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int todo_id;
	
	public ToDoResource(UriInfo uriInfo, Request request, int todo_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.todo_id = todo_id;
	}
	
	
	
	// ---------
	// todo/{id}
	// ---------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoRest getById() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.todo.toRest( DaoFactory.todo.getById(todo_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDoRest put(ToDoRest todo) {
		System.out.println("http put "+uriInfo.getPath());
		ToDo todo_dao = ParserFactory.todo.toDao(todo);
		todo_dao.setId(todo_id);
		return ParserFactory.todo.toRest( DaoFactory.todo.update(todo_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.todo.remove( DaoFactory.todo.getById(todo_id) );
	}
	
	
}
