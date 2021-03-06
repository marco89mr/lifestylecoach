package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * The persistent class for the "user" database table.
 * 
 */
@XmlType(name = "user", propOrder = { "selfLink", "name", "mail", "password", "birthdate", "recordsLink", "goalsLink", "notificationsLink", "todosLink" })
@XmlRootElement(name="user")
public class User extends Base<User.Relation> {

	public enum Relation implements RelationInterface {
		all, self, records, goals, notifications, todos;
	}
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( UserRest.Relation.all, PathFactory.user().getCompletePath() );
	}
	
	public static LinkRest<Link> generateSelfLink(int id) {
		return new LinkRest<Link>( UserRest.Relation.self, getBaseUrl()+path+"/"+id );
	}
	
	public static LinkRest<Link> generateRecordsLink(int user_id) {
		return new LinkRest<Link>( UserRest.Relation.records, getBaseUrl()+path+"/"+user_id+"/record" );
	}
	
	public static LinkRest<Link> generateGoalsLink(int user_id) {
		return new LinkRest<Link>( UserRest.Relation.goals, getBaseUrl()+path+"/"+user_id+"/goal" );
	}
	
	public static LinkRest<Link> generateNotificationsLink(int user_id) {
		return new LinkRest<Link>( UserRest.Relation.notifications, getBaseUrl()+path+"/"+user_id+"/notification" );
	}
	
	public static LinkRest<Link> generateTodosLink(int user_id) {
		return new LinkRest<Link>( UserRest.Relation.todos, getBaseUrl()+path+"/"+user_id+"/todo" );
	}
	*/
	
	
	
	
	
	
	
	// From Here it's about the Rest Model for this Class
	//
	
	private String name;

	private String mail;
	
    private String birthdate;
	
	private String password;
	
	
	
	
	public User() { }
	
	
	
	
	
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
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<User.Relation> getSelfLink() { return getLink(User.Relation.self);	}
	
	public void setSelfLink(Link<User.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<User.Relation> getRecordsLink() { return getLink(User.Relation.records); }
	
	public void setRecordsLink(Link<User.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<User.Relation> getGoalsLink() { return getLink(User.Relation.goals); }
	
	public void setGoalsLink(Link<User.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<User.Relation> getNotificationsLink() { return getLink(User.Relation.notifications); }
	
	public void setNotificationsLink(Link<User.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<User.Relation> getTodosLink() { return getLink(User.Relation.todos); }
	
	public void setTodosLink(Link<User.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<User.Relation> getParentLink() { return getLink(User.Relation.self); }
	
	public void setParentLink(Link<User.Relation> link) { putLink(link);	}
	
	
	
	
	
	
	
}
