package lsc.localdatabase.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


@Entity
@Table(name="\"notification\"")
@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
public class Notification extends Base {
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
	
	
	

	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	public Deadline getDeadline() {	return deadline; }
	
	public void setDeadline(Deadline d) { this.deadline = d; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
}


