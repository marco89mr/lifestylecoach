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

import lsc.localdatabase.dao.NotificationDao;
import lsc.localdatabase.model.Notification;
import lsc.localdatabase.parser.Parser;


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
	public Notification getById() {
		System.out.println("http get "+uriInfo.getPath());
		return Parser.generate( NotificationDao.getById(notification_id) );
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Notification put(Notification notification) {
		System.out.println("http put "+uriInfo.getPath());
		notification.setId(notification_id);
		Parser.parse(notification);
		Parser.generate(notification);
		return NotificationDao.update(notification);
	}
	
	@DELETE
	public void delete() {
		System.out.println("http delete "+uriInfo.getPath());
		NotificationDao.remove( NotificationDao.getById(notification_id) );
	}
	
	
}
