package lsc.localdatabase.dao;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.LifeCoachDao;
import lsc.localdatabase.model.Data;
import lsc.localdatabase.model.DataCollection;


public class DataDao {
	
	
	public static Data getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Data entry = getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Data getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Data m = em.find(Data.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static DataCollection getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DataCollection list = new DataCollection( em.createNamedQuery("Measure.findAll", Data.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static DataCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DataCollection list = new DataCollection();
		// building query
		String where = " WHERE d.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and d.record.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_id"))
			where+=" and d.record.id LIKE "+param.getFirst("record_id");
		if(param.containsKey("record_type"))
			where+=" and d.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and s.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT d FROM Data d"+where);
		TypedQuery<Data> query = em.createQuery("SELECT d FROM Data d"+where, Data.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Data save(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Data update(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
