package lsc.localdatabase.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "notification", propOrder = { "selfLink", "userLink", "date", "message", "type", "status", "deadlineLink" })
@XmlRootElement(name="notification")
public class Notification extends EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private User user;
	
	private String date;
	
	private String type;

	private Deadline deadline;
	
	private String message;
	
	private String status;
	
	private Link user_link;
	
	
	
	
	
	
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
	public Link getSelf_link() { return Link.create("self", this._getUrl() ); }
	
	public void setSelf_link(Link link) {
		if(link.getRel().equals("user"))
			this.user_link = link;
	}
	
	@XmlElement(name="link")
	public Link getUser_link() { return Link.create("user", this.user._getUrl() ); }
	
	public void setUser_link(Link link) { setSelf_link(link); }
	
	
	@XmlElement(name="link")
	public Link getDeadline_link() { return Link.create("deadlines", this._getUrl()+"/deadline"); }
	
	public void setDeadline_link(Link link) { setSelf_link(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"notification/"+this.getId();
	}
	
	
}


