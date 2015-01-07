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
@Table(name="notification")
@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
@XmlType(name = "Notification", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="notification")
public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_notification")
	@TableGenerator(name="sqlite_notification", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="notification")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "type")
	private String type;

	@ManyToOne
	@JoinColumn(name="deadline_id",referencedColumnName="id", insertable = true, updatable = true)
	private Deadline deadline;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "status")
	private String status;
	
	
	
	
	
	
	public Notification() { }
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public Deadline getDeadline() {	return deadline; }
	
	public void setDeadline(Deadline d) { this.deadline = d; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
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


