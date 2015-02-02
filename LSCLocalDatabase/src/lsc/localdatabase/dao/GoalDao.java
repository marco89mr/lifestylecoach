package lsc.localdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.Goal;
import lsc.localdatabase.dao.model.GoalCollection;


public class GoalDao extends BaseDao<Goal, GoalCollection>  {
	
	
	public GoalDao() {
		this.model_class = Goal.class;
		this.model_collection_class = GoalCollection.class;
	}
	
	
	
	public GoalCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.goal.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE g.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and g.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_type"))
			where+=" and g.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and g.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT g FROM Goal g"+where);
		TypedQuery<Goal> query = em.createQuery("SELECT g FROM Goal g"+where, Goal.class);
		// querying
		GoalCollection list = new GoalCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
}
