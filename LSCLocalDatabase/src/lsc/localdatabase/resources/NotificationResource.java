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
import lsc.localdatabase.dao.model.Notification;
import lsc.localdatabase.rest.ParserFactory;
import lsc.localdatabase.rest.model.NotificationRest;


@Stateless
@LocalBean
public class NotificationResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int notification_id;
	
	public NotificationResource(UriInfo uriInfo, Request request, int notification_id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.notification_id = notification_id;
	}
	
	
	
	// ------------
	// notification/{id}
	// ------------
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationRest getById() {
		System.out.println("http get "+uriInfo.getPath());
		return ParserFactory.notification.toRest( DaoFactory.notification.getById(notification_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public NotificationRest put(NotificationRest notification) {
		System.out.println("http put "+uriInfo.getPath());
		Notification notification_dao = ParserFactory.notification.toDao(notification);
		notification_dao.setId(notification_id);
		return ParserFactory.notification.toRest( DaoFactory.notification.update(notification_dao) );
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		DaoFactory.notification.remove( DaoFactory.notification.getById(notification_id) );
	}
	
	
}
