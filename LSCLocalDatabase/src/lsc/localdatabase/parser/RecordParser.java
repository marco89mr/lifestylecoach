package lsc.localdatabase.parser;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.RecordDao;
import lsc.localdatabase.dao.model.RecordCollectionDao;
import lsc.rest.model.Record;
import lsc.rest.model.RecordCollection;


public class RecordParser extends BaseParser<	RecordDao,
												RecordCollectionDao,
												Record,
												RecordCollection	> {

	
	public Record toRest(RecordDao entity) {
		Record record = new Record();
		try{
		record.setDate		(	entity.getDate()			);
		}catch (IllegalArgumentException e){}
		try{
		record.setType		(	entity.getType()			);
		}catch (IllegalArgumentException e){}
		try{
		record.setId		(	entity.getId()				);
		}catch (IllegalArgumentException e){}
		try{
		record.setUserId	(	entity.getUser().getId()	);
		}catch (IllegalArgumentException e){}
		return record;
	}
	
	public RecordDao toDao(Record entity) {
		RecordDao recordDao = new RecordDao();
		try{
		recordDao.setId			(	entity.getId()				);
		}catch (IllegalArgumentException e){}
		try{
		recordDao.setUser		(	LocalDatabaseDataAccess.user.getById( entity.getUserId() )	);
		}catch (IllegalArgumentException e){}
		try{
		recordDao.setDate		(	entity.getDate()			);
		}catch (IllegalArgumentException e){}
		try{
		recordDao.setType		(	entity.getType()			);
		}catch (IllegalArgumentException e){}
		return recordDao;
	}
	
	@Override
	protected RecordCollectionDao new_collection_dao() {
		return new RecordCollectionDao();
	}

	@Override
	protected RecordCollection new_collection() {
		return new RecordCollection();
	}
	
	
	
	
}
