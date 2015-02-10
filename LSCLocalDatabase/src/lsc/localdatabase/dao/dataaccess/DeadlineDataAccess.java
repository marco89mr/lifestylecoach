package lsc.localdatabase.dao.dataaccess;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.DeadlineDao;
import lsc.localdatabase.dao.model.DeadlineCollectionDao;


public class DeadlineDataAccess extends BaseDataAccess<DeadlineDao, DeadlineCollectionDao>{
	
	
	public DeadlineDataAccess() {
		this.model_class = DeadlineDao.class;
		this.model_collection_class = DeadlineCollectionDao.class;
	}
	
	
	
	public DeadlineCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" d.goal.user.id = "+param.getFirst("user_id");
		}
		if(param.containsKey("goal_id")) {
			if(!where.equals("")) where+=" and";
			where+=" d.goal.id LIKE "+param.getFirst("goal_id");
		}
		if(param.containsKey("record_type")) {
			if(!where.equals("")) where+=" and";
			where+=" d.goal.record_type LIKE "+param.getFirst("record_type");
		}
		if(param.containsKey("data_name")) {
			if(!where.equals("")) where+=" and";
			where+=" d.name LIKE "+param.getFirst("data_name");
		}
		if(param.containsKey("status")) {
			if(!where.equals("")) where+=" and";
			where+=" d.status LIKE \""+param.getFirst("status")+"\"";
		}
		if(param.containsKey("by_date")) {
			if(!where.equals("")) where+=" and";
			where+=" d.end_date < \""+param.getFirst("by_date")+"\"";
		}
		if(param.containsKey("since_date")) {
			if(!where.equals("")) where+=" and";
			where+=" d.start_date > \""+param.getFirst("since_date")+"\"";
		}
		if(param.containsKey("include_date")) {
			if(!where.equals("")) where+=" and";
			where+=" d.start_date < \""+param.getFirst("include_date")+"\"";
			where+=" and d.end_date > \""+param.getFirst("include_date")+"\"";
		}
		if(param.containsKey("last")) {
			if(!where.equals("")) where+=" and";
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		}
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT d FROM DeadlineDao d"+where;
		return getAll(new DeadlineCollectionDao(), personal_query);
	}
	
}
