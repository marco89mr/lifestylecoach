package lsc.localdatabase.model;

import java.io.Serializable;
import java.net.URI;

import javax.persistence.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.eclipse.persistence.nosql.annotations.DataFormatType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lsc.localdatabase.App;
import lsc.localdatabase.dao.LifeCoachDao;

/**
 * The persistent class for the "user" database table.
 * 
 */
@Entity
@Table(name="\"user\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u") 
@XmlRootElement(name="user")
@XmlType(name = "user", propOrder = { "selfLink", "name", "mail", "password", "birthdate", "recordsLink", "goalsLink", "notificationsLink", "todosLink" })
public class User extends TableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// For sqlite in particular, you need to use the following @GeneratedValue annotation
	// This holds also for the other tables
	// SQLITE implements auto increment ids through named sequences that are stored in a 
	// special table named "sqlite_sequence"
	@GeneratedValue(generator="sqlite_user")
	@TableGenerator(name="sqlite_user", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="user")
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"name\"")
	private String name;

	@Column(name="\"mail\"")
	private String mail;
	
    //@Temporal(TemporalType.TIMESTAMP)
    //@DateTimeFormat(pattern = "YYYY-MM-dd") only for Joda
	@Column(name="\"birthdate\"")
	private String birthdate;
	
	@Column(name="\"password\"")
	private String password;

	// mappedBy must be equal to the name of the attribute in Measure that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Record> record;

	// mappedBy must be equal to the name of the attribute in Goal that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Goal> goal;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Notification> notification;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ToDo> todo;
	
	
	
	
	
	public User() { }
	
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement(name="name")
	public String getName() { return this.name;	}
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="birthdate")
	public String getBirthdate() { return this.birthdate; }
	
	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
	
	
	@XmlElement(name="mail")
	public String getMail() { return this.mail; }
	
	public void setMail(String email) {	this.mail = email; }
	
	
	@XmlElement(name="password")
	public String getPassword() { return this.password;	}
	
	public void setPassword(String password) { this.password = password; }
	
	
	@XmlTransient
	public List<Record> getRecord() { return record; }

	public void setRecord(List<Record> param) { this.record = param;	}
	
	
	@XmlTransient
	public List<Goal> getGoal() { return goal; }

	public void setGoal(List<Goal> param) { this.goal = param; }

	
	@XmlTransient
	public List<Notification> getNotification() { return notification; }

	public void setNotification(List<Notification> param) { this.notification = param; }

	
	@XmlTransient
	public List<ToDo> getTodo() { return todo; }

	public void setTodo(List<ToDo> param) { this.todo = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("self", this._getUrl() );	}
	
	public void setSelfLink(Link link) { System.out.println("DEBUG: User.setSelfLink()"); }
	
	
	@XmlElement(name="link")
	public Link getRecordsLink() { return Link.create("records", this._getUrl()+"/record"); }
	
	public void setRecordsLink(LinkContainer lc) { }
	
	
	@XmlElement(name="link")
	public Link getGoalsLink() { return Link.create("goals", this._getUrl()+"/goal"); }
	
	public void setGoalsLink(Link lc) { }
	
	
	@XmlElement(name="link")
	public Link getNotificationsLink() { return Link.create("notifications", this._getUrl()+"/notification"); }
	
	public void setNotificationsLink(Link lc) { }
	
	
	@XmlElement(name="link")
	public Link getTodosLink() { return Link.create("todos", this._getUrl()+"/todo"); }
	
	public void setTodosLink(Link lc) { }
	
	
	
	// Accessory methods
	// 
	
	public Date _getBirthdate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.parse(this.birthdate);
	}
	
	public void _setBirthdate(Date birthdate) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    this.birthdate = sdf.format(birthdate);
	}
	
	public String _getUrl() {
		return App.getBASE_URI()+"user/"+this.id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static User getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		User entry = User.getById( Integer.parseInt(id) );
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
	
	public static UserList getAll() {
		System.out.println("--> model.user.getAll()");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		UserList list = new UserList( em.createNamedQuery("User.findAll", User.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static UserList getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.user.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		UserList list = new UserList();
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
