package lsc.localdatabase.client;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lsc.localdatabase.dao.LifeCoachDao;


@XmlRootElement(name="notifications")
public class NotificationCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Notification> list;	
	
	public NotificationCollection() { }
	
	public NotificationCollection(List<Notification> list) {
		this.list = list;
	}

	@XmlElement(name="notification")
	public List<Notification> getList() { return this.list; }
	public void setList(List<Notification> param) { this.list = param; }
	
	
	

	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Notification getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Notification entry = getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Notification getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Notification m = em.find(Notification.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static List<Notification> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Notification> list = em.createNamedQuery("Notification.findAll", Notification.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static NotificationCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.goal.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		NotificationCollection list = new NotificationCollection();
		// building query
		String where = " WHERE n.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and n.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_type"))
			where+=" and n.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and n.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT n FROM Goal n"+where);
		TypedQuery<Notification> query = em.createQuery("SELECT n FROM Notification n"+where, Notification.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Notification save(Notification m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Notification update(Notification m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Notification m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}
