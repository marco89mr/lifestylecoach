package lsc.localdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.ToDo;
import lsc.localdatabase.dao.model.ToDoCollection;


public class ToDoDao extends BaseDao<ToDo, ToDoCollection> {
	
	
	public ToDoDao() {
		this.model_class = ToDo.class;
		this.model_collection_class = ToDoCollection.class;
	}
	
	
	
	public ToDoCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE t.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and t.user.id = "+param.getFirst("user_id")+"";
		if(param.containsKey("type"))
			where+=" and t.type LIKE \""+param.getFirst("type")+"\"";
		if(param.containsKey("last"))
			where+=" and t.mail LIKE \""+param.getFirst("last")+"\"";
		if(param.containsKey("fromdate"))
			where+=" and t.date > \""+param.getFirst("fromdate")+"\"";
		if(param.containsKey("todate"))
			where+=" and t.date < \""+param.getFirst("todate")+"\"";
		
		System.out.println("--> "+"SELECT t FROM Record t"+where);
		TypedQuery<ToDo> query = em.createQuery("SELECT t FROM ToDo t"+where, ToDo.class);
		// querying
		ToDoCollection list = new ToDoCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}


	
	
}
