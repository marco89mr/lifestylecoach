package lsc.localdatabase.model;

import lsc.localdatabase.App;
import lsc.localdatabase.dao.LifeCoachDao;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@Entity
@Table(name="\"todo\"")
@NamedQuery(name = "ToDo.findAll", query = "SELECT d FROM ToDo d")
@XmlType(name = "todo", propOrder = { "selfLink", "userLink", "by_date", "message", "status" })
@XmlRootElement(name="todo")
public class ToDo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_toDo")
	@TableGenerator(name="sqlite_toDo", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="toDo")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"by_date\"")
	private String by_date;
	
	@Column(name = "\"message\"")
	private String message;
	
	@Column(name = "\"status\"")
	private String status;
	
	
	
	
	
	
	public ToDo() { }
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getBy_date() { return this.by_date; }
	
	public void setBy_date(String value) { this.by_date = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("self", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		System.out.println("DEBUG: selfLink");
		if(link.getRel().equals("user"))
			this.user = User.getByUrl( link.getHref() );
	}
	
	
	@XmlElement(name="link", required=true)
	public Link getUserLink() { return Link.create("user", this.getUser()._getUrl() ); }
	
	public void setUserLink(Link link) { setSelfLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return App.getBASE_URI()+"todo/"+this.getId();
	}
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static ToDo getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		ToDo entry = ToDo.getById( Integer.parseInt(id) );
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
	
	public static List<ToDo> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<ToDo> list = em.createNamedQuery("ToDo.findAll", ToDo.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static ToDoList getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		ToDoList list = new ToDoList();
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


