package lsc.localdatabase.model;

import lsc.localdatabase.App;
import lsc.localdatabase.dao.LifeCoachDao;

import java.io.Serializable;
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

import java.net.URI;


@Entity
@Table(name="\"deadline\"")
@NamedQuery(name = "Deadline.findAll", query = "SELECT d FROM Deadline d")
//@XmlType(name = "Deadline", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="deadline")
public class Deadline implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_deadline")
	@TableGenerator(name="sqlite_deadline", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="deadline")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"goal_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private Goal goal;
	
	@Column(name = "\"start_date\"")
	private String start_date;
	
	@Column(name = "\"end_date\"")
	private String end_date;
	
	@Column(name = "\"status\"")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="\"record_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private Record record;
	
	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="deadline",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Notification> notification;
	
	
	
	
	
	public Deadline() { }
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public Goal getGoal() { return goal; }

	public void setGoal(Goal goal) { this.goal = goal; }
	
	
	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String value) { this.start_date = value; }
	
	
	public String getEnd_date() { return this.end_date; }
	
	public void setEnd_date(String value) { this.end_date = value; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	

	@XmlTransient
	public Record getRecord() { return this.record; }
	
	public void setRecord(Record x) { this.record = x; }
	

	@XmlTransient
	public List<Notification> getNotification() { return this.notification; }
	
	public void setDeadline(List<Notification> param) { this.notification = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("deadline", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		if(link.getRel().equals("goal"))
			this.goal = Goal.getByUrl( link.getHref() );
		if(link.getRel().equals("record"))
			this.record = Record.getByUrl( link.getHref() );
	}
	
	
	@XmlElement(name="link")
	public Link getDeadlineLink() { return Link.create("goal", this.goal._getUrl() ); }
	
	public void setDeadlineLink(Link link) { }
	
	
	@XmlElement(name="link")
	public Link getRecordLink() { return Link.create("record", this.record._getUrl() ); }
	
	public void setRecordLink(Link link) { }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return App.getBASE_URI()+"deadline/"+this.id;
	}
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Deadline getByUrl(String url) {
		URI uri = URI.create( url );
		String path = uri.getPath();
		int slash = path.lastIndexOf("/");
		String id = path.substring(slash+1);
		Deadline entry = Deadline.getById( Integer.parseInt(id) );
		// check
		if(entry!=null && entry._getUrl().equals( url ) )
			return entry;
		else
			return null;
	}
	
	public static Deadline getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Deadline m = em.find(Deadline.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static DeadlineList getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DeadlineList list = new DeadlineList( em.createNamedQuery("Deadline.findAll", Deadline.class).getResultList() );
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static DeadlineList getAll(MultivaluedMap<String,String> param) {
		System.out.println("--> model.record.getAll(filters)");
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		DeadlineList list = new DeadlineList();
		// building query
		String where = " WHERE d.id > 0";
		
		if(param.containsKey("user_id"))
			where+=" and d.goal.user.id = "+param.getFirst("user_id");
		if(param.containsKey("goal_id"))
			where+=" and d.goal.id LIKE "+param.getFirst("goal_id");
		if(param.containsKey("data_name"))
			where+=" and d.name LIKE "+param.getFirst("data_name");
		if(param.containsKey("last"))
			where+=" ORDER BY id DESC LIMIT 0, "+param.getFirst("last");
		
		System.out.println("--> "+"SELECT d FROM Deadline d"+where);
		TypedQuery<Deadline> query = em.createQuery("SELECT d FROM Deadline d"+where, Deadline.class);
		// querying
		list.setList( query.getResultList() );
		LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Deadline save(Deadline m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Deadline update(Deadline m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Deadline m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}


