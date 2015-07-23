package lsc.localdatabase.dao.dataaccess;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.DataDao;
import lsc.localdatabase.dao.model.DataCollectionDao;


public class DataDataAccess extends BaseDataAccess<DataDao, DataCollectionDao>{
	
	
	public DataDataAccess() {
		this.model_class = DataDao.class;
		this.model_collection_class = DataCollectionDao.class;
	}
	
	

	public DataCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" d.record.user.id = "+param.getFirst("user_id");
		}
		if(param.containsKey("record_id")) {
			if(!where.equals("")) where+=" and";
			where+=" d.record.id LIKE "+param.getFirst("record_id");
		}
		if(param.containsKey("record_type")) {
			if(!where.equals("")) where+=" and";
			where+=" d.record.type LIKE \""+param.getFirst("record_type")+"\"";
		}
		if(param.containsKey("data_name")) {
			if(!where.equals("")) where+=" and";
			where+=" s.name LIKE \""+param.getFirst("data_name")+"\"";
		}
		if(param.containsKey("fromdate")) {
			if(!where.equals("")) where+=" and";
			where+=" d.record.date > \""+param.getFirst("fromdate")+"\"";
		}
		if(param.containsKey("todate")) {
			if(!where.equals("")) where+=" and";
			where+=" d.record.date < \""+param.getFirst("todate")+"\"";
		}
		if(param.containsKey("last")) {
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		}
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT d FROM DataDao d"+where;
		return getAll(new DataCollectionDao(), personal_query);
	}
	
}
