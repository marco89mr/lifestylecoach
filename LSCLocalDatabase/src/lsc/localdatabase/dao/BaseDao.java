package lsc.localdatabase.dao;

import java.net.URI;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lsc.localdatabase.dao.model.Base;
import lsc.localdatabase.dao.model.BaseCollection;


public class BaseDao <M extends Base, MM extends BaseCollection<M>> {

	
	
	// fields
	
	protected Class<M> model_class;
	protected Class<MM> model_collection_class;
	
	
	
	// constructor
	
	public BaseDao(){ }
	
	public BaseDao(Class<M> model_class, Class<MM> model_collection_class){
		this.model_class = model_class; 
		this.model_collection_class = model_collection_class; 
	}
	
	
	
	// public methods
	
	public M getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		M entry = getById( Integer.parseInt(id) );
		return entry;
		/*
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
		 */
	}
	
	
	public M getById(int id) {
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		M m = em.find(model_class, id);
		LifeStyleCoachDao.instance.closeConnections(em);
		return m;
	}
	
	
	public MM getAll() {
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		@SuppressWarnings("unchecked")
		MM list = (MM) new ArrayList<M>();
		list.addAll( em.createNamedQuery(model_class.getName()+".findAll", model_class).getResultList() );
	    LifeStyleCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	
	public M save(M m) {
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		//System.out.println("BaseDao.save id:"+m.getId());
		tx.commit();
	    LifeStyleCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	
	public M update(M m) {
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeStyleCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	
	public void remove(M m) {
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeStyleCoachDao.instance.closeConnections(em);
	}
}
