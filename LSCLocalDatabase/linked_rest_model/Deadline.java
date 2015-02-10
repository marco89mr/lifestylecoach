package lsc.rest.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


//@XmlType(name = "Deadline", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="deadline")
public class Deadline extends Base<Deadline.Relation> {

	public enum Relation implements RelationInterface {
		all, self, goal, record;
	};
	
	/*
	public static LinkRest<Link> generateAllLink() {
		return new LinkRest<Link>( DeadlineRest.Relation.all, getBaseUrl()+path );
	}
	
	public static LinkRest<Link> generateSelfLink(int deadline_id) {
		return new LinkRest<Link>( DeadlineRest.Relation.self, getBaseUrl()+path+"/"+deadline_id );
	}
	
	public static LinkRest<Link> generateRecordLink(int record_id) {
		return new LinkRest<Link>( DeadlineRest.Relation.record, getBaseUrl()+RecordRest.path+"/"+record_id );
	}
	
	public static LinkRest<Link> generateGoalLink(int goal_id) {
		return new LinkRest<Link>( DeadlineRest.Relation.goal, getBaseUrl()+GoalRest.path+"/"+goal_id );
	}
	*/
	
	
	
	
	
	
	
	
	private String start_date;
	
	private String end_date;
	
	private String status;
	
	
	
	
	
	
	public Deadline() { }
	
	
	

	
	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String value) { this.start_date = value; }
	
	
	public String getEnd_date() { return this.end_date; }
	
	public void setEnd_date(String value) { this.end_date = value; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link<Deadline.Relation> getSelfLink() {	return getLink(Deadline.Relation.self); }
	
	public void setSelfLink(Link<Deadline.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<Deadline.Relation> getGoalLink() { return getLink(Deadline.Relation.goal); }
	
	public void setGoalLink(Link<Deadline.Relation> link) { putLink(link); }
	
	
	@XmlElement(name="link")
	public Link<Deadline.Relation> getRecordLink() { return getLink(Deadline.Relation.record); }
	
	public void setRecordLink(Link<Deadline.Relation> link) { putLink(link); }
	
	@XmlTransient
	public Link<Deadline.Relation> getParentLink() { return getLink(Deadline.Relation.goal); }
	
	public void setParentLink(Link<Deadline.Relation> link) { putLink(link);	}
	
	
	
}


