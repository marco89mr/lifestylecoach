package lsc.localdatabase.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "notification", propOrder = { "selfLink", "userLink", "date", "message", "type", "status", "deadlineLink" })
@XmlRootElement(name="notification")
public class NotificationRest extends BaseRest<NotificationRest.Relation> {

	public enum Relation implements RelationInterface {
		all, self, user, deadline;
	};
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( NotificationRest.Relation.all, getBaseUrl()+path );
	}
	
	public static LinkRest<Link> generateSelfLink(int id) {
		return new LinkRest<Link>( NotificationRest.Relation.self, getBaseUrl()+path+"/"+id );
	}
	
	public static LinkRest<Link> generateUserLink(int user_id) {
		return new LinkRest<Link>( NotificationRest.Relation.user, getBaseUrl()+UserRest.path+"/"+user_id );
	}
	
	public static LinkRest<Link> generateDeadlinesLink(int goal_id) {
		return new LinkRest<Link>( NotificationRest.Relation.deadlines, getBaseUrl()+path+"/"+goal_id+"/deadline" );
	}
	*/
	
	
	
	
	
	
	private String date;
	
	private String type;

	private String message;
	
	private String status;
	
	
	
	
	
	
	public NotificationRest() { }
	
	
	

	public String getDate() { return this.date; }
	
	public void setDate(String date) { this.date = date; }
	
	
	public String getType() { return this.type; }
	
	public void setType(String value) { this.type = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public LinkRest<NotificationRest.Relation> getSelfLink() { return getLink(NotificationRest.Relation.self); }
	
	public void setSelfLink(LinkRest<NotificationRest.Relation> link) { putLink(link); }
	
	@XmlElement(name="link")
	public LinkRest<NotificationRest.Relation> getUserLink() { return getLink(NotificationRest.Relation.user); }
	
	public void setUserLink(LinkRest<NotificationRest.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public LinkRest<NotificationRest.Relation> getDeadlineLink() { return getLink(NotificationRest.Relation.deadline); }
	
	public void setDeadlineLink(LinkRest<NotificationRest.Relation> link) { putLink(link); }
	
	
	
	
}


