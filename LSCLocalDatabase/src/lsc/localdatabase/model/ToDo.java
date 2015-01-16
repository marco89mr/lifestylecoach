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
@Table(name="\"todo\"")
@NamedQuery(name = "ToDo.findAll", query = "SELECT d FROM ToDo d")
@XmlType(name = "todo", propOrder = { "selfLink", "userLink", "by_date", "message", "status" })
@XmlRootElement(name="todo")
public class ToDo extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="sqlite_toDo")
	@TableGenerator(name="sqlite_toDo", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq",
	    pkColumnValue="toDo")
	@Column(name = "\"id\"")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="\"user_id\"",referencedColumnName="\"id\"", insertable = true, updatable = true)
	private User user;
	
	@Column(name = "\"by_date\"")
	private String by_date;
	
	@Column(name = "\"message\"")
	private String message;
	
	@Column(name = "\"status\"")
	private String status;
	
	
	
	
	
	
	public ToDo() { }
	
	
	
	
	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public User getUser() {	return user; }
	
	public void setUser(User user) { this.user = user;	}
	
	
	public String getBy_date() { return this.by_date; }
	
	public void setBy_date(String value) { this.by_date = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return getLink("self"); }
	
	public void setSelfLink(Link link) { putLink(link); }
	
	
	@XmlElement(name="link", required=true)
	public Link getUserLink() { return getLink("user"); }
	
	public void setUserLink(Link link) { putLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"todo/"+this.getId();
	}
	
	
}


