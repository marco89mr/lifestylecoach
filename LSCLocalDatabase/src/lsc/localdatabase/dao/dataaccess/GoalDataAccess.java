package lsc.localdatabase.dao.dataaccess;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.GoalDao;
import lsc.localdatabase.dao.model.GoalCollectionDao;


public class GoalDataAccess extends BaseDataAccess<GoalDao, GoalCollectionDao>  {
	
	
	public GoalDataAccess() {
		this.model_class = GoalDao.class;
		this.model_collection_class = GoalCollectionDao.class;
	}
	
	
	
	public GoalCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" g.user.id = "+param.getFirst("user_id");
		}
		if(param.containsKey("record_type")) {
			if(!where.equals("")) where+=" and";
			where+=" g.record.type LIKE "+param.getFirst("record_type");
		}
		if(param.containsKey("data_name")) {
			if(!where.equals("")) where+=" and";
			where+=" g.name LIKE "+param.getFirst("data_name");
		}
		if(param.containsKey("last")) {
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		}
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT g FROM GoalDao g"+where;
		return getAll(new GoalCollectionDao(), personal_query);
	}
	
}
