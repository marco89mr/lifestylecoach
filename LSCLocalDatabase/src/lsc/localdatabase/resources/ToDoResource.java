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

import lsc.localdatabase.dao.ToDoDao;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.parser.Parser;


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
	public ToDo getById() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( ToDoDao.getById(todo_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public ToDo put(ToDo todo) {
		System.out.println("http put "+uriInfo.getPath());
		todo.setId(todo_id);
		Parser.parse(todo);
		Parser.generate(todo);
		return ToDoDao.update(todo);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		ToDoDao.remove( ToDoDao.getById(todo_id) );
	}
	
	
}
