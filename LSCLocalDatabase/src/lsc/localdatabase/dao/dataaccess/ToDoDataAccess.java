package lsc.localdatabase.dao.dataaccess;

import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.ToDoDao;
import lsc.localdatabase.dao.model.ToDoCollectionDao;


public class ToDoDataAccess extends BaseDataAccess<ToDoDao, ToDoCollectionDao> {
	
	
	public ToDoDataAccess() {
		this.model_class = ToDoDao.class;
		this.model_collection_class = ToDoCollectionDao.class;
	}
	
	
	
	public ToDoCollectionDao getAll(MultivaluedMap<String,String> param) {
		String where = "";
		if(param.containsKey("user_id")) {
			if(!where.equals("")) where+=" and";
			where+=" t.user.id = "+param.getFirst("user_id")+"";
		}
		if(param.containsKey("type")) {
			if(!where.equals("")) where+=" and";
			where+=" t.type LIKE \""+param.getFirst("type")+"\"";
		}
		if(param.containsKey("fromdate")) {
			if(!where.equals("")) where+=" and";
			where+=" t.date >= \""+param.getFirst("fromdate")+"\"";
		}
		if(param.containsKey("todate")) {
			if(!where.equals("")) where+=" and";
			where+=" t.date <= \""+param.getFirst("todate")+"\"";
		}
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		if(!where.equals(""))
			where = " WHERE" + where;
		String personal_query = "SELECT t FROM ToDoDao t"+where;
		return getAll(new ToDoCollectionDao(), personal_query);
	}


	
	
}
