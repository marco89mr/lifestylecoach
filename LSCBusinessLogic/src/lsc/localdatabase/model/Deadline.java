package lsc.localdatabase.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


//@XmlType(name = "Deadline", propOrder = { "mid", "dateRegistered", "measureType", "measureValue", "measureValueType" })
@XmlRootElement(name="deadline")
public class Deadline extends EntityModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private Goal goal;
	
	private String start_date;
	
	private String end_date;
	
	private String status;
	
	private Record record;
	
	private List<Notification> notification;
	
	private Link goal_link;
	
	private Link record_link;
	
	
	
	
	
	public Deadline() { }
	
	
	

	@XmlTransient
	public int getId() { return this.id; }
	
	public void setId(int id) {	this.id = id; }
	
	
	@XmlTransient
	public Goal getGoal() { return goal; }

	public void setGoal(Goal goal) { this.goal = goal; }
	
	
	public String getStart_date() { return this.start_date; }
	
	public void setStart_date(String value) { this.start_date = value; }
	
	
	public String getEnd_date() { return this.end_date; }
	
	public void setEnd_date(String value) { this.end_date = value; }
	
	
	public String getStatus() { return this.status; }
	
	public void setStatus(String x) { this.status = x; }
	

	@XmlTransient
	public Record getRecord() { return this.record; }
	
	public void setRecord(Record x) { this.record = x; }
	

	@XmlTransient
	public List<Notification> getNotification() { return this.notification; }
	
	public void setNotification(List<Notification> param) { this.notification = param; }
	
	
	
	// Links for related resources
	// 
	
	@XmlElement(name="link")
	public Link getSelfLink() { return Link.create("deadline", this._getUrl() ); }
	
	public void setSelfLink(Link link) {
		if(link.getRel().equals("goal"))
			this.goal_link = link;
		if(link.getRel().equals("record"))
			this.record_link = link;
	}
	
	
	@XmlElement(name="link")
	public Link getGoalLink() { return Link.create("goal", this.goal._getUrl() ); }
	
	public void setGoalLink(Link link) { setSelfLink(link); }
	
	
	@XmlElement(name="link")
	public Link getRecordLink() { return Link.create("record", this.record._getUrl() ); }
	
	public void setRecordLink(Link link) { setSelfLink(link); }
	
	
	
	// Accessory methods
	// 
	
	public String _getUrl() {
		return _getBaseUrl()+"deadline/"+this.id;
	}
	
	
}


