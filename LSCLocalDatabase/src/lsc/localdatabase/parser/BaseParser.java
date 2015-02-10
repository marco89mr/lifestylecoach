package lsc.localdatabase.parser;

import lsc.localdatabase.dao.model.BaseDao;
import lsc.localdatabase.dao.model.BaseCollectionDao;
import lsc.rest.model.Base;
import lsc.rest.model.BaseCollection;


public abstract class BaseParser <	D extends BaseDao,
						 			DD extends BaseCollectionDao<D>,
					 				M extends Base,
				 					MM extends BaseCollection<M>			> {
		
	// properties / utils
	protected abstract DD new_collection_dao();
	protected abstract MM new_collection();
	
	
	
	// abstract
	public abstract D toDao(M rest);
	public abstract M toRest(D dao);
	
	
	
	// public generic methods
	
	public DD toDao(MM rest){
		DD dao = new_collection_dao();
		for(M d : rest)
			dao.add( toDao(d) );
		return dao;
	}

	public MM toRest(DD dao){
		MM rest = new_collection();
		for(D d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	
	
}
