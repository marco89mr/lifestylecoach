package lsc.localdatabase.dao.dataaccess;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.RecordDao;
import lsc.localdatabase.dao.model.RecordCollectionDao;


public class RecordDataAccess extends BaseDataAccess<RecordDao, RecordCollectionDao> {
	
	
	public RecordDataAccess() {
		this.model_class = RecordDao.class;
		this.model_collection_class = RecordCollectionDao.class;
	}
	
	
	
	public RecordCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" r.user.id = "+param.getFirst("user_id")+"";
		}
		if(param.containsKey("type")) {
			if(!where.equals("")) where+=" and";
			where+=" r.type LIKE \""+param.getFirst("type")+"\"";
		}
		if(param.containsKey("fromdate")) {
			if(!where.equals("")) where+=" and";
			where+=" r.date > \""+param.getFirst("fromdate")+"\"";
		}
		if(param.containsKey("todate")) {
			if(!where.equals("")) where+=" and";
			where+=" r.date < \""+param.getFirst("todate")+"\"";
		}
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT r FROM RecordDao r"+where;
		return getAll(new RecordCollectionDao(), personal_query);
	}
	
	
}
