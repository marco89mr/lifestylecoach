package lsc.localdatabase.rest;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Data;
import lsc.localdatabase.dao.model.DataCollection;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.rest.model.DataCollectionRest;
import lsc.localdatabase.rest.model.DataRest;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.path.PathFactory;


public class DataParser extends BaseParser			<	Data,
														DataCollection,
														DataRest,
														DataCollectionRest,
														DataRest.Relation			> {
	

	
	public DataRest toRest(Data entity) {
		DataRest data = new DataRest();
		data.setName		(	entity.getName()						);
		data.setValue		(	entity.getValue()						);
		data.putLink		(	getSelfLink(entity)						);
		data.putLink		(	getRecordLink(entity)					);
		return data;
	}
	public DataCollectionRest toRest(DataCollection dao) {
		DataCollectionRest rest = new DataCollectionRest();
		for(Data d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public Data toDao(DataRest entity) {
		Data dao = new Data();
		dao.setId			(	getId(entity)				);
		dao.setName			(	entity.getName()			);
		dao.setValue		(	entity.getValue()			);
		dao.setRecord		(	getRecord(entity)			);
		return dao;
	}
	public DataCollection toDao(DataCollectionRest rest) {
		DataCollection dao = new DataCollection();
		for(DataRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<DataRest.Relation> getSelfLink(Data entity) {
		return LinkRest.create( DataRest.Relation.self, PathFactory.data().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<DataRest.Relation> getRecordLink(Data entity) {
		return LinkRest.create( DataRest.Relation.record, PathFactory.record().id(entity.getRecord().getId()).getCompletePath() );
	}
	
	
	
	// To Dao
	
	public Record getRecord(DataRest entity){
		return DaoFactory.data.getById( getId(entity) ).getRecord();
	}
	
}
