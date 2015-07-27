package lsc.andrux;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.MessageBodyReader;

import org.glassfish.jersey.client.ClientConfig;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lsc.andrux.rest.model.Quote;


public class TestApp {
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException
	{
		
		
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
		
		
		
		
		
		
		/*

		// build end-point link
		URI uri = UriBuilder.fromUri( "https://andruxnet-random-famous-quotes.p.mashape.com/cat=famous" ).build();
		// set up client
		Client client = ClientBuilder.newClient( new ClientConfig() );
		// compose client with url into a service
		WebTarget service = client.target( uri );
		// compose service and headers into a builder
		Builder builder = service.request()
				.header("X-Mashape-Key", "KAp7unbQ2xmshqKhMTIZktqxAdkwp1i2leejsnZowUCQGDAJO9")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "application/json");
		// post
		Response r = builder.post(null);
		System.out.println( r.toString() );
		System.out.println( r.getEntity() );
		// read response
		Quote q = r.<Quote>readEntity( Quote.class );
		// close connection
		r.close();
		
		*/
		
	}
	
}
