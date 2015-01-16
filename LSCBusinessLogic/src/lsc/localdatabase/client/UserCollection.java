package lsc.localdatabase.client;

import java.io.Serializable;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lsc.localdatabase.dao.LifeCoachDao;


@XmlRootElement(name="users")
public class UserCollection extends LocalDatabaseClient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<User> list;	
	
	public UserCollection() { }
	
	public UserCollection(List<User> list) {
		this.list = list;
	}
	
	@XmlElement(name="user")
	public List<User> getList() { return this.list; }
	public void setList(List<User> param) { this.list = param; }
	
	
	
	
	// Database operations
	// 
	
	public static User getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		User entry = UserCollection.getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static User getById(int id) {
		//System.out.println("--> model.user.getById("+id+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		User u = em.find(User.class, id);
		//System.out.println("--> model.getById() name:"+u.getName()+" birthdate:"+u.getBirthdate());
		LifeCoachDao.instance.closeConnections(em);
		return u;
	}
	
	public static UserCollection getAll() {
		System.out.println("--> model.user.getAll()");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		UserCollection list = new UserCollection( em.createNamedQuery("User.findAll", User.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static UserCollection getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.user.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		UserCollection list = new UserCollection();
		// building query
		String where = " WHERE u.id > 0";
		
		if(param.containsKey("name"))
			where+=" and u.name LIKE \""+param.getFirst("name")+"\"";
		if(param.containsKey("mail"))
			where+=" and u.mail LIKE \""+param.getFirst("mail")+"\"";
		if(param.containsKey("olderthan")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    int years = Integer.parseInt( param.getFirst("olderthan") );
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, -years);
		    String string_date = sdf.format( cal.getTime() );
			where+=" and u.birthdate < \""+string_date+"\"";
		}
		if(param.containsKey("youngerthan")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    int years = Integer.parseInt( param.getFirst("olderthan") );
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, -years);
		    String string_date = sdf.format( cal.getTime() );
			where+=" and u.birthdate > \""+string_date+"\"";
		}
		if(param.containsKey("bornbeforedate"))
			where+=" and u.birthdate < \""+param.getFirst("bornbeforedate")+"\"";
		if(param.containsKey("bornafterdate"))
			where+=" and u.birthdate > \""+param.getFirst("bornafterdate")+"\"";
		
		System.out.println("--> "+"SELECT u FROM User u"+where);
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u"+where, User.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static User save(User u) {
		System.out.println("--> model.user.save("+u.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(u);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return u;
	}
	
	public static User update(User u) {
		System.out.println("--> model.user.update("+u.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		u=em.merge(u);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return u;
	}
	
	public static void remove(User u) {
		System.out.println("--> model.user.remove("+u.getId()+")");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    u=em.merge(u);
	    em.remove(u);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
