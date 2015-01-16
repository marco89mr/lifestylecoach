package lsc.localdatabase.dao;

import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import lsc.localdatabase.dao.LifeCoachDao;
import lsc.localdatabase.model.Goal;
import lsc.localdatabase.model.GoalCollection;


public class GoalDao {
	
	
	public static Goal getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Goal entry = getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Goal getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Goal m = em.find(Goal.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static GoalCollection getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		GoalCollection list = new GoalCollection( em.createNamedQuery("Goal.findAll", Goal.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static GoalCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.goal.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		GoalCollection list = new GoalCollection();
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
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Goal save(Goal m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Goal update(Goal m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Goal m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}
