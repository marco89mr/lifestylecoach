package lsc.localdatabase.rest;

import lsc.localdatabase.dao.model.Base;
import lsc.localdatabase.dao.model.BaseCollection;
import lsc.localdatabase.rest.model.BaseCollectionRest;
import lsc.localdatabase.rest.model.BaseRest;
import lsc.localdatabase.rest.model.RelationInterface;


public abstract class BaseParser <	M extends Base,
						 			MM extends BaseCollection<M>,
					 				MR extends BaseRest<L>,
				 					MMR extends BaseCollectionRest<MR>,
			 						L extends RelationInterface				> {
	
	
	
	// fields

	protected Class<M> db_model_class;
	protected Class<MM> db_model_collection_class;
	protected Class<MR> xml_model_class;
	protected Class<MMR> xml_model_collection_class;
	
	
	
	// constructor
	
	public BaseParser(){ }
	
	public BaseParser(	Class<M> model_class,
						Class<MM> model_collection_class,
						Class<MR> xml_model_class,
						Class<MMR> xml_model_collection_class	) {
		this.db_model_class = model_class;
		this.db_model_collection_class = model_collection_class;
		this.xml_model_class = xml_model_class;
		this.xml_model_collection_class = xml_model_collection_class;
	}
	
	
	
	// abstract

	public abstract MM toDao(MMR collection);

	public abstract MMR toRest(MM collection);
	
	
	
	public abstract M toDao(MR rest);

	public abstract MR toRest(M dao);
	
	
	
	
	// public generic methods
	//
	
	// To Rest
	

	
	// To Dao
	
	public int getId(MR rest){
		return rest.getSelfLink()._parseId();
	}
	
	
	
	
	
	
	
	
}
