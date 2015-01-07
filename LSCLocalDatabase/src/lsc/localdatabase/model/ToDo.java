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
@Table(name="ToDo")
@NamedQuery(name = "ToDo.findAll", query = "SELECT d FROM ToDo d")
@XmlType(name = "ToDo", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="ToDo")
public class ToDo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_toDo")
	@TableGenerator(name="sqlite_toDo", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="toDo")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "by_date")
	private String by_date;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "status")
	private String status;
	
	
	
	
	
	
	public ToDo() { }
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getBy_date() { return this.by_date; }
	
	public void setBy_date(String value) { this.by_date = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
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


