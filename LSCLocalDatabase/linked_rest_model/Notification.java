package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "notification", propOrder = { "selfLink", "userLink", "date", "message", "type", "status", "deadlineLink" })
@XmlRootElement(name="notification")
public class Notification extends Base<Notification.Relation> {

	public enum Relation implements RelationInterface {
		all, self, user, deadline;
	};

	@XmlEnum(String.class)
	public static enum Status {
		read, unread;
	};
	
	
	
	
	
	
	private String date;
	
	private String type;

	private String message;
	
	private Status status;
	
	
	
	
	
	
	public Notification() { }
	
	
	

	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public Status getStatus() { return this.status; }
	
	public void setStatus(Status x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<Notification.Relation> getSelfLink() { return getLink(Notification.Relation.self); }
	
	public void setSelfLink(Link<Notification.Relation> link) { putLink(link); }
	
	@XmlElement(name="link")
	public Link<Notification.Relation> getUserLink() { return getLink(Notification.Relation.user); }
	
	public void setUserLink(Link<Notification.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<Notification.Relation> getDeadlineLink() { return getLink(Notification.Relation.deadline); }
	
	public void setDeadlineLink(Link<Notification.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<Notification.Relation> getParentLink() { return getLink(Notification.Relation.user); }
	
	public void setParentLink(Link<Notification.Relation> link) { putLink(link);	}
	
	
	
	
}


