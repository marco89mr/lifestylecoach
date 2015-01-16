package lsc.localdatabase.dao;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeCoachDao;
import lsc.localdatabase.model.ToDo;
import lsc.localdatabase.model.ToDoCollection;


public class ToDoDao {
	
	
	public static ToDo getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		ToDo entry = getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static ToDo getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		ToDo m = em.find(ToDo.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static ToDoCollection getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		ToDoCollection list = new ToDoCollection( em.createNamedQuery("ToDo.findAll", ToDo.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static ToDoCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		ToDoCollection list = new ToDoCollection();
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
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static ToDo save(ToDo m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static ToDo update(ToDo m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(ToDo m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}
