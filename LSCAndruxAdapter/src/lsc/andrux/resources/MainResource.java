package lsc.andrux.resources;

import javax.ejb.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.URI;

import lsc.andrux.rest.model.Quote;



@Stateless
@LocalBean
@Path("/")
public class MainResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	
	
	// ----
	// famous
	// ----
	
	@Path("/famous")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public Quote getQuote() {

		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.post("https://andruxnet-random-famous-quotes.p.mashape.com/cat=famous")
					.header("X-Mashape-Key", "KAp7unbQ2xmshqKhMTIZktqxAdkwp1i2leejsnZowUCQGDAJO9")
					.header("Content-Type", "application/x-www-form-urlencoded")
					.header("Accept", "application/json")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( response );
		
		JSONObject obj = response.getBody().getObject();
		
		Quote q = new Quote();
		q.setQuote(obj.getString("quote"));
		q.setAuthor(obj.getString("author"));
		q.setCategory(obj.getString("category"));
		
		return q;
	}
	
	
	
}
