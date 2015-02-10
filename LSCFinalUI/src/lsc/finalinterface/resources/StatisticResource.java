package lsc.finalinterface.resources;

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

import lsc.finalinterface.logic.FinalInterfaceLogic;
import lsc.rest.model.Notification;


@Stateless
@LocalBean
public class StatisticResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	int notification_id;
	
	public StatisticResource(UriInfo uriInfo, Request request, int notification_id) {
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
		return FinalInterfaceLogic.notification.getById(uriInfo, notification_id);
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Notification put(Notification notification) {
		return FinalInterfaceLogic.notification.put( uriInfo, notification, notification_id );
	}
	
	@DELETE
	public void delete() {
		FinalInterfaceLogic.notification.delete( uriInfo, notification_id );
	}
	
	
}
