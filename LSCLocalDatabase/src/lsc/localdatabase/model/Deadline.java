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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


@Entity
@Table(name="deadline")
@NamedQuery(name = "Deadline.findAll", query = "SELECT d FROM Deadline d")
@XmlType(name = "Deadline", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="Goal")
public class Deadline implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_deadline")
	@TableGenerator(name="sqlite_deadline", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="deadline")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="goal_id",referencedColumnName="id", insertable = true, updatable = true)
	private Goal goal;
	
	@Column(name = "start_date")
	private String start_date;
	
	@Column(name = "end_date")
	private String end_date;
	
	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="measure_id",referencedColumnName="id", insertable = true, updatable = true)
	private Record record;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="deadline",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Notification> notification;
	
	
	
	
	
	public Deadline() { }
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public Goal getGoal() { return goal; }

	public void setGoal(Goal goal) { this.goal = goal; }
	
	
	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String value) { this.start_date = value; }
	
	
	public String getEnd_date() { return this.end_date; }
	
	public void setEnd_date(String value) { this.end_date = value; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	public Record getMeasure() { return this.record; }
	
	public void setMeasure(Record x) { this.record = x; }
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Deadline getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Deadline m = em.find(Deadline.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static List<Deadline> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Deadline> list = em.createNamedQuery("Deadline.findAll", Deadline.class).getResultList();
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


