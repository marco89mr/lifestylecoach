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
@Table(name="Record")
@NamedQuery(name = "Record.findAll", query = "SELECT r FROM Record r")
@XmlType(name = "Record", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="Record")
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_record")
	@TableGenerator(name="sqlite_record", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="record")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "date")
	private Date date;
	
	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="record",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Data> data;
	
	
	
	
	public Record() { }
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	public Date getDate() { return this.date; }
	
	public void setDate(Date date) { this.date = date; }
	
	
	public List<Data> getData() { return this.data; }
	
	public void setData(List<Data> param) { this.data = param; }
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Record getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Record m = em.find(Record.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static List<Record> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Record> list = em.createNamedQuery("Measure.findAll", Record.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Record save(Record m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Record update(Record m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Record m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}

