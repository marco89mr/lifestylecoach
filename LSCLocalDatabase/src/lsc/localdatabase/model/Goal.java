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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@Entity
@Table(name="\"goal\"")
@NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g")
//@XmlType(name = "goal", propOrder = { "selfLink", "userLink", "record_type", "data_name", "operator", "value", "function", "reference", "perc", "repeat", "days", "deadlineLink" })
@XmlRootElement(name="goal")
public class Goal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_goal")
	@TableGenerator(name="sqlite_goal", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="goal")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"record_type\"")
	private String record_type;
	
	@Column(name = "\"data_name\"")
	private String data_name;
	
	@Column(name = "\"operator\"")
	private String operator;
	
	@Column(name = "\"value\"")
	private String value;
	
	@Column(name = "\"function\"")
	private String function;
	
	@Column(name = "\"reference\"")
	private String reference;
	
	@Column(name = "\"perc\"")
	private String perc;
	
	@Column(name = "\"repeat\"")
	private String repeat;
	
	@Column(name = "\"days\"")
	private String days;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="goal",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Deadline> deadline;
	
	
	
	
	
	public Goal() { }
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getRecord_type() { return this.record_type; }
	
	public void setRecord_type(String value) { this.record_type = value; }
	
	
	public String getData_name() { return this.data_name; }
	
	public void setData_name(String value) { this.data_name = value; }
	
	
	public String getOperator() { return this.operator; }
	
	public void setOperator(String operator) { this.operator = operator; }
	
	
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	public String getFunction() { return this.function; }
	
	public void setFunction(String x) { this.function = x; }
	
	
	public String getReference() { return this.reference; }
	
	public void setReference(String x) { this.reference = x; }
	
	
	public String getPerc() { return this.perc; }
	
	public void setPerc(String x) { this.perc = x; }
	
	
	public String getReapeat() { return this.repeat; }
	
	public void setReapeat(String x) { this.repeat = x; }
	
	
	public String getDays() { return this.days; }
	
	public void setDays(String x) { this.days = x; }
	

	@XmlTransient
	public List<Deadline> getDeadline() { return this.deadline; }
	
	public void setDeadline(List<Deadline> param) { this.deadline = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("self", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		if(link.getRel().equals("user"))
			this.user = User.getByUrl( link.getHref() );
	}
	
	@XmlElement(name="link")
	public Link getUserLink() { return Link.create("user", this.user._getUrl() ); }
	
	public void setUserLink(Link link) { setSelfLink(link); }
	
	
	@XmlElement(name="link")
	public Link getDeadlineLink() { return Link.create("deadlines", this._getUrl()+"/deadline"); }
	
	public void setDeadlineLink(Link link) { setSelfLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return App.getBASE_URI()+"goal/"+this.getId();
	}
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Goal getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Goal entry = Goal.getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Goal getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Goal m = em.find(Goal.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static GoalList getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		GoalList list = new GoalList( em.createNamedQuery("Goal.findAll", Goal.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static GoalList getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.goal.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		GoalList list = new GoalList();
		// building query
		String where = " WHERE g.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and g.user.id = "+param.getFirst("user_id");
		if(param.containsKey("record_type"))
			where+=" and g.record.type LIKE "+param.getFirst("record_type");
		if(param.containsKey("data_name"))
			where+=" and g.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT g FROM Goal g"+where);
		TypedQuery<Goal> query = em.createQuery("SELECT g FROM Goal g"+where, Goal.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Goal save(Goal m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Goal update(Goal m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Goal m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}


