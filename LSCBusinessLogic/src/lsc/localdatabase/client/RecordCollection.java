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


@XmlRootElement(name="records")
public class RecordCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Record> list;	
	
	public RecordCollection() { }
	
	public RecordCollection(List<Record> list) {
		this.list = list;
	}

	@XmlElement(name="record")
	public List<Record> getList() { return this.list; }
	public void setList(List<Record> param) { this.list = param; }
	
	
	
	

	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Record getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Record entry = getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Record getById(int id) {
		System.out.println("--> model.record.getById("+id+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Record m = em.find(Record.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static RecordCollection getAll() {
		System.out.println("--> model.record.getAll()");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		RecordCollection list = new RecordCollection( em.createNamedQuery("Record.findAll", Record.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static RecordCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		RecordCollection list = new RecordCollection();
		// building query
		String where = " WHERE r.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and r.user.id = "+param.getFirst("user_id")+"";
		if(param.containsKey("type"))
			where+=" and r.type LIKE \""+param.getFirst("type")+"\"";
		if(param.containsKey("last"))
			where+=" and r.mail LIKE \""+param.getFirst("last")+"\"";
		if(param.containsKey("fromdate"))
			where+=" and r.date > \""+param.getFirst("fromdate")+"\"";
		if(param.containsKey("todate"))
			where+=" and r.date < \""+param.getFirst("todate")+"\"";
		
		System.out.println("--> "+"SELECT r FROM Record r"+where);
		TypedQuery<Record> query = em.createQuery("SELECT r FROM Record r"+where, Record.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Record save(Record r) {
		System.out.println("--> model.record.save("+r.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(r);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return r;
	}
	
	public static Record update(Record r) {
		System.out.println("--> model.record.update("+r.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		r=em.merge(r);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return r;
	}
	
	public static void remove(Record r) {
		System.out.println("--> model.record.remove("+r.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    r=em.merge(r);
	    em.remove(r);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
