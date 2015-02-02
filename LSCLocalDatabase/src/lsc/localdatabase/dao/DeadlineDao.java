package lsc.localdatabase.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeStyleCoachDao;
import lsc.localdatabase.dao.model.Deadline;
import lsc.localdatabase.dao.model.DeadlineCollection;


public class DeadlineDao extends BaseDao<Deadline, DeadlineCollection>{
	
	
	public DeadlineDao() {
		this.model_class = Deadline.class;
		this.model_collection_class = DeadlineCollection.class;
	}
	
	
	
	public DeadlineCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		// building query
		String where = " WHERE d.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and d.goal.user.id = "+param.getFirst("user_id");
		if(param.containsKey("goal_id"))
			where+=" and d.goal.id LIKE "+param.getFirst("goal_id");
		if(param.containsKey("data_name"))
			where+=" and d.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT d FROM Deadline d"+where);
		TypedQuery<Deadline> query = em.createQuery("SELECT d FROM Deadline d"+where, Deadline.class);
		// querying
		DeadlineCollection list = new DeadlineCollection();
		list.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
}
