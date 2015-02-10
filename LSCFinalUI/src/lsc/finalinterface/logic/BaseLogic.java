package lsc.finalinterface.logic;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.finalinterface.rest.client.FinalInterfaceClient;
import lsc.rest.filter.Filter;
import lsc.rest.model.Base;
import lsc.rest.model.BaseCollection;
import lsc.rest.model.User;
import lsc.rest.model.UserCollection;
import lsc.storage.rest.client.StorageClient;


public abstract class BaseLogic 	<	M extends Base,
									MM extends BaseCollection<M>,
							 		F extends Filter.BaseFilter<F>,
							 		C extends StorageClient.BaseClient<M, MM, F>,
							 		H extends FinalInterfaceClient.BaseClient<M, MM, F> > {
	
	
	protected abstract F filter();
	protected abstract C storage_client();
	protected abstract H final_client();
	protected abstract int master(M x);
	
	
	
	public static int authenticate(UriInfo uriInfo) {
		if( !uriInfo.getQueryParameters().containsKey("user") ||
			!uriInfo.getQueryParameters().containsKey("password") ){
			//missed credentials parameters
			System.out.println("ALERT missed credentials parameters");
			return 0;
		}
		String name = uriInfo.getQueryParameters().getFirst("user");
		String password = uriInfo.getQueryParameters().getFirst("password");
		System.out.println("checking authentication credentials for "+name);
		UserCollection uu = StorageClient.user.getAll( Filter.user.name(name) );
		if(uu==null || uu.size()==0){
			//invalid user
			System.out.println("ALERT invalid user name");
			return 0;
		}
		User user = null;
		for(User u : uu)
			if( u.getPassword().equals(password) ){
				user = u;
				break;
			}
		if(user==null){
			//invalid password
			System.out.println("ALERT invalid user password");
			return 0;
		}
		//succesfully authenticated
		System.out.println("OK authenticated");
		return user.getId();
	}
	
	
	
	public static boolean checkPermission(UriInfo uriInfo, int required_user_id) {
		int authenticated_user_id = authenticate(uriInfo);
		if( authenticated_user_id == 0 ){
			//invalid credentials
			System.out.println("ALERT invalid credentials");
			return false;
		}
		if( required_user_id != 0 && authenticated_user_id != required_user_id ){
			//operation not permitted
			System.out.println("ALERT operation not permitted to this user");
			return false;
		}
		return true;
	}
	
	
	
	public MM getAll(UriInfo uriInfo) {
		System.out.println("http get "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		if( authenticated_id == 0 )
			//invalid credentials
			return null;
		F filter = filter().addFilter( uriInfo.getQueryParameters() );
		MM collection = storage_client().getAll( filter );
		for(M entity : collection)
			if( authenticated_id != master(entity) )
				//access to this element not granted
				collection.remove(entity);
		return collection;
	}

	public MM getAllUnderGoal(UriInfo uriInfo, int goal_id) {
		System.out.println("http get "+uriInfo.getPath());
		if( !checkPermission(uriInfo, StorageClient.goal.getById(goal_id).getUserId()) )
			return null;
		F filter = filter().addFilter("goal_id", ""+goal_id);
		filter.addFilter( uriInfo.getQueryParameters() );
		MM collection = storage_client().getAll( filter );
		return collection;
	}

	public MM getAllUnderUser(UriInfo uriInfo, int user_id) {
		System.out.println("http get "+uriInfo.getPath());
		if( !checkPermission(uriInfo, user_id) )
			return null;
		F filter = filter().addFilter("user_id", ""+user_id);
		filter.addFilter( uriInfo.getQueryParameters() );
		MM collection = storage_client().getAll( filter );
		return collection;
	}
	
	
	
	public M getById(UriInfo uriInfo, int id) {
		System.out.println("http get "+uriInfo.getPath());
		M entity = storage_client().getById(id);
		if( !checkPermission(uriInfo, master(entity)) )
			return null;
		return entity;
	}
	
	
	
	public Response post(UriInfo uriInfo, M entity) {
		System.out.println("http post "+uriInfo.getPath());
		if( !checkPermission(uriInfo, master(entity)) )
			return null;
		storage_client().post( entity );
		URI uri = URI.create( final_client().resource_url() +"/"+ entity.getId() );
		return Response.created(uri).build();
	}
	
	
	
	public M put(UriInfo uriInfo, M entity, int id) {
		System.out.println("http put "+uriInfo.getPath());
		if( !checkPermission(uriInfo, master(storage_client().getById(id))) )
			return null;
		entity.setId(id);
		storage_client().put( entity );
		return entity;
	}
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		System.out.println("http delete "+uriInfo.getPath());
		if( !checkPermission(uriInfo, master(storage_client().getById(id))) )
			return;
		storage_client().delete( id );
	}
	
	
	
	
	
}
