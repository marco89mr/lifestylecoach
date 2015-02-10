package lsc.localdatabase.logic;

import java.net.URI;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import lsc.localdatabase.dao.dataaccess.BaseDataAccess;
import lsc.localdatabase.dao.model.BaseCollectionDao;
import lsc.localdatabase.dao.model.BaseDao;
import lsc.localdatabase.parser.BaseParser;
import lsc.localdatabase.rest.client.LocalDatabaseClient;
import lsc.rest.filter.LocalDatabaseFilter;
import lsc.rest.model.Base;
import lsc.rest.model.BaseCollection;
import lsc.utils.MultivaluedMapImpl;


public abstract class BaseLogic <	M extends Base,
									MM extends BaseCollection<M>,
					 				F extends LocalDatabaseFilter.BaseFilter,
					 				D extends BaseDao,
									DD extends BaseCollectionDao<D>,
					 				A extends BaseDataAccess<D, DD>,
					 				P extends BaseParser<D, DD, M, MM>,
					 				C extends LocalDatabaseClient.BaseClient<M, MM, F>			> {
	
	
	protected abstract C client();
	protected abstract A data_access();
	protected abstract P parser();
	protected abstract F filter();
	
	
	
	
	
	public MM getAll(UriInfo uriInfo) {
		return getAll(uriInfo, filter());
	}

	public MM getAll(UriInfo uriInfo, F filter) {
		System.out.println("http get "+uriInfo.getPath()+" +filter: "+filter.param_url());
		if(uriInfo==null || filter==null)
			System.out.println("ERROR Logic.getAll has a null parameter");
		MultivaluedMap<String,String> param = MultivaluedMapImpl.clone( uriInfo.getQueryParameters() );
		for(String key : filter.getFilter().keySet())
			param.putSingle( key, filter.getFilter().get(key) );
		DD dao_collection = data_access().getAll(param);
		if(dao_collection==null)
			System.out.println("ERROR Logic.getAll no data retrieved from db");
		System.out.println("DEBUG Logic.getAll 1");
		MM model_collection = parser().toRest( dao_collection );
		System.out.println("DEBUG Logic.getAll 2");
		if(model_collection==null)
			System.out.println("ERROR Logic.getAll impossible to parse collection");
		return model_collection;
	}
	
	public Response post(UriInfo uriInfo, M entity) {
		return post(uriInfo, entity, 0);
	}
	
	public Response post(UriInfo uriInfo, M entity, int id) {
		System.out.println("http post "+uriInfo.getPath());
		if(id!=0)
			entity.setId(id);
		D dao = parser().toDao( entity );
		data_access().save( dao );
		//entity = parser().toRest( dao );
		URI uri = URI.create( LocalDatabaseClient.data.resource_url() +"/"+ dao.getId() );
		return Response.created( uri ).build();
	}
	
	public M getById(UriInfo uriInfo, int id) {
		System.out.println("http get "+uriInfo.getPath());
		return parser().toRest( data_access().getById( id ) );
	}
	
	public M put(UriInfo uriInfo, M entity, int id) {
		System.out.println("http put "+uriInfo.getPath());
		D dao = parser().toDao( entity );
		dao.setId( id );
		return parser().toRest( data_access().update( dao ) );
	}
	
	public void delete(UriInfo uriInfo, int id) {
		System.out.println("http delete "+uriInfo.getPath());
		data_access().remove( data_access().getById( id ) );
	}
	
	
	
	
	
}
