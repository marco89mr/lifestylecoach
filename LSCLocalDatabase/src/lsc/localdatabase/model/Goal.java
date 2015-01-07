package lsc.localdatabase.model;

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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@Entity
@Table(name="goal")
@NamedQuery(name = "Goal.findAll", query = "SELECT g FROM Goal g")
@XmlType(name = "Goal", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="Goal")
public class Goal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_goal")
	@TableGenerator(name="sqlite_goal", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="goal")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "operator")
	private String operator;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "function")
	private String function;
	
	@Column(name = "reference")
	private String reference;
	
	@Column(name = "perc")
	private String perc;
	
	@Column(name = "repeat")
	private String repeat;
	
	@Column(name = "days")
	private String days;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="goal",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Deadline> deadline;
	
	
	
	
	
	public Goal() { }
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
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
	
	
	public List<Deadline> getDeadline() { return this.deadline; }
	
	public void setDeadline(List<Deadline> param) { this.deadline = param; }
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Goal getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Goal m = em.find(Goal.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static List<Goal> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Goal> list = em.createNamedQuery("Goal.findAll", Goal.class).getResultList();
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


