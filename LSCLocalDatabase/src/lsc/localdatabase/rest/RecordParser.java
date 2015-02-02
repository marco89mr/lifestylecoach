package lsc.localdatabase.rest;

import java.util.List;

import lsc.localdatabase.dao.DaoFactory;
import lsc.localdatabase.dao.model.Data;
import lsc.localdatabase.dao.model.Record;
import lsc.localdatabase.dao.model.RecordCollection;
import lsc.localdatabase.dao.model.User;
import lsc.localdatabase.rest.model.LinkRest;
import lsc.localdatabase.rest.model.RecordCollectionRest;
import lsc.localdatabase.rest.model.RecordRest;
import lsc.localdatabase.rest.path.PathFactory;


public class RecordParser extends BaseParser<	Record,
												RecordCollection,
												RecordRest,
												RecordCollectionRest,
												RecordRest.Relation		> {

	
	public RecordRest toRest(Record entity) {
		RecordRest record = new RecordRest();
		record.setDate		(	entity.getDate()			);
		record.setType		(	entity.getType()			);
		record.putLink		(	getSelfLink(entity)			);
		record.putLink		(	getUserLink(entity)			);
		record.putLink		(	getDatasLink(entity)		);
		return record;
	}
	public RecordCollectionRest toRest(RecordCollection dao) {
		RecordCollectionRest rest = new RecordCollectionRest();
		for(Record d : dao)
			rest.add( toRest(d) );
		return rest;
	}
	
	public Record toDao(RecordRest entity) {
		Record record = new Record();
		record.setId		(	getId(entity)				);
		record.setUser		(	getUser(entity)				);
		record.setDate		(	entity.getDate()			);
		record.setType		(	entity.getType()			);
		return record;
	}
	public RecordCollection toDao(RecordCollectionRest rest) {
		RecordCollection dao = new RecordCollection();
		for(RecordRest d : rest)
			dao.add( toDao(d) );
		return dao;
	}
	
	
	
	// To Rest
	
	public LinkRest<RecordRest.Relation> getSelfLink(Record entity) {
		return LinkRest.create( RecordRest.Relation.self, PathFactory.record().id(entity.getId()).getCompletePath() );
	}
	
	public LinkRest<RecordRest.Relation> getUserLink(Record entity) {
		return LinkRest.create( RecordRest.Relation.user, PathFactory.user().id(entity.getUser().getId()).getCompletePath() );
	}
	
	public LinkRest<RecordRest.Relation> getDatasLink(Record entity) {
		return LinkRest.create( RecordRest.Relation.datas, PathFactory.record().id(entity.getId()).data().getCompletePath() );
	}
	
	
	
	// To Dao
	
	public User getUser(RecordRest entity){
		return DaoFactory.record.getById( getId(entity) ).getUser();
	}
	
	public List<Data> getDatas(RecordRest entity){
		return DaoFactory.record.getById( getId(entity) ).getData();
	}
	
	
}
