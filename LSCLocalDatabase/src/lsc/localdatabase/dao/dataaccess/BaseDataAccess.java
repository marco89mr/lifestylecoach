package lsc.localdatabase.dao.dataaccess;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;

import lsc.localdatabase.dao.model.BaseCollectionDao;
import lsc.localdatabase.dao.model.BaseDao;


public abstract class BaseDataAccess <M extends BaseDao, MM extends BaseCollectionDao<M>> {

	
	
	// fields
	
	protected Class<M> model_class;
	protected Class<MM> model_collection_class;
	
	
	
	// constructor
	
	public BaseDataAccess(){ }
	
	public BaseDataAccess(Class<M> model_class, Class<MM> model_collection_class){
		this.model_class = model_class; 
		this.model_collection_class = model_collection_class; 
	}
	
	
	
	// public methods
	
	public abstract MM getAll(MultivaluedMap<String,String> param);
	
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
	
	/*
	public MM getAll(MM empty_collection) {
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		empty_collection.clear();
		empty_collection.addAll( em.createNamedQuery(model_class.getName()+".findAll", model_class).getResultList() );
	    LifeStyleCoachDao.instance.closeConnections(em);
	    return empty_collection;
	}
	*/
	
	public MM getAll(MM empty_collection, String personal_query) {
		System.out.println("--> DataAccess.getAll( query: "+personal_query+" )");
		EntityManager em = LifeStyleCoachDao.instance.createEntityManager();
		TypedQuery<M> query = em.createQuery(personal_query, model_class);
		empty_collection.clear();
		empty_collection.addAll( query.getResultList() );
		LifeStyleCoachDao.instance.closeConnections(em);
	    return empty_collection;
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
