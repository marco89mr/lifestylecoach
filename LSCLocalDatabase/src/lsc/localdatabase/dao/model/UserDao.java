package lsc.localdatabase.dao.model;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the "user" database table.
 * 
 */
@Entity
@Table(name="\"user\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM UserDao u") 
public class UserDao extends BaseDao {
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
	private List<RecordDao> record;

	// mappedBy must be equal to the name of the attribute in Goal that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<GoalDao> goal;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<NotificationDao> notification;

	// mappedBy must be equal to the name of the attribute in Notification that maps this relation
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ToDoDao> todo;
	
	
	
	
	
	public UserDao() { }
	
	
	
	
	
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
	
	
	public List<RecordDao> getRecord() { return record; }

	public void setRecord(List<RecordDao> param) { this.record = param;	}
	
	
	public List<GoalDao> getGoal() { return goal; }

	public void setGoal(List<GoalDao> param) { this.goal = param; }

	
	public List<NotificationDao> getNotification() { return notification; }

	public void setNotification(List<NotificationDao> param) { this.notification = param; }

	
	public List<ToDoDao> getTodo() { return todo; }

	public void setTodo(List<ToDoDao> param) { this.todo = param; }
	
	
	
	
	
	
	
}
