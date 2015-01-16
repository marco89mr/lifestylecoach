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


@XmlType(name = "todo", propOrder = { "selfLink", "userLink", "by_date", "message", "status" })
@XmlRootElement(name="todo")
public class ToDo extends EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private User user;
	
	private String by_date;
	
	private String message;
	
	private String status;
	
	private Link user_link;
	
	
	
	
	
	
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
	public Link getSelf_link() { return Link.create("self", this._getUrl() ); }
	
	public void setSelf_link(Link link) {
		if(link.getRel().equals("user"))
			this.user_link = link;
	}
	
	
	@XmlElement(name="link", required=true)
	public Link getUser_link() { return Link.create("user", this.getUser()._getUrl() ); }
	
	public void setUser_link(Link link) { setSelf_link(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"todo/"+this.getId();
	}
	
	
}


