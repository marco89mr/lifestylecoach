package lsc.localdatabase.dao.dataaccess;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.NotificationDao;
import lsc.localdatabase.dao.model.NotificationCollectionDao;


public class NotificationDataAccess extends BaseDataAccess<NotificationDao, NotificationCollectionDao>  {
	
	
	
	public NotificationDataAccess() {
		this.model_class = NotificationDao.class;
		this.model_collection_class = NotificationCollectionDao.class;
	}
	
	

	public NotificationCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" n.user.id = "+param.getFirst("user_id");
		}
		if(param.containsKey("record_type")) {
			if(!where.equals("")) where+=" and";
			where+=" n.record.type LIKE "+param.getFirst("record_type");
		}
		if(param.containsKey("data_name")) {
			if(!where.equals("")) where+=" and";
			where+=" n.name LIKE "+param.getFirst("data_name");
		}
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT n FROM NotificationDao n"+where;
		return getAll(new NotificationCollectionDao(), personal_query);
	}
	
	
}
