package lsc.localdatabase.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.validation.constraints.Size;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import lsc.localdatabase.model.User;
import lsc.localdatabase.model.UserList;

import org.glassfish.jersey.client.ClientConfig;

public class ClientApp {
	
	WebTarget service;
	
	
	public static void main(String[] args) {
		
		// init client
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI uri = UriBuilder.fromUri( "http://localhost:5900/lsc/user" ).build();
		WebTarget service = client.target( uri );
		
			
		// parameters
		String path = "";

		UserList users = new UserList();
		
		Response r = service
					.path(path)
					.request()
					.accept(MediaType.APPLICATION_XML)
					.get();
		r.bufferEntity(); //reload the buffer

		// elaborate response
		try{ users = r.readEntity(UserList.class); }
		catch(javax.ws.rs.ProcessingException e) {
			System.out.println(e.getLocalizedMessage()); }
		finally{}
		
		for(User u : users.getList()) {
			int id = users.getList().get(0).getId();
			System.out.println( id );
		}
	}

}