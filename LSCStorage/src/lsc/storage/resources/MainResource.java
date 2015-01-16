package lsc.storage.resources;



import javax.ejb.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.ClientConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


@Stateless
@LocalBean
@Path("/local")
public class MainResource {
	
	@Context UriInfo uriInfo;
	@Context Request request;
	@Context Response response;
	
	
	// -----
	// local
	// -----
	
	@Path("{requestPath : .+}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
	public Response get(@PathParam("requestPath") String requestPath) throws URISyntaxException {
		return redirectLocal(requestPath).get();
	}
	
	@Path("{requestPath : .+}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getJ(@PathParam("requestPath") String requestPath) throws URISyntaxException {
		return redirectLocal(requestPath).accept(MediaType.APPLICATION_JSON).get();
	}
	
	@Path("{requestPath : .+}")
	@PUT
	public Response put(String body, @PathParam("requestPath") String requestPath) throws URISyntaxException {
		return redirectLocal(requestPath).put(Entity.entity(body, MediaType.APPLICATION_XML));
	}
	
	@Path("{requestPath : .+}")
	@POST
	public Response post(String body, @PathParam("requestPath") String requestPath) throws URISyntaxException {
		return redirectLocal(requestPath).post(Entity.entity(body, MediaType.APPLICATION_XML));
	}
	
	@Path("{requestPath : .+}")
	@DELETE
	public Response delete(@PathParam("requestPath") String requestPath) throws URISyntaxException {
		return redirectLocal(requestPath).delete();
	}
	
	public Builder redirectLocal(String requestPath) throws URISyntaxException {
		System.out.println("http "+uriInfo.getPath());
		
		// build redirect link
		String redirect = "http://localhost:5900/lsc/" + requestPath;
		System.out.println("redirect " + redirect );
		UriBuilder uriBuilder = UriBuilder.fromUri(redirect);
		MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
		for(String param : queryParameters.keySet())
			for(String value : queryParameters.get(param))
				uriBuilder.queryParam(param, value);
		URI uri = uriBuilder.build();
		
		// set up client
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient( clientConfig );
		WebTarget service = client.target( uri );
		Builder builder = service.request().accept(MediaType.APPLICATION_XML);
		
		return builder;
		
		/*
		// call
		System.out.println("call "+service.getUri().getPath() );
		Response r = builder.get();
		
		// response
		r.bufferEntity(); //reload the buffer
		System.out.println("response ");
		return Response.ok( r.getEntity() ).build();
		*/
	}
	
	
}
