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
	
	
	
	/*
	protected abstract M storage_client_getById(int id);
	protected abstract MM storage_client_getAll(F f);
	protected abstract M storage_client_post(M m);
	protected abstract M storage_client_put(M m);
	protected abstract void storage_client_delete(int id);
	*/
	protected M storage_client_getById(int authenticated_id, int id) {
		return storage_client().getById(id);
	}
	protected MM storage_client_getAll(int authenticated_id, F filter) {
		return storage_client().getAll(filter);
	}
	protected M storage_client_post(int authenticated_id, M m) {
		return storage_client().post(m);
	}
	protected M storage_client_put(int authenticated_id, M m) {
		return storage_client().put(m);
	}
	protected boolean storage_client_delete(int authenticated_id, int id) {
		return storage_client().delete(id);
	}
	
	
	
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
		UserCollection uu = StorageClient.user.getAll( Filter.user().name(name) );
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
	
	
	
	public static boolean checkPermission(int authenticated_user_id, int required_user_id) {
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
		if( !checkPermission(authenticated_id, authenticated_id) )
			return null;
		
		/*
		 * Pre-filtering option
		 */
		F filter = filter();
		filter.addFilter( uriInfo.getQueryParameters() );
		filter.addFilter("user_id", ""+authenticated_id);
		
		return storage_client_getAll(authenticated_id,  filter );
		 
		/*
		 *  Post-filtering option 
		 * 
		@SuppressWarnings("unchecked")
		MM out = (MM) collection.clone();
		out.clear();
		for(M entity : collection)
			if( authenticated_id == master(entity) )
				//access to this element granted
				out.add(entity);
		return out;
		*/
	}

	public MM getAllUnderGoal(UriInfo uriInfo, int goal_id) {
		System.out.println("http get "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		
		//if( !checkPermission(authenticated_id, StorageClient.goal.getById(goal_id).getUserId()) )
		//	return null;
		
		F filter = filter();
		filter.addFilter( uriInfo.getQueryParameters() );
		filter.addFilter("goal_id", ""+goal_id);
		filter.addFilter("user_id", ""+authenticated_id);
		return storage_client_getAll(authenticated_id, filter );
	}

	public MM getAllUnderUser(UriInfo uriInfo, int user_id) {
		System.out.println("http get "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		
		//if( !checkPermission(authenticated_id, user_id) )
		//	return null;
		
		F filter = filter();
		filter.addFilter( uriInfo.getQueryParameters() );
		filter.addFilter("user_id", ""+user_id);
		MM collection = storage_client_getAll(authenticated_id, filter );
		return collection;
	}
	
	
	
	public M getById(UriInfo uriInfo, int id) {
		System.out.println("http get "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		
		M entity = storage_client_getById(authenticated_id, id);
		
		if( !checkPermission(authenticated_id, master(entity)) )
			return null;
		
		return entity;
	}
	
	
	
	public Response post(UriInfo uriInfo, M entity) {
		System.out.println("http post "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		
		if( !checkPermission(authenticated_id, master(entity)) )
			return null;
		
		entity = storage_client_post(authenticated_id, entity );
		URI uri = URI.create( final_client().resource_url() +"/"+ entity.getId() );
		return Response.created(uri).build();
	}
	
	
	
	public M put(UriInfo uriInfo, M entity, int id) {
		System.out.println("http put "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		
		if( !checkPermission(authenticated_id, master(storage_client_getById(authenticated_id, id))) )
			return null;
		
		entity.setId(id);
		storage_client_put(authenticated_id, entity );
		return entity;
	}
	
	
	
	public void delete(UriInfo uriInfo, int id) {
		System.out.println("http delete "+uriInfo.getPath());
		int authenticated_id = authenticate(uriInfo);
		
		if( !checkPermission(authenticated_id, master(storage_client_getById(authenticated_id, id))) )
			return;
		
		storage_client_delete(authenticated_id, id );
	}
	
	
	
	
	
}
