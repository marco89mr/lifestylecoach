package lsc.localdatabase.parser;

import lsc.localdatabase.dao.dataaccess.LocalDatabaseDataAccess;
import lsc.localdatabase.dao.model.DataDao;
import lsc.localdatabase.dao.model.DataCollectionDao;
import lsc.rest.model.Data;
import lsc.rest.model.DataCollection;


public class DataParser extends BaseParser			<	DataDao,
														DataCollectionDao,
														Data,
														DataCollection			> {
	

	
	public Data toRest(DataDao entity) {
		Data data = new Data();
		try{
		data.setName		(	entity.getName()			);
		}catch (IllegalArgumentException e){}
		try{
		data.setValue		(	entity.getValue()			);
		}catch (IllegalArgumentException e){}
		try{
		data.setId			(	entity.getId()				);
		}catch (IllegalArgumentException e){}
		try{
		if(entity.getRecord()!=null)
		data.setRecordId	(	entity.getRecord().getId()	);
		}catch (IllegalArgumentException e){}
		return data;
	}
	
	public DataDao toDao(Data entity) {
		DataDao dao = new DataDao();
		try{
		dao.setId			(	entity.getId()				);
		}catch (IllegalArgumentException e){}
		try{
		dao.setName			(	entity.getName()			);
		}catch (IllegalArgumentException e){}
		try{
		dao.setValue		(	entity.getValue()			);
		}catch (IllegalArgumentException e){}
		try{
		dao.setRecord		(	LocalDatabaseDataAccess.record.getById(entity.getRecordId())		);
		}catch (IllegalArgumentException e){}
		return dao;
	}
	
	@Override
	protected DataCollectionDao new_collection_dao() {
		return new DataCollectionDao();
	}
	@Override
	protected DataCollection new_collection() {
		return new DataCollection();
	}
	
	
	
}
