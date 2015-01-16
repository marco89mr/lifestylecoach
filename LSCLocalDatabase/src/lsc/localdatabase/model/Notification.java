package lsc.localdatabase.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name="\"notification\"")
@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
@XmlType(name = "notification", propOrder = { "selfLink", "userLink", "date", "message", "type", "status", "deadlineLink" })
@XmlRootElement(name="notification")
public class Notification extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_notification")
	@TableGenerator(name="sqlite_notification", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="notification")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"date\"")
	private String date;
	
	@Column(name = "\"type\"")
	private String type;

	@ManyToOne
	@JoinColumn(name="\"deadline_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private Deadline deadline;
	
	@Column(name = "\"message\"")
	private String message;
	
	@Column(name = "\"status\"")
	private String status;
	
	
	
	
	
	
	public Notification() { }
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	@XmlTransient
	public Deadline getDeadline() {	return deadline; }
	
	public void setDeadline(Deadline d) { this.deadline = d; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return getLink("self"); }
	
	public void setSelfLink(Link link) { putLink(link); }
	
	@XmlElement(name="link")
	public Link getUserLink() { return getLink("user"); }
	
	public void setUserLink(Link link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link getDeadlineLink() { return getLink("deadlines"); }
	
	public void setDeadlineLink(Link link) { putLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"notification/"+this.getId();
	}
	
	
}


