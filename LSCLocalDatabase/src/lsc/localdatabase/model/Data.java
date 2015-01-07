package lsc.localdatabase.model;

import lsc.localdatabase.dao.LifeCoachDao;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@Table(name="Data")
@NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d")
@XmlType(name = "Data", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="Data")
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_data")
	@TableGenerator(name="sqlite_data", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="data")
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="record_id",referencedColumnName="id", insertable = true, updatable = true)
	private Record record;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "value")
	private String value;
	
	
	
	
	
	public Data() { }
	
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient // to avoid infinite loop on serialization
	public Record getUser() {	return record; }
	
	public void setUser(Record record) { this.record = record;	}
	
	
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static Data getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		Data m = em.find(Data.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return m;
	}
	
	public static List<Data> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<Data> list = em.createNamedQuery("Measure.findAll", Data.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Data save(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static Data update(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		m=em.merge(m);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return m;
	}
	
	public static void remove(Data m) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    m=em.merge(m);
	    em.remove(m);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
	
}

