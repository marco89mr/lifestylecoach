package lsc.localdatabase.dao;

import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import lsc.localdatabase.dao.LifeCoachDao;
import lsc.localdatabase.model.Deadline;
import lsc.localdatabase.model.DeadlineCollection;


public class DeadlineDao {
	
	
	public static Deadline getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Deadline entry = getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Deadline getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Deadline m = em.find(Deadline.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static DeadlineCollection getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DeadlineCollection list = new DeadlineCollection( em.createNamedQuery("Deadline.findAll", Deadline.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static DeadlineCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DeadlineCollection list = new DeadlineCollection();
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
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Deadline save(Deadline m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Deadline update(Deadline m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Deadline m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}
