package lsc.localdatabase.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

import java.util.Date;
import java.util.List;

import lsc.localdatabase.dao.LifeCoachDao;
import lsc.localdatabase.model.Record;


@Entity
@Table(name="\"User\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@XmlRootElement(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// For sqlite in particular, you need to use the following @GeneratedValue annotation
	// This holds also for the other tables
	// SQLITE implements auto increment ids through named sequences that are stored in a 
	// special table named "sqlite_sequence"
	@GeneratedValue
	(generator="sqlite_user")
	@TableGenerator(name="sqlite_user", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="user")
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"name\"")
	private String name;

	@Column(name="\"mail\"")
	private String mail;
	
	//@Temporal(TemporalType.DATE)
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
	
	
	
	
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	public String getName() { return this.name;	}
	
	public void setName(String name) { this.name = name; }
	
	
	public String getBirthdate() { return this.birthdate; }
	
	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
	
	
	public String getMail() { return this.mail; }
	
	public void setMail(String email) {	this.mail = email; }
	
	
	public String getPassword() { return this.password;	}
	
	public void setPassword(String password) { this.password = password; }
	
	
	@XmlElementWrapper(name = "Measurements")
	public List<Record> getMeasure() { return record; }

	public void setMeasure(List<Record> param) { this.record = param;	}
	
	
	@XmlElementWrapper(name = "Goals")
	public List<Goal> getGoal() { return goal; }

	public void setGoal(List<Goal> param) { this.goal = param; }

	
	@XmlElementWrapper(name = "Notifications")
	public List<Notification> getNotification() { return notification; }

	public void setNotification(List<Notification> param) { this.notification = param; }

	
	@XmlElementWrapper(name = "Notifications")
	public List<ToDo> getTodo() { return todo; }

	public void setTodo(List<ToDo> param) { this.todo = param; }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Database operations
	// 
	
	public static User getById(int id) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		User u = em.find(User.class, id);
		LifeCoachDao.instance.closeConnections(em);
		return u;
	}
	
	public static List<User> getAll() {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
	    List<User> list = em.createNamedQuery("User.findAll", User.class).getResultList();
	    LifeCoachDao.instance.closeConnections(em);
	    return list;
	}
	
	public static User save(User u) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(u);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return u;
	}
	
	public static User update(User u) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		u=em.merge(u);
		tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	    return u;
	}
	
	public static void remove(User u) {
		EntityManager em = LifeCoachDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    u=em.merge(u);
	    em.remove(u);
	    tx.commit();
	    LifeCoachDao.instance.closeConnections(em);
	}
}
