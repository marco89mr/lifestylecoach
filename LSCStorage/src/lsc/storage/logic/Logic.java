package lsc.storage.logic;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.Filter;
import lsc.rest.model.Base;
import lsc.rest.model.BaseCollection;
import lsc.storage.parser.BaseParser;


public abstract class Logic 	<	M extends Base,
									MM extends BaseCollection<M>,
							 		F extends Filter.BaseFilter<F>,
							 		C extends LocalDatabaseClient.BaseClient<M, MM, F>,
							 		P extends BaseParser<M, MM>					> {
	
	
	protected abstract F filter();
	protected abstract C client();
	protected abstract P parser();
	
	
	
	
	
	public MM getAllUnderGoal(UriInfo uriInfo, int goal_id) {
		System.out.println("http get "+uriInfo.getPath());
		F filter = filter().addFilter("goal_id", ""+goal_id);
		filter.addFilter( uriInfo.getQueryParameters() );
		return getAll(uriInfo);
	}

	public MM getAllUnderUser(UriInfo uriInfo, int user_id) {
		System.out.println("http get "+uriInfo.getPath());
		F filter = filter().addFilter("user_id", ""+user_id);
		filter.addFilter( uriInfo.getQueryParameters() );
		return getAll(uriInfo);
	}

	public MM getAll(UriInfo uriInfo) {
		System.out.println("http get "+uriInfo.getPath());
		System.out.println("DEBUG Logic.getAll filter:"+filter().param_url());
		F filter = filter().addFilter( uriInfo.getQueryParameters() );
		System.out.println("DEBUG Logic.getAll filter:"+filter.param_url());
		MM collection = client().getAll( filter );
		return parser().toStorage( collection );
	}

	
	
	
	public Response post(UriInfo uriInfo, M entity) {
		System.out.println("http post "+uriInfo.getPath());
		entity = parser().toDatabase( entity );
		entity = client().post( entity );
		URI uri = URI.create( client().resource_url() +"/"+ entity.getId() );
		return Response.created(uri).build();
	}
	
	
	
	
	public M getById(UriInfo uriInfo, int id) {
		System.out.println("http get "+uriInfo.getPath());
		M entity = client().getById(id);
		return parser().toStorage( entity );
	}
	
	
	
	
	public M put(UriInfo uriInfo, M entity, int id) {
		System.out.println("http put "+uriInfo.getPath());
		entity.setId(id);
		client().put( entity );
		parser().toDatabase( entity );
		return entity;
	}
	
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		System.out.println("http delete "+uriInfo.getPath());
		client().delete( id );
	}
	
	
	
	
	
}
