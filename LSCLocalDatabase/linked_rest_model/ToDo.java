package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;



@XmlType(name = "todo", propOrder = { "selfLink", "userLink", "by_date", "message", "status" })
@XmlRootElement(name="todo")
public class ToDo extends Base<ToDo.Relation> {

	public enum Relation implements RelationInterface {
		all, self, user;
	};
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( ToDoRest.Relation.all, getBaseUrl()+path );
	}
	
	public static LinkRest<Link> generateSelfLink(int id) {
		return new LinkRest<Link>( ToDoRest.Relation.self, getBaseUrl()+path+"/"+id );
	}
	
	public static LinkRest<Link> generateUserLink(int user_id) {
		return new LinkRest<Link>( ToDoRest.Relation.user, getBaseUrl()+UserRest.path+"/"+user_id );
	}
	*/
	
	
	
	
	
	private String by_date;
	
	private String message;
	
	private String status;
	
	
	
	
	
	
	public ToDo() { }
	
	
	
	
	
	public String getBy_date() { return this.by_date; }
	
	public void setBy_date(String value) { this.by_date = value; }
	
	
	public String getMessage() { return this.message; }
	
	public void setMessage(String message) { this.message = message; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<ToDo.Relation> getSelfLink() { return getLink(ToDo.Relation.self); }
	
	public void setSelfLink(Link<ToDo.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link", required=true)
	public Link<ToDo.Relation> getUserLink() { return getLink(ToDo.Relation.user); }
	
	public void setUserLink(Link<ToDo.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<ToDo.Relation> getParentLink() { return getLink(ToDo.Relation.user); }
	
	public void setParentLink(Link<ToDo.Relation> link) { putLink(link);	}
	
	
	
}


