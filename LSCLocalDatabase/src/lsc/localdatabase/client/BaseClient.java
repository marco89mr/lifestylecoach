package lsc.localdatabase.client;

import lsc.localdatabase.rest.model.BaseCollectionRest;
import lsc.localdatabase.rest.model.BaseRest;
import lsc.localdatabase.rest.model.RelationInterface;
import lsc.localdatabase.rest.path.Path;


public class BaseClient <	P extends Path.BasePath,
							M extends BaseRest<L>,
							MM extends BaseCollectionRest<M>,
							L extends RelationInterface						> {
	
	
	
	// fields
	
	public Class<M> model_class;
	public Class<MM> model_collection_class;
	public P path;
	
	
	
	// constructor
	
	public BaseClient(Class<M> model_class, Class<MM> model_collection_class, P path){
		this.model_class = model_class;
		this.model_collection_class = model_collection_class;
		this.path = path;
	}
	
	
	// public methods: API Calls
		
	
	
	public M getByUrl(String url) {
		return ClientUtils.<M>http_get(this.model_class, url);
	}
	
	public M getById(int id) {
		return ClientUtils.<M>http_get(model_class, path.getCompletePath() + "/" + id );
	}
	
	public MM getAll() {
		return ClientUtils.<MM>http_get(model_collection_class, path.getCompletePath() );
	}
	
	public MM getAll(P path) {
		return ClientUtils.<MM>http_get(model_collection_class, path.getCompletePath());
	}
	
	public void post(M x) {
		ClientUtils.<M>http_post(model_class, path.getCompletePath(), x);
	}
	
	public void put(M x) {
		ClientUtils.<M>http_put(model_class, x.getSelfLink().getHref(), x);
	}
	
	public int delete(M x) {
		return ClientUtils.http_delete( x.getSelfLink().getHref() );
	}
	
	
	
	
}
