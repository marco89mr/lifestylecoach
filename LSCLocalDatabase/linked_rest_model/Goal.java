package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


//@XmlType(name = "goal", propOrder = { "selfLink", "userLink", "record_type", "data_name", "operator", "value", "function", "reference", "perc", "repeat", "days", "deadlineLink" })
@XmlRootElement(name="goal")
public class Goal extends Base<Goal.Relation> {

	public enum Relation implements RelationInterface {
		all, self, user, deadlines;
	};
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( GoalRest.Relation.all, getBaseUrl()+path );
	}
	
	public static LinkRest<Link> generateSelfLink(int goal_id) {
		return new LinkRest<Link>( GoalRest.Relation.self, getBaseUrl()+path+"/"+goal_id );
	}
	
	public static LinkRest<Link> generateUserLink(int user_id) {
		return new LinkRest<Link>( GoalRest.Relation.user, getBaseUrl()+UserRest.path+"/"+user_id );
	}
	
	public static LinkRest<Link> generateDeadlinesLink(int goal_id) {
		return new LinkRest<Link>( GoalRest.Relation.deadlines, getBaseUrl()+path+"/"+goal_id+"/deadline" );
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String record_type;
	
	private String data_name;
	
	private String operator;
	
	private String value;
	
	private String function;
	
	private String reference;
	
	private String perc;
	
	private String repeat;
	
	private String days;
	
	
	
	
	public Goal() { }
	
	
	
	
	public String getRecord_type() { return this.record_type; }
	
	public void setRecord_type(String value) { this.record_type = value; }
	
	
	public String getData_name() { return this.data_name; }
	
	public void setData_name(String value) { this.data_name = value; }
	
	
	public String getOperator() { return this.operator; }
	
	public void setOperator(String operator) { this.operator = operator; }
	
	
	public String getValue() { return this.value; }
	
	public void setValue(String value) { this.value = value; }
	
	
	public String getFunction() { return this.function; }
	
	public void setFunction(String x) { this.function = x; }
	
	
	public String getReference() { return this.reference; }
	
	public void setReference(String x) { this.reference = x; }
	
	
	public String getPerc() { return this.perc; }
	
	public void setPerc(String x) { this.perc = x; }
	
	
	public String getReapeat() { return this.repeat; }
	
	public void setReapeat(String x) { this.repeat = x; }
	
	
	public String getDays() { return this.days; }
	
	public void setDays(String x) { this.days = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<Goal.Relation> getSelfLink() { return getLink(Goal.Relation.self); }
	
	public void setSelfLink(Link<Goal.Relation> link) { putLink(link); }
	
	@XmlElement(name="link")
	public Link<Goal.Relation> getUserLink() { return getLink(Goal.Relation.user); }
	
	public void setUserLink(Link<Goal.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<Goal.Relation> getDeadlineLink() { return getLink(Goal.Relation.deadlines); }
	
	public void setDeadlineLink(Link<Goal.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<Goal.Relation> getParentLink() { return getLink(Goal.Relation.user); }
	
	public void setParentLink(Link<Goal.Relation> link) { putLink(link);	}
	
	
	
	
}


