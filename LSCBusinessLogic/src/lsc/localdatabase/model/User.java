package lsc.localdatabase.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the "user" database table.
 * 
 */
@Entity
@Table(name="\"user\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u") 
@XmlRootElement(name="user")
@XmlType(name = "user", propOrder = { "selfLink", "name", "mail", "password", "birthdate", "recordsLink", "goalsLink", "notificationsLink", "todosLink" })
public class User extends EntityModel implements Serializable {
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
	
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) { this.id = id; }
	
	
	@XmlElement(name="name")
	public String getName() { return this.name;	}
	
	public void setName(String name) { this.name = name; }
	
	
	@XmlElement(name="birthdate")
	public String getBirthdate() { return this.birthdate; }
	
	public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
	
	
	@XmlElement(name="mail")
	public String getMail() { return this.mail; }
	
	public void setMail(String email) {	this.mail = email; }
	
	
	@XmlElement(name="password")
	public String getPassword() { return this.password;	}
	
	public void setPassword(String password) { this.password = password; }
	
	
	@XmlTransient
	public List<Record> getRecord() { return record; }

	public void setRecord(List<Record> param) { this.record = param;	}
	
	
	@XmlTransient
	public List<Goal> getGoal() { return goal; }

	public void setGoal(List<Goal> param) { this.goal = param; }

	
	@XmlTransient
	public List<Notification> getNotification() { return notification; }

	public void setNotification(List<Notification> param) { this.notification = param; }

	
	@XmlTransient
	public List<ToDo> getTodo() { return todo; }

	public void setTodo(List<ToDo> param) { this.todo = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("self", this._getUrl() );	}
	
	public void setSelfLink(Link link) { System.out.println("DEBUG: User.setSelfLink()"); }
	
	
	@XmlElement(name="link")
	public Link getRecordsLink() { return Link.create("records", this._getUrl()+"/record"); }
	
	public void setRecordsLink(LinkCollection lc) { }
	
	
	@XmlElement(name="link")
	public Link getGoalsLink() { return Link.create("goals", this._getUrl()+"/goal"); }
	
	public void setGoalsLink(Link lc) { }
	
	
	@XmlElement(name="link")
	public Link getNotificationsLink() { return Link.create("notifications", this._getUrl()+"/notification"); }
	
	public void setNotificationsLink(Link lc) { }
	
	
	@XmlElement(name="link")
	public Link getTodosLink() { return Link.create("todos", this._getUrl()+"/todo"); }
	
	public void setTodosLink(Link lc) { }
	
	
	
	// Accessory methods
	// 
	
	public Date _getBirthdate() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.parse(this.birthdate);
	}
	
	public void _setBirthdate(Date birthdate) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    this.birthdate = sdf.format(birthdate);
	}
	
	public String _getUrl() {
		return _getBaseUrl()+"user/"+this.id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
